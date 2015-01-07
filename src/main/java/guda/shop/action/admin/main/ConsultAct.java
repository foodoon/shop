/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.Consult;
/*     */ import guda.shop.cms.manager.ConsultMng;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.common.page.SimplePage;
/*     */ import guda.shop.common.web.CookieUtils;
/*     */ import guda.shop.common.web.RequestUtils;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.SiteUtils;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import java.util.Date;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class ConsultAct
/*     */ {
/*  31 */   private static final Logger log = LoggerFactory.getLogger(ConsultAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ConsultMng manager;
/*     */ 
/*  36 */   @RequestMapping({"/consult/v_list.do"})
/*     */   public String list(Date startTime, Date endTime, Integer pageNo, HttpServletRequest request, ModelMap model) { String userName = RequestUtils.getQueryParam(request, "userName");
/*  37 */     userName = StringUtils.trim(userName);
/*  38 */     String productName = RequestUtils.getQueryParam(request, "productName");
/*  39 */     productName = StringUtils.trim(productName);
/*  40 */     Pagination pagination = this.manager.getPage(null, userName, productName, startTime, endTime, 
/*  41 */       SimplePage.cpn(pageNo), CookieUtils.getPageSize(request), Boolean.valueOf(true));
/*     */ 
/*  43 */     if (!StringUtils.isBlank(userName)) {
/*  44 */       model.addAttribute("userName", userName);
/*     */     }
/*  46 */     if (!StringUtils.isBlank(productName)) {
/*  47 */       model.addAttribute("productName", productName);
/*     */     }
/*     */ 
/*  50 */     model.addAttribute("startTime", startTime);
/*  51 */     model.addAttribute("endTime", endTime);
/*     */ 
/*  53 */     model.addAttribute("pagination", pagination);
/*  54 */     model.addAttribute("pageNo", pageNo);
/*  55 */     return "consult/list"; }
/*     */ 
/*     */   @RequestMapping({"/consult/v_add.do"})
/*     */   public String add(ModelMap model)
/*     */   {
/*  60 */     return "consult/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/consult/v_edit.do"})
/*     */   public String edit(Long id, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  66 */     WebErrors errors = validateEdit(id, request);
/*  67 */     if (errors.hasErrors()) {
/*  68 */       return errors.showErrorPage(model);
/*     */     }
/*  70 */     model.addAttribute("consult", this.manager.findById(id));
/*  71 */     model.addAttribute("pageNo", pageNo);
/*  72 */     return "consult/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/consult/o_save.do"})
/*     */   public String save(Consult bean, HttpServletRequest request, ModelMap model) {
/*  77 */     WebErrors errors = validateSave(bean, request);
/*  78 */     if (errors.hasErrors()) {
/*  79 */       return errors.showErrorPage(model);
/*     */     }
/*     */ 
/*  82 */     log.info("save Consult id={}", bean.getId());
/*  83 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/consult/o_update.do"})
/*     */   public String update(Long id, String adminReplay, HttpServletRequest request, ModelMap model, Integer pageNo)
/*     */   {
/*  89 */     Consult bean = this.manager.findById(id);
/*  90 */     bean.setAdminReplay(adminReplay);
/*  91 */     this.manager.update(bean);
/*  92 */     model.addAttribute("pageNo", pageNo);
/*  93 */     log.info("update Consult id={}.", bean.getId());
/*  94 */     return list(null, null, pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/consult/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 100 */     WebErrors errors = validateDelete(ids, request);
/* 101 */     if (errors.hasErrors()) {
/* 102 */       return errors.showErrorPage(model);
/*     */     }
/* 104 */     Consult[] beans = this.manager.deleteByIds(ids);
/* 105 */     for (Consult bean : beans) {
/* 106 */       log.info("delete Consult id={}", bean.getId());
/*     */     }
/* 108 */     return list(null, null, Integer.valueOf(SimplePage.cpn(pageNo)), request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(Consult bean, HttpServletRequest request) {
/* 112 */     WebErrors errors = WebErrors.create(request);
/*     */ 
/* 115 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 119 */     WebErrors errors = WebErrors.create(request);
/* 120 */     Website web = SiteUtils.getWeb(request);
/* 121 */     if (vldExist(id, web.getId(), errors)) {
/* 122 */       return errors;
/*     */     }
/* 124 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 128 */     WebErrors errors = WebErrors.create(request);
/* 129 */     Website web = SiteUtils.getWeb(request);
/* 130 */     if (vldExist(id, web.getId(), errors)) {
/* 131 */       return errors;
/*     */     }
/* 133 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 137 */     WebErrors errors = WebErrors.create(request);
/* 138 */     Website web = SiteUtils.getWeb(request);
/* 139 */     errors.ifEmpty(ids, "ids");
/* 140 */     for (Long id : ids) {
/* 141 */       vldExist(id, web.getId(), errors);
/*     */     }
/* 143 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 147 */     if (errors.ifNull(id, "id")) {
/* 148 */       return true;
/*     */     }
/* 150 */     Consult entity = this.manager.findById(id);
/* 151 */     if (errors.ifNotExist(entity, Consult.class, id)) {
/* 152 */       return true;
/*     */     }
/*     */ 
/* 158 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ConsultAct
 * JD-Core Version:    0.6.2
 */