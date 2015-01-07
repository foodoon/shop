/*     */ package guda.shop.cms.action.front;
/*     */ 
/*     */ import guda.shop.cms.entity.Order;
/*     */ import guda.shop.cms.entity.OrderReturn;
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.manager.OrderMng;
/*     */ import guda.shop.cms.manager.OrderReturnMng;
/*     */ import guda.shop.cms.manager.ShopDictionaryMng;
/*     */ import guda.shop.cms.web.ShopFrontHelper;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.cms.web.threadvariable.MemberThread;
/*     */ import guda.shop.common.security.annotation.Secured;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import guda.shop.core.web.front.FrontHelper;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Secured
/*     */ @Controller
/*     */ public class OrderReturnAct
/*     */ {
/*  39 */   private static final Logger log = LoggerFactory.getLogger(OrderReturnAct.class);
/*     */   private static final String NODELIVERY_ORDER_RETURN = "tpl.noDeliveryOrderReturn";
/*     */   private static final String DELIVERYED_ORDER_RETURN = "tpl.DeliveryedOrderReturn";
/*     */   private static final String NODELIVERY_RETURN_MONEY_SUCCESS = "tpl.NoDeliveryedReturnMoneySuccess";
/*     */   private static final String DELIVERY_RETURN_MONEY_SUCCESS = "tpl.DeliveryedReturnMoneySuccess";
/*     */ 
/*     */   @Autowired
/*     */   private OrderMng orderMng;
/*     */ 
/*     */   @Autowired
/*     */   private OrderReturnMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private ShopDictionaryMng shopDictionaryMng;
/*     */ 
/*     */   @RequestMapping({"/orderReturn/orderReturn.jspx"})
/*     */   public String getOrderReturn(Long orderId, Boolean delivery, HttpServletRequest request, ModelMap model)
/*     */   {
/*  54 */     Website web = SiteUtils.getWeb(request);
/*  55 */     ShopMember member = MemberThread.get();
/*  56 */     if (member == null) {
/*  57 */       return "redirect:../login.jspx";
/*     */     }
/*  59 */     WebErrors errors = validateOrderView(orderId, member, request);
/*  60 */     if (errors.hasErrors()) {
/*  61 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/*  63 */     Order order = this.orderMng.findById(orderId);
/*  64 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  65 */     List ndList = null;
/*  66 */     List returnList = this.shopDictionaryMng.getListByType(Long.valueOf(9L));
/*  67 */     model.addAttribute("returnList", returnList);
/*  68 */     model.addAttribute("order", order);
/*  69 */     model.addAttribute("delivery", delivery);
/*  70 */     if (delivery.booleanValue())
/*     */     {
/*  72 */       ndList = this.shopDictionaryMng.getListByType(Long.valueOf(8L));
/*  73 */       model.addAttribute("ndList", ndList);
/*  74 */       return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.DeliveryedOrderReturn", new Object[0]));
/*     */     }
/*     */ 
/*  77 */     ndList = this.shopDictionaryMng.getListByType(Long.valueOf(6L));
/*  78 */     model.addAttribute("ndList", ndList);
/*  79 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.noDeliveryOrderReturn", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/orderReturn/orderReturnRefer.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String getOrderReturnRefer(OrderReturn bean, Long orderId, Boolean delivery, Long reasonId, String[] picPaths, String[] picDescs, HttpServletRequest request, ModelMap model)
/*     */   {
/*  89 */     Website web = SiteUtils.getWeb(request);
/*  90 */     ShopMember member = MemberThread.get();
/*  91 */     if (member == null) {
/*  92 */       return "redirect:../login.jspx";
/*     */     }
/*  94 */     WebErrors errors = validateOrderView(orderId, member, request);
/*  95 */     if (errors.hasErrors()) {
/*  96 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/*  98 */     Order order = this.orderMng.findById(orderId);
/*     */ 
/* 100 */     OrderReturn orderReturn = this.manager.save(bean, order, reasonId, delivery, picPaths, picDescs);
/* 101 */     log.debug("orderReturn createTime is: {}", orderReturn.getCreateTime());
/*     */ 
/* 103 */     order.setReturnOrder(orderReturn);
/* 104 */     this.orderMng.updateByUpdater(order);
/* 105 */     model.addAttribute("order", order);
/* 106 */     model.addAttribute("orderReturn", orderReturn);
/* 107 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 108 */     if (delivery.booleanValue()) {
/* 109 */       return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.DeliveryedReturnMoneySuccess", new Object[0]));
/*     */     }
/* 111 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.NoDeliveryedReturnMoneySuccess", new Object[0]));
/*     */   }
/*     */ 
/*     */   private WebErrors validateOrderView(Long orderId, ShopMember member, HttpServletRequest request)
/*     */   {
/* 117 */     WebErrors errors = WebErrors.create(request);
/* 118 */     if (errors.ifNull(orderId, "orderId")) {
/* 119 */       return errors;
/*     */     }
/* 121 */     Order order = this.orderMng.findById(orderId);
/* 122 */     if (errors.ifNotExist(order, Order.class, orderId)) {
/* 123 */       return errors;
/*     */     }
/* 125 */     if (!order.getMember().getId().equals(member.getId())) {
/* 126 */       errors.noPermission(Order.class, orderId);
/* 127 */       return errors;
/*     */     }
/* 129 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.OrderReturnAct
 * JD-Core Version:    0.6.2
 */