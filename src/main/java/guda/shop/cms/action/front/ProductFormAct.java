 package guda.shop.cms.action.front;
/*     */*/ import gcms.entity.Consult;
/*     */ import guda.shop.y.Discuss;
/*     */ import guda.shop.cms.entitem;
/*     */ import guda.shop.cms.entity.Product*/ import guda.shop.cms.entity.ShopMember;
/*  ort guda.shop.cms.manager.ConsultMng;
/*     */ im.shop.cms.manager.DiscussMng;
/*     */ import guda.manager.OrderItemMng;
/*     */ import guda.shop.cr.ProductMng;
/*     */ import guda.shop.cms.web.Shoper;
/*     */ import guda.shop.cms.web.SiteUtils;
/import guda.shop.cms.web.threadvariable.MemberThread*/ import guda.shop.common.page.Pagination;
/*mport guda.shop.common.page.SimplePage;
/*     */ import guda.sh.web.ResponseUtils;
/*     */ import guda.shop.commringmvc.MessageResolver;
/*     */ import guda.shopity.Website;
/*     */ import guda.shop.core.web.fronlper;
/*     */ import javax.servlet.http.Cookie;
/*     */ imporervlet.http.HttpServletRequest;
/*     */ importrvlet.http.HttpServletResponse;
/*     */ import org.apons.lang.StringUtils;
/*     */ import org.sework.beans.factory.annotation.Autowired;
/*     */ imporingframework.stereotype.Controller;
/*     */ import orgamework.ui.ModelMap;
/*     */ import org.springframewind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */lass ProductFormAct
/*     */ {
/*     */ 
/*     */   @Auto    */   private ProductMng productMng;
/*     */ /   @Autowired
/*     */   private ConsultMng consultMng;
/*     */
/*  Autowired
/  private OrderItemMngmMng;
/*     */ 
/*     */   @Autowire*/   privateng discussM  */
/*     */   @Requg({"/searchDiscussPage*.jspx"})
/*     */  tring searcage(Long productId, IntNo, HttpServletResponse response, HttpServl request, Model)
/*     */   {
/*   Website web = SiteUtils.getWeb(request);
/*   if ((produull) || (this.productMnd(productId) == null)) {
/*  49 */       retHelper.pag(web, model, request);
/*     */     }
     Pagine = this.discussMng.getPage(productId, null, null, null, null, SimplePage.cpn(pageNo), 10, true);
/*  52 */     Product bean = this.productMng.findByIId);
/*  53 */     model.addAttribute("product", bean);
/*  54 */     model.addAttribute("pagination", page);
/*  55 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  56 */     ShopFrontHelper.setDynamicPageData(rodel, web, "", "searchDiscussPage", ".jspx", SimplePage.cpn(pageNo));
/*  57 */     return web.getTemplate("shop", MessageResolver.getMessage(request, "tpl.discussContentPage", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/haveDiscuss.jspx"})
/*     */   public String haveDiscuss(Long productId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  66 */     Website web = SiteUtils.getWeb(request);
/*  67 */     ShopMember member = MemberThread.get();
/*  68 */     if (member == null) {
/*  69 */       ResponseUtils.renderJson(response, "denru");
/*  70 */       rl;
/*     */  72 */     ictId == null) || (this.productMng.findById(productIl)) {
/*  73 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/*  75 */     OrderItem bean = this.Mng.findByMember(member.getId(), productId);
/*  76 */     if (bean != null) {
/*  77 */       ResponseUtils.renderJson(response, "success");
/*  78 */       return null;
/*     */     }
/*  80 */     ResponseUtils.renderJson(response, "false");
/*   return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/consultProduct*.jspx"})
/*     */   public String consultProduct(Long productId, Integer pageNo, HttpServse response, HttpServletRequest request, ModelMap model)
/*     */   {
/*  92 */     Website web = SiteUtils.getWeb(request);
/*  93 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/*  94 */       ontHelper.pageNotFound(web, model, request);
/*     */     }
/*  96 */     ShopFrontHelper.setCommonDat, model, web,ge.cpn(page 97 */     Product bean = this.productMng.findById(prod*  98 */     Pagination page = this.consultMng.getPage(productId, null, null, null, null, SimplePage.cpn(pageNo), 5, Boolean.valueOf(true));
/*  99odel.addAttribute("product", bean);
/* 100 */     model.addAttribute("pagination", page);
/* 101 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "consultProduct", ".jspx", SimplePage.cpn(pageNo));
/* 102 */    eb.getTemplate("shop", MessageResolver.getMessage(request, "tpl.consultProduct", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/bargain*.jspx"})
/*     */   public String bargain(Long productId, Integer pageNo, HttpServletResponse response, HttpServletRequest request, ModelMap model)
/*     */   {
/* 111 */     Website web = SiteUtils.getWeb(request);
/* 112 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/* 113 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/* 115 */     ShopFrontHelper.setCommonData(request, model, web, SimplePage.cpn(pageNo));
/* 116 */     Produ this.productMId(productI7 */     Pagination page = this.orderItemMng.get(Integer.valueOf(SimplePage.cpn(pageNo)), Integer.valueOf(4), productId);
/* 118 */     model.addAttribute("pagination", page);
/* 119 */   ddAttribute("product", bean);
/* 120 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "bargain", ".jspx", SimplePage.cpn(pageNo));
/* 121 */     return web.getTemplate("shop", MessageResolver.getMessage(request,gain", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/insertConsult.jspx"})
/*     */   public String insertConsult(Long productId, String content, HttpServletResponse response, HttpServletRequest request, ModelMap model)
/*     */   {
/* 132 */     Website web = SiteUtils.getWeb(request);
/* 133 */     ShopMember member = MemberThread.get();
/* 134 */     if (member == null) {
/* 135 */       ResponseUtils.renderJson(response, "false");
/* 136 */       return null;
/*     */     }
/* 138 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/* 139 */       return FrontHelper.pageNot, model, reque   */     }/     Consult bean = this.consultMng.saveOrUpdate(proontent, member.getId());
/* 142 */     if (bean == null) {
/* 143 */       ResponseUtils.renderJson(response, "sameUsually");
/* 144 */       retu/*     */     }
/* 146 */     ResponseUtils.renderJson(response, "success");
/* 147 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/insertDiscuss.jspx"})
/*     */   public String insertDiscuss(Long productId, String disCervletResponse response, HttpServletRequest request, ModelMap model)
/*     */   {
/* 156 */     Website web = SiteUtils.getWeb(request);
/* 157 */     ShopMember member = Membget();
/* 158 */     if (member == null) {
/* 159 */       ResponseUtils.renderJson(response, "false");
/* 160 */       return null;
/*     */     }
/* 162 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/*     return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/* 165 */     Discuss bean = ussMng.saveOrUductId, diser.getId());
/* 166 */     if (bean == null) {
/* 167 ResponseUtils.renderJson(response, "sameUsually");
/* 168 */       return null;
/*     */     }
/* 170 */     ResponseUtils.renderJson(response,");
/* 171 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/historyRecord.jspx"})
/*     */   public String historyRecord(Long productId, HttpServletResponse response, HttpServletRequest request, ModelMap model)
/*     */ 9 */     Website web = SiteUtils.getWeb(request);
/* 180 */     ShopMember member = MemberThread.get();
/* 181 */     if (member == null) {
/* 182 */       ResponseUtils.renderonse, "false");
/* 183 */       return null;
/*     */     }
/* 185 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/* 186 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/*   String str = "";
/* 189 */     Cookie[] cookeis = request.getCookies();
/* 190 */     int num = cookei
/* 191 */     i = 0; i <) {
/* 192 */       if (cookeis[i].getName().equals("rd")) {
/* 193 */         str = ',' + cookeis[i].getValue();
/* 194 */         break;
/*     */       }
/*     */     }
/* 197 */  productId + str;
/* 198 */     int n = 0; int m = 0;
/* 199 */     int j = str.length();
/* 200 */     for (int i = 0; i < j; i++) {
/* 201 */       if (str.charAt(i) == ',') {
/* 202 */         n++;
/*     */       }
/* 204 */       if (n == 6) {
         break;
/*     */       }
/* 207 */       m = i + 1;
/*     */     }
/* 209 */     Cookie cook = new Cookie("shop_record", str.substring(0, m));
/* 210 */     String paest.getContextPath();
/* 211 */     cook.setPath(StringUtils.isBlank(path) ? "/" : path);
/* 212 */     response.addCookie(cook);
/* 213 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.ProductFormAct
 * JD-Core Version:    0.6.2
 */