/*    */ package guda.shop.cms.action.admin;
/*    */ 
/*    */ import guda.shop.cms.AdminMap;
/*    */ import guda.shop.cms.entity.ShopAdmin;
/*    */ import guda.shop.cms.service.LoginSvc;
/*    */ import guda.shop.cms.web.SiteUtils;
/*    */ import guda.shop.cms.web.threadvariable.AdminThread;
/*    */ import guda.shop.common.security.BadCredentialsException;
/*    */ import guda.shop.common.security.UsernameNotFoundException;
/*    */ import guda.shop.core.entity.Website;
/*    */ import guda.shop.core.security.UserNotInWebsiteException;
/*    */ import guda.shop.core.web.WebErrors;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class LoginAct
/*    */ {
/* 30 */   private static final Logger log = LoggerFactory.getLogger(LoginAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private LoginSvc loginSvc;
/*    */ 
/* 34 */   @RequestMapping(value={"/index.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String index(ModelMap model) { ShopAdmin admin = AdminThread.get();
/* 35 */     if (admin != null) {
/* 36 */       model.addAttribute("admin", admin);
/* 37 */       return "index";
/*    */     }
/* 39 */     return "login";
/*    */   }
/*    */ 
/*    */   @RequestMapping(value={"/index.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*    */   public String loginSubmit(String username, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 49 */     WebErrors errors = validateSubmit(username, request, response);
/* 50 */     if (!errors.hasErrors()) {
/* 51 */       Website web = SiteUtils.getWeb(request);
/*    */       try {
/* 53 */         this.loginSvc.adminLogin(request, response, web, username, password);
/* 54 */         log.info("admin '{}' login success.", username);
/* 55 */         return "redirect:index.do";
/*    */       } catch (UsernameNotFoundException e) {
/* 57 */         errors.addError(e.getMessage());
/* 58 */         log.info(e.getMessage());
/*    */       } catch (BadCredentialsException e) {
/* 60 */         if (!username.trim().equals("admin")) {
/* 61 */           AdminMap.addAdminMapVal(username);
/*    */         }
/* 63 */         errors.addError(e.getMessage());
/* 64 */         log.info(e.getMessage());
/*    */       } catch (UserNotInWebsiteException e) {
/* 66 */         errors.addError(e.getMessage());
/* 67 */         log.info(e.getMessage());
/*    */       }
/*    */     }
/* 70 */     errors.toModel(model);
/* 71 */     return "login";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/logout.do"})
/*    */   public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 76 */     this.loginSvc.logout(request, response);
/* 77 */     return "redirect:index.do";
/*    */   }
/*    */ 
/*    */   private WebErrors validateSubmit(String username, HttpServletRequest request, HttpServletResponse response) {
/* 81 */     WebErrors errors = WebErrors.create(request);
/* 82 */     Integer errCount = AdminMap.getAdminMapVal(username);
/* 83 */     if ((errCount != null) && (errCount.intValue() >= 3)) {
/* 84 */       errors.addError("你的账号被锁定!");
/* 85 */       return errors;
/*    */     }
/* 87 */     return errors;
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.LoginAct
 * JD-Core Version:    0.6.2
 */