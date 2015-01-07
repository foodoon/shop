 package guda.shop.cms.action.admin.main;

 import guda.shop.cms.entity.Order;
 import guda.shop.cms.entity.OrderItem;
 import guda.shop.cms.entity.OrderReturn;
 import guda.shop.cms.entity.PaymentPlugins;
 import guda.shop.cms.entity.Product;
 import guda.shop.cms.entity.ProductFashion;
 import guda.shop.cms.entity.ShopDictionary;
 import guda.shop.cms.entity.ShopMember;
 import guda.shop.cms.entity.ShopScore;
 import guda.shop.cms.manager.OrderMng;
 import guda.shop.cms.manager.OrderReturnMng;
 import guda.shop.cms.manager.PaymentPluginsMng;
 import guda.shop.cms.manager.ProductFashionMng;
 import guda.shop.cms.manager.ProductMng;
 import guda.shop.cms.manager.ShopMemberMng;
 import guda.shop.cms.manager.ShopScoreMng;
 import guda.shop.common.page.Pagination;
 import guda.shop.common.page.SimplePage;
 import guda.shop.common.web.CookieUtils;
 import guda.shop.core.entity.Website;
 import guda.shop.core.web.SiteUtils;
 import guda.shop.core.web.WebErrors;
 import java.io.PrintWriter;
 import java.io.UnsupportedEncodingException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Collections;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.codec.digest.DigestUtils;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;

 @Controller
 public class OrderReturnAct
 {
/*  55 */   private static final Logger log = LoggerFactory.getLogger(OrderReturnAct.class);
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

   @RequestMapping({"/orderReturn/v_list.do"})
   public String list(Integer status, Integer pageNo, HttpServletRequest request, ModelMap model)
   {
/*  59 */     Pagination pagination = this.manager.getPage(status, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  60 */     model.addAttribute("pagination", pagination);
/*  61 */     model.addAttribute("status", status);
/*  62 */     return "orderReturn/list";
   }

   @RequestMapping({"/orderReturn/v_view.do"})
   public String view(Long id, HttpServletRequest request, ModelMap model) {
/*  67 */     WebErrors errors = validateEdit(id, request);
/*  68 */     if (errors.hasErrors()) {
/*  69 */       return errors.showErrorPage(model);
     }
/*  71 */     model.addAttribute("order", this.manager.findById(id).getOrder());
/*  72 */     return "orderReturn/view";
   }

   @RequestMapping({"/orderReturn/o_affirm.do"})
   public String affirm(Long id, HttpServletRequest request, ModelMap model) {
/*  77 */     WebErrors errors = validateEdit(id, request);
/*  78 */     if (errors.hasErrors()) {
/*  79 */       return errors.showErrorPage(model);
     }
/*  81 */     OrderReturn entity = this.manager.findById(id);
/*  82 */     entity.setStatus(Integer.valueOf(2));
/*  83 */     this.manager.update(entity);
/*  84 */     model.addAttribute("order", this.manager.findById(id).getOrder());
/*  85 */     return "orderReturn/view";
   }

   @RequestMapping({"/orderReturn/o_sendback.do"})
   public String sendback(Long id, HttpServletRequest request, ModelMap model) {
/*  90 */     WebErrors errors = validateEdit(id, request);
/*  91 */     if (errors.hasErrors()) {
/*  92 */       return errors.showErrorPage(model);
     }
/*  94 */     OrderReturn entity = this.manager.findById(id);
/*  95 */     entity.setStatus(Integer.valueOf(3));
/*  96 */     this.manager.update(entity);
/*  97 */     model.addAttribute("order", this.manager.findById(id).getOrder());
/*  98 */     return "orderReturn/view";
   }

   @RequestMapping({"/orderReturn/o_accomplish.do"})
   public String accomplish(Long id, HttpServletRequest request, ModelMap model) {
/* 103 */     WebErrors errors = validateEdit(id, request);
/* 104 */     if (errors.hasErrors()) {
/* 105 */       return errors.showErrorPage(model);
     }
/* 107 */     OrderReturn entity = this.manager.findById(id);
/* 108 */     entity.setStatus(Integer.valueOf(7));
     ProductFashion fashion;
/* 110 */     for (OrderItem item : entity.getOrder().getItems()) {
/* 111 */       Product product = item.getProduct();
/* 112 */       if (item.getProductFash() != null) {
/* 113 */         fashion = item.getProductFash();
/* 114 */         fashion.setStockCount(Integer.valueOf(fashion.getStockCount().intValue() + item.getCount().intValue()));
/* 115 */         product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
/* 116 */         product.setSaleCount(Integer.valueOf(product.getSaleCount().intValue() - item.getCount().intValue()));
/* 117 */         product.setLiRun(Double.valueOf(product.getLiRun().doubleValue() - item.getCount().intValue() * (fashion.getSalePrice().doubleValue() - fashion.getCostPrice().doubleValue())));
/* 118 */         this.productFashionMng.update(fashion);
       } else {
/* 120 */         product.setLiRun(Double.valueOf(product.getLiRun().doubleValue() - item.getCount().intValue() * (product.getSalePrice().doubleValue() - product.getCostPrice().doubleValue())));
/* 121 */         product.setSaleCount(Integer.valueOf(product.getSaleCount().intValue() - item.getCount().intValue()));
/* 122 */         product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
       }
/* 124 */       this.productMng.updateByUpdater(product);
     }

/* 127 */     ShopMember member = entity.getOrder().getMember();
/* 128 */     member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - entity.getOrder().getScore().intValue()));
/* 129 */     this.shopMemberMng.update(member);
/* 130 */     List list = this.shopScoreMng.getlist(Long.toString(entity.getOrder().getCode()));
/* 131 */     for (ShopScore s : list) {
/* 132 */       this.shopScoreMng.deleteById(s.getId());
     }
/* 134 */     Order order = this.manager.findById(id).getOrder();
/* 135 */     order.setStatus(Integer.valueOf(3));
/* 136 */     this.manager.update(entity);
/* 137 */     this.orderMng.updateByUpdater(order);
/* 138 */     model.addAttribute("order", this.manager.findById(id).getOrder());
/* 139 */     return "orderReturn/view";
   }

   @RequestMapping({"/orderReturn/o_reimburse.do"})
   public String reimburse(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 144 */     Website web = SiteUtils.getWeb(request);
/* 145 */     WebErrors errors = validateEdit(id, request);
/* 146 */     if (errors.hasErrors()) {
/* 147 */       return errors.showErrorPage(model);
     }
/* 149 */     OrderReturn entity = this.manager.findById(id);
/* 150 */     if (entity.getPayType().intValue() == 2) {
/* 151 */       PaymentPlugins paymentPlugins = this.paymentPluginsMng.findByCode("alipay");
/* 152 */       PrintWriter out = null;
       try {
/* 154 */         String aliURL = alipayReturn(paymentPlugins, web, entity, request, model);
/* 155 */         response.setContentType("text/html;charset=UTF-8");
/* 156 */         out = response.getWriter();
/* 157 */         out.print(aliURL);
       } catch (Exception localException) {
       } finally {
/* 160 */         out.close();
       }
     } else {
/* 163 */       ShopMember shopMember = entity.getOrder().getMember();
/* 164 */       shopMember.setMoney(Double.valueOf(shopMember.getFreezeScore().intValue() + entity.getMoney().doubleValue()));
/* 165 */       this.shopMemberMng.update(shopMember);
     }
/* 167 */     entity.setStatus(Integer.valueOf(6));
/* 168 */     this.manager.update(entity);
/* 169 */     model.addAttribute("order", this.manager.findById(id).getOrder());
/* 170 */     return "orderReturn/view";
   }

   private String alipayReturn(PaymentPlugins paymentPlugins, Website web, OrderReturn orderReturn, HttpServletRequest request, ModelMap model)
   {
/* 177 */     Date date = new Date();
/* 178 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/* 179 */     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 180 */     String date1 = sdf.format(date);

/* 183 */     String batch_no = date1.concat(String.valueOf(orderReturn.getId().longValue() * 100L));

/* 186 */     String refund_date = sdf1.format(date);

/* 189 */     String batch_num = String.valueOf(1);

/* 192 */     String detail_data = orderReturn.getOrder().getId().toString() + "^" + 1.0D + "^" + orderReturn.getShopDictionary().getName();

/* 205 */     String seller_email = paymentPlugins.getSellerEmail();

/* 208 */     String seller_user_id = paymentPlugins.getSellerKey();

/* 210 */     String notify_url = "http://" + web.getDomain() + "/cart/aliReturn.jspx";

/* 213 */     Map sParaTemp = new HashMap();
/* 214 */     sParaTemp.put("partner", paymentPlugins.getPartner());
/* 215 */     sParaTemp.put("seller_email", seller_email);
/* 216 */     sParaTemp.put("seller_user_id", seller_user_id);

/* 219 */     sParaTemp.put("batch_no", batch_no);
/* 220 */     sParaTemp.put("refund_date", refund_date);
/* 221 */     sParaTemp.put("batch_num", batch_num);
/* 222 */     sParaTemp.put("detail_data", detail_data);
/* 223 */     sParaTemp.put("notify_url", notify_url);

/* 225 */     String sHtmlText = null;
     try {
/* 227 */       sHtmlText = refund_fastpay_by_platform_pwd(sParaTemp);
     } catch (Exception e) {
/* 229 */       e.printStackTrace();
     }

/* 232 */     return sHtmlText;
   }

   public static String refund_fastpay_by_platform_pwd(Map<String, String> sParaTemp)
     throws Exception
   {
/* 248 */     sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
/* 249 */     sParaTemp.put("_input_charset", "UTF-8");
/* 250 */     String strButtonName = "退款";
/* 251 */     return buildForm(sParaTemp, "https://mapi.alipay.com/gateway.do?", "get", strButtonName);
   }

   public static String buildForm(Map<String, String> sParaTemp, String gateway, String strMethod, String strButtonName)
   {
/* 265 */     Map sPara = buildRequestPara(sParaTemp);
/* 266 */     List keys = new ArrayList((Collection)sPara.keySet());

/* 268 */     StringBuffer sbHtml = new StringBuffer();

/* 270 */     sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + gateway + 
/* 271 */       "_input_charset=" + "UTF-8" + "\" method=\"" + strMethod + 
/* 272 */       "\">");

