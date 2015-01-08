package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.GiftExchange;
import guda.shop.cms.manager.GiftExchangeMng;
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
public class GiftExchangeAct {
    private static final Logger log = LoggerFactory.getLogger(GiftExchangeAct.class);

    @Autowired
    private GiftExchangeMng manager;


    @RequestMapping({"/exchange/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo),
                CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        return "exchange/list";
    }

    @RequestMapping({"/exchange/v_add.do"})
    public String add(ModelMap model) {

        return "exchange/add";
    }

    @RequestMapping({"/exchange/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("giftExchange", this.manager.findById(id));

        return "exchange/edit";
    }

    @RequestMapping({"/exchange/o_save.do"})
    public String save(GiftExchange bean, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.save(bean);

        log.info("save GiftExchange id={}", bean.getId());

        return "redirect:v_list.do";
    }

    @RequestMapping({"/exchange/o_update.do"})
    public String update(Long id, String waybill, Integer pageNo, HttpServletRequest request, ModelMap model) {

        GiftExchange bean = this.manager.findById(id);

        bean.setWaybill(waybill);

        bean.setStatus(Integer.valueOf(2));

        this.manager.update(bean);

        log.info("update GiftExchange id={}.", bean.getId());

        return list(pageNo, request, model);
    }

    @RequestMapping({"/exchange/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        GiftExchange[] beans = this.manager.deleteByIds(ids);

        for (GiftExchange bean : beans) {

            log.info("delete GiftExchange id={}", bean.getId());
        }

        return list(pageNo, request, model);
    }

    private WebErrors validateSave(GiftExchange bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        Website web = SiteUtils.getWeb(request);

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

        GiftExchange entity = this.manager.findById(id);

        if (errors.ifNotExist(entity, GiftExchange.class, id)) {

            return true;
        }

        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.GiftExchangeAct
 * JD-Core Version:    0.6.2
 */