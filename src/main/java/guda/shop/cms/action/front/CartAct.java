 package guda.shop.cms.action.front;
/*     */*/ import gcms.entity.Cart;
/*     */ import guda.shop.y.CartItem;
/*     */ import guda.shop.cms.entit/*     */ import guda.shop.cms.entity.OrderIt  */ import guda.shop.cms.entity.PopularityGroup;/ import guda.shop.cms.entity.PopularityItem;
/*     */uda.shop.cms.entity.Product;
/*     */ import guda.shoity.ProductFashion;
/*     */ import guda.shop.y.Shipping;
/*     */ import guda.shop.cms.entity.Shop*     */ import guda.shop.cms.manager.AddressMng*/ import guda.shop.cms.manager.CartItemMng;
/*   rt guda.shop.cms.manager.CartMng;
/*     */ import .cms.manager.MemberCouponMng;
/*     */ import guda.manager.OrderMng;
/*     */ import guda.shop.cmsPaymentMng;
/*     */ import guda.shop.cms.manager.PopulpMng;
/*     */ import guda.shop.cms.manager.PopumMng;
/*     */ import guda.shop.cms.manager.Producng;
/*     */ import guda.shop.cms.manager.ProductMng;
/*  ort guda.shop.cms.manager.ShippingMng;
/*     */ import gums.manager.ShopMemberAddressMng;
/*     */ import guda.shovice.ShoppingSvc;
/*     */ import guda.shop.cms.wentHelper;
/*     */ import guda.shop.cms.web.SiteUti  */ import guda.shop.cms.web.threadvariable.MemberThread;
/*mport guda.shop.common.web.ResponseUtils;
/*     */ da.shop.common.web.springmvc.MessageResolver;
/*    t guda.shop.core.entity.Website;
/*     */ impmath.BigDecimal;
/*     */ import java.util.HashSet;
/*     */ ia.util.List;
/*     */ import java.util.Set;
/*     *javax.servlet.http.HttpServletRequest;
/*     */ import javax.ser.HttpServletResponse;
/*     */ import org.apach.lang.StringUtils;
/*     */ import orgNException;
/*     */ import org.jsoect;
/*     */ import org.slf4j.L     */ import org.slf4j.LoggerF*     */ import org.springframework.beans.factory.annotawired;
/*     */ import org.springframework.stereotype.Co
/*     */ import org.springframework.ui.ModelMap;
/* port org.springframework.web.bind.annotatstMapping;
/*     */ 
/*     */ @Contr    */ public class CartAct
/*     64 */   private static final Logger log = tory.getLogger(CartAct.class);
/*     */   private static final String SHRT = "tpl.shoppingCart";
/*     */   private static final StKOUT_SHIPPING = "tpl.checkoutShipping";
/*     */ /   @Autowired
/*     */   private OrderMng orderMng;
/*     */ 
/*     *wired
/*   ivate ShoppingSvc shop/*     */
/*     */   @Autowir */   private ProductMng productMng;
/*     */ 
/*     */   @Autowired
/*     */   private Productg productFashionMng;
/*     */ 
/*     */   @Autowired
/*     */   private Cg cartItemMng;
/*     */ 
/*     */   @Autowired
/*     */   private CartMng cartMng*/
/*     owired
/*     */   privssMng addressMng;
/*     */
/*     */ red
/*     ate PaymentMng paymentM  */
/*     */   @Autowired
/*     */   privingMng ship/*     */
/*     */   d
/*     */   private ShopMemberAddressMng rAddressMng*/
/*     */   @Autowi  */   private MemberCouponMng memberCouponMng;
/*     */*/   @Autow   */   private Popularng popularityGroupMng;
/*     */
/*     */  ed
/*     *te PopularityItemMng potemMng;
/*     */
/*     */   @Reque({"/cart/shrt.jspx"})
/*     */   ring shoppingCart(String backUrl, HttpServl request, HtResponse response, Model)
/*     */   {
/*  72 */     ShopMember MemberThrea/*  73 */     if (membe) {
/*  74 */       return "redirect:../login*     */   6 */     Website web = .getWeb(request);
/*  77 */     Cart cart = this.shoppingSvc.geber, requesse);
/*  78 */     ListtyItems = null;
/*  79 */     if (cart != null) {
/*     popular= this.popularityItemMn(cart.getId(), null);
/*     */     }
/*  82 */     model.ate("cart",   83 */     if (!Stringlank(backUrl)) {
/*  84 */       model.addAttribute("backkUrl);
/*   }
/*  86 */     model.addAttribute("popularityItems", popems);
/*  87 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  88 */     return web.getTplSys("member", MessageRetMessage(request, "tpl.shoppingCart", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/add_orderItem.jspx"})
/*     */   pubaddToCart(Long productId, Integer productAmount, Long fashId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/*  99 */     Website web = SiteUtils.getWeb(request);
/*     */
/* 101 */     ShopMember member = MemberThread.get();
/* 102 *NObject json = new JSONObject();
/* 103 */     if (member == null) {
/* 104 */       json.put("status", 0);
/*     */     } else {
/* 106 */       Product product = this.p.findById(productId);
/* 107 */       if (fashId == null) {
/* 108 */         if (productAmount.intValue() > product.getStockCount().intValue()) {
/* 109 */           json.put("status", 2);
/* 110 */           json.put("error", "库存不足");
/*     */         } else {
/* 112 */  Cart cart = pingSvc.colCart(product, fashId, null, productAmount.intValue(), true web, request, response);
/* 113 */           json.put("status", 1);
/* 114 */           json.put("count", cart.getTotalItems());
/*     */         }
/*     */ lse {
/* 117 */         ProductFashctFashion = this.productFashionMng.findById(fashId);
/* 118 */       ductAmount.intValue() > productFashion.getStockCount().intValue()) {
/* 119 */           json.put("status", 2);
/* 120 */           json.put("error", productFashion.getAttitude() + "库存不足");
         } else {
/* 122 */           Cart cart = this.shoppingSvc.collectAddToCart(product, fashId, null, productAmount.intValue(), true, member, web, request, response);
/* 123 */           json.put("status", 1);
/* 124 */           json.put("count", cart.getTotalItems());
/*     */         }
/*     */       */     }
/* 128 */     log.info("add to cart productId={}, count={}", productId, productAmount);
/* 129 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/add_popularity.jspx"})
/*     */   public void addToPopulg popularityId, Httpquest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 136 */     Website web = SiteUtils.getWeb(request);
/*     */ 
/* 138 */     ShopMember member = MemberThread.get();
/* 139 */     JSONObject json = new JSONObject();
/* 140 */     if (member == null) {
/* 141 */      ("status", 0);
/*     */     }
/* 143 */     else if (getinventory(popularityId)) {
/* 144 */       Cart cart = null;
/* 145 */       for (Product product : this.popularityGroupMng.findById(popularityId).getProducts()) {
/* 146 */         cart = this.shoppingSvc.collectAddToCart(produ popularityId, 1, trr, web, request, r
/*     */       }
/* 148 */       this.popularityItemMng.save(cart, popularityId);
/* 149 */       json.put("status", 1);
/* 150 */       json.put("count", cart.getTotalI/*     */     /* 152 */  .put("status", 2);
/* 153 */       json.put("error", "库存不足" */     }
/*     */ 
/* 156 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   public btinventory(Long popularityId)
/*   /* 161 */     for (Product product : this.popularityGroupMng.findByIdtyId).getProducts()) {
/* 162 */       if (1 > product.getStockCount().intValue()) {
/* 163 */         return false;
/*     */       }
/*     */     }
/* 166 */     return true;
/*     */   */
/*     */   @RequestMapping({"/cart/add_orderItem1.jspx"})
/*     */   public String orderAddToCart(Long orderId, Boolean isAdd, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 175 */     Website web = SiteUtils.getWeb(request);
/*     */ 
/* 177 */     r member = MemberThread.get();
/* 178 */     if (member == null) {
/* 179 */       return "redirect:../login.jspx";
/*     */     }
/* 181 */     Order order = this.orderMng.findBId);
/* 182 */     Product product = null;
/* 183 */     Integer productAmount = Integer.valueOf(0);
/* 1 Long fashId = n85 */     Cart cart = null;
/* 186 */     for (OrderItem item : order.getItems187 */       pitem.getPro* 188 */       productAmount = item.getCount();
/* 189 */  item.getProductFash() != null) {
/* 190 */         fashId = item.getProductFash().getId();
/*     */       }
/* 192 */       cart = this.shoppingSvc.collectAddToCart(product, fashId, null, productAmount.intVale, member, web, response);
/*     */     }
/* 194 */     retuect:shopping_c;
/*     */   */
/*     */   @RequestMapping({"/cart/ajaxUpdateCartIt)
/*     */   public void ajaxUpdateCartItem(Long cartItemId, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap      */     throws JSONException
/* {
/* 202 */     ShopMember member = MemberThread.get();
/* 203 */   ect json = new JSONObject();
/* 204 */     if (member == null) {
/* 205 */       json.put("status", 0);
/*     */     }
/* 207 */     CartItem cartItecartItemMng.findById(cartItemId);
/* 208 */     cartItem.setCount(count);
/* 209 */     cartItem.setScore(Integer.valueOf(cartItem.getProduct().getScore().intValue() * count.intValue()));
/* 210 */     this.cartItemMng.updateByUpdater(cartItem);
/* 211 */     log.info("update to cartItem cartItemId={}", cartItemId);
/* 212 */     json.put("status", 1);
/* 213 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */
/*     */   @RequestMapping({"/cart/ajaxDeleteCartIte
/*     */   public void ajaxDeleteCartItem(Long cartItemId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONExc     */   {
/* 221 */     ShopMember member = MemberThread.get();
/*   JSONObject w JSONObjec23 */     if (member == null) {
/* 224 */       json.put("statu*     */     }
/* 226 */     CartItem cartItem = this.cartItemMng.findById(cartItemId);
/* 227 */     Cart cart = cartItem.getCart();
/* 228 */     ut, cartItem);
/* 229 */     cart.geremove(cartItem);
/* 230 */     this.cartMng.update(cart);
/*     */ 
/* 232 */     log.info("delete to cartItem cartItemId={}", cartItemId);
/* 233 */     json.put("status", 1);
/* 234 */     tils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   public void update(Cart cart, CartItem cartItem)
/*     */   {
/* 240 */     if (cartItem.getPopularityGroup() != null) {
/* 241 */       PopularityItem popularityItem = this.popularityItemMng.findById(cart.getId(), cartItem.getPopularityGroup().getId());
/* 242 */       if (popularityItem != null) {
/* 243 */         this.popularityItemMng.deleteById(popularityItem.getId());
/*     */       }/       if ((cll) && (carPopularityGroup() != null)) {
/* 246 */         List list = thimMng.getlist(cart.getId(), cartItem.getPopularityGroup().getId());
/* 247 */         for (CartItem item : list) {
/* 248 */           opularityGroup(null);
/* 249 */    is.cartItemMng.updateByUpdater(item);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/checkStockCount.jspx"})
/*     */   publheckStockCount(Long productId, String productFashionId, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 262 */     ShopMember member = MemberThread.get();
/* 263 */     t json = new JSONObject();
/* 264 */     if (member == null) {
/* 265 */       json.put("status", 0);
/*     */     } else {
/* 267 */       Product product = this.productMng.findById(pro/* 268 */     ductFashion("undefined")) {
/* 269 */         if (count.intValue() > protockCount().intValue()) {
/* 270 */           json.put("status", 2);
/* 271 */           json.put("error", product.getName() + "该商品库存不足。");
/*     */         } else {
/* 273 */           json.put("status", 1);
/*     */         }
/*     */       } else {
/* 276 */         ProductFashion productFashion = this.productFashionMng.fing.valueOf(Long.parseLong(productFashionId)));
/* 277 */         if (count.intValue() > productFashion.getStockCount().intValue()) {
/* 278 */           json.put("error", product.getName() + productFashion.getAttitude() + "该款式库存不足。");
/* 279 */           json.put("status", 2);
/*     */         } else {
/* 281 */           json.put("status", 1);
/*     */         */       }
/*     */ 285 */     ResponenderJson(respontoString());
/  }
/*       */   @RequestMapping({"/cart/ajaxtotalDeliveryFee.jspx"})
   public void ajaxtotalDeliveryFee(Long deliveryMethod, Double weight, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSOn
/*     */   {
/* 293 */     ShopMber = MemberThread.get();
/* 294 */     JSONObject json = new JSONObject();
/* 295 */     if (member == null) {
/* 296 */       json.put("status", 0);
/*     */     }
/* 298 */     Shipping shihis.shippingMng.findById(deliveryMethod);
/*     */
/* 300 */     Double freight = shipping.calPrice(weight);
/* 301 */     json.put("status", 1);
/* 302 */     json.put("freight", freight);
/* 303 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/checkout_shipping.jspx"})
/  public String shippingInput(Long[] cart2Checkbox, HttpServletRequest HttpServletResponse, ModelMap model)
/*     */   {
/* 310 */     Website web = SiteUtils.getWeb(request);
/* 311 */     ShopMember member = MemberThread.get();
/* 312 */     if (member == null) {
/* 313 */       return "redirect:../login.jspx";
/*     */     }
/* 315 */     Cart cart = this.shoppingSvc.getCart(member.getId());
/* 316 */     if (cart == null) {
/* 317 */       return "redirect:shopping_c;
/*     */     }
/* 319 */     List popularityItems = null;
/* 320 */le popularityPrice =alueOf(0.0D);
/* 3 if (cart != null) {
/* 322 */       popularityItems = this.popularityItemMng.getlietId(), null);/       forityItem popularityItem : popularityItems) {
/* 324 */         popice = Double.valueOf(popularityPrice.doubleValue() + popularityItem.getPopularityGroup().getPrice().doubleValue() * popularityItem.getCount().intValue());
       }
/*     */     }
/*     */ /     Set items = getItems(cart2Checkbox, cart);
/* 329 */     Double price = getPrice(items);
/*     */ 
/* 331 */     List splist = this.shippingMng.getList(web.getId(), true);
/*     */
/*   List smalist = this.shopMemberAddressMng.getList(member.getId());
/*     */ 
/* 335 */     t = this.addressMng.getListById(null);
/*     */ 
/* 337 */     List paylist = this.paymentMng.getList(Long.valueOf(1L), true);
/* 338 */     model.addAttribute("memberCouponlist", this.memberCouponMng.getList(member.new BigDecimalubleValue()39 */     model.addAttribute("items", getItems(cart2Checkbox, * 340 */     model.addAttribute("smalist", smalist);
/* 341 */     model.addAttribute("plist", plist);
/* 342 */     model.addAttribute(, paylist);
/* 343 */     model.addAttribute("splist", splist);
/* 344 */     model.addAttribute("popularityPrice", popularityPrice);
/* 345 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 34return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.checkoutShipping", new Object[0]));
/*     */   }
/*     */ 
/*     */   public Set<CartItem> getIte cart2Checkbox, Cart cart) {
/* 350 */     Set items = new HashSet();
/* 351 */     if (cart2Checkbox != null) {
/* 352 */       for (Long id : cart2Checkbox)
/* 353 */         items.add(this.cartItemMng.findById(id));
/*     */     }
/*     */     else {
/* 356 */       items = cart.getItems();
/*     */     }
/* 358 */     return items;
/*     */   }
/*     */ 
/*     */   public Double getPrice(Set<CartItem> items) {
/* 362 */     Double price = Double.valueOf(0.0D);
/* 363 */     for (Carm : items) {
/* 36  if (item.getPr() != null)
/* 365 */         price = Double.valueOf(price.doubleValue() + item.getProductFash().getSalePrice().do() * item.getCount().intValue());
/*     */       else {
/* 367 */         price = DueOf(price.doubleValue() + item.getProduct().getSalePrice().doubleValue() * item.getCount()());
/*     */       }
/*     */     }
/* 370 */     return price;
/*    *     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.CartAct
 * JD-Core Version:    0.6.2
 */