 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.ShopChannel;
/*     */ import guda.shop.er.ShopChannelMng;
/*     */ import guda.shop.common.weeUtils;
/*     */ import guda.shop.core.entity.Websit */ import guda.shop.core.web.SiteUtils;
/*      guda.shop.core.web.WebErrors;
/*     */ importl.List;
/*     */ import javax.servlet.ServletC*     */ import javax.servlet.httvletRequest;
/*     */ import javax.servlet.httvletResponse;
/*     */ import org.apache.commons.lang.Ss;
/*     */ import org.slf4j.Logger;
/*     */ import oroggerFactory;
/*     */ import org.springframework.beay.annotation.Autowired;
/*     */ i.springframework.stereotype.Controller;
/*mport org.springframework.ui.ModelMap;
/*     */ import org.springframewond.annotation.RequestMapping;
/*     */ import org.springfrab.context.ServletContextAware;
/*     */
/*     *ller
/*     */ public class ShopChannelAct
/*     */   implements Servletare
/*     */ {
/*  32 */   private static final Logger log = LoggerFaLogger(Shopt.class);
/*     */ 
/  @Autowired
/*     */   private ShopC manager;
/*     */   private ServletContexContext;
/*     */
/*  36 */   @RequestMapping({"/channel/v_left.do"})
/*     */   public String left() "channel/le     */
/*     */ 
/* @RequestMapping({"/channel/v_tree.do"})
/*  ublic String tree(String root, HttpServletRequest rttpServletResponse response, ModelMap model)
/*     */   {
/*   Website web = SiteUtils.getWeb(request);
/*  43 */     log.ee path={}"/*     */  n isRoot;
/*     */     boolean isRoot;
/*  46 */   ringUtils.isBlank(root)) || ("source".equals(root)))
/*  47 */       isRoot = true;
/*     */     else {
/*  49 */     = false;
/*     */     }
     model.addAttribute("isRoot", Boolean.valueOf(isRoot));
