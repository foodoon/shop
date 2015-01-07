 package guda.shop.cms.action.front;
/*     */*/ import gcms.entity.Brand;
/*     */ import guda.shop.y.Category;
/*     */ import guda.shop.cms.entit;
/*     */ import guda.shop.cms.entity.Product*/ import guda.shop.cms.entity.ProductFashion;
 import guda.shop.cms.entity.ProductType;
/*     */ im.shop.cms.entity.ShopArticle;
/*     */ import guda.entity.ShopChannel;
/*     */ import guda.shop.cmsBrandMng;
/*     */ import guda.shop.cms.manager.Ca;
/*     */ import guda.shop.cms.manager.ExendedM  */ import guda.shop.cms.manager.ProductMng;
/*    t guda.shop.cms.manager.ProductStandardMng;
/*      guda.shop.cms.manager.ShopArticleMng;
/*     */ im.shop.cms.manager.ShopChannelMng;
/*     */ import guda.shoager.StandardTypeMng;
/*     */ import guda.shop.cms.wentHelper;
/*     */ import guda.shop.cms.web.SiteUtils;/ import guda.shop.common.web.RequestUtils;
/*     */ im.shop.common.web.springmvc.MessageResolver;
/*     *guda.shop.core.entity.Global;
/*     */ importp.core.entity.Website;
/*     */ import guda.shop.cobErrors;
/*     */ import guda.shop.core.web.front.FrontHelper;
/import guda.shop.core.web.front.URLHelper;
/*  ort guda.shop.core.web.front.URLHelper.URLInfo;
 import java.util.ArrayList;
/*     */ import jCollection;
/*     */ import java.util.HashMap;
/*      java.util.List;
/*     */ import java.util.Map;
/*  ort javax.servlet.http.HttpServletRequest;
/*     */ import jlet.http.HttpServletResponse;
/*     *org.apache.commons.lang.StringUtils;
/*mport org.springframework.beans.factation.Autowired;
/*     */ importngframework.stereotype.Controlle */ import org.springframework.ui.ModelMap;
/*     */ imspringframework.web.bind.annotation.RequestMapping;
/*       */ @Controller
/*     */ public class DynamicPageA */ {
/*     */   public static final String TPL_INDEX = "tpl.index";
/* private static final String BRAND = "tpl.brand";
/*     */  static final String BRAND_DETAIL = "tpl.brandDetai  */
/*     */   @Autowired
/*     */   private CategoryMng categoryMng;/
/*     *wired
/*     */   privctMng productMng;
/*     */
/*     */ired
/*     ate ShopChannelMng shopChannelMng;
/*     */ 
/*     */   @Autow   */   private ShopArticleMng shopArticleMng;
/*     */
/* @Autowired
/*     */   private BrandMng brandMng;
/*     */
/*     */   @
/*     */  StandardTypeMng standa;
/*     */ 
/*     */   @Autowired
/*     */e ProductSt productStandardMng;
/*/*     */   @Autowired
/*     */   private g exendedMn */
/*     */   @Reque(value={"/index.jhtml"}, method={org.springframeword.annotatioMethod.GET})
/*     */ String indexForWeblogic(HttpServletRequest request, model)
/* {
/*  80 */     return uest, model);
/*     */   }
/*     */ 
   @Requestalue={"/"}, method={orgamework.web.bind.annotation.RequestMethod.GET})
