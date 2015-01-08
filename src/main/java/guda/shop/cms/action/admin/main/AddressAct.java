package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Address;
import guda.shop.cms.manager.AddressMng;
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
public class AddressAct {
    /*  27 */   private static final Logger log = LoggerFactory.getLogger(AddressAct.class);

    @Autowired
    private AddressMng manager;

    /*  31 */
    @RequestMapping({"/address/v_list.do"})
    public String list(Long parentId, Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPageByParentId(parentId, SimplePage.cpn(pageNo),
/*  32 */       CookieUtils.getPageSize(request));
/*  33 */
        model.addAttribute("pagination", pagination);
/*  34 */
        model.addAttribute("parentId", parentId);
/*  35 */
        return "address/list";
    }

    @RequestMapping({"/address/v_add.do"})
    public String add(Long parentId, ModelMap model) {
/*  40 */
        model.addAttribute("parentId", parentId);
/*  41 */
        return "address/add";
    }

    @RequestMapping({"/address/v_edit.do"})
    public String edit(Long id, Long parentId, HttpServletRequest request, ModelMap model) {
/*  46 */
        WebErrors errors = validateEdit(id, request);
/*  47 */
        if (errors.hasErrors()) {
/*  48 */
            return errors.showErrorPage(model);
        }
/*  50 */
        model.addAttribute("address", this.manager.findById(id));
/*  51 */
        model.addAttribute("parentId", parentId);
/*  52 */
        return "address/edit";
    }

    @RequestMapping({"/address/o_save.do"})
    public String save(Long parentId, Address bean, HttpServletRequest request, ModelMap model) {
/*  57 */
        WebErrors errors = validateSave(bean, request);
/*  58 */
        if (errors.hasErrors()) {
/*  59 */
            return errors.showErrorPage(model);
        }
/*  61 */
        if (parentId != null) {
/*  62 */
            Address address = this.manager.findById(parentId);
/*  63 */
            bean.setParent(address);
        }
/*  65 */
        bean = this.manager.save(bean);
/*  66 */
        log.info("save Address id={}", bean.getId());
/*  67 */
        model.addAttribute("parentId", parentId);
/*  68 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/address/o_update.do"})
    public String update(Long parentId, Address bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  74 */
        WebErrors errors = validateUpdate(bean.getId(), request);
/*  75 */
        if (errors.hasErrors()) {
/*  76 */
            return errors.showErrorPage(model);
        }
/*  78 */
        bean = this.manager.update(bean);
/*  79 */
        log.info("update Address id={}.", bean.getId());
/*  80 */
        return list(parentId, pageNo, request, model);
    }

    @RequestMapping({"/address/o_delete.do"})
    public String delete(Long[] ids, Long parentId, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  86 */
        WebErrors errors = validateDelete(ids, request);
/*  87 */
        if (errors.hasErrors()) {
/*  88 */
            return errors.showErrorPage(model);
        }
/*  90 */
        Address[] beans = this.manager.deleteByIds(ids);
/*  91 */
        for (Address bean : beans) {
/*  92 */
            log.info("delete Address id={}", bean.getId());
        }
/*  94 */
        return list(parentId, pageNo, request, model);
    }

    @RequestMapping({"/address/o_priority.do"})
    public String priority(Long[] wids, Long parentId, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 100 */
        WebErrors errors = validatePriority(wids, priority, request);
/* 101 */
        if (errors.hasErrors()) {
/* 102 */
            return errors.showErrorPage(model);
        }
/* 104 */
        this.manager.updatePriority(wids, priority);
/* 105 */
        model.addAttribute("message", "global.success");
/* 106 */
        return list(parentId, pageNo, request, model);
    }

    private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request) {
/* 111 */
        WebErrors errors = WebErrors.create(request);
/* 112 */
        if (errors.ifEmpty(wids, "wids")) {
/* 113 */
            return errors;
        }
/* 115 */
        if (errors.ifEmpty(priority, "priority")) {
/* 116 */
            return errors;
        }
/* 118 */
        if (wids.length != priority.length) {
/* 119 */
            errors.addErrorString("wids length not equals priority length");
/* 120 */
            return errors;
        }
/* 122 */
        int i = 0;
        for (int len = wids.length; i < len; i++) {
/* 123 */
            vldExist(wids[i], errors);
/* 124 */
            if (priority[i] == null) {
/* 125 */
                priority[i] = Integer.valueOf(0);
            }
        }
/* 128 */
        return errors;
    }

    private boolean vldExist(Long id, WebErrors errors) {
/* 132 */
        Address entity = this.manager.findById(id);
/* 133 */
        return errors.ifNotExist(entity, Address.class, id);
    }

    private WebErrors validateSave(Address bean, HttpServletRequest request) {
/* 137 */
        WebErrors errors = WebErrors.create(request);

/* 140 */
        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 144 */
        WebErrors errors = WebErrors.create(request);
/* 145 */
        Website web = SiteUtils.getWeb(request);
/* 146 */
        if (vldExist(id, web.getId(), errors)) {
/* 147 */
            return errors;
        }
/* 149 */
        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 153 */
        WebErrors errors = WebErrors.create(request);
/* 154 */
        Website web = SiteUtils.getWeb(request);
/* 155 */
        if (vldExist(id, web.getId(), errors)) {
/* 156 */
            return errors;
        }
/* 158 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 162 */
        WebErrors errors = WebErrors.create(request);
/* 163 */
        Website web = SiteUtils.getWeb(request);
/* 164 */
        errors.ifEmpty(ids, "ids");
/* 165 */
        for (Long id : ids) {
/* 166 */
            vldExist(id, web.getId(), errors);
        }
/* 168 */
        return errors;
    }

    private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 172 */
        if (errors.ifNull(id, "id")) {
/* 173 */
            return true;
        }
/* 175 */
        Address entity = this.manager.findById(id);
/* 176 */
        if (errors.ifNotExist(entity, Address.class, id)) {
/* 177 */
            return true;
        }

/* 183 */
        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.AddressAct
 * JD-Core Version:    0.6.2
 */