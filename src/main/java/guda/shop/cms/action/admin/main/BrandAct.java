/*     */ package guda.shop.cms.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.Brand;
/*     */ import guda.shop.cms.manager.BrandMng;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.common.util.CnToSpell;
/*     */ import guda.shop.common.util.StrUtils;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class BrandAct
/*     */ {
/*  29 */   private static final Logger log = LoggerFactory.getLogger(BrandAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private BrandMng manager;
/*     */ 
/*  35 */   @RequestMapping({"/brand/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { List list = this.manager.getAllList();
/*  36 */     model.addAttribute("list", list);
/*  37 */     return "brand/list"; }
/*     */ 
/*     */   @RequestMapping({"/brand/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model)
/*     */   {
/*  42 */     return "brand/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/brand/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  47 */     WebErrors errors = validateEdit(id, request);
/*  48 */     if (errors.hasErrors()) {
/*  49 */       return errors.showErrorPage(model);
/*     */     }
/*  51 */     model.addAttribute("brand", this.manager.findById(id));
/*  52 */     return "brand/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/brand/o_save.do"})
/*     */   public String save(Brand bean, String text, HttpServletRequest request, ModelMap model)
/*     */   {
/*  58 */     WebErrors errors = validateSave(bean, request);
/*  59 */     if (errors.hasErrors()) {
/*  60 */       return errors.showErrorPage(model);
/*     */     }
/*     */ 
/*  63 */     String name = bean.getName();
/*  64 */     CnToSpell cts = new CnToSpell();
/*  65 */     bean.setFirstCharacter(cts.getBeginCharacter(name).substring(0, 1));
/*  66 */     bean = this.manager.save(bean, text);
/*  67 */     log.info("save brand. id={}", bean.getId());
/*  68 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/brand/o_update.do"})
/*     */   public String update(Brand bean, String text, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  74 */     WebErrors errors = validateUpdate(bean, request);
/*  75 */     if (errors.hasErrors()) {
/*  76 */       return errors.showErrorPage(model);
/*     */     }
/*     */ 
/*  79 */     String name = bean.getName();
/*  80 */     CnToSpell cts = new CnToSpell();
/*  81 */     bean.setFirstCharacter(cts.getBeginCharacter(name).substring(0, 1));
/*  82 */     bean = this.manager.update(bean, text);
/*  83 */     log.info("update brand. id={}.", bean.getId());
/*  84 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/brand/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  90 */     WebErrors errors = validateDelete(ids, request);
/*  91 */     if (errors.hasErrors()) {
/*  92 */       return errors.showErrorPage(model);
/*     */     }
/*  94 */     Brand[] beans = this.manager.deleteByIds(ids);
/*  95 */     for (Brand bean : beans) {
/*  96 */       log.info("delete brand. id={}", bean.getId());
/*     */     }
/*  98 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/brand/o_priority.do"})
/*     */   public String priority(Long[] wids, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 104 */     WebErrors errors = validatePriority(wids, priority, request);
/* 105 */     if (errors.hasErrors()) {
/* 106 */       return errors.showErrorPage(model);
/*     */     }
/* 108 */     this.manager.updatePriority(wids, priority);
/* 109 */     model.addAttribute("message", "global.success");
/* 110 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/brand/v_check_brandName.do"})
/*     */   public void checkUsername(String name, HttpServletResponse response)
/*     */   {
/*     */     String pass;
/*     */     String pass;
/* 116 */     if (StringUtils.isBlank(name))
/* 117 */       pass = "false";
/*     */     else {
/* 119 */       pass = this.manager.brandNameNotExist(name) ? "true" : "false";
/*     */     }
/* 121 */     ResponseUtils.renderJson(response, pass);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(Brand bean, HttpServletRequest request) {
/* 125 */     WebErrors errors = WebErrors.create(request);
/* 126 */     bean.setWebUrl(StrUtils.handelUrl(bean.getWebUrl()));
/* 127 */     bean.setWebsite(SiteUtils.getWeb(request));
/* 128 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 132 */     WebErrors errors = WebErrors.create(request);
/* 133 */     errors.ifNull(id, "id");
/* 134 */     vldExist(id, errors);
/* 135 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Brand bean, HttpServletRequest request) {
/* 139 */     WebErrors errors = WebErrors.create(request);
/* 140 */     Long id = bean.getId();
/* 141 */     bean.setWebUrl(StrUtils.handelUrl(bean.getWebUrl()));
/* 142 */     errors.ifNull(id, "id");
/* 143 */     vldExist(id, errors);
/* 144 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 148 */     WebErrors errors = WebErrors.create(request);
/* 149 */     errors.ifEmpty(ids, "ids");
/* 150 */     for (Long id : ids) {
/* 151 */       vldExist(id, errors);
/*     */     }
/* 153 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {
/* 158 */     WebErrors errors = WebErrors.create(request);
/* 159 */     if (errors.ifEmpty(wids, "wids")) {
/* 160 */       return errors;
/*     */     }
/* 162 */     if (errors.ifEmpty(priority, "priority")) {
/* 163 */       return errors;
/*     */     }
/* 165 */     if (wids.length != priority.length) {
/* 166 */       errors.addErrorString("wids length not equals priority length");
/* 167 */       return errors;
/*     */     }
/* 169 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 170 */       vldExist(wids[i], errors);
/* 171 */       if (priority[i] == null) {
/* 172 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 175 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, WebErrors errors) {
/* 179 */     Brand entity = this.manager.findById(id);
/* 180 */     return errors.ifNotExist(entity, Brand.class, id);
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.BrandAct
 * JD-Core Version:    0.6.2
 */