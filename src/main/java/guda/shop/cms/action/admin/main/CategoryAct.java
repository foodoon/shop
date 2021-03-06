package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.Category;
import guda.shop.cms.entity.ProductType;
import guda.shop.cms.entity.StandardType;
import guda.shop.cms.manager.*;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.web.RequestUtils;
import guda.shop.common.web.ResponseUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.web.WebErrors;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryAct
        implements ServletContextAware {
    private static final Logger log = LoggerFactory.getLogger(CategoryAct.class);

    @Autowired
    private BrandMng brandMng;

    @Autowired
    private ProductTypeMng productTypeMng;

    @Autowired
    private ProductTypePropertyMng productTypePropertyMng;

    @Autowired
    private StandardTypeMng standardTypeMng;

    @Autowired
    private CategoryMng manager;
    private ServletContext servletContext;


    @RequestMapping({"/category/left.do"})
    public String left() {
        return "category/left";
    }


    @RequestMapping({"/category/tree.do"})
    public String tree(String root, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Website web = SiteUtils.getWeb(request);
        log.debug("tree path={}", root);
        boolean isRoot;
        if ((StringUtils.isBlank(root)) || ("source".equals(root)))
            isRoot = true;
        else {
            isRoot = false;
        }
        model.addAttribute("isRoot", Boolean.valueOf(isRoot));
        WebErrors errors = validateTree(root, request);
        if (errors.hasErrors()) {
            log.error((String) errors.getErrors().get(0));
            ResponseUtils.renderJson(response, "[]");
            return null;
        }
        List list;
        if (isRoot) {
            list = this.manager.getTopList(web.getId());
        } else {
            Long rootId = Long.valueOf(root);
            list = this.manager.getChildList(web.getId(), rootId);
        }
        model.addAttribute("list", list);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/json;charset=UTF-8");
        return "category/tree";
    }

    @RequestMapping({"/category/list.do"})
    public String list(Long root, HttpServletRequest request, ModelMap model) {

        Website web = SiteUtils.getWeb(request);
        List list;

        if (root == null)
            list = this.manager.getTopList(web.getId());
        else {

            list = this.manager.getChildList(web.getId(), root);
        }

        List typeList = this.productTypeMng.getList(web.getId());

        model.addAttribute("typeList", typeList);

        model.addAttribute("root", root);

        model.addAttribute("list", list);

        return "category/list";
    }

    @RequestMapping({"/category/add.do"})
    public String add(Long root, Long typeId, HttpServletRequest request, ModelMap model) {
        Website web = SiteUtils.getWeb(request);
        Category parent = null;
        ProductType type = this.productTypeMng.findById(typeId);
        List itemList = this.productTypePropertyMng.getList(typeId, true);
        List brandList;
        if (root != null) {
            parent = this.manager.findById(root);
            model.addAttribute("parent", parent);
            model.addAttribute("root", root);
            brandList = new ArrayList(parent.getBrands());
        } else {
            brandList = this.brandMng.getList();
        }
        model.addAttribute("brandList", brandList);
        String ctgTplDirRel = type.getCtgTplDirRel();
        String realDir = this.servletContext.getRealPath(ctgTplDirRel);
        String relPath = ctgTplDirRel.substring(web.getTemplateRel().length());
        String txtTplDirRel = type.getTxtTplDirRel();
        String txtrealDir = this.servletContext.getRealPath(txtTplDirRel);
        String txtrelPath = txtTplDirRel.substring(web.getTemplateRel().length());
        String[] channelTpls = type.getChannelTpls(realDir, relPath);
        String[] contentTpls = type.getContentTpls(txtrealDir, txtrelPath);
        List parentList = this.manager.getListForParent(
                SiteUtils.getWebId(request), null);
        List standardTypeList = this.standardTypeMng.getList();
        model.addAttribute("standardTypeList", standardTypeList);
        model.addAttribute("channelTpls", channelTpls);
        model.addAttribute("contentTpls", contentTpls);
        model.addAttribute("parentList", parentList);
        model.addAttribute("type", type);
        model.addAttribute("itemList", itemList);
        return "category/add";
    }

    @RequestMapping({"/category/edit.do"})
    public String edit(Long id, Long root, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        if (root != null) {

            model.addAttribute("root", root);
        }

        Website web = SiteUtils.getWeb(request);

        Category category = this.manager.findById(id);

        List parentList = this.manager.getListForParent(SiteUtils.getWebId(request), id);

        List typeList = this.productTypeMng.getList(web.getId());


        ProductType type = category.getType();


        List itemList = this.productTypePropertyMng.getList(type.getId(), true);
        List brandList;

        if (category.getParent() != null)
            brandList = new ArrayList(category.getParent().getBrands());
        else {

            brandList = brandList = this.brandMng.getList();
        }

        model.addAttribute("brandList", brandList);


        String ctgTplDirRel = type.getCtgTplDirRel();

        String realDir = this.servletContext.getRealPath(ctgTplDirRel);

        String relPath = ctgTplDirRel.substring(web.getTemplateRel().length());


        String txtTplDirRel = type.getTxtTplDirRel();

        String txtrealDir = this.servletContext.getRealPath(txtTplDirRel);

        String txtrelPath = txtTplDirRel.substring(web.getTemplateRel().length());


        String[] channelTpls = type.getChannelTpls(realDir, relPath);


        String[] contentTpls = type.getContentTpls(txtrealDir, txtrelPath);

        String tpl = category.getTplChannel();

        if ((!StringUtils.isBlank(tpl)) && (!ArrayUtils.contains(channelTpls, tpl))) {

            channelTpls = (String[]) ArrayUtils.add(channelTpls, 0, tpl);
        }

        tpl = category.getTplContent();

        if ((!StringUtils.isBlank(tpl)) && (!ArrayUtils.contains(contentTpls, tpl))) {

            contentTpls = (String[]) ArrayUtils.add(contentTpls, 0, tpl);
        }

        List standardTypeList = this.standardTypeMng.getList();

        Long[] standardTypeIds = StandardType.fetchIds(category.getStandardType());

        model.addAttribute("standardTypeList", standardTypeList);

        model.addAttribute("channelTpls", channelTpls);

        model.addAttribute("contentTpls", contentTpls);

        model.addAttribute("parentList", parentList);

        model.addAttribute("typeList", typeList);

        model.addAttribute("category", category);

        model.addAttribute("standardTypeIds", standardTypeIds);

        model.addAttribute("itemList", itemList);

        return "category/edit";
    }

    @RequestMapping({"/category/o_save.do"})
    public String save(Category bean, Long root, Long typeId, Long[] brandIds, Long[] standardTypeIds, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean.setAttr(RequestUtils.getRequestMap(request, "attr_"));

        bean = this.manager.save(bean, root, typeId, brandIds, standardTypeIds);

        log.info("save Category. id={}", bean.getId());

        model.addAttribute("root", root);

        return "redirect:list.do";
    }

    @RequestMapping({"/category/o_update.do"})
    public String update(Category bean, Long root, Long parentId, Long[] brandIds, Long[] standardTypeIds, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean.getId(), request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        Map attr = RequestUtils.getRequestMap(request, "attr_");

        bean = this.manager.update(bean, parentId, null, brandIds, attr, standardTypeIds);

        log.info("update Category. id={}.", bean.getId());

        return list(root, request, model);
    }

    @RequestMapping({"/category/o_delete.do"})
    public String delete(Long[] ids, Long root, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        Category[] beans = this.manager.deleteByIds(ids);

        for (Category bean : beans) {

            log.info("delete Category. id={}", bean.getId());
        }

        return list(root, request, model);
    }

    @RequestMapping({"/category/checkPath.do"})
    public String checkPath(String path, HttpServletRequest request, HttpServletResponse response) {

        if ((StringUtils.isBlank(path)) ||
                (!this.manager.checkPath(SiteUtils.getWebId(request), path)))
            ResponseUtils.renderJsonString(response, "false");
        else {

            ResponseUtils.renderJsonString(response, "true");
        }

        return null;
    }

    @RequestMapping({"/category/o_priority.do"})
    public String priority(Long[] wids, Long root, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validatePriority(wids, priority, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        this.manager.updatePriority(wids, priority);

        model.addAttribute("message", "global.success");

        return list(root, request, model);
    }

    private WebErrors validateTree(String path, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);


        return errors;
    }

    private WebErrors validateSave(Category bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        bean.setWebsite(SiteUtils.getWeb(request));

        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifNull(id, "id");

        vldExist(id, errors);

        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {

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

    private void vldExist(Long id, WebErrors errors) {

        if (errors.hasErrors()) {

            return;
        }

        Category entity = this.manager.findById(id);

        errors.ifNotExist(entity, Category.class, id);
    }

    public void setServletContext(ServletContext servletContext) {

        this.servletContext = servletContext;
    }
}

