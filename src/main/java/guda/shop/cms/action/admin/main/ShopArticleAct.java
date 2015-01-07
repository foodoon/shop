 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.ShopArticle;
/*     */ import guda.shop.y.ShopChannel;
/*     */ import guda.shop.cms.managticleMng;
/*     */ import guda.shop.cms.manager.ShopCh
/*     */ import guda.shop.common.page.Pagination;
/* port guda.shop.common.page.SimplePage;
/*     */ im.shop.common.web.CookieUtils;
/*     */ import gudamon.web.ResponseUtils;
/*     */ import guda.shop.cy.Website;
/*     */ import guda.shop.core.web.SiteUt   */ import guda.shop.core.web.WebErrors;
/*   rt java.util.List;
/*     */ import javax.servlttpServletRequest;
/*     */ import javax.servlttpServletResponse;
/*     */ imppache.commons.lang.StringUtils;
/*     */ import org.slf;
/*     */ import org.slf4j.LoggerFactory;
/*     */ imppringframework.beans.factory.annotation.Autowired;
/* port org.springframework.stereotypeer;
/*     */ import org.springframework.up;
/*     */ import org.springframework.web.bind.annotation.RequestMappin */
/*     */ @Controller
/*     */ public class ShopArticl   */ {
/*  36 */   private static final Logger lorFactory.getLogger(ShopArticleAct.class);
/*     */ 
/*     */   @Autowir */   privaannelMng channelMng;
/
/*     */   @Autowired
/*     */   prpArticleMng manager;
/*     */ 
/*  40 */   @RequestMapping({"/article/v_left.do"})
/*     */   public St() { return/left"; }
/*     */
/*/*     */   @RequestMapping({"/article/v_tree.d   */   pubg tree(String root, Httequest request, HttpServletResponse responsep model)
/*     */   {
/*  55 */     Website web = SiteUtils.geest);
/*  56 */     log.debug("tree path={}", root);
/*     olean isRoo */     booot;
/*  59 */     if ((StringUtils.isBlank(root)) ||".equals(root)))
/*  60 */       isRoot = true;
/*     */     else {
/*  62 */       isRoot = false;
/*     */     }
/    model.addAttribute("isRoot", Boolean.valueOf(isRoot));
/*  65 */     WebErrors errors = validateTree(root, reque66 */     if (errors.hasErrors 67 */       log.error((String)errors.getErrors().get(0));
/*  68 */       ResponseUtils.renderJson(response, "[]");
/*  69 */       re;
/*     */     }
/*     */     List list;
/*     */ list;
/*  72 */     if (isRoot) {
/*  73 */       list = this.channelMng.getTopList(web.getId());
/*     */     } else {
/*  75 */       Long rootId = Long.valueOf(root);
/*  76 */       list = this.channelMng.getChildList(web.getId(), rootId);
/*     */     }
/*  78 */     model.addAttribute("list", list);
/*  79 */     response.setHche-Control", "n;
/*  80 */     response.tType("text/json;charset=UTF-8");
/*  81 */     return "article/tree";
/*     */   }
/*     */ 
/*     */   @RequestM/article/v_list.do"})
/*     */   public String list(Long cid, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  87 */ te web = SiteUtils.getWeb(request);
/*  88 */     Pagination pagination = this.manager.getPage(cid, web.getId(), SimplePage.cpn(pageNo),
/*  89 */       CookieUtils.getPageSize(request));
/*  90 */     model.addAttribute("paginatination);
/*   if (cid !=/*  92 */       ShopChannel channel = this.channelMnd(cid);
/*  93 */       model.addAttribute("channel", channel);
/*     */     }
/*  95 */     model.ate("cid", cid);
/*  96 */     return "article/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/article/v_add.do"})
/*     */   public String add(Long cid, HttpServletRequest request, ModelMap model) {
/* 101 */     Website web = SiteUtils.getWeb(request);
/* 102 */     List channelList = this.channelMng.getArticleList(
/* 103 */       web.getId());
/* 104 */     model.addAttribute("channelList", channelList);
/* 105 */    != null) {
/* 106 */       model.addAttribute("cid", cid);
/*     */     }
/* 108 */     return "ar";
/*     */   */
/*    questMapping({"/article/v_edit.do"})
/*     */   pung edit(Long id, HttpServletRequest request, ModelMap model) {
/* 113 */     Website web = SiteUtils.getWeb(request);
/* 114 */     WebErrors errors = validateEdit(id, request);
/* 115 */     if (errors.hasErrors()) {
/* 116 */       return errors.showErrorPage(model);
/*     */     }
/* 118 */     List articleChannelList = this.channelMng.getArticleList(
/* 119 */       web.g/* 120 */     model.addAttribute("articleChannelListeChannelList);/     modelbute("shopArticle", this.manager.findById(id));
/* 1 return "article/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/article/o_save.do"})
/*     */   public String save(ShopArticle bean, Long channelId, String content, HttpServletRequest request, ModelMap model)
/*     */   {
/* 128 */     WebErrors errors = validateSave(bean, c request);
/* 129 */     if (errors.hasErrors()) {
/* 130 */       return errors.showErrorPage(model);
/*     */     }
/* 132 */     bean = this.manager.save(bean, channelId, content);
/* 133 */     log.info("save ShopArticle id={}", bean.getId());
/* 134 */     return "redirect:v_list.do";
/*     */   }
/
/*     */   @pping({"/arpdate.do"})
/*     */   public String update(ShopArt, Long channelId, String content, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 140 */     Webrors = validateUpdate(bean.getId(), channelId, request);
/* 141 */     if (errors.hasErrors()) {
/* 142 */       return errors.showErrorPage(model);
/*     */     }
/* 144 */     is.manager.update(bean, channelId, content);
/* 145 */     log.info("update ShopArticle id={}.", bean.getId());
/* 146 */     return list(null, pageNo, request, model);
/*     */   }
/*       */   @Reque({"/article.do"})
/*     */   public String delete(Long[] ids, IneNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 152 */     WebErrors errors = validateDelete(ids, request);
/* 153 */     if asErrors()) {
/* 154 */       return errors.showErrorPage(model);
/*     */     }
/* 156 */     ShopArticle[] beans = this.manager.deleteByIds(ids);
/* 157 */     for (ShopArticle bean : be 158 */       log.info("delete ShopArticle id={}", bean.getId());
/*     */     }
/* 160 */     return list(null, pageNo, request, model);
/*     */   }
/*     */
/*     */   private WebErrors validateSave(Se bean, Long c HttpServlerequest)
/*     */   {
/* 165 */     WebErrors errors rs.create(request);
/* 166 */     Website web = SiteUtils.getWeb(request);
/* 167 */     bean.setWebsite(168 */     if (errors.ifNull(channelId, "channelId")) {
/* 169 */       return errors;
/*     */     }
/* 171 */     ShopChannel channel = this.channelMng.findById(chann 172 */     if (!channel.getWebsite().getId().equals(web.getId())) {
/* 173 */       errors.notInWeb(ShopChannel.class, channelId);
/* 174 */       return errors;
/*     */     }
/* 176 */     retur
/*     */   }
/*     */
/*     */   private WebErrors validateEdit(LongServletRequest {
/* 180 *Errors errors = WebErrors.create(request);
/* 181 */     Website web = SiteUtils.getWeb(request);
/* 182  (vldExist(id, web.getId(), errors)) {
/* 183 */       return errors;
/*     */     }
/* 185 */     return errors;
/*     */   }
/*     */
/*     */   private WebErrors validateUpdate(Long id, Long channelId, HttpServletRequest request)
/*     */   {
/*   WebErrors errors = WebErrors.create(request);
/* 191 */     Website web = SiteUtils.getWeb(request);
/* 192 */     if (vldExist(id, web.getId(), errors)) {
/* 193 */       return errors;
/*     */     }
/* 195 */     ShopChannel channel = this.channelMId(channelId);
/* 196 */     if (!channel.get.getId().equalId())) {
/*     errors.notInWeb(ShopChannel.class, channelId);
/* 198 */       return errors;
/*     */     }
/* 200 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 204 */     WebErrors errors = WebErrors.quest);
/* 205 */     Website web = SiteUtilsequest);
/* 20errors.ifEm"ids");
/* 207 */     for (Long id : ids) {
/* 208 */       vldExist(id, web.getId(), errors);
/*   }
/* 210 */     return errors;
/*     */   }
/*     */
/*     */   private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 214 */     if (errors.ifNull(id, "id")) {
/* 215 */       return true;
/*   }
/* 217 */     ShopArticle entity = this.manager.findById(id);
/* 218 */     if (errors.ifNotExist(entity, ShopArticle.class, id)) {
/* 219 */       return true;
/*     */     }
/* 221 */     if (!entity.getWebsite().getId().equals(webId)) {
/* 222 */  rs.notInWeb(ShopArticle.class, id);
/* 223 */turn true;
/*   }
/* 225 turn false;
/*     */   }
/*     */ 
/*     */   private WebErrors validateTree(String path, HttpServletRequest request) {
/* 229 */     WebErrors errors = WebErrors.create(request);
/*     */ 
/* 233 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopArticleAct
 * JD-Core Version:    0.6.2
 */