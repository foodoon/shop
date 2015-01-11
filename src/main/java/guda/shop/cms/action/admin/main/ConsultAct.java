package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Consult;
import guda.shop.cms.manager.ConsultMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.common.web.RequestUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import guda.shop.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class ConsultAct {
    private static final Logger log = LoggerFactory.getLogger(ConsultAct.class);

    @Autowired
    private ConsultMng manager;


    @RequestMapping({"/consult/v_list.do"})
    public String list(Date startTime, Date endTime, Integer pageNo, HttpServletRequest request, ModelMap model) {
        String userName = RequestUtils.getQueryParam(request, "userName");

        userName = StringUtils.trim(userName);

        String productName = RequestUtils.getQueryParam(request, "productName");

        productName = StringUtils.trim(productName);

        Pagination pagination = this.manager.getPage(null, userName, productName, startTime, endTime,
                SimplePage.cpn(pageNo), CookieUtils.getPageSize(request), Boolean.valueOf(true));


        if (!StringUtils.isBlank(userName)) {

            model.addAttribute("userName", userName);
        }

        if (!StringUtils.isBlank(productName)) {

            model.addAttribute("productName", productName);
        }


        model.addAttribute("startTime", startTime);

        model.addAttribute("endTime", endTime);


        model.addAttribute("pagination", pagination);

        model.addAttribute("pageNo", pageNo);

        return "consult/list";
    }

    @RequestMapping({"/consult/v_add.do"})
    public String add(ModelMap model) {

        return "consult/add";
    }

    @RequestMapping({"/consult/v_edit.do"})
    public String edit(Long id, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("consult", this.manager.findById(id));

        model.addAttribute("pageNo", pageNo);

        return "consult/edit";
    }

    @RequestMapping({"/consult/o_save.do"})
    public String save(Consult bean, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }


        log.info("save Consult id={}", bean.getId());

        return "redirect:v_list.do";
    }

    @RequestMapping({"/consult/o_update.do"})
    public String update(Long id, String adminReplay, HttpServletRequest request, ModelMap model, Integer pageNo) {

        Consult bean = this.manager.findById(id);

        bean.setAdminReplay(adminReplay);

        this.manager.update(bean);

        model.addAttribute("pageNo", pageNo);

        log.info("update Consult id={}.", bean.getId());

        return list(null, null, pageNo, request, model);
    }

    @RequestMapping({"/consult/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        Consult[] beans = this.manager.deleteByIds(ids);

        for (Consult bean : beans) {

            log.info("delete Consult id={}", bean.getId());
        }

        return list(null, null, Integer.valueOf(SimplePage.cpn(pageNo)), request, model);
    }

    private WebErrors validateSave(Consult bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);


        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        Website web = SiteUtils.getWeb(request);

        if (vldExist(id, web.getId(), errors)) {

            return errors;
        }

        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        Website web = SiteUtils.getWeb(request);

        if (vldExist(id, web.getId(), errors)) {

            return errors;
        }

        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        Website web = SiteUtils.getWeb(request);

        errors.ifEmpty(ids, "ids");

        for (Long id : ids) {

            vldExist(id, web.getId(), errors);
        }

        return errors;
    }

    private boolean vldExist(Long id, Long webId, WebErrors errors) {

        if (errors.ifNull(id, "id")) {

            return true;
        }

        Consult entity = this.manager.findById(id);

        if (errors.ifNotExist(entity, Consult.class, id)) {

            return true;
        }


        return false;
    }
}

