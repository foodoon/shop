/*     */ package guda.shop.action.front;
/*     */ 
/*     */ import guda.shop.cms.entity.Brand;
/*     */ import guda.shop.cms.entity.Category;
/*     */ import guda.shop.cms.entity.Exended;
/*     */ import guda.shop.cms.entity.Product;
/*     */ import guda.shop.cms.entity.ProductFashion;
/*     */ import guda.shop.cms.entity.ProductType;
/*     */ import guda.shop.cms.entity.ShopArticle;
/*     */ import guda.shop.cms.entity.ShopChannel;
/*     */ import guda.shop.cms.manager.BrandMng;
/*     */ import guda.shop.cms.manager.CategoryMng;
/*     */ import guda.shop.cms.manager.ExendedMng;
/*     */ import guda.shop.cms.manager.ProductMng;
/*     */ import guda.shop.cms.manager.ProductStandardMng;
/*     */ import guda.shop.cms.manager.ShopArticleMng;
/*     */ import guda.shop.cms.manager.ShopChannelMng;
/*     */ import guda.shop.cms.manager.StandardTypeMng;
/*     */ import guda.shop.cms.web.ShopFrontHelper;
/*     */ import guda.shop.cms.web.SiteUtils;
/*     */ import guda.shop.common.web.RequestUtils;
/*     */ import guda.shop.common.web.springmvc.MessageResolver;
/*     */ import guda.shop.core.entity.Global;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.web.WebErrors;
/*     */ import guda.shop.core.web.front.FrontHelper;
/*     */ import guda.shop.core.web.front.URLHelper;
/*     */ import guda.shop.core.web.front.URLHelper.URLInfo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class DynamicPageAct
/*     */ {
/*     */   public static final String TPL_INDEX = "tpl.index";
/*     */   private static final String BRAND = "tpl.brand";
/*     */   private static final String BRAND_DETAIL = "tpl.brandDetail";
/*     */ 
/*     */   @Autowired
/*     */   private CategoryMng categoryMng;
/*     */ 
/*     */   @Autowired
/*     */   private ProductMng productMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShopChannelMng shopChannelMng;
/*     */ 
/*     */   @Autowired
/*     */   private ShopArticleMng shopArticleMng;
/*     */ 
/*     */   @Autowired
/*     */   private BrandMng brandMng;
/*     */ 
/*     */   @Autowired
/*     */   private StandardTypeMng standardTypeMng;
/*     */ 
/*     */   @Autowired
/*     */   private ProductStandardMng productStandardMng;
/*     */ 
/*     */   @Autowired
/*     */   private ExendedMng exendedMng;
/*     */ 
/*     */   @RequestMapping(value={"/index.jhtml"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String indexForWeblogic(HttpServletRequest request, ModelMap model)
/*     */   {
/*  80 */     return index(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String index(HttpServletRequest request, ModelMap model)
/*     */   {
/*  92 */     Website web = SiteUtils.getWeb(request);
/*  93 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/*  94 */     return web.getTemplate("index", MessageResolver.getMessage(request, "tpl.index", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/**/*.*"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String excute(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 102 */     String url = request.getRequestURL().toString();
/* 103 */     Global global = SiteUtils.getWeb(request).getGlobal();
/* 104 */     model.addAttribute("global", global);
/* 105 */     URLHelper.URLInfo info = URLHelper.getURLInfo(url, request.getContextPath());
/* 106 */     String queryString = request.getQueryString();
/* 107 */     Website web = SiteUtils.getWeb(request);
/* 108 */     ShopFrontHelper.setDynamicPageData(request, model, web, url, info.getUrlPrefix(), info.getUrlSuffix(), info.getPageNo());
/* 109 */     String[] paths = info.getPaths();
/* 110 */     String[] params = info.getParams();
/* 111 */     int pageNo = info.getPageNo();
/* 112 */     int len = paths.length;
/* 113 */     if (len == 1)
/*     */     {
/* 115 */       return channel(paths[0], params, pageNo, queryString, url, web, request, response, model);
/* 116 */     }if (len == 2) {
/* 117 */       if (paths[1].equals("index"))
/*     */       {
/* 119 */         return channel(paths[0], params, pageNo, queryString, url, web, request, response, model);
/*     */       }
/*     */       try {
/* 122 */         Long id = Long.valueOf(Long.parseLong(paths[1]));
/*     */ 
/* 124 */         return content(paths[0], id, params, pageNo, queryString, url, web, request, response, model);
/*     */       } catch (NumberFormatException localNumberFormatException) {
/*     */       }
/*     */     }
/* 128 */     return FrontHelper.pageNotFound(web, model, request);
/*     */   }
/*     */ 
/*     */   public String channel(String path, String[] params, int pageNo, String queryString, String url, Website web, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 137 */     Category category = this.categoryMng.getByPath(web.getId(), path);
/* 138 */     if (category != null) {
/* 139 */       List exendeds = this.exendedMng.getList(category.getType().getId());
/* 140 */       Map map = new HashMap();
/* 141 */       Map map1 = new HashMap();
/* 142 */       int num = exendeds.size();
/* 143 */       for (int i = 0; i < num; i++) {
/* 144 */         map.put(((Exended)exendeds.get(i)).getId().toString(), ((Exended)exendeds.get(i)).getItems());
/* 145 */         map1.put(((Exended)exendeds.get(i)).getId().toString(), exendeds.get(i));
/*     */       }
/* 147 */       model.addAttribute("brandId", getBrandId(request));
/* 148 */       model.addAttribute("map", map);
/* 149 */       model.addAttribute("map1", map1);
/* 150 */       model.addAttribute("fields", getNames(request));
/* 151 */       model.addAttribute("zhis", getValues(request));
/* 152 */       model.addAttribute("category", category);
/* 153 */       model.addAttribute("pageSize", getpageSize(request));
/* 154 */       model.addAttribute("names", getName(request));
/* 155 */       model.addAttribute("values", getValue(request));
/* 156 */       model.addAttribute("isShow", getIsShow(request));
/* 157 */       model.addAttribute("orderBy", getOrderBy(request));
/* 158 */       return category.getTplChannelRel();
/*     */     }
/* 160 */     ShopChannel channel = this.shopChannelMng.getByPath(web.getId(), path);
/* 161 */     if (channel != null) {
/* 162 */       model.addAttribute("channel", channel);
/* 163 */       return channel.getTplChannelRel();
/*     */     }
/*     */ 
/* 166 */     return FrontHelper.pageNotFound(web, model, request);
/*     */   }
/*     */ 
/*     */   public String content(String chnlName, Long id, String[] params, int pageNo, String queryString, String url, Website web, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 176 */     Category category = this.categoryMng.getByPath(web.getId(), chnlName);
/* 177 */     if (category != null) {
/* 178 */       Product product = this.productMng.findById(id);
/* 179 */       if (product != null) {
/* 180 */         if (product.getProductFashion() != null) {
/* 181 */           String[] arr = (String[])null;
/* 182 */           arr = product.getProductFashion().getNature().split("_");
/* 183 */           model.addAttribute("arr", arr);
/*     */         }
/* 185 */         List psList = this.productStandardMng.findByProductId(id);
/* 186 */         List standardTypeList = this.standardTypeMng.getList(category.getId());
/*     */ 
/* 188 */         model.addAttribute("standardTypeList", standardTypeList);
/* 189 */         model.addAttribute("psList", psList);
/* 190 */         model.addAttribute("category", category);
/* 191 */         model.addAttribute("product", product);
/* 192 */         return category.getTplContentRel();
/*     */       }
/* 194 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/*     */ 
/* 197 */     ShopArticle article = this.shopArticleMng.findById(id);
/* 198 */     if (article != null) {
/* 199 */       ShopChannel channel = article.getChannel();
/* 200 */       model.addAttribute("article", article);
/* 201 */       model.addAttribute("channel", channel);
/* 202 */       return channel.getTplContentRel();
/*     */     }
/* 204 */     return FrontHelper.pageNotFound(web, model, request);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/brand.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String brand(Long id, HttpServletRequest request, ModelMap model)
/*     */   {
/* 214 */     Website web = SiteUtils.getWeb(request);
/* 215 */     WebErrors errors = validateBrand(id, request);
/* 216 */     if (errors.hasErrors())
/* 217 */       return FrontHelper.showError(errors, web, model, request);
/*     */     String tpl;
/*     */     String tpl;
/* 219 */     if (id != null) {
/* 220 */       model.addAttribute("brand", this.brandMng.findById(id));
/* 221 */       tpl = MessageResolver.getMessage(request, "tpl.brandDetail", new Object[0]);
/*     */     } else {
/* 223 */       tpl = MessageResolver.getMessage(request, "tpl.brand", new Object[0]);
/*     */     }
/* 225 */     ShopFrontHelper.setCommonData(request, model, web, 1);
/* 226 */     return web.getTplSys("shop", tpl);
/*     */   }
/*     */ 
/*     */   public Integer getBrandId(HttpServletRequest request) {
/* 230 */     String brandId = RequestUtils.getQueryParam(request, "brandId");
/* 231 */     Integer id = null;
/* 232 */     if (!StringUtils.isBlank(brandId)) {
/* 233 */       id = Integer.valueOf(Integer.parseInt(brandId));
/*     */     }
/* 235 */     return id;
/*     */   }
/*     */ 
/*     */   public Integer getpageSize(HttpServletRequest request) {
/* 239 */     String pageSize = RequestUtils.getQueryParam(request, "pageSize");
/* 240 */     Integer pagesize = null;
/* 241 */     if (!StringUtils.isBlank(pageSize)) {
/* 242 */       pagesize = Integer.valueOf(Integer.parseInt(pageSize));
/*     */     }
/* 244 */     if (pagesize == null) {
/* 245 */       pagesize = Integer.valueOf(12);
/*     */     }
/* 247 */     return pagesize;
/*     */   }
/*     */ 
/*     */   public Integer getIsShow(HttpServletRequest request) {
/* 251 */     String isShow = RequestUtils.getQueryParam(request, "isShow");
/* 252 */     Integer isshow = null;
/* 253 */     if (!StringUtils.isBlank(isShow)) {
/* 254 */       isshow = Integer.valueOf(Integer.parseInt(isShow));
/*     */     }
/* 256 */     if (isshow == null) {
/* 257 */       isshow = Integer.valueOf(0);
/*     */     }
/* 259 */     return isshow;
/*     */   }
/*     */ 
/*     */   public Integer getOrderBy(HttpServletRequest request) {
/* 263 */     String orderBy = RequestUtils.getQueryParam(request, "orderBy");
/* 264 */     Integer orderby = null;
/* 265 */     if (!StringUtils.isBlank(orderBy)) {
/* 266 */       orderby = Integer.valueOf(Integer.parseInt(orderBy));
/*     */     }
/* 268 */     if (orderby == null) {
/* 269 */       orderby = Integer.valueOf(0);
/*     */     }
/* 271 */     return orderby;
/*     */   }
/*     */ 
/*     */   public String[] getNames(HttpServletRequest request) {
/* 275 */     Map attr = RequestUtils.getRequestMap(request, "exended_");
/* 276 */     List li = new ArrayList((Collection)attr.keySet());
/* 277 */     String name = "";
/* 278 */     for (int i = 0; i < li.size(); i++) {
/* 279 */       if (i + 1 == li.size())
/* 280 */         name = name + (String)li.get(i);
/*     */       else {
/* 282 */         name = name + (String)li.get(i) + ",";
/*     */       }
/*     */     }
/* 285 */     String[] names = StringUtils.split(name, ',');
/* 286 */     return names;
/*     */   }
/*     */ 
/*     */   public String[] getValues(HttpServletRequest request) {
/* 290 */     Map attr = RequestUtils.getRequestMap(request, "exended_");
/* 291 */     List li = new ArrayList((Collection)attr.keySet());
/* 292 */     String value = "";
/* 293 */     for (int i = 0; i < li.size(); i++) {
/* 294 */       if (i + 1 == li.size()) {
/* 295 */         if (StringUtils.isBlank((String)attr.get(li.get(i))))
/* 296 */           value = value + "0";
/*     */         else {
/* 298 */           value = value + (String)attr.get(li.get(i));
/*     */         }
/*     */       }
/* 301 */       else if (StringUtils.isBlank((String)attr.get(li.get(i))))
/* 302 */         value = value + "0,";
/*     */       else {
/* 304 */         value = value + (String)attr.get(li.get(i)) + ",";
/*     */       }
/*     */     }
/*     */ 
/* 308 */     String[] values = StringUtils.split(value, ',');
/* 309 */     return values;
/*     */   }
/*     */ 
/*     */   public String getName(HttpServletRequest request)
/*     */   {
/* 314 */     Map attr = RequestUtils.getRequestMap(request, "exended_");
/* 315 */     List li = new ArrayList((Collection)attr.keySet());
/* 316 */     String name = "";
/* 317 */     for (int i = 0; i < li.size(); i++) {
/* 318 */       if (i + 1 == li.size())
/* 319 */         name = name + (String)li.get(i);
/*     */       else {
/* 321 */         name = name + (String)li.get(i) + ",";
/*     */       }
/*     */     }
/*     */ 
/* 325 */     return name;
/*     */   }
/*     */ 
/*     */   public String getValue(HttpServletRequest request) {
/* 329 */     Map attr = RequestUtils.getRequestMap(request, "exended_");
/* 330 */     List li = new ArrayList((Collection)attr.keySet());
/* 331 */     String value = "";
/* 332 */     for (int i = 0; i < li.size(); i++) {
/* 333 */       if (i + 1 == li.size()) {
/* 334 */         if (StringUtils.isBlank((String)attr.get(li.get(i))))
/* 335 */           value = value + "0";
/*     */         else {
/* 337 */           value = value + (String)attr.get(li.get(i));
/*     */         }
/*     */       }
/* 340 */       else if (StringUtils.isBlank((String)attr.get(li.get(i))))
/* 341 */         value = value + "0,";
/*     */       else {
/* 343 */         value = value + (String)attr.get(li.get(i)) + ",";
/*     */       }
/*     */     }
/*     */ 
/* 347 */     return value;
/*     */   }
/*     */ 
/*     */   private WebErrors validateBrand(Long id, HttpServletRequest request) {
/* 351 */     WebErrors errors = WebErrors.create(request);
/* 352 */     if (id != null) {
/* 353 */       Brand brand = this.brandMng.findById(id);
/* 354 */       if (errors.ifNotExist(brand, Brand.class, id)) {
/* 355 */         return errors;
/*     */       }
/*     */     }
/* 358 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.DynamicPageAct
 * JD-Core Version:    0.6.2
 */