/*    */ package guda.shop.action.front;
/*    */ 
/*    */ import guda.shop.cms.entity.ShopMember;
/*    */ import guda.shop.cms.web.ShopFrontHelper;
/*    */ import guda.shop.cms.web.SiteUtils;
/*    */ import guda.shop.cms.web.threadvariable.MemberThread;
/*    */ import guda.shop.common.web.springmvc.MessageResolver;
/*    */ import guda.shop.core.entity.Website;
/*    */ import guda.shop.core.web.front.URLHelper;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class ShopMoneyAct
/*    */ {
/*    */   public static final String MEMBER_MONEY = "tpl.mymoney";
/*    */ 
/*    */   @RequestMapping({"/shopMoney/mymoney*.jspx"})
/*    */   public String getMyScore(Integer status, Integer useStatus, String startTime, String endTime, HttpServletRequest request, ModelMap model)
/*    */   {
/* 33 */     Website web = SiteUtils.getWeb(request);
/* 34 */     ShopMember member = MemberThread.get();
/*    */ 
/* 36 */     if (member == null) {
/* 37 */       return "redirect:../login.jspx";
/*    */     }
/* 39 */     Integer pageNo = Integer.valueOf(URLHelper.getPageNo(request));
/* 40 */     model.addAttribute("status", status);
/* 41 */     model.addAttribute("startTime", startTime);
/* 42 */     model.addAttribute("endTime", endTime);
/* 43 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 44 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "mymoney", ".jspx", pageNo.intValue());
/* 45 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.mymoney", new Object[0]));
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.ShopMoneyAct
 * JD-Core Version:    0.6.2
 */