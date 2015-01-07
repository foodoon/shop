/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.Exended;
/*     */ import guda.shop.cms.entity.ExendedItem;
/*     */ import guda.shop.cms.entity.ProductType;
/*     */ import guda.shop.cms.manager.ExendedMng;
/*     */ import guda.shop.cms.manager.ProductTypeMng;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.common.page.SimplePage;
/*     */ import guda.shop.common.web.CookieUtils;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.SiteUtils;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
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
/*     */ public class ExendedAct
/*     */ {
/*  37 */   private static final Logger log = LoggerFactory.getLogger(ExendedAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ExendedMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private ProductTypeMng productTypeMng;
/*     */ 
/*  41 */   @RequestMapping({"/exended/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  42 */     model.addAttribute("pagination", pagination);
/*  43 */     return "exended/list"; }
/*     */ 
/*     */   @RequestMapping({"/exended/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model)
/*     */   {
/*  48 */     Website web = SiteUtils.getWeb(request);
/*  49 */     List ptList = this.productTypeMng.getList(web.getId());
/*  50 */     model.addAttribute("ptList", ptList);
/*  51 */     return "exended/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/exended/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  56 */     Website web = SiteUtils.getWeb(request);
/*  57 */     WebErrors errors = validateEdit(id, request);
/*  58 */     if (errors.hasErrors()) {
/*  59 */       return errors.showErrorPage(model);
/*     */     }
/*  61 */     Long[] typeIds = this.manager.findById(id).getProductTypeIds();
/*  62 */     List list = new ArrayList();
/*  63 */     list.addAll(this.manager.findById(id).getItems());
/*  64 */     List ptList = this.productTypeMng.getList(web.getId());
/*  65 */     model.addAttribute("ptList", ptList);
/*  66 */     model.addAttribute("list", list);
/*  67 */     model.addAttribute("typeIds", typeIds);
/*  68 */     model.addAttribute("exended", this.manager.findById(id));
/*  69 */     return "exended/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/exended/o_save.do"})
/*     */   public String save(Exended bean, Long[] typeIds, String[] itemName, HttpServletRequest request, ModelMap model) {
/*  74 */     WebErrors errors = validateSave(bean, request);
/*  75 */     if (errors.hasErrors()) {
/*  76 */       return errors.showErrorPage(model);
/*     */     }
/*  78 */     List items = getItems(null, itemName);
/*  79 */     bean = this.manager.save(bean, typeIds, items);
/*  80 */     log.info("save exended id={}", bean.getId());
/*  81 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/exended/o_update.do"})
/*     */   public String update(Exended bean, Long[] typeIds, Long[] itemId, String[] itemName, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  87 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  88 */     if (errors.hasErrors()) {
/*  89 */       return errors.showErrorPage(model);
/*     */     }
/*  91 */     List items = getItems(null, itemName);
/*  92 */     bean = this.manager.update(bean, typeIds, items);
/*     */ 
/*  94 */     log.info("update Exended id={}.", bean.getId());
/*  95 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/exended/o_priority.do"})
/*     */   public String priority(Integer pageNo, Long[] wids, Integer[] priority, HttpServletRequest request, ModelMap model)
/*     */   {
/* 101 */     WebErrors errors = validatePriority(wids, priority, request);
/* 102 */     if (errors.hasErrors()) {
/* 103 */       return errors.showErrorPage(model);
/*     */     }
/* 105 */     this.manager.updatePriority(wids, priority);
/* 106 */     model.addAttribute("message", "global.success");
/* 107 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/exended/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 113 */     WebErrors errors = validateDelete(ids, request);
/* 114 */     if (errors.hasErrors()) {
/* 115 */       return errors.showErrorPage(model);
/*     */     }
/* 117 */     Exended[] beans = this.manager.deleteByIds(ids);
/* 118 */     for (Exended bean : beans) {
/* 119 */       log.info("delete Exended id={}", bean.getId());
/*     */     }
/* 121 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private List<ExendedItem> getItems(Long[] itemId, String[] itemName)
/*     */   {
/* 126 */     List items = new ArrayList();
/*     */ 
/* 128 */     if (itemName != null) {
/* 129 */       int i = 0; for (int len = itemName.length; i < len; i++) {
/* 130 */         if (!StringUtils.isBlank(itemName[i])) {
/* 131 */           ExendedItem item = new ExendedItem();
/* 132 */           if ((itemId != null) && (i < itemId.length)) {
/* 133 */             item.setId(itemId[i]);
/*     */           }
/* 135 */           item.setName(itemName[i]);
/* 136 */           items.add(item);
/*     */         }
/*     */       }
/*     */     }
/* 140 */     return items;
/*     */   }
/*     */ 
/*     */   public static Long[] fetchProductTypeIds(Collection<ProductType> productTypes)
/*     */   {
/* 150 */     Long[] ids = new Long[productTypes.size()];
/* 151 */     int i = 0;
/* 152 */     for (ProductType sdt : productTypes) {
/* 153 */       ids[(i++)] = sdt.getId();
/*     */     }
/* 155 */     return ids;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(Exended bean, HttpServletRequest request)
/*     */   {
/* 160 */     WebErrors errors = WebErrors.create(request);
/* 161 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 165 */     WebErrors errors = WebErrors.create(request);
/* 166 */     Website web = SiteUtils.getWeb(request);
/* 167 */     if (vldExist(id, web.getId(), errors)) {
/* 168 */       return errors;
/*     */     }
/* 170 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 174 */     WebErrors errors = WebErrors.create(request);
/* 175 */     Website web = SiteUtils.getWeb(request);
/* 176 */     if (vldExist(id, web.getId(), errors)) {
/* 177 */       return errors;
/*     */     }
/* 179 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 183 */     WebErrors errors = WebErrors.create(request);
/* 184 */     Website web = SiteUtils.getWeb(request);
/* 185 */     errors.ifEmpty(ids, "ids");
/* 186 */     for (Long id : ids) {
/* 187 */       vldExist(id, web.getId(), errors);
/*     */     }
/* 189 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 193 */     if (errors.ifNull(id, "id")) {
/* 194 */       return true;
/*     */     }
/* 196 */     Exended entity = this.manager.findById(id);
/* 197 */     if (errors.ifNotExist(entity, Exended.class, id)) {
/* 198 */       return true;
/*     */     }
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {
/* 205 */     Website web = SiteUtils.getWeb(request);
/* 206 */     WebErrors errors = WebErrors.create(request);
/* 207 */     if (errors.ifEmpty(wids, "ids")) {
/* 208 */       return errors;
/*     */     }
/* 210 */     if (errors.ifEmpty(priority, "priority")) {
/* 211 */       return errors;
/*     */     }
/* 213 */     if (wids.length != priority.length) {
/* 214 */       errors.addErrorString("ids length not equals priority length");
/* 215 */       return errors;
/*     */     }
/* 217 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 218 */       if (vldExist(wids[i], web.getId(), errors)) {
/* 219 */         return errors;
/*     */       }
/* 221 */       if (priority[i] == null) {
/* 222 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 225 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ExendedAct
 * JD-Core Version:    0.6.2
 */