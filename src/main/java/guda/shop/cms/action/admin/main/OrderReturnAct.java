package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.*;
import guda.shop.cms.manager.*;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import guda.shop.core.web.WebErrors;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderReturnAct {
    private static final Logger log = LoggerFactory.getLogger(OrderReturnAct.class);
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    @Autowired
    private OrderReturnMng manager;

    @Autowired
    private ProductMng productMng;

    @Autowired
    private ProductFashionMng productFashionMng;

    @Autowired
    private ShopMemberMng shopMemberMng;

    @Autowired
    private ShopScoreMng shopScoreMng;

    @Autowired
    private PaymentPluginsMng paymentPluginsMng;

    @Autowired
    private OrderMng orderMng;

    public static String refund_fastpay_by_platform_pwd(Map<String, String> sParaTemp)
            throws Exception {

        sParaTemp.put("service", "refund_fastpay_by_platform_pwd");

        sParaTemp.put("_input_charset", "UTF-8");

        String strButtonName = "退款";

        return buildForm(sParaTemp, "https://mapi.alipay.com/gateway.do?", "get", strButtonName);
    }

    public static String buildForm(Map<String, String> sParaTemp, String gateway, String strMethod, String strButtonName) {

        Map sPara = buildRequestPara(sParaTemp);

        List keys = new ArrayList((Collection) sPara.keySet());


        StringBuffer sbHtml = new StringBuffer();


        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + gateway +
                "_input_charset=" + "UTF-8" + "\" method=\"" + strMethod +
                "\">");


        for (int i = 0; i < keys.size(); i++) {

            String name = (String) keys.get(i);

            String value = (String) sPara.get(name);


            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }


        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");

        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");


        return sbHtml.toString();
    }

    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {

        Map sPara = paraFilter(sParaTemp);


        String mysign = buildMysign(sPara);


        sPara.put("sign", mysign);

        sPara.put("sign_type", "MD5");


        return sPara;
    }

    public static String buildMysign(Map<String, String> sArray) {

        String prestr = createLinkString(sArray);

        prestr = prestr + (String) sArray.get("key");

        String mysign = md5(prestr);

        return mysign;
    }

    public static String md5(String text) {

        return DigestUtils.md5Hex(getContentBytes(text, "UTF-8"));
    }

    private static byte[] getContentBytes(String content, String charset) {

        if ((charset == null) || ("".equals(charset))) {

            return content.getBytes();
        }
        try {

            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
        }

        throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
    }

    public static String createLinkString(Map<String, String> params) {

        List keys = new ArrayList((Collection) params.keySet());

        Collections.sort(keys);


        String prestr = "";


        for (int i = 0; i < keys.size(); i++) {

            String key = (String) keys.get(i);

            String value = (String) params.get(key);


            if (i == keys.size() - 1)
                prestr = prestr + key + "=" + value;
            else {

                prestr = prestr + key + "=" + value + "&";
            }
        }


        return prestr;
    }

    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map result = new HashMap();


        if ((sArray == null) || (sArray.size() <= 0)) {

            return result;
        }


        for (String key : sArray.keySet()) {

            String value = (String) sArray.get(key);

            if ((value != null) && (!value.equals("")) && (!key.equalsIgnoreCase("sign")) &&
                    (!key.equalsIgnoreCase("sign_type"))) {

                result.put(key, value);
            }
        }

        return result;
    }

    @RequestMapping({"/orderReturn/v_list.do"})
    public String list(Integer status, Integer pageNo, HttpServletRequest request, ModelMap model) {

        Pagination pagination = this.manager.getPage(status, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        model.addAttribute("status", status);

        return "orderReturn/list";
    }

    @RequestMapping({"/orderReturn/v_view.do"})
    public String view(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("order", this.manager.findById(id).getOrder());

        return "orderReturn/view";
    }

    @RequestMapping({"/orderReturn/o_affirm.do"})
    public String affirm(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        OrderReturn entity = this.manager.findById(id);

        entity.setStatus(Integer.valueOf(2));

        this.manager.update(entity);

        model.addAttribute("order", this.manager.findById(id).getOrder());

        return "orderReturn/view";
    }

    @RequestMapping({"/orderReturn/o_sendback.do"})
    public String sendback(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        OrderReturn entity = this.manager.findById(id);

        entity.setStatus(Integer.valueOf(3));

        this.manager.update(entity);

        model.addAttribute("order", this.manager.findById(id).getOrder());

        return "orderReturn/view";
    }

    @RequestMapping({"/orderReturn/o_accomplish.do"})
    public String accomplish(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        OrderReturn entity = this.manager.findById(id);

        entity.setStatus(Integer.valueOf(7));
        ProductFashion fashion;

        for (OrderItem item : entity.getOrder().getItems()) {

            Product product = item.getProduct();

            if (item.getProductFash() != null) {

                fashion = item.getProductFash();

                fashion.setStockCount(Integer.valueOf(fashion.getStockCount().intValue() + item.getCount().intValue()));

                product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));

                product.setSaleCount(Integer.valueOf(product.getSaleCount().intValue() - item.getCount().intValue()));

                product.setLiRun(Double.valueOf(product.getLiRun().doubleValue() - item.getCount().intValue() * (fashion.getSalePrice().doubleValue() - fashion.getCostPrice().doubleValue())));

                this.productFashionMng.update(fashion);
            } else {

                product.setLiRun(Double.valueOf(product.getLiRun().doubleValue() - item.getCount().intValue() * (product.getSalePrice().doubleValue() - product.getCostPrice().doubleValue())));

                product.setSaleCount(Integer.valueOf(product.getSaleCount().intValue() - item.getCount().intValue()));

                product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
            }

            this.productMng.updateByUpdater(product);
        }


        ShopMember member = entity.getOrder().getMember();

        member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - entity.getOrder().getScore().intValue()));

        this.shopMemberMng.update(member);

        List<ShopScore> list = this.shopScoreMng.getlist(Long.toString(entity.getOrder().getCode()));

        for (ShopScore s : list) {

            this.shopScoreMng.deleteById(s.getId());
        }

        Order order = this.manager.findById(id).getOrder();

        order.setStatus(Integer.valueOf(3));

        this.manager.update(entity);

        this.orderMng.updateByUpdater(order);

        model.addAttribute("order", this.manager.findById(id).getOrder());

        return "orderReturn/view";
    }

    @RequestMapping({"/orderReturn/o_reimburse.do"})
    public String reimburse(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        OrderReturn entity = this.manager.findById(id);

        if (entity.getPayType().intValue() == 2) {

            PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode("alipay");

            PrintWriter out = null;
            try {

                String aliURL = alipayReturn(paymentPlugins, web, entity, request, model);

                response.setContentType("text/html;charset=UTF-8");

                out = response.getWriter();

                out.print(aliURL);
            } catch (Exception localException) {
            } finally {

                out.close();
            }
        } else {

            ShopMember shopMember = entity.getOrder().getMember();

            shopMember.setMoney(Double.valueOf(shopMember.getFreezeScore().intValue() + entity.getMoney().doubleValue()));

            this.shopMemberMng.update(shopMember);
        }

        entity.setStatus(Integer.valueOf(6));

        this.manager.update(entity);

        model.addAttribute("order", this.manager.findById(id).getOrder());

        return "orderReturn/view";
    }

    private String alipayReturn(PaymentPlugins paymentPlugins, Website web, OrderReturn orderReturn, HttpServletRequest request, ModelMap model) {

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date1 = sdf.format(date);


        String batch_no = date1.concat(String.valueOf(orderReturn.getId().longValue() * 100L));


        String refund_date = sdf1.format(date);


        String batch_num = String.valueOf(1);


        String detail_data = orderReturn.getOrder().getId().toString() + "^" + 1.0D + "^" + orderReturn.getShopDictionary().getName();


        String seller_email = paymentPlugins.getSellerEmail();


        String seller_user_id = paymentPlugins.getSellerKey();


        String notify_url = "http://" + web.getDomain() + "/cart/aliReturn.jspx";


        Map sParaTemp = new HashMap();

        sParaTemp.put("partner", paymentPlugins.getPartner());

        sParaTemp.put("seller_email", seller_email);

        sParaTemp.put("seller_user_id", seller_user_id);


        sParaTemp.put("batch_no", batch_no);

        sParaTemp.put("refund_date", refund_date);

        sParaTemp.put("batch_num", batch_num);

        sParaTemp.put("detail_data", detail_data);

        sParaTemp.put("notify_url", notify_url);


        String sHtmlText = null;
        try {

            sHtmlText = refund_fastpay_by_platform_pwd(sParaTemp);
        } catch (Exception e) {

            e.printStackTrace();
        }


        return sHtmlText;
    }

    @RequestMapping({"/orderReturn/o_salesreturn.do"})
    public String salesreturn(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        OrderReturn entity = this.manager.findById(id);

        entity.setStatus(Integer.valueOf(5));

        this.manager.update(entity);

        model.addAttribute("order", this.manager.findById(id).getOrder());

        return "orderReturn/view";
    }

    @RequestMapping({"/orderReturn/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        OrderReturn[] beans = this.manager.deleteByIds(ids);

        for (OrderReturn bean : beans) {

            log.info("delete OrderReturn id={}", bean.getId());
        }

        return list(null, pageNo, request, model);
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        Website web = SiteUtils.getWeb(request);

        if (vldExist(id, web.getId(), errors)) {

            return errors;
        }

        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        Website web = SiteUtils.getWeb(request);

        errors.ifEmpty(ids, "ids");

        for (Long id : ids) {

            vldExist(id, web.getId(), errors);
        }

        return errors;
    }

    private boolean vldExist(Long id, Long webId, WebErrors errors) {

        if (errors.ifNull(id, "id")) {

            return true;
        }

        OrderReturn entity = this.manager.findById(id);

        if (errors.ifNotExist(entity, OrderReturn.class, id)) {

            return true;
        }

        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.OrderReturnAct
 * JD-Core Version:    0.6.2
 */