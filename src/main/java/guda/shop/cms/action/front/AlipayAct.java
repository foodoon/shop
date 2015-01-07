 package guda.shop.cms.action.front;
/*     */*/ import gcms.Alipay;
/*     */ import guda.shop.y.Order;
/*     */ import guda.shop.cms.entitPlugins;
/*     */ import guda.shop.cms.entity.Shippin */ import guda.shop.cms.manager.OrderMng;
/*   rt guda.shop.cms.manager.PaymentPluginsMng;
/*   rt guda.shop.cms.web.FrontUtils;
/*     */ import guda.sho.SiteUtils;
/*     */ import guda.shop.common.wtUtils;
/*     */ import guda.shop.core.entity
/*     */ import guda.shop.core.web.front.FrontHelp  */ import java.io.IOException;
/*     */ impor.PrintStream;
/*     */ import java.io.PrintWriter;
/* port java.io.UnsupportedEncodingExcept   */ import java.util.HashMap;
/*    t java.util.Iterator;
/*     */ importl.Map;
/*     */ import java.util.Set;
/*     */ importrvlet.http.HttpServletRequest;
/*   rt javax.servlet.http.HttpServletResp    */ import org.apache.commonsingUtils;
/*     */ import org.sework.beans.factory.annotation.Autowired;
/*     */ imporingframework.stereotype.Controller;
/*     */ import orgamework.ui.ModelMap;
/*     */ import org.springframewind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */lass AlipayAct extends Alipay
/*     */ {
/*     */
/*     owired
/*     */   private OrderMng manager;
/*       */   @Autowired
/*     */   private PaymentPluginsMng paymentPluginsM  */
/*   equestMapping(value={""}, method={org.springframework.web.bind.annotatstMethod.POS  */   publ pay(Long orderId, StriHttpServletRequest request, HttpServle response, model)
/*     */   {
/*   Website web = SiteUtils.getWeb(request);
/*  42 */    erId != nulis.manager.findById(orderId) != null)) {
/*  43 */       Order order = this.manager.findById(orderId);
/*  44 */      lugins paymentPlugins = this.paymentPluginsMng.findByCode(code);
/*  45 */       PrintWriter out = null;
/*  46 */       String alil;
/*     */       try {
/*  48 */         if ((!StringUtils.isBlank(code)) && (code.equals("alipayPartner")))
/*  49 */           aliURL = alipay(paymentPlugins, web, order, request, model);
/*  50 */         else if ((!StringUtils.isBlank(code)) && (code.equals("alipay"))) {
           aliURL = alipayapi(paymentPlugins, web, order, request, model);
/*     */         }/         response.setContentType("text/html;charset=UTF-8");
/*  54 */         out = response.getWriter();
/*  55 */         out.print(aliURL);
/*     */       } catch (IOException e) {
/*  57 */         e.printStackTrace();
/*     */       } finally {
/*  59 */         out.close();
/*     */       }
/*  61 */       return null;
/*     */     }
/*  63 */   FrontHelper.pageNotFound(web, model, request);
/*     */   }
/*     */
/*     */   @RequestMapping({"/aliReturn.jspx"})
/*     */   public String aliReturn(HttpServletRequst, HttpServletResponse response, ModelMap model)
/*     */     throws UnsupporteException
/*     */   {
/*  70 */     Map params = new Hash  71 */     Map requestParams = request.getPara);
/*  72 */     for (Iterator iter = requestParams.keySet().iterator(); iter.hasNex/*  73 */     name = (Strnext();
/*  74 */       String[] values = (StringtParams.get(name);
/*  75 */       String valueStr = "";
/*  76 */       for (int i = 0; i < values.length; i+77 */         valueStr = valueStr + values[i] + ", */       }
/*  79 */       params.put(name, valueStr);
/*     */     }
/*     */ 
/*  82 */     String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
/*     */ 
/*  84 */     String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
/*     */ 
/*  86 */     String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
/*  87 */     PaymentPlugins ugins = this.paymentPluginsMng.findByCode("alipayPartner");
/*   Long orderId lueOf(Long.parseLong(out_trade_no));
/*  89 */     Order order = this.manager.findById(orderId);
/*  90 */     if (verify(params,lugins.getPartner(), paymentPlugins.getSellerKey())) {
/*  91 */       if (trade_status.equals("WAIT_BUYER_PAY"))
/*     {
/*  93 */         return FrontUtils.showMessage(request, model, "付款失败！");
/*  94 */       }if (trade_status.equals("WAIT_SELLER_SEND_GOODS"))
/*     */       {
/*  96 */         order.setPaymentStatus(Integer.valueOf(2));
/*  97 */         order.setTradeNo(trade_no);
/*  98 */         order.setPaymentCode("alipayPartner");
/*  99 */         this.manager.updateByUpdater(order);
/* 100 */         return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
/* 101 */       }if (trade_status.equals("WAIT_BUYER_OODS"))
/*     */       {
/* 103 */         return FrontUtils.showMessage(request, model, "已发货，未确认收货！");
/* 104 */       }if (trade_status.equals("TRADE_FINISHE   */       {
/* 106 */         return FrontUtils.showMessage(request, model, "交易完成！");
/*     */       }
/* 108 */       return FrontUtils.showMessage(request, model, "success！");
/*     */     }
/*     */
/* 111 */     return FrontUtils.showMessage(request, model, "付款异常！");
/*     */   }
/*     */
/*     */   private String alipay(PaymentPlugins paymentPlugins, Website web, Order opServletRequest request, ModelMap model)
/*     */   {
/* 117 */     String payment_type = "1";
/*     */ 
/* 119 */     String notify_url = "http://" + web.() + "/aliReturn.jspx";
/*     */ 
/* 121 */     String return_url = "http://" + web.getDomaaliReturn.jspx";
/*     */
/* 123 */     String seller_email = paymentPlugins.getSellerEmail  */
/* 125 */ g out_trade_no = order.getId().toString();
/*     */ 
/* 127 */     String subjec order.getId()*     */
/    String price = String.valueOf(order.getTotal());
/*     */ 
/* 131 */     String quantity = "1";
/*     */ 
/* 133 */     String loee = String.valueOf(order.getFreight());
/*     */
/*   String logistics_type = getLogisticsType(order);
/*     */
/* 137 */     String logistics_p"BUYER_PAY";
/*     */ 
/* 139 */     String body = "(" + order.getId() + ")";
/*     */
/* 1 String show_url = "http://" + web.getDomain() + "/";
/*     */
/* 143 */     Seive_name = order.getReceiveName();
/*     */ 
/* 145 */     String receis = order.getReceiveAddress();
/*     */
/* 147 */     String receivrder.getReceiveZip();
/*     */ 
/* 149 */     String receive_phone = ordeivePhone();
/*     */ 
/* 151 */     String recee = order.getReceiveMobile();
/*     */ 
/* 153 */     Map sParaTemp = new HashMap()*/     sParaTemp.put("service", "create_partner_trade_by_buyer");
/* 155 *raTemp.put("partner", paymentPlugins.getPartner());
/* 156 */    p.put("_input_charset", "utf-8");
/* 157 */     sParaTemp.put("pay", payment_type);
/* 158 */     sParaTemp.put("notify_url", notify_url);
/* 15sParaTemp.put("return_url", return_url);
/* 160 */     sParaTemp.put("sil", seller_email);
/* 161 */     sParaTemp.put("out_trade_no", out_trade_no)*/     sParaTemp.put("subject", subject);
/* 163 */     sParaTemp.put price);
/* 164 */     sParaTemp.put("quantity", quantity);
/* 165 */    p.put("logistics_fee", logistics_fee);
/* 166 */     sParaTemp.put("logisti logistics_type);
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
/* 190 */       for (int i = 0; i < valh; i++) {
/* 191 */         valueStr = 
/* 192 */           valueStr + values[i] + ",";
/*     */       }
/*     */ 
/* 196 */       params.put(neStr);
/*     /*     */
     String out_trade_no = new String(request.getParut_trade_no").getBytes("ISO-8859-1"), "UTF-8");
/*     */ 
/* 201 */     String trade_no = new String(request.getParrade_no").getBytes("ISO-8859-1"), "UTF-8");
/*    03 */     String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
/*     */
/* 205 */     PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode("alipayPartner");
/* 206 */     Long orderId = Long.valueOf(Long.parseLong(out_trade_no));
/* 207 */     Order order = this.manager.findById(orderId);
/* 208 */     if (verify(params, paymentPlugins.getPartner(), paymentPlugins.getSellerKey())) {
/* 209 */       if (trade_status.equals("TRADE_FI
/*     */       {/         order.setPaymentStatus(Integer.valueOf(2));
/        this.manteByUpdater(order);
/* 215 */         return FrontUtils.showMessage(request, model, "付款成功，请等待发货！");
/*     */       }
/*     */ 
       if (trade_status.equals("TRADE_SUCCESS"))
/*     */       {
/* 224 */         order.setPaymentStatus(Integer.value* 225 */         this.manager.updateByUpdater(order);
/* 226 */         return FrontUtils.showMessage(request, model, "付款成功，请等待发货   */       }
/*     */     }
/*     */     else
/*     */     {
/* 231 */       return FrontUtils.showMessage(request, model, "验证失败！");
/*     */     }
/* 233 */     return FrontUtils.showMessage(request, model, "付款异常！");
/*     */   }
/*     */ 
/*     */   private String alipayapi(PaymentPlugins paymentPlugins, Website web, Order order, HttpServletRequest request, ModelMap model)
/*     */   */     String payment_type = "1";
/*     */
/* 241 */     String notify_url = "http://" + web.getDomain() + "/aliReturnUrl.jspx";
/*     */ 
/* 243 */     String return_url = "http://" + web.getDomain() + "/aliRetpx";
/*     */ 
/*   String seller_email = paymentPlugins.getSellerEmail();
/*     */ 
     String out_trade_no = order.getId().toString();
/*     */ 
/* 249 */     String subject = "(" + order.getId() + ")";
/*     */ 
/* 251 */     String total_fee = String.valueOf(order.getTotal());
/*     */ 
/* 2 String body = "("getId() + ")";
/
/* 255 */     Strirl = "http://" + web.getDomain() + "/";
/*     */ 
/* 257 */     String anti_phishing_ke*     */
/* 259 */     String exter_invoke_ip = RequestUtils.getIpAddr(request);
/* 2 Map sParaTempshMap();
/*   sParaTemp.put("service", "create_direct_pay_by_user");
/* 262 */     sParaTemp.put("partner", paymentPlugins.getPartner());
/* 263 */  emp.put("_input_charset", "utf-8");
/* 264 */     sPara"payment_type", payment_type);
/* 265 */     sParaTemp.put("notify_url", notify_url);
/* 266 */  emp.put("return_url", return_url);
/* 267 */     sParaTemp.put("seller_email", seller_email);
/*   sParaTemp.put("out_trade_no", out_trade_no);
/* 269 */     sParaTemp.put("subjject);
/* 270 */     sParaTemp.put("total_fee", total_fee);
/* 271 */    p.put("body", body);
/* 272 */     sParaTemp.put("show_url", show_url */     sParaTemp.put("anti_phishing_key", anti_phishing_key);
/* 274 */     sput("exter_invoke_ip", exter_invoke_ip);
/*     */ 
/* 276 */     tmlText = buildRequest(sParaTemp, paymentPlugins.getSellerKey(), "get", "确认");/     System.out.println(sHtmlText);
/* 278 */     returxt;
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