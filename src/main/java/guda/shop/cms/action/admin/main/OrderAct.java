package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.*;
import guda.shop.cms.manager.*;
import guda.shop.cms.web.threadvariable.AdminThread;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.common.web.RequestUtils;
import guda.shop.common.web.ResponseUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import guda.shop.core.web.WebErrors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderAct {
    public static final String ALL = "all";
    public static final String PENDING = "pending";
    public static final String PROSESSING = "processing";
    public static final String DELIVERED = "delivered";
    public static final String COMPLETE = "complete";
    /*  79 */   public static final String[] TYPES = {"all", "pending", "processing", "delivered",
/*  80 */     "complete"};
    /*  73 */   private static final Logger log = LoggerFactory.getLogger(OrderAct.class);
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    @Autowired
    private ShippingMng shippingMng;

    @Autowired
    private PaymentMng paymentMng;

    @Autowired
    private OrderMng manager;

    @Autowired
    private OrderReturnMng orderReturnMng;

    @Autowired
    private ShopMemberMng shopMemberMng;

    @Autowired
    private ShopScoreMng shopScoreMng;

    @Autowired
    private ProductMng productMng;

    @Autowired
    private ProductFashionMng productFashionMng;

    @Autowired
    private OrderItemMng orderItemMng;

    @Autowired
    private GatheringMng gatheringMng;

    public static String refund_fastpay_by_platform_pwd(Map<String, String> sParaTemp)
            throws Exception {
/* 408 */
        sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
/* 409 */
        sParaTemp.put("_input_charset", "UTF-8");
/* 410 */
        String strButtonName = "退款";
/* 411 */
        return buildForm(sParaTemp, "https://mapi.alipay.com/gateway.do?", "get", strButtonName);
    }

    public static String buildForm(Map<String, String> sParaTemp, String gateway, String strMethod, String strButtonName) {
/* 425 */
        Map sPara = buildRequestPara(sParaTemp);
/* 426 */
        List keys = new ArrayList((Collection) sPara.keySet());

/* 428 */
        StringBuffer sbHtml = new StringBuffer();

/* 430 */
        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + gateway +
/* 431 */       "_input_charset=" + "UTF-8" + "\" method=\"" + strMethod +
/* 432 */       "\">");

/* 434 */
        for (int i = 0; i < keys.size(); i++) {
/* 435 */
            String name = (String) keys.get(i);
/* 436 */
            String value = (String) sPara.get(name);

/* 438 */
            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

/* 442 */
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
/* 443 */
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

/* 445 */
        return sbHtml.toString();
    }

    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
/* 456 */
        Map sPara = paraFilter(sParaTemp);

/* 458 */
        String mysign = buildMysign(sPara);

/* 461 */
        sPara.put("sign", mysign);
/* 462 */
        sPara.put("sign_type", "MD5");

/* 464 */
        return sPara;
    }

    public static String buildMysign(Map<String, String> sArray) {
/* 468 */
        String prestr = createLinkString(sArray);
/* 469 */
        prestr = prestr + (String) sArray.get("key");
/* 470 */
        String mysign = md5(prestr);
/* 471 */
        return mysign;
    }

    public static String md5(String text) {
/* 476 */
        return DigestUtils.md5Hex(getContentBytes(text, "UTF-8"));
    }

    private static byte[] getContentBytes(String content, String charset) {
/* 482 */
        if ((charset == null) || ("".equals(charset))) {
/* 483 */
            return content.getBytes();
        }
        try {
/* 487 */
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
        }
/* 489 */
        throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
    }

    public static String createLinkString(Map<String, String> params) {
/* 499 */
        List keys = new ArrayList((Collection) params.keySet());
/* 500 */
        Collections.sort(keys);

/* 502 */
        String prestr = "";

/* 504 */
        for (int i = 0; i < keys.size(); i++) {
/* 505 */
            String key = (String) keys.get(i);
/* 506 */
            String value = (String) params.get(key);

/* 508 */
            if (i == keys.size() - 1)
/* 509 */ prestr = prestr + key + "=" + value;
            else {
/* 511 */
                prestr = prestr + key + "=" + value + "&";
            }
        }

/* 515 */
        return prestr;
    }

    public static Map<String, String> paraFilter(Map<String, String> sArray) {
/* 526 */
        Map result = new HashMap();

/* 528 */
        if ((sArray == null) || (sArray.size() <= 0)) {
/* 529 */
            return result;
        }

/* 532 */
        for (String key : sArray.keySet()) {
/* 533 */
            String value = (String) sArray.get(key);
/* 534 */
            if ((value != null) && (!value.equals("")) && (!key.equalsIgnoreCase("sign")) &&
/* 535 */         (!key.equalsIgnoreCase("sign_type"))) {
/* 538 */
                result.put(key, value);
            }
        }
/* 541 */
        return result;
    }

    @RequestMapping({"/order/v_list.do"})
    public String list(Long code, Integer status, Integer paymentStatus, Integer shippingStatus, Long paymentId, Long shoppingId, Date startTime, Date endTime, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  86 */
        Website web = SiteUtils.getWeb(request);
/*  87 */
        String userName = RequestUtils.getQueryParam(request, "userName");
/*  88 */
        userName = StringUtils.trim(userName);
/*  89 */
        Pagination pagination = this.manager.getPage(web.getId(), null, null, userName,
/*  90 */       paymentId, shoppingId, startTime, endTime, null, null, status, paymentStatus, shippingStatus, code,
/*  91 */       SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  92 */
        model.addAttribute("pagination", pagination);

/*  94 */
        List shippingList = this.shippingMng.getList(web.getId(), true);
/*  95 */
        List paymentList = this.paymentMng.getList(web.getId(), true);
/*  96 */
        model.addAttribute("paymentList", paymentList);
/*  97 */
        model.addAttribute("shippingList", shippingList);
/*  98 */
        model.addAttribute("paymentId", paymentId);
/*  99 */
        model.addAttribute("shoppingId", shoppingId);
/* 100 */
        model.addAttribute("userName", userName);
/* 101 */
        model.addAttribute("startTime", startTime);
/* 102 */
        model.addAttribute("endTime", endTime);
/* 103 */
        model.addAttribute("status", status);
/* 104 */
        model.addAttribute("paymentStatus", paymentStatus);
/* 105 */
        model.addAttribute("shippingStatus", shippingStatus);
/* 106 */
        return "order/list";
    }

    @RequestMapping({"/order/v_view.do"})
    public String view(Long id, HttpServletRequest request, ModelMap model) {
/* 112 */
        Website web = SiteUtils.getWeb(request);
/* 113 */
        WebErrors errors = validateEdit(id, request);
/* 114 */
        if (errors.hasErrors()) {
/* 115 */
            return errors.showErrorPage(model);
        }
/* 117 */
        model.addAttribute("order", this.manager.findById(id));
/* 118 */
        return "order/view";
    }

    @RequestMapping({"/order/o_affirm.do"})
    public String affirm(Long id, HttpServletRequest request, ModelMap model) {
/* 123 */
        Website web = SiteUtils.getWeb(request);
/* 124 */
        WebErrors errors = validateEdit(id, request);
/* 125 */
        if (errors.hasErrors()) {
/* 126 */
            return errors.showErrorPage(model);
        }
/* 128 */
        Order order = this.manager.findById(id);
/* 129 */
        if (order.getStatus().intValue() == 1) {
/* 130 */
            order.setStatus(Integer.valueOf(2));
/* 131 */
            this.manager.updateByUpdater(order);
        }
/* 133 */
        model.addAttribute("order", order);
/* 134 */
        return "order/view";
    }

    @RequestMapping({"/order/o_abolish.do"})
    public String abolish(Long id, HttpServletRequest request, ModelMap model) {
/* 139 */
        Website web = SiteUtils.getWeb(request);
/* 140 */
        WebErrors errors = validateEdit(id, request);
/* 141 */
        if (errors.hasErrors()) {
/* 142 */
            return errors.showErrorPage(model);
        }
/* 144 */
        Order order = this.manager.findById(id);
/* 145 */
        if ((order.getStatus().intValue() != 4) && (order.getShippingStatus().intValue() != 2) && (order.getPaymentStatus().intValue() != 2)) {
/* 146 */
            order.setStatus(Integer.valueOf(3));
            ProductFashion fashion;
/* 148 */
            for (OrderItem item : order.getItems()) {
/* 149 */
                Product product = item.getProduct();
/* 150 */
                if (item.getProductFash() != null) {
/* 151 */
                    fashion = item.getProductFash();
/* 152 */
                    fashion.setStockCount(Integer.valueOf(fashion.getStockCount().intValue() + item.getCount().intValue()));
/* 153 */
                    product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
/* 154 */
                    this.productFashionMng.update(fashion);
                } else {
/* 156 */
                    product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
                }
/* 158 */
                this.productMng.updateByUpdater(product);
            }

/* 161 */
            ShopMember member = order.getMember();
/* 162 */
            member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - order.getScore().intValue()));
/* 163 */
            this.shopMemberMng.update(member);
/* 164 */
            List<ShopScore> list = this.shopScoreMng.getlist(Long.toString(order.getCode()));
/* 165 */
            for (ShopScore s : list) {
/* 166 */
                this.shopScoreMng.deleteById(s.getId());
            }
/* 168 */
            this.manager.updateByUpdater(order);
        }
