package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Brand;
import guda.shop.cms.entity.Gift;
import guda.shop.cms.manager.GiftMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.core.web.WebErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GiftAct {
    private static final Logger log = LoggerFactory.getLogger(GiftAct.class);

    @Autowired
    private GiftMng manager;


    @RequestMapping({"/gift/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPageGift(SimplePage.cpn(pageNo),
                CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        return "gift/list";
    }

    @RequestMapping({"/gift/v_add.do"})
    public String add(HttpServletRequest request, ModelMap model) {

        return "gift/add";
    }

    @RequestMapping({"/gift/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("gift", this.manager.findById(id));

        return "gift/edit";
    }

    @RequestMapping({"/gift/o_save.do"})
    public String save(Gift bean, HttpServletRequest request, ModelMap model) {

        bean = this.manager.save(bean);

        log.info("save brand. id={}", bean.getId());

        return "redirect:v_list.do";
    }

    @RequestMapping({"/gift/o_update.do"})
    public String update(Gift gift, String text, Long[] typeIds, Integer pageNo, HttpServletRequest request, ModelMap model) {

        this.manager.updateByUpdater(gift);

        log.info("update brand. id={}.", gift.getId());

        return list(pageNo, request, model);
    }

    @RequestMapping({"/gift/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        Gift[] beans = this.manager.deleteByIds(ids);

        for (Gift bean : beans) {

            log.info("delete brand. id={}", bean.getId());
        }

        return list(pageNo, request, model);
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {

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

        Gift entity = this.manager.findById(id);

        return errors.ifNotExist(entity, Brand.class, id);
    }
}

