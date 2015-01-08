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
    /*  36 */   private static final Logger log = LoggerFactory.getLogger(PopularityGroupAct.class);

    @Autowired
    private BrandMng brandMng;

    @Autowired
    private ProductTypeMng productTypeMng;

    @Autowired
    private PopularityGroupMng manager;

    @Autowired
    private ProductMng productMng;

    /*  40 */
    @RequestMapping({"/popularityGroup/v_list.do"})
    public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  41 */
        model.addAttribute("pagination", pagination);
/*  42 */
        return "popularityGroup/list";
    }

    @RequestMapping({"/popularityGroup/v_search.do"})
    public void update(Long typeId, Long brandId, String productName, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/*  50 */
        List<Product> list = this.productMng.getList(typeId, brandId, productName);
/*  51 */
        JSONObject json = new JSONObject();
        try {
/*  53 */
            int i = 0;
            for (int j = list.size(); i < j; i++) {

                json.append(String.valueOf(list.get(i).getId()), (list.get(i)).getName());
            }
        } catch (JSONException e) {
/*  57 */
            e.printStackTrace();
        }
/*  59 */
        ResponseUtils.renderJson(response, json.toString());
    }

    @RequestMapping({"/popularityGroup/v_add.do"})
    public String add(ModelMap model) {
/*  64 */
        List typeList = this.productTypeMng.getList(Long.valueOf(1L));
/*  65 */
        List brandList = this.brandMng.getAllList();
/*  66 */
        model.addAttribute("brandList", brandList);
/*  67 */
        model.addAttribute("typeList", typeList);
/*  68 */
        return "popularityGroup/add";
    }

    @RequestMapping({"/popularityGroup/v_edit.do"})
    public String edit(Long id, HttpServletRequest request, ModelMap model) {
/*  73 */
        WebErrors errors = validateEdit(id, request);
/*  74 */
        if (errors.hasErrors()) {
/*  75 */
            return errors.showErrorPage(model);
        }
/*  77 */
        List typeList = this.productTypeMng.getList(Long.valueOf(1L));
/*  78 */
        List brandList = this.brandMng.getAllList();
/*  79 */
        model.addAttribute("brandList", brandList);
/*  80 */
        model.addAttribute("typeList", typeList);
/*  81 */
        model.addAttribute("popularityGroup", this.manager.findById(id));
/*  82 */
        return "popularityGroup/edit";
    }

    @RequestMapping({"/popularityGroup/o_save.do"})
    public String save(PopularityGroup bean, String rightlist, HttpServletRequest request, ModelMap model) {
/*  87 */
        WebErrors errors = validateSave(bean, request);
/*  88 */
        if (errors.hasErrors()) {
/*  89 */
            return errors.showErrorPage(model);
        }
/*  91 */
        bean = this.manager.save(bean);
/*  92 */
        this.manager.addProduct(bean, getProductIds(rightlist));
/*  93 */
        log.info("save PopularityGroup id={}", bean.getId());
/*  94 */
        return "redirect:v_list.do";
    }

    @RequestMapping({"/popularityGroup/o_update.do"})
    public String update(PopularityGroup bean, String rightlist, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 100 */
        WebErrors errors = validateUpdate(bean.getId(), request);
/* 101 */
        if (errors.hasErrors()) {
/* 102 */
            return errors.showErrorPage(model);
        }
/* 104 */
        bean = this.manager.update(bean);
/* 105 */
        this.manager.updateProduct(bean, getProductIds(rightlist));
/* 106 */
        log.info("update PopularityGroup id={}.", bean.getId());
/* 107 */
        return list(pageNo, request, model);
    }

    @RequestMapping({"/popularityGroup/o_delete.do"})
    public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 113 */
        WebErrors errors = validateDelete(ids, request);
/* 114 */
        if (errors.hasErrors()) {
/* 115 */
            return errors.showErrorPage(model);
        }
/* 117 */
        PopularityGroup[] beans = this.manager.deleteByIds(ids);
/* 118 */
        for (PopularityGroup bean : beans) {
/* 119 */
            log.info("delete PopularityGroup id={}", bean.getId());
        }
/* 121 */
        return list(pageNo, request, model);
    }

    public Long[] getProductIds(String rightlist) {
/* 126 */
        String[] arr = rightlist.split(",");
/* 127 */
        Long[] productIds = new Long[arr.length];
/* 128 */
        int i = 0;
        for (int j = arr.length; i < j; i++) {
/* 129 */
            if (!arr[i].equals("")) {
/* 130 */
                productIds[i] = Long.valueOf(Long.parseLong(arr[i]));
            }
        }
/* 133 */
        return productIds;
    }

    private WebErrors validateSave(PopularityGroup bean, HttpServletRequest request) {
/* 137 */
        WebErrors errors = WebErrors.create(request);
/* 138 */
        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 142 */
        WebErrors errors = WebErrors.create(request);
/* 143 */
        Website web = SiteUtils.getWeb(request);
/* 144 */
        if (vldExist(id, web.getId(), errors)) {
/* 145 */
            return errors;
        }
/* 147 */
        return errors;
    }

    private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 151 */
        WebErrors errors = WebErrors.create(request);
/* 152 */
        Website web = SiteUtils.getWeb(request);
/* 153 */
        if (vldExist(id, web.getId(), errors)) {
/* 154 */
            return errors;
        }
/* 156 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 160 */
        WebErrors errors = WebErrors.create(request);
/* 161 */
        Website web = SiteUtils.getWeb(request);
/* 162 */
        errors.ifEmpty(ids, "ids");
/* 163 */
        for (Long id : ids) {
/* 164 */
            vldExist(id, web.getId(), errors);
        }
/* 166 */
        return errors;
    }

    private boolean vldExist(Long id, Long webId, WebErrors errors) {
/* 170 */
        if (errors.ifNull(id, "id")) {
/* 171 */
            return true;
        }
/* 173 */
        PopularityGroup entity = this.manager.findById(id);
/* 174 */
        if (errors.ifNotExist(entity, PopularityGroup.class, id)) {
/* 175 */
            return true;
        }
/* 177 */
        return false;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.PopularityGroupAct
 * JD-Core Version:    0.6.2
 */