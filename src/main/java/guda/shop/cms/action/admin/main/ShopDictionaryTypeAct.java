 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.ShopDictionaryType;
/*     */ import guda.shop.er.ShopDictionaryTypeMng;
/*     */ import guda.shop.common.pation;
/*     */ import guda.shop.common.page.Simple    */ import guda.shop.common.web.CookieUtils;
/* port guda.shop.core.entity.Website;
/*     */ imporop.core.web.SiteUtils;
/*     */ import guda.shob.WebErrors;
/*     */ import javax.servlet.httvletRequest;
/*     */ import org.slf4j.Logger;/ import org.slf4j.LoggerFactory;
/*     */ import org.sework.beans.factory.annotation.Auto     */ import org.springframework.stereotoller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ importngframework.web.bind.annotation.RequestMapping;
/*     */ 
/@Controller
/*     */ public class ShopDictionaryT     */ {
/*  28 */   private static final Logger log = LoggerFactory.getopDictionarclass);
/*     */ 
/* @Autowired
/*     */   private ShopDictionaryanager;
/*     */
/*  32 */   @RequestMapping({"/shopDictionaryType/v_list.do"})
/*     */   public String listpageNo, Httequest request, ModelMa{ Pagination pagination = this.manager.getPage(Simpn(pageNo),
/*  33 */       CookieUtils.getPageSize(request));
/*  34 */  addAttribute("pagination", pagination);
/*  35 */     return "shopDictionaryType/list"; }
/*     */ 
/*     */   @RequestMapping({"/shopDictionaryType/v_add.do"})
/*     */   public String add(ModelMap model)
/*     */   {
/*  40 */     return "shopDictionaryType/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({tionaryTypeo"})
/*     */   public String edit(Long id, HttpServletReques, ModelMap model) {
/*  45 */     WebErrors erlidateEdit(id, request);
/*  46 */     if (errors.hasErrors()7 */       rets.showErrorl);
/*     */     }
/*  49 */     model.addAttribute("shopDicti", this.manager.findById(id));
/*  50 */     return "shopDictionaryType/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopDictionaryType/o_save.do"})
/*     */   public String save(ShopDictionaryType bean, HttpServletRequst, ModelMap model) {
/*  55 */     WebErrors errors = validateSave(bean, request);
/*  56 */     if (errors.hasErrors()) {
/*  57 */       return owErrorPage(mo    */     */     bean = this.manager.save(bean);
/*  60 */     log.info("DictionaryType id={}", bean.getId());
/*  61 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopDictionaryType/o_update.do"})
/*     */   public String update(ShopDictionaryType bean, Integer pageNo, HttpServletequest, ModelMap model)
/*     */   {
/*  67 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  68 */     if (errors.hasErrors()) {
/*  69 */       return errrrorPage(model */     }
/    bean = this.manager.update(bean);
/*  72 */     log.info("updictionaryType id={}.", bean.getId());
/*  73 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*  RequestMapping({"/shopDictionaryType/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  79 bErrors errors = validateDelete(ids, request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     ShopDictionaryType[]this.manager.ds(ids);
/*   for (ShopDictionaryType bean : beans) {
/*  85 */       log.inf ShopDictionaryType id={}", bean.getId());
/*     */     }
/*  87 */     return list(pageNo, request, mod   */   }
/*     */ 
/*     */   private WebErrors validateSave(ShopDictionaryType bean, HttpServletRequest request) {
/*  91 */     WebErrors errors = WebErrors.create(
/*     */ 
/*  94 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/*  98 */     WebErrors errors = WebErrors.create(request);
/*  99 */  e web = SiteUtils.getWeb(request);
/* 100 */     if (vldExist(id, w), errors)) {
       retu;
/*     */     }
/* 103 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/*   WebErrors errors = WebErrors.create(r/* 108 */     eb = SiteUtb(request);
/* 109 */     if (vldExist(id, web.getId(), errors)) {
/* 110 */       return errors;
/*     */     }
/* 112 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 116 */     WebErrors errorrors.create(request);
/* 117 */     Website eUtils.getWeb(
/* 118 */ s.ifEmpty(ids, "ids");
/* 119 */     for (Long id : ids) {
/* 120 */       vldExist(id, web.getId(), errors);
/*     */     }
/* 122 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 126 */     if (errors.if"id")) {
/* 127 */       return true;
/*     /* 129 */     onaryType ehis.manager.findById(id);
/* 130 */     if (errors.ifNotExist(entity, ShopDictionaryType.class, id)) {
/* 131 */       return true;
/*     */     }
/*     */ 
/* 137 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopDictionaryTypeAct
 * JD-Core Version:    0.6.2
 */