/*  52 */     WebErro = validateTree(root, request)*/     if (errors.hasErrors()) {
/*  54 */       log.error((String)errors.getErrors().get(0));
/*  55 */       ResponseUtils.renderJson, "[]");
/*  56 */       return null;
/*     */     }/     List list;
/*     */     List list;
/*  59 */     if (isRoot) {
/*  60 */       list = this.manager.getTopList(web.getId());
/*     */     } else {
/*  62 */       Long rootId = Long.valueOf(root);
/*  63 */       list = this.manager.getChildList(web.getId(), rootId);
/*     */     }
/*  65 */     model.addAttribute("list", list */     responser("Cache-Control", "no-ca  67 */     response.setContentType("text/json;charset=UTF-8");
/*  68 */     return "channel/tree";
/*     */   }/
/*     */   @RequestMapping({"/channel/v_list.do"})
/*     */   public String list(Long root, HttpServletRequest request, ModelMap model) {
/    Website web = SiteUtils.getWeb(request);
/*     */     List list;
/*     */     List list;
/*  75 */     if (root == null)
/*  76 */       list = this.manager.getTopList(web.getId());
/*     */     else {
/*  78 */       list nager.getChildgetId(), ro   */     }
/*  80 */     model.addAttribute("root",*  81 */     model.addAttribute("list", list);
/*  82 */     return "channel/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/chad.do"})
/*     */   publiadd(Long root, Integer type, HttpServletRequest request, ModelMap model)
/*     */   {
/*  88 */     Website web = SittWeb(request);
/*  89 */     ShopChannel parent = null;
/*  90 */     if (root != null) {
       parent = this.manager.findById(root);
/*  92 */       model.addAttribute("parent", parent);
/*  93 */       model.addAttribute("root", root);
     }
/*     6 */     SthannelDirRel = ShopChannel.getChannelTplDirRel(web)*/     String realChannelDir = this.servletContext.getRealPath(tplChannelDirRel);
/*  98 */     StrannelPath = tplChannelDirRel.substring(web.getTemplateRel().length());
/*  99 */     String[] channelTpls = ShopChannel.getChannelTpls(type, realChannelDir, relChannelPath);
/*     */ 
/* 101 */     String tplContentDirRel = ShopChannel.getContentTplDirRel(web);
/* 102 */     String realContentDir = tetContext.getReaContentDirRel);
/* 103 */     String relContentPath = tplContentDirRel.substring(web.getTemplateRel().length());
/* 104 */     String[] contentTpls = ShopChannel.getContentTpls(type, realContentDir, relContentPath);
/*     */ 
/* 106 */     model.addAttribute("channelTpls", channelTpls);
/* 107 */     model.addAttribute("contentTpls", contentTpls);
/* 108 */     model.addAttrpe", type);
/* 109 */     return "channel/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest request, ModelMap model) {
/* 114 */     Website web = SiteUtils.getWeb(request);
/* 115 */     WebErrors errors = validateEdit(id, request);
/* 116 */     if (errors.hasErrors()) {
/* 117 */   n errors.showErrorPage(model);
/*     */     }
/* 119 */     ShopChannel shopChannel = this.manager.findById(id);
/* 120 */     Integer type = shopChannel.getType();
/*     */ 
/* 122 */     String tplChannelDirRel = Sh.getChannelTplb);
/* 123 ring realChannelDir = this.servletContext.getRealPatnelDirRel);
/* 124 */     String relChannelPath = tplChannelDirRel.substring(web.getTemplateRel().length());
/* 125 */     String[] channelTpls = ShopChannel.getChannelTpls(type, realChannelDir, relChannelPath);
/*     */ 
/* 127 */     String tplContentDirRel = ShopChannel.getContentTplDirRe* 128 */     String realContentDir = this.servletContext.getRealPath(tplContentDirRel);
/* 129 */     String relContentPath = tplContenubstring(web.getTemplateRel().length());
/* 130 */     String[] contentTpls = ShopChannel.getContentTpls(type, realContentDir, relContentPath);
/* 131 */     model.addAttribute("channelTpls", channelTpls);
/* 132 */     model.addAttribute("contentTpls", contentTpls);
/*     */ 
/* 134 */     List parentList = this.manager.getListForParent(web.getId(), 
/* 135 */       shopChad());
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
/* 146 */       rers.showErrorPage(model);
/*     */     }
/* 148 */     bean = this.manager.save(bean, root, content);
/* 149 */     log.info("save ShopChannel id={}", bean.getId());
/* 150 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_update.do  */   public date(ShopChn, Long parentId, String content, HttpServletRequest ModelMap model)
/*     */   {
/* 156 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 157 */     if asErrors()) {
/* 158 */       return errors.showErrorPage(model);
/*     */     }
/* 160 */     bean = this.manager.update(bean, parentId, content);
/* 161 */     log.info("upChannel id={}.", bean.getId());
/* 162 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_delete.do"})
/*     */   public String del] ids, HttpSerst request, model) {
/* 167 */     WebErrors errors = validateDelrequest);
/* 168 */     if (errors.hasErrors()) {
/* 169 */       return errors.showErrorPage(model);
/*     */     }
/* 171 *pChannel[] beans = this.manager.deleteByIds(ids);
/* 172 */     for (ShopChannel bean : beans) {
/* 173 */       log.info("delete ShopChannel id={}", bean.getId());
/*     */     */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_priority.do"})
/*     */   public String priority(Long[] wids, Integer[] priority, IntegerHttpServletReqest, ModelM
/*     */   {
/* 181 */     WebErrors errors = validay(wids, priority, request);
/* 182 */     if (errors.hasErrors()) {
/* 183 */       return errors.showErrorPage(model);
/*     */     }
/* 185 */     this.manager.updatePriority(wids, priority);
/* 186 */     model.addAttribute("message", "global);
/* 187 */     return list(null, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {/     WebErrors errors = WebErrors.create(request);
/* 193  (errors.ifEmp"wids")) {
       return errors;
/*     */     }
/* 196 */     if (Empty(priority, "priority")) {
/* 197 */       return errors;
/*     */     }
/* 199 */     if (wids.length != priority.length) */       errors.addErrorString("wids length not equals priority length");
/* 201 */       return errors;
/*     */     }
/* 203 */     int i = 0; for (int len = wids.length; i < len;* 204 */       vldExist(wids[i], errors);
/* 205 */       if (priority[i] == null) {
/* 206 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 209 */     rers;
/*     */   */
/*   ivate void vldExist(Long id, WebErrors errors) {
/* 213 */     if (errors.hasErrors()) {
/* 214 */       ret   */     }
/* 216 */     ShopChannel entity = this.manager.findById(id);
/* 217 */     errors.ifNotExist(entity, ShopChannel.class, id);
/*     */   }
/* *     */   private WebErrors validateSave(ShopChannel bean, Long parentId, HttpServletRequest request)
/* {
/* 222 */     WebErrors errors = WebErrors.create(request);
/* 223 */     Website web = SiteUtils.getWeb(request);
/* 224 */     bean.setWebsite(web);
/* 225 */     if ((parentI) &&
/* 226 */       (vldExist(parentId, web.getId(), errors))) {
/* 227 */       return errors;
/*     */     }
/*     */ 
/* 230 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(LttpServletRequest {
/* 234 */     WebErrors errors = WebErrors.quest);
/* 235ebsite web ls.getWeb(request);
/* 236 */     if (vldExist(id, web.getId(), errors)) {
/* 237 */       return errors;
/*     */     }
/* 23return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 243 */     WebEors = WebErrorrequest);
/    Website web = SiteUtils.getWeb(request);
/* 245 */     if (vldExist(id, web.getId(), errors)) {
/* 2   return errors;
/*     */     }
/* 248 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 252 */     WebErrors errors = WebErrors.create(request);
/* 253 */     Website web = SiteUtils.getWeb(request);
/* 254 *ors.ifEmpty(ids,/* 255 */     for (Long id : ids) {
/* 2   vldExist(idId(), error  */     }
/* 258 */     return errors;
/*     */   }
/*     */
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 262 */     if (errors.ifNull(id, "id")) {
/* 263 */       return true;
/*     */     }
/* 265 */     ShopChannel entity = this.manager.fi);
/* 266 */     if (errors.ifNotExist(entitynnel.class, id67 */      rue;
/*     */     }
/* 269 */     if (!entity.getWebsite().getId().equals(webId)) {
/* 270 */       errors.notInWeb(ShopChannel.class, id);
/* 271 */       return true;
/*     */     }
/* 273 */     return false;
/*     */   }
/*     */ 
/*     */   private WebErrors validateTree(Str HttpServletRequest request) {
/* 277 */      errors = WebEate(request */
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