/*    */ package guda.shop.action.front;
/*    */ 
/*    */ import guda.shop.cms.entity.Coupon;
/*    */ import guda.shop.cms.entity.MemberCoupon;
/*    */ import guda.shop.cms.entity.ShopMember;
/*    */ import guda.shop.cms.manager.CouponMng;
/*    */ import guda.shop.cms.manager.MemberCouponMng;
/*    */ import guda.shop.cms.web.ShopFrontHelper;
/*    */ import guda.shop.cms.web.SiteUtils;
/*    */ import guda.shop.cms.web.threadvariable.MemberThread;
/*    */ import guda.shop.common.web.RequestUtils;
/*    */ import guda.shop.common.web.ResponseUtils;
/*    */ import guda.shop.common.web.springmvc.MessageResolver;
/*    */ import guda.shop.core.entity.Website;
/*    */ import guda.shop.core.web.front.URLHelper;
/*    */ import java.util.Date;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class CouponAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CouponMng manager;
/*    */ 
/*    */   @Autowired
/*    */   private MemberCouponMng memberCouponMng;
/*    */ 
/*    */   @RequestMapping(value={"/coupon*.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String list(HttpServletRequest request, ModelMap model)
/*    */   {
/* 40 */     Website web = SiteUtils.getWeb(request);
/* 41 */     int pageNo = URLHelper.getPageNo(request);
/* 42 */     ShopFrontHelper.setCommonData(request, model, web, pageNo);
/* 43 */     ShopFrontHelper.setDynamicPageData(request, model, web, RequestUtils.getLocation(request), "coupon", ".jspx", pageNo);
/* 44 */     return web.getTplSys("coupon", MessageResolver.getMessage(request, "tpl.coupon", new Object[0]));
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/getcoupon.jspx"})
/*    */   public void fetchGift(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
/* 49 */     Website web = SiteUtils.getWeb(request);
/* 50 */     ShopMember member = MemberThread.get();
/* 51 */     Date newdate = new Date();
/* 52 */     JSONObject json = new JSONObject();
/* 53 */     if (member == null) {
/* 54 */       json.put("status", 0);
/* 55 */     } else if ((id == null) || (this.manager.findById(id) == null)) {
/* 56 */       json.put("status", 2);
/* 57 */       json.put("error", "参数有误");
/* 58 */     } else if (this.memberCouponMng.findByCoupon(member.getId(), id) != null) {
/* 59 */       json.put("status", 2);
/* 60 */       json.put("error", "您已领取过该优惠券了");
/* 61 */     } else if (!this.manager.findById(id).getIsusing().booleanValue()) {
/* 62 */       json.put("status", 2);
/* 63 */       json.put("error", "该优惠券已停用");
/* 64 */     } else if (!newdate.before(this.manager.findById(id).getCouponEndTime())) {
/* 65 */       json.put("status", 2);
/* 66 */       json.put("error", "该优惠券已过期");
/* 67 */     } else if (this.manager.findById(id).getCouponCount().intValue() < 1) {
/* 68 */       json.put("status", 2);
/* 69 */       json.put("error", "该优惠券已领完");
/*    */     } else {
/* 71 */       Coupon coupon = this.manager.findById(id);
/* 72 */       MemberCoupon memberCoupon = new MemberCoupon();
/* 73 */       memberCoupon.setCoupon(coupon);
/* 74 */       memberCoupon.setMember(member);
/* 75 */       memberCoupon.setIsuse(Boolean.valueOf(false));
/* 76 */       coupon.setCouponCount(Integer.valueOf(coupon.getCouponCount().intValue() - 1));
/* 77 */       this.memberCouponMng.save(memberCoupon);
/* 78 */       this.manager.update(coupon);
/* 79 */       json.put("status", 1);
/*    */     }
/* 81 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 82 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.CouponAct
 * JD-Core Version:    0.6.2
 */