/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.ShopMoney;
/*     */ import guda.shop.cms.manager.ShopMoneyMng;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.common.page.SimplePage;
/*     */ import guda.shop.common.web.CookieUtils;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.SiteUtils;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class ShopMoneyAct
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(ShopMoneyAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ShopMoneyMng manager;
/*     */ 
/*  32 */   @RequestMapping({"/shopMoney/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo), 
/*  33 */       CookieUtils.getPageSize(request));
/*  34 */     model.addAttribute("pagination", pagination);
/*  35 */     return "shopMoney/list"; }
/*     */ 
/*     */   @RequestMapping({"/shopMoney/v_add.do"})
/*     */   public String add(ModelMap model)
/*     */   {
/*  40 */     return "shopMoney/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopMoney/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  45 */     WebErrors errors = validateEdit(id, request);
/*  46 */     if (errors.hasErrors()) {
/*  47 */       return errors.showErrorPage(model);
/*     */     }
/*  49 */     model.addAttribute("shopMoney", this.manager.findById(id));
/*  50 */     return "shopMoney/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopMoney/o_save.do"})
/*     */   public String save(ShopMoney bean, HttpServletRequest request, ModelMap model) {
/*  55 */     WebErrors errors = validateSave(bean, request);
/*  56 */     if (errors.hasErrors()) {
/*  57 */       return errors.showErrorPage(model);
/*     */     }
/*  59 */     bean = this.manager.save(bean);
/*  60 */     log.info("save ShopMoney id={}", bean.getId());
/*  61 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopMoney/o_update.do"})
/*     */   public String update(ShopMoney bean, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  67 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  68 */     if (errors.hasErrors()) {
/*  69 */       return errors.showErrorPage(model);
/*     */     }
/*  71 */     bean = this.manager.update(bean);
/*  72 */     log.info("update ShopMoney id={}.", bean.getId());
/*  73 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopMoney/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  79 */     WebErrors errors = validateDelete(ids, request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     ShopMoney[] beans = this.manager.deleteByIds(ids);
/*  84 */     for (ShopMoney bean : beans) {
/*  85 */       log.info("delete ShopMoney id={}", bean.getId());
/*     */     }
/*  87 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(ShopMoney bean, HttpServletRequest request) {
/*  91 */     WebErrors errors = WebErrors.create(request);
/*     */ 
/*  94 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/*  98 */     WebErrors errors = WebErrors.create(request);
/*  99 */     Website web = SiteUtils.getWeb(request);
/* 100 */     if (vldExist(id, web.getId(), errors)) {
/* 101 */       return errors;
/*     */     }
/* 103 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 107 */     WebErrors errors = WebErrors.create(request);
/* 108 */     Website web = SiteUtils.getWeb(request);
/* 109 */     if (vldExist(id, web.getId(), errors)) {
/* 110 */       return errors;
/*     */     }
/* 112 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 116 */     WebErrors errors = WebErrors.create(request);
/* 117 */     Website web = SiteUtils.getWeb(request);
/* 118 */     errors.ifEmpty(ids, "ids");
/* 119 */     for (Long id : ids) {
/* 120 */       vldExist(id, web.getId(), errors);
/*     */     }
/* 122 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 126 */     if (errors.ifNull(id, "id")) {
/* 127 */       return true;
/*     */     }
/* 129 */     ShopMoney entity = this.manager.findById(id);
/* 130 */     if (errors.ifNotExist(entity, ShopMoney.class, id)) {
/* 131 */       return true;
/*     */     }
/*     */ 
/* 137 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopMoneyAct
 * JD-Core Version:    0.6.2
 */