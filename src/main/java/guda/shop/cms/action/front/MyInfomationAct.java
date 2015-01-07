 package guda.shop.cms.action.front;
/*     */*/ import gcms.entity.ShopMember;
/*     */ import guda.shop.er.ConsultMng;
/*     */ import guda.shop.cms.managsMng;
/*     */ import guda.shop.cms.manager.OrderI*     */ import guda.shop.cms.web.ShopFrontHelper;
/*mport guda.shop.cms.web.SiteUtils;
/*     */ import .cms.web.threadvariable.MemberThread;
/*     *guda.shop.common.page.Pagination;
/*     */ import guda.shop.comSimplePage;
/*     */ import guda.shop.common.securation.Secured;
/*     */ import guda.shop.common.wevc.MessageResolver;
/*     */ import guda.shop.core.entity.Webs   */ import javax.servlet.http.Cookie;
/*     */ import javax.sep.HttpServletRequest;
/*     */ import org.sprink.beans.factory.annotation.Autowired;
/*    t org.springframework.stereotype.Controller;
/*     */ i.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bition.RequestMapping;
/*     */
/*     */ @Secured
/*     */ler
/*     */ public class MyInfomationAct
/*        */
/*     */   @Autowired
/*     */   private OrderItemMng orderItemM  */
/*   utowired
/*     */  ConsultMng consultMng*/
/*     */   @Autowired
/*     */   iscussMng di
/*     */ /   @RequestMapping({"/s*.jspx"})
/*     */   public String getMyDiscur pageNo, HtRequest request, Model)
/*     */   {
/*  40 */     Website web =s.getWeb(re*  41 */     ShopMember MemberThread.get();
/*  42 */     if (membl) {
/*  43 return "redirect:../login.jspx";
/*     */     }
/    Pagination pagination = this.discussMng.getPageByMember(member.getId(), SimplePage.cpn(pageNo),);
/*  46 */     model.addAttribute("pagination", pagination);
/*  47 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/*  48 */     ShopFrontHelper.setCommonData(request, model,
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
/*  62 */       rdirect:../logi/*     */  64 */     Pagination pagination = this.consultMng.gember(member.getId(), SimplePage.cpn(pageNo), 1, true);
/*  65 */     model.addAttribute("paginatioation);
/*  66 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/*  67 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  68 */     ShopFrontHelper.setDynamicPageDst, model, web, "", "my_cousult", ".jspx", SimplePage.cpn(pageNo));
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
/*  83 gination paginhis.orderItPageByMember(Integer.valueOf(4), member.getId(), S.cpn(pageNo), 2);
/*  84 */     model.addAttribute("pagination", pagination);
/*  85 */     model.ate("historyProductIds", getHistoryProductIds(request));
/*  86 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  87 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "buyRecorx", SimplePage.cpn(pageNo));
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
/* 101 */     r;
/*     */   */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.MyInfomationAct
 * JD-Core Version:    0.6.2
 */