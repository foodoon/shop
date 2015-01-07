 package guda.shop.cms.action.member;
/*     */*/ import gcms.entity.ShopConfig;
/*     */ import guda.shop.y.ShopMember;
/*     */ import guda.shop.cms.entitberGroup;
/*     */ import guda.shop.cms.entity.ShopSco  */ import guda.shop.cms.entity.ShopScore.ScoreT    */ import guda.shop.cms.manager.ShopMemberMng;
/*     */uda.shop.cms.manager.ShopScoreMng;
/*     */ import gums.web.ShopFrontHelper;
/*     */ import guda.shop.cmeUtils;
/*     */ import guda.shop.common.web.Reques*     */ import guda.shop.common.web.ResponseU    */ import guda.shop.common.web.session.SessionPr*     */ import guda.shop.common.web.springmvc.Messag;
/*     */ import guda.shop.core.entity.EmailSender;
/*     */uda.shop.core.entity.Global;
/*     */ import guda.shop.core.enti;
/*     */ import guda.shop.core.entity.MessageTemp    */ import guda.shop.core.entity.Website;
/*mport guda.shop.core.manager.MemberMng;
/*      guda.shop.core.manager.UserMng;
/*     */ import guda.sweb.WebErrors;
/*     */ import guda.shop.core.wFrontHelper;
/*     */ import com.octo.captcha.servhaService;
/*     */ import com.octo.captcha.servhaServiceException;
/*     */ import java.io.IO;
/*     */ import java.util.Date;
/*     */ import javcale;
/*     */ import java.util.Map;
/*     */ import javID;
/*     */ import javax.servlet.http.HttpServletRequest;
/*      javax.servlet.http.HttpServletRespons */ import org.apache.commons.lantils;
/*     */ import org.json.JSOn;
/*     */ import org.json.JSO/*     */ import org.slf4j.Logger*/ import org.slf4j.LoggerFactory;
/*     */ import org.mework.beans.factory.annotation.Autowired;
/*     */ imporingframework.stereotype.Controller;
/*     */ import gframework.ui.ModelMap;
/*     */ import gframework.web.bind.annotation.Request/*     */
/*     */ @Controller
/*ublic class RegisterAct
/*     */ {
/*  55vate static final Logger log = LoggerFactory.getLogger(RegisterAct.class)*/   private static final String REGISTER = "tpl.register";
   private static final String REGISTER_RESULT = "terResult";
/*     */   private static final String REGISTER_TREATY = "tprTreaty";
/  private static finalEGISTER_ACTIVE_STATUS = "tpl.registtatus";
/*     */ 
/*     */   @Autowired
/*     */   private UserMng userMng;
/*     */ 
/*     */   d
/*     */   private ShopMemberMng shopMemberMng;
/*     */ 
/*   utowired
/*     */   private ShopScoreMng shopScoreMng;
/*     */ 
/*     */   @
/*     */   private MemberMng memberMng;
/*     */ 
/*     */   @Autowired
/*  rivate CaptchaService captchaService;
/*     */
/*     */   @Autowired
/*     */   private Svider sessi  */
/*     */   @Requg(value={"/register.jspx"}, method={oframework.wnnotation.RequestMethod     */   public String registerInput(HttpServletequest, Model)
/*     */   {
/*  6Website web = SiteUtils.getWeb(request);
/*  64hopFrontHelmmonData(request, model;
/*  65 */     return web.getTplSys("memsageResolveage(request, "tpl.regis Object[0]));
/*     */   }
/*     */ 
/*     */   apping(valuster.jspx"}, method={orramework.web.bind.annotation.RequestMethod.PO   */   pubg registerSubmit(String checkcode, String username, String email, String password, HttpServletRequest request, HttpServletresponse, ModelMap model)
/*     */   {
/*  72 */     Website web = SiteUtils.getWeb;
/*  73 */     ShopConfig config = SiteUtils.getConfig(request);
/*  74 */     WebErrors errors = validate(checkcode, username, email, password, request, response);
/*  75 */     if (errors.hasErrors()) {
/*  76 */       return FrontHelper.showError(eb, model, requ    */     */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  79 */     EmailSender sender = web.getEmailSender();
/*   MessageTemplate tpl = (MessageTemplate)web.getMessages().get("resetPassword");
/*     */ 
/*  82 */     if (sender == null)
/*     */     {
/*  84 */       model.addAttribute("statger.valueOf(2));
/*  85 */     } else if (tpl == null)
/*     */     {
/*  87 */       model.addAttribute("status", Integer.valueOf(3));
/*     */     }
/*     */     else {
/*     */       try {
/*  91 */         String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
/*  92 */         String base = new String(web.getUrlBuff(true));/         this.userMng.senderActiveEmail(username, base, email, uuid, sender, tpl);
/*  94 */         ShopMember member = this.shopMemberMng.register(username, password, email, Boolean.valueOf(false), uuid,
/*  95 */           requesteAddr(), Boolean.valueOf(false), web.getId(),etRegisterGroup().getId());
/*     */ 
/*  97 */         String emailtype = email.substring(email.indexOf("@") + 1, emai("."));
/*  98 */         model.addAttribute("emailtype", emailtype);
/*  99 */   el.addAttribute( member);
/* 100 */  del.addAttribute("status", Integer.valueOf(1));
/* 101 */         log.info("register member '{}'", member.getUsername());
/*     */       }
/*     */       catch (Exception e) {
/* 104 */         model.addAttribute("status", Integer.valueOf(4));
/* 105 */         model.addAttribute("message", e.getMessage());
/* 106 */         log.error("send email exception {}.", e.getMessage());
/*     */       }
/*     */     }
/* 109 */     return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.regis", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/active.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String active(String userName, String activationCode, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 116 */ te web = SiteUtilsequest);
/* 117 */     Member bean = this.memberMng.getByUsername(web.getId(), userName);
/* 118 */     long l = System.currentTimeMillis();
/* 119 */     l -= 86400000L;
/* 120 */     Date date = new Date();
/* 121 */     date.setTime(l);
/* 1 if ((StringUtils.tring.valueOf(userName))) || (StringUtils.isBlank(activationCode))) {
/* 123 */       model.addAttribute("status", Integer.valueOf(2));
     } else if null) {
/*     model.addAttribute("status", Integer.valueOf(3));
/* 126 */     } else if (bean.getActive().booleanValue()) {
/* 12  model.addAttribute("status", Integer.valueOf(4));
/* 128 */     } else if (!bean.getActivationCode().equals(activationCode)) {
/* 129 */       motribute("status", Integer.valueOf130 */     } else if (bean.getCreateTime().compareTo(date) < 0) {
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
/* 147 */ el.addAttribute("member", bean);
/*     */     }
/* 149 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 15return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.registerActiveStatus", new Object[0]));
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
/* 164 */         e.priace();
/*     */       }
/*     */     }
/*     */     else {
/* 168 */       String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');
/* 169 */       bean.setActivationCode(uuid);
/* 170 */       beateTime(new Da 171 */    emberMng.update(bean);
/* 172 */       String base = new String(web.getUrlBuff(true));
/* 173 */       EmailSender sender =mailSender();
/* 174 */       Map messages = web.getMessages();
/* 175 */       MessageTemplate tpl = (MessageTemplate)met("resetPassword");
/*     */
/* 177 */         this.userMng.senderActiveEmail(bean.getUsername(), base, bean.getEmail(), uuid, sender, tpl);
/*     */         try {
/* 179 */           json.put("data", 2);
/*     */         } catch (JSONException e) {
/* 181 */  e.printStackTrace();
/*     */         }
/*     */       }
       catch (Exception e) {
/*     */         try {
/* 186 */           json.put(";
/*     */        (JSONException 188 */           e1.printStackTrace();
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
/* 201 */     ShopFrontHelper.seta(request, model, web, 1);
/* 202 */     return web.getTplSys("member", 
/* 203 */       MessageResolver.getMessage(request,
/* 203 *tpl.registerTreaty", new Object[0]));
/*     */   }
/*     */
/* @RequestMapping({"/username_unique.jspx"})
/*     */   public void checkUsername(HttpSuest request, HttpSeonse response)
/* {
/* 210 */     String username = RequgetQueryParam(request, "username");
/*     */ 
/* 212 */     if (ls.isBlank(username)) {
/* 213 */       ResponseUtils.renderJson(response, "false");
/* 2   return;
/*     */     */
/* 217 */this.userMng.uset(username)) {
/* 218 */       ResponseUtils.renderJson(response, "false");
/*     return;
/    }
/* 22ResponseUtils.renderJson(response, "true");
/* }
/*     */ 
/*     */   @RequestMapping({"/email_unique.jspx"})
/*     */  oid checkEmail(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 228 */     String email = RequestUtils.getQueryParam(request, "email");
/*     */ 
/* 230 */     if (StringUtils.isBlank(email)) {
/* 231 */       ResponseUtils.renderJson(response, "false");
/* 232 */       return;
/*     */     }
/*     */ 
/* 235 */     if (this.userMng.emailExist(
/* 236 */    seUtils.renesponse, "false");
/* 237 */       return;
/*     */   9 */     ResponseUtils.renderJson(response, "true");
/*     */   }
/*     */ 
/*     */   privatrs validate(String checkcode, String username, String email, String password, HttpServletReques, HttpServletResponse response)
/*     */   {
/* 244 */     WebErrors errors = WebErrors.create(request);
/* 245 */     String id = this.session.ged(request, respo246 */     if (errors.ifOutOfLength(checkcode, "checkcode", 3, 10)) {
/* 247 */       return errors;
/*     */     }
/* 249 */     if (errors.ifOutOfLengtd, "password", 3, 32))
/* 250 */       return errors;
/*     */     try
/*   {
/* 253 */ (!this.capce.validateResponseForID(id,
/* 254 */         checpperCase(Locale.ENGLISH)).booleanValue())
/*     */       {
/* 255 */         errors.addErroror.checkcodeIncorrect");
/* 256 */         return errors;
/*     */       }
/*     */    (CaptchaServiceException e) {
/* 259 */       errors.addErrorCode("error.checkcodeInvalid");
/* 260 */       errors.addErrorString(e.getMessage(1 */       retur
/*     */     }
/* 263 */     if (errors.ifNotEmail(email, "email", 100)) {
/* 264 */       return errors;
/*     */     }
/* 266 */     if (this.uailExist(email)) {
/* 267 */       errors.addErrorCode("error.emailExist")*/       retur
/*     */ 270 */     if (errors.ifNotUsername(username, "username", 3, 100)) {
/* 271 */       return errors;
/*     */     }
/* 273 */     if (this.userMng.usernameExist(us{
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