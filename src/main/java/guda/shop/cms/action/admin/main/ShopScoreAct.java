package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.ShopScore;
import guda.shop.cms.manager.ShopScoreMng;
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
public class ShopScoreAct {
       private static final Logger log = LoggerFactory.getLogger(ShopScoreAct.class);

    @Autowired
    private ShopScoreMng manager;


    @RequestMapping({"/shopScore/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(null, Boolean.valueOf(false), Boolean.valueOf(false), null, null, Integer.valueOf(SimplePage.cpn(pageNo)),
       Integer.valueOf(CookieUtils.getPageSize(request)));

        model.addAttribute("pagination", pagination);

        return "shopScore/list";
    }

    @RequestMapping({"/shopScore/v_add.do"})
    public String add(ModelMap model) {

        return "shopScore/add";
    }

    @RequestMapping({"/shopScore/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("shopScore", this.manager.findById(id));

        return "shopScore/edit";
    }

    @RequestMapping({"/shopScore/o_save.do"})
    public String save(ShopScore bean, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.save(bean);

        log.info("save ShopScore id={}", bean.getId());

        return "redirect:v_list.do";
    }

    @RequestMapping({"/shopScore/o_update.do"})
    public String update(ShopScore bean, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean.getId(), request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.update(bean);

        log.info("update ShopScore id={}.", bean.getId());

        return list(pageNo, request, model);
    }

    @RequestMapping({"/shopScore/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        ShopScore[] beans = this.manager.deleteByIds(ids);

        for (ShopScore bean : beans) {

            log.info("delete ShopScore id={}", bean.getId());
        }

        return list(pageNo, request, model);
    }

    private WebErrors validateSave(ShopScore bean, HttpServletRequest request) {

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

        ShopScore entity = this.manager.findById(id);

        if (errors.ifNotExist(entity, ShopScore.class, id)) {

            return true;
        }


        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShopScoreAct
 * JD-Core Version:    0.6.2
 */