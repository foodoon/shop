/*     */ package guda.shop.cms.action.admin.main;
/*     */ 
/*     */ import guda.shop.cms.entity.DataBackup;
/*     */ import guda.shop.cms.manager.DataBackupMng;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.common.web.ResponseUtils;
/*     */ import guda.shop.core.entity.EmailSender;
/*     */ import guda.shop.core.entity.Global;
/*     */ import guda.shop.core.entity.MessageTemplate;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.manager.GlobalMng;
/*     */ import guda.shop.core.manager.UserMng;
/*     */ import guda.shop.core.manager.WebsiteMng;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ public class ConfigAct
/*     */ {
/*  42 */   private final Logger log = LoggerFactory.getLogger(ConfigAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private WebsiteMng websiteMng;
/*     */ 
/*     */   @Autowired
/*     */   private GlobalMng globalMng;
/*     */ 
/*     */   @Autowired
/*     */   private UserMng userMng;
/*     */ 
/*     */   @Autowired
/*     */   private DataBackupMng dataBackupMng;
/*     */ 
/*  46 */   @RequestMapping({"/config/v_global_edit.do"})
/*     */   public String globalEdit(HttpServletRequest request, ModelMap model) { model.addAttribute("global", SiteUtils.getWeb(request).getGlobal());
/*  47 */     return "config/global_edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_global_update.do"})
/*     */   public String globalUpdate(Global global, HttpServletRequest request, ModelMap model)
/*     */   {
/*  53 */     global.setId(SiteUtils.getWeb(request).getGlobal().getId());
/*  54 */     this.globalMng.update(global);
/*  55 */     this.log.info("update Global success.");
/*  56 */     model.addAttribute("message", "global.success");
/*  57 */     return globalEdit(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/v_basic_edit.do"})
/*     */   public String basicEdit(HttpServletRequest request, ModelMap model) {
/*  62 */     model.addAttribute("website", SiteUtils.getWeb(request));
/*  63 */     return "config/basic_edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_basic_update.do"})
/*     */   public String basicUpdate(Website website, HttpServletRequest request, ModelMap model)
/*     */   {
/*  69 */     website.setId(SiteUtils.getWebId(request));
/*  70 */     this.websiteMng.update(website);
/*  71 */     this.log.info("update website success. id={}", website.getId());
/*  72 */     model.addAttribute("message", "global.success");
/*  73 */     return basicEdit(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/v_shop_edit.do"})
/*     */   public String shopEdit(HttpServletRequest request, ModelMap model) {
/*  78 */     model.addAttribute("config", SiteUtils.getWeb(request));
/*  79 */     return "config/basic_edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_shop_update.do"})
/*     */   public String shopUpdate(Website website, HttpServletRequest request, ModelMap model)
/*     */   {
/*  85 */     website.setId(SiteUtils.getWebId(request));
/*  86 */     this.websiteMng.update(website);
/*  87 */     this.log.info("update website success. id={}", website.getId());
/*  88 */     model.addAttribute("message", "global.success");
/*  89 */     return basicEdit(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/v_email_edit.do"})
/*     */   public String emailEdit(HttpServletRequest request, ModelMap model) {
/*  94 */     Website web = SiteUtils.getWeb(request);
/*  95 */     model.addAttribute("emailSender", web.getEmailSender());
/*  96 */     model.addAttribute("messageMap", web.getMessages());
/*  97 */     return "config/email_edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_email_update.do"})
/*     */   public String emailUpdate(EmailSender emailSender, String resetPasswordSubject, String resetPasswordText, String activeTitle, String activeTxt, HttpServletRequest request, ModelMap model)
/*     */   {
/* 105 */     Website web = SiteUtils.getWeb(request);
/* 106 */     WebErrors errors = validateEmail(request);
/* 107 */     if (errors.hasErrors()) {
/* 108 */       return errors.showErrorPage(model);
/*     */     }
/* 110 */     web.setEmailSender(emailSender);
/* 111 */     Map messages = web.getMessages();
/* 112 */     MessageTemplate resetPassword = new MessageTemplate();
/* 113 */     resetPassword.setSubject(resetPasswordSubject);
/* 114 */     resetPassword.setText(resetPasswordText);
/* 115 */     resetPassword.setActiveTitle(activeTitle);
/* 116 */     resetPassword.setActiveTxt(activeTxt);
/* 117 */     messages.put("resetPassword", resetPassword);
/* 118 */     this.websiteMng.update(web);
/* 119 */     this.log.info("update EmailSender success.");
/* 120 */     return emailEdit(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_email_send_test.do"})
/*     */   public String sendEmailTest(EmailSender email, String to, MessageTemplate tpl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 127 */     Website web = SiteUtils.getWeb(request);
/*     */     try {
/* 129 */       String base = new String(web.getUrlBuff(true));
/* 130 */       this.userMng.senderEmail(Long.valueOf(0L), "Test_Username", base, to, "Test_ResetKey", 
/* 131 */         "Test_ResetPassword", email, tpl);
/* 132 */       ResponseUtils.renderJson(response, 
/* 133 */         new JSONObject().put("success", true).toString());
/*     */     } catch (Exception e) {
/* 135 */       JSONObject json = new JSONObject();
/* 136 */       json.put("success", false);
/* 137 */       json.put("message", e.getMessage());
/* 138 */       ResponseUtils.renderJson(response, json.toString());
/*     */     }
/* 140 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/v_dataBackup.do"})
/*     */   public String v_dataBackup(HttpServletRequest request, ModelMap model)
/*     */   {
/* 147 */     DataBackup dataBackup = this.dataBackupMng.getDataBackup();
/* 148 */     model.addAttribute("dataBackup", dataBackup);
/* 149 */     return "config/dataBackup";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_dataBackup.do"})
/*     */   public void o_dataBackup(DataBackup bean, HttpServletRequest request, ModelMap model, HttpServletResponse response)
/*     */     throws UnsupportedEncodingException, IOException
/*     */   {
/* 156 */     this.dataBackupMng.update(bean);
/* 157 */     response.setContentType("application/x-download;charset=UTF-8");
/* 158 */     response.setHeader("Content-Disposition", "attachment;filename=" + bean.getDataBaseName() + new Date().getTime() + ".sql");
/* 159 */     PrintWriter out = response.getWriter();
/* 160 */     out.print(backup(bean));
/* 161 */     out.flush();
/* 162 */     out.close();
/*     */   }
/*     */ 
/*     */   private String backup(DataBackup bean)
/*     */   {
/* 167 */     String outStr = "";
/*     */     try {
/* 169 */       Runtime rt = Runtime.getRuntime();
/* 170 */       Process child = rt.exec("cmd /c mysqldump -u" + bean.getUsername() + " -p" + bean.getPassword() + 
/* 171 */         " -h" + bean.getAddress() + " " + bean.getDataBaseName());
/* 172 */       InputStream in = child.getInputStream();
/* 173 */       InputStreamReader xx = new InputStreamReader(in, "utf-8");
/*     */ 
/* 175 */       StringBuffer sb = new StringBuffer("");
/* 176 */       BufferedReader br = new BufferedReader(xx);
/*     */       String inStr;
/* 177 */       while ((inStr = br.readLine()) != null)
/*     */       {
/*     */         String inStr;
/* 178 */         sb.append(inStr + "\r\n");
/*     */       }
/* 180 */       outStr = sb.toString();
/* 181 */       child.waitFor();
/* 182 */       in.close();
/* 183 */       xx.close();
/* 184 */       br.close();
/*     */     } catch (Exception e) {
/* 186 */       e.printStackTrace();
/*     */     }
/* 188 */     return outStr;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEmail(HttpServletRequest request) {
/* 192 */     WebErrors errors = WebErrors.create(request);
/* 193 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ConfigAct
 * JD-Core Version:    0.6.2
 */