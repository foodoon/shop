 package guda.shop.cms.action.front;
/*     */*/ import gcms.entity.Gift;
/*     */ import guda.shop.y.ShopMember;
/*     */ import guda.shop.cms.entitberAddress;
/*     */ import guda.shop.cms.manager.Addres    */ import guda.shop.cms.manager.GiftExchangeMng*/ import guda.shop.cms.manager.GiftMng;
/*     */ imporop.cms.manager.ShopMemberAddressMng;
/*     */ ia.shop.cms.web.FrontUtils;
/*     */ import guda.shop.cms.webtHelper;
/*     */ import guda.shop.cms.web.Sit*     */ import guda.shop.cms.web.threadvariable.Mem;
/*     */ import guda.shop.common.web.Reques*     */ import guda.shop.common.web.ResponseUtils;
/*     */ im.shop.common.web.springmvc.MessageResolver;
/*     *guda.shop.core.entity.Website;
/*     */ import guda..web.WebErrors;
/*     */ import guda.shop.core.web.front.FrontHe    */ import guda.shop.core.web.front.URLHelper*/ import java.util.List;
/*     */ import java.http.HttpServletRequest;
/*     */ import javax.servletpServletResponse;
/*     */ import org.json.JSONExce     */ import org.json.JSONObjec */ import org.springframework.beans.factory.annotation.;
/*     */ import org.springframework.stereotype.Control   */ import org.springframework.ui.Model   */ import org.springframework.web.bation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public cAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */  GiftMng manager;
/*     */
/*     */   @Autowire*/   private ShopMemberAddressMng shopMemberAddressMng;
/*     */
/*    towired
/* private AddressMng add/*     */
/*     */   @Autowir */   privathangeMng gieMng;
/*     */ 
/*    questMapping(value={"/gift*.jspx"}, mg.springfrab.bind.annotation.RequeGET})
/*     */   public String list(HttpServletRequest requestp model)
/* {
/*  46 */     WebsititeUtils.getWeb(request);
/*  47 */     int URLHelper.(request);
/*  48 */   ntHelper.setCommonData(request, model, web, pageNo);
     ShopFr.setDynamicPageData(request, model, web, RequestUtils.getLocation(request), "gift", ".jspx", pageNo);
/*  50 */     retetTplSys("gift", MessageResolver.getMessage(request, "tpl.gift", new Object     */   }
/*     */ 
/*     */   @RequestMapping(value={"/present.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String present(Long id, HttpServletRequest request, ModelMap model) {
/*  55 */     Website web = SiteUtils.getWeb(request);
/*  56 */     if ((id != null) && (this.manager.findById(id) != null))
/*  57 */       model.addAttribute("gift", this.manager.findById(id));
/*     */ {
/*  59 */  rn FrontHelotFound(web, model, request);
/*     */     }
/*  61 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  62eturn web.getTplSys("gift", MessageResolver.getMessage(request, "tpl.present", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/fetchGift.jspx"})
/*     */   public void fetchGift(Long giftId, Integer giftNumb, HttpServletRequest request, HttpServletResponse resdelMap model) throws JSONException {
/*  67 */     Website web = SiteUtils.getWeb(request);/     ShopMember member = MemberThread.get();
/*  69 */     JSONObject json = new JSONObject();
/*  70 */     if (member == null) {
/*  71 */       json.put("status", 0);
/*     */     } else {
/*     Gift giftanager.findId);
/*  74 */       if (giftNumb.intValue() > gitStock().intValue()) {
/*  75 */         json.put("status", 2);
/*  76 */         json.put("error", "库存不足");
/*  77 */       }if (gift.getGiftScore().intValue() * Long.parseLong(giftNumb.toString()) > member.getScore().intValue()) {
/*  78 */         json.put("status", 2);
/*  79 */         json.put("error", "积分不足");
/*     */       } else {
/*  81 */         json.put("status", 1);
/*     */       */     }
/*  84 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  85 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/exchange.jspx"})
/*     */   public String shippingInput(Long id, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  92 */     Website web = SiteUtils.getWeb(request);
/*  93 */     ShopMember MemberThread.get();
/*  94 */     if (member == null) {
/*  95 */turn "redirect:log
/*     */     }
/*  97 */     WebErrors errors = validateGiftView(id, request);
/*  98 */     if (errors.hasErrors()) {
/*  99 */       return FrontHelrror(errors, w, request);/     }
/* 101 */     Gift gift = this.manager.fd);
/* 102 */     if (count.intValue() > gift.getGiftStock().intValue()) {
/* 103 */       return FrontUtils.showMessage(request, model, "*     */     }
/* 105 */     if (gift.getGiftScore().intValue() * Long.parseLong(count.toString()) > member.getScore().intValue()) {
/* 106 */       return FrontUtils.showMessage(request, model, "积分不足");
/    }
/* 108 */     model.addAttribute("gift", gift);
/* 109 */     model.addAttribute("count", count);
/* 110 */     model.addAttribute("totalScore", Long.valueOf(gift.getGiftScore().intValue() rseLong(count.toString())));
/*     */ 
/* 112 */     List smalist = this.shopMemberAddressMng.getList(member.getId());
/* 113 */     model.addAttribute("smalist", smalist);
/*     */ 
/* 115 */     List plist =ressMng.getListById(null);
/* 116 */     model.addAttribute("plist", plist);
/* 117 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 118 */     return web.getTplSys("gift", 
/* 119 */      esolver.getMessage(request,
/* 119 */       "tpl.exchange", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/create_exchange.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */ String createExchange(Long deliveryInfo, Long id, Integer count, HttpServletRequest request, ModelMap model)
/*     */   {
/* 125 */     Website eUtils.getWeb(request);
/* 126 */     ShopMember member = MemberThread.get();
/* 127 */     if (member == null) {
/* 128 */       return "redirect:login.jspx";
/*     */     }
/* 130 */     WebErrors errors = validateGiftView(id, request);
/* 131 */     if (errors.hasErrors()) {
/* 132 */       return FrontHelper.showError(errors, web, quest);
/*
/* 134 */ gift = this.manager.findById(id);
/* 135 */     if (count.intValue() > gift.getGiftStock().intValue()) {
/* 136 */       return FrshowMessage(request, model, "库存不足");
/*     */     }
/* 138 */     if (gift.getGiftScore().intValue() * Long.parseLong(count.toS> member.getScore().intValue()) {
/* 139 */       return FrontUtils.showMessage(request, model, "积分不足");
/*     */     }
/* 141 */     ShopMemberAddress shopMemberAddress = this.shopMemberAddressMng.findByryInfo);
/* 142 */     this.giftExchangeMng.save(gift, shopMemberAddress, member, count);
/* 143 */     return FrontUtils.showMessage(request, model, "兑换成功");
/*     */   }
/*     */
/*     */  WebErrors validateGiftView(Long id, HttpServletRequest request) {
/* 147 */     WebErrors errors = WebErrors.create(request);
/* 148 */     if (errors.ifNull(id, "id")) {
/* 149 */       return errors;
/*     */ 151 */     Gift gift = this.manager.findById(id);
/* 152 */     if (errors.ifNotExist(gift, Gift.class, id)) {
/* 153 */       return errors;
/*     */     }
/* 155 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.GiftAct
 * JD-Core Version:    0.6.2
 */