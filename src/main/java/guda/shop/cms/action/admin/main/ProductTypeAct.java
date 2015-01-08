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
    private static final Logger log = LoggerFactory.getLogger(ProductTypeAct.class);

    @Autowired
    private BrandMng brandMng;

    @Autowired
    private ProductTypeMng manager;


    @RequestMapping({"/type/v_list.do"})
    public String list(HttpServletRequest request, ModelMap model) {
        List list = this.manager.getList(SiteUtils.getWebId(request));

        model.addAttribute("list", list);

        return "type/list";
    }

    @RequestMapping({"/type/v_add.do"})
    public String add(ModelMap model) {

        return "type/add";
    }

    @RequestMapping({"/type/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        model.addAttribute("productType", this.manager.findById(id));

        return "type/edit";
    }

    @RequestMapping({"/type/o_save.do"})
    public String save(ProductType bean, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.save(bean);

        log.info("save ProductType. id={}", bean.getId());

        return list(request, model);
    }

    @RequestMapping({"/type/o_update.do"})
    public String update(ProductType bean, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean.getId(), request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.update(bean);

        log.info("update ProductType. id={}.", bean.getId());

        return list(request, model);
    }

    @RequestMapping({"/type/o_delete.do"})
    public String delete(Long[] ids, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        ProductType[] beans = this.manager.deleteByIds(ids);

        for (ProductType bean : beans) {

            log.info("delete ProductType. id={}", bean.getId());
        }

        return list(request, model);
    }

    private WebErrors validateSave(ProductType bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        bean.setWebsite(SiteUtils.getWeb(request));

        return errors;
    }

    public WebErrors validateEdit(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifNull(id, "id");

        vldExist(id, errors);

        return errors;
    }

    public WebErrors validateUpdate(Long id, HttpServletRequest request) {

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

        ProductType entity = this.manager.findById(id);

        errors.ifNotExist(entity, ProductType.class, id);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ProductTypeAct
 * JD-Core Version:    0.6.2
 */