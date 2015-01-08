package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Shipping;
import guda.shop.cms.manager.LogisticsMng;
import guda.shop.cms.manager.ShippingMng;
import guda.shop.core.web.SiteUtils;
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
public class ShippingAct {
    /*  28 */   private static final Logger log = LoggerFactory.getLogger(ShippingAct.class);

    @Autowired
    private ShippingMng manager;

    @Autowired
    private LogisticsMng logisticsMng;

    /*  33 */
    @RequestMapping({"/shipping/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        List list = this.manager.getList(SiteUtils.getWebId(request), true);
/*  34 */
        model.addAttribute("list", list);
/*  35 */
        return "shipping/list";
    }

    @RequestMapping({"/shipping/v_add.do"})
    public String add(HttpServletRequest request, ModelMap model) {
/*  40 */
        List list = this.logisticsMng.getAllList();
/*  41 */
        model.addAttribute("list", list);
/*  42 */
        return "shipping/add";
    }

    @RequestMapping({"/shipping/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  47 */
        WebErrors errors = validateEdit(id, request);
/*  48 */
        if (errors.hasErrors()) {
/*  49 */
            return errors.showErrorPage(model);
        }
/*  51 */
        model.addAttribute("shipping", this.manager.findById(id));
/*  52 */
        List list = this.logisticsMng.getAllList();
/*  53 */
        model.addAttribute("list", list);
/*  54 */
        return "shipping/edit";
    }

    @RequestMapping({"/shipping/o_save.do"})
    public String save(Shipping bean, Long logisticsId, HttpServletRequest request, ModelMap model) {
/*  59 */
        WebErrors errors = validateSave(bean, request);
/*  60 */
        if (errors.hasErrors()) {
/*  61 */
            return errors.showErrorPage(model);
        }
/*  63 */
        if (bean.getIsDefault().booleanValue()) {
/*  64 */
            List list = this.manager.getList(Long.valueOf(1L), true);
/*  65 */
            for (int i = 0; i < list.size(); i++) {
/*  66 */
                ((Shipping) list.get(i)).setIsDefault(Boolean.valueOf(false));
/*  67 */
                this.manager.update((Shipping) list.get(i));
            }
        }
/*  70 */
        bean.setLogistics(this.logisticsMng.findById(logisticsId));
/*  71 */
        bean = this.manager.save(bean);
/*  72 */
        log.info("save Shipping. id={}", bean.getId());
/*  73 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/shipping/o_update.do"})
    public String update(Shipping bean, Long logisticsId, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  79 */
        WebErrors errors = validateUpdate(bean.getId(), request);
/*  80 */
        if (errors.hasErrors()) {
/*  81 */
            return errors.showErrorPage(model);
        }
/*  83 */
        if (bean.getIsDefault().booleanValue()) {
/*  84 */
            List list = this.manager.getList(Long.valueOf(1L), true);
/*  85 */
            for (int i = 0; i < list.size(); i++) {
/*  86 */
                ((Shipping) list.get(i)).setIsDefault(Boolean.valueOf(false));
/*  87 */
                this.manager.update(bean);
            }
        }
/*  90 */
        bean.setLogistics(this.logisticsMng.findById(logisticsId));
/*  91 */
        bean = this.manager.update(bean);
/*  92 */
        log.info("update Shipping. id={}.", bean.getId());
/*  93 */
        return list(pageNo, request, model);
    }

    @RequestMapping({"/shipping/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  99 */
        WebErrors errors = validateDelete(ids, request);
/* 100 */
        if (errors.hasErrors()) {
/* 101 */
            return errors.showErrorPage(model);
        }
/* 103 */
        Shipping[] beans = this.manager.deleteByIds(ids);
/* 104 */
        for (Shipping bean : beans) {
/* 105 */
            log.info("delete Shipping. id={}", bean.getId());
        }
/* 107 */
        return list(pageNo, request, model);
    }

    @RequestMapping({"/shipping/o_priority.do"})
    public String priority(Long[] wids, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 113 */
        WebErrors errors = validatePriority(wids, priority, request);
/* 114 */
        if (errors.hasErrors()) {
/* 115 */
            return errors.showErrorPage(model);
        }
/* 117 */
        this.manager.updatePriority(wids, priority);
/* 118 */
        model.addAttribute("message", "global.success");
/* 119 */
        return list(pageNo, request, model);
    }

    private WebErrors validateSave(Shipping bean, HttpServletRequest request) {
/* 125 */
        WebErrors errors = WebErrors.create(request);
/* 126 */
        bean.setWebsite(SiteUtils.getWeb(request));
/* 127 */
        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 131 */
        WebErrors errors = WebErrors.create(request);
/* 132 */
        errors.ifNull(id, "id");
/* 133 */
        vldExist(id, errors);
/* 134 */
        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 138 */
        WebErrors errors = WebErrors.create(request);
/* 139 */
        errors.ifNull(id, "id");
/* 140 */
        vldExist(id, errors);
/* 141 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 145 */
        WebErrors errors = WebErrors.create(request);
/* 146 */
        errors.ifEmpty(ids, "ids");
/* 147 */
        for (Long id : ids) {
/* 148 */
            vldExist(id, errors);
        }
/* 150 */
        return errors;
    }

    private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request) {
/* 155 */
        WebErrors errors = WebErrors.create(request);
/* 156 */
        if (errors.ifEmpty(wids, "wids")) {
/* 157 */
            return errors;
        }
/* 159 */
        if (errors.ifEmpty(priority, "priority")) {
/* 160 */
            return errors;
        }
/* 162 */
        if (wids.length != priority.length) {
/* 163 */
            errors.addErrorString("wids length not equals priority length");
/* 164 */
            return errors;
        }
/* 166 */
        int i = 0;
        for (int len = wids.length; i < len; i++) {
/* 167 */
            vldExist(wids[i], errors);
/* 168 */
            if (priority[i] == null) {
/* 169 */
                priority[i] = Integer.valueOf(0);
            }
        }
/* 172 */
        return errors;
    }

    private boolean vldExist(Long id, WebErrors errors) {
/* 176 */
        Shipping entity = this.manager.findById(id);
/* 177 */
        return errors.ifNotExist(entity, Shipping.class, id);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ShippingAct
 * JD-Core Version:    0.6.2
 */