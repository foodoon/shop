/*     */ package guda.shop.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.ShopChannel;
/*     */ import guda.shop.cms.manager.ShopChannelMng;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.SiteUtils;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.context.ServletContextAware;
/*     */ 
/*     */ @Controller
/*     */ public class ShopChannelAct
/*     */   implements ServletContextAware
/*     */ {
/*  32 */   private static final Logger log = LoggerFactory.getLogger(ShopChannelAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ShopChannelMng manager;
/*     */   private ServletContext servletContext;
/*     */ 
/*  36 */   @RequestMapping({"/channel/v_left.do"})
/*     */   public String left() { return "channel/left"; }
/*     */ 
/*     */ 
/*     */   @RequestMapping({"/channel/v_tree.do"})
/*     */   public String tree(String root, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  42 */     Website web = SiteUtils.getWeb(request);
/*  43 */     log.debug("tree path={}", root);
/*     */     boolean isRoot;
/*     */     boolean isRoot;
/*  46 */     if ((StringUtils.isBlank(root)) || ("source".equals(root)))
/*  47 */       isRoot = true;
/*     */     else {
/*  49 */       isRoot = false;
/*     */     }
/*  51 */     model.addAttribute("isRoot", Boolean.valueOf(isRoot));
/*  52 */     WebErrors errors = validateTree(root, request);
/*  53 */     if (errors.hasErrors()) {
/*  54 */       log.error((String)errors.getErrors().get(0));
/*  55 */       ResponseUtils.renderJson(response, "[]");
/*  56 */       return null;
/*     */     }
/*     */     List list;
/*     */     List list;
/*  59 */     if (isRoot) {
/*  60 */       list = this.manager.getTopList(web.getId());
/*     */     } else {
/*  62 */       Long rootId = Long.valueOf(root);
/*  63 */       list = this.manager.getChildList(web.getId(), rootId);
/*     */     }
/*  65 */     model.addAttribute("list", list);
/*  66 */     response.setHeader("Cache-Control", "no-cache");
/*  67 */     response.setContentType("text/json;charset=UTF-8");
/*  68 */     return "channel/tree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_list.do"})
/*     */   public String list(Long root, HttpServletRequest request, ModelMap model) {
/*  73 */     Website web = SiteUtils.getWeb(request);
/*     */     List list;
/*     */     List list;
/*  75 */     if (root == null)
/*  76 */       list = this.manager.getTopList(web.getId());
/*     */     else {
/*  78 */       list = this.manager.getChildList(web.getId(), root);
/*     */     }
/*  80 */     model.addAttribute("root", root);
/*  81 */     model.addAttribute("list", list);
/*  82 */     return "channel/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_add.do"})
/*     */   public String add(Long root, Integer type, HttpServletRequest request, ModelMap model)
/*     */   {
/*  88 */     Website web = SiteUtils.getWeb(request);
/*  89 */     ShopChannel parent = null;
/*  90 */     if (root != null) {
/*  91 */       parent = this.manager.findById(root);
/*  92 */       model.addAttribute("parent", parent);
/*  93 */       model.addAttribute("root", root);
/*     */     }
/*     */ 
/*  96 */     String tplChannelDirRel = ShopChannel.getChannelTplDirRel(web);
/*  97 */     String realChannelDir = this.servletContext.getRealPath(tplChannelDirRel);
/*  98 */     String relChannelPath = tplChannelDirRel.substring(web.getTemplateRel().length());
/*  99 */     String[] channelTpls = ShopChannel.getChannelTpls(type, realChannelDir, relChannelPath);
/*     */ 
/* 101 */     String tplContentDirRel = ShopChannel.getContentTplDirRel(web);
/* 102 */     String realContentDir = this.servletContext.getRealPath(tplContentDirRel);
/* 103 */     String relContentPath = tplContentDirRel.substring(web.getTemplateRel().length());
/* 104 */     String[] contentTpls = ShopChannel.getContentTpls(type, realContentDir, relContentPath);
/*     */ 
/* 106 */     model.addAttribute("channelTpls", channelTpls);
/* 107 */     model.addAttribute("contentTpls", contentTpls);
/* 108 */     model.addAttribute("type", type);
/* 109 */     return "channel/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model) {
/* 114 */     Website web = SiteUtils.getWeb(request);
/* 115 */     WebErrors errors = validateEdit(id, request);
/* 116 */     if (errors.hasErrors()) {
/* 117 */       return errors.showErrorPage(model);
/*     */     }
/* 119 */     ShopChannel shopChannel = this.manager.findById(id);
/* 120 */     Integer type = shopChannel.getType();
/*     */ 
/* 122 */     String tplChannelDirRel = ShopChannel.getChannelTplDirRel(web);
/* 123 */     String realChannelDir = this.servletContext.getRealPath(tplChannelDirRel);
/* 124 */     String relChannelPath = tplChannelDirRel.substring(web.getTemplateRel().length());
/* 125 */     String[] channelTpls = ShopChannel.getChannelTpls(type, realChannelDir, relChannelPath);
/*     */ 
/* 127 */     String tplContentDirRel = ShopChannel.getContentTplDirRel(web);
/* 128 */     String realContentDir = this.servletContext.getRealPath(tplContentDirRel);
/* 129 */     String relContentPath = tplContentDirRel.substring(web.getTemplateRel().length());
/* 130 */     String[] contentTpls = ShopChannel.getContentTpls(type, realContentDir, relContentPath);
/* 131 */     model.addAttribute("channelTpls", channelTpls);
/* 132 */     model.addAttribute("contentTpls", contentTpls);
/*     */ 
/* 134 */     List parentList = this.manager.getListForParent(web.getId(), 
/* 135 */       shopChannel.getId());
/* 136 */     model.addAttribute("parentList", parentList);
/* 137 */     model.addAttribute("shopChannel", shopChannel);
/* 138 */     return "channel/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_save.do"})
/*     */   public String save(Long root, ShopChannel bean, String content, HttpServletRequest request, ModelMap model)
/*     */   {
/* 144 */     WebErrors errors = validateSave(bean, root, request);
/* 145 */     if (errors.hasErrors()) {
/* 146 */       return errors.showErrorPage(model);
/*     */     }
/* 148 */     bean = this.manager.save(bean, root, content);
/* 149 */     log.info("save ShopChannel id={}", bean.getId());
/* 150 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_update.do"})
/*     */   public String update(ShopChannel bean, Long parentId, String content, HttpServletRequest request, ModelMap model)
/*     */   {
/* 156 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 157 */     if (errors.hasErrors()) {
/* 158 */       return errors.showErrorPage(model);
/*     */     }
/* 160 */     bean = this.manager.update(bean, parentId, content);
/* 161 */     log.info("update ShopChannel id={}.", bean.getId());
/* 162 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_delete.do"})
/*     */   public String delete(Long[] ids, HttpServletRequest request, ModelMap model) {
/* 167 */     WebErrors errors = validateDelete(ids, request);
/* 168 */     if (errors.hasErrors()) {
/* 169 */       return errors.showErrorPage(model);
/*     */     }
/* 171 */     ShopChannel[] beans = this.manager.deleteByIds(ids);
/* 172 */     for (ShopChannel bean : beans) {
/* 173 */       log.info("delete ShopChannel id={}", bean.getId());
/*     */     }
/* 175 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_priority.do"})
/*     */   public String priority(Long[] wids, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 181 */     WebErrors errors = validatePriority(wids, priority, request);
/* 182 */     if (errors.hasErrors()) {
/* 183 */       return errors.showErrorPage(model);
/*     */     }
/* 185 */     this.manager.updatePriority(wids, priority);
/* 186 */     model.addAttribute("message", "global.success");
/* 187 */     return list(null, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {
/* 192 */     WebErrors errors = WebErrors.create(request);
/* 193 */     if (errors.ifEmpty(wids, "wids")) {
/* 194 */       return errors;
/*     */     }
/* 196 */     if (errors.ifEmpty(priority, "priority")) {
/* 197 */       return errors;
/*     */     }
/* 199 */     if (wids.length != priority.length) {
/* 200 */       errors.addErrorString("wids length not equals priority length");
/* 201 */       return errors;
/*     */     }
/* 203 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 204 */       vldExist(wids[i], errors);
/* 205 */       if (priority[i] == null) {
/* 206 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 209 */     return errors;
/*     */   }
/*     */ 
/*     */   private void vldExist(Long id, WebErrors errors) {
/* 213 */     if (errors.hasErrors()) {
/* 214 */       return;
/*     */     }
/* 216 */     ShopChannel entity = this.manager.findById(id);
/* 217 */     errors.ifNotExist(entity, ShopChannel.class, id);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(ShopChannel bean, Long parentId, HttpServletRequest request)
/*     */   {
/* 222 */     WebErrors errors = WebErrors.create(request);
/* 223 */     Website web = SiteUtils.getWeb(request);
/* 224 */     bean.setWebsite(web);
/* 225 */     if ((parentId != null) && 
/* 226 */       (vldExist(parentId, web.getId(), errors))) {
/* 227 */       return errors;
/*     */     }
/*     */ 
/* 230 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 234 */     WebErrors errors = WebErrors.create(request);
/* 235 */     Website web = SiteUtils.getWeb(request);
/* 236 */     if (vldExist(id, web.getId(), errors)) {
/* 237 */       return errors;
/*     */     }
/* 239 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 243 */     WebErrors errors = WebErrors.create(request);
/* 244 */     Website web = SiteUtils.getWeb(request);
/* 245 */     if (vldExist(id, web.getId(), errors)) {
/* 246 */       return errors;
/*     */     }
/* 248 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 252 */     WebErrors errors = WebErrors.create(request);
/* 253 */     Website web = SiteUtils.getWeb(request);
/* 254 */     errors.ifEmpty(ids, "ids");
/* 255 */     for (Long id : ids) {
/* 256 */       vldExist(id, web.getId(), errors);
/*     */     }
/* 258 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 262 */     if (errors.ifNull(id, "id")) {
/* 263 */       return true;
/*     */     }
/* 265 */     ShopChannel entity = this.manager.findById(id);
/* 266 */     if (errors.ifNotExist(entity, ShopChannel.class, id)) {
/* 267 */       return true;
/*     */     }
/* 269 */     if (!entity.getWebsite().getId().equals(webId)) {
/* 270 */       errors.notInWeb(ShopChannel.class, id);
/* 271 */       return true;
/*     */     }
/* 273 */     return false;
/*     */   }
/*     */ 
/*     */   private WebErrors validateTree(String path, HttpServletRequest request) {
/* 277 */     WebErrors errors = WebErrors.create(request);
/*     */ 
/* 281 */     return errors;
/*     */   }
/*     */ 
/*     */   public void setServletContext(ServletContext servletContext)
/*     */   {
/* 289 */     this.servletContext = servletContext;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopChannelAct
 * JD-Core Version:    0.6.2
 */