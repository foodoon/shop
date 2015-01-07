/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.ShopScore;
/*     */ import guda.shop.cms.manager.ShopScoreMng;
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
/*     */ public class ShopScoreAct
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(ShopScoreAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ShopScoreMng manager;
/*     */ 
/*  32 */   @RequestMapping({"/shopScore/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(null, Boolean.valueOf(false), Boolean.valueOf(false), null, null, Integer.valueOf(SimplePage.cpn(pageNo)), 
/*  33 */       Integer.valueOf(CookieUtils.getPageSize(request)));
/*  34 */     model.addAttribute("pagination", pagination);
/*  35 */     return "shopScore/list"; }
/*     */ 
/*     */   @RequestMapping({"/shopScore/v_add.do"})
/*     */   public String add(ModelMap model)
/*     */   {
/*  40 */     return "shopScore/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopScore/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  45 */     WebErrors errors = validateEdit(id, request);
/*  46 */     if (errors.hasErrors()) {
/*  47 */       return errors.showErrorPage(model);
/*     */     }
/*  49 */     model.addAttribute("shopScore", this.manager.findById(id));
/*  50 */     return "shopScore/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopScore/o_save.do"})
/*     */   public String save(ShopScore bean, HttpServletRequest request, ModelMap model) {
/*  55 */     WebErrors errors = validateSave(bean, request);
/*  56 */     if (errors.hasErrors()) {
/*  57 */       return errors.showErrorPage(model);
/*     */     }
/*  59 */     bean = this.manager.save(bean);
/*  60 */     log.info("save ShopScore id={}", bean.getId());
/*  61 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopScore/o_update.do"})
/*     */   public String update(ShopScore bean, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  67 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  68 */     if (errors.hasErrors()) {
/*  69 */       return errors.showErrorPage(model);
/*     */     }
/*  71 */     bean = this.manager.update(bean);
/*  72 */     log.info("update ShopScore id={}.", bean.getId());
/*  73 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopScore/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  79 */     WebErrors errors = validateDelete(ids, request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     ShopScore[] beans = this.manager.deleteByIds(ids);
/*  84 */     for (ShopScore bean : beans) {
/*  85 */       log.info("delete ShopScore id={}", bean.getId());
/*     */     }
/*  87 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(ShopScore bean, HttpServletRequest request) {
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
/* 129 */     ShopScore entity = this.manager.findById(id);
/* 130 */     if (errors.ifNotExist(entity, ShopScore.class, id)) {
/* 131 */       return true;
/*     */     }
/*     */ 
/* 137 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopScoreAct
 * JD-Core Version:    0.6.2
 */