/*     */ package guda.shop.action.front;
/*     */ 
/*     */ import guda.shop.cms.entity.Cart;
/*     */ import guda.shop.cms.entity.Order;
/*     */ import guda.shop.cms.entity.OrderItem;
/*     */ import guda.shop.cms.entity.OrderReturn;
/*     */ import guda.shop.cms.entity.Product;
/*     */ import guda.shop.cms.entity.ProductFashion;
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.entity.ShopScore;
/*     */ import guda.shop.cms.manager.OrderMng;
/*     */ import guda.shop.cms.manager.OrderReturnMng;
/*     */ import guda.shop.cms.manager.PaymentMng;
/*     */ import guda.shop.cms.manager.PaymentPluginsMng;
/*     */ import guda.shop.cms.manager.ProductFashionMng;
/*     */ import guda.shop.cms.manager.ProductMng;
/*     */ import guda.shop.cms.manager.ShippingMng;
/*     */ import guda.shop.cms.manager.ShopMemberMng;
/*     */ import guda.shop.cms.manager.ShopScoreMng;
/*     */ import guda.shop.cms.service.ShoppingSvc;
/*     */ import guda.shop.cms.web.ShopFrontHelper;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.cms.web.threadvariable.MemberThread;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.common.security.annotation.Secured;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import guda.shop.core.web.front.FrontHelper;
/*     */ import guda.shop.core.web.front.URLHelper;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Secured
/*     */ @Controller
/*     */ public class OrderAct
/*     */ {
/*     */   private static final String MY_ORDER = "tpl.myOrder";
/*     */   private static final String MY_RETURN_ORDER = "tpl.myReturnOrder";
/*     */   private static final String MY_ORDER_VIEW = "tpl.myOrderView";
/*     */   private static final String SUCCESSFULLY_ORDER = "tpl.successfullyOrder";
/*     */ 
/*     */   @Autowired
/*     */   private OrderMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private ShippingMng shippingMng;
/*     */ 
/*     */   @Autowired
/*     */   private PaymentMng paymentMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShoppingSvc shoppingSvc;
/*     */ 
/*     */   @Autowired
/*     */   private PaymentPluginsMng paymentPluginsMng;
/*     */ 
/*     */   @Autowired
/*     */   private ProductMng productMng;
/*     */ 
/*     */   @Autowired
/*     */   private ProductFashionMng productFashionMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShopMemberMng shopMemberMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShopScoreMng shopScoreMng;
/*     */ 
/*     */   @Autowired
/*     */   private OrderReturnMng orderReturnMng;
/*     */ 
/*     */   @RequestMapping({"/order/myorder*.jspx"})
/*     */   public String myOrder(Integer status, String code, String userName, Long paymentId, Long shippingId, String startTime, String endTime, Double startOrderTotal, Double endOrderTotal, HttpServletRequest request, ModelMap model)
/*     */   {
/*  75 */     Website web = SiteUtils.getWeb(request);
/*  76 */     ShopMember member = MemberThread.get();
/*  77 */     if (member == null) {
/*  78 */       return "redirect:../login.jspx";
/*     */     }
/*  80 */     if (StringUtils.isBlank(userName)) {
/*  81 */       userName = null;
/*     */     }
/*  83 */     if (StringUtils.isBlank(startTime)) {
/*  84 */       startTime = null;
/*     */     }
/*  86 */     if (StringUtils.isBlank(endTime)) {
/*  87 */       endTime = null;
/*     */     }
/*  89 */     List shippingList = this.shippingMng.getList(web.getId(), true);
/*  90 */     List paymentList = this.paymentMng.getList(web.getId(), true);
/*  91 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/*  92 */     model.addAttribute("paymentList", paymentList);
/*  93 */     model.addAttribute("shippingList", shippingList);
/*  94 */     model.addAttribute("status", status);
/*  95 */     model.addAttribute("code", code);
/*  96 */     model.addAttribute("userName", userName);
/*  97 */     model.addAttribute("startTime", startTime);
/*  98 */     model.addAttribute("endTime", endTime);
/*  99 */     model.addAttribute("paymentId", paymentId);
/* 100 */     model.addAttribute("shippingId", shippingId);
/* 101 */     model.addAttribute("startOrderTotal", startOrderTotal);
/* 102 */     model.addAttribute("endOrderTotal", endOrderTotal);
/* 103 */     Integer pageNo = Integer.valueOf(URLHelper.getPageNo(request));
/* 104 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 105 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "myorder", ".jspx", pageNo.intValue());
/* 106 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myOrder", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/order/myOrderView.jspx"})
/*     */   public String myOrderView(Long orderId, HttpServletRequest request, ModelMap model)
/*     */   {
/* 115 */     Website web = SiteUtils.getWeb(request);
/* 116 */     ShopMember member = MemberThread.get();
/* 117 */     if (member == null) {
/* 118 */       return "redirect:../login.jspx";
/*     */     }
/* 120 */     WebErrors errors = validateOrderView(orderId, member, request);
/* 121 */     if (errors.hasErrors()) {
/* 122 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/* 124 */     Order order = this.manager.findById(orderId);
/* 125 */     model.addAttribute("order", order);
/* 126 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 127 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myOrderView", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/order/order_shipping.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String orderShipping(Long deliveryInfo, Long shippingMethodId, Long paymentMethodId, Long[] cartItemId, String comments, String memberCouponId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 136 */     Website web = SiteUtils.getWeb(request);
/* 137 */     ShopMember member = MemberThread.get();
/* 138 */     if (member == null) {
/* 139 */       return "redirect:../login.jspx";
/*     */     }
/* 141 */     Order order = null;
/* 142 */     Cart cart = this.shoppingSvc.getCart(member.getId());
/* 143 */     if (cart != null) {
/* 144 */       order = this.manager.createOrder(cart, cartItemId, shippingMethodId, deliveryInfo, paymentMethodId, comments, request.getRemoteAddr(), member, web.getId(), memberCouponId);
/*     */     }
/* 146 */     this.shoppingSvc.clearCookie(request, response);
/* 147 */     List list = this.paymentPluginsMng.getList();
/* 148 */     model.addAttribute("list", list);
/* 149 */     model.addAttribute("order", order);
/* 150 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 151 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.successfullyOrder", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/order/deleteOrder.jspx"})
/*     */   public void deleteOrder(Long orderId, HttpServletRequest request, HttpServletResponse response)
/*     */     throws JSONException
/*     */   {
/* 161 */     JSONObject json = new JSONObject();
/* 162 */     if (orderId != null) {
/* 163 */       Order order = this.manager.findById(orderId);
/* 164 */       order.getItems().clear();
/* 165 */       this.manager.deleteById(orderId);
/*     */     }
/* 167 */     json.put("success", true);
/* 168 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/order/abolishOrder.jspx"})
/*     */   public void abolishOrder(Long orderId, HttpServletRequest request, HttpServletResponse response)
/*     */     throws JSONException
/*     */   {
/* 177 */     JSONObject json = new JSONObject();
/* 178 */     ShopMember member = MemberThread.get();
/* 179 */     if (orderId != null) {
/* 180 */       Order order = this.manager.findById(orderId);
/* 181 */       order.setStatus(Integer.valueOf(3));
/* 182 */       Set set = order.getItems();
/*     */       Product product;
/* 184 */       for (OrderItem item : set) {
/* 185 */         product = item.getProduct();
/* 186 */         if (item.getProductFash() != null) {
/* 187 */           ProductFashion fashion = item.getProductFash();
/* 188 */           fashion.setStockCount(Integer.valueOf(fashion.getStockCount().intValue() + item.getCount().intValue()));
/* 189 */           product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
/* 190 */           this.productFashionMng.update(fashion);
/*     */         } else {
/* 192 */           product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
/*     */         }
/* 194 */         this.productMng.updateByUpdater(product);
/*     */       }
/*     */ 
/* 197 */       member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - order.getScore().intValue()));
/* 198 */       this.shopMemberMng.update(member);
/* 199 */       List list = this.shopScoreMng.getlist(Long.toString(order.getCode()));
/* 200 */       for (ShopScore s : list) {
/* 201 */         this.shopScoreMng.deleteById(s.getId());
/*     */       }
/* 203 */       this.manager.updateByUpdater(order);
/*     */     }
/* 205 */     json.put("success", true);
/* 206 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/order/accomplishOrder.jspx"})
/*     */   public void accomplishOrder(Long orderId, HttpServletRequest request, HttpServletResponse response)
/*     */     throws JSONException
/*     */   {
/* 216 */     JSONObject json = new JSONObject();
/* 217 */     ShopMember member = MemberThread.get();
/* 218 */     if (orderId != null) {
/* 219 */       Order order = this.manager.findById(orderId);
/* 220 */       order.setStatus(Integer.valueOf(4));
/*     */ 
/* 222 */       member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - order.getScore().intValue()));
/* 223 */       member.setScore(Integer.valueOf(member.getScore().intValue() + order.getScore().intValue()));
/* 224 */       this.shopMemberMng.update(member);
/* 225 */       List list = this.shopScoreMng.getlist(Long.toString(order.getCode()));
/* 226 */       for (ShopScore s : list) {
/* 227 */         s.setStatus(true);
/* 228 */         this.shopScoreMng.update(s);
/*     */       }
/* 230 */       this.manager.updateliRun(orderId);
/* 231 */       this.manager.updateByUpdater(order);
/*     */     }
/* 233 */     json.put("success", true);
/* 234 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/order/order_payAgain.jspx"})
/*     */   public String payOrderAgain(Long orderId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 242 */     Website web = SiteUtils.getWeb(request);
/* 243 */     ShopMember member = MemberThread.get();
/* 244 */     if (member == null) {
/* 245 */       return "redirect:../login.jspx";
/*     */     }
/* 247 */     WebErrors errors = validateOrderView(orderId, member, request);
/* 248 */     if (errors.hasErrors()) {
/* 249 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/* 251 */     this.shoppingSvc.clearCookie(request, response);
/* 252 */     Order order = this.manager.findById(orderId);
/* 253 */     List list = this.paymentPluginsMng.getList();
/* 254 */     model.addAttribute("list", list);
/* 255 */     model.addAttribute("order", order);
/* 256 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 257 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.successfullyOrder", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/order/myReturnOrder*.jspx"})
/*     */   public String myReturnOrder(HttpServletRequest request, ModelMap model)
/*     */   {
/* 265 */     Website web = SiteUtils.getWeb(request);
/* 266 */     ShopMember member = MemberThread.get();
/* 267 */     if (member == null) {
/* 268 */       return "redirect:../login.jspx";
/*     */     }
/*     */ 
/* 271 */     Integer pageNo = Integer.valueOf(URLHelper.getPageNo(request));
/* 272 */     Pagination pagination = this.manager.getPageForOrderReturn(member.getId(), pageNo.intValue(), 10);
/* 273 */     model.addAttribute("pagination", pagination);
/* 274 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/* 275 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 276 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "myReturnOrder", ".jspx", pageNo.intValue());
/* 277 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myReturnOrder", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/order/shipments.jspx"})
/*     */   public String shipments(Long id, HttpServletRequest request, ModelMap model)
/*     */   {
/* 286 */     Website web = SiteUtils.getWeb(request);
/* 287 */     ShopMember member = MemberThread.get();
/* 288 */     if (member == null) {
/* 289 */       return "redirect:../login.jspx";
/*     */     }
/* 291 */     WebErrors errors = validateOrderReturnView(id, member, request);
/* 292 */     if (errors.hasErrors()) {
/* 293 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/* 295 */     OrderReturn entity = this.orderReturnMng.findById(id);
/* 296 */     entity.setStatus(Integer.valueOf(4));
/* 297 */     this.orderReturnMng.update(entity);
/* 298 */     return myReturnOrder(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/order/accomplish.jspx"})
/*     */   public String accomplish(Long id, HttpServletRequest request, ModelMap model)
/*     */   {
/* 306 */     Website web = SiteUtils.getWeb(request);
/* 307 */     ShopMember member = MemberThread.get();
/* 308 */     if (member == null) {
/* 309 */       return "redirect:../login.jspx";
/*     */     }
/* 311 */     WebErrors errors = validateOrderReturnView(id, member, request);
/* 312 */     if (errors.hasErrors()) {
/* 313 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/* 315 */     OrderReturn entity = this.orderReturnMng.findById(id);
/* 316 */     entity.setStatus(Integer.valueOf(7));
/* 317 */     Set set = entity.getOrder().getItems();
/*     */     Product product;
/* 319 */     for (OrderItem item : set) {
/* 320 */       product = item.getProduct();
/* 321 */       if (item.getProductFash() != null) {
/* 322 */         ProductFashion fashion = item.getProductFash();
/* 323 */         fashion.setStockCount(Integer.valueOf(fashion.getStockCount().intValue() + item.getCount().intValue()));
/* 324 */         product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
/* 325 */         this.productFashionMng.update(fashion);
/*     */       } else {
/* 327 */         product.setStockCount(Integer.valueOf(product.getStockCount().intValue() + item.getCount().intValue()));
/*     */       }
/* 329 */       this.productMng.updateByUpdater(product);
/*     */     }
/*     */ 
/* 332 */     member.setFreezeScore(Integer.valueOf(member.getFreezeScore().intValue() - entity.getOrder().getScore().intValue()));
/* 333 */     this.shopMemberMng.update(member);
/* 334 */     List list = this.shopScoreMng.getlist(Long.toString(entity.getOrder().getCode()));
/* 335 */     for (ShopScore s : list) {
/* 336 */       this.shopScoreMng.deleteById(s.getId());
/*     */     }
/* 338 */     this.orderReturnMng.update(entity);
/* 339 */     return myReturnOrder(request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateOrderView(Long orderId, ShopMember member, HttpServletRequest request)
/*     */   {
/* 344 */     WebErrors errors = WebErrors.create(request);
/* 345 */     if (errors.ifNull(orderId, "orderId")) {
/* 346 */       return errors;
/*     */     }
/* 348 */     Order order = this.manager.findById(orderId);
/* 349 */     if (errors.ifNotExist(order, Order.class, orderId)) {
/* 350 */       return errors;
/*     */     }
/* 352 */     if (!order.getMember().getId().equals(member.getId())) {
/* 353 */       errors.noPermission(Order.class, orderId);
/* 354 */       return errors;
/*     */     }
/* 356 */     return errors;
/*     */   }
/*     */ 
/*     */   public String getHistoryProductIds(HttpServletRequest request)
/*     */   {
/* 361 */     String str = null;
/* 362 */     Cookie[] cookie = request.getCookies();
/* 363 */     int num = cookie.length;
/* 364 */     for (int i = 0; i < num; i++) {
/* 365 */       if (cookie[i].getName().equals("shop_record")) {
/* 366 */         str = cookie[i].getValue();
/* 367 */         break;
/*     */       }
/*     */     }
/* 370 */     return str;
/*     */   }
/*     */ 
/*     */   private WebErrors validateOrderReturnView(Long orderReturnId, ShopMember member, HttpServletRequest request)
/*     */   {
/* 375 */     WebErrors errors = WebErrors.create(request);
/* 376 */     if (errors.ifNull(orderReturnId, "orderReturnId")) {
/* 377 */       return errors;
/*     */     }
/* 379 */     OrderReturn orderReturn = this.orderReturnMng.findById(orderReturnId);
/* 380 */     if (errors.ifNotExist(orderReturn, OrderReturn.class, orderReturnId)) {
/* 381 */       return errors;
/*     */     }
/* 383 */     if (!orderReturn.getOrder().getMember().getId().equals(member.getId())) {
/* 384 */       errors.noPermission(OrderReturn.class, orderReturnId);
/* 385 */       return errors;
/*     */     }
/* 387 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.OrderAct
 * JD-Core Version:    0.6.2
 */