/*     */ package guda.shop.action.front;
/*     */ 
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.manager.ConsultMng;
/*     */ import guda.shop.cms.manager.DiscussMng;
/*     */ import guda.shop.cms.manager.OrderItemMng;
/*     */ import guda.shop.cms.web.ShopFrontHelper;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.cms.web.threadvariable.MemberThread;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.common.page.SimplePage;
/*     */ import guda.shop.common.security.annotation.Secured;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.Website;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Secured
/*     */ @Controller
/*     */ public class MyInfomationAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private OrderItemMng orderItemMng;
/*     */ 
/*     */   @Autowired
/*     */   private ConsultMng consultMng;
/*     */ 
/*     */   @Autowired
/*     */   private DiscussMng discussMng;
/*     */ 
/*     */   @RequestMapping({"/my_discuss*.jspx"})
/*     */   public String getMyDiscuss(Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  40 */     Website web = SiteUtils.getWeb(request);
/*  41 */     ShopMember member = MemberThread.get();
/*  42 */     if (member == null) {
/*  43 */       return "redirect:../login.jspx";
/*     */     }
/*  45 */     Pagination pagination = this.discussMng.getPageByMember(member.getId(), SimplePage.cpn(pageNo), 10, true);
/*  46 */     model.addAttribute("pagination", pagination);
/*  47 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/*  48 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  49 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "my_discuss", ".jspx", SimplePage.cpn(pageNo));
/*  50 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.mydiscuss", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/my_cousult*.jspx"})
/*     */   public String getMyCousult(Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  59 */     Website web = SiteUtils.getWeb(request);
/*  60 */     ShopMember member = MemberThread.get();
/*  61 */     if (member == null) {
/*  62 */       return "redirect:../login.jspx";
/*     */     }
/*  64 */     Pagination pagination = this.consultMng.getPageByMember(member.getId(), SimplePage.cpn(pageNo), 1, true);
/*  65 */     model.addAttribute("pagination", pagination);
/*  66 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/*  67 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  68 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "my_cousult", ".jspx", SimplePage.cpn(pageNo));
/*  69 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myconsult", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/buyRecord*.jspx"})
/*     */   public String getBuyRecord(Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  78 */     Website web = SiteUtils.getWeb(request);
/*  79 */     ShopMember member = MemberThread.get();
/*  80 */     if (member == null) {
/*  81 */       return "redirect:../login.jspx";
/*     */     }
/*  83 */     Pagination pagination = this.orderItemMng.getPageByMember(Integer.valueOf(4), member.getId(), SimplePage.cpn(pageNo), 2);
/*  84 */     model.addAttribute("pagination", pagination);
/*  85 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/*  86 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  87 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "buyRecord", ".jspx", SimplePage.cpn(pageNo));
/*  88 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.buyRecord", new Object[0]));
/*     */   }
/*     */ 
/*     */   public String getHistoryProductIds(HttpServletRequest request) {
/*  92 */     String str = null;
/*  93 */     Cookie[] cookie = request.getCookies();
/*  94 */     int num = cookie.length;
/*  95 */     for (int i = 0; i < num; i++) {
/*  96 */       if (cookie[i].getName().equals("shop_record")) {
/*  97 */         str = cookie[i].getValue();
/*  98 */         break;
/*     */       }
/*     */     }
/* 101 */     return str;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.MyInfomationAct
 * JD-Core Version:    0.6.2
 */