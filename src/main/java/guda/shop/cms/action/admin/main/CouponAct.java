/*    */ package guda.shop.cms.action.admin.main;
/*    */ 
/*    */ import guda.shop.cms.entity.Coupon;
/*    */ import guda.shop.cms.manager.CouponMng;
/*    */ import guda.shop.cms.web.SiteUtils;
/*    */ import guda.shop.core.entity.Website;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class CouponAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CouponMng manager;
/*    */ 
/*    */   @RequestMapping({"/coupon/v_add.do"})
/*    */   public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 28 */     return "coupon/add";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/coupon/o_save.do"})
/*    */   public String save(Coupon bean, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 34 */     Website site = SiteUtils.getWeb(request);
/* 35 */     this.manager.save(bean, site.getId());
/* 36 */     return list(request, response, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/coupon/v_list.do"})
/*    */   public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 42 */     List cList = this.manager.getList();
/* 43 */     model.addAttribute("list", cList);
/* 44 */     return "coupon/list";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/coupon/v_edit.do"})
/*    */   public String edit(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 50 */     model.addAttribute("coupon", this.manager.findById(id));
/* 51 */     return "coupon/edit";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/coupon/o_update.do"})
/*    */   public String update(Coupon bean, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 57 */     this.manager.update(bean);
/* 58 */     return list(request, response, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/coupon/o_delete.do"})
/*    */   public String delete(Long[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 64 */     Website site = SiteUtils.getWeb(request);
/* 65 */     String url = site.getUploadUrl();
/* 66 */     this.manager.deleteByIds(ids, url);
/* 67 */     return list(request, response, model);
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.CouponAct
 * JD-Core Version:    0.6.2
 */