/*   blic StringtpServletRequest requesap model)
/*     */   {
/*  92 */     Website web = SiteUti(request);
     ShopFrontHelper.seta(request, model, web, 1);
/*  94 */     r.getTemplat, MessageResolver.getMessage(request, "tpl.index", new Object[0]));
/*     */   }
/*     */ 
/*     */   @RequestMapping/**/*.*"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     *c String excute(HttpServletRequest request, HttpServletResponse, ModelMa/*     */   */     String url = request.getRequestURL().toString();
/* 103 */     Global global = SiteUtils.getWeb(requeobal();
/* 104 */     model.addAttribute("global", global);
/* 105 */     URRLInfo info = URLHelper.getURLInfo(url, request.getContextPath());
/* 106 */     String queryString = request.getQueryString();
/* 107 */     Website web = SiteUtils.getWeb(request);
/* 108 */     ShopFrontHelper.setDynamicPageData(request, model, wenfo.getUrlPrefo.getUrlSuffo.getPageNo());
/* 109 */     String[] paths = info.getPaths();
/* 110 */     String[] params = info.getParams();
     int pageNo = info.getPageNo();
/* 112 */     int len = paths.length;
/* 113 */     if (len == 1)
/*   {
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
/* public String channel(String path, String[] params, int pageNo, String queryString, String url, Website web, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*   Category category = this.categoryMng.getByPath(web.getId(), path);
/* 138 */     if (category != null) {
/* 139 */       Liss = this.exendedMn(category.getType().getId());
/* 140 */       Map map = new HashMap();
/* 141 */       Mapew HashMap();
/* 142 */       int num = exendeds.size();
/* 143 */       for (int i = 0; i < num; i++) {
/* 144 */         mExended)exendeds.get(i)).getId().toString(), ((Exended)exendeds.get(i)).getIt* 145 */         m(Exended)exendeds.get(i)).getId().toString(), exendeds.get(i));
/*     */       }
/*     model.add("brandId",Id(request));
/* 148 */       model.addAttribute("map", map);
/* 149 */       model.addAttribute("map1", map1);
/* 150 */       model.addAttribute("fields", getNames(request));
/* 151 */       motribute("zhis", getValues(request));
/* 152 */       model.addAttribute("category", category);
/* 153 */       model.addAttribute("pageSize", getpageSize(request));
/* 154 */       model.addAttribute("names", getName(request));
/* 155 */       model.addAttribute("values", getValue(request));
/* 156 */       model.addAttribute("isShow", getIsShow(request));
/* 157 */       model.addAttribute("orderBy", getOrderBy(request));
/* 158 */       return category.getTplChannelRel();
/*     */     }
/* 160 */     ShopChannel channel = this.shopChannelMng.getByPath(web.getId(), path);
/* 161 */     if != null) {
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
/* 181     String[] arr = (String[])null;
/* 182 */           arr = product.getProductFashion().getNature().split("_");
/* 183 */           model.addAttribute("arr", arr);
/*     */         }
/* 185 */         List psList = this.productStandardMng.findBd(id);
/* 186 */List standardTypeList = this.standardTypeMng.getList(category.getId());
/*     8 */         mttribute("speList", standardTypeList);
/* 189 */         model.addAttribute("psList", psList);
/* 190 */         model.addAttribute("category", category);
/* 191 */         model.addAttribute("product", product);
/* 192   return category.getTplContentRel();
/*     */       }
/* 194 */       return FrontHelper.pageNotFound(web, model, request);
/*     */     }
/*     */ 
/* 197 */     ShopArticle article = this.shopArticleMng.findById(id);
/* 198 */     if (article != null) {
/* 199 */       ShopChannel channel = article.getChannel();
/* 200 */       model.addAttribute("article", article);
/* 201 */       model.addAttribute("channel", channel);
/* 202 */       return channel.getTplContentRel()*/     }
/* 204 */     return FrontHelper.pageNotFound(web, model, request);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/brand.jspx"}, method={org.springframework.web.bition.RequestMethod.GET})
/*     */   public String brand(Long id, HttpServletRequest request, ModelMap model)
/*     */   {
/* 214 */     Website web = SiteUtils.getWeb(request);
/* 215 */     WebErrors errors = validateBrand(id, request);
/* 216 */     if (errors.hasErrors())
/* 217 */       return FrontHelper.shrrors, web, model, request);
/*     */     String tpl;
/*     */     String tpl;
/* 219  (id != null) {
       model.addAttribute("brand", this.brandMng.findById(id));
/* 221 */       tpl = MessageResolver.getMessage(request, "tpl.brandDetail", new Object[0]);
/*     */     } else {
/* 223 */       tpl = MessageResolver.getMessage(request, "tpl.brand", new Object[0]);
/*     */     }
/* 225 */     ShopFrontHelper.setCommonData(request, model;
/* 226 */     return web.getTplSys("shop", tpl);
/*     */   }
/*     */ 
/*     *c Integer getBtpServletReuest) {
/* 230 */     String brandId = RequestUtils.getQueryParam(request, "brandId");
/* 231 */     Integer id = null;/     if (!StringUtils.isBlank(brandId)) {
/* 233 */       id = Integer.valueOf(Integnt(brandId));
/*     */     }
/* 235 */     return id;
/*     */   }
/*     */ 
/*     */   public Integer getpageSize(HttpServletRequest request) {
/* 239 */     String pageSize = RequestUtils.getQueryParam(request, "pageSize");
/* 240 */    pagesize = null;
/* 241 */!StringUtils.isBlank(pageSize)) {
/* 242 */       pagesize = Integer.valueOf(Integer.parseInt(pageSize));
/*     */     }
/* 244 */     if (pagesize == null) {
/* 245 */       pagesize = Integer.valueOf(12);
/*     */     }
     return pagesize;
