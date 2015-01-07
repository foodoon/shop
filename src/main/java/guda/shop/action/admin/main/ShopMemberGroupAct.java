/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.ShopMemberGroup;
/*     */ import guda.shop.cms.manager.ShopMemberGroupMng;
/*     */ import guda.shop.cms.web.SiteUtils;
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
/*     */ public class ShopMemberGroupAct
/*     */ {
/*  26 */   private static final Logger log = LoggerFactory.getLogger(ShopMemberGroupAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ShopMemberGroupMng manager;
/*     */ 
/*  31 */   @RequestMapping({"/group/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { List list = this.manager.getList(
/*  32 */       SiteUtils.getWebId(request));
/*  33 */     model.addAttribute("list", list);
/*  34 */     return "group/list"; }
/*     */ 
/*     */   @RequestMapping({"/group/v_add.do"})
/*     */   public String add(ModelMap model)
/*     */   {
/*  39 */     return "group/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  44 */     WebErrors errors = validateEdit(id, request);
/*  45 */     if (errors.hasErrors()) {
/*  46 */       return errors.showErrorPage(model);
/*     */     }
/*  48 */     model.addAttribute("group", this.manager.findById(id));
/*  49 */     return "group/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/o_save.do"})
/*     */   public String save(ShopMemberGroup bean, HttpServletRequest request, ModelMap model)
/*     */   {
/*  55 */     WebErrors errors = validateSave(bean, request);
/*  56 */     if (errors.hasErrors()) {
/*  57 */       return errors.showErrorPage(model);
/*     */     }
/*  59 */     bean = this.manager.save(bean);
/*  60 */     log.info("save ShopMemberGroup. id={}", bean.getId());
/*  61 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/o_update.do"})
/*     */   public String update(ShopMemberGroup bean, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  67 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  68 */     if (errors.hasErrors()) {
/*  69 */       return errors.showErrorPage(model);
/*     */     }
/*  71 */     bean = this.manager.update(bean);
/*  72 */     log.info("update ShopMemberGroup. id={}.", bean.getId());
/*  73 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  79 */     WebErrors errors = validateDelete(ids, request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     ShopMemberGroup[] beans = this.manager.deleteByIds(ids);
/*  84 */     for (ShopMemberGroup bean : beans) {
/*  85 */       log.info("delete ShopMemberGroup. id={}", bean.getId());
/*     */     }
/*  87 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(ShopMemberGroup bean, HttpServletRequest request)
/*     */   {
/*  92 */     WebErrors errors = WebErrors.create(request);
/*  93 */     bean.setWebsite(SiteUtils.getWeb(request));
/*  94 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/*  98 */     WebErrors errors = WebErrors.create(request);
/*  99 */     errors.ifNull(id, "id");
/* 100 */     vldExist(id, errors);
/* 101 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 105 */     WebErrors errors = WebErrors.create(request);
/* 106 */     errors.ifNull(id, "id");
/* 107 */     vldExist(id, errors);
/* 108 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 112 */     WebErrors errors = WebErrors.create(request);
/* 113 */     errors.ifEmpty(ids, "ids");
/* 114 */     for (Long id : ids) {
/* 115 */       vldExist(id, errors);
/*     */     }
/* 117 */     return errors;
/*     */   }
/*     */ 
/*     */   private void vldExist(Long id, WebErrors errors) {
/* 121 */     if (errors.hasErrors()) {
/* 122 */       return;
/*     */     }
/* 124 */     ShopMemberGroup entity = this.manager.findById(id);
/* 125 */     errors.ifNotExist(entity, ShopMemberGroup.class, id);
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopMemberGroupAct
 * JD-Core Version:    0.6.2
 */