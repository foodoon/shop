/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.Standard;
/*     */ import guda.shop.cms.entity.StandardType;
/*     */ import guda.shop.cms.manager.StandardMng;
/*     */ import guda.shop.cms.manager.StandardTypeMng;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.common.page.SimplePage;
/*     */ import guda.shop.common.web.CookieUtils;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.SiteUtils;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Set;
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
/*     */ public class StandardTypeAct
/*     */ {
/*  36 */   private static final Logger log = LoggerFactory.getLogger(StandardTypeAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private StandardTypeMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private StandardMng standardMng;
/*     */ 
/*  40 */   @RequestMapping({"/standardType/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo), 
/*  41 */       CookieUtils.getPageSize(request));
/*  42 */     model.addAttribute("pagination", pagination);
/*  43 */     return "standardType/list"; }
/*     */ 
/*     */   @RequestMapping({"/standardType/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model)
/*     */   {
/*  48 */     return "standardType/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/standardType/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  53 */     Website web = SiteUtils.getWeb(request);
/*  54 */     WebErrors errors = validateEdit(id, request);
/*  55 */     if (errors.hasErrors()) {
/*  56 */       return errors.showErrorPage(model);
/*     */     }
/*  58 */     StandardType bean = this.manager.findById(id);
/*  59 */     List standards = this.standardMng.findByTypeId(id);
/*  60 */     model.addAttribute("standardType", bean);
/*  61 */     model.addAttribute("standards", standards);
/*  62 */     return "standardType/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/standardType/o_save.do"})
/*     */   public String save(StandardType bean, String[] itemName, String[] itemColor, Integer[] itemPriority, HttpServletRequest request, ModelMap model)
/*     */   {
/*  69 */     WebErrors errors = validateSave(bean, request);
/*  70 */     if (errors.hasErrors()) {
/*  71 */       return errors.showErrorPage(model);
/*     */     }
/*  73 */     bean = this.manager.save(bean);
/*  74 */     addStandard(bean, itemName, itemColor, itemPriority);
/*  75 */     log.info("save StandardType id={}", bean.getId());
/*  76 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/standardType/o_update.do"})
/*     */   public String update(StandardType bean, Long[] itemId, String[] itemName, String[] itemColor, Integer[] itemPriority, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  83 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  84 */     if (errors.hasErrors()) {
/*  85 */       return errors.showErrorPage(model);
/*     */     }
/*  87 */     bean = this.manager.update(bean);
/*     */ 
/*  89 */     updateStandard(bean, itemId, itemName, itemColor, itemPriority);
/*  90 */     log.info("update StandardType id={}.", bean.getId());
/*  91 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/standardType/o_priority.do"})
/*     */   public String priority(Integer pageNo, Long[] wids, Integer[] priority, HttpServletRequest request, ModelMap model)
/*     */   {
/*  97 */     WebErrors errors = validatePriority(wids, priority, request);
/*  98 */     if (errors.hasErrors()) {
/*  99 */       return errors.showErrorPage(model);
/*     */     }
/* 101 */     this.manager.updatePriority(wids, priority);
/* 102 */     model.addAttribute("message", "global.success");
/* 103 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/standardType/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 111 */     WebErrors errors = validateDelete(ids, request);
/* 112 */     if (errors.hasErrors()) {
/* 113 */       return errors.showErrorPage(model);
/*     */     }
/* 115 */     StandardType[] beans = this.manager.deleteByIds(ids);
/* 116 */     for (StandardType bean : beans) {
/* 117 */       log.info("delete StandardType id={}", bean.getId());
/*     */     }
/* 119 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private void addStandard(StandardType bean, String[] itemName, String[] itemColor, Integer[] itemPriority)
/*     */   {
/* 124 */     if (itemName != null) {
/* 125 */       int i = 0; for (int len = itemName.length; i < len; i++)
/* 126 */         if (!StringUtils.isBlank(itemName[i])) {
/* 127 */           Standard item = new Standard();
/* 128 */           item.setName(itemName[i]);
/* 129 */           item.setColor(itemColor[i]);
/* 130 */           item.setPriority(itemPriority[i]);
/* 131 */           item.setType(bean);
/* 132 */           this.standardMng.save(item);
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/standardType/v_check_field.do"})
/*     */   public String checkUsername(String field, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 141 */     if ((StringUtils.isBlank(field)) || (this.manager.getByField(field) != null))
/* 142 */       ResponseUtils.renderJson(response, "false");
/*     */     else {
/* 144 */       ResponseUtils.renderJson(response, "true");
/*     */     }
/* 146 */     return null;
/*     */   }
/*     */ 
/*     */   private void updateStandard(StandardType bean, Long[] itemId, String[] itemName, String[] itemColor, Integer[] itemPriority) {
/* 150 */     Set set = bean.getStandardSet();
/* 151 */     if (itemId != null) {
/* 152 */       for (Standard s : set) {
/* 153 */         if (!Arrays.asList(itemId).contains(s.getId()))
/* 154 */           this.standardMng.deleteById(s.getId());
/*     */       }
/*     */     }
/*     */     else {
/* 158 */       for (Standard s : set) {
/* 159 */         this.standardMng.deleteById(s.getId());
/*     */       }
/*     */     }
/*     */ 
/* 163 */     if (itemName != null) {
/* 164 */       int i = 0; for (int len = itemName.length; i < len; i++)
/* 165 */         if (!StringUtils.isBlank(itemName[i]))
/* 166 */           if ((itemId != null) && (i < itemId.length)) {
/* 167 */             Standard item = this.standardMng.findById(itemId[i]);
/* 168 */             item.setName(itemName[i]);
/* 169 */             item.setColor(itemColor[i]);
/* 170 */             item.setPriority(itemPriority[i]);
/* 171 */             item.setType(bean);
/* 172 */             this.standardMng.update(item);
/*     */           } else {
/* 174 */             Standard item = new Standard();
/* 175 */             item.setName(itemName[i]);
/* 176 */             item.setColor(itemColor[i]);
/* 177 */             item.setPriority(itemPriority[i]);
/* 178 */             item.setType(bean);
/* 179 */             this.standardMng.save(item);
/*     */           }
/*     */     }
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(StandardType bean, HttpServletRequest request)
/*     */   {
/* 187 */     WebErrors errors = WebErrors.create(request);
/* 188 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 192 */     WebErrors errors = WebErrors.create(request);
/* 193 */     Website web = SiteUtils.getWeb(request);
/* 194 */     if (vldExist(id, web.getId(), errors)) {
/* 195 */       return errors;
/*     */     }
/* 197 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 201 */     WebErrors errors = WebErrors.create(request);
/* 202 */     Website web = SiteUtils.getWeb(request);
/* 203 */     if (vldExist(id, web.getId(), errors)) {
/* 204 */       return errors;
/*     */     }
/* 206 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 210 */     WebErrors errors = WebErrors.create(request);
/* 211 */     Website web = SiteUtils.getWeb(request);
/* 212 */     errors.ifEmpty(ids, "ids");
/* 213 */     for (Long id : ids) {
/* 214 */       vldExist(id, web.getId(), errors);
/*     */     }
/* 216 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 220 */     if (errors.ifNull(id, "id")) {
/* 221 */       return true;
/*     */     }
/* 223 */     StandardType entity = this.manager.findById(id);
/* 224 */     if (errors.ifNotExist(entity, StandardType.class, id)) {
/* 225 */       return true;
/*     */     }
/* 227 */     return false;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {
/* 232 */     Website web = SiteUtils.getWeb(request);
/* 233 */     WebErrors errors = WebErrors.create(request);
/* 234 */     if (errors.ifEmpty(wids, "ids")) {
/* 235 */       return errors;
/*     */     }
/* 237 */     if (errors.ifEmpty(priority, "priority")) {
/* 238 */       return errors;
/*     */     }
/* 240 */     if (wids.length != priority.length) {
/* 241 */       errors.addErrorString("ids length not equals priority length");
/* 242 */       return errors;
/*     */     }
/* 244 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 245 */       if (vldExist(wids[i], web.getId(), errors)) {
/* 246 */         return errors;
/*     */       }
/* 248 */       if (priority[i] == null) {
/* 249 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 252 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.StandardTypeAct
 * JD-Core Version:    0.6.2
 */