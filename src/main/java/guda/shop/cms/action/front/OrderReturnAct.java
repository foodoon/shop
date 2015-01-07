 package guda.shop.cms.action.front;
/*     */*/ import gcms.entity.Order;
/*     */ import guda.shop.y.OrderReturn;
/*     */ import guda.shop.cms.entitber;
/*     */ import guda.shop.cms.manager.OrderM  */ import guda.shop.cms.manager.OrderReturnMng;/ import guda.shop.cms.manager.ShopDictionaryMng;
/*   rt guda.shop.cms.web.ShopFrontHelper;
/*     */ import guds.web.SiteUtils;
/*     */ import guda.shop.cms.web.iable.MemberThread;
/*     */ import guda.shopecurity.annotation.Secured;
/*     */ import guda.shop.common.wevc.MessageResolver;
/*     */ import guda.shop.core.entity.Webs   */ import guda.shop.core.web.WebErrors;
/*     */ import guda..web.front.FrontHelper;
/*     */ import java.ut/*     */ import javax.servlet.http.HttpServlet/*     */ import org.slf4j.Logger;
/*     */ import orgggerFactory;
/*     */ import orgamework.beans.factory.annotation.Autowired;
/*     */ imspringframework.stereotype.Controll  */ import org.springframework.ui.ModelMa */ import org.springframework.web.bind.annotation.RequestMapping;
/*       */ @Secured
/*     */ @Controller
/*     */ public class rnAct
/*     */ {
/*  39 */   private static finalog = LoggerFactory.getLogger(OrderReturnAct.class);
/*     */   private sal String N_ORDER_RETURN = "tperyOrderReturn";
/*   ivate static final String DELIVERYED_ORN = "tpl.DeliveryedOrderReturn";
/*     */   private static final String NODELIVERY_RETURN_MONEY_SUCCESSoDeliveryedReturnMoneySuccess";
/*     */   private static final String DELIVERY_RETURN_MONEY_S"tpl.DeliveryedReturnMoneySuccess";
/*     */
/*     */   @Autowired
/*     */   private OrderMng;
/*     */ 
/*     */   @Autowired
/*     */   private OrderReturnMng manager;
/*     */
/*     */   @Autow   */   private ShopDictionaryMng shopDictionaryMng;
/*     */
/*     */   @RequestMapping({"/orderReturn/on.jspx"})
/  public String getOrdeong orderId, Boolean delivery, HttpServt request, model)
/*     */   {
/*   Website web = SiteUtils.getWeb(request);
     ShopMeer = MemberThread.get()*/     if (member == null) {
/*  57 */       return "rediogin.jspx";/     }
/*  59 */     WebErrors errors = validateOrderView(ordeer, request);
/*  60 */     if (errors.hasErrors()) {
/*  61 */       return FrontHelper.showError(errors, web, model);
/*     */     }
/*  63 */     Order order = this.orderMng.findById(orderId);
/*  64 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  65 */     List ndList = null;
/*  66 */     List returnis.shopDictionaryMng.getListByType(Long.valueOf(9L));
/*  67 */     model.addAttribute("returnList", returnList);
/*  68 */     model.addAttribute("order", order);
/*  69 */     model.addAttribute("delivery", ;
/*  70 */     if (delivery.booleanValue())
/*     */     {
/*  72 */       ndList = this.shopDictionaryMng.getListByType(Long.valueOf(8L));
/*  73 */       model.addAttribute("ndList", ndList);
/*  74 */       return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.DeliveryedOrderReturn", new Object[0]));
/*     */     }
/*     */ 
/*  77 */     ndList = this.shopDictionaryMng.getListByType(Long.valueOf(6L));
/*  78 */     model.addAttribute("ndList", ndL 79 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.noDeliveryOrderReturn", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/orderReturn/orderReturnRefer.jspx"}, method={org.springframework.web.bind.annotation.RequestMe})
/*     */   ping getOrderReturnRefer(OrderReturn bean, Long orderId, Boolean delivery, Long reasonId, String[] picPaths, String[] picDescs, HttpServletRequest request, ModelMap model)
/*     */   {
/*  89 */     Website web = SiteUtils.getWeb(request);
/*  90 */     ShopMember memberThread.get()*/     if ( null) {
/*  92 */       return "redirect:../login.jspx";
/*     */     }
/*  94 */     WebErrors errors = validateOrderView(orderId, member, r/*  95 */     if (errors.hasErrors()) {
/*  96 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/*  98 */     Order order = this.orderMng.findById(orderId);
/
/* 100 */     OrderReturn orderReturn = this.manager.save(bean, order, reasonId, delivery, picPaths, picDescs);
/* 101 */     log.debug("orderReturn createTime is: {}", orderReturn.getCreateTime());
/*     * */     order.setReturnOrder(orderReturn);
/* 104 */     this.orderMng.updateByUpdater(order);
/* 105 */     model.addAttribute("order", order);
/* 106 */     model.addAttribute("orderReturn", orderReturn);
/*   ShopFrontHelper.setCommonData(request, model, web, 1);
/* 108 */     if (dooleanValue()) {
/* 109 */       return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.DeliveryedReturnMoneySuccess", new Object[0]));
/*     */     }
/* 111 */     return web.getTplSys("membegeResolver.getMessage(request, "tpl.NoDeliveryedReturnMoneySuccess", new Object[0]));
/*     */   }
/*     */ 
/*     */   private WebErrors validateOrderView(Long orderId, ShopMember member, HttpServletRequest request)
/*     */   {
/* 117 */     WebErrors errors = WebErrors.create(request);
/* 118 */     if (errors.ifNull(orderId, "orderId")) {
/* 119 */       return errors;
/*     */     }
/* 121 */     Order order = this.orderMng.findById(orderId);
/* 122 */     if fNotExist(order, Order.class, orderId)) {
/* 123 */       return errors;
/*     */     }
/* 125 */     if (!order.getMember().getId().equals(member.getI* 126 */      oPermissionass, orderId);
/* 127 */       return errors;
/*     */     }
/* 129 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.OrderReturnAct
 * JD-Core Version:    0.6.2
 */