/*     */   }
/*     */ 
/*     */   public Integer getIsShow(HttpServletRequest request1 */     String isShow = RequestUtils.getQueryParam(request, "isShow");
/* 252 */     Integer isshow = null;
/* 253 */     if (!Stringlank(isShow)) */       isteger.valueOf(Integer.parseInt(isShow));
/*     */     }
/* 256 */     if (isshow == null) {
/* 257 */       isshow = Integer.valueOf(0);
/*     */     }
/* 259 */     return isshow;
/*     */   }
/*     */ 
/*     */   public Integer getOrderBy(HttpServletRequest request) {
/* 263 */     String= RequestUtils.getQueryParam(request, "or/* 264 */     rderby = nu5 */     if (!StringUtils.isBlank(orderBy)) {
/* 266 */       orderby = Integer.valueOf(Integer.parseInt(orderBy));
/*     */     }
/* 268 */     if (orderby == null) {
/* 269 */       orderby = Integer.valueOf(0);
/*     */     }
/* 271 */     return orderby;
/*     */   }
/*     */ 
/*     */   public String[](HttpServletRequest request) {
/* 275 */     Map attr = RequestUtils.getRequestMap(request, "exended_" */     List li = new ArrayList((Collection)att));
/* 277 */ g name = ""*/     for (int i = 0; i < li.size(); i++) {
/* 279 */       if (i + 1 == li.size())
/* 280 */         name = name + (String)li.get(i);
/*     */       else {
/* 282 */         name = name + (String)li.get(i) + ",";
/*     */       }
/*     */     }
/* 285 */     String[] names = StringUtils.split();
/* 286 */     return names;
/*     */   }
/*     */ 
/*     */   public String[] getValues(Httequest request) {
/* 290 */     Map attr = Res.getRequestMa, "exended_1 */     List li = new ArrayList((Collection)attr.keySet());
/* 292 */     String value = "";
/* 293 */     for (int i = 0; i < li.size(); i++) {
/* 294 */       if (i + 1 == li.size()) {
/* 295 */         if (StringUtils.isBlank((String)attr.get(li.get(i))))
/* 296 */           value = value + "0";
/*       else {
/* 298 */           value = value + (String)attr.get(li.get(i));
/*     */         }
/*     }
/* 301 */       else if (StringUtils.isBing)attr.get(l))
/* 302 * value = value + "0,";
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
/* 314 */     Map attr = RequgetRequestMap(request, "exended_");
/* 315 */     List li = new ArrayList((Coller.keySet());
/* 31String name = "";
/* 317 */     for (int i = 0; i < li.size(); i++) {
/* 318 */       if (i + 1 == li.siz19 */         me + (Strini);
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
/* 332 */     for (int i = 0;ize(); i++) {
/* 333 */       if (i + 1 == li.size()) {
/* 334 */         if (StringUtils.String)attr.get(li.g/* 335 */           value = value + "0";
/*     */         else {
/* 337 */           value = value + (String)attr.get(li.get(i));
/*       }
/*     */       }
/* 340 */       else if (StringUtils.isBlank((String)attr.get(li.
/* 341 */        value + "0,";
/*     else {
/* 343 */         value = value + (String)attr.get(li.get(i)) + ",";
/*     */       }
/*   }
/*     */ 
     return*     */   }
/*     */ 
/*     */   private WebErrors validateg id, HttpServletRequest request) {
/* 351 */     WebErrors errors = WebErrors.create(request);
/* 352 */     if (id != null) {
/* 353 */       Brand brand = this.brandMng.findById(id);
/* 354 */       if (errors.ifNotExist(brand, Brand.class, id)) {
/* 355 */         return errors;
/*     */       }
/*     */     }
/* 358 */   errors;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.DynamicPageAct
 * JD-Core Version:    0.6.2
 */