/* 274 */     for (int i = 0; i < keys.size(); i++) {
/* 275 */       String name = (String)keys.get(i);
/* 276 */       String value = (String)sPara.get(name);

/* 278 */       sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
     }

/* 282 */     sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
/* 283 */     sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

/* 285 */     return sbHtml.toString();
   }

   private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp)
   {
/* 295 */     Map sPara = paraFilter(sParaTemp);

/* 297 */     String mysign = buildMysign(sPara);

/* 300 */     sPara.put("sign", mysign);
/* 301 */     sPara.put("sign_type", "MD5");

/* 303 */     return sPara;
   }

   public static String buildMysign(Map<String, String> sArray) {
/* 307 */     String prestr = createLinkString(sArray);
/* 308 */     prestr = prestr + (String)sArray.get("key");
/* 309 */     String mysign = md5(prestr);
/* 310 */     return mysign;
   }

   public static String md5(String text)
   {
/* 315 */     return DigestUtils.md5Hex(getContentBytes(text, "UTF-8"));
   }

   private static byte[] getContentBytes(String content, String charset)
   {
/* 320 */     if ((charset == null) || ("".equals(charset))) {
/* 321 */       return content.getBytes();
     }
     try
     {
/* 325 */       return content.getBytes(charset); } catch (UnsupportedEncodingException e) {
     }
/* 327 */     throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
   }

   public static String createLinkString(Map<String, String> params)
   {
/* 338 */     List keys = new ArrayList((Collection)params.keySet());
/* 339 */     Collections.sort(keys);

/* 341 */     String prestr = "";

/* 343 */     for (int i = 0; i < keys.size(); i++) {
/* 344 */       String key = (String)keys.get(i);
/* 345 */       String value = (String)params.get(key);

/* 347 */       if (i == keys.size() - 1)
/* 348 */         prestr = prestr + key + "=" + value;
       else {
/* 350 */         prestr = prestr + key + "=" + value + "&";
       }
     }

/* 354 */     return prestr;
   }

   public static Map<String, String> paraFilter(Map<String, String> sArray)
   {
/* 364 */     Map result = new HashMap();

/* 366 */     if ((sArray == null) || (sArray.size() <= 0)) {
/* 367 */       return result;
     }

/* 370 */     for (String key : sArray.keySet()) {
/* 371 */       String value = (String)sArray.get(key);
/* 372 */       if ((value != null) && (!value.equals("")) && (!key.equalsIgnoreCase("sign")) && 
/* 373 */         (!key.equalsIgnoreCase("sign_type")))
       {
/* 376 */         result.put(key, value);
       }
     }
/* 379 */     return result;
   }

   @RequestMapping({"/orderReturn/o_salesreturn.do"})
   public String salesreturn(Long id, HttpServletRequest request, ModelMap model) {
/* 384 */     WebErrors errors = validateEdit(id, request);
/* 385 */     if (errors.hasErrors()) {
/* 386 */       return errors.showErrorPage(model);
     }
/* 388 */     OrderReturn entity = this.manager.findById(id);
/* 389 */     entity.setStatus(Integer.valueOf(5));
/* 390 */     this.manager.update(entity);
/* 391 */     model.addAttribute("order", this.manager.findById(id).getOrder());
/* 392 */     return "orderReturn/view";
   }

   @RequestMapping({"/orderReturn/o_delete.do"})
   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
   {
/* 398 */     WebErrors errors = validateDelete(ids, request);
/* 399 */     if (errors.hasErrors()) {
/* 400 */       return errors.showErrorPage(model);
     }
/* 402 */     OrderReturn[] beans = this.manager.deleteByIds(ids);
/* 403 */     for (OrderReturn bean : beans) {
/* 404 */       log.info("delete OrderReturn id={}", bean.getId());
     }
/* 406 */     return list(null, pageNo, request, model);
   }

   private WebErrors validateEdit(Long id, HttpServletRequest request)
   {
/* 411 */     WebErrors errors = WebErrors.create(request);
/* 412 */     Website web = SiteUtils.getWeb(request);
/* 413 */     if (vldExist(id, web.getId(), errors)) {
/* 414 */       return errors;
     }
/* 416 */     return errors;
   }

   private WebErrors validateDelete(Long[] ids, HttpServletRequest request)
   {
/* 421 */     WebErrors errors = WebErrors.create(request);
/* 422 */     Website web = SiteUtils.getWeb(request);
/* 423 */     errors.ifEmpty(ids, "ids");
/* 424 */     for (Long id : ids) {
/* 425 */       vldExist(id, web.getId(), errors);
     }
/* 427 */     return errors;
   }

   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 431 */     if (errors.ifNull(id, "id")) {
/* 432 */       return true;
     }
/* 434 */     OrderReturn entity = this.manager.findById(id);
/* 435 */     if (errors.ifNotExist(entity, OrderReturn.class, id)) {
/* 436 */       return true;
     }
/* 438 */     return false;
   }
 }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.OrderReturnAct
 * JD-Core Version:    0.6.2
 */