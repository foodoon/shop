/*     */ package guda.shop.action.front;
/*     */ 
/*     */ import guda.shop.cms.entity.GiftExchange;
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.manager.GiftExchangeMng;
/*     */ import guda.shop.cms.web.ShopFrontHelper;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.cms.web.threadvariable.MemberThread;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import guda.shop.core.web.front.FrontHelper;
/*     */ import guda.shop.core.web.front.URLHelper;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class ShopScoreAct
/*     */ {
/*     */   public static final String MEMBER_SCORE = "tpl.myscore";
/*     */ 
/*     */   @Autowired
/*     */   private GiftExchangeMng giftExchangeMng;
/*     */ 
/*     */   @RequestMapping({"/shopScore/myscore*.jspx"})
/*     */   public String getMyScore(Integer status, Integer useStatus, String startTime, String endTime, HttpServletRequest request, ModelMap model)
/*     */   {
/*  40 */     Website web = SiteUtils.getWeb(request);
/*  41 */     ShopMember member = MemberThread.get();
/*     */ 
/*  43 */     if (member == null) {
/*  44 */       return "redirect:../login.jspx";
/*     */     }
/*  46 */     Integer pageNo = Integer.valueOf(URLHelper.getPageNo(request));
/*  47 */     model.addAttribute("historyProductIds", getHistoryProductIds(request));
/*  48 */     model.addAttribute("status", status);
/*  49 */     model.addAttribute("useStatus", useStatus);
/*  50 */     model.addAttribute("startTime", startTime);
/*  51 */     model.addAttribute("endTime", endTime);
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
/*  66 */     if (member == null) {
/*  67 */       return "redirect:../login.jspx";
/*     */     }
/*  69 */     model.addAttribute("list", this.giftExchangeMng.getlist(member.getId()));
/*  70 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  71 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.exchange", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopScore/exchange_accomplish.jspx"})
/*     */   public String exchangeaccomplish(Long id, HttpServletRequest request, ModelMap model)
/*     */   {
/*  79 */     Website web = SiteUtils.getWeb(request);
/*  80 */     ShopMember member = MemberThread.get();
/*     */ 
/*  82 */     if (member == null) {
/*  83 */       return "redirect:../login.jspx";
/*     */     }
/*  85 */     WebErrors errors = validateGiftExchangeView(id, request);
/*  86 */     if (errors.hasErrors()) {
/*  87 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/*  89 */     GiftExchange giftExchange = this.giftExchangeMng.findById(id);
/*  90 */     if ((giftExchange.getMember().equals(member)) && (giftExchange.getStatus().intValue() == 2)) {
/*  91 */       giftExchange.setStatus(Integer.valueOf(3));
/*  92 */       this.giftExchangeMng.update(giftExchange);
/*     */     }
/*  94 */     return exchange(request, model);
/*     */   }
/*     */ 
/*     */   public String getHistoryProductIds(HttpServletRequest request)
/*     */   {
/*  99 */     String str = null;
/* 100 */     Cookie[] cookie = request.getCookies();
/* 101 */     int num = cookie.length;
/* 102 */     for (int i = 0; i < num; i++) {
/* 103 */       if (cookie[i].getName().equals("shop_record")) {
/* 104 */         str = cookie[i].getValue();
/* 105 */         break;
/*     */       }
/*     */     }
/* 108 */     return str;
/*     */   }
/*     */ 
/*     */   private WebErrors validateGiftExchangeView(Long id, HttpServletRequest request) {
/* 112 */     WebErrors errors = WebErrors.create(request);
/* 113 */     if (errors.ifNull(id, "id")) {
/* 114 */       return errors;
/*     */     }
/* 116 */     GiftExchange giftExchange = this.giftExchangeMng.findById(id);
/* 117 */     if (errors.ifNotExist(giftExchange, GiftExchange.class, id)) {
/* 118 */       return errors;
/*     */     }
/* 120 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.ShopScoreAct
 * JD-Core Version:    0.6.2
 */