/*     */ package guda.shop.action.member;
/*     */ 
/*     */ import guda.shop.cms.entity.ShopConfig;
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.entity.ShopMemberGroup;
/*     */ import guda.shop.cms.entity.ShopScore;
/*     */ import guda.shop.cms.entity.ShopScore.ScoreTypes;
/*     */ import guda.shop.cms.manager.ShopMemberMng;
/*     */ import guda.shop.cms.manager.ShopScoreMng;
/*     */ import guda.shop.cms.web.ShopFrontHelper;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.common.web.RequestUtils;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.common.web.session.SessionProvider;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.EmailSender;
/*     */ import guda.shop.core.entity.Global;
/*     */ import guda.shop.core.entity.Member;
/*     */ import guda.shop.core.entity.MessageTemplate;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.manager.MemberMng;
/*     */ import guda.shop.core.manager.UserMng;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import guda.shop.core.web.front.FrontHelper;
/*     */ import com.octo.captcha.service.CaptchaService;
/*     */ import com.octo.captcha.service.CaptchaServiceException;
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class RegisterAct
/*     */ {
/*  55 */   private static final Logger log = LoggerFactory.getLogger(RegisterAct.class);
/*     */   private static final String REGISTER = "tpl.register";
/*     */   private static final String REGISTER_RESULT = "tpl.registerResult";
/*     */   private static final String REGISTER_TREATY = "tpl.registerTreaty";
/*     */   private static final String REGISTER_ACTIVE_STATUS = "tpl.registerActiveStatus";
/*     */ 
/*     */   @Autowired
/*     */   private UserMng userMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShopMemberMng shopMemberMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShopScoreMng shopScoreMng;
/*     */ 
/*     */   @Autowired
/*     */   private MemberMng memberMng;
/*     */ 
/*     */   @Autowired
/*     */   private CaptchaService captchaService;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @RequestMapping(value={"/register.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String registerInput(HttpServletRequest request, ModelMap model)
/*     */   {
/*  63 */     Website web = SiteUtils.getWeb(request);
/*  64 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  65 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.register", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/register.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String registerSubmit(String checkcode, String username, String email, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  72 */     Website web = SiteUtils.getWeb(request);
/*  73 */     ShopConfig config = SiteUtils.getConfig(request);
/*  74 */     WebErrors errors = validate(checkcode, username, email, password, request, response);
/*  75 */     if (errors.hasErrors()) {
/*  76 */       return FrontHelper.showError(errors, web, model, request);
/*     */     }
/*  78 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  79 */     EmailSender sender = web.getEmailSender();
/*  80 */     MessageTemplate tpl = (MessageTemplate)web.getMessages().get("resetPassword");
/*     */ 
/*  82 */     if (sender == null)
/*     */     {
/*  84 */       model.addAttribute("status", Integer.valueOf(2));
/*  85 */     } else if (tpl == null)
/*     */     {
/*  87 */       model.addAttribute("status", Integer.valueOf(3));
/*     */     }
/*     */     else {
/*     */       try {
/*  91 */         String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
/*  92 */         String base = new String(web.getUrlBuff(true));
/*  93 */         this.userMng.senderActiveEmail(username, base, email, uuid, sender, tpl);
/*  94 */         ShopMember member = this.shopMemberMng.register(username, password, email, Boolean.valueOf(false), uuid, 
/*  95 */           request.getRemoteAddr(), Boolean.valueOf(false), web.getId(), config.getRegisterGroup().getId());
/*     */ 
/*  97 */         String emailtype = email.substring(email.indexOf("@") + 1, email.indexOf("."));
/*  98 */         model.addAttribute("emailtype", emailtype);
/*  99 */         model.addAttribute("member", member);
/* 100 */         model.addAttribute("status", Integer.valueOf(1));
/* 101 */         log.info("register member '{}'", member.getUsername());
/*     */       }
/*     */       catch (Exception e) {
/* 104 */         model.addAttribute("status", Integer.valueOf(4));
/* 105 */         model.addAttribute("message", e.getMessage());
/* 106 */         log.error("send email exception {}.", e.getMessage());
/*     */       }
/*     */     }
/* 109 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.registerResult", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/active.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String active(String userName, String activationCode, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 116 */     Website web = SiteUtils.getWeb(request);
/* 117 */     Member bean = this.memberMng.getByUsername(web.getId(), userName);
/* 118 */     long l = System.currentTimeMillis();
/* 119 */     l -= 86400000L;
/* 120 */     Date date = new Date();
/* 121 */     date.setTime(l);
/* 122 */     if ((StringUtils.isBlank(String.valueOf(userName))) || (StringUtils.isBlank(activationCode))) {
/* 123 */       model.addAttribute("status", Integer.valueOf(2));
/* 124 */     } else if (bean == null) {
/* 125 */       model.addAttribute("status", Integer.valueOf(3));
/* 126 */     } else if (bean.getActive().booleanValue()) {
/* 127 */       model.addAttribute("status", Integer.valueOf(4));
/* 128 */     } else if (!bean.getActivationCode().equals(activationCode)) {
/* 129 */       model.addAttribute("status", Integer.valueOf(5));
/* 130 */     } else if (bean.getCreateTime().compareTo(date) < 0) {
/* 131 */       model.addAttribute("status", Integer.valueOf(6));
/*     */     } else {
/* 133 */       bean.setActive(Boolean.valueOf(true));
/* 134 */       this.memberMng.update(bean);
/*     */ 
/* 136 */       this.shopMemberMng.updateScore(this.shopMemberMng.findById(bean.getId()), 
/* 137 */         SiteUtils.getWeb(request).getGlobal().getActiveScore());
/* 138 */       ShopScore shopScore = new ShopScore();
/* 139 */       shopScore.setMember(this.shopMemberMng.findById(bean.getId()));
/* 140 */       shopScore.setName("邮箱验证送积分");
/* 141 */       shopScore.setScoreTime(new Date());
/* 142 */       shopScore.setStatus(true);
/* 143 */       shopScore.setUseStatus(false);
/* 144 */       shopScore.setScoreType(Integer.valueOf(ShopScore.ScoreTypes.EMAIL_VALIDATE.getValue()));
/* 145 */       this.shopScoreMng.save(shopScore);
/* 146 */       model.addAttribute("status", Integer.valueOf(1));
/* 147 */       model.addAttribute("member", bean);
/*     */     }
/* 149 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 150 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.registerActiveStatus", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/reactive.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void reactive(Long userId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 157 */     Website web = SiteUtils.getWeb(request);
/* 158 */     JSONObject json = new JSONObject();
/* 159 */     Member bean = this.memberMng.findById(userId);
/* 160 */     if (bean.getActive().booleanValue()) {
/*     */       try {
/* 162 */         json.put("data", 1);
/*     */       } catch (JSONException e) {
/* 164 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/* 168 */       String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
/* 169 */       bean.setActivationCode(uuid);
/* 170 */       bean.setCreateTime(new Date());
/* 171 */       this.memberMng.update(bean);
/* 172 */       String base = new String(web.getUrlBuff(true));
/* 173 */       EmailSender sender = web.getEmailSender();
/* 174 */       Map messages = web.getMessages();
/* 175 */       MessageTemplate tpl = (MessageTemplate)messages.get("resetPassword");
/*     */       try {
/* 177 */         this.userMng.senderActiveEmail(bean.getUsername(), base, bean.getEmail(), uuid, sender, tpl);
/*     */         try {
/* 179 */           json.put("data", 2);
/*     */         } catch (JSONException e) {
/* 181 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/*     */         try {
/* 186 */           json.put("data", 3);
/*     */         } catch (JSONException e1) {
/* 188 */           e1.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 193 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/treaty.jspx"})
/*     */   public String treaty(HttpServletRequest request, ModelMap model)
/*     */   {
/* 199 */     Website web = SiteUtils.getWeb(request);
/* 200 */     model.addAttribute("global", SiteUtils.getWeb(request).getGlobal());
/* 201 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 202 */     return web.getTplSys("member", 
/* 203 */       MessageResolver.getMessage(request, 
/* 203 */       "tpl.registerTreaty", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/username_unique.jspx"})
/*     */   public void checkUsername(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 210 */     String username = RequestUtils.getQueryParam(request, "username");
/*     */ 
/* 212 */     if (StringUtils.isBlank(username)) {
/* 213 */       ResponseUtils.renderJson(response, "false");
/* 214 */       return;
/*     */     }
/*     */ 
/* 217 */     if (this.userMng.usernameExist(username)) {
/* 218 */       ResponseUtils.renderJson(response, "false");
/* 219 */       return;
/*     */     }
/* 221 */     ResponseUtils.renderJson(response, "true");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/email_unique.jspx"})
/*     */   public void checkEmail(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 228 */     String email = RequestUtils.getQueryParam(request, "email");
/*     */ 
/* 230 */     if (StringUtils.isBlank(email)) {
/* 231 */       ResponseUtils.renderJson(response, "false");
/* 232 */       return;
/*     */     }
/*     */ 
/* 235 */     if (this.userMng.emailExist(email)) {
/* 236 */       ResponseUtils.renderJson(response, "false");
/* 237 */       return;
/*     */     }
/* 239 */     ResponseUtils.renderJson(response, "true");
/*     */   }
/*     */ 
/*     */   private WebErrors validate(String checkcode, String username, String email, String password, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 244 */     WebErrors errors = WebErrors.create(request);
/* 245 */     String id = this.session.getSessionId(request, response);
/* 246 */     if (errors.ifOutOfLength(checkcode, "checkcode", 3, 10)) {
/* 247 */       return errors;
/*     */     }
/* 249 */     if (errors.ifOutOfLength(password, "password", 3, 32))
/* 250 */       return errors;
/*     */     try
/*     */     {
/* 253 */       if (!this.captchaService.validateResponseForID(id, 
/* 254 */         checkcode.toUpperCase(Locale.ENGLISH)).booleanValue())
/*     */       {
/* 255 */         errors.addErrorCode("error.checkcodeIncorrect");
/* 256 */         return errors;
/*     */       }
/*     */     } catch (CaptchaServiceException e) {
/* 259 */       errors.addErrorCode("error.checkcodeInvalid");
/* 260 */       errors.addErrorString(e.getMessage());
/* 261 */       return errors;
/*     */     }
/* 263 */     if (errors.ifNotEmail(email, "email", 100)) {
/* 264 */       return errors;
/*     */     }
/* 266 */     if (this.userMng.emailExist(email)) {
/* 267 */       errors.addErrorCode("error.emailExist");
/* 268 */       return errors;
/*     */     }
/* 270 */     if (errors.ifNotUsername(username, "username", 3, 100)) {
/* 271 */       return errors;
/*     */     }
/* 273 */     if (this.userMng.usernameExist(username)) {
/* 274 */       errors.addErrorCode("error.usernameExist");
/* 275 */       return errors;
/*     */     }
/* 277 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.RegisterAct
 * JD-Core Version:    0.6.2
 */