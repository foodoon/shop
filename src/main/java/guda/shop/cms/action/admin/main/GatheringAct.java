package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Gathering;
import guda.shop.cms.manager.GatheringMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import guda.shop.core.web.WebErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GatheringAct {
    private static final Logger log = LoggerFactory.getLogger(GatheringAct.class);

    @Autowired
    private GatheringMng manager;


    @RequestMapping({"/Gathering/list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo),
                CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        return "Gathering/list";
    }

    @RequestMapping({"/Gathering/add.do"})
    public String add(ModelMap model) {

        return "Gathering/add";
    }

    @RequestMapping({"/Gathering/edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("gathering", this.manager.findById(id));

        model.addAttribute("order", this.manager.findById(id).getIndent());

        return "Gathering/edit";
    }

    @RequestMapping({"/Gathering/o_save.do"})
    public String save(Gathering bean, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.save(bean);

        log.info("save Gathering id={}", bean.getId());

        return "redirect:list.do";
    }

    @RequestMapping({"/Gathering/o_update.do"})
    public String update(Gathering bean, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean.getId(), request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.update(bean);

        log.info("update Gathering id={}.", bean.getId());

        return list(pageNo, request, model);
    }

    @RequestMapping({"/Gathering/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        Gathering[] beans = this.manager.deleteByIds(ids);

        for (Gathering bean : beans) {

            log.info("delete Gathering id={}", bean.getId());
        }

        return list(pageNo, request, model);
    }

    private WebErrors validateSave(Gathering bean, HttpServletRequest request) {

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

        Gathering entity = this.manager.findById(id);

        if (errors.ifNotExist(entity, Gathering.class, id)) {

            return true;
        }

        return false;
    }
}

