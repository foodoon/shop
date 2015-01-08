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
    private static final Logger log = LoggerFactory.getLogger(LoginAct.class);
    private static final String LOGIN_INPUT = "tpl.loginInput";
    @Autowired
    private LoginSvc loginSvc;

    @Autowired
    private UserMng userMng;

    @RequestMapping(value = {"/login.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String loginInput(String returnUrl, String message, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        if (!StringUtils.isBlank(returnUrl)) {

            model.addAttribute("returnUrl", returnUrl);

            if (!StringUtils.isBlank(message)) {

                model.addAttribute("message", message);
            }
        }

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member",
                MessageResolver.getMessage(request,
                        "tpl.loginInput", new Object[0])
        );
    }

    @RequestMapping(value = {"/login.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String loginSubmit(String username, String password, String returnUrl, String redirectUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        WebErrors errors = WebErrors.create(request);
        try {

            ShopMember member = this.loginSvc.memberLogin(request, response, web, username, password);

            if (member == null) {

                return "redirect:/";
            }

            log.info("member '{}' login success.", username);

            if (!StringUtils.isBlank(returnUrl))
                return "redirect:" + returnUrl;

            if (!StringUtils.isBlank(redirectUrl)) {

                return "redirect:" + redirectUrl;
            }

            model.addAttribute("member", member);

            ShopFrontHelper.setCommonData(request, model, web, 1);


            return web.getTemplate("index",
                    MessageResolver.getMessage(request,
                            "tpl.index", new Object[0])
            );
        } catch (UsernameNotFoundException e) {

            errors.addErrorCode("error.usernameNotExist");

            log.info(e.getMessage());
        } catch (BadCredentialsException e) {

            errors.addErrorCode("error.passwordInvalid");

            log.info(e.getMessage());
        } catch (UserNotInWebsiteException e) {

            errors.addErrorCode("error.usernameNotInWebsite");

            log.info(e.getMessage());
        } catch (UserNotAcitveException e) {

            errors.addErrorCode("error.usernameNotActivated");

            log.info(e.getMessage());
        }

        errors.toModel(model);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.loginInput", new Object[0]));
    }

    public Integer errorRemaining(String username) {

        if (StringUtils.isBlank(username)) {

            return null;
        }

        User user = this.userMng.getByUsername(username);

        if (user == null) {

            return null;
        }

        return null;
    }

    @RequestMapping({"/logout.jspx"})
    public String logout(String redirectUrl, HttpServletRequest request, HttpServletResponse response) {

        this.loginSvc.logout(request, response);

        if (!StringUtils.isBlank(redirectUrl)) {

            return "redirect:" + redirectUrl;
        }

        return "redirect:/";
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.member.LoginAct
 * JD-Core Version:    0.6.2
 */