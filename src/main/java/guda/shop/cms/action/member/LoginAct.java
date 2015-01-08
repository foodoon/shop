package guda.shop.cms.action.member;

import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.service.LoginSvc;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.security.BadCredentialsException;
import guda.shop.common.security.UserNotAcitveException;
import guda.shop.common.security.UsernameNotFoundException;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.User;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.UserMng;
import guda.shop.core.security.UserNotInWebsiteException;
import guda.shop.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginAct {
    public static final String TPL_INDEX = "tpl.index";
    /*  38 */   private static final Logger log = LoggerFactory.getLogger(LoginAct.class);
    private static final String LOGIN_INPUT = "tpl.loginInput";
    @Autowired
    private LoginSvc loginSvc;

    @Autowired
    private UserMng userMng;

    @RequestMapping(value = {"/login.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String loginInput(String returnUrl, String message, HttpServletRequest request, ModelMap model) {
/*  47 */
        Website web = SiteUtils.getWeb(request);
/*  48 */
        if (!StringUtils.isBlank(returnUrl)) {
/*  49 */
            model.addAttribute("returnUrl", returnUrl);
/*  50 */
            if (!StringUtils.isBlank(message)) {
/*  51 */
                model.addAttribute("message", message);
            }
        }
/*  54 */
        ShopFrontHelper.setCommonData(request, model, web, 1);
/*  55 */
        return web.getTplSys("member",
/*  56 */       MessageResolver.getMessage(request, 
/*  56 */       "tpl.loginInput", new Object[0]));
    }

    @RequestMapping(value = {"/login.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String loginSubmit(String username, String password, String returnUrl, String redirectUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/*  64 */
        Website web = SiteUtils.getWeb(request);
/*  65 */
        WebErrors errors = WebErrors.create(request);
        try {
/*  68 */
            ShopMember member = this.loginSvc.memberLogin(request, response, web, username, password);
/*  69 */
            if (member == null) {
/*  70 */
                return "redirect:/";
            }
/*  72 */
            log.info("member '{}' login success.", username);
/*  73 */
            if (!StringUtils.isBlank(returnUrl))
/*  74 */ return "redirect:" + returnUrl;
/*  75 */
            if (!StringUtils.isBlank(redirectUrl)) {
/*  76 */
                return "redirect:" + redirectUrl;
            }
/*  78 */
            model.addAttribute("member", member);
/*  79 */
            ShopFrontHelper.setCommonData(request, model, web, 1);

/*  81 */
            return web.getTemplate("index",
/*  82 */         MessageResolver.getMessage(request, 
/*  82 */         "tpl.index", new Object[0]));
        } catch (UsernameNotFoundException e) {
/*  85 */
            errors.addErrorCode("error.usernameNotExist");
/*  86 */
            log.info(e.getMessage());
        } catch (BadCredentialsException e) {
/*  88 */
            errors.addErrorCode("error.passwordInvalid");
/*  89 */
            log.info(e.getMessage());
        } catch (UserNotInWebsiteException e) {
/*  91 */
            errors.addErrorCode("error.usernameNotInWebsite");
/*  92 */
            log.info(e.getMessage());
        } catch (UserNotAcitveException e) {
/*  94 */
            errors.addErrorCode("error.usernameNotActivated");
/*  95 */
            log.info(e.getMessage());
        }
/*  97 */
        errors.toModel(model);
/*  98 */
        ShopFrontHelper.setCommonData(request, model, web, 1);
/*  99 */
        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.loginInput", new Object[0]));
    }

    public Integer errorRemaining(String username) {
/* 103 */
        if (StringUtils.isBlank(username)) {
/* 104 */
            return null;
        }
/* 106 */
        User user = this.userMng.getByUsername(username);
/* 107 */
        if (user == null) {
/* 108 */
            return null;
        }
/* 110 */
        return null;
    }

    @RequestMapping({"/logout.jspx"})
    public String logout(String redirectUrl, HttpServletRequest request, HttpServletResponse response) {
/* 117 */
        this.loginSvc.logout(request, response);
/* 118 */
        if (!StringUtils.isBlank(redirectUrl)) {
/* 119 */
            return "redirect:" + redirectUrl;
        }
/* 121 */
        return "redirect:/";
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.LoginAct
 * JD-Core Version:    0.6.2
 */