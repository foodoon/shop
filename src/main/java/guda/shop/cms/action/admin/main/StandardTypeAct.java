 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.Standard;
/*     */ import guda.shop.y.StandardType;
/*     */ import guda.shop.cms.managrdMng;
/*     */ import guda.shop.cms.manager.Standa;
/*     */ import guda.shop.common.page.Pagination;
/* port guda.shop.common.page.SimplePage;
/*     */ im.shop.common.web.CookieUtils;
/*     */ import gudamon.web.ResponseUtils;
/*     */ import guda.shop.cy.Website;
/*     */ import guda.shop.core.web.SiteUt   */ import guda.shop.core.web.WebErrors;
/*   rt java.util.Arrays;
/*     */ import java.util     */ import java.util.Set;
/*     */ import vlet.http.HttpServletRequest;
/*   rt javax.servlet.http.HttpServlet
/*     */ import org.apache.com.StringUtils;
/*     */ import org.slf4j.Logger;
/*      org.slf4j.LoggerFactory;
/*     */ import org.springframns.factory.annotation.Autowired;
/*     */ import org.mework.stereotype.Controller;
/*   rt org.springframework.ui.ModelMap;
/*    t org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*  ntroller
/*     */ public class StandardTypeAct
/*     */ {
   private static final Logger log = LoggerFactoryr(StandardTypeAct.class);
/*     */ 
/*     */   @Autowired
/*     */   pandardTypeMr;
/*     */ 
/*     *wired
/*     */   private StandardMng sg;
/*     */
/*  40 */   @RequestMapping({"/standardType/v_list.do"})
/*     */   public String list(Inteo, HttpServt request, ModelMap modination pagination = this.manager.getPage(Simpn(pageNo),*/       CookieUtils.ge(request));
/*  42 */     model.addAttribute(on", pagination);
/*  43 */     return "standardType/list"; }
/*       */   @RequestMapping({"/standardType/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model)
/*     */   {
/*  48 */     return "standardType/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/standardType/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequst, ModelMa{
/*  53 */     Website web = SiteUtils.getWeb(request);/     WebErrors errors = validateEdit(id, request);
/*  55 */     if (erroors()) {
/*  56 */       return errors.showErrorPage(mo    */     }
/    Standarn = this.manager.findById(id);
/*  59 */     List standar.standardMng.findByTypeId(id);
/*  60 */     model.addAttribute("standardType", bean);
/*  61 */     model.addAttribute("standards", standards);
/*  62 */     return "standardType/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/standardType/o_save.do"})
/*     */   public StrinandardType bean, String[] itemName, String[] itemColor, Integer[] itemPriority, HttpServletRequest request, ModelMap model)
/*     */   {
/*  69 */     WebErrors errors = validateSave(bean, request);
/*  70 */     if (errors.hasErrors()) {
/*  71 */       return errors.showErrorPage(model);
/*     /*  73 */     is.manager.);
/*  74 */     addStandard(bean, itemName, itemColor, ity);
/*  75 */     log.info("save StandardType id={}", bean.getId());
/*  76 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @Reques{"/standardType/o_update.do"})
/*     */   public String update(StandardType bean, Long[] itemId, String[] itemName, String[] itemColor, Integer[] itemPriority, IntegerHttpServletRequest request, ModelMap model)
/*     */   {
/*  83 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  84 */     if (errors.hasErrors()) {
/*  85 */       return errors.showErrorPage(model);
/*     */     }
     bean = thr.update(be   */
/*  89 */     updateStandard(bean, itemId, itemName,r, itemPriority);
/*  90 */     log.info("update StandardType id={}.", bean.getId());
/*  91 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(rdType/o_priority.do"})
/*     */   public String priority(Integer pageNo, Long[] wids, Integer[] priority, HttpServletRequest request, ModelMap model)
/*     */   {
/*  97 */   rs errors = validatePriority(wids, priority, request);
/*  98 */errors.hasErrors()) {
/*  99 */       return errors.showErrorPage(model);
/*     */     }
/* 101 */     this.manager.updatePriority(wids, priority);
/* 102 */     model.addAttribute("message", "global.success" */     returneNo, reques;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/sta/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*      111 */     WebErrors errors = validateDelete(ids, request);
/* 112 */     if (errors.hasErrors()) {
/* 113 */       return errors.showErrorPage(model);
/*     */     }
/* 115 */    Type[] beans = this.manager.deleteByIds(ids);
/* 116 */     for (StandardType bean : beans) {
/* 117 */       log.info("delete StandardType id={}", bean.getId());
/*     */     }
/* 119 */ n list(pageNo, model);
/* }
/*     */ 
/*     */   private void addStandard(Standard, String[] itemName, String[] itemColor, Integer[] itemPriority)
/*     */   {
/* 124 */     if (itemName {
/* 125 */       int i = 0; for (int len = itemName.length; i < len; i++)
/* 126 */         if (!StringUtils.isBlank(itemName[i])) {
/* 127 */           Standard item ndard();
/* 128 */           item.setName(itemName[i]);
/* 129 */           item.setColor(itemColor[i]);
/* 130 */           item.setPriority(itemPriority[i]);
/* 131 */           item.setType(bean);
/          this.standardMng.save(item);
/*     */         }
/*     **     */   }
/
/*     */ tMapping({"/standardType/v_check_field.do"})
/*     */   public String checkUsername(String field, HttpServletRequest rttpServletResponse response)
/*     */   {
/* 141 */     if ((StringUtils.isBlank(field)) || (this.manager.getByField(field) != null))
/* 142 */       ResponseUtils.renderJson(response, "false");
/*     */     else {
/* 144 */       ResponseUtils.renderJson(response, "true");
/*     */     }
/* 146 */     return null;
/*     */   }
/*     */ 
/*     */   private void updateStandard(StandardType bean, Long[] itemId, String[] itemName, String[] itemColor, Integer[] itemPrio* 150 */     Set setetStandardSet();/     if (iteml) {
/* 152 for (Standard s : set) {
/* 153 */         if (!Arrays.asList(intains(s.getId()))
/* 154 */           this.standardMng.deleteById(s.getId());
/*     */       }
/*     */     }/     else {
/* 158 */       for (Standard s : set) {
/* 159 */         this.standardMng.deleteById(s.getId());
/*     */       }
/*     */     }
/*     */ 
/* 163 */ temName != null) {
/* 164 */       int i = 0; for (int len = itemName.length; i <)
/* 165 */         if (!StringUtils.isBlane[i]))
/* 166     if ((itull) && (i < itemId.length)) {
/* 167 */             Standard item = this.standardMng.findById(itemId[i]);
/* 168 */             item.setName(itemName[i]);
/* 169 */             item.setColor(itemColor[i]);
/* 170 */             item.setPriority(itemPriority[i]);
/* 171 */             item.setType(bean);
/* 172 */             this.standardMng.update(item);
/*     */           } else {
/            Standa new Standard();/             item.setName(itemName[i]);
/* 176 */             item.setColor(itemColor[i]);
/* 177 */             item.sy(itemPriority[i])*/             ipe(bean);
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
/* 194 */     if (vldExist(id, web.errors)) {
/* 195 */       return errors;
/*     */     }
/* 197 */     return errors;
/*     */   }
/*     */
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 201 */     WebErrors errors = WebErrors.create(request);
/* 202 */     Website web = SiteUtils.getWeb(request);
/* 203 */     if (vldEweb.getId(), errors)) */       return *     */     }/     retur
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpSerst request) {
/* 210 */     WebErrors errors = WebErrors.create(request);
/* 211 */     Website web = SgetWeb(request */     errty(ids, "ids");
/* 213 */     for (Long id : ids) {
/* 214 */       vldExist(id, web.getId(), errors);
/*     */     }
/* 216 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 220 */     if (errors.ifNull() {
/* 221 */       return true;
/*     */   3 */     Standntity = thi.findById(id);
/* 224 */     if (errors.ifNotExist(entity, StandardType.class, id)) {
/* 225 */       return true;
/*     */     }
/* 227 */     return false;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest re     */   {
/* 232 */     Website web = SiteUeb(request);
/    WebErro = WebErrors.create(request);
/* 234 */     if (errors.ifEmpty(wids, "ids")) {
/* 235 */       return errors;
/*     */     }
/* 237 */     if (errors.ifEmpty(priority, "priority")) {
/* 238 */       return errors;
/*     */     }
/* 240 */     if (wids.length != priority.length) {
/* 241 */       errors.addErrorString("ids lengtals priority length");
/* 242 */       return/*     */     */     int r (int len = wids.length; i < len; i++) {
/* 245 */       if (vldExist(wids[i], web.getId(), errors)) {
/* 246 */         return errors;
/*     */       */       if (priority[i] == null) {
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