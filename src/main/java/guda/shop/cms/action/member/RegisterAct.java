package guda.shop.cms.action.member;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
import guda.shop.cms.entity.ShopConfig;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.entity.ShopScore;
import guda.shop.cms.manager.ShopMemberMng;
import guda.shop.cms.manager.ShopScoreMng;
import guda.shop.cms.web.ShopFrontHelper;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.web.RequestUtils;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.session.SessionProvider;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.EmailSender;
import guda.shop.core.entity.Member;
import guda.shop.core.entity.MessageTemplate;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.MemberMng;
import guda.shop.core.manager.UserMng;
import guda.shop.core.web.WebErrors;
import guda.shop.core.web.front.FrontHelper;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegisterAct {
    private static final Logger log = LoggerFactory.getLogger(RegisterAct.class);
    private static final String REGISTER = "tpl.register";
    private static final String REGISTER_RESULT = "tpl.registerResult";
    private static final String REGISTER_TREATY = "tpl.registerTreaty";
    private static final String REGISTER_ACTIVE_STATUS = "tpl.registerActiveStatus";

    @Autowired
    private UserMng userMng;

    @Autowired
    private ShopMemberMng shopMemberMng;

    @Autowired
    private ShopScoreMng shopScoreMng;

    @Autowired
    private MemberMng memberMng;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private SessionProvider session;

    @RequestMapping(value = {"/register.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String registerInput(HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.register", new Object[0]));
    }

    @RequestMapping(value = {"/register.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public String registerSubmit(String checkcode, String username, String email, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        ShopConfig config = SiteUtils.getConfig(request);

        WebErrors errors = validate(checkcode, username, email, password, request, response);

        if (errors.hasErrors()) {

            return FrontHelper.showError(errors, web, model, request);
        }

        ShopFrontHelper.setCommonData(request, model, web, 1);

        EmailSender sender = web.getEmailSender();

        MessageTemplate tpl = (MessageTemplate) web.getMessages().get("resetPassword");


        if (sender == null) {

            model.addAttribute("status", Integer.valueOf(2));

        } else if (tpl == null) {

            model.addAttribute("status", Integer.valueOf(3));
        } else {
            try {

                String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');

                String base = new String(web.getUrlBuff(true));

                this.userMng.senderActiveEmail(username, base, email, uuid, sender, tpl);

                ShopMember member = this.shopMemberMng.register(username, password, email, Boolean.valueOf(false), uuid,
                        request.getRemoteAddr(), Boolean.valueOf(false), web.getId(), config.getRegisterGroup().getId());


                String emailtype = email.substring(email.indexOf("@") + 1, email.indexOf("."));

                model.addAttribute("emailtype", emailtype);

                model.addAttribute("member", member);

                model.addAttribute("status", Integer.valueOf(1));

                log.info("register member '{}'", member.getUsername());
            } catch (Exception e) {

                model.addAttribute("status", Integer.valueOf(4));

                model.addAttribute("message", e.getMessage());

                log.error("send email exception {}.", e.getMessage());
            }
        }

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.registerResult", new Object[0]));
    }

    @RequestMapping(value = {"/active.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String active(String userName, String activationCode, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws IOException {

        Website web = SiteUtils.getWeb(request);

        Member bean = this.memberMng.getByUsername(web.getId(), userName);

        long l = System.currentTimeMillis();

        l -= 86400000L;

        Date date = new Date();

        date.setTime(l);

        if ((StringUtils.isBlank(String.valueOf(userName))) || (StringUtils.isBlank(activationCode))) {

            model.addAttribute("status", Integer.valueOf(2));

        } else if (bean == null) {

            model.addAttribute("status", Integer.valueOf(3));

        } else if (bean.getActive().booleanValue()) {

            model.addAttribute("status", Integer.valueOf(4));

        } else if (!bean.getActivationCode().equals(activationCode)) {

            model.addAttribute("status", Integer.valueOf(5));

        } else if (bean.getCreateTime().compareTo(date) < 0) {

            model.addAttribute("status", Integer.valueOf(6));
        } else {

            bean.setActive(Boolean.valueOf(true));

            this.memberMng.update(bean);


            this.shopMemberMng.updateScore(this.shopMemberMng.findById(bean.getId()),
                    SiteUtils.getWeb(request).getGlobal().getActiveScore());

            ShopScore shopScore = new ShopScore();

            shopScore.setMember(this.shopMemberMng.findById(bean.getId()));

            shopScore.setName("邮箱验证送积分");

            shopScore.setScoreTime(new Date());

            shopScore.setStatus(true);

            shopScore.setUseStatus(false);

            shopScore.setScoreType(Integer.valueOf(ShopScore.ScoreTypes.EMAIL_VALIDATE.getValue()));

            this.shopScoreMng.save(shopScore);

            model.addAttribute("status", Integer.valueOf(1));

            model.addAttribute("member", bean);
        }

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member", MessageResolver.getMessage(request, "tpl.registerActiveStatus", new Object[0]));
    }

    @RequestMapping(value = {"/reactive.jspx"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public void reactive(Long userId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws IOException {

        Website web = SiteUtils.getWeb(request);

        JSONObject json = new JSONObject();

        Member bean = this.memberMng.findById(userId);

        if (bean.getActive().booleanValue()) {
            try {

                json.put("data", 1);
            } catch (JSONException e) {

                e.printStackTrace();
            }
        } else {

            String uuid = StringUtils.remove(UUID.randomUUID().toString(), '-');

            bean.setActivationCode(uuid);

            bean.setCreateTime(new Date());

            this.memberMng.update(bean);

            String base = new String(web.getUrlBuff(true));

            EmailSender sender = web.getEmailSender();

            Map messages = web.getMessages();

            MessageTemplate tpl = (MessageTemplate) messages.get("resetPassword");
            try {

                this.userMng.senderActiveEmail(bean.getUsername(), base, bean.getEmail(), uuid, sender, tpl);
                try {

                    json.put("data", 2);
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            } catch (Exception e) {
                try {

                    json.put("data", 3);
                } catch (JSONException e1) {

                    e1.printStackTrace();
                }
            }
        }


        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/treaty.jspx"})
    public String treaty(HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        model.addAttribute("global", SiteUtils.getWeb(request).getGlobal());

        ShopFrontHelper.setCommonData(request, model, web, 1);

        return web.getTplSys("member",
                MessageResolver.getMessage(request,
                        "tpl.registerTreaty", new Object[0])
        );
    }

    @RequestMapping({"/username_unique.jspx"})
    public void checkUsername(HttpServletRequest request, HttpServletResponse response) {

        String username = RequestUtils.getQueryParam(request, "username");


        if (StringUtils.isBlank(username)) {

            ResponseUtils.renderJson(response, "false");

            return;
        }


        if (this.userMng.usernameExist(username)) {

            ResponseUtils.renderJson(response, "false");

            return;
        }

        ResponseUtils.renderJson(response, "true");
    }

    @RequestMapping({"/email_unique.jspx"})
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) {

        String email = RequestUtils.getQueryParam(request, "email");


        if (StringUtils.isBlank(email)) {

            ResponseUtils.renderJson(response, "false");

            return;
        }


        if (this.userMng.emailExist(email)) {

            ResponseUtils.renderJson(response, "false");

            return;
        }

        ResponseUtils.renderJson(response, "true");
    }

    private WebErrors validate(String checkcode, String username, String email, String password, HttpServletRequest request, HttpServletResponse response) {

        WebErrors errors = WebErrors.create(request);

        String id = this.session.getSessionId(request, response);

        if (errors.ifOutOfLength(checkcode, "checkcode", 3, 10)) {

            return errors;
        }

        if (errors.ifOutOfLength(password, "password", 3, 32))
            return errors;
        try {

            if (!this.captchaService.validateResponseForID(id,
                    checkcode.toUpperCase(Locale.ENGLISH)).booleanValue()) {

                errors.addErrorCode("error.checkcodeIncorrect");

                return errors;
            }
        } catch (CaptchaServiceException e) {

            errors.addErrorCode("error.checkcodeInvalid");

            errors.addErrorString(e.getMessage());

            return errors;
        }

        if (errors.ifNotEmail(email, "email", 100)) {

            return errors;
        }

        if (this.userMng.emailExist(email)) {

            errors.addErrorCode("error.emailExist");

            return errors;
        }

        if (errors.ifNotUsername(username, "username", 3, 100)) {

            return errors;
        }

        if (this.userMng.usernameExist(username)) {

            errors.addErrorCode("error.usernameExist");

            return errors;
        }

        return errors;
    }
}

