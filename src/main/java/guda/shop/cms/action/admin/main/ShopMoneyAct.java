package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.ShopMoney;
import guda.shop.cms.manager.ShopMoneyMng;
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
public class ShopMoneyAct {
    private static final Logger log = LoggerFactory.getLogger(ShopMoneyAct.class);

    @Autowired
    private ShopMoneyMng manager;


    @RequestMapping({"/shopMoney/list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo),
                CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        return "shopMoney/list";
    }

    @RequestMapping({"/shopMoney/add.do"})
    public String add(ModelMap model) {

        return "shopMoney/add";
    }

    @RequestMapping({"/shopMoney/edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("shopMoney", this.manager.findById(id));

        return "shopMoney/edit";
    }

    @RequestMapping({"/shopMoney/o_save.do"})
    public String save(ShopMoney bean, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.save(bean);

        log.info("save ShopMoney id={}", bean.getId());

        return "redirect:list.do";
    }

    @RequestMapping({"/shopMoney/o_update.do"})
    public String update(ShopMoney bean, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean.getId(), request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.update(bean);

        log.info("update ShopMoney id={}.", bean.getId());

        return list(pageNo, request, model);
    }

    @RequestMapping({"/shopMoney/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        ShopMoney[] beans = this.manager.deleteByIds(ids);

        for (ShopMoney bean : beans) {

            log.info("delete ShopMoney id={}", bean.getId());
        }

        return list(pageNo, request, model);
    }

    private WebErrors validateSave(ShopMoney bean, HttpServletRequest request) {

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

        ShopMoney entity = this.manager.findById(id);

        if (errors.ifNotExist(entity, ShopMoney.class, id)) {

            return true;
        }


        return false;
    }
}

