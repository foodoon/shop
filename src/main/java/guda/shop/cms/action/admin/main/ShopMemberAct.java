 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.ShopMember;
/*     */ import guda.shop.er.ShopDictionaryMng;
/*     */ import guda.shop.cms.managmberGroupMng;
/*     */ import guda.shop.cms.manager.ShopMe/*     */ import guda.shop.cms.web.SiteUtils;
/*     *guda.shop.common.page.Pagination;
/*     */ im.shop.common.page.SimplePage;
/*     */ import gudamon.web.CookieUtils;
/*     */ import guda.shop.corWebsite;
/*     */ import guda.shop.core.manager.Us     */ import guda.shop.core.web.WebErrors;
/* port java.util.List;
/*     */ import javax.servlttpServletRequest;
/*     */ import org.apache.ang.StringUtils;
/*     */ importj.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*  ort org.springframework.beans.factory.annotation.Autow    */ import org.springframework.s.Controller;
/*     */ import org.springfri.ModelMap;
/*     */ import org.springframework.web.bind.annotation.Requg;
/*     */
/*     */ @Controller
/*     */ public class SAct
/*     */ {
/*  37 */   private static final L = LoggerFactory.getLogger(ShopMemberAct.class);
/*     */ 
/*     */   @
/*     */  ShopMemberGroupMng shroupMng;
/*     */ 
/*     */   @Auto    */   private UserMng userMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShopMemberMng ma     */
/* @Autowired
/*     */  ShopDictionaryMng shopDictionaryMng;
/*     */ 
/*  42 */  Mapping({"/list.do"})
/*     */   ring list(Integer pageNo, HttpServletequest, Model) { Pagination paginais.manager.getPage(SiteUtils.getWebId(reque 43 */     age.cpn(pageNo), CookiePageSize(request));
/*  44 */     model.addAttribute("pag pagination);
/*  45 */     return "member/list"; }
/*     */ /   @RequestMapping({"/member/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model)
/*     */   {
/*  50 */     Website web = SiteUtils.getWeb(request);
     List groupList = this.shopMemberGroupMng.getList(
/*  52 */       SiteUtils.getWebId(request));
/*     */ 
/*  54 */     List userDegreeList =pDictionarystByType(Long.valueOf(1L));
/*     */ 
/*  56 */  amilyMembersList = this.shopDictionaryMng.getListByType(Long.valueOf(2L));/
/*  58 */     List incomeDescList = this.shopDictionaryMng.getListByType(Long.valueOf(3L));
/*     */ 
/*  60 */     List workSeniorityList = this.shopDictionaryMng.getListByTyalueOf(4L));
/*     */ 
/*  62 */     List degreeList = this.shopDictionaryMng.getListByType(Long.value/*     */
/*  64 */     model.addAttribute("groupList", groupList);
/*  65 */     model.addAttribute("usest", userDegreeList);
/*  66 */     model.addAttribute("familyMembersList", familyMembersList);
/*  67 del.addAttribute("incomeDescList", incomeDescList);
/*  68 */     model.addAttribute("workSeniorityList", rityList);
/*  69 */     model.addAttribute("degreeList", degreeList);
/*  70 */     return "member     */   }
/*     */ 
/*     */   @RequestMapping({"/member/o_save.do"})
/*     */   public String save(ShopMember bean, Long groupId, Long userDegreeId, Long degreeId, Long incomeDescId, Long workSeniorityId, Long familyMembersId, String username, String password, String email, Boolean disabled, HttpServletRequest request, ModelMap model)
/*     */   {
/*  78 */     WebErrors errors = validateSave(bean, username, email, request);
/*  79 */   ors.hasErrors(80 */      rrors.showErrorPage(model);
/*     */     }
/*     3 */     bean = this.manager.register(username, password, email, Boolean.valueOf(true), null,
/*  84 */       request.getRemoteAddr(), disabled, SiteUtils.getWebId(request),
/*  85 */       groupId, userDegreeId, degreeId, incomeDescId, workSeniorityId, 
/*  86 */   yMembersId, bean);
/*  87 */     log.info("save ShopMember, id={}", bean.getId());
/*  88 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/memt.do"})
/*     *c String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  93 */     Website web = SiteUtils.getWeb(request);
/*  94 */     WebErrors errors = validateEdit(id, request);
/*  95 */     if (errors.hasErrors()) {
/*  96 */       return errors.showErrorPage(model);
/*     */     }
/*  98 */     List groupList = this.shopMemberGroupMng.getList(
/*  99 */       SiteUtils.getWebId(request));
/*     */ 
/* 102 st userDegreeLs.shopDictigetListByType(Long.valueOf(1L));
/*     */
/* 104 st familyMembersList = this.shopDictionaryMng.getListByType(Long.valueOf(2L));
/*     */ 
/* 106 */     List incomeDescList = this.shopDictionaryMng.getListByType(Long.valueOf(3L));
/*     */ 
/* 108 */     List workSeniorityList = this.shopDictionaryMng.getListByType(Long.valueOf(4L));
/*   110 */     List degreeList = this.shopDictionaryMng.getListByType(Long.valueOf(5L));
/*     */ 
/* 112 */     ShopMember membemanager.findById(id);
/* 113 */     model.addAttribute("groupList", groupList);
/* 114 */     model.add("member", member);
/* 115 */     model.addAttribute("groupList", groupList);
/* 116 */     model.addAttrirDegreeList", userDegreeList);
/* 117 */     model.addAttribute("familyMembersList", familyMembersList)*/     model.addAttribute("incomeDescList", incomeDescList);
/* 119 */     model.addAttribute("workSenioriworkSeniorityList);
/* 120 */     model.addAttribute("degreeList", degreeList);
/* 121 */     retur/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/o_update.do"})
/*     */   public String update(ShopMember bean, Long groupId, Long userDegreeId, Long degreeId, Long incomeDescId, Long workSeniorityId, Long familyMembersId, String password, String email, Boolean disabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 129 */     WebErrors errors = validateUpdate(bean.getId(), email, request);
/* 130 */     if (errors.hasErrors()) {
/* 131 */       return errors.showErrorPage(model);
/*     */     }
/* 133 */     bean = this.manager.update(bean, groupId, userDeg* 134 */      , incomeDesSeniorityId, familyMembersId,
/* 135 */       passwo, disabled);
/* 136 */     log.info("update ShopMember, id={}.", bean.getId());
/* 137 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, etRequest request, ModelMap model)
/*     */   {
/* 143 */     WebErrors errors = validateDelete(ids, request);
/* 144 */     if (errors.hasErrors()) {
/* 145 */       return errors.shoe(model);
/*     */     }
/* 147 */     ShopMember[] beans = this.manager.deleteByIds(ids);
/* 148 */     for (ShopMember bean : beans) {
/* 149 */       log.info("delete ShopMember, id={}", bean.getId());
/*     */     }
/* 151 */     return list(pageNo, request, model);
/*     */   }
/*     */
/*     */   private WebErrateSave(ShopMe, String ustring email, HttpServletRequest request)
/*     */   */     WebErrors errors = WebErrors.create(request);
/* 157 */     Website web = SiteUtils.getWeb(request */     bean.setWebsite(web);
/* 159 */     if (vldUsername(username, errors)) {
/* 160 */       return errors;
/*     */     }
/* 162 */     if (vldEmail(email, errors)3 */       return errors;
/*     */     }
/* 165 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 169 */     WebEors = WebErrors.create(request);
/* 170 */     Website web = SiteUtb(request);
/*   if (vldEweb.getId(), errors)) {
/* 172 */       return errors;
/*     */     }
/* 174 */     return errors;
/*     */   }
/*       */   private WebErrors validateUpdate(Long id, String email, HttpServletRequest request)
/*     */   {
/* 179 */     WebErrors errors = WebErrors.create(request);
/* 180 */     Website web = SiteUtils.getWeb(request);
/* 181 */     if (vldEmaid, email, errors)) {
/* 182 */       return errors;
/*     */     }
/* 184 */     if (vldExib.getId(), errors)) {
/* 185 */       return *     */     }/     retur
/*     */   }
/*     */
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 191 */     WebErrors errors = WebErrors.create(request);
/* 192 */     Website web = SiteUtils.getWeb(request);
/* 193 */     errors.ifEmpty(ids, "ids");
/* 194 */    g id : ids) {
/* 195 */       vldExist(id, we, errors);
/*   }
/* 197 turn errors;
/*     */   }
/*     */
/*     */   private boolean vldExist(Long id, Long webId,  errors) {
/* 201 */     if (errors.hasErrors()) {
/* 202 */       return true;
/*     */     }
/* 204 */     if (errors.ifNull(id, "id")) {
/* 205 */       return true;
/*     */     }
/* 207 */     ShopMember enti.manager.findById(id);
/* 208 */     if (errors.ifNotExist(entity, ShopMember.class, id)) {
/* 209 */ urn true;
/*     */     }
/* 211 */     if (!tWebsite().getls(webId)) */       errors.notInWeb(ShopMember.class, id);
/* 213 */       return true;
/*     */     }
/* 215 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean vldEmailUpdate(Long id, String email, WebErrors errors) {
/* 219 */     if (!StringUtils.isBlank(email)) {
/* 220 */       ShopMember member = this.manager.fd);
/* 221 */       if ((!member.getEmail().eil)) &&
/* 22    (vldEma errors))) {
/* 223 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 227 */     return false;
/*     */   }
/*     */
/*   ivate boolean vldEmail(String email, WebErrors errors) {
/* 231 */     if (errors.ifNotEma "email", 100)) {
/* 232 */       return true;
/*     */     }
/* 234 */     if (this.userMng.emailExist(email)) {
/* 235 */       errors.addErrorCode("error.emailExist");
/*     return true;
/*     */     }
/* 238 */     return false;
/*     */   }
/*     */
/*     */   private boolean vldUsername(String username, WebErrors errors) {
/    if (errors.ifNotUsername(username, "user 100)) {
/* 24  return tr  */     }
/* 245 */     if (this.userMng.usernameExist(username)) {
/* 246 */       errors.addErrorCode("error.usernameExist");
/* 247 */       return true;
/*     */     }
/* 249 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopMemberAct
 * JD-Core Version:    0.6.2
 */