 package guda.shop.cms.action.member;
/*     */*/ import gcms.web.ShopFrontHelper;
/*     */ import guda.shop.iteUtils;
/*     */ import guda.shop.common.we.SessionProvider;
/*     */ import guda.shop.common.web.springmeResolver;
/*     */ import guda.shop.core.entity.EmailSender;
/*mport guda.shop.core.entity.MessageTemplate;
/*      guda.shop.core.entity.User;
/*     */ import guda.shop.ty.Website;
/*     */ import guda.shop.core.merMng;
/*     */ import guda.shop.core.web.WebEr    */ import guda.shop.core.web.front.FrontHelpe */ import com.octo.captcha.service.CaptchaServ   */ import com.octo.captcha.service.CaptchaServiceExc*     */ import java.util.List;
/*     */ import java.util/*     */ import java.util.Map;
/*     */ import javax.servlet.httpletRequest;
/*     */ import java.http.HttpServletResponse;
/*     *org.slf4j.Logger;
/*     */ impof4j.LoggerFactory;
/*     */ import org.springframework.tory.annotation.Autowired;
/*     */ import org.springfraereotype.Controller;
/*     */ imporingframework.ui.ModelMap;
/*     */ imporingframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Co/*     */ public class ForgotPasswordAct
/*     */ {
/*  46 ate static final Logger log = LoggerFactory.getLogtPasswordAct.class);
/*     */   private static final String FORGOTTEN_INl.forgotten*     */   private sta String FORGOTTEN_RESULT = "tpl.forgotten/*     */   private static final String RESET_PASSWORD_TPL = "tpl.resetPassword";
/*     */ 
/*     */   @Au*     */   private UserMng userMng;
/*     */ 
/*     */   @Autowired
/*     */  CaptchaService captchaService;
/*     */
/*     */   @Autowired
/*     */   privonProvider session;
/*     */ 
/*     */   @RequestMapping(value={"/forgot_passwor method={orramework.web.bind.annotuestMethod.GET})
/*     */   public SottenInput(etRequest request, Model)
/*     */   {
/*  61 */     Website web = SiteUtb(request);/     ShopFrontHelper.sata(request, model, web, 1);
/*  63 */     regetTplSys("
/*  64 */       MessageResolver.getMessage(request,
/*  64 */       "tpl.forgottenInput", new Object[0]));
/*     */   }
/*       */   @RequestMapping(value={"/forgot_password.jspx"}, method={org.springframework.annotation.RequestMethod.POST})
/*     */   public String fogottenSubmit(String checkcode, String username, String email, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  81 */     Website web = SiteUtils.getWeb(request);
/*  82 */     WebErrors erlidateFogottene, username
/*  83 */       request, response);
/*  84 */     if (errors.hasErrors()) {
/*  85 */       return FrontHelper.showError(errors, l, request);
/*     */     }
/*     */ 
/*  88 */     User user = this.userMng.getByUsername(username);
/*  89 */     MessageTemplate tpl = (MessageTemplate)web.get).get(
/*  90 */       "resetPassword");
/*  91 */     EmailSender sender = web.getEmailSender();
/*  92 */     if (user == null)
/*     */     {
/*  94 */       model.addAttribute("status", Integer.valueOf(1));
/*  95 */     } else if (!user.getEmail().equalsIgnoreCase(email))
/*     */     {
/*     model.addAttratus", Integer.valueOf(2));
/*  98 */     } else if (!user.getEmail().equals(email))
/*     */     {
/* 100 */       model.addAttribute("status", Integer.valueOf(3));
/* 101 */     } else if (sender == null)
/*     */     {
/* 103 */       model.addAttribute("status", Integf(4));
/* 104 */     } else if (tpl == null)
/*     */     {
/* 106 */       model.addAttribute("status", Integer.valueOf(5));
/*     */     }
/*    lse {
/*     */       try {
/* 110 */         String base = new String(web.getUrlBuff(true));
/* 111 */         user = this.userMng.passworn(user.getId(), base, sender, tpl);
/*     */ 
/* 113 */         String emailtype = email.substring(email.indexOf("@") + 1,dexOf("."));
/* 114 */         model.addAttribute("emailtype", emailtype);
/* 115 */         model.addAttribute("status".valueOf(0));
/* 116 */         model.addAttribute("user", user);
/*     */       */       catch ( e) {
/* 119 */      addAttribute("status", Integer.valueOf(100));
/* 120 */         model.addAttribute("message", e.getMessage());
/* 121 */         log.error("send email exception.", e);
/*     */ *     */     }
/* 124 */     model.addAttribute("user", user);
/* 125 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 126 */     log.info("find passsword, username={} email={}", username, email);
/* 127 */     return web.getTplSys("member",
/* 128 */       MessageResolver.e(request,
/* 128 "tpl.forgottenResult", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/reset_password.jspx"})
/*     */   public String resetPwd(Long uid, String activationCode, HttpServletRequest request, HttpServletResponse, ModelMap mo   */   {
/* 135 */     Website web = SiteUtils.getWeb(request);
/* 136 */     WebErrors errors = validateReset(uid, activationCode, request);
/* 137 */     if (errors.hasErrors()) {
/* 138 */       return FrontHelper.showMessage((String)errors.getErrors().get(0), web,
/* 139 */         model, request);
/*     */     }
/* 141 */     User user = this.userMng.findById     */     bocess;
/*   boolean success;
/* 143 */     if (activationCode.equaetResetKey())) {
/* 144 */       user = this.userMng.resetPassword(user.getId());
/* 145 */       success = true;
/*     */     } else {
/* 14  success = false;
/*     */     }
/* 149 */     model.addAttribute("user", user);
/* 150 */     model.addAttribute("success", Boolean.valueOf(success));
/* 151 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 152 */     return web.getTplSys("member",
/* 153 */       MessageResolver.getMessag,
/* 153 */       "tpl.resetPassword", new Object[0]));
/*     */   }/
/*     */   private WebErroreFogotten(String checkcode, String username, String email, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 159 */     WebErrors errors = WebErrors.create(request);
     String id = this.session.getSessionId(request, resp 161 */     if (errors.ifOutOfLength(checkcode, "checkcode", 3, 10))
/* 162 */       return errors;
/*     */     try
/*     */     {
/* 165 */       if (!this.captchaService.validateResponseForID(id,
/* 166 */         checkcode.toUpperCase(Locale.ENGLISH)).booleanValue())
/*     */       {
/* 167 */         errors.addErrorCode("error.checkcodeIncorrect" */         rers;
/*     }
/*     */     } catch (CaptchaServiceException e) {
/* 171 */       errors.addErrorCode("error.checkcodeInvalid");
/* 172 */       errors.addErrorStringsage());
/* 173 */       return errors;
/*     */     }
/* 175 */     if (errors.ifNotEmail(email, "email", 100)) {
/* 176 */       return errors;
/*     */     }
/* 178 */     if (errors.ifNotUsername(username, "username", 3, 100)) {
/* 179 */     errors;
/*     */ 181 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateReset(Long uid, String resetKey, HttpServletRequest request)
/*    * 186 */     WebErrors errors = WebErrors.create(request);
/* 187 */     if (errors.ifNull(uid, "uid")) {
/* 188 */   n errors;
/*     ** 190 */     User user = this.userMng.findById(uid);
/* 191 */     if (errors.ifNotExist(user, User.class, uid)) {
/* 192 */       return errors;
/*     */     }
/* 194 */     if (errors.ifOutOfLength( "resetKey", 32, 32)) {
/* 195 */       return errors;
/*     */     }
/* 197 */     return errors;
/*          */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.ForgotPasswordAct
 * JD-Core Version:    0.6.2
 */