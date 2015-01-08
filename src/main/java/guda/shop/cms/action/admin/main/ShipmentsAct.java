package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Shipments;
import guda.shop.cms.manager.ShipmentsMng;
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
public class ShipmentsAct {
    /*  28 */   private static final Logger log = LoggerFactory.getLogger(ShipmentsAct.class);

    @Autowired
    private ShipmentsMng manager;

    /*  32 */
    @RequestMapping({"/Shipments/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo),
/*  33 */       CookieUtils.getPageSize(request));
/*  34 */
        model.addAttribute("pagination", pagination);
/*  35 */
        return "Shipments/list";
    }

    @RequestMapping({"/Shipments/v_add.do"})
    public String add(ModelMap model) {
/*  40 */
        return "Shipments/add";
    }

    @RequestMapping({"/Shipments/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  45 */
        WebErrors errors = validateEdit(id, request);
/*  46 */
        if (errors.hasErrors()) {
/*  47 */
            return errors.showErrorPage(model);
        }
/*  49 */
        model.addAttribute("order", this.manager.findById(id).getIndent());
/*  50 */
        model.addAttribute("shipments", this.manager.findById(id));
/*  51 */
        return "Shipments/edit";
    }

    @RequestMapping({"/Shipments/o_save.do"})
    public String save(Shipments bean, HttpServletRequest request, ModelMap model) {
/*  56 */
        WebErrors errors = validateSave(bean, request);
/*  57 */
        if (errors.hasErrors()) {
/*  58 */
            return errors.showErrorPage(model);
        }
/*  60 */
        bean = this.manager.save(bean);
/*  61 */
        log.info("save Shipments id={}", bean.getId());
/*  62 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/Shipments/o_update.do"})
    public String update(Shipments bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  68 */
        WebErrors errors = validateUpdate(bean.getId(), request);
/*  69 */
        if (errors.hasErrors()) {
/*  70 */
            return errors.showErrorPage(model);
        }
/*  72 */
        bean = this.manager.update(bean);
/*  73 */
        log.info("update Shipments id={}.", bean.getId());
/*  74 */
        return list(pageNo, request, model);
    }

    @RequestMapping({"/Shipments/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  80 */
        WebErrors errors = validateDelete(ids, request);
/*  81 */
        if (errors.hasErrors()) {
/*  82 */
            return errors.showErrorPage(model);
        }
/*  84 */
        Shipments[] beans = this.manager.deleteByIds(ids);
/*  85 */
        for (Shipments bean : beans) {
/*  86 */
            log.info("delete Shipments id={}", bean.getId());
        }
/*  88 */
        return list(pageNo, request, model);
    }

    private WebErrors validateSave(Shipments bean, HttpServletRequest request) {
/*  92 */
        WebErrors errors = WebErrors.create(request);
/*  93 */
        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/*  97 */
        WebErrors errors = WebErrors.create(request);
/*  98 */
        Website web = SiteUtils.getWeb(request);
/*  99 */
        if (vldExist(id, web.getId(), errors)) {
/* 100 */
            return errors;
        }
/* 102 */
        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 106 */
        WebErrors errors = WebErrors.create(request);
/* 107 */
        Website web = SiteUtils.getWeb(request);
/* 108 */
        if (vldExist(id, web.getId(), errors)) {
/* 109 */
            return errors;
        }
/* 111 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 115 */
        WebErrors errors = WebErrors.create(request);
/* 116 */
        Website web = SiteUtils.getWeb(request);
/* 117 */
        errors.ifEmpty(ids, "ids");
/* 118 */
        for (Long id : ids) {
/* 119 */
            vldExist(id, web.getId(), errors);
        }
/* 121 */
        return errors;
    }

    private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 125 */
        if (errors.ifNull(id, "id")) {
/* 126 */
            return true;
        }
/* 128 */
        Shipments entity = this.manager.findById(id);
/* 129 */
        if (errors.ifNotExist(entity, Shipments.class, id)) {
/* 130 */
            return true;
        }
/* 132 */
        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShipmentsAct
 * JD-Core Version:    0.6.2
 */