 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.ProductTag;
/*     */ import guda.shop.er.ProductTagMng;
/*     */ import guda.shop.cms.web.S
/*     */ import guda.shop.core.web.WebErrors*/ import java.util.List;
/*     */ import java.http.HttpServletRequest;
/*      org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFact   */ import org.springframework.bery.annotation.Autowired;
/*     */ import gframework.stereotype.Controller;
/*     */ import org.springframework.ui;
/*     */ import org.springframework.web.bind.annotation.Rping;
/*     */
/*     */ @Controller
/*     */ pss ProductTagAct
/*     */ {
/*  26 */   private static final Logger log actory.getLductTagAct.class);
/* *     */   @Autowired
/*     */   priuctTagMng manager;
/*     */
/*  30 */   @RequestMapping({"/tag/v_list.do"})
/*     */   public String ServletRequst, ModelMap model) { L= this.manager.getList(SiteUtils.getWebId(r
/*  31 */     model.addAttribute("list", list);
/*  32 */ n "tag/list";
/*     */   }
/*     */
/*     */   @RequestMapping({"/tag/o_save.do"})
/*     */   public String save(ProductTag bean, HttpServletRequest request, ModelMap model)
/*     */   {
/*  38 */     WebErrors errodateSave(bean,;
/*  39 */errors.hasErrors()) {
/*  40 */       return errrrorPage(model);
/*     */     }
/*  42 */     bean = this.manager.save(bean);
/*  43 */    ("save ProductTag. id={}", bean.getId());
/*  44 */     return "redirect:v_list.do";
/*     */   }
/*     */
/*     */   @RequestMapping({"/tag/o_update_tag_names.do"}*/   public String updateTagName(Long[] wids, String[] tagNames, HttpServletRequest request, ModelMap model)
/*     */   {
/*  50 */     WebErrors errors = validateUpdat(wids, tagNamet);
/*  51  (errors.hasErrors()) {
/*  52 */       return errors.showErodel);
/*     */     }
/*  54 */     ProductTag[] beans = this.manager.updateTagName(wids, tagNames);
/*  55 */     uctTag bean : beans) {
/*  56 */       log.info("update ProductTag. id={},name={}", bean.getId(), 
/*  57 */         bean.getName());
/*     */     }
/*  59 */     return "redirect:v_list.   */   }
/*     */
/*     */   @RequestMapping({"/tag/o_delete.do"})
/*     */   public String delete(Long[] ids, HttpServletRequest request, ModelMap model) {
/*  64 */     WebErrors errors = validateDelete(ids, request);
/*  65 */     if (errors.ha) {
/*  66 */       return errors.showErrorPage(model);
/*   }
/*  68 */ ctTag[] bea.manager.deleteByIds(ids);
/*  69 */     for (Prodan : beans) {
/*  70 */       log.info("delete ProductTag. id={},name={}", bean.getId(), 
/*  71 */         bean.getName());
/*     */     }
/*  73 */     return list(request, model);
/*     */   }
/*     */
/*     */   private WebErrors validatductTag bean, HttpServletRequest request) {
/*  77 */     WebErrors errors = WebErrors.create(request);
/*  78 */     bean.setWebsite(SiteUtils.getWeb(request));
/*  79 */     return errors;
/*     */   }
/*     */
/*     */   private WeblidateUpdateTagNames(Long[] wids, String[] tagNames, HttpSeest request)
/  {
/*  84 bErrors errors = WebErrors.create(request);
/*  85 */     errors.ifEmpty(wids, "wids");
/*  86 */     errors.ifEmpty(tagNames, "tagNames");
/*  87 */     if (errors.hasErrors()) {
/*  88 */       return errors;
/*     */     }
/*  90 */ ids.length != length) {
/      errors.addErrorString("wids length must equals tagNames length");
/*  92 */       return errors;
/*     */  94 */     int i = 0; for (int len = wids.length; i < len; i++) {
/*  95 */       vldExist(wids[i], errors);
/*  96 */       if (errors.hasErrors()) {
/*  97 */         return errors;
/*     */       }
/*     */     }
/* 100 */     return er    */   }
/*     */
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 104 */     WebErrors errors = WebErrors.create(request);
/* 105 */   ifEmpty(ids, "ids");
/* 106 */     for (Long id : ids) {
/* 107 */       vldExist(id, errors);
/*     */     }
/* 109 */     return errors;
/*     */   }
/*     */
/*     */   private void vldExist(Longrrors errors) {
/*   if (errors.hasErrors()) {
/* 114 */       *     */     }/     Produity = this.manager.findById(id);
/* 117 */     errors.ifNotExist(entity, ProductTag.class, id);
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ProductTagAct
 * JD-Core Version:    0.6.2
 */