 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.ShopDictionary;
/*     */ import guda.shop.er.ShopDictionaryMng;
/*     */ import guda.shop.cms.managctionaryTypeMng;
/*     */ import guda.shop.common.page.Pagina    */ import guda.shop.common.page.SimplePage;
/* port guda.shop.common.web.CookieUtils;
/*     */ im.shop.common.web.RequestUtils;
/*     */ import gudre.entity.Website;
/*     */ import guda.shop.core.wils;
/*     */ import guda.shop.core.web.WebErro  */ import java.util.List;
/*     */ import jaet.http.HttpServletRequest;
/*     */ import orogger;
/*     */ import org.slf4jctory;
/*     */ import org.springframework.beans.factorion.Autowired;
/*     */ import orgamework.stereotype.Controller;
/*     */ i.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bition.RequestMapping;
/*     */
/*     */ @Controller
/*    c class ShopDictionaryAct
/*     */ {
/*  33 */   tatic final Logger log = LoggerFactory.getLogger(ShopDictionaryAct.class)*/
/*     owired
/*     */   priDictionaryMng manager;
/*     */ 
/*     owired
/*     */   private ShopDictionaryTypeMng shopDictionaryTypeMng;
/*     */
/*  38 */   @RequestMappipDictionaryo"})
/*     */   publicist(Long typeId, Integer pageNo, HttpServletReqest, ModelM { String name = RequestQueryParam(request, "name");
/*  39 */     List typelist = this.onaryTypeMng.findAll();
/*  40 */     Pagination pagination = this.manage(name, typeId, SimplePage.cpn(pageNo),
/*  41 */       CookieUtils.getPageSize(request));
/*  42 */     model.addAttribute("name", name);
/*  43 */     model.addAttribute("typeId", typeId);
/*  44 */     model.addAttribute("typelist", typelist);
/*  45 */     model.addAttribute("pagination", pagination);
/*  46 */     return "shopDictionary/list"; }
/*     */ 
/*     */   @RequestMapping({"/shopDictionary/v_add.do"})
/*     */   public String add(ModelMap model)
/*     */   {
     List sdtList = this.shopDictionaryTypeMng.findAll();
/*  52 */     model.addAttribute("sdtList", sdtList);
/*  53 */     return "shopDictionary/a   */   }
/
/*     */   @RequestMapping({"/shopDictionary/v_edit.do"}*/   public String edit(Long id, HttpServletReuest, ModelMap model) {
/*  58 */     WebErrors errors = validateEdit(id, request);
/*  59 */     if (errors.hasErrors()) {
/*  60 */       return errors.showErrorPage(model);
/*   }
/*  62 */ sdtList = DictionaryTypeMng.findAll();
/*  63 */     model.addAttribust", sdtList);
/*  64 */     model.addAttribute("shopDictionary", this.manager.findById(id));
/*  65 */     return "shopDictionary/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopDictionary/o_save.do"})
/*     */   publ save(ShopDictionary bean, Long shopDictionaryTypeId, HttpServletRequest request, ModelMap model)
/*     */   {
/*  71 */     WebErrors errors = validateSave(bean, request);
/*  72 */     if (errors.hasErrors()) {
/*  73 */       return errors.showErrorPage(mo    */     }
/    bean.seionaryType(this.shopDictionaryTypeMng.findById(shopDictiona);
/*  76 */     bean = this.manager.save(bean);
/*  77 */     log.info("save ShopDictionary id={}", bean.getId());
/*  78 turn "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopDictionary/o_update.do"})
/*     */   public String update(ShopDictionary bean, LictionaryTypeId, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  85 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  86 */     if (errors.hasErrors()) {
/*  87 */       return errors.showErrorPage(model);
/*     */     }
/*   bean.setShoryType(thisionaryTypeMng.findById(shopDictionaryTypeId));
/*  90 */     is.manager.update(bean);
/*  91 */     log.info("update ShopDictionary id={}.", bean.getId());
/*  92 */     return list(null, pageNo, reques;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopDictionary/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, Model)
/*     */   {
/*  98 */     WebErrors errors = validateDelete(ids, request);
/*  99 */     if (errors.hasErrors()) {
/* 100 */       return errors.showErrorPage(model);
/*     */     }
/* 102 */     ShopDictionary[] beans = this.manager.deleteByIds(ids);
/* 103 */     for (ShopDictiona beans) {
/* 1   log.infoShopDictionary id={}", bean.getId());
/*     */     }
/* 106 turn list(null, pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopDictpriority.do"})
/*     */   public String priority(Integer pageNo, Long[] wids, Integer[] priority, HttpServletRequest request, ModelMap model)
/*     */   {
/* 112 */   rs errors = validatePriority(wids, priority, request);
/* 113 */     if (errors.hasErrors()) {
/* 114 */       return errors.showErrorPage(model);
/*     */     }
/* 116 */     this.manager.updatePriority(wiity);
/* 117 */     model.addAttribute("message", "global.success");
/* 1 return list(nNo, request
/*     */   }
/*     */ 
/*     */   private WebErrors validatpDictionary bean, HttpServletRequest request) {
/* 122 */     WebErrors errors = WebErrors.create(request);
/*     */ 
/* 125 */rn errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 129 */     WebErrors errors = WebErrors.create(request);
/* 1 Website web = SiteUtils.getWeb(request);
/* 131 */     if (vldExist(id, web.getId(), errors)) {
/* 132 */       return errors;
/*     */     }
/* 134 */     return errors;
/*     */   }
/*     * */   private  validateUp id, HttpServletRequest request) {
/* 138 */     WebErrors errors = WebErrors.create(request);
/* 139 */     Website web = SiteUtils.getWeb(request);
/* 1 if (vldExist(id, web.getId(), errors)) */       retur
/*     */ 143 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 147 */     WebErrors errors = WebErrors.create(request);
/* 148 */     Website web = SiteUtils.getWeb(request);
/* 149 */     errors.ifEmpty(i);
/* 150 */     for (Long id : ids) {
/* 151 vldExist(id, (), errors)*/     }
/* 153 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 157 */     if (errors.ifNull(id, "id")) {
/* 158 */       return true;
/*     */     }
/* 160 */     ShopDictionary entity = this.manager.fin;
/* 161 */     if (errors.ifNotExist(entity,ionary.class,  162 */     true;
/*     */     }
/*     */ 
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {
/* 173 */     Website web = SiteUtils.getWeb(request);
/* 174 */     WebErrors errors = WebErrors.create(request);
/* 175  (errors.ifEmpty(wids, "ids")) {
/* 176 */   n errors;
/*   }
/* 178 *(errors.ifEmpty(priority, "priority")) {
/* 179 */       return errors;
/*     */     }
/* 181 */     if (wids.length != priority.length) {
/* 182 */   s.addErrorString("ids length not equals priority length");
/* 183 */       return errors;
/*     */     }
/* 185 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 186 *f (vldExist(widsgetId(), errors)) {
/* 187 */         rors;
/*     *//* 189 */  priority[i] == null) {
/* 190 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }/     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopDictionaryAct
 * JD-Core Version:    0.6.2
 */