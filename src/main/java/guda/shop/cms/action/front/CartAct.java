/*     */ package guda.shop.cms.action.front;
/*     */ 
/*     */ import guda.shop.cms.entity.Cart;
/*     */ import guda.shop.cms.entity.CartItem;
/*     */ import guda.shop.cms.entity.Order;
/*     */ import guda.shop.cms.entity.OrderItem;
/*     */ import guda.shop.cms.entity.PopularityGroup;
/*     */ import guda.shop.cms.entity.PopularityItem;
/*     */ import guda.shop.cms.entity.Product;
/*     */ import guda.shop.cms.entity.ProductFashion;
/*     */ import guda.shop.cms.entity.Shipping;
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.manager.AddressMng;
/*     */ import guda.shop.cms.manager.CartItemMng;
/*     */ import guda.shop.cms.manager.CartMng;
/*     */ import guda.shop.cms.manager.MemberCouponMng;
/*     */ import guda.shop.cms.manager.OrderMng;
/*     */ import guda.shop.cms.manager.PaymentMng;
/*     */ import guda.shop.cms.manager.PopularityGroupMng;
/*     */ import guda.shop.cms.manager.PopularityItemMng;
/*     */ import guda.shop.cms.manager.ProductFashionMng;
/*     */ import guda.shop.cms.manager.ProductMng;
/*     */ import guda.shop.cms.manager.ShippingMng;
/*     */ import guda.shop.cms.manager.ShopMemberAddressMng;
/*     */ import guda.shop.cms.service.ShoppingSvc;
/*     */ import guda.shop.cms.web.ShopFrontHelper;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.cms.web.threadvariable.MemberThread;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.Website;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CartAct
/*     */ {
/*  64 */   private static final Logger log = LoggerFactory.getLogger(CartAct.class);
/*     */   private static final String SHOPPING_CART = "tpl.shoppingCart";
/*     */   private static final String CHECKOUT_SHIPPING = "tpl.checkoutShipping";
/*     */ 
/*     */   @Autowired
/*     */   private OrderMng orderMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShoppingSvc shoppingSvc;
/*     */ 
/*     */   @Autowired
/*     */   private ProductMng productMng;
/*     */ 
/*     */   @Autowired
/*     */   private ProductFashionMng productFashionMng;
/*     */ 
/*     */   @Autowired
/*     */   private CartItemMng cartItemMng;
/*     */ 
/*     */   @Autowired
/*     */   private CartMng cartMng;
/*     */ 
/*     */   @Autowired
/*     */   private AddressMng addressMng;
/*     */ 
/*     */   @Autowired
/*     */   private PaymentMng paymentMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShippingMng shippingMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShopMemberAddressMng shopMemberAddressMng;
/*     */ 
/*     */   @Autowired
/*     */   private MemberCouponMng memberCouponMng;
/*     */ 
/*     */   @Autowired
/*     */   private PopularityGroupMng popularityGroupMng;
/*     */ 
/*     */   @Autowired
/*     */   private PopularityItemMng popularityItemMng;
/*     */ 
/*     */   @RequestMapping({"/cart/shopping_cart.jspx"})
/*     */   public String shoppingCart(String backUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  72 */     ShopMember member = MemberThread.get();
/*  73 */     if (member == null) {
/*  74 */       return "redirect:../login.jspx";
/*     */     }
/*  76 */     Website web = SiteUtils.getWeb(request);
/*  77 */     Cart cart = this.shoppingSvc.getCart(member, request, response);
/*  78 */     List popularityItems = null;
/*  79 */     if (cart != null) {
/*  80 */       popularityItems = this.popularityItemMng.getlist(cart.getId(), null);
/*     */     }
/*  82 */     model.addAttribute("cart", cart);
/*  83 */     if (!StringUtils.isBlank(backUrl)) {
/*  84 */       model.addAttribute("backUrl", backUrl);
/*     */     }
/*  86 */     model.addAttribute("popularityItems", popularityItems);
/*  87 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  88 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.shoppingCart", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/add_orderItem.jspx"})
/*     */   public void addToCart(Long productId, Integer productAmount, Long fashId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/*  99 */     Website web = SiteUtils.getWeb(request);
/*     */ 
/* 101 */     ShopMember member = MemberThread.get();
/* 102 */     JSONObject json = new JSONObject();
/* 103 */     if (member == null) {
/* 104 */       json.put("status", 0);
/*     */     } else {
/* 106 */       Product product = this.productMng.findById(productId);
/* 107 */       if (fashId == null) {
/* 108 */         if (productAmount.intValue() > product.getStockCount().intValue()) {
/* 109 */           json.put("status", 2);
/* 110 */           json.put("error", "库存不足");
/*     */         } else {
/* 112 */           Cart cart = this.shoppingSvc.collectAddToCart(product, fashId, null, productAmount.intValue(), true, member, web, request, response);
/* 113 */           json.put("status", 1);
/* 114 */           json.put("count", cart.getTotalItems());
/*     */         }
/*     */       } else {
/* 117 */         ProductFashion productFashion = this.productFashionMng.findById(fashId);
/* 118 */         if (productAmount.intValue() > productFashion.getStockCount().intValue()) {
/* 119 */           json.put("status", 2);
/* 120 */           json.put("error", productFashion.getAttitude() + "库存不足");
/*     */         } else {
/* 122 */           Cart cart = this.shoppingSvc.collectAddToCart(product, fashId, null, productAmount.intValue(), true, member, web, request, response);
/* 123 */           json.put("status", 1);
/* 124 */           json.put("count", cart.getTotalItems());
/*     */         }
/*     */       }
/*     */     }
/* 128 */     log.info("add to cart productId={}, count={}", productId, productAmount);
/* 129 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/add_popularity.jspx"})
/*     */   public void addToPopularity(Long popularityId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 136 */     Website web = SiteUtils.getWeb(request);
/*     */ 
/* 138 */     ShopMember member = MemberThread.get();
/* 139 */     JSONObject json = new JSONObject();
/* 140 */     if (member == null) {
/* 141 */       json.put("status", 0);
/*     */     }
/* 143 */     else if (getinventory(popularityId)) {
/* 144 */       Cart cart = null;
/* 145 */       for (Product product : this.popularityGroupMng.findById(popularityId).getProducts()) {
/* 146 */         cart = this.shoppingSvc.collectAddToCart(product, null, popularityId, 1, true, member, web, request, response);
/*     */       }
/* 148 */       this.popularityItemMng.save(cart, popularityId);
/* 149 */       json.put("status", 1);
/* 150 */       json.put("count", cart.getTotalItems());
/*     */     } else {
/* 152 */       json.put("status", 2);
/* 153 */       json.put("error", "库存不足");
/*     */     }
/*     */ 
/* 156 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   public boolean getinventory(Long popularityId)
/*     */   {
/* 161 */     for (Product product : this.popularityGroupMng.findById(popularityId).getProducts()) {
/* 162 */       if (1 > product.getStockCount().intValue()) {
/* 163 */         return false;
/*     */       }
/*     */     }
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/add_orderItem1.jspx"})
/*     */   public String orderAddToCart(Long orderId, Boolean isAdd, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 175 */     Website web = SiteUtils.getWeb(request);
/*     */ 
/* 177 */     ShopMember member = MemberThread.get();
/* 178 */     if (member == null) {
/* 179 */       return "redirect:../login.jspx";
/*     */     }
/* 181 */     Order order = this.orderMng.findById(orderId);
/* 182 */     Product product = null;
/* 183 */     Integer productAmount = Integer.valueOf(0);
/* 184 */     Long fashId = null;
/* 185 */     Cart cart = null;
/* 186 */     for (OrderItem item : order.getItems()) {
/* 187 */       product = item.getProduct();
/* 188 */       productAmount = item.getCount();
/* 189 */       if (item.getProductFash() != null) {
/* 190 */         fashId = item.getProductFash().getId();
/*     */       }
/* 192 */       cart = this.shoppingSvc.collectAddToCart(product, fashId, null, productAmount.intValue(), true, member, web, request, response);
/*     */     }
/* 194 */     return "redirect:shopping_cart.jspx";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/ajaxUpdateCartItem.jspx"})
/*     */   public void ajaxUpdateCartItem(Long cartItemId, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 202 */     ShopMember member = MemberThread.get();
/* 203 */     JSONObject json = new JSONObject();
/* 204 */     if (member == null) {
/* 205 */       json.put("status", 0);
/*     */     }
/* 207 */     CartItem cartItem = this.cartItemMng.findById(cartItemId);
/* 208 */     cartItem.setCount(count);
/* 209 */     cartItem.setScore(Integer.valueOf(cartItem.getProduct().getScore().intValue() * count.intValue()));
/* 210 */     this.cartItemMng.updateByUpdater(cartItem);
/* 211 */     log.info("update to cartItem cartItemId={}", cartItemId);
/* 212 */     json.put("status", 1);
/* 213 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/ajaxDeleteCartItem.jspx"})
/*     */   public void ajaxDeleteCartItem(Long cartItemId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 221 */     ShopMember member = MemberThread.get();
/* 222 */     JSONObject json = new JSONObject();
/* 223 */     if (member == null) {
/* 224 */       json.put("status", 0);
/*     */     }
/* 226 */     CartItem cartItem = this.cartItemMng.findById(cartItemId);
/* 227 */     Cart cart = cartItem.getCart();
/* 228 */     update(cart, cartItem);
/* 229 */     cart.getItems().remove(cartItem);
/* 230 */     this.cartMng.update(cart);
/*     */ 
/* 232 */     log.info("delete to cartItem cartItemId={}", cartItemId);
/* 233 */     json.put("status", 1);
/* 234 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   public void update(Cart cart, CartItem cartItem)
/*     */   {
/* 240 */     if (cartItem.getPopularityGroup() != null) {
/* 241 */       PopularityItem popularityItem = this.popularityItemMng.findById(cart.getId(), cartItem.getPopularityGroup().getId());
/* 242 */       if (popularityItem != null) {
/* 243 */         this.popularityItemMng.deleteById(popularityItem.getId());
/*     */       }
/* 245 */       if ((cart != null) && (cartItem.getPopularityGroup() != null)) {
/* 246 */         List list = this.cartItemMng.getlist(cart.getId(), cartItem.getPopularityGroup().getId());
/* 247 */         for (CartItem item : list) {
/* 248 */           item.setPopularityGroup(null);
/* 249 */           this.cartItemMng.updateByUpdater(item);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/checkStockCount.jspx"})
/*     */   public void checkStockCount(Long productId, String productFashionId, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 262 */     ShopMember member = MemberThread.get();
/* 263 */     JSONObject json = new JSONObject();
/* 264 */     if (member == null) {
/* 265 */       json.put("status", 0);
/*     */     } else {
/* 267 */       Product product = this.productMng.findById(productId);
/* 268 */       if (productFashionId.equals("undefined")) {
/* 269 */         if (count.intValue() > product.getStockCount().intValue()) {
/* 270 */           json.put("status", 2);
/* 271 */           json.put("error", product.getName() + "该商品库存不足。");
/*     */         } else {
/* 273 */           json.put("status", 1);
/*     */         }
/*     */       } else {
/* 276 */         ProductFashion productFashion = this.productFashionMng.findById(Long.valueOf(Long.parseLong(productFashionId)));
/* 277 */         if (count.intValue() > productFashion.getStockCount().intValue()) {
/* 278 */           json.put("error", product.getName() + productFashion.getAttitude() + "该款式库存不足。");
/* 279 */           json.put("status", 2);
/*     */         } else {
/* 281 */           json.put("status", 1);
/*     */         }
/*     */       }
/*     */     }
/* 285 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/ajaxtotalDeliveryFee.jspx"})
/*     */   public void ajaxtotalDeliveryFee(Long deliveryMethod, Double weight, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 293 */     ShopMember member = MemberThread.get();
/* 294 */     JSONObject json = new JSONObject();
/* 295 */     if (member == null) {
/* 296 */       json.put("status", 0);
/*     */     }
/* 298 */     Shipping shipping = this.shippingMng.findById(deliveryMethod);
/*     */ 
/* 300 */     Double freight = shipping.calPrice(weight);
/* 301 */     json.put("status", 1);
/* 302 */     json.put("freight", freight);
/* 303 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/cart/checkout_shipping.jspx"})
/*     */   public String shippingInput(Long[] cart2Checkbox, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 310 */     Website web = SiteUtils.getWeb(request);
/* 311 */     ShopMember member = MemberThread.get();
/* 312 */     if (member == null) {
/* 313 */       return "redirect:../login.jspx";
/*     */     }
/* 315 */     Cart cart = this.shoppingSvc.getCart(member.getId());
/* 316 */     if (cart == null) {
/* 317 */       return "redirect:shopping_cart.jspx";
/*     */     }
/* 319 */     List popularityItems = null;
/* 320 */     Double popularityPrice = Double.valueOf(0.0D);
/* 321 */     if (cart != null) {
/* 322 */       popularityItems = this.popularityItemMng.getlist(cart.getId(), null);
/* 323 */       for (PopularityItem popularityItem : popularityItems) {
/* 324 */         popularityPrice = Double.valueOf(popularityPrice.doubleValue() + popularityItem.getPopularityGroup().getPrice().doubleValue() * popularityItem.getCount().intValue());
/*     */       }
/*     */     }
/*     */ 
/* 328 */     Set items = getItems(cart2Checkbox, cart);
/* 329 */     Double price = getPrice(items);
/*     */ 
/* 331 */     List splist = this.shippingMng.getList(web.getId(), true);
/*     */ 
/* 333 */     List smalist = this.shopMemberAddressMng.getList(member.getId());
/*     */ 
/* 335 */     List plist = this.addressMng.getListById(null);
/*     */ 
/* 337 */     List paylist = this.paymentMng.getList(Long.valueOf(1L), true);
/* 338 */     model.addAttribute("memberCouponlist", this.memberCouponMng.getList(member.getId(), new BigDecimal(price.doubleValue())));
/* 339 */     model.addAttribute("items", getItems(cart2Checkbox, cart));
/* 340 */     model.addAttribute("smalist", smalist);
/* 341 */     model.addAttribute("plist", plist);
/* 342 */     model.addAttribute("paylist", paylist);
/* 343 */     model.addAttribute("splist", splist);
/* 344 */     model.addAttribute("popularityPrice", popularityPrice);
/* 345 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 346 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.checkoutShipping", new Object[0]));
/*     */   }
/*     */ 
/*     */   public Set<CartItem> getItems(Long[] cart2Checkbox, Cart cart) {
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
/* 363 */     for (CartItem item : items) {
/* 364 */       if (item.getProductFash() != null)
/* 365 */         price = Double.valueOf(price.doubleValue() + item.getProductFash().getSalePrice().doubleValue() * item.getCount().intValue());
/*     */       else {
/* 367 */         price = Double.valueOf(price.doubleValue() + item.getProduct().getSalePrice().doubleValue() * item.getCount().intValue());
/*     */       }
/*     */     }
/* 370 */     return price;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.CartAct
 * JD-Core Version:    0.6.2
 */