package guda.shop.cms.action.front;

import guda.shop.cms.Alipay;
import guda.shop.cms.entity.Order;
import guda.shop.cms.entity.PaymentPlugins;
import guda.shop.cms.manager.OrderMng;
import guda.shop.cms.manager.PaymentPluginsMng;
import guda.shop.cms.web.FrontUtils;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.web.RequestUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.web.front.FrontHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class AlipayAct extends Alipay {

    @Autowired
    private OrderMng manager;

    @Autowired
    private PaymentPluginsMng paymentPluginsMng;

    @RequestMapping(value = {"/pay.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String pay(Long orderId, String code, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        if ((orderId != null) && (this.manager.findById(orderId) != null)) {

            Order order = this.manager.findById(orderId);

            PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode(code);

            PrintWriter out = null;

            String aliURL = null;
            try {

                if ((!StringUtils.isBlank(code)) && (code.equals("alipayPartner")))
                    aliURL = alipay(paymentPlugins, web, order, request, model);

                else if ((!StringUtils.isBlank(code)) && (code.equals("alipay"))) {

                    aliURL = alipayapi(paymentPlugins, web, order, request, model);
                }

                response.setContentType("text/html;charset=UTF-8");

                out = response.getWriter();

                out.print(aliURL);
            } catch (IOException e) {

                e.printStackTrace();
            } finally {

                out.close();
            }

            return null;
        }

        return FrontHelper.pageNotFound(web, model, request);
    }

    @RequestMapping({"/aliReturn.jspx"})
    public String aliReturn(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws UnsupportedEncodingException {

        Map params = new HashMap();

        Map requestParams = request.getParameterMap();

        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {

            String name = (String) iter.next();

            String[] values = (String[]) requestParams.get(name);

            String valueStr = "";

            for (int i = 0; i < values.length; i++) {

                valueStr = valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
        }


        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");


        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");


        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

        PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode("alipayPartner");

        Long orderId = Long.valueOf(Long.parseLong(out_trade_no));

        Order order = this.manager.findById(orderId);

        if (verify(params, paymentPlugins.getPartner(), paymentPlugins.getSellerKey())) {

            if (trade_status.equals("WAIT_BUYER_PAY")) {

                return FrontUtils.showMessage(request, model, "付款失败！");

            }
            if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {

                order.setPaymentStatus(Integer.valueOf(2));

                order.setTradeNo(trade_no);

                order.setPaymentCode("alipayPartner");

                this.manager.updateByUpdater(order);

                return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");

            }
            if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {

                return FrontUtils.showMessage(request, model, "已发货，未确认收货！");

            }
            if (trade_status.equals("TRADE_FINISHED")) {

                return FrontUtils.showMessage(request, model, "交易完成！");
            }

            return FrontUtils.showMessage(request, model, "success！");
        }


        return FrontUtils.showMessage(request, model, "付款异常！");
    }

    private String alipay(PaymentPlugins paymentPlugins, Website web, Order order, HttpServletRequest request, ModelMap model) {

        String payment_type = "1";


        String notify_url = "http://" + web.getDomain() + "/aliReturn.jspx";


        String return_url = "http://" + web.getDomain() + "/aliReturn.jspx";


        String seller_email = paymentPlugins.getSellerEmail();


        String out_trade_no = order.getId().toString();


        String subject = "(" + order.getId() + ")";


        String price = String.valueOf(order.getTotal());


        String quantity = "1";


        String logistics_fee = String.valueOf(order.getFreight());


        String logistics_type = getLogisticsType(order);


        String logistics_payment = "BUYER_PAY";


        String body = "(" + order.getId() + ")";


        String show_url = "http://" + web.getDomain() + "/";


        String receive_name = order.getReceiveName();


        String receive_address = order.getReceiveAddress();


        String receive_zip = order.getReceiveZip();


        String receive_phone = order.getReceivePhone();


        String receive_mobile = order.getReceiveMobile();


        Map sParaTemp = new HashMap();

        sParaTemp.put("service", "create_partner_trade_by_buyer");

        sParaTemp.put("partner", paymentPlugins.getPartner());

        sParaTemp.put("_input_charset", "utf-8");

        sParaTemp.put("payment_type", payment_type);

        sParaTemp.put("notify_url", notify_url);

        sParaTemp.put("return_url", return_url);

        sParaTemp.put("seller_email", seller_email);

        sParaTemp.put("out_trade_no", out_trade_no);

        sParaTemp.put("subject", subject);

        sParaTemp.put("price", price);

        sParaTemp.put("quantity", quantity);

        sParaTemp.put("logistics_fee", logistics_fee);

        sParaTemp.put("logistics_type", logistics_type);

        sParaTemp.put("logistics_payment", logistics_payment);

        sParaTemp.put("body", body);

        sParaTemp.put("show_url", show_url);

        sParaTemp.put("receive_name", receive_name);

        sParaTemp.put("receive_address", receive_address);

        sParaTemp.put("receive_zip", receive_zip);

        sParaTemp.put("receive_phone", receive_phone);

        sParaTemp.put("receive_mobile", receive_mobile);


        String sHtmlText = buildRequest(sParaTemp, paymentPlugins.getSellerKey(), "get", "确认");

        return sHtmlText;
    }

    @RequestMapping({"/aliReturnUrl.jspx"})
    public String aliReturndirect(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws UnsupportedEncodingException {

        Map params = new HashMap();

        Map requestParams = request.getParameterMap();

        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {

            String name = (String) iter.next();

            String[] values = (String[]) requestParams.get(name);

            String valueStr = "";

            for (int i = 0; i < values.length; i++) {

                valueStr =
                        valueStr + values[i] + ",";
            }


            params.put(name, valueStr);
        }


        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");


        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");


        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");


        PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode("alipayPartner");

        Long orderId = Long.valueOf(Long.parseLong(out_trade_no));

        Order order = this.manager.findById(orderId);

        if (verify(params, paymentPlugins.getPartner(), paymentPlugins.getSellerKey())) {

            if (trade_status.equals("TRADE_FINISHED")) {

                order.setPaymentStatus(Integer.valueOf(2));

                this.manager.updateByUpdater(order);

                return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
            }


            if (trade_status.equals("TRADE_SUCCESS")) {

                order.setPaymentStatus(Integer.valueOf(2));

                this.manager.updateByUpdater(order);

                return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
            }
        } else {

            return FrontUtils.showMessage(request, model, "验证失败！");
        }

        return FrontUtils.showMessage(request, model, "付款异常！");
    }

    private String alipayapi(PaymentPlugins paymentPlugins, Website web, Order order, HttpServletRequest request, ModelMap model) {

        String payment_type = "1";


        String notify_url = "http://" + web.getDomain() + "/aliReturnUrl.jspx";


        String return_url = "http://" + web.getDomain() + "/aliReturnUrl.jspx";


        String seller_email = paymentPlugins.getSellerEmail();


        String out_trade_no = order.getId().toString();


        String subject = "(" + order.getId() + ")";


        String total_fee = String.valueOf(order.getTotal());


        String body = "(" + order.getId() + ")";


        String show_url = "http://" + web.getDomain() + "/";


        String anti_phishing_key = "";


        String exter_invoke_ip = RequestUtils.getIpAddr(request);

        Map sParaTemp = new HashMap();

        sParaTemp.put("service", "create_direct_pay_by_user");

        sParaTemp.put("partner", paymentPlugins.getPartner());

        sParaTemp.put("_input_charset", "utf-8");

        sParaTemp.put("payment_type", payment_type);

        sParaTemp.put("notify_url", notify_url);

        sParaTemp.put("return_url", return_url);

        sParaTemp.put("seller_email", seller_email);

        sParaTemp.put("out_trade_no", out_trade_no);

        sParaTemp.put("subject", subject);

        sParaTemp.put("total_fee", total_fee);

        sParaTemp.put("body", body);

        sParaTemp.put("show_url", show_url);

        sParaTemp.put("anti_phishing_key", anti_phishing_key);

        sParaTemp.put("exter_invoke_ip", exter_invoke_ip);


        String sHtmlText = buildRequest(sParaTemp, paymentPlugins.getSellerKey(), "get", "确认");

        System.out.println(sHtmlText);

        return sHtmlText;
    }

    public String getLogisticsType(Order order) {
        String logistics_type;

        if (!StringUtils.isBlank(order.getShipping().getLogisticsType()))
            logistics_type = order.getShipping().getLogisticsType();
        else {

            logistics_type = "EXPRESS";
        }

        return logistics_type;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.AlipayAct
 * JD-Core Version:    0.6.2
 */