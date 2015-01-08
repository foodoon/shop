package guda.shop.cms.action.admin.main;

import guda.shop.cms.entity.*;
import guda.shop.cms.lucene.LuceneProductSvc;
import guda.shop.cms.manager.*;
import guda.shop.cms.web.SiteUtils;
import guda.shop.common.image.ImageUtils;
import guda.shop.common.page.Pagination;
import guda.shop.common.page.SimplePage;
import guda.shop.common.web.CookieUtils;
import guda.shop.common.web.RequestUtils;
import guda.shop.common.web.ResponseUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.WebErrors;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.store.LockObtainFailedException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class ProductAct
        implements ServletContextAware {
       private static final Logger log = LoggerFactory.getLogger(ProductAct.class);

    @Autowired
    private StandardMng standardMng;

    @Autowired
    private StandardTypeMng standardTypeMng;

    @Autowired
    private ProductFashionMng fashMng;

    @Autowired
    private LuceneProductSvc luceneProductSvc;

    @Autowired
    private ProductTagMng productTagMng;

    @Autowired
    private CategoryMng categoryMng;

    @Autowired
    private ProductMng manager;

    @Autowired
    private ProductTypePropertyMng productTypePropertyMng;

    @Autowired
    private ProductFashionMng productFashionMng;

    @Autowired
    private ProductTypeMng productTypeMng;

    @Autowired
    private ExendedMng exendedMng;

    @Autowired
    private ProductStandardMng productStandardMng;
    private ServletContext servletContext;


    @RequestMapping({"/product/v_list.do"})
    public String list(Long ctgId, Boolean isOnSale, Boolean isRecommend, Boolean isSpecial, Boolean isHotsale, Boolean isNewProduct, Long typeId, Double startSalePrice, Double endSalePrice, Integer startStock, Integer endStock, Integer pageNo, HttpServletRequest request, ModelMap model) {
        String productName = RequestUtils.getQueryParam(request, "productName");

        productName = StringUtils.trim(productName);

        String brandName = RequestUtils.getQueryParam(request, "brandName");

        brandName = StringUtils.trim(brandName);

        Website web = SiteUtils.getWeb(request);

        if (ctgId != null) {

            model.addAttribute("category", this.categoryMng.findById(ctgId));
        }

        Pagination pagination = this.manager.getPage(SiteUtils.getWebId(request),
       ctgId, productName, brandName, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId,
       startSalePrice, endSalePrice, startStock, endStock,
       SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));

        List typeList = this.productTypeMng.getList(web.getId());

        model.addAttribute("typeList", typeList);

        model.addAttribute("productName", productName);

        model.addAttribute("brandName", brandName);

        model.addAttribute("isOnSale", isOnSale);

        model.addAttribute("isRecommend", isRecommend);

        model.addAttribute("isSpecial", isSpecial);

        model.addAttribute("isHotsale", isHotsale);

        model.addAttribute("isNewProduct", isNewProduct);

        model.addAttribute("typeId", typeId);

        model.addAttribute("startSalePrice", startSalePrice);

        model.addAttribute("endSalePrice", endSalePrice);

        model.addAttribute("startStock", startStock);

        model.addAttribute("endStock", endStock);

        model.addAttribute("pagination", pagination);

        model.addAttribute("ctgId", ctgId);

        return "product/list";
    }

    @RequestMapping({"/product/v_left.do"})
    public String left(HttpServletRequest request, ModelMap model) {

        List list = this.categoryMng.getTopList(
       SiteUtils.getWebId(request));


        if (list.size() > 0) {

            Category treeRoot = new Category();

            treeRoot.setName(
         MessageResolver.getMessage(request,
         "product.allCategory", new Object[0]));

            treeRoot.setChild(new LinkedHashSet(list));

            model.addAttribute("treeRoot", treeRoot);
        }

        return "product/left";
    }

    @RequestMapping({"/product/v_add.do"})
    public String add(Long ctgId, HttpServletRequest request, ModelMap model) {

        Category category = this.categoryMng.findById(ctgId);


        List itemList = this.productTypePropertyMng.getList(category.getType().getId(), false);

        List standardTypeList = this.standardTypeMng.getList(category.getId());

        model.addAttribute("ctgId", ctgId);

        model.addAttribute("category", category);

        model.addAttribute("categoryList", this.categoryMng.getListForProduct(SiteUtils.getWebId(request), ctgId));

        model.addAttribute("tagList", this.productTagMng.getList(SiteUtils.getWebId(request)));

        model.addAttribute("standardTypeList", standardTypeList);

        model.addAttribute("itemList", itemList);

        List exendeds = this.exendedMng.getList(category.getType().getId());

        Map map = new HashMap();

        Map map1 = new HashMap();

        int num = exendeds.size();

        for (int i = 0; i < num; i++) {

            map.put(((Exended) exendeds.get(i)).getId().toString(), ((Exended) exendeds.get(i)).getItems());

            map1.put(((Exended) exendeds.get(i)).getId().toString(), exendeds.get(i));
        }

        model.addAttribute("map", map);

        model.addAttribute("map1", map1);

        return "product/add";
    }

    @RequestMapping({"/product/v_edit.do"})
    public String edit(Long id, Long ctgId, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateEdit(id, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        Product product = this.manager.findById(id);

        List psList = this.productStandardMng.findByProductId(id);

        String productKeywords = StringUtils.join(product.getKeywords(), ",");

        Category category = product.getCategory();

        List standardTypeList = this.standardTypeMng.getList(category.getId());

        List itemList = this.productTypePropertyMng.getList(category.getType().getId(), false);

        List pelist = product.getExendeds();

        List exendeds = this.exendedMng.getList(category.getType().getId());

        Map map = new HashMap();

        Map map1 = new HashMap();

        int num = exendeds.size();

        for (int i = 0; i < num; i++) {

            map.put(((Exended) exendeds.get(i)).getId().toString(), ((Exended) exendeds.get(i)).getItems());

            map1.put(((Exended) exendeds.get(i)).getId().toString(), exendeds.get(i));
        }

        Map map2 = new HashMap();

        for (int i = 0; i < pelist.size(); i++) {

            map2.put(((ProductExended) pelist.get(i)).getName(), ((ProductExended) pelist.get(i)).getValue());
        }

        model.addAttribute("map2", map2);

        model.addAttribute("map", map);

        model.addAttribute("map1", map1);

        model.addAttribute("productKeywords", productKeywords);

        model.addAttribute("tagList", this.productTagMng.getList(SiteUtils.getWebId(request)));

        model.addAttribute("categoryList", this.categoryMng.getList(SiteUtils.getWebId(request)));

        model.addAttribute("standardTypeList", standardTypeList);

        model.addAttribute("category", category);

        model.addAttribute("product", product);

        model.addAttribute("ctgId", ctgId);

        model.addAttribute("isLimit", product.getProductExt().getIslimitTime());

        model.addAttribute("itemList", itemList);

        model.addAttribute("psList", psList);

        return "product/edit";
    }

    @RequestMapping({"/product/o_save.do"})
    public String save(Product bean, ProductExt ext, Long categoryId, Long brandId, Long[] tagIds, String productKeywords, String[] nature, Long[] picture, String[] colorImg, Long[] character, @RequestParam(value = "file", required = false) MultipartFile file, String[] fashionSwitchPic, String[] fashionBigPic, String[] fashionAmpPic, Boolean[] isDefaults, Long[] colors, Long[] measures, Integer[] stockCounts, Double[] salePrices, Double[] marketPrices, Double[] costPrices, Long ctgId, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateSave(bean, file, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        productKeywords = StringUtils.replace(productKeywords, MessageResolver.getMessage(request, "product.keywords.split", new Object[0]), ",");

        String[] keywords = StringUtils.split(productKeywords, ",");

        Website web = SiteUtils.getWeb(request);

        Map exended = RequestUtils.getRequestMap(request, "exended_");

        List li = new ArrayList((Collection) exended.keySet());

        String[] names = new String[li.size()];

        String[] values = new String[li.size()];

        if (stockCounts != null) {

            Integer stockCount = Integer.valueOf(0);

            for (Integer s : stockCounts) {

                stockCount = Integer.valueOf(stockCount.intValue() + s.intValue());
            }

            bean.setStockCount(stockCount);
        }

        for (int i = 0; i < li.size(); i++) {

            names[i] = ((String) li.get(i));

            values[i] = ((String) exended.get(li.get(i)));
        }

        bean.setAttr(RequestUtils.getRequestMap(request, "attr_"));

        bean = this.manager.save(bean, ext, web.getId(), categoryId, brandId, tagIds,
       keywords, names, values, fashionSwitchPic, fashionBigPic, fashionAmpPic, file);

        if (picture != null) {

            for (int i = 0; i < picture.length; i++) {

                ProductStandard ps = new ProductStandard();

                ps.setImgPath(colorImg[i]);

                ps.setType(this.standardMng.findById(picture[i]).getType());

                ps.setProduct(bean);

                ps.setStandard(this.standardMng.findById(picture[i]));

                ps.setDataType(true);

                this.productStandardMng.save(ps);
            }
        }

        if (character != null) {

            for (int i = 0; i < character.length; i++) {

                ProductStandard ps = new ProductStandard();

                ps.setType(this.standardMng.findById(character[i]).getType());

                ps.setProduct(bean);

                ps.setStandard(this.standardMng.findById(character[i]));

                ps.setDataType(false);

                this.productStandardMng.save(ps);
            }
        }

        saveProductFash(bean, nature, isDefaults, stockCounts, salePrices, marketPrices, costPrices);

        log.info("save Product. id={}", bean.getId());

        model.addAttribute("message", "global.success");

        model.addAttribute("brandId", brandId);

        return add(ctgId, request, model);
    }

    @RequestMapping({"/product/o_update.do"})
    public String update(Product bean, ProductExt ext, Long categoryId, Long brandId, Long[] tagIds, String productKeywords, String[] nature, Long[] picture, String[] colorImg, Long[] character, @RequestParam(value = "file", required = false) MultipartFile file, String[] fashionSwitchPic, String[] fashionBigPic, String[] fashionAmpPic, Boolean[] isDefaults, Long[] colors, Long[] measures, Integer[] stockCounts, Double[] salePrices, Double[] marketPrices, Double[] costPrices, Long[] fashId, Long ctgId, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateUpdate(bean.getId(), file, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }

        productKeywords = StringUtils.replace(productKeywords,
       MessageResolver.getMessage(request, "product.keywords.split", new Object[0]), ",");

        String[] keywords = StringUtils.split(productKeywords, ",");

        Map exended = RequestUtils.getRequestMap(request, "exended_");

        List li = new ArrayList((Collection) exended.keySet());

        String[] names = new String[li.size()];

        String[] values = new String[li.size()];

        for (int i = 0; i < li.size(); i++) {

            names[i] = ((String) li.get(i));

            values[i] = ((String) exended.get(li.get(i)));
        }

        Map attr = RequestUtils.getRequestMap(request, "attr_");

        if (stockCounts != null) {

            Integer stockCount = Integer.valueOf(0);

            for (Integer s : stockCounts) {

                stockCount = Integer.valueOf(stockCount.intValue() + s.intValue());
            }

            bean.setStockCount(stockCount);
        }

        bean = this.manager.update(bean, ext, categoryId, brandId, tagIds, keywords, names, values, attr,
       fashionSwitchPic, fashionBigPic, fashionAmpPic, file);

        List pcList = this.productStandardMng.findByProductId(bean.getId());

        for (int j = 0; j < pcList.size(); j++) {

            this.productStandardMng.deleteById(((ProductStandard) pcList.get(j)).getId());
        }

        if (picture != null) {

            for (int i = 0; i < picture.length; i++) {

                ProductStandard ps = new ProductStandard();

                ps.setImgPath(colorImg[i]);

                ps.setType(this.standardMng.findById(picture[i]).getType());

                ps.setProduct(bean);

                ps.setStandard(this.standardMng.findById(picture[i]));

                ps.setDataType(true);

                this.productStandardMng.save(ps);
            }
        }

        if (character != null) {

            for (int i = 0; i < character.length; i++) {

                ProductStandard ps = new ProductStandard();

                ps.setType(this.standardMng.findById(character[i]).getType());

                ps.setProduct(bean);

                ps.setStandard(this.standardMng.findById(character[i]));

                ps.setDataType(false);

                this.productStandardMng.save(ps);
            }
        }


        if (bean.getCategory().getColorsize().booleanValue()) {

            Set<ProductFashion> pfList = bean.getFashions();

            if (fashId != null) {

                for (ProductFashion ps : pfList) {

                    if (!Arrays.asList(fashId).contains(ps.getId()))
 this.fashMng.deleteById(ps.getId());
                }
            } else {

                for (ProductFashion ps : pfList) {

                    this.fashMng.deleteById(ps.getId());
                }
            }

            updateProductFash(bean, fashId, nature, isDefaults, stockCounts, salePrices, marketPrices, costPrices);
        }


        log.info("update Product. id={}.", bean.getId());

        return list(ctgId, null, null, null, null, null,
       null, null, null, null, null, pageNo, request, model);
    }

    @RequestMapping({"/product/o_delete.do"})
    public String delete(Long[] ids, Long ctgId, Boolean isRecommend, Boolean isSpecial, Integer pageNo, HttpServletRequest request, ModelMap model) {

        WebErrors errors = validateDelete(ids, request);

        if (errors.hasErrors()) {

            return errors.showErrorPage(model);
        }
        try {

            Product[] beans = this.manager.deleteByIds(ids);

            for (Product bean : beans)

                log.info("delete Product. id={}", bean.getId());
        } catch (Exception e) {

            return "redirect:v_error.do";
        }
        Product[] beans;

        return list(ctgId, null, isRecommend, isSpecial, null, null, null, null, null, null, null, pageNo, request, model);
    }

    @RequestMapping({"/product/v_error.do"})
    public String error(HttpServletRequest request, ModelMap model) {

        return "product/error";
    }

    @RequestMapping({"/product/v_standardTypes_add.do"})
    public String standardTypesAdd(Long categoryId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        List standardTypeList = this.standardTypeMng.getList(categoryId);

        model.addAttribute("standardTypeList", standardTypeList);

        model.addAttribute("digit", Integer.valueOf(standardTypeList.size()));

        response.setHeader("Cache-Control", "no-cache");

        response.setContentType("text/json;charset=UTF-8");

        return "product/standardTypes_add";
    }

    @RequestMapping({"/product/v_standards_add.do"})
    public String standards(Long standardTypeId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {

        List sList = this.standardMng.findByTypeId(standardTypeId);

        model.addAttribute("sList", sList);

        model.addAttribute("standardType", this.standardTypeMng.findById(standardTypeId));

        response.setHeader("Cache-Control", "no-cache");

        response.setContentType("text/json;charset=UTF-8");

        return "product/standards_add";
    }

    @RequestMapping({"/product/o_create_index.do"})
    public String createIndex(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        String path = this.servletContext.getRealPath("/WEB-INF/lucene");

        boolean success = false;
        try {

            int count = this.luceneProductSvc.index(path, null, null, null);

            model.addAttribute("count", Integer.valueOf(count));

            success = true;
        } catch (CorruptIndexException e) {

            log.error("", e);
        } catch (LockObtainFailedException e) {

            log.error("", e);
        } catch (IOException e) {

            log.error("", e);
        }

        model.addAttribute("success", Boolean.valueOf(success));

        return "product/create_index_result";
    }

    @RequestMapping({"/product/o_delFashion.do"})
    public String deleFashion(Long id, Long productId, HttpServletResponse response)
            throws JSONException {

        Boolean b = this.productFashionMng.getOneFash(productId);

        JSONObject j = new JSONObject();

        if ((b != null) && (b.booleanValue())) {

            this.productFashionMng.deleteById(id);

            j.put("message", "删除成功！");

            j.put("boo", true);

            ResponseUtils.renderJson(response, j.toString());

            return null;
        }

        j.put("message", "必须留一款式！");

        j.put("boo", false);

        ResponseUtils.renderJson(response, j.toString());

        return null;
    }

    private void saveProductFash(Product bean, String[] nature, Boolean[] isDefaults, Integer[] stockCounts, Double[] salePrices, Double[] marketPrices, Double[] costPrices) {

        if (nature != null)
 for (int i = 0; i < nature.length; i++) {

            ProductFashion pfash = new ProductFashion();

            pfash.setCreateTime(new Date());

            pfash.setIsDefault(isDefaults[i]);

            pfash.setStockCount(stockCounts[i]);

            pfash.setMarketPrice(marketPrices[i]);

            pfash.setSalePrice(salePrices[i]);

            pfash.setCostPrice(costPrices[i]);

            pfash.setProductId(bean);

            pfash.setNature(nature[i]);

            String[] arr = nature[i].split("_");

            ProductFashion fashion = this.productFashionMng.save(pfash, arr);

            this.productFashionMng.addStandard(fashion, arr);

            if (isDefaults[i].booleanValue()) {

                bean.setCostPrice(costPrices[i]);

                bean.setMarketPrice(marketPrices[i]);

                bean.setSalePrice(salePrices[i]);

                this.manager.update(bean);
            }
        }
    }

    private void updateProductFash(Product bean, Long[] fashId, String[] nature, Boolean[] isDefaults, Integer[] stockCounts, Double[] salePrices, Double[] marketPrices, Double[] costPrices) {

        if (nature != null)
 for (int i = 0; i < nature.length; i++) {

            if ((fashId != null) && (i < fashId.length)) {

                ProductFashion pfash = this.productFashionMng.findById(fashId[i]);

                pfash.setCreateTime(new Date());

                pfash.setIsDefault(isDefaults[i]);

                pfash.setStockCount(stockCounts[i]);

                pfash.setMarketPrice(marketPrices[i]);

                pfash.setSalePrice(salePrices[i]);

                pfash.setCostPrice(costPrices[i]);

                pfash.setProductId(bean);

                pfash.setNature(nature[i]);

                String[] arr = nature[i].split("_");

                this.productFashionMng.updateStandard(pfash, arr);

                if (isDefaults[i].booleanValue()) {

                    bean.setCostPrice(costPrices[i]);

                    bean.setMarketPrice(marketPrices[i]);

                    bean.setSalePrice(salePrices[i]);

                    this.manager.update(bean);
                }
            } else {

                ProductFashion pfash = new ProductFashion();

                pfash.setCreateTime(new Date());

                pfash.setIsDefault(isDefaults[i]);

                pfash.setStockCount(stockCounts[i]);

                pfash.setMarketPrice(marketPrices[i]);

                pfash.setSalePrice(salePrices[i]);

                pfash.setCostPrice(costPrices[i]);

                pfash.setProductId(bean);

                pfash.setNature(nature[i]);

                String[] arr = nature[i].split("_");

                ProductFashion fashion = this.productFashionMng.save(pfash, arr);

                this.productFashionMng.addStandard(fashion, arr);

                if (isDefaults[i].booleanValue()) {

                    bean.setCostPrice(costPrices[i]);

                    bean.setMarketPrice(marketPrices[i]);

                    bean.setSalePrice(salePrices[i]);

                    this.manager.update(bean);
                }
            }
        }
    }

    private WebErrors validateSave(Product bean, MultipartFile file, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        if ((file != null) && (!file.isEmpty())) {

            String name = file.getOriginalFilename();

            vldImage(name, errors);
        }

        bean.setWebsite(SiteUtils.getWeb(request));

        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifNull(id, "id");

        vldExist(id, errors);

        return errors;
    }

    private WebErrors validateUpdate(Long id, MultipartFile file, HttpServletRequest request) {

        WebErrors errors = WebErrors.create(request);

        errors.ifNull(id, "id");

        if ((file != null) && (!file.isEmpty())) {

            String name = file.getOriginalFilename();

            vldImage(name, errors);

            if (errors.hasErrors()) {

                return errors;
            }
        }

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

        Product entity = this.manager.findById(id);

        errors.ifNotExist(entity, Product.class, id);
    }

    private void vldImage(String filename, WebErrors errors) {

        if (errors.hasErrors()) {

            return;
        }

        String ext = FilenameUtils.getExtension(filename);

        if (!ImageUtils.isImage(ext))
 errors.addErrorString("not support image extension:" + filename);
    }

    public void setServletContext(ServletContext servletContext) {

        this.servletContext = servletContext;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ProductAct
 * JD-Core Version:    0.6.2
 */