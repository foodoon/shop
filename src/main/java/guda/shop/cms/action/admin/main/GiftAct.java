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
    /*  29 */   private static final Logger log = LoggerFactory.getLogger(GiftAct.class);

    @Autowired
    private GiftMng manager;

    /*  34 */
    @RequestMapping({"/gift/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPageGift(SimplePage.cpn(pageNo),
/*  35 */       CookieUtils.getPageSize(request));
/*  36 */
        model.addAttribute("pagination", pagination);
/*  37 */
        return "gift/list";
    }

    @RequestMapping({"/gift/v_add.do"})
    public String add(HttpServletRequest request, ModelMap model) {
/*  42 */
        return "gift/add";
    }

    @RequestMapping({"/gift/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  47 */
        WebErrors errors = validateEdit(id, request);
/*  48 */
        if (errors.hasErrors()) {
/*  49 */
            return errors.showErrorPage(model);
        }
/*  51 */
        model.addAttribute("gift", this.manager.findById(id));
/*  52 */
        return "gift/edit";
    }

    @RequestMapping({"/gift/o_save.do"})
    public String save(Gift bean, HttpServletRequest request, ModelMap model) {
/*  58 */
        bean = this.manager.save(bean);
/*  59 */
        log.info("save brand. id={}", bean.getId());
/*  60 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/gift/o_update.do"})
    public String update(Gift gift, String text, Long[] typeIds, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  66 */
        this.manager.updateByUpdater(gift);
/*  67 */
        log.info("update brand. id={}.", gift.getId());
/*  68 */
        return list(pageNo, request, model);
    }

    @RequestMapping({"/gift/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  74 */
        WebErrors errors = validateDelete(ids, request);
/*  75 */
        if (errors.hasErrors()) {
/*  76 */
            return errors.showErrorPage(model);
        }
/*  78 */
        Gift[] beans = this.manager.deleteByIds(ids);
/*  79 */
        for (Gift bean : beans) {
/*  80 */
            log.info("delete brand. id={}", bean.getId());
        }
/*  82 */
        return list(pageNo, request, model);
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/*  99 */
        WebErrors errors = WebErrors.create(request);
/* 100 */
        errors.ifNull(id, "id");
/* 101 */
        vldExist(id, errors);
/* 102 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 107 */
        WebErrors errors = WebErrors.create(request);
/* 108 */
        errors.ifEmpty(ids, "ids");
/* 109 */
        for (Long id : ids) {
/* 110 */
            vldExist(id, errors);
        }
/* 112 */
        return errors;
    }

    private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request) {
/* 117 */
        WebErrors errors = WebErrors.create(request);
/* 118 */
        if (errors.ifEmpty(wids, "wids")) {
/* 119 */
            return errors;
        }
/* 121 */
        if (errors.ifEmpty(priority, "priority")) {
/* 122 */
            return errors;
        }
/* 124 */
        if (wids.length != priority.length) {
/* 125 */
            errors.addErrorString("wids length not equals priority length");
/* 126 */
            return errors;
        }
/* 128 */
        int i = 0;
        for (int len = wids.length; i < len; i++) {
/* 129 */
            vldExist(wids[i], errors);
/* 130 */
            if (priority[i] == null) {
/* 131 */
                priority[i] = Integer.valueOf(0);
            }
        }
/* 134 */
        return errors;
    }

    private boolean vldExist(Long id, WebErrors errors) {
/* 138 */
        Gift entity = this.manager.findById(id);
/* 139 */
        return errors.ifNotExist(entity, Brand.class, id);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.GiftAct
 * JD-Core Version:    0.6.2
 */