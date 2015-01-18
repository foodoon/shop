package guda.shop.cms.action.admin;

import guda.shop.cms.AdminMap;
import guda.shop.cms.entity.ShopAdmin;
import guda.shop.cms.service.LoginSvc;
import guda.shop.cms.web.SiteUtils;
import guda.shop.cms.web.threadvariable.AdminThread;
import guda.shop.common.security.BadCredentialsException;
import guda.shop.common.security.UsernameNotFoundException;
import guda.shop.core.entity.Website;
import guda.shop.core.security.UserNotInWebsiteException;
import guda.shop.core.web.WebErrors;
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
    private static final Logger log = LoggerFactory.getLogger(LoginAct.class);

    @Autowired
    private LoginSvc loginSvc;


    @RequestMapping(value = {"/index.do"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String index(ModelMap model) {
        ShopAdmin admin = AdminThread.get();

        if (admin != null) {

            model.addAttribute("admin", admin);

            return "redirect:main.do";
        }

        return "login";
    }

    @RequestMapping(value = {"/index.do"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String loginSubmit(String username, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        WebErrors errors = validateSubmit(username, request, response);

        if (!errors.hasErrors()) {

            Website web = SiteUtils.getWeb(request);
            try {

                this.loginSvc.adminLogin(request, response, web, username, password);

                log.info("admin '{}' login success.", username);

                return "redirect:index.do";
            } catch (UsernameNotFoundException e) {

                errors.addError(e.getMessage());

                log.info(e.getMessage());
            } catch (BadCredentialsException e) {

                if (!username.trim().equals("admin")) {

                    AdminMap.addAdminMapVal(username);
                }

                errors.addError(e.getMessage());

                log.info(e.getMessage());
            } catch (UserNotInWebsiteException e) {

                errors.addError(e.getMessage());

                log.info(e.getMessage());
            }
        }

        errors.toModel(model);

        return "login";
    }

    @RequestMapping({"/logout.do"})
    public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        this.loginSvc.logout(request, response);

        return "redirect:index.do";
    }

    private WebErrors validateSubmit(String username, HttpServletRequest request, HttpServletResponse response) {

        WebErrors errors = WebErrors.create(request);

        Integer errCount = AdminMap.getAdminMapVal(username);

        if ((errCount != null) && (errCount.intValue() >= 3)) {

            errors.addError("你的账号被锁定!");

            return errors;
        }

        return errors;
    }
}

