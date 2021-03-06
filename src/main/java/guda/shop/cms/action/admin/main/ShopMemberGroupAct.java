package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.ShopMemberGroup;
import guda.shop.cms.manager.ShopMemberGroupMng;
import guda.shop.cms.web.SiteUtils;
import guda.shop.core.web.WebErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShopMemberGroupAct {
    private static final Logger log = LoggerFactory.getLogger(ShopMemberGroupAct.class);

    @Autowired
    private ShopMemberGroupMng manager;


    @RequestMapping({"/group/list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        List list = this.manager.getList(
                SiteUtils.getWebId(request));

        model.addAttribute("list", list);

        return "group/list";
    }

    @RequestMapping({"/group/add.do"})
    public String add(ModelMap model) {

        return "group/add";
    }

    @RequestMapping({"/group/edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("group", this.manager.findById(id));

        return "group/edit";
    }

    @RequestMapping({"/group/o_save.do"})
    public String save(ShopMemberGroup bean, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.save(bean);

        log.info("save ShopMemberGroup. id={}", bean.getId());

        return "redirect:list.do";
    }

    @RequestMapping({"/group/o_update.do"})
    public String update(ShopMemberGroup bean, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean.getId(), request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.update(bean);

        log.info("update ShopMemberGroup. id={}.", bean.getId());

        return list(pageNo, request, model);
    }

    @RequestMapping({"/group/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        ShopMemberGroup[] beans = this.manager.deleteByIds(ids);

        for (ShopMemberGroup bean : beans) {

            log.info("delete ShopMemberGroup. id={}", bean.getId());
        }

        return list(pageNo, request, model);
    }

    private WebErrors validateSave(ShopMemberGroup bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        bean.setWebsite(SiteUtils.getWeb(request));

        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifNull(id, "id");

        vldExist(id, errors);

        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

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

    private void vldExist(Long id, WebErrors errors) {

        if (errors.hasErrors()) {

            return;
        }

        ShopMemberGroup entity = this.manager.findById(id);

        errors.ifNotExist(entity, ShopMemberGroup.class, id);
    }
}

