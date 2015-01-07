 package guda.shop.cms.action.member;
/*     */*/ import gcms.dao.OrderDao;
/*     */ import guda.shop.y.ShopMember;
/*     */ import guda.shop.cms.managtMng;
/*     */ import guda.shop.cms.manager.ShopDing;
/*     */ import guda.shop.cms.manager.ShopMemberMng;
 import guda.shop.cms.web.ShopFrontHelper;
/*     */ ia.shop.cms.web.SiteUtils;
/*     */ import guda.shopthreadvariable.MemberThread;
/*     */ import .common.web.ResponseUtils;
/*     */ import guda.shop.common.webc.MessageResolver;
/*     */ import guda.shop.core.ener;
/*     */ import guda.shop.core.entity.User;
/*     */ importp.core.entity.Website;
/*     */ import guda.shanager.UserMng;
/*     */ import guda.shop.cobErrors;
/*     */ import guda.shop.core.web.froelper;
/*     */ import java.io.IOException;
/*  ort java.math.BigDecimal;
/*     */ import javat;
/*     */ import javax.servlet.http.Cookie;
/*     *javax.servlet.http.HttpServletRequest;/ import javax.servlet.http.HttpServlet
/*     */ import org.apache.commStringUtils;
/*     */ import org.slf4j.Logg  */ import org.slf4j.LoggerFactory;
/*     */ import orramework.beans.factory.annotation.Autowired;
/*     */ imspringframework.stereotype.Controller;
/*     */ imporingframework.ui.ModelMap;
/*     */rg.springframework.web.bind.annotation.Reqng;
/*     */
/*     */ @Controller
/*     */ public class ShopMemberAct/ {
/*  46 */   private static final Logger log = LoggerFactgger(ShopMemberAct.class);
/*     */   public statString MEMBER_CENTER = "tpl.memberCenter";
/*     */   public static finaMEMBER_PORTpl.memberPortrait";
/* public static final String MEMBER_PRtpl.memberProfile";
/*     */   public static final String MEMBER_PASSWORD = "tpl.memberPassword";
/*       */   @Autowired
/*     */   private OrderDao dao;
/*     */ 
/*     */ red
/*     */   private UserMng userMng;
/*     */
/*     */   @Autowired
/*  rivate ProductMng productMng;
/*     */
/*     */   @Autowired
/*     */   popMemberMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private ShopyMng shopDing;
/*     */ 
/*     *estMapping(value={"/shopMember/ind, method={oframework.web.bind.annoquestMethod.GET})
/*     */   public dex(HttpSerst request, ModelMap mo   */   {
/*  74 */     Website web = SiteUeb(request)*/     ShopMember memberThread.get();
/*     */ 
/*  77 */     if = null) {
/      return "redirect:jspx";
/*     */     }
/*  80 */     BigDecimal money = tetMemberMonmember.getId());
/*  81 */     Integer[] orders = this.dao.getOrderByMember(member.getId());
/*  82 */     Integer[] products = thtMng.getProductByTag(web.getId());
/*  83 */     model.addAttribute("productcts);
/*  84 */     model.addAttribute("orders", orders);
/*  85 */     model.addAttribute("money", money);
/*  86 */     mttribute("historyProductIds", getHistoryProductIds(request));
/*  87 */     ShopFrontHelper.setC(request, model, web, 1);
/*  88 */     return web.getTplSys("member", 
/*  89 */       MessageResolver.getMessage(request, 
/*  89 */       "tpl.memberCenter", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shopMember/profile.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 102 */     Website web = SiteUtils.getWeb(request);
/* 103 */     ShopMember member = MemberThread.get();
/*     */ 
/* 105 */     if (member == null) {
/* 106 */       return "redirect:../login.jspx";
/*     */     }
/*     */ 
/* 109 */     List userDegreeLi.shopDictionaristByType(LOf(1L));
/*     */ 
/* 111 */     List familyMembersList = this.shopDictionaryMng.getListByType(Long.valueOf(2L));
/*     */ 
/* 113ist incomeDescList = this.shopDictionaryMng.getListByType(Long.valueOf(3L));
/*     */ 
/* 115 */     ListorityList = this.shopDictionaryMng.getListByType(Long.valueOf(4L));
/*     */ 
/* 117 */     List degreeList = this.shopDicg.getListByType(Long.valueOf(5L));
/* 118 */     model.addAttribute("member", member);
/*     */*/     model.add("userDegreeList", userDegreeList);
/* 121 */     model.addAttribute("familyMembersList", familyMembers 122 */     model.addAttribute("incomeDescList", incomeDescList);
/* 123 */     model.addAttribute("workSest", workSeniorityList);
/* 124 */     model.addAttribute("degreeList", degreeList);
/* 125 */     Shoper.setCommonData(request, model, web, 1);
/* 126 */     return web.getTplSys("member", MessageResolver.getequest, "tpl.memberProfile", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shopMember/profile.jspx"}, method={org.sprirk.web.bind.annotation.RequestMethod.POST})
/*     */   public String profileSubmit(ShopMember bean, Long groupId, Long userDegreeId, Long degreeId, Long incomeDescId, Long workSeniorityId, Long familyMembersId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 142 */     ShopMember member = MemberThread.get();
/*     */ 
/* 144 */     if (member == null) {
/* 145 */       return "redirect:../login.jspx";
/*     */     }
/* 147 */     bean = this.manager.update(bean, groupId, userDegreeId, degreeIDescId, workSe, familyMem/* 148 */     log.info("ShopMember update infomation: {}", bean.getUsername());
/* 149 */     return index(request, response, model);/   }
/*     */ 
/*     */   @RequestMapping(value={"/shopMember/pwd.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String passwordInput(HttpServletRequest request, HttpServletResponse response, model)
/*     */   {
/* 163 */     Website web = SiteUtils.getWeb(r/* 164 */     ShopMember member = MemberThread.get();
/*     */ 
/* 166 */     if (member == nul67 */       return "redirect:../login.jspx";
/*     */     }
/* 169 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 170 */     model.addAttribute("email", MemberThread.get().getEmail());
/* 171 */     model.addAttribute("historyProductIds", getHistoryProductIds(;
/* 172 */   web.getTplSr", MessageResolver.getMessage(request, "tpl.memberPassword", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapp={"/shopMember/portrait.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   pung portrait(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 186 */     Website wUtils.getWeb(request);
/* 187 */     ShopMember member = MemberThread.get();
/*     */ 
/* 189 *(member == null) {
/* 190 */       return "redirect:../login.jspx";
/*     */     }
/* 192 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 193 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.memberPortrait", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/shopMember/updateAvatar.jspx"}{org.springfrab.bind.annoquestMethod.POST})
/*     */   public String updateAvatar(String picPaths, HttpServletRequest request, HttpServletResponse response, model)
/*     */   {
/* 206 */     ShopMember member = MemberThread.get();
/*     */
/* 208 */     if (membe) {
/* 209 */       return "redirect:../login.jspx";
/*     */     }
/* 211 */     member.setAvatar(picPaths);
/* 212 */   nager.update(member);
/* 213 */     return "redirect: index.jspx";
/*     */   }
/*     */ 
/*  RequestMapping(value={"/shopMember/pwd.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String passwordSubmit(String origPwd, String newPwd, String email, Strrl, HttpServlerequest, HtResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 238 */     Website web = SiteUtils.getWeb(request);
/    ShopMember member = MemberThread.get();
/*     */ 
/* 241 */     if (member == null) {
/* 242 */       return "redirect:../log
/*     */     }
/* 244 */     Long userId = member.getMember().getUId();
/* 245 */     WebErrors errors = validatePassword(userId, email, newPwd, member.getEmail(), request);
/* 246 */     if (errors.hasErrors()) {
/* 247 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/*   this.userMnger(userId, mail);
/* 250 */     log.info("ShopMember update password: {}", member.getUsername());
/* 251 */     return FrontHelper.showSuccel.success", nextUrl, web, model, request);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/shopMember/checkPwd.jspx"})
/*     */   public void checkPwd(String origPwd, etRequest request, HttpServletResponse)
/*     */   {
/* 265 */     ShopMember member = MemberThread.get();
/* 266 */     Long userId = member.getMember().ggetId();
/* 267 */     boolean pass = this.userMng.isPasswordValid(userId, origPwd);
/* 268 */  seUtils.renderJson(response, pass ? "true" : "false");
/*     */   }
/*     */ 
/*     */   public String getHistoryProductIds(HttpServletRequest request) {
/* 272 */     String str = null;
/* 273 */     Cookie[] cookie = request.getCookies();
/* 274 */     int num = cookie.length;
/* 275 */     for (int i num; i++) {
/* 276 */       if (cookie[i].getName().equals("shop_record")) {
/* 277 */         str = cookie[i].getValue();
/* 278 */         break;
/*     */       }
/*     */     }
/* 281 */     return str;
/*     */   }
/*     */ 
/*     */   privates validatePass userId, Stl, String newPwd, String origEmail, String origPwd, HttpSerst request)
/*     */   {
/* 287 */     WebErrors errors = WebErrors.create(request);
/* 288 */     if ((!Ss.isBlank(newPwd)) &&
/* 289 */       (errors.ifOutOfLength(newPwd, "password", 3, 32))) {
/* 290 */       return errors;
/*     */     }
/* 292 */     if (!this.userMng.isPasswordValid(userId, origPwd)) {
/* 293 */       errors.addErrorCode("error.passwordInvalid");
/*     */     }
     if (errorail(email, 100)) {
/* 296 */       return errors;
/*     */     }
/* 298 */     if ((!email.equals(origEmail)) && (this.userMng.emailExist(email))) {
/* 299 */       errors.addErrorCode("error.emailExist");
/* 300 */       return errors;
/*     */     }
/* 302 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.ShopMemberAct
 * JD-Core Version:    0.6.2
 */