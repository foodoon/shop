 package guda.shop.cms.action.admin.main;

 import guda.shop.cms.entity.Category;
 import guda.shop.cms.entity.ProductType;
 import guda.shop.cms.entity.StandardType;
 import guda.shop.cms.manager.BrandMng;
 import guda.shop.cms.manager.CategoryMng;
 import guda.shop.cms.manager.ProductTypeMng;
 import guda.shop.cms.manager.ProductTypePropertyMng;
 import guda.shop.cms.manager.StandardTypeMng;
 import guda.shop.cms.web.SiteUtils;
 import guda.shop.common.web.RequestUtils;
 import guda.shop.common.web.ResponseUtils;
 import guda.shop.core.entity.Website;
 import guda.shop.core.web.WebErrors;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.ServletContext;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.lang.ArrayUtils;
 import org.apache.commons.lang.StringUtils;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.context.ServletContextAware;

 @Controller
 public class CategoryAct
   implements ServletContextAware
 {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(CategoryAct.class);

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

/*  47 */   @RequestMapping({"/category/v_left.do"})
   public String left() { return "category/left"; }


   @RequestMapping({"/category/v_tree.do"})
   public String tree(String root, HttpServletRequest request, HttpServletResponse response, ModelMap model)
   {
/*  53 */     Website web = SiteUtils.getWeb(request);
/*  54 */     log.debug("tree path={}", root);
     boolean isRoot;
     boolean isRoot;
/*  57 */     if ((StringUtils.isBlank(root)) || ("source".equals(root)))
/*  58 */       isRoot = true;
     else {
/*  60 */       isRoot = false;
     }
/*  62 */     model.addAttribute("isRoot", Boolean.valueOf(isRoot));
/*  63 */     WebErrors errors = validateTree(root, request);
/*  64 */     if (errors.hasErrors()) {
/*  65 */       log.error((String)errors.getErrors().get(0));
/*  66 */       ResponseUtils.renderJson(response, "[]");
/*  67 */       return null;
     }
     List list;
     List list;
/*  70 */     if (isRoot) {
/*  71 */       list = this.manager.getTopList(web.getId());
     } else {
/*  73 */       Long rootId = Long.valueOf(root);
/*  74 */       list = this.manager.getChildList(web.getId(), rootId);
     }
/*  76 */     model.addAttribute("list", list);
/*  77 */     response.setHeader("Cache-Control", "no-cache");
/*  78 */     response.setContentType("text/json;charset=UTF-8");
/*  79 */     return "category/tree";
   }

   @RequestMapping({"/category/v_list.do"})
   public String list(Long root, HttpServletRequest request, ModelMap model) {
/*  84 */     Website web = SiteUtils.getWeb(request);
     List list;
     List list;
/*  86 */     if (root == null)
/*  87 */       list = this.manager.getTopList(web.getId());
     else {
/*  89 */       list = this.manager.getChildList(web.getId(), root);
     }
/*  91 */     List typeList = this.productTypeMng.getList(web.getId());
/*  92 */     model.addAttribute("typeList", typeList);
/*  93 */     model.addAttribute("root", root);
/*  94 */     model.addAttribute("list", list);
/*  95 */     return "category/list";
   }

   @RequestMapping({"/category/v_add.do"})
   public String add(Long root, Long typeId, HttpServletRequest request, ModelMap model)
   {
/* 101 */     Website web = SiteUtils.getWeb(request);
/* 102 */     Category parent = null;

/* 105 */     ProductType type = this.productTypeMng.findById(typeId);

/* 107 */     List itemList = this.productTypePropertyMng.getList(typeId, true);
     List brandList;
     List brandList;
/* 108 */     if (root != null) {
/* 109 */       parent = this.manager.findById(root);
/* 110 */       model.addAttribute("parent", parent);
/* 111 */       model.addAttribute("root", root);
/* 112 */       brandList = new ArrayList(parent.getBrands());
     } else {
/* 114 */       brandList = this.brandMng.getList();
     }
/* 116 */     model.addAttribute("brandList", brandList);

/* 118 */     String ctgTplDirRel = type.getCtgTplDirRel();
/* 119 */     String realDir = this.servletContext.getRealPath(ctgTplDirRel);
/* 120 */     String relPath = ctgTplDirRel.substring(web.getTemplateRel().length());

/* 122 */     String txtTplDirRel = type.getTxtTplDirRel();
/* 123 */     String txtrealDir = this.servletContext.getRealPath(txtTplDirRel);
/* 124 */     String txtrelPath = txtTplDirRel.substring(web.getTemplateRel().length());

/* 126 */     String[] channelTpls = type.getChannelTpls(realDir, relPath);

/* 128 */     String[] contentTpls = type.getContentTpls(txtrealDir, txtrelPath);

/* 130 */     List parentList = this.manager.getListForParent(
/* 131 */       SiteUtils.getWebId(request), null);

/* 133 */     List standardTypeList = this.standardTypeMng.getList();
/* 134 */     model.addAttribute("standardTypeList", standardTypeList);
/* 135 */     model.addAttribute("channelTpls", channelTpls);
/* 136 */     model.addAttribute("contentTpls", contentTpls);
/* 137 */     model.addAttribute("parentList", parentList);
/* 138 */     model.addAttribute("type", type);
/* 139 */     model.addAttribute("itemList", itemList);
/* 140 */     return "category/add";
   }

   @RequestMapping({"/category/v_edit.do"})
   public String edit(Long id, Long root, HttpServletRequest request, ModelMap model) {
/* 145 */     WebErrors errors = validateEdit(id, request);
/* 146 */     if (errors.hasErrors()) {
/* 147 */       return errors.showErrorPage(model);
     }
/* 149 */     if (root != null) {
/* 150 */       model.addAttribute("root", root);
     }
/* 152 */     Website web = SiteUtils.getWeb(request);
/* 153 */     Category category = this.manager.findById(id);
/* 154 */     List parentList = this.manager.getListForParent(SiteUtils.getWebId(request), id);
/* 155 */     List typeList = this.productTypeMng.getList(web.getId());

/* 157 */     ProductType type = category.getType();

/* 159 */     List itemList = this.productTypePropertyMng.getList(type.getId(), true);
     List brandList;
     List brandList;
/* 161 */     if (category.getParent() != null)
/* 162 */       brandList = new ArrayList(category.getParent().getBrands());
     else {
/* 164 */       brandList = brandList = this.brandMng.getList();
     }
/* 166 */     model.addAttribute("brandList", brandList);

/* 168 */     String ctgTplDirRel = type.getCtgTplDirRel();
/* 169 */     String realDir = this.servletContext.getRealPath(ctgTplDirRel);
/* 170 */     String relPath = ctgTplDirRel.substring(web.getTemplateRel().length());

/* 172 */     String txtTplDirRel = type.getTxtTplDirRel();
/* 173 */     String txtrealDir = this.servletContext.getRealPath(txtTplDirRel);
/* 174 */     String txtrelPath = txtTplDirRel.substring(web.getTemplateRel().length());

/* 176 */     String[] channelTpls = type.getChannelTpls(realDir, relPath);

/* 178 */     String[] contentTpls = type.getContentTpls(txtrealDir, txtrelPath);
/* 179 */     String tpl = category.getTplChannel();
/* 180 */     if ((!StringUtils.isBlank(tpl)) && (!ArrayUtils.contains(channelTpls, tpl))) {
/* 181 */       channelTpls = (String[])ArrayUtils.add(channelTpls, 0, tpl);
     }
/* 183 */     tpl = category.getTplContent();
/* 184 */     if ((!StringUtils.isBlank(tpl)) && (!ArrayUtils.contains(contentTpls, tpl))) {
/* 185 */       contentTpls = (String[])ArrayUtils.add(contentTpls, 0, tpl);
     }
/* 187 */     List standardTypeList = this.standardTypeMng.getList();
/* 188 */     Long[] standardTypeIds = StandardType.fetchIds(category.getStandardType());
/* 189 */     model.addAttribute("standardTypeList", standardTypeList);
/* 190 */     model.addAttribute("channelTpls", channelTpls);
/* 191 */     model.addAttribute("contentTpls", contentTpls);
/* 192 */     model.addAttribute("parentList", parentList);
/* 193 */     model.addAttribute("typeList", typeList);
/* 194 */     model.addAttribute("category", category);
/* 195 */     model.addAttribute("standardTypeIds", standardTypeIds);
/* 196 */     model.addAttribute("itemList", itemList);
/* 197 */     return "category/edit";
   }

   @RequestMapping({"/category/o_save.do"})
   public String save(Category bean, Long root, Long typeId, Long[] brandIds, Long[] standardTypeIds, HttpServletRequest request, ModelMap model)
   {
/* 203 */     WebErrors errors = validateSave(bean, request);
/* 204 */     if (errors.hasErrors()) {
/* 205 */       return errors.showErrorPage(model);
     }
/* 207 */     bean.setAttr(RequestUtils.getRequestMap(request, "attr_"));
/* 208 */     bean = this.manager.save(bean, root, typeId, brandIds, standardTypeIds);
/* 209 */     log.info("save Category. id={}", bean.getId());
/* 210 */     model.addAttribute("root", root);
/* 211 */     return "redirect:v_list.do";
   }

   @RequestMapping({"/category/o_update.do"})
   public String update(Category bean, Long root, Long parentId, Long[] brandIds, Long[] standardTypeIds, Integer pageNo, HttpServletRequest request, ModelMap model)
   {
/* 217 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 218 */     if (errors.hasErrors()) {
/* 219 */       return errors.showErrorPage(model);
     }
/* 221 */     Map attr = RequestUtils.getRequestMap(request, "attr_");
/* 222 */     bean = this.manager.update(bean, parentId, null, brandIds, attr, standardTypeIds);
/* 223 */     log.info("update Category. id={}.", bean.getId());
/* 224 */     return list(root, request, model);
   }

   @RequestMapping({"/category/o_delete.do"})
   public String delete(Long[] ids, Long root, Integer pageNo, HttpServletRequest request, ModelMap model)
   {
/* 230 */     WebErrors errors = validateDelete(ids, request);
/* 231 */     if (errors.hasErrors()) {
/* 232 */       return errors.showErrorPage(model);
     }
/* 234 */     Category[] beans = this.manager.deleteByIds(ids);
/* 235 */     for (Category bean : beans) {
/* 236 */       log.info("delete Category. id={}", bean.getId());
     }
/* 238 */     return list(root, request, model);
   }

   @RequestMapping({"/category/v_checkPath.do"})
   public String checkPath(String path, HttpServletRequest request, HttpServletResponse response)
   {
/* 244 */     if ((StringUtils.isBlank(path)) || 
/* 245 */       (!this.manager.checkPath(SiteUtils.getWebId(request), path)))
/* 246 */       ResponseUtils.renderJson(response, "false");
     else {
/* 248 */       ResponseUtils.renderJson(response, "true");
     }
/* 250 */     return null;
   }

   @RequestMapping({"/category/o_priority.do"})
   public String priority(Long[] wids, Long root, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model)
   {
/* 256 */     WebErrors errors = validatePriority(wids, priority, request);
/* 257 */     if (errors.hasErrors()) {
/* 258 */       return errors.showErrorPage(model);
     }
/* 260 */     this.manager.updatePriority(wids, priority);
/* 261 */     model.addAttribute("message", "global.success");
/* 262 */     return list(root, request, model);
   }

   private WebErrors validateTree(String path, HttpServletRequest request) {
/* 266 */     WebErrors errors = WebErrors.create(request);

/* 270 */     return errors;
   }

   private WebErrors validateSave(Category bean, HttpServletRequest request) {
/* 274 */     WebErrors errors = WebErrors.create(request);
/* 275 */     bean.setWebsite(SiteUtils.getWeb(request));
/* 276 */     return errors;
   }

   private WebErrors validateEdit(Long id, HttpServletRequest request) {
/* 280 */     WebErrors errors = WebErrors.create(request);
/* 281 */     errors.ifNull(id, "id");
/* 282 */     vldExist(id, errors);
/* 283 */     return errors;
   }

   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 287 */     WebErrors errors = WebErrors.create(request);
/* 288 */     errors.ifNull(id, "id");
/* 289 */     vldExist(id, errors);
/* 290 */     return errors;
   }

   private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
