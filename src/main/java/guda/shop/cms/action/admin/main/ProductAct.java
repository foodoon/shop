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
    /*  74 */   private static final Logger log = LoggerFactory.getLogger(ProductAct.class);

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

    /*  82 */
    @RequestMapping({"/product/v_list.do"})
    public String list(Long ctgId, Boolean isOnSale, Boolean isRecommend, Boolean isSpecial, Boolean isHotsale, Boolean isNewProduct, Long typeId, Double startSalePrice, Double endSalePrice, Integer startStock, Integer endStock, Integer pageNo, HttpServletRequest request, ModelMap model) {
        String productName = RequestUtils.getQueryParam(request, "productName");
/*  83 */
        productName = StringUtils.trim(productName);
/*  84 */
        String brandName = RequestUtils.getQueryParam(request, "brandName");
/*  85 */
        brandName = StringUtils.trim(brandName);
/*  86 */
        Website web = SiteUtils.getWeb(request);
/*  87 */
        if (ctgId != null) {
/*  88 */
            model.addAttribute("category", this.categoryMng.findById(ctgId));
        }
/*  90 */
        Pagination pagination = this.manager.getPage(SiteUtils.getWebId(request),
/*  91 */       ctgId, productName, brandName, isOnSale, isRecommend, isSpecial, isHotsale, isNewProduct, typeId, 
/*  92 */       startSalePrice, endSalePrice, startStock, endStock, 
/*  93 */       SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  94 */
        List typeList = this.productTypeMng.getList(web.getId());
/*  95 */
        model.addAttribute("typeList", typeList);
/*  96 */
        model.addAttribute("productName", productName);
/*  97 */
        model.addAttribute("brandName", brandName);
/*  98 */
        model.addAttribute("isOnSale", isOnSale);
/*  99 */
        model.addAttribute("isRecommend", isRecommend);
/* 100 */
        model.addAttribute("isSpecial", isSpecial);
/* 101 */
        model.addAttribute("isHotsale", isHotsale);
/* 102 */
        model.addAttribute("isNewProduct", isNewProduct);
/* 103 */
        model.addAttribute("typeId", typeId);
/* 104 */
        model.addAttribute("startSalePrice", startSalePrice);
/* 105 */
        model.addAttribute("endSalePrice", endSalePrice);
/* 106 */
        model.addAttribute("startStock", startStock);
/* 107 */
        model.addAttribute("endStock", endStock);
/* 108 */
        model.addAttribute("pagination", pagination);
/* 109 */
        model.addAttribute("ctgId", ctgId);
/* 110 */
        return "product/list";
    }

    @RequestMapping({"/product/v_left.do"})
    public String left(HttpServletRequest request, ModelMap model) {
/* 115 */
        List list = this.categoryMng.getTopList(
/* 116 */       SiteUtils.getWebId(request));

/* 118 */
        if (list.size() > 0) {
/* 119 */
            Category treeRoot = new Category();
/* 120 */
            treeRoot.setName(
/* 121 */         MessageResolver.getMessage(request, 
/* 121 */         "product.allCategory", new Object[0]));
/* 122 */
            treeRoot.setChild(new LinkedHashSet(list));
/* 123 */
            model.addAttribute("treeRoot", treeRoot);
        }
/* 125 */
        return "product/left";
    }

    @RequestMapping({"/product/v_add.do"})
    public String add(Long ctgId, HttpServletRequest request, ModelMap model) {
/* 131 */
        Category category = this.categoryMng.findById(ctgId);

/* 133 */
        List itemList = this.productTypePropertyMng.getList(category.getType().getId(), false);
/* 134 */
        List standardTypeList = this.standardTypeMng.getList(category.getId());
/* 135 */
        model.addAttribute("ctgId", ctgId);
/* 136 */
        model.addAttribute("category", category);
/* 137 */
        model.addAttribute("categoryList", this.categoryMng.getListForProduct(SiteUtils.getWebId(request), ctgId));
/* 138 */
        model.addAttribute("tagList", this.productTagMng.getList(SiteUtils.getWebId(request)));
/* 139 */
        model.addAttribute("standardTypeList", standardTypeList);
/* 140 */
        model.addAttribute("itemList", itemList);
/* 141 */
        List exendeds = this.exendedMng.getList(category.getType().getId());
/* 142 */
        Map map = new HashMap();
/* 143 */
        Map map1 = new HashMap();
/* 144 */
        int num = exendeds.size();
/* 145 */
        for (int i = 0; i < num; i++) {
/* 146 */
            map.put(((Exended) exendeds.get(i)).getId().toString(), ((Exended) exendeds.get(i)).getItems());
/* 147 */
            map1.put(((Exended) exendeds.get(i)).getId().toString(), exendeds.get(i));
        }
/* 149 */
        model.addAttribute("map", map);
/* 150 */
        model.addAttribute("map1", map1);
/* 151 */
        return "product/add";
    }

    @RequestMapping({"/product/v_edit.do"})
    public String edit(Long id, Long ctgId, HttpServletRequest request, ModelMap model) {
/* 158 */
        WebErrors errors = validateEdit(id, request);
/* 159 */
        if (errors.hasErrors()) {
/* 160 */
            return errors.showErrorPage(model);
        }
/* 162 */
        Product product = this.manager.findById(id);
/* 163 */
        List psList = this.productStandardMng.findByProductId(id);
/* 164 */
        String productKeywords = StringUtils.join(product.getKeywords(), ",");
/* 165 */
        Category category = product.getCategory();
/* 166 */
        List standardTypeList = this.standardTypeMng.getList(category.getId());
/* 167 */
        List itemList = this.productTypePropertyMng.getList(category.getType().getId(), false);
/* 168 */
        List pelist = product.getExendeds();
/* 169 */
        List exendeds = this.exendedMng.getList(category.getType().getId());
/* 170 */
        Map map = new HashMap();
/* 171 */
        Map map1 = new HashMap();
/* 172 */
        int num = exendeds.size();
/* 173 */
        for (int i = 0; i < num; i++) {
/* 174 */
            map.put(((Exended) exendeds.get(i)).getId().toString(), ((Exended) exendeds.get(i)).getItems());
/* 175 */
            map1.put(((Exended) exendeds.get(i)).getId().toString(), exendeds.get(i));
        }
/* 177 */
        Map map2 = new HashMap();
/* 178 */
        for (int i = 0; i < pelist.size(); i++) {
/* 179 */
            map2.put(((ProductExended) pelist.get(i)).getName(), ((ProductExended) pelist.get(i)).getValue());
        }
/* 181 */
        model.addAttribute("map2", map2);
/* 182 */
        model.addAttribute("map", map);
/* 183 */
        model.addAttribute("map1", map1);
/* 184 */
        model.addAttribute("productKeywords", productKeywords);
/* 185 */
        model.addAttribute("tagList", this.productTagMng.getList(SiteUtils.getWebId(request)));
/* 186 */
        model.addAttribute("categoryList", this.categoryMng.getList(SiteUtils.getWebId(request)));
/* 187 */
        model.addAttribute("standardTypeList", standardTypeList);
/* 188 */
        model.addAttribute("category", category);
/* 189 */
        model.addAttribute("product", product);
/* 190 */
        model.addAttribute("ctgId", ctgId);
/* 191 */
        model.addAttribute("isLimit", product.getProductExt().getIslimitTime());
/* 192 */
        model.addAttribute("itemList", itemList);
/* 193 */
        model.addAttribute("psList", psList);
/* 194 */
        return "product/edit";
    }

    @RequestMapping({"/product/o_save.do"})
    public String save(Product bean, ProductExt ext, Long categoryId, Long brandId, Long[] tagIds, String productKeywords, String[] nature, Long[] picture, String[] colorImg, Long[] character, @RequestParam(value = "file", required = false) MultipartFile file, String[] fashionSwitchPic, String[] fashionBigPic, String[] fashionAmpPic, Boolean[] isDefaults, Long[] colors, Long[] measures, Integer[] stockCounts, Double[] salePrices, Double[] marketPrices, Double[] costPrices, Long ctgId, HttpServletRequest request, ModelMap model) {
/* 206 */
        WebErrors errors = validateSave(bean, file, request);
/* 207 */
        if (errors.hasErrors()) {
/* 208 */
            return errors.showErrorPage(model);
        }
/* 210 */
        productKeywords = StringUtils.replace(productKeywords, MessageResolver.getMessage(request, "product.keywords.split", new Object[0]), ",");
/* 211 */
        String[] keywords = StringUtils.split(productKeywords, ",");
/* 212 */
        Website web = SiteUtils.getWeb(request);
/* 213 */
        Map exended = RequestUtils.getRequestMap(request, "exended_");
/* 214 */
        List li = new ArrayList((Collection) exended.keySet());
/* 215 */
        String[] names = new String[li.size()];
/* 216 */
        String[] values = new String[li.size()];
/* 217 */
        if (stockCounts != null) {
/* 218 */
            Integer stockCount = Integer.valueOf(0);
/* 219 */
            for (Integer s : stockCounts) {
/* 220 */
                stockCount = Integer.valueOf(stockCount.intValue() + s.intValue());
            }
/* 222 */
            bean.setStockCount(stockCount);
        }
/* 224 */
        for (int i = 0; i < li.size(); i++) {
/* 225 */
            names[i] = ((String) li.get(i));
/* 226 */
            values[i] = ((String) exended.get(li.get(i)));
        }
/* 228 */
        bean.setAttr(RequestUtils.getRequestMap(request, "attr_"));
/* 229 */
        bean = this.manager.save(bean, ext, web.getId(), categoryId, brandId, tagIds,
/* 230 */       keywords, names, values, fashionSwitchPic, fashionBigPic, fashionAmpPic, file);
/* 231 */
        if (picture != null) {
/* 232 */
            for (int i = 0; i < picture.length; i++) {
/* 233 */
                ProductStandard ps = new ProductStandard();
/* 234 */
                ps.setImgPath(colorImg[i]);
/* 235 */
                ps.setType(this.standardMng.findById(picture[i]).getType());
/* 236 */
                ps.setProduct(bean);
/* 237 */
                ps.setStandard(this.standardMng.findById(picture[i]));
/* 238 */
                ps.setDataType(true);
/* 239 */
                this.productStandardMng.save(ps);
            }
        }
/* 242 */
        if (character != null) {
/* 243 */
            for (int i = 0; i < character.length; i++) {
/* 244 */
                ProductStandard ps = new ProductStandard();
/* 245 */
                ps.setType(this.standardMng.findById(character[i]).getType());
/* 246 */
                ps.setProduct(bean);
/* 247 */
                ps.setStandard(this.standardMng.findById(character[i]));
/* 248 */
                ps.setDataType(false);
/* 249 */
                this.productStandardMng.save(ps);
            }
        }
/* 252 */
        saveProductFash(bean, nature, isDefaults, stockCounts, salePrices, marketPrices, costPrices);
/* 253 */
        log.info("save Product. id={}", bean.getId());
/* 254 */
        model.addAttribute("message", "global.success");
/* 255 */
        model.addAttribute("brandId", brandId);
/* 256 */
        return add(ctgId, request, model);
    }

    @RequestMapping({"/product/o_update.do"})
    public String update(Product bean, ProductExt ext, Long categoryId, Long brandId, Long[] tagIds, String productKeywords, String[] nature, Long[] picture, String[] colorImg, Long[] character, @RequestParam(value = "file", required = false) MultipartFile file, String[] fashionSwitchPic, String[] fashionBigPic, String[] fashionAmpPic, Boolean[] isDefaults, Long[] colors, Long[] measures, Integer[] stockCounts, Double[] salePrices, Double[] marketPrices, Double[] costPrices, Long[] fashId, Long ctgId, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 269 */
        WebErrors errors = validateUpdate(bean.getId(), file, request);
/* 270 */
        if (errors.hasErrors()) {
/* 271 */
            return errors.showErrorPage(model);
        }
/* 273 */
        productKeywords = StringUtils.replace(productKeywords,
/* 274 */       MessageResolver.getMessage(request, "product.keywords.split", new Object[0]), ",");
/* 275 */
        String[] keywords = StringUtils.split(productKeywords, ",");
/* 276 */
        Map exended = RequestUtils.getRequestMap(request, "exended_");
/* 277 */
        List li = new ArrayList((Collection) exended.keySet());
/* 278 */
        String[] names = new String[li.size()];
/* 279 */
        String[] values = new String[li.size()];
/* 280 */
        for (int i = 0; i < li.size(); i++) {
/* 281 */
            names[i] = ((String) li.get(i));
/* 282 */
            values[i] = ((String) exended.get(li.get(i)));
        }
/* 284 */
        Map attr = RequestUtils.getRequestMap(request, "attr_");
/* 285 */
        if (stockCounts != null) {
/* 286 */
            Integer stockCount = Integer.valueOf(0);
/* 287 */
            for (Integer s : stockCounts) {
/* 288 */
                stockCount = Integer.valueOf(stockCount.intValue() + s.intValue());
            }
/* 290 */
            bean.setStockCount(stockCount);
        }
/* 292 */
        bean = this.manager.update(bean, ext, categoryId, brandId, tagIds, keywords, names, values, attr,
/* 293 */       fashionSwitchPic, fashionBigPic, fashionAmpPic, file);
/* 294 */
        List pcList = this.productStandardMng.findByProductId(bean.getId());
/* 295 */
        for (int j = 0; j < pcList.size(); j++) {
/* 296 */
            this.productStandardMng.deleteById(((ProductStandard) pcList.get(j)).getId());
        }
/* 298 */
        if (picture != null) {
/* 299 */
            for (int i = 0; i < picture.length; i++) {
/* 300 */
                ProductStandard ps = new ProductStandard();
/* 301 */
                ps.setImgPath(colorImg[i]);
/* 302 */
                ps.setType(this.standardMng.findById(picture[i]).getType());
/* 303 */
                ps.setProduct(bean);
/* 304 */
                ps.setStandard(this.standardMng.findById(picture[i]));
/* 305 */
                ps.setDataType(true);
/* 306 */
                this.productStandardMng.save(ps);
            }
        }
/* 309 */
        if (character != null) {
/* 310 */
            for (int i = 0; i < character.length; i++) {
/* 311 */
                ProductStandard ps = new ProductStandard();
/* 312 */
                ps.setType(this.standardMng.findById(character[i]).getType());
/* 313 */
                ps.setProduct(bean);
/* 314 */
                ps.setStandard(this.standardMng.findById(character[i]));
/* 315 */
                ps.setDataType(false);
/* 316 */
                this.productStandardMng.save(ps);
            }
        }

/* 320 */
        if (bean.getCategory().getColorsize().booleanValue()) {
/* 321 */
            Set<ProductFashion> pfList = bean.getFashions();
/* 322 */
            if (fashId != null) {
/* 323 */
                for (ProductFashion ps : pfList) {
/* 324 */
                    if (!Arrays.asList(fashId).contains(ps.getId()))
/* 325 */ this.fashMng.deleteById(ps.getId());
                }
            } else {
/* 329 */
                for (ProductFashion ps : pfList) {
/* 330 */
                    this.fashMng.deleteById(ps.getId());
                }
            }
/* 333 */
            updateProductFash(bean, fashId, nature, isDefaults, stockCounts, salePrices, marketPrices, costPrices);
        }

/* 337 */
        log.info("update Product. id={}.", bean.getId());
/* 338 */
        return list(ctgId, null, null, null, null, null,
/* 339 */       null, null, null, null, null, pageNo, request, model);
    }

    @RequestMapping({"/product/o_delete.do"})
    public String delete(Long[] ids, Long ctgId, Boolean isRecommend, Boolean isSpecial, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 346 */
        WebErrors errors = validateDelete(ids, request);
/* 347 */
        if (errors.hasErrors()) {
/* 348 */
            return errors.showErrorPage(model);
        }
        try {
/* 352 */
            Product[] beans = this.manager.deleteByIds(ids);
/* 353 */
            for (Product bean : beans)
/* 354 */
                log.info("delete Product. id={}", bean.getId());
        } catch (Exception e) {
/* 357 */
            return "redirect:v_error.do";
        }
        Product[] beans;
/* 359 */
        return list(ctgId, null, isRecommend, isSpecial, null, null, null, null, null, null, null, pageNo, request, model);
    }

    @RequestMapping({"/product/v_error.do"})
    public String error(HttpServletRequest request, ModelMap model) {
/* 365 */
        return "product/error";
    }

    @RequestMapping({"/product/v_standardTypes_add.do"})
    public String standardTypesAdd(Long categoryId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 371 */
        List standardTypeList = this.standardTypeMng.getList(categoryId);
/* 372 */
        model.addAttribute("standardTypeList", standardTypeList);
/* 373 */
        model.addAttribute("digit", Integer.valueOf(standardTypeList.size()));
/* 374 */
        response.setHeader("Cache-Control", "no-cache");
/* 375 */
        response.setContentType("text/json;charset=UTF-8");
/* 376 */
        return "product/standardTypes_add";
    }

    @RequestMapping({"/product/v_standards_add.do"})
    public String standards(Long standardTypeId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
/* 382 */
        List sList = this.standardMng.findByTypeId(standardTypeId);
/* 383 */
        model.addAttribute("sList", sList);
/* 384 */
        model.addAttribute("standardType", this.standardTypeMng.findById(standardTypeId));
/* 385 */
        response.setHeader("Cache-Control", "no-cache");
/* 386 */
        response.setContentType("text/json;charset=UTF-8");
/* 387 */
        return "product/standards_add";
    }

    @RequestMapping({"/product/o_create_index.do"})
    public String createIndex(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 393 */
        String path = this.servletContext.getRealPath("/WEB-INF/lucene");
/* 394 */
        boolean success = false;
        try {
/* 396 */
            int count = this.luceneProductSvc.index(path, null, null, null);
/* 397 */
            model.addAttribute("count", Integer.valueOf(count));
/* 398 */
            success = true;
        } catch (CorruptIndexException e) {
/* 400 */
            log.error("", e);
        } catch (LockObtainFailedException e) {
/* 402 */
            log.error("", e);
        } catch (IOException e) {
/* 404 */
            log.error("", e);
        }
/* 406 */
        model.addAttribute("success", Boolean.valueOf(success));
/* 407 */
        return "product/create_index_result";
    }

    @RequestMapping({"/product/o_delFashion.do"})
    public String deleFashion(Long id, Long productId, HttpServletResponse response)
            throws JSONException {
/* 414 */
        Boolean b = this.productFashionMng.getOneFash(productId);
/* 415 */
        JSONObject j = new JSONObject();
/* 416 */
        if ((b != null) && (b.booleanValue())) {
/* 417 */
            this.productFashionMng.deleteById(id);
/* 418 */
            j.put("message", "删除成功！");
/* 419 */
            j.put("boo", true);
/* 420 */
            ResponseUtils.renderJson(response, j.toString());
/* 421 */
            return null;
        }
/* 423 */
        j.put("message", "必须留一款式！");
/* 424 */
        j.put("boo", false);
/* 425 */
        ResponseUtils.renderJson(response, j.toString());
/* 426 */
        return null;
    }

    private void saveProductFash(Product bean, String[] nature, Boolean[] isDefaults, Integer[] stockCounts, Double[] salePrices, Double[] marketPrices, Double[] costPrices) {
/* 433 */
        if (nature != null)
/* 434 */ for (int i = 0; i < nature.length; i++) {
/* 436 */
            ProductFashion pfash = new ProductFashion();
/* 437 */
            pfash.setCreateTime(new Date());
/* 438 */
            pfash.setIsDefault(isDefaults[i]);
/* 439 */
            pfash.setStockCount(stockCounts[i]);
/* 440 */
            pfash.setMarketPrice(marketPrices[i]);
/* 441 */
            pfash.setSalePrice(salePrices[i]);
/* 442 */
            pfash.setCostPrice(costPrices[i]);
/* 443 */
            pfash.setProductId(bean);
/* 444 */
            pfash.setNature(nature[i]);
/* 445 */
            String[] arr = nature[i].split("_");
/* 446 */
            ProductFashion fashion = this.productFashionMng.save(pfash, arr);
/* 447 */
            this.productFashionMng.addStandard(fashion, arr);
/* 448 */
            if (isDefaults[i].booleanValue()) {
/* 449 */
                bean.setCostPrice(costPrices[i]);
/* 450 */
                bean.setMarketPrice(marketPrices[i]);
/* 451 */
                bean.setSalePrice(salePrices[i]);
/* 452 */
                this.manager.update(bean);
            }
        }
    }

    private void updateProductFash(Product bean, Long[] fashId, String[] nature, Boolean[] isDefaults, Integer[] stockCounts, Double[] salePrices, Double[] marketPrices, Double[] costPrices) {
/* 461 */
        if (nature != null)
/* 462 */ for (int i = 0; i < nature.length; i++) {
/* 465 */
            if ((fashId != null) && (i < fashId.length)) {
/* 466 */
                ProductFashion pfash = this.productFashionMng.findById(fashId[i]);
/* 467 */
                pfash.setCreateTime(new Date());
/* 468 */
                pfash.setIsDefault(isDefaults[i]);
/* 469 */
                pfash.setStockCount(stockCounts[i]);
/* 470 */
                pfash.setMarketPrice(marketPrices[i]);
/* 471 */
                pfash.setSalePrice(salePrices[i]);
/* 472 */
                pfash.setCostPrice(costPrices[i]);
/* 473 */
                pfash.setProductId(bean);
/* 474 */
                pfash.setNature(nature[i]);
/* 475 */
                String[] arr = nature[i].split("_");
/* 476 */
                this.productFashionMng.updateStandard(pfash, arr);
/* 477 */
                if (isDefaults[i].booleanValue()) {
/* 478 */
                    bean.setCostPrice(costPrices[i]);
/* 479 */
                    bean.setMarketPrice(marketPrices[i]);
/* 480 */
                    bean.setSalePrice(salePrices[i]);
/* 481 */
                    this.manager.update(bean);
                }
            } else {
/* 484 */
                ProductFashion pfash = new ProductFashion();
/* 485 */
                pfash.setCreateTime(new Date());
/* 486 */
                pfash.setIsDefault(isDefaults[i]);
/* 487 */
                pfash.setStockCount(stockCounts[i]);
/* 488 */
                pfash.setMarketPrice(marketPrices[i]);
/* 489 */
                pfash.setSalePrice(salePrices[i]);
/* 490 */
                pfash.setCostPrice(costPrices[i]);
/* 491 */
                pfash.setProductId(bean);
/* 492 */
                pfash.setNature(nature[i]);
/* 493 */
                String[] arr = nature[i].split("_");
/* 494 */
                ProductFashion fashion = this.productFashionMng.save(pfash, arr);
/* 495 */
                this.productFashionMng.addStandard(fashion, arr);
/* 496 */
                if (isDefaults[i].booleanValue()) {
/* 497 */
                    bean.setCostPrice(costPrices[i]);
/* 498 */
                    bean.setMarketPrice(marketPrices[i]);
/* 499 */
                    bean.setSalePrice(salePrices[i]);
/* 500 */
                    this.manager.update(bean);
                }
            }
        }
    }

    private WebErrors validateSave(Product bean, MultipartFile file, HttpServletRequest request) {
/* 510 */
        WebErrors errors = WebErrors.create(request);
/* 511 */
        if ((file != null) && (!file.isEmpty())) {
/* 512 */
            String name = file.getOriginalFilename();
/* 513 */
            vldImage(name, errors);
        }
/* 515 */
        bean.setWebsite(SiteUtils.getWeb(request));
/* 516 */
        return errors;
    }

    private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 520 */
        WebErrors errors = WebErrors.create(request);
/* 521 */
        errors.ifNull(id, "id");
/* 522 */
        vldExist(id, errors);
/* 523 */
        return errors;
    }

    private WebErrors validateUpdate(Long id, MultipartFile file, HttpServletRequest request) {
/* 528 */
        WebErrors errors = WebErrors.create(request);
/* 529 */
        errors.ifNull(id, "id");
/* 530 */
        if ((file != null) && (!file.isEmpty())) {
/* 531 */
            String name = file.getOriginalFilename();
/* 532 */
            vldImage(name, errors);
/* 533 */
            if (errors.hasErrors()) {
/* 534 */
                return errors;
            }
        }
/* 537 */
        vldExist(id, errors);
/* 538 */
        return errors;
    }

    private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 542 */
        WebErrors errors = WebErrors.create(request);
/* 543 */
        errors.ifEmpty(ids, "ids");
/* 544 */
        for (Long id : ids) {
/* 545 */
            vldExist(id, errors);
        }
/* 547 */
        return errors;
    }

    private void vldExist(Long id, WebErrors errors) {
/* 551 */
        if (errors.hasErrors()) {
/* 552 */
            return;
        }
/* 554 */
        Product entity = this.manager.findById(id);
/* 555 */
        errors.ifNotExist(entity, Product.class, id);
    }

    private void vldImage(String filename, WebErrors errors) {
/* 559 */
        if (errors.hasErrors()) {
/* 560 */
            return;
        }
/* 562 */
        String ext = FilenameUtils.getExtension(filename);
/* 563 */
        if (!ImageUtils.isImage(ext))
/* 564 */ errors.addErrorString("not support image extension:" + filename);
    }

    public void setServletContext(ServletContext servletContext) {
/* 595 */
        this.servletContext = servletContext;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ProductAct
 * JD-Core Version:    0.6.2
 */