/* 170 */
        model.addAttribute("order", order);
/* 171 */
        return "order/view";
    }

    @RequestMapping({"/order/v_payment.do"})
    public String zhifu(Long id, HttpServletRequest request, ModelMap model) {
/* 177 */
        Website web = SiteUtils.getWeb(request);
/* 178 */
        WebErrors errors = validateEdit(id, request);
/* 179 */
        if (errors.hasErrors()) {
/* 180 */
            return errors.showErrorPage(model);
        }
/* 182 */
        Order order = this.manager.findById(id);
/* 183 */
        model.addAttribute("order", order);
/* 184 */
        return "order/payment";
    }

    @RequestMapping({"/order/v_shipments.do"})
    public String shipmentses(Long id, HttpServletRequest request, ModelMap model) {
/* 189 */
        Website web = SiteUtils.getWeb(request);
/* 190 */
        WebErrors errors = validateEdit(id, request);
/* 191 */
        if (errors.hasErrors()) {
/* 192 */
            return errors.showErrorPage(model);
        }
/* 194 */
        Order order = this.manager.findById(id);
/* 195 */
        model.addAttribute("order", order);
/* 196 */
        return "order/shipments";
    }

    @RequestMapping({"/order/o_payment.do"})
    public String payment(Gathering bean, Long id, HttpServletRequest request, ModelMap model) {
/* 203 */
        Website web = SiteUtils.getWeb(request);
/* 204 */
        WebErrors errors = validateEdit(id, request);
/* 205 */
        if (errors.hasErrors()) {
/* 206 */
            return errors.showErrorPage(model);
        }
/* 208 */
        Order order = this.manager.findById(id);
/* 209 */
        ShopAdmin admin = AdminThread.get();
/* 210 */
        bean.setShopAdmin(admin);
/* 211 */
        bean.setIndent(order);
/* 212 */
        if ((order.getPaymentStatus().intValue() == 1) && (order.getPayment().getType().byteValue() == 2)) {
/* 213 */
            this.gatheringMng.save(bean);
/* 214 */
            order.setPaymentStatus(Integer.valueOf(2));
/* 215 */
            this.manager.updateByUpdater(order);
        }
/* 217 */
        model.addAttribute("order", order);
/* 218 */
        return "order/view";
    }

    @RequestMapping({"/order/o_accomplish.do"})
    public String accomplish(Long id, HttpServletRequest request, ModelMap model) {
/* 226 */
        WebErrors errors = validateEdit(id, request);
/* 227 */
        if (errors.hasErrors()) {
/* 228 */
            return errors.showErrorPage(model);
        }
/* 230 */
        Order order = this.manager.findById(id);
/* 231 */
        if ((order.getShippingStatus().intValue() == 2) && (order.getStatus().intValue() == 2) && (order.getPaymentStatus().intValue() == 2)) {
/* 232 */
            order.setStatus(Integer.valueOf(4));

/* 234 */
            ShopMember member = order.getMember();
/* 235 */
            member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - order.getScore().intValue()));
