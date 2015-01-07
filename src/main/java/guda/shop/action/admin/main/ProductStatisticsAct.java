/*    */ package guda.shop.action.admin.main;
/*    */ 
/*    */ import guda.shop.cms.entity.Product;
/*    */ import guda.shop.cms.manager.ProductMng;
/*    */ import guda.shop.cms.web.SiteUtils;
/*    */ import guda.shop.common.page.Pagination;
/*    */ import guda.shop.common.page.SimplePage;
/*    */ import guda.shop.common.web.CookieUtils;
/*    */ import guda.shop.core.entity.Global;
/*    */ import guda.shop.core.entity.Website;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class ProductStatisticsAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private ProductMng productMng;
/*    */ 
/*    */   @RequestMapping({"/productStatistics/v_productLack.do"})
/*    */   public String productLack(Integer count, Boolean status, Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 37 */     Website web = SiteUtils.getWeb(request);
/* 38 */     Global global = web.getGlobal();
/* 39 */     if (count == null) {
/* 40 */       count = global.getStockWarning();
/*    */     }
/* 42 */     Pagination pagination = this.productMng.getPageByStockWarning(web.getId(), count, status, SimplePage.cpn(pageNo), 
/* 43 */       CookieUtils.getPageSize(request));
/* 44 */     model.addAttribute("pagination", pagination);
/* 45 */     model.addAttribute("count", count);
/* 46 */     model.addAttribute("status", status);
/* 47 */     return "sale/productLack";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/productStatistics/o_updateRemind.do"})
/*    */   public String updateRemind(Boolean status, Integer count, Integer pageNo, Long productId, HttpServletRequest request, ModelMap model)
/*    */   {
/* 53 */     Product product = this.productMng.findById(productId);
/* 54 */     product.setLackRemind(status);
/* 55 */     this.productMng.updateByUpdater(product);
/* 56 */     if (status.booleanValue())
/* 57 */       status = Boolean.valueOf(false);
/*    */     else {
/* 59 */       status = Boolean.valueOf(true);
/*    */     }
/* 61 */     model.addAttribute("status", status);
/* 62 */     model.addAttribute("count", count);
/* 63 */     return "redirect:v_productLack.do";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/productStatistics/o_resetSaleTop.do"})
/*    */   public String resetSaleTop(Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 68 */     this.productMng.resetSaleTop();
/* 69 */     return productSaleTop(pageNo, request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/productStatistics/v_productSaleTop.do"})
/*    */   public String productSaleTop(Integer pageNo, HttpServletRequest request, ModelMap model)
/*    */   {
/* 75 */     Pagination pagination = this.productMng.getPage(4, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/* 76 */     model.addAttribute("pagination", pagination);
/* 77 */     model.addAttribute("pageNo", pageNo);
/* 78 */     return "sale/productSaleTop";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/productStatistics/o_resetProfitTop.do"})
/*    */   public String resetProfitTop(Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 83 */     this.productMng.resetProfitTop();
/* 84 */     return productProfitTop(pageNo, request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/productStatistics/v_productProfitTop.do"})
/*    */   public String productProfitTop(Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 89 */     Pagination pagination = this.productMng.getPage(8, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/* 90 */     model.addAttribute("pagination", pagination);
/* 91 */     model.addAttribute("pageNo", pageNo);
/* 92 */     return "sale/productProfitTop";
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ProductStatisticsAct
 * JD-Core Version:    0.6.2
 */