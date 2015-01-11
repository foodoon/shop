package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.DataBackup;
import guda.shop.cms.manager.DataBackupMng;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.web.ResponseUtils;
import guda.shop.core.entity.EmailSender;
import guda.shop.core.entity.Global;
import guda.shop.core.entity.MessageTemplate;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.GlobalMng;
import guda.shop.core.manager.UserMng;
import guda.shop.core.manager.WebsiteMng;
import guda.shop.core.web.WebErrors;
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
import java.io.*;
import java.util.Date;
import java.util.Map;

@Controller
public class ConfigAct {
    private final Logger log = LoggerFactory.getLogger(ConfigAct.class);

    @Autowired
    private WebsiteMng websiteMng;

    @Autowired
    private GlobalMng globalMng;

    @Autowired
    private UserMng userMng;

    @Autowired
    private DataBackupMng dataBackupMng;


    @RequestMapping({"/config/v_global_edit.do"})
    public String globalEdit(HttpServletRequest request, ModelMap model) {
        model.addAttribute("global", SiteUtils.getWeb(request).getGlobal());

        return "config/global_edit";
    }

    @RequestMapping({"/config/o_global_update.do"})
    public String globalUpdate(Global global, HttpServletRequest request, ModelMap model) {

        global.setId(SiteUtils.getWeb(request).getGlobal().getId());

        this.globalMng.update(global);

        this.log.info("update Global success.");

        model.addAttribute("message", "global.success");

        return globalEdit(request, model);
    }

    @RequestMapping({"/config/v_basic_edit.do"})
    public String basicEdit(HttpServletRequest request, ModelMap model) {

        model.addAttribute("website", SiteUtils.getWeb(request));

        return "config/basic_edit";
    }

    @RequestMapping({"/config/o_basic_update.do"})
    public String basicUpdate(Website website, HttpServletRequest request, ModelMap model) {

        website.setId(SiteUtils.getWebId(request));

        this.websiteMng.update(website);

        this.log.info("update website success. id={}", website.getId());

        model.addAttribute("message", "global.success");

        return basicEdit(request, model);
    }

    @RequestMapping({"/config/v_shop_edit.do"})
    public String shopEdit(HttpServletRequest request, ModelMap model) {

        model.addAttribute("config", SiteUtils.getWeb(request));

        return "config/basic_edit";
    }

    @RequestMapping({"/config/o_shop_update.do"})
    public String shopUpdate(Website website, HttpServletRequest request, ModelMap model) {

        website.setId(SiteUtils.getWebId(request));

        this.websiteMng.update(website);

        this.log.info("update website success. id={}", website.getId());

        model.addAttribute("message", "global.success");

        return basicEdit(request, model);
    }

    @RequestMapping({"/config/v_email_edit.do"})
    public String emailEdit(HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        model.addAttribute("emailSender", web.getEmailSender());

        model.addAttribute("messageMap", web.getMessages());

        return "config/email_edit";
    }

    @RequestMapping({"/config/o_email_update.do"})
    public String emailUpdate(EmailSender emailSender, String resetPasswordSubject, String resetPasswordText, String activeTitle, String activeTxt, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);

        WebErrors errors = validateEmail(request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        web.setEmailSender(emailSender);

        Map messages = web.getMessages();

        MessageTemplate resetPassword = new MessageTemplate();

        resetPassword.setSubject(resetPasswordSubject);

        resetPassword.setText(resetPasswordText);

        resetPassword.setActiveTitle(activeTitle);

        resetPassword.setActiveTxt(activeTxt);

        messages.put("resetPassword", resetPassword);

        this.websiteMng.update(web);

        this.log.info("update EmailSender success.");

        return emailEdit(request, model);
    }

    @RequestMapping({"/config/o_email_send_test.do"})
    public String sendEmailTest(EmailSender email, String to, MessageTemplate tpl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws JSONException {

        Website web = SiteUtils.getWeb(request);
        try {

            String base = new String(web.getUrlBuff(true));

            this.userMng.senderEmail(Long.valueOf(0L), "Test_Username", base, to, "Test_ResetKey",
                    "Test_ResetPassword", email, tpl);

            ResponseUtils.renderJson(response,
                    new JSONObject().put("success", true).toString());
        } catch (Exception e) {

            JSONObject json = new JSONObject();

            json.put("success", false);

            json.put("message", e.getMessage());

            ResponseUtils.renderJson(response, json.toString());
        }

        return null;
    }

    @RequestMapping({"/config/v_dataBackup.do"})
    public String v_dataBackup(HttpServletRequest request, ModelMap model) {

        DataBackup dataBackup = this.dataBackupMng.getDataBackup();

        model.addAttribute("dataBackup", dataBackup);

        return "config/dataBackup";
    }

    @RequestMapping({"/config/o_dataBackup.do"})
    public void o_dataBackup(DataBackup bean, HttpServletRequest request, ModelMap model, HttpServletResponse response)
            throws UnsupportedEncodingException, IOException {

        this.dataBackupMng.update(bean);

        response.setContentType("application/x-download;charset=UTF-8");

        response.setHeader("Content-Disposition", "attachment;filename=" + bean.getDataBaseName() + new Date().getTime() + ".sql");

        PrintWriter out = response.getWriter();

        out.print(backup(bean));

        out.flush();

        out.close();
    }

    private String backup(DataBackup bean) {

        String outStr = "";
        try {

            Runtime rt = Runtime.getRuntime();

            Process child = rt.exec("cmd /c mysqldump -u" + bean.getUsername() + " -p" + bean.getPassword() +
                    " -h" + bean.getAddress() + " " + bean.getDataBaseName());

            InputStream in = child.getInputStream();

            InputStreamReader xx = new InputStreamReader(in, "utf-8");


            StringBuffer sb = new StringBuffer("");

            BufferedReader br = new BufferedReader(xx);
            String inStr;

            while ((inStr = br.readLine()) != null) {

                sb.append(inStr + "\r\n");
            }

            outStr = sb.toString();

            child.waitFor();

            in.close();

            xx.close();

            br.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return outStr;
    }

    private WebErrors validateEmail(HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        return errors;
    }
}

