package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Discuss;
import guda.shop.cms.manager.DiscussMng;
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
public class DiscussAct {
    /*  32 */   private static final Logger log = LoggerFactory.getLogger(DiscussAct.class);

    @Autowired
    private DiscussMng manager;

    /*  37 */
    @RequestMapping({"/discuss/v_list.do"})
    public String list(Date startTime, Date endTime, Integer pageNo, HttpServletRequest request, ModelMap model) {
        String userName = RequestUtils.getQueryParam(request, "userName");
/*  38 */
        userName = StringUtils.trim(userName);
/*  39 */
        String productName = RequestUtils.getQueryParam(request, "productName");
/*  40 */
        productName = StringUtils.trim(productName);
/*  41 */
        Pagination pagination = this.manager.getPage(null, userName, productName, startTime, endTime,
/*  42 */       SimplePage.cpn(pageNo), CookieUtils.getPageSize(request), true);
/*  43 */
        if (!StringUtils.isBlank(userName)) {
/*  44 */
            model.addAttribute("userName", userName);
        }
/*  46 */
        if (!StringUtils.isBlank(productName)) {
/*  47 */
            model.addAttribute("productName", productName);
        }

/*  51 */
        model.addAttribute("startTime", startTime);
/*  52 */
        model.addAttribute("endTime", endTime);

/*  54 */
        model.addAttribute("pagination", pagination);
/*  55 */
        model.addAttribute("pageNo", pageNo);
/*  56 */
        return "discuss/list";
    }

    @RequestMapping({"/discuss/v_add.do"})
    public String add(ModelMap model) {
/*  61 */
        return "discuss/add";
    }

    @RequestMapping({"/discuss/v_edit.do"})
    public String edit(Long id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  66 */
        WebErrors errors = validateEdit(id, request);
/*  67 */
        if (errors.hasErrors()) {
/*  68 */
            return errors.showErrorPage(model);
        }
/*  70 */
        model.addAttribute("discuss", this.manager.findById(id));
/*  71 */
        model.addAttribute("pageNo", pageNo);
/*  72 */
        return "discuss/edit";
    }

    @RequestMapping({"/discuss/o_save.do"})
    public String save(Discuss bean, HttpServletRequest request, ModelMap model) {
/*  77 */
        WebErrors errors = validateSave(bean, request);
/*  78 */
        if (errors.hasErrors()) {
/*  79 */
            return errors.showErrorPage(model);
        }

/*  82 */
        log.info("save Discuss id={}", bean.getId());
/*  83 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/discuss/o_update.do"})
    public String update(Discuss bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  89 */
        WebErrors errors = validateUpdate(bean.getId(), request);
/*  90 */
        if (errors.hasErrors()) {
/*  91 */
            return errors.showErrorPage(model);
        }
/*  93 */
        bean = this.manager.update(bean);
/*  94 */
        log.info("update Discuss id={}.", bean.getId());
/*  95 */
        return list(null, null, pageNo, request, model);
    }

    @RequestMapping({"/discuss/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 101 */
        WebErrors errors = validateDelete(ids, request);
/* 102 */
        if (errors.hasErrors()) {
/* 103 */
            return errors.showErrorPage(model);
        }
/* 105 */
        Discuss[] beans = this.manager.deleteByIds(ids);
/* 106 */
        for (Discuss bean : beans) {
/* 107 */
            log.info("delete Discuss id={}", bean.getId());
        }
/* 109 */
        return list(null, null, pageNo, request, model);
    }

    private WebErrors validateSave(Discuss bean, HttpServletRequest request) {
/* 113 */
        WebErrors errors = WebErrors.create(request);

/* 116 */
        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 120 */
        WebErrors errors = WebErrors.create(request);
/* 121 */
        Website web = SiteUtils.getWeb(request);
/* 122 */
        if (vldExist(id, web.getId(), errors)) {
/* 123 */
            return errors;
        }
/* 125 */
        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 129 */
        WebErrors errors = WebErrors.create(request);
/* 130 */
        Website web = SiteUtils.getWeb(request);
/* 131 */
        if (vldExist(id, web.getId(), errors)) {
/* 132 */
            return errors;
        }
/* 134 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 138 */
        WebErrors errors = WebErrors.create(request);
/* 139 */
        Website web = SiteUtils.getWeb(request);
/* 140 */
        errors.ifEmpty(ids, "ids");
/* 141 */
        for (Long id : ids) {
/* 142 */
            vldExist(id, web.getId(), errors);
        }
/* 144 */
        return errors;
    }

    private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 148 */
        if (errors.ifNull(id, "id")) {
/* 149 */
            return true;
        }
/* 151 */
        Discuss entity = this.manager.findById(id);
/* 152 */
        if (errors.ifNotExist(entity, Discuss.class, id)) {
/* 153 */
            return true;
        }

/* 159 */
        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.DiscussAct
 * JD-Core Version:    0.6.2
 */