/* 294 */     WebErrors errors = WebErrors.create(request);
/* 295 */     errors.ifEmpty(ids, "ids");
/* 296 */     for (Long id : ids) {
/* 297 */       vldExist(id, errors);
     }
/* 299 */     return errors;
   }

   private WebErrors validatePriority(Long[] wids, Integer[] priority, HttpServletRequest request)
   {
/* 304 */     WebErrors errors = WebErrors.create(request);
/* 305 */     if (errors.ifEmpty(wids, "wids")) {
/* 306 */       return errors;
     }
/* 308 */     if (errors.ifEmpty(priority, "priority")) {
/* 309 */       return errors;
     }
/* 311 */     if (wids.length != priority.length) {
/* 312 */       errors.addErrorString("wids length not equals priority length");
/* 313 */       return errors;
     }
/* 315 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 316 */       vldExist(wids[i], errors);
/* 317 */       if (priority[i] == null) {
/* 318 */         priority[i] = Integer.valueOf(0);
       }
     }
/* 321 */     return errors;
   }

   private void vldExist(Long id, WebErrors errors) {
/* 325 */     if (errors.hasErrors()) {
/* 326 */       return;
     }
/* 328 */     Category entity = this.manager.findById(id);
/* 329 */     errors.ifNotExist(entity, Category.class, id);
   }

   public void setServletContext(ServletContext servletContext)
   {
/* 345 */     this.servletContext = servletContext;
   }
 }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.CategoryAct
 * JD-Core Version:    0.6.2
 */