/* 236 */
            member.setScore(Integer.valueOf(member.getScore().intValue() + order.getScore().intValue()));
/* 237 */
            this.shopMemberMng.update(member);
/* 238 */
            List<ShopScore> list = this.shopScoreMng.getlist(Long.toString(order.getCode()));
/* 239 */
            for (ShopScore s : list) {
/* 240 */
                s.setStatus(true);
/* 241 */
                this.shopScoreMng.update(s);
            }
/* 243 */
            this.manager.updateByUpdater(order);
/* 244 */
            this.manager.updateliRun(id);
        }
/* 246 */
        model.addAttribute("order", order);
/* 247 */
        return "order/view";
    }

    @RequestMapping({"/order/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {
/* 252 */
        Website web = SiteUtils.getWeb(request);
/* 253 */
        WebErrors errors = validateEdit(id, request);
/* 254 */
        if (errors.hasErrors()) {
/* 255 */
            return errors.showErrorPage(model);
        }
/* 257 */
        List shippingList = this.shippingMng.getList(web.getId(), true);
/* 258 */
        List paymentList = this.paymentMng.getList(web.getId(), true);
/* 259 */
        model.addAttribute("order", this.manager.findById(id));
/* 260 */
        model.addAttribute("paymentList", paymentList);
/* 261 */
        model.addAttribute("shippingList", shippingList);
/* 262 */
        model.addAttribute("orderReturn", this.orderReturnMng.findByOrderId(id));
/* 263 */
        return "order/edit";
    }

    @RequestMapping({"/order/o_update.do"})
    public String update(Long id, String comments, Long shippingId, Long paymentId, Long[] itemId, Integer[] itemCount, Double[] itemPrice, Double totalPrice, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 270 */
        WebErrors errors = validateUpdate(id, shippingId, itemId, itemCount, itemPrice, request);
/* 271 */
        if (errors.hasErrors()) {
/* 272 */
            return errors.showErrorPage(model);
        }
/* 274 */
        Order order = this.manager.findById(id);
/* 275 */
        int score = 0;
/* 276 */
        int weight = 0;
/* 277 */
        double price = 0.0D;
/* 278 */
        for (int i = 0; i < itemId.length; i++) {
/* 279 */
            OrderItem orderItem = this.orderItemMng.findById(itemId[i]);
/* 280 */
            Product product = orderItem.getProduct();
/* 281 */
            product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + orderItem.getCount().intValue() - itemCount[i].intValue()));
/* 282 */
            if (orderItem.getProductFash() != null) {
/* 283 */
                ProductFashion productFash = orderItem.getProductFash();
/* 284 */
                productFash.setStockCount(Integer.valueOf(productFash.getStockCount().intValue() + orderItem.getCount().intValue() - itemCount[i].intValue()));
/* 285 */
                this.productFashionMng.update(productFash);
            }
/* 287 */
            orderItem.setCount(itemCount[i]);
/* 288 */
            orderItem.setSalePrice(itemPrice[i]);
/* 289 */
            score += itemCount[i].intValue() * orderItem.getProduct().getScore().intValue();
/* 290 */
            weight += orderItem.getProduct().getWeight().intValue() * itemCount[i].intValue();
/* 291 */
            if (orderItem.getProductFash() != null)
/* 292 */ price += itemPrice[i].doubleValue() * itemCount[i].intValue();
            else {
/* 294 */
                price += itemPrice[i].doubleValue() * itemCount[i].intValue();
            }
/* 296 */
            this.orderItemMng.updateByUpdater(orderItem);
/* 297 */
            this.productMng.updateByUpdater(product);
        }
