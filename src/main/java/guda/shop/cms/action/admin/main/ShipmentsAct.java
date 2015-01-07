 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.Shipments;
/*     */ import guda.shop.er.ShipmentsMng;
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
/*     */ public class ShipmentsAct
/*
/*  28 */   private static final Logger log = LoggerFactory.getLogger(Sht.class);
/
/*     */   @Autowire*/   private ShipmentsMng manager;
/
/*  32 */   @RequestMapping({"/Shipments/v_list.do"})
/*     */   public String list(Integer pageNo, HtRequest redelMap model) { Paginatation = this.manager.getPage(SimplePage.cp,
/*  33 */       CookieUtils.getPageSize(request));
/*  34 */  addAttribute("pagination", pagination);
/*  35 */     return "Shipments/list"; }
/*     */ 
/*     */   @RequestMapping({"/Shipments/v_add.do"})
/*     */   public String add(ModelMap model)
/*     */   {
/*  40 */     return "Shipments/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/Shipments/v_edit     */   ping edit(Long id, HttpServletRequest request, ModelMa{
/*  45 */     WebErrors errors = validateEdiuest);
/*  46 */     if (errors.hasErrors()) {
/*  4  return errororPage(mode  */     }
/*  49 */     model.addAttribute("order", ter.findById(id).getIndent());
/*  50 */     model.addAttribute("shipments", this.manager.findById(id));
     return "Shipments/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/Shipments/o_save.do"})
/*     */   publ save(Shipments bean, HttpServletRequest request, ModelMap model) {
/*  56 */     WebErrors errors = validateSave(bean, request);
/*  57 */     if (errors.hasErrors()) {
/*  58 */       return errors.showErrorPa;
/*     */   0 */     be.manager.save(bean);
/*  61 */     log.info("save Ship{}", bean.getId());
/*  62 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/Shipments/o_update.do"})
/*     */   public String update(Shipments bean, Integer pageNo, HttpServletRequest request, ModelMap m    */   {
/*  68 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  69 */     if (errors.hasErrors()) {
/*  70 */       return errors.showErrorPage(*     */     }/     bean nager.update(bean);
/*  73 */     log.info("update Shipm}.", bean.getId());
/*  74 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @pping({"/Shipments/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  80 */     WebErrors ealidateDelete(ids, request);
/*  81 */     if (errors.hasErrors()) {
/*  82 */       return errors.showErrorPage(model);
/*     */     }
/*  84 */     Shipments[] beans = this.manaeByIds(ids);
/    for (Shean : beans) {
/*  86 */       log.info("delete Shipment bean.getId());
/*     */     }
/*  88 */     return list(pageNo, request, model);
/*     */   }
/*     * */   private WebErrors validateSave(Shipments bean, HttpServletRequest request) {
/*  92 */     WebErrors errors = WebErrors.create(request);
/*  93 */     return error */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/*  97 */     WebErrors errors = WebErrors.create(request);
/*  98 */     Website web = SittWeb(request);
/*  99 */     if (vldExist(id, web.getId(), errors)) */       retu;
/*     */ 102 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 106 */     WebErrors errors = WebErrorsequest);
/* 10Website webils.getWeb(request);
/* 108 */     if (vldExist(id, web.getId(), errors)) {
/* 109 */       return errors;
/*     */     }
/* 111 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 115 */     WebErrs = WebErrors.create(request);
/* 116 */    web = SiteUtilrequest);
/    errors.ifEmpty(ids, "ids");
/* 118 */     for (Long id : ids) {
/* 119 */       vldExist(id, web.getId(), errors);
/*     */     }
/* 121 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 125 */     if (Null(id, "id")) {
/* 126 */       return true*/     }
/* 12Shipments ehis.manager.findById(id);
/* 129 */     if (errors.ifNotExist(entity, Shipments.class, id)) {
/* 130 */       return true;
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShipmentsAct
 * JD-Core Version:    0.6.2
 */