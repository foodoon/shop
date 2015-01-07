 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.ShopAdmin;
/*     */ import guda.shop.er.ShopAdminMng;
/*     */ import guda.shop.common.pation;
/*     */ import guda.shop.common.page.Simple    */ import guda.shop.common.web.CookieUtils;
/* port guda.shop.common.web.ResponseUtils;
/*     */ da.shop.core.entity.Admin;
/*     */ import guda.shopity.Website;
/*     */ import guda.shop.core.mminMng;
/*     */ import guda.shop.core.manager./*     */ import guda.shop.core.manager.UserMng;
/import guda.shop.core.web.SiteUtils;
/*     */ im.shop.core.web.WebErrors;
/*     */ import java.uet;
/*     */ import java.util.List;
/*     */ va.util.Set;
/*     */ import javax.servlet.httvletRequest;
/*     */ import javax.ttp.HttpServletResponse;
/*     *org.apache.commons.lang.StringUt   */ import org.slf4j.Logger;
/*     */ import org.slf4actory;
/*     */ import org.springframework.beans.factorion.Autowired;
/*     */ import org.springframework.stController;
/*     */ import org.spwork.ui.ModelMap;
/*     */ import org.sprork.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
 public class ShopAdminAct
/*     */ {
/*  40 */   private sal Logger log = LoggerFactory.getLogger(ShopAdminA;
/*     */ 
/*     */   @Autowired
/*     */   private ShopAdminMng mana   */
/*  Autowired
/*     */   serMng userMng;
/*     */
/*     */ired
/*     */   protected RoleMng roleMng;
/*     */
/*     */   @Autowired
/*     */   protected AdminMng;
/*    45 */   @RequestMappiin/v_list.do"})
/*     */   public String ger pageNo,letRequest request, Model) { Pagination pagination = this.maPage(SiteUtbId(request), SimplePageNo), CookieUtils.getPageSize(request))*/     modeibute(pagination);
/*   return "admin/list"; }
/*     */ 
/*    questMapping({"/admin/v_add.do"})
/*     */   public String aap model)
/*     */   {
/*  52 */     List roleList = this.roleMng.getList();
/*  53 */     model.addAttribute("roleList", roleList);
/*  54 */     return "admin/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin/v_edit.do"})
/*     */   public String edit(Long id, HttpServletRequest requestp model) {
     WebErrors errors = validateEdit(id, request)*/     if (errors.hasErrors()) {
/*  61 */     errors.showErrorPage(model);
/*     */     }
/*  63 */     List roleList = this.roleMng.getList();
/*  64 */     model.addAttribute("roleList", roleList);
/*   model.addAtadmin", thi.findById(id));
/*  66 */     model.addAttribute(" this.manager.findById(id).getAdmin().getRoleIds());
/*  67 */     return "admin/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin/o_save.do"})
/*     */   public String save(ShopAdmin bean, String username, String passlean viewonlyAdmin, String email, Boolean disabled, Integer[] roleIds, HttpServletRequest request, ModelMap model)
/*     */   {
/*  74 */     WebErrors errors = validateSave(bean, request);
/*  75 */     if (errors.hasErrors()) {
/*  76 */       return errors.showErrorPage(model);
/*     */     }
/*  78 */     Website web = .getWeb(reques9 */     be.manager.register(username, password, viewonlyAdmi request.getRemoteAddr(), disabled.booleanValue(), web.getId(), bean);
/*  80 */     this.adminMng.addRole(bean.getAdmin(), roleIds);
/*  81 */     log.info("save ShopAdmin id={}", bean.getId());
/*   return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin/o_update.do"})
/*     */   public String update(ShopAdmin bean, String passlean viewonlyAdmin, String email, Boolean disabled, Integer[] roleIds, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  89 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  90 */     if (errors.hasErrors()) {
/*  91 */       return errors.showErrorPage(model);
/*     */     }
/*  93 */     bean = this.manager.update(bean, password, disabled, emonlyAdmin);
/*   this.admateRole(bean.getAdmin(), roleIds);
/*  95 */     logdate ShopAdmin id={}.", bean.getId());
/*  96 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin/o_delete.do"})
/*     */   public String delete(s, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 102 */     WebErrors errors = validateDelete(ids, request);
/* 103 */     if (errors.hasErrors()) */       return errors.showErrorPage(model);
/*     */     }
/* 106 */     ShopAdmin[] beans = this.manager.deleteByIds(ids);
/* 107 */     for (ShopAdmin bean : beans) {
/* 108 */       log.info("delete ShopAdmin id={}", bean.getId());
/*     */     }
/* 110 */     return list(pageNo, rodel);
/*          */
/* @RequestMapping({"/admin/v_check_username.do"})
/* public String checkUsername(String username, HttpServletRequest request, HttpServletResponse response)
/* {
/* 116 */     if ((StringUtils.isBlank(username)) || (this.userMng.usernameExist(username)))
/* 117 */       ResponseUtils.renderJson(response, "false");
/*     */   /* 119 */       ResponseUtils.renderJson(response, "true");
/*     */     }
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin/v_check_email.do"})
/*     ic String checkEmail(String email, HttpServletRequest request, Httpsponse respons */   {
/*   if ((StringUtils.isBlank(email)) || (this.userMng.emailExi))
/* 128 */       ResponseUtils.renderJson(response, "false");
/*     */     else {
/* 130 */       ResponseUtils.n(response, "true");
/*     */     }
/* 132 */     return null;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(ShopAdmin bean, HttpServletRequest r*     */   {
/* 137 */     WebErrors errors = WebErrors.create(request);
/* 138 *urn errors;
/*     */   }
/*     */ 
/*    vate WebErrorsEdit(Long irvletRequest request) {
/* 142 */     WebErrors errors = .create(request);
/* 143 */     errors.ifNull(id, "id");
/* 144 */     vldExist(id, errors);
/* 145 */     rers;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request)
/*     */   {
/* 150 */     WebErrors errors = Wcreate(request);
/* 151 */     if (vldExist(id, errors)) {
/* 152 */       return/*     */     }
/* 154 */     return errors*/   }
/*       */   privrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 158 */     WebErrors eebErrors.create(request);
/* 159 */     errors.ifEmpty(ids, "ids");
/* 160 */     for (Long id : ids) {/       vldExirors);
/*   }
/* 163 */     return errors;
/*     */   }
/*     */
/*     */   private boolean vldExist(Long id, WebErrors errors) {
/* 167 */     ShopAdmin entity = this.manager.findById(id);
/* 168 */     return errors.ifNotExist(entity, ShopAdmin.class,    */   }
/* *     */   et<String> handleperms(String[] perms) {
/* 172 */     Set permSet = new HashSet()*/
/* 174 */     for (String perm : perms) {
/* 175 */       String[] arr = perm.split("@");
/* 176 */       int i = 0; for (int len = arr.length; i++) {
/* 177 */         permSet.add(arr[i])*/       }
/*   }
/* 180 turn permSet;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopAdminAct
 * JD-Core Version:    0.6.2
 */