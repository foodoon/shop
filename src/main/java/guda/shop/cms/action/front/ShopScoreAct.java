 package guda.shop.cms.action.front;
/*     */*/ import gcms.entity.GiftExchange;
/*     */ import guda.shop.y.ShopMember;
/*     */ import guda.shop.cms.managchangeMng;
/*     */ import guda.shop.cms.web.ShopFrontH     */ import guda.shop.cms.web.SiteUtils;
/*     *guda.shop.cms.web.threadvariable.MemberThread;/ import guda.shop.common.web.springmvc.MessageResolver;
/*      guda.shop.core.entity.Website;
/*     */ import guda.shop.core.wors;
/*     */ import guda.shop.core.web.front.Fr;
/*     */ import guda.shop.core.web.front.UR/*     */ import javax.servlet.http.Cookie;
/*     */ iax.servlet.http.HttpServletRequest;
/*     */ import gframework.beans.factory.annotation.Autowire */ import org.springframework.stereotype.Controller;
/*mport org.springframework.ui.ModelMap;
/*     */ import org.springframewond.annotation.RequestMapping;
/*     */ 
/*     */ @Controll */ public class ShopScoreAct
/*     */ {
/*     *c static final String MEMBER_SCORE = "tpl.myscore";
/*     */ 
/*     */ red
/*     ate GiftExchangeMng gieMng;
/*     */
/*     */   @Reques{"/shopScore.jspx"})
/*     */   public String getMyScore(Integer status, Integers, String s String endTime, HttpSeest request, ModelMap model)
/*     */   {
/*  40 */ te web = SietWeb(request);
/*  41 */     ShopMember member = MemberTh);
/*     */ 
/*  43 */     if (member == null) {
/*  44 */       return "redirect:../login.jspx";
/*     */     }
/*  46 */     Integer pageNo = InteOf(URLHelper.getPageNo(request));
/*  47 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/*   model.addAttribute("status", status);
/*  49 */     model.addAttribute("useStatus", useStatus)*/     model.addAttribute("startTime", startTime);
     model.addAttribute("endTime", endTime);
/*  52 */     model.addAttribute("historyProductIds", "");
/*  53 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  54 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "myscore", ".jspx", pageNo.intValue());
/*  55 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.myscore", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopScore/exchange.jspx"})
/*     */   public String exchange(HttpServletRequest request, ModelMap model)
/*     */   {
/*  63 */     Website web = SiteUtils.getWeb(request);
/*  64 */     ShopMember member = MemberThread.get();
/*     */ 
/    if (member {
/*  67 *eturn "redirect:../login.jspx";
/*     */     }
/*  69 */ .addAttribute("list", this.giftExchangeMng.getlist(member.getId()));
/*  70 */ rontHelper.setCommonData(request, model, web, 1);
/*  71 */     return web.getTplSys("member", MessageResolver.getMessage(rtpl.exchange", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopScore_accomplish.jspx"})
/*     */   public String exchangeaccomplish(Long id, HttpServletRequest request, ModelMap model)
/*     */   {
/*  79 */     Website web = SiteUtils.getWeb(request);
/*  80 */     ShopMember member = MemberThread.get();
/*     */ 
/*  82 */     if (member == null) */       returct:../login*     */     }
/*  85 */     WebErrors errors = validateGiftExchangeVequest);
/*  86 */     if (errors.hasErrors()) {
/*  87 */       return FrontHelper.showError(erromodel, request);
/*     */     }
/*  89 */     GiftExchange giftExchange = this.giftExchangeMng.findById(id);
/*  90 */    tExchange.getMember().equals(member)) && (giftExchange.getStatus().intValue() == 2)) {
/*  91 */ftExchange.setStatus(Integer.valueOf(3));
/*  92 */       this.giftExchangeMng.update(giftExchange);
/*     */     }
/*  94 */     return exchange(request, model);
/*     */   }
/*     */ 
/*     */   pung getHistoryProductIds(HttpServletRequest request)
/*     */   {
/*  99 */     String str = null;
/* 100 */     Cookie[] cookie = request.getCookies();
/* 101 */     int num = cookie.length;
/* 102 */     for (int i = 0; i < num; i++) {
/* 103 */       if (cookie[i].getName().equals("shop_record")) {
/* 104 */         kie[i].getValue();
/* 105 */         break;
/*     */       }
/    }
/* 108 *urn str;
/* }
/*     */ 
/*     */   private WebErrors validateGiftExchangeView(Long irvletRequest request) {
/* 112 */     WebErrors errors = WebErrors.create(request);
/* 113 */     if (errors.ifNull(id, "id")) {
/* 114 */       return errors;
/*     */     }
/* 116 */     GiftExchange giftExchange = this.giftExchangeMng.findById(id);
/* 117 */     if (errors.ifNotExist(giftExchange, GiftExchange.class,* 118 */       rets;
/*     */     }
/* 120 */     return er    */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.ShopScoreAct
 * JD-Core Version:    0.6.2
 */