/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.Logistics;
/*     */ import guda.shop.cms.manager.LogisticsMng;
/*     */ import guda.shop.common.util.StrUtils;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class LogisticsAct
/*     */ {
/*  25 */   private static final Logger log = LoggerFactory.getLogger(LogisticsAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private LogisticsMng manager;
/*     */ 
/*  29 */   @RequestMapping({"/logistics/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { List list = this.manager.getAllList();
/*  30 */     model.addAttribute("list", list);
/*  31 */     return "logistics/list"; }
/*     */ 
/*     */   @RequestMapping({"/logistics/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model)
/*     */   {
/*  36 */     return "logistics/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logistics/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  41 */     WebErrors errors = validateEdit(id, request);
/*  42 */     if (errors.hasErrors()) {
/*  43 */       return errors.showErrorPage(model);
/*     */     }
/*  45 */     model.addAttribute("logistics", this.manager.findById(id));
/*  46 */     return "logistics/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logistics/o_save.do"})
/*     */   public String save(Logistics bean, String text, Long[] typeIds, HttpServletRequest request, ModelMap model)
/*     */   {
/*  52 */     WebErrors errors = validateSave(bean, request);
/*  53 */     if (errors.hasErrors()) {
/*  54 */       return errors.showErrorPage(model);
/*     */     }
/*  56 */     bean = this.manager.save(bean, text);
/*  57 */     log.info("save brand. id={}", bean.getId());
/*  58 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logistics/o_update.do"})
/*     */   public String update(Logistics bean, String text, HttpServletRequest request, ModelMap model) {
/*  63 */     WebErrors errors = validateUpdate(bean, request);
/*  64 */     if (errors.hasErrors()) {
/*  65 */       return errors.showErrorPage(model);
/*     */     }
/*  67 */     bean = this.manager.update(bean, text);
/*  68 */     log.info("update brand. id={}.", bean.getId());
/*  69 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logistics/o_delete.do"})
/*     */   public String delete(Long[] ids, HttpServletRequest request, ModelMap model)
/*     */   {
/*  75 */     WebErrors errors = validateDelete(ids, request);
/*  76 */     if (errors.hasErrors()) {
/*  77 */       return errors.showErrorPage(model);
/*     */     }
/*  79 */     Logistics[] beans = this.manager.deleteByIds(ids);
/*  80 */     for (Logistics bean : beans) {
/*  81 */       log.info("delete brand. id={}", bean.getId());
/*     */     }
/*  83 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logistics/o_priority.do"})
/*     */   public String priority(Long[] wids, Integer[] priority, HttpServletRequest request, ModelMap model)
/*     */   {
/*  89 */     WebErrors errors = validatePriority(wids, priority, request);
/*  90 */     if (errors.hasErrors()) {
/*  91 */       return errors.showErrorPage(model);
/*     */     }
/*  93 */     this.manager.updatePriority(wids, priority);
/*  94 */     model.addAttribute("message", "global.success");
/*  95 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(Logistics bean, HttpServletRequest request)
/*     */   {
/* 100 */     WebErrors errors = WebErrors.create(request);
/* 101 */     bean.setWebUrl(StrUtils.handelUrl(bean.getWebUrl()));
/* 102 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 106 */     WebErrors errors = WebErrors.create(request);
/* 107 */     errors.ifNull(id, "id");
/* 108 */     vldExist(id, errors);
/* 109 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Logistics bean, HttpServletRequest request) {
/* 113 */     WebErrors errors = WebErrors.create(request);
/* 114 */     Long id = bean.getId();
/* 115 */     bean.setWebUrl(StrUtils.handelUrl(bean.getWebUrl()));
/* 116 */     errors.ifNull(id, "id");
/* 117 */     vldExist(id, errors);
/* 118 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 122 */     WebErrors errors = WebErrors.create(request);
/* 123 */     errors.ifEmpty(ids, "ids");
/* 124 */     for (Long id : ids) {
/* 125 */       vldExist(id, errors);
/*     */     }
/* 127 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {
/* 132 */     WebErrors errors = WebErrors.create(request);
/* 133 */     if (errors.ifEmpty(wids, "wids")) {
/* 134 */       return errors;
/*     */     }
/* 136 */     if (errors.ifEmpty(priority, "priority")) {
/* 137 */       return errors;
/*     */     }
/* 139 */     if (wids.length != priority.length) {
/* 140 */       errors.addErrorString("wids length not equals priority length");
/* 141 */       return errors;
/*     */     }
/* 143 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 144 */       vldExist(wids[i], errors);
/* 145 */       if (priority[i] == null) {
/* 146 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 149 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, WebErrors errors) {
/* 153 */     Logistics entity = this.manager.findById(id);
/* 154 */     return errors.ifNotExist(entity, Logistics.class, id);
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.LogisticsAct
 * JD-Core Version:    0.6.2
 */