/*     */ package guda.shop.action.front;
/*     */ 
/*     */ import guda.shop.cms.Alipay;
/*     */ import guda.shop.cms.entity.Order;
/*     */ import guda.shop.cms.entity.PaymentPlugins;
/*     */ import guda.shop.cms.entity.Shipping;
/*     */ import guda.shop.cms.manager.OrderMng;
/*     */ import guda.shop.cms.manager.PaymentPluginsMng;
/*     */ import guda.shop.cms.web.FrontUtils;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.common.web.RequestUtils;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.front.FrontHelper;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class AlipayAct extends Alipay
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private OrderMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private PaymentPluginsMng paymentPluginsMng;
/*     */ 
/*     */   @RequestMapping(value={"/pay.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String pay(Long orderId, String code, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  41 */     Website web = SiteUtils.getWeb(request);
/*  42 */     if ((orderId != null) && (this.manager.findById(orderId) != null)) {
/*  43 */       Order order = this.manager.findById(orderId);
/*  44 */       PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode(code);
/*  45 */       PrintWriter out = null;
/*  46 */       String aliURL = null;
/*     */       try {
/*  48 */         if ((!StringUtils.isBlank(code)) && (code.equals("alipayPartner")))
/*  49 */           aliURL = alipay(paymentPlugins, web, order, request, model);
/*  50 */         else if ((!StringUtils.isBlank(code)) && (code.equals("alipay"))) {
/*  51 */           aliURL = alipayapi(paymentPlugins, web, order, request, model);
/*     */         }
/*  53 */         response.setContentType("text/html;charset=UTF-8");
/*  54 */         out = response.getWriter();
/*  55 */         out.print(aliURL);
/*     */       } catch (IOException e) {
/*  57 */         e.printStackTrace();
/*     */       } finally {
/*  59 */         out.close();
/*     */       }
/*  61 */       return null;
/*     */     }
/*  63 */     return FrontHelper.pageNotFound(web, model, request);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/aliReturn.jspx"})
/*     */   public String aliReturn(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws UnsupportedEncodingException
/*     */   {
/*  70 */     Map params = new HashMap();
/*  71 */     Map requestParams = request.getParameterMap();
/*  72 */     for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
/*  73 */       String name = (String)iter.next();
/*  74 */       String[] values = (String[])requestParams.get(name);
/*  75 */       String valueStr = "";
/*  76 */       for (int i = 0; i < values.length; i++) {
/*  77 */         valueStr = valueStr + values[i] + ",";
/*     */       }
/*  79 */       params.put(name, valueStr);
/*     */     }
/*     */ 
/*  82 */     String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
/*     */ 
/*  84 */     String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
/*     */ 
/*  86 */     String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
/*  87 */     PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode("alipayPartner");
/*  88 */     Long orderId = Long.valueOf(Long.parseLong(out_trade_no));
/*  89 */     Order order = this.manager.findById(orderId);
/*  90 */     if (verify(params, paymentPlugins.getPartner(), paymentPlugins.getSellerKey())) {
/*  91 */       if (trade_status.equals("WAIT_BUYER_PAY"))
/*     */       {
/*  93 */         return FrontUtils.showMessage(request, model, "付款失败！");
/*  94 */       }if (trade_status.equals("WAIT_SELLER_SEND_GOODS"))
/*     */       {
/*  96 */         order.setPaymentStatus(Integer.valueOf(2));
/*  97 */         order.setTradeNo(trade_no);
/*  98 */         order.setPaymentCode("alipayPartner");
/*  99 */         this.manager.updateByUpdater(order);
/* 100 */         return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
/* 101 */       }if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS"))
/*     */       {
/* 103 */         return FrontUtils.showMessage(request, model, "已发货，未确认收货！");
/* 104 */       }if (trade_status.equals("TRADE_FINISHED"))
/*     */       {
/* 106 */         return FrontUtils.showMessage(request, model, "交易完成！");
/*     */       }
/* 108 */       return FrontUtils.showMessage(request, model, "success！");
/*     */     }
/*     */ 
/* 111 */     return FrontUtils.showMessage(request, model, "付款异常！");
/*     */   }
/*     */ 
/*     */   private String alipay(PaymentPlugins paymentPlugins, Website web, Order order, HttpServletRequest request, ModelMap model)
/*     */   {
/* 117 */     String payment_type = "1";
/*     */ 
/* 119 */     String notify_url = "http://" + web.getDomain() + "/aliReturn.jspx";
/*     */ 
/* 121 */     String return_url = "http://" + web.getDomain() + "/aliReturn.jspx";
/*     */ 
/* 123 */     String seller_email = paymentPlugins.getSellerEmail();
/*     */ 
/* 125 */     String out_trade_no = order.getId().toString();
/*     */ 
/* 127 */     String subject = "(" + order.getId() + ")";
/*     */ 
/* 129 */     String price = String.valueOf(order.getTotal());
/*     */ 
/* 131 */     String quantity = "1";
/*     */ 
/* 133 */     String logistics_fee = String.valueOf(order.getFreight());
/*     */ 
/* 135 */     String logistics_type = getLogisticsType(order);
/*     */ 
/* 137 */     String logistics_payment = "BUYER_PAY";
/*     */ 
/* 139 */     String body = "(" + order.getId() + ")";
/*     */ 
/* 141 */     String show_url = "http://" + web.getDomain() + "/";
/*     */ 
/* 143 */     String receive_name = order.getReceiveName();
/*     */ 
/* 145 */     String receive_address = order.getReceiveAddress();
/*     */ 
/* 147 */     String receive_zip = order.getReceiveZip();
/*     */ 
/* 149 */     String receive_phone = order.getReceivePhone();
/*     */ 
/* 151 */     String receive_mobile = order.getReceiveMobile();
/*     */ 
/* 153 */     Map sParaTemp = new HashMap();
/* 154 */     sParaTemp.put("service", "create_partner_trade_by_buyer");
/* 155 */     sParaTemp.put("partner", paymentPlugins.getPartner());
/* 156 */     sParaTemp.put("_input_charset", "utf-8");
/* 157 */     sParaTemp.put("payment_type", payment_type);
/* 158 */     sParaTemp.put("notify_url", notify_url);
/* 159 */     sParaTemp.put("return_url", return_url);
/* 160 */     sParaTemp.put("seller_email", seller_email);
/* 161 */     sParaTemp.put("out_trade_no", out_trade_no);
/* 162 */     sParaTemp.put("subject", subject);
/* 163 */     sParaTemp.put("price", price);
/* 164 */     sParaTemp.put("quantity", quantity);
/* 165 */     sParaTemp.put("logistics_fee", logistics_fee);
/* 166 */     sParaTemp.put("logistics_type", logistics_type);
/* 167 */     sParaTemp.put("logistics_payment", logistics_payment);
/* 168 */     sParaTemp.put("body", body);
/* 169 */     sParaTemp.put("show_url", show_url);
/* 170 */     sParaTemp.put("receive_name", receive_name);
/* 171 */     sParaTemp.put("receive_address", receive_address);
/* 172 */     sParaTemp.put("receive_zip", receive_zip);
/* 173 */     sParaTemp.put("receive_phone", receive_phone);
/* 174 */     sParaTemp.put("receive_mobile", receive_mobile);
/*     */ 
/* 176 */     String sHtmlText = buildRequest(sParaTemp, paymentPlugins.getSellerKey(), "get", "确认");
/* 177 */     return sHtmlText;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/aliReturnUrl.jspx"})
/*     */   public String aliReturndirect(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws UnsupportedEncodingException
/*     */   {
/* 184 */     Map params = new HashMap();
/* 185 */     Map requestParams = request.getParameterMap();
/* 186 */     for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
/* 187 */       String name = (String)iter.next();
/* 188 */       String[] values = (String[])requestParams.get(name);
/* 189 */       String valueStr = "";
/* 190 */       for (int i = 0; i < values.length; i++) {
/* 191 */         valueStr = 
/* 192 */           valueStr + values[i] + ",";
/*     */       }
/*     */ 
/* 196 */       params.put(name, valueStr);
/*     */     }
/*     */ 
/* 199 */     String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
/*     */ 
/* 201 */     String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
/*     */ 
/* 203 */     String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
/*     */ 
/* 205 */     PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode("alipayPartner");
/* 206 */     Long orderId = Long.valueOf(Long.parseLong(out_trade_no));
/* 207 */     Order order = this.manager.findById(orderId);
/* 208 */     if (verify(params, paymentPlugins.getPartner(), paymentPlugins.getSellerKey())) {
/* 209 */       if (trade_status.equals("TRADE_FINISHED"))
/*     */       {
/* 213 */         order.setPaymentStatus(Integer.valueOf(2));
/* 214 */         this.manager.updateByUpdater(order);
/* 215 */         return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
/*     */       }
/*     */ 
/* 220 */       if (trade_status.equals("TRADE_SUCCESS"))
/*     */       {
/* 224 */         order.setPaymentStatus(Integer.valueOf(2));
/* 225 */         this.manager.updateByUpdater(order);
/* 226 */         return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 231 */       return FrontUtils.showMessage(request, model, "验证失败！");
/*     */     }
/* 233 */     return FrontUtils.showMessage(request, model, "付款异常！");
/*     */   }
/*     */ 
/*     */   private String alipayapi(PaymentPlugins paymentPlugins, Website web, Order order, HttpServletRequest request, ModelMap model)
/*     */   {
/* 239 */     String payment_type = "1";
/*     */ 
/* 241 */     String notify_url = "http://" + web.getDomain() + "/aliReturnUrl.jspx";
/*     */ 
/* 243 */     String return_url = "http://" + web.getDomain() + "/aliReturnUrl.jspx";
/*     */ 
/* 245 */     String seller_email = paymentPlugins.getSellerEmail();
/*     */ 
/* 247 */     String out_trade_no = order.getId().toString();
/*     */ 
/* 249 */     String subject = "(" + order.getId() + ")";
/*     */ 
/* 251 */     String total_fee = String.valueOf(order.getTotal());
/*     */ 
/* 253 */     String body = "(" + order.getId() + ")";
/*     */ 
/* 255 */     String show_url = "http://" + web.getDomain() + "/";
/*     */ 
/* 257 */     String anti_phishing_key = "";
/*     */ 
/* 259 */     String exter_invoke_ip = RequestUtils.getIpAddr(request);
/* 260 */     Map sParaTemp = new HashMap();
/* 261 */     sParaTemp.put("service", "create_direct_pay_by_user");
/* 262 */     sParaTemp.put("partner", paymentPlugins.getPartner());
/* 263 */     sParaTemp.put("_input_charset", "utf-8");
/* 264 */     sParaTemp.put("payment_type", payment_type);
/* 265 */     sParaTemp.put("notify_url", notify_url);
/* 266 */     sParaTemp.put("return_url", return_url);
/* 267 */     sParaTemp.put("seller_email", seller_email);
/* 268 */     sParaTemp.put("out_trade_no", out_trade_no);
/* 269 */     sParaTemp.put("subject", subject);
/* 270 */     sParaTemp.put("total_fee", total_fee);
/* 271 */     sParaTemp.put("body", body);
/* 272 */     sParaTemp.put("show_url", show_url);
/* 273 */     sParaTemp.put("anti_phishing_key", anti_phishing_key);
/* 274 */     sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
/*     */ 
/* 276 */     String sHtmlText = buildRequest(sParaTemp, paymentPlugins.getSellerKey(), "get", "确认");
/* 277 */     System.out.println(sHtmlText);
/* 278 */     return sHtmlText;
/*     */   }
/*     */ 
/*     */   public String getLogisticsType(Order order)
/*     */   {
/*     */     String logistics_type;
/*     */     String logistics_type;
/* 283 */     if (!StringUtils.isBlank(order.getShipping().getLogisticsType()))
/* 284 */       logistics_type = order.getShipping().getLogisticsType();
/*     */     else {
/* 286 */       logistics_type = "EXPRESS";
/*     */     }
/* 288 */     return logistics_type;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.AlipayAct
 * JD-Core Version:    0.6.2
 */