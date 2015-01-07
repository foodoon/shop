/*     */ package guda.shop.cms.action.front;
/*     */ 
/*     */ import guda.shop.cms.entity.Consult;
/*     */ import guda.shop.cms.entity.Discuss;
/*     */ import guda.shop.cms.entity.OrderItem;
/*     */ import guda.shop.cms.entity.Product;
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.manager.ConsultMng;
/*     */ import guda.shop.cms.manager.DiscussMng;
/*     */ import guda.shop.cms.manager.OrderItemMng;
/*     */ import guda.shop.cms.manager.ProductMng;
/*     */ import guda.shop.cms.web.ShopFrontHelper;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.cms.web.threadvariable.MemberThread;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.common.page.SimplePage;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.front.FrontHelper;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class ProductFormAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ProductMng productMng;
/*     */ 
/*     */   @Autowired
/*     */   private ConsultMng consultMng;
/*     */ 
/*     */   @Autowired
/*     */   private OrderItemMng orderItemMng;
/*     */ 
/*     */   @Autowired
/*     */   private DiscussMng discussMng;
/*     */ 
/*     */   @RequestMapping({"/searchDiscussPage*.jspx"})
/*     */   public String searchDiscussPage(Long productId, Integer pageNo, HttpServletResponse response, HttpServletRequest request, ModelMap model)
/*     */   {
/*  47 */     Website web = SiteUtils.getWeb(request);
/*  48 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/*  49 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/*  51 */     Pagination page = this.discussMng.getPage(productId, null, null, null, null, SimplePage.cpn(pageNo), 10, true);
/*  52 */     Product bean = this.productMng.findById(productId);
/*  53 */     model.addAttribute("product", bean);
/*  54 */     model.addAttribute("pagination", page);
/*  55 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  56 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "searchDiscussPage", ".jspx", SimplePage.cpn(pageNo));
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
/*  70 */       return null;
/*     */     }
/*  72 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/*  73 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/*  75 */     OrderItem bean = this.orderItemMng.findByMember(member.getId(), productId);
/*  76 */     if (bean != null) {
/*  77 */       ResponseUtils.renderJson(response, "success");
/*  78 */       return null;
/*     */     }
/*  80 */     ResponseUtils.renderJson(response, "false");
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/consultProduct*.jspx"})
/*     */   public String consultProduct(Long productId, Integer pageNo, HttpServletResponse response, HttpServletRequest request, ModelMap model)
/*     */   {
/*  92 */     Website web = SiteUtils.getWeb(request);
/*  93 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/*  94 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/*  96 */     ShopFrontHelper.setCommonData(request, model, web, SimplePage.cpn(pageNo));
/*  97 */     Product bean = this.productMng.findById(productId);
/*  98 */     Pagination page = this.consultMng.getPage(productId, null, null, null, null, SimplePage.cpn(pageNo), 5, Boolean.valueOf(true));
/*  99 */     model.addAttribute("product", bean);
/* 100 */     model.addAttribute("pagination", page);
/* 101 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "consultProduct", ".jspx", SimplePage.cpn(pageNo));
/* 102 */     return web.getTemplate("shop", MessageResolver.getMessage(request, "tpl.consultProduct", new Object[0]));
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
/* 116 */     Product bean = this.productMng.findById(productId);
/* 117 */     Pagination page = this.orderItemMng.getOrderItem(Integer.valueOf(SimplePage.cpn(pageNo)), Integer.valueOf(4), productId);
/* 118 */     model.addAttribute("pagination", page);
/* 119 */     model.addAttribute("product", bean);
/* 120 */     ShopFrontHelper.setDynamicPageData(request, model, web, "", "bargain", ".jspx", SimplePage.cpn(pageNo));
/* 121 */     return web.getTemplate("shop", MessageResolver.getMessage(request, "tpl.bargain", new Object[0]));
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
/* 139 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/* 141 */     Consult bean = this.consultMng.saveOrUpdate(productId, content, member.getId());
/* 142 */     if (bean == null) {
/* 143 */       ResponseUtils.renderJson(response, "sameUsually");
/* 144 */       return null;
/*     */     }
/* 146 */     ResponseUtils.renderJson(response, "success");
/* 147 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/insertDiscuss.jspx"})
/*     */   public String insertDiscuss(Long productId, String disCon, HttpServletResponse response, HttpServletRequest request, ModelMap model)
/*     */   {
/* 156 */     Website web = SiteUtils.getWeb(request);
/* 157 */     ShopMember member = MemberThread.get();
/* 158 */     if (member == null) {
/* 159 */       ResponseUtils.renderJson(response, "false");
/* 160 */       return null;
/*     */     }
/* 162 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/* 163 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/* 165 */     Discuss bean = this.discussMng.saveOrUpdate(productId, disCon, member.getId());
/* 166 */     if (bean == null) {
/* 167 */       ResponseUtils.renderJson(response, "sameUsually");
/* 168 */       return null;
/*     */     }
/* 170 */     ResponseUtils.renderJson(response, "success");
/* 171 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/historyRecord.jspx"})
/*     */   public String historyRecord(Long productId, HttpServletResponse response, HttpServletRequest request, ModelMap model)
/*     */   {
/* 179 */     Website web = SiteUtils.getWeb(request);
/* 180 */     ShopMember member = MemberThread.get();
/* 181 */     if (member == null) {
/* 182 */       ResponseUtils.renderJson(response, "false");
/* 183 */       return null;
/*     */     }
/* 185 */     if ((productId == null) || (this.productMng.findById(productId) == null)) {
/* 186 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/* 188 */     String str = "";
/* 189 */     Cookie[] cookeis = request.getCookies();
/* 190 */     int num = cookeis.length;
/* 191 */     for (int i = 0; i < num; i++) {
/* 192 */       if (cookeis[i].getName().equals("shop_record")) {
/* 193 */         str = ',' + cookeis[i].getValue();
/* 194 */         break;
/*     */       }
/*     */     }
/* 197 */     str = productId + str;
/* 198 */     int n = 0; int m = 0;
/* 199 */     int j = str.length();
/* 200 */     for (int i = 0; i < j; i++) {
/* 201 */       if (str.charAt(i) == ',') {
/* 202 */         n++;
/*     */       }
/* 204 */       if (n == 6) {
/*     */         break;
/*     */       }
/* 207 */       m = i + 1;
/*     */     }
/* 209 */     Cookie cook = new Cookie("shop_record", str.substring(0, m));
/* 210 */     String path = request.getContextPath();
/* 211 */     cook.setPath(StringUtils.isBlank(path) ? "/" : path);
/* 212 */     response.addCookie(cook);
/* 213 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.ProductFormAct
 * JD-Core Version:    0.6.2
 */