/*    */ package guda.shop.cms.action.front;
/*    */ 
/*    */ import guda.shop.cms.entity.ShopMember;
/*    */ import guda.shop.cms.manager.MemberCouponMng;
/*    */ import guda.shop.cms.web.ShopFrontHelper;
/*    */ import guda.shop.cms.web.SiteUtils;
/*    */ import guda.shop.cms.web.threadvariable.MemberThread;
/*    */ import guda.shop.common.web.springmvc.MessageResolver;
/*    */ import guda.shop.core.entity.Website;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.Cookie;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class MemberCouponAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private MemberCouponMng manage;
/*    */ 
/*    */   @RequestMapping(value={"/myCoupon.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String pay1(HttpServletRequest request, ModelMap model)
/*    */   {
/* 36 */     Website web = SiteUtils.getWeb(request);
/* 37 */     ShopMember member = MemberThread.get();
/* 38 */     List list = this.manage.getList(member.getId());
/* 39 */     model.addAttribute("couList", list);
/* 40 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/* 41 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 42 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myCoupon", new Object[0]));
/*    */   }
/*    */ 
/*    */   public String getHistoryProductIds(HttpServletRequest request) {
/* 46 */     String str = null;
/* 47 */     Cookie[] cookie = request.getCookies();
/* 48 */     int num = cookie.length;
/* 49 */     for (int i = 0; i < num; i++) {
/* 50 */       if (cookie[i].getName().equals("shop_record")) {
/* 51 */         str = cookie[i].getValue();
/* 52 */         break;
/*    */       }
/*    */     }
/* 55 */     return str;
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.MemberCouponAct
 * JD-Core Version:    0.6.2
 */