package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Category;
import guda.shop.cms.entity.ProductTag;
import guda.shop.cms.manager.CategoryMng;
import guda.shop.cms.manager.ProductTagMng;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.web.WebErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
public class ProductTagAct {
    private static final Logger log = LoggerFactory.getLogger(ProductTagAct.class);

    @Autowired
    private ProductTagMng manager;
    @Autowired
    private CategoryMng categoryMng;

    @RequestMapping({"/tag/list.do"})
    public String list(HttpServletRequest request, ModelMap model) {
        List list = this.manager.getList(SiteUtils.getWebId(request));

        model.addAttribute("list", list);
        /// 查找栏目树

        List categoryList = this.categoryMng.getTopList(
                SiteUtils.getWebId(request));
        if (categoryList.size() > 0) {
            Category treeRoot = new Category();
            treeRoot.setName(
                    MessageResolver.getMessage(request,
                            "product.allCategory", new Object[0])
            );
            treeRoot.setChild(new LinkedHashSet(categoryList));
            model.addAttribute("treeRoot", treeRoot);
        }
        return "tag/list";
    }

    @RequestMapping({"/tag/o_save.do"})
    public String save(ProductTag bean, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.save(bean);

        log.info("save ProductTag. id={}", bean.getId());

        return "redirect:list.do";
    }

    @RequestMapping({"/tag/o_update_tag_names.do"})
    public String updateTagName(Long[] wids, String[] tagNames, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdateTagNames(wids, tagNames, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        ProductTag[] beans = this.manager.updateTagName(wids, tagNames);

        for (ProductTag bean : beans) {

            log.info("update ProductTag. id={},name={}", bean.getId(),
                    bean.getName());
        }

        return "redirect:list.do";
    }

    @RequestMapping({"/tag/o_delete.do"})
    public String delete(Long[] ids, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        ProductTag[] beans = this.manager.deleteByIds(ids);

        for (ProductTag bean : beans) {

            log.info("delete ProductTag. id={},name={}", bean.getId(),
                    bean.getName());
        }

        return list(request, model);
    }

    private WebErrors validateSave(ProductTag bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        bean.setWebsite(SiteUtils.getWeb(request));

        return errors;
    }

    private WebErrors validateUpdateTagNames(Long[] wids, String[] tagNames, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifEmpty(wids, "wids");

        errors.ifEmpty(tagNames, "tagNames");

        if (errors.hasErrors()) {

            return errors;
        }

        if (wids.length != tagNames.length) {

            errors.addErrorString("wids length must equals tagNames length");

            return errors;
        }

        int i = 0;
        for (int len = wids.length; i < len; i++) {

            vldExist(wids[i], errors);

            if (errors.hasErrors()) {

                return errors;
            }
        }

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

        ProductTag entity = this.manager.findById(id);

        errors.ifNotExist(entity, ProductTag.class, id);
    }
}

