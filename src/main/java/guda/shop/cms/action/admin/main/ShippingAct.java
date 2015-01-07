 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.Shipping;
/*     */ import guda.shop.er.LogisticsMng;
/*     */ import guda.shop.cms.managngMng;
/*     */ import guda.shop.core.web.SiteUtils*/ import guda.shop.core.web.WebErrors;
/*      java.util.List;
/*     */ import javax.servletpServletRequest;
/*     */ importj.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*  ort org.springframework.beans.factotion.Autowired;
/*     */ import org.sprink.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap*/ import org.springframework.web.bind.annotation.RequestMap    */
/*     */ @Controller
/*     */ public clangAct
/*     */ {
/*  28 */   private static final Logger log = LoggerFacogger(Shippass);
/*     */ 
/*   utowired
/*     */   private Shippiager;
/*     */
/*     */   @Autowired
/*     */   private LogisticsMng logisticsMng;
/*     */
/*  RequestMappipping/v_list.do"})
/* public String list(Integer pageNo, HttpSeest requestp model) { List list = ger.getList(SiteUtils.getWebId(request), true);/     model.addAttribute("list", list);
/*  35 */     return "shst"; }
/*     */ 
/*     */   @RequestMapping({"/shipping/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model)
/*     */   {
/*  40 */     List list = this.logisticsMng.getAllList();
/*  41 */     model.addAttribute("st);
/*  42eturn "shipping/add";
/*     */   }
/*     */ 
/*   equestMapping({"/shipping/v_edit.do"})
/*     */   public String edit(LongServletRequest request, ModelMap model) {
/*  47 */     WebErrors errors = validateEdit(id, request);
/*  48 */     if (errors.hasErrors()) {
/*  49 */      rrors.showErroel);
/*
     model.addAttribute("shipping", this.madById(id));
/*  52 */     List list = this.logisticsMng.getAllList();
/*  53 */     model.addAttribute("list", list);
/*  54 */     return "shipping/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shipping/o_save.do"})
/*  ublic String save(Shipping bean, Long logisticsId, HttpServletRequest request, ModelMap model) {
/*  59 */     WebErrors errors = validateSave(bean, request);
/*  60 */     if (errors.hasErrors()) {
/*  61 */       return errors.show(model);
/*   }
/*  63 */bean.getIsDefault().booleanValue()) {
/*  64 */      t = this.manager.getList(Long.valueOf(1L), true);
/*  65 */       for (int i = 0; i < list.size(); i++) {
/*  66 */         ((Shipping)list.get(i)).setIsDefault(Boolean.valueOf(false));
/*  67 */         this.manager.update((Shipping)list.get(i));
/*     */       */     }
/*  70 */     bean.setLogistics(this.logisticsMng.findById(logisticsId));
/*  71 */     bean = this.manager.save(bean);
/*  72 */     log.info("save Shipping. id={}", bean.getId());
/*  73 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shipping/o_update.do"})
/*     */   public String updang bean, Long logiInteger pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  79 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*   }
/*  83 */ ean.getIsDeooleanValue()) {
/*  84 */       List list = this.managt(Long.valueOf(1L), true);
/*  85 */       for (int i = 0; i < list.size(); i++) {
/*  86 */         ((Shipping)list.get(i)).slt(Boolean.valueOf(false));
/*  87 */         this.manager.update(bean);
/*     */       }
/*     */     }
/*  90 */     bean.setLogistics(this.logisticsMng.findById(logisticsId) */     bean = this.manager.update(bean);
/*  92 */     log.info("update Shipping. id={}.", bean.getId());
/*  93 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shipping/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, model)
/*     */   */     WebErrors errors = validateDelete(ids, request);
/* 100 */     if (errors.hasErrors()) {
/* 101 */       return errors.showErrorPage(model);
/*     */     }
/* 103 */     Shipping[] beans = this.manager.deleteByIds(ids);
/* 104 */     for (Shippi beans) {
/* 1   log.infoShipping. id={}", bean.getId());
/*     */     }
/* 107eturn list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shipping/o_pr"})
/*     */   public String priority(Long[] wids, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 113 */     WebErrors validatePriority(wids, priority, request);
/* 114 */     if (errors.hasErrors()) {
/* 115 */       return errors.showErrorPage(model);
/*     */     }
/* 117 */     this.manager.updatePrior priority);
/* 118 */     model.addAttribute("message", "global.suc* 119 */     rt(pageNo, rodel);
/*     */   }
/*     */ 
/*     */   private WebErdateSave(Shipping bean, HttpServletRequest request)
/*     */   {
/* 125 */     WebErrors errors = WebErrors.create(request);
/*   bean.setWebsite(SiteUtils.getWeb(request));
/* 127 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {/     WebErrors errors = WebErrors.create(request);
/* 132 */     errors.ifNull(id, "id");
/* 133 */     vldExist(id, errors);
/* 134 */     return errors;
/*     */   }
/*     */ 
/*     *te WebErrors vdate(Long irvletRequest request) {
/* 138 */     WebErrors errors = WebErrors.create(request);
/*   errors.ifNull(id, "id");
/* 140 */     vldExist(id, errors);
/* 141 */     return errors;
/*     */   }
/*     */
/*     */   private WebErrors validateDeletids, HttpServl request) {/     WebErrors errors = WebErrors.create(request);
/* 146 */     errors.ifEmpty(ids, "ids");
/* 147 */     for (Long id : ids) {
/* 148 */       vldExist(id, errors);
/*     */     }
/* 150 */     return errors;
/*     */   }
/*     */ 
/*     *te WebErrors viority(LongInteger[] priority, HttpServletRequest request)
/*     */   {
/* 155 */     WebErrors errors = WebErrors.create(request);
/* 156 */     if (errors.ifEmpty(wids, "wids")) {
/* 157 */       return errors;
/*     */     }
/* 159 */     if (errors.ifEmity, "priority160 */     errors;
/*     */     }
/* 162 */     if (wids.length != priority.length) {
/* 163 */       errors.addErrorString("wids length not equals priority length");
/* 164 */       return errors;
/*     */     }
/* 166 */     int i = 0; for (int len = wids.length; i < le
/* 167 */       vldExist(wids[i], errors);
/      if (prio= null) {
/        priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 172 */     return errors;
/* }
/*     */ 
/*     */   private boolean vldExist(Long id, WebErrors errors) {
/* 176 */     Shipping entity = this.manager.findById(id);
/* 177 */     rets.ifNotExist(entity, Shipping.class, id);
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShippingAct
 * JD-Core Version:    0.6.2
 */