/* 299 */
        order.setScore(Integer.valueOf(score));
/* 300 */
        order.setWeight(Double.valueOf(weight));
/* 301 */
        order.setProductPrice(Double.valueOf(price));
/* 302 */
        double freight = this.shippingMng.findById(shippingId).calPrice(Double.valueOf(weight)).doubleValue();
/* 303 */
        order.setFreight(Double.valueOf(freight));
/* 304 */
        order.setTotal(Double.valueOf(freight + price));
/* 305 */
        order.setComments(comments);
/* 306 */
        order.setShipping(this.shippingMng.findById(shippingId));
/* 307 */
        order.setPayment(this.paymentMng.findById(paymentId));
/* 308 */
        order.setLastModified(new Timestamp(System.currentTimeMillis()));
/* 309 */
        this.manager.updateByUpdater(order);
/* 310 */
        log.info("update Order, id={}.", order.getId());
/* 311 */
        return list(null, null, null, null, null, null, null, null, pageNo, request, model);
    }

    @RequestMapping({"/order/o_returnMoney.do"})
    public void returnMoney(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 317 */
        Website web = SiteUtils.getWeb(request);
/* 318 */
        OrderReturn orderReturn = this.orderReturnMng.findByOrderId(id);
/* 319 */
        if (orderReturn.getPayType().intValue() == 2) {
/* 320 */
            Payment pay = this.paymentMng.findById(Long.valueOf(3L));
/* 321 */
            PrintWriter out = null;
            try {
/* 323 */
                String aliURL = alipayReturn(pay, web, orderReturn, request, model);
/* 324 */
                response.setContentType("text/html;charset=UTF-8");
/* 325 */
                out = response.getWriter();
/* 326 */
                out.print(aliURL);
            } catch (Exception localException) {
            } finally {
/* 329 */
                out.close();
            }
        }
    }

    private String alipayReturn(Payment pay, Website web, OrderReturn orderReturn, HttpServletRequest request, ModelMap model) {
/* 338 */
        Date date = new Date();
/* 339 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 340 */
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 341 */
        String date1 = sdf.format(date);

/* 344 */
        String batch_no = date1.concat(String.valueOf(orderReturn.getId().longValue() * 100L));

/* 347 */
        String refund_date = sdf1.format(date);

/* 350 */
        String batch_num = String.valueOf(1);

/* 353 */
        String detail_data = orderReturn.getOrder().getId().toString() + "^" + 1.0D + "^" + orderReturn.getShopDictionary().getName();

/* 371 */
        String notify_url = "http://" + web.getDomain() + "/cart/aliReturn.jspx";

/* 374 */
        Map sParaTemp = new HashMap();

/* 378 */
        sParaTemp.put("batch_no", batch_no);
/* 379 */
        sParaTemp.put("refund_date", refund_date);
/* 380 */
        sParaTemp.put("batch_num", batch_num);
/* 381 */
        sParaTemp.put("detail_data", detail_data);
/* 382 */
        sParaTemp.put("notify_url", notify_url);

/* 384 */
        String sHtmlText = null;
        try {
/* 386 */
            sHtmlText = refund_fastpay_by_platform_pwd(sParaTemp);
        } catch (Exception e) {
/* 388 */
            e.printStackTrace();
        }

/* 391 */
        return sHtmlText;
    }

    @RequestMapping({"/order/ajaxtotalDeliveryFee.do"})
    public void ajaxtotalDeliveryFee(Long deliveryMethod, Double weight, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {
/* 549 */
        ShopMember member = MemberThread.get();
/* 550 */
        JSONObject json = new JSONObject();
/* 551 */
        if (member == null) {
/* 552 */
            json.put("status", 0);
        }
/* 554 */
        Shipping shipping = this.shippingMng.findById(deliveryMethod);

/* 556 */
        Double freight = shipping.calPrice(weight);
/* 557 */
        json.put("status", 1);
/* 558 */
        json.put("freight", freight);
/* 559 */
        ResponseUtils.renderJson(response, json.toString());
    }

    private Integer findItem(Long[] itemIds, Long itemId) {
/* 563 */
        for (int i = 0; i < itemIds.length; i++) {
/* 564 */
            if (itemIds[i].equals(itemId)) {
/* 565 */
                return Integer.valueOf(i);
            }
        }
/* 568 */
        return null;
    }

    @RequestMapping({"/order/o_delete.do"})
    public String delete(Long[] ids, String type, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 574 */
        WebErrors errors = validateDelete(ids, request);
/* 575 */
        if (errors.hasErrors()) {
/* 576 */
            return errors.showErrorPage(model);
        }
/* 578 */
        Order[] beans = this.manager.deleteByIds(ids);
/* 579 */
        for (Order bean : beans) {
/* 580 */
            log.info("delete Order, id={}", bean.getId());
        }
/* 582 */
        return list(null, null, null, null, null, null, null, null, pageNo, request, model);
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 586 */
        WebErrors errors = WebErrors.create(request);
/* 587 */
        Website web = SiteUtils.getWeb(request);
/* 588 */
        if (vldExist(id, web.getId(), errors)) {
/* 589 */
            return errors;
        }
/* 591 */
        return errors;
    }

    private WebErrors validateUpdate(Long id, Long shippingId, Long[] itemId, Integer[] itemCount, Double[] itemPrice, HttpServletRequest request) {
/* 598 */
        WebErrors errors = WebErrors.create(request);
/* 599 */
        Website web = SiteUtils.getWeb(request);
/* 600 */
        if (vldExist(id, web.getId(), errors)) {
/* 601 */
            return errors;
        }
/* 603 */
        if ((itemId == null) || (itemCount == null) || (itemPrice == null) ||
/* 604 */       (itemId.length == 0) || (itemId.length != itemCount.length) || 
/* 605 */       (itemCount.length != itemPrice.length)) {
/* 606 */
            errors.addErrorString("order item invalid!");
/* 607 */
            return errors;
        }
/* 609 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 613 */
        WebErrors errors = WebErrors.create(request);
/* 614 */
        Website web = SiteUtils.getWeb(request);
/* 615 */
        errors.ifEmpty(ids, "ids");
/* 616 */
        for (Long id : ids) {
/* 617 */
            vldExist(id, web.getId(), errors);
        }
/* 619 */
        return errors;
    }

    private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 623 */
        if (errors.ifNull(id, "id")) {
/* 624 */
            return true;
        }
/* 626 */
        Order entity = this.manager.findById(id);
/* 627 */
        if (errors.ifNotExist(entity, Order.class, id)) {
/* 628 */
            return true;
        }
/* 630 */
        if (!entity.getWebsite().getId().equals(webId)) {
/* 631 */
            errors.notInWeb(Order.class, id);
/* 632 */
            return true;
        }
/* 634 */
        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.OrderAct
 * JD-Core Version:    0.6.2
 */