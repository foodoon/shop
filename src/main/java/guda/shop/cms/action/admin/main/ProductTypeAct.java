package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.ProductType;
import guda.shop.cms.manager.BrandMng;
import guda.shop.cms.manager.ProductTypeMng;
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
public class ProductTypeAct {
    /*  27 */   private static final Logger log = LoggerFactory.getLogger(ProductTypeAct.class);

    @Autowired
    private BrandMng brandMng;

    @Autowired
    private ProductTypeMng manager;

    /*  31 */
    @RequestMapping({"/type/v_list.do"})
    public String list(HttpServletRequest request, ModelMap model) {
        List list = this.manager.getList(SiteUtils.getWebId(request));
/*  32 */
        model.addAttribute("list", list);
/*  33 */
        return "type/list";
    }

    @RequestMapping({"/type/v_add.do"})
    public String add(ModelMap model) {
/*  38 */
        return "type/add";
    }

    @RequestMapping({"/type/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  43 */
        WebErrors errors = validateEdit(id, request);
/*  44 */
        if (errors.hasErrors()) {
/*  45 */
            return errors.showErrorPage(model);
        }
/*  47 */
        model.addAttribute("productType", this.manager.findById(id));
/*  48 */
        return "type/edit";
    }

    @RequestMapping({"/type/o_save.do"})
    public String save(ProductType bean, HttpServletRequest request, ModelMap model) {
/*  54 */
        WebErrors errors = validateSave(bean, request);
/*  55 */
        if (errors.hasErrors()) {
/*  56 */
            return errors.showErrorPage(model);
        }
/*  58 */
        bean = this.manager.save(bean);
/*  59 */
        log.info("save ProductType. id={}", bean.getId());
/*  60 */
        return list(request, model);
    }

    @RequestMapping({"/type/o_update.do"})
    public String update(ProductType bean, HttpServletRequest request, ModelMap model) {
/*  66 */
        WebErrors errors = validateUpdate(bean.getId(), request);
/*  67 */
        if (errors.hasErrors()) {
/*  68 */
            return errors.showErrorPage(model);
        }
/*  70 */
        bean = this.manager.update(bean);
/*  71 */
        log.info("update ProductType. id={}.", bean.getId());
/*  72 */
        return list(request, model);
    }

    @RequestMapping({"/type/o_delete.do"})
    public String delete(Long[] ids, HttpServletRequest request, ModelMap model) {
/*  77 */
        WebErrors errors = validateDelete(ids, request);
/*  78 */
        if (errors.hasErrors()) {
/*  79 */
            return errors.showErrorPage(model);
        }
/*  81 */
        ProductType[] beans = this.manager.deleteByIds(ids);
/*  82 */
        for (ProductType bean : beans) {
/*  83 */
            log.info("delete ProductType. id={}", bean.getId());
        }
/*  85 */
        return list(request, model);
    }

    private WebErrors validateSave(ProductType bean, HttpServletRequest request) {
/*  89 */
        WebErrors errors = WebErrors.create(request);
/*  90 */
        bean.setWebsite(SiteUtils.getWeb(request));
/*  91 */
        return errors;
    }

    public WebErrors validateEdit(Long id, HttpServletRequest request) {
/*  95 */
        WebErrors errors = WebErrors.create(request);
/*  96 */
        errors.ifNull(id, "id");
/*  97 */
        vldExist(id, errors);
/*  98 */
        return errors;
    }

    public WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 102 */
        WebErrors errors = WebErrors.create(request);
/* 103 */
        errors.ifNull(id, "id");
/* 104 */
        vldExist(id, errors);
/* 105 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 109 */
        WebErrors errors = WebErrors.create(request);
/* 110 */
        errors.ifEmpty(ids, "ids");
/* 111 */
        for (Long id : ids) {
/* 112 */
            vldExist(id, errors);
        }
/* 114 */
        return errors;
    }

    private void vldExist(Long id, WebErrors errors) {
/* 118 */
        if (errors.hasErrors()) {
/* 119 */
            return;
        }
/* 121 */
        ProductType entity = this.manager.findById(id);
/* 122 */
        errors.ifNotExist(entity, ProductType.class, id);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ProductTypeAct
 * JD-Core Version:    0.6.2
 */