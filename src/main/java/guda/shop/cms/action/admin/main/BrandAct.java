package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Brand;
import guda.shop.cms.manager.BrandMng;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.util.CnToSpell;
import guda.shop.common.util.StrUtils;
import guda.shop.common.web.ResponseUtils;
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
import java.util.List;

@Controller
public class BrandAct {
    private static final Logger log = LoggerFactory.getLogger(BrandAct.class);

    @Autowired
    private BrandMng manager;


    @RequestMapping({"/brand/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        List list = this.manager.getAllList();

        model.addAttribute("list", list);

        return "brand/list";
    }

    @RequestMapping({"/brand/v_add.do"})
    public String add(HttpServletRequest request, ModelMap model) {

        return "brand/add";
    }

    @RequestMapping({"/brand/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("brand", this.manager.findById(id));

        return "brand/edit";
    }

    @RequestMapping({"/brand/o_save.do"})
    public String save(Brand bean, String text, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }


        String name = bean.getName();

        CnToSpell cts = new CnToSpell();

        bean.setFirstCharacter(cts.getBeginCharacter(name).substring(0, 1));

        bean = this.manager.save(bean, text);

        log.info("save brand. id={}", bean.getId());

        return "redirect:v_list.do";
    }

    @RequestMapping({"/brand/o_update.do"})
    public String update(Brand bean, String text, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }


        String name = bean.getName();

        CnToSpell cts = new CnToSpell();

        bean.setFirstCharacter(cts.getBeginCharacter(name).substring(0, 1));

        bean = this.manager.update(bean, text);

        log.info("update brand. id={}.", bean.getId());

        return list(pageNo, request, model);
    }

    @RequestMapping({"/brand/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        Brand[] beans = this.manager.deleteByIds(ids);

        for (Brand bean : beans) {

            log.info("delete brand. id={}", bean.getId());
        }

        return list(pageNo, request, model);
    }

    @RequestMapping({"/brand/o_priority.do"})
    public String priority(Long[] wids, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validatePriority(wids, priority, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        this.manager.updatePriority(wids, priority);

        model.addAttribute("message", "global.success");

        return list(pageNo, request, model);
    }

    @RequestMapping({"/brand/v_check_brandName.do"})
    public void checkUsername(String name, HttpServletResponse response) {
        String pass;

        if (StringUtils.isBlank(name))
            pass = "false";
        else {

            pass = this.manager.brandNameNotExist(name) ? "true" : "false";
        }

        ResponseUtils.renderJson(response, pass);
    }

    private WebErrors validateSave(Brand bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        bean.setWebUrl(StrUtils.handelUrl(bean.getWebUrl()));

        bean.setWebsite(SiteUtils.getWeb(request));

        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifNull(id, "id");

        vldExist(id, errors);

        return errors;
    }

    private WebErrors validateUpdate(Brand bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        Long id = bean.getId();

        bean.setWebUrl(StrUtils.handelUrl(bean.getWebUrl()));

        errors.ifNull(id, "id");

        vldExist(id, errors);

        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifEmpty(ids, "ids");

        for (Long id : ids) {

            vldExist(id, errors);
        }

        return errors;
    }

    private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        if (errors.ifEmpty(wids, "wids")) {

            return errors;
        }

        if (errors.ifEmpty(priority, "priority")) {

            return errors;
        }

        if (wids.length != priority.length) {

            errors.addErrorString("wids length not equals priority length");

            return errors;
        }

        int i = 0;
        for (int len = wids.length; i < len; i++) {

            vldExist(wids[i], errors);

            if (priority[i] == null) {

                priority[i] = Integer.valueOf(0);
            }
        }

        return errors;
    }

    private boolean vldExist(Long id, WebErrors errors) {

        Brand entity = this.manager.findById(id);

        return errors.ifNotExist(entity, Brand.class, id);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.BrandAct
 * JD-Core Version:    0.6.2
 */