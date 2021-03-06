package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.PopularityGroup;
import guda.shop.cms.entity.Product;
import guda.shop.cms.manager.BrandMng;
import guda.shop.cms.manager.PopularityGroupMng;
import guda.shop.cms.manager.ProductMng;
import guda.shop.cms.manager.ProductTypeMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.common.web.ResponseUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import guda.shop.core.web.WebErrors;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PopularityGroupAct {
    private static final Logger log = LoggerFactory.getLogger(PopularityGroupAct.class);

    @Autowired
    private BrandMng brandMng;

    @Autowired
    private ProductTypeMng productTypeMng;

    @Autowired
    private PopularityGroupMng manager;

    @Autowired
    private ProductMng productMng;


    @RequestMapping({"/popularityGroup/list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));

        model.addAttribute("pagination", pagination);

        return "popularityGroup/list";
    }

    @RequestMapping({"/popularityGroup/search.do"})
    public void update(Long typeId, Long brandId, String productName, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        List<Product> list = this.productMng.getList(typeId, brandId, productName);

        JSONObject json = new JSONObject();
        try {

            int i = 0;
            for (int j = list.size(); i < j; i++) {

                json.append(String.valueOf(list.get(i).getId()), (list.get(i)).getName());
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }

        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/popularityGroup/add.do"})
    public String add(ModelMap model) {

        List typeList = this.productTypeMng.getList(Long.valueOf(1L));

        List brandList = this.brandMng.getAllList();

        model.addAttribute("brandList", brandList);

        model.addAttribute("typeList", typeList);

        return "popularityGroup/add";
    }

    @RequestMapping({"/popularityGroup/edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        List typeList = this.productTypeMng.getList(Long.valueOf(1L));

        List brandList = this.brandMng.getAllList();

        model.addAttribute("brandList", brandList);

        model.addAttribute("typeList", typeList);

        model.addAttribute("popularityGroup", this.manager.findById(id));

        return "popularityGroup/edit";
    }

    @RequestMapping({"/popularityGroup/o_save.do"})
    public String save(PopularityGroup bean, String rightlist, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.save(bean);

        this.manager.addProduct(bean, getProductIds(rightlist));

        log.info("save PopularityGroup id={}", bean.getId());

        return "redirect:list.do";
    }

    @RequestMapping({"/popularityGroup/o_update.do"})
    public String update(PopularityGroup bean, String rightlist, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean.getId(), request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        bean = this.manager.update(bean);

        this.manager.updateProduct(bean, getProductIds(rightlist));

        log.info("update PopularityGroup id={}.", bean.getId());

        return list(pageNo, request, model);
    }

    @RequestMapping({"/popularityGroup/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        PopularityGroup[] beans = this.manager.deleteByIds(ids);

        for (PopularityGroup bean : beans) {

            log.info("delete PopularityGroup id={}", bean.getId());
        }

        return list(pageNo, request, model);
    }

    public Long[] getProductIds(String rightlist) {

        String[] arr = rightlist.split(",");

        Long[] productIds = new Long[arr.length];

        int i = 0;
        for (int j = arr.length; i < j; i++) {

            if (!arr[i].equals("")) {

                productIds[i] = Long.valueOf(Long.parseLong(arr[i]));
            }
        }

        return productIds;
    }

    private WebErrors validateSave(PopularityGroup bean, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

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

        PopularityGroup entity = this.manager.findById(id);

        if (errors.ifNotExist(entity, PopularityGroup.class, id)) {

            return true;
        }

        return false;
    }
}

