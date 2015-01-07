 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.PopularityGroup;
/*     */ import guda.shop.y.Product;
/*     */ import guda.shop.cms.managng;
/*     */ import guda.shop.cms.manager.PopulaMng;
/*     */ import guda.shop.cms.manager.ProductMng;
/* port guda.shop.cms.manager.ProductTypeMng;
/*     *guda.shop.common.page.Pagination;
/*     */ import gudamon.page.SimplePage;
/*     */ import guda.shop.comookieUtils;
/*     */ import guda.shop.common.web.Rils;
/*     */ import guda.shop.core.entity.Website*/ import guda.shop.core.web.SiteUtils;
/*     */ impshop.core.web.WebErrors;
/*     */ import java.u
/*     */ import javax.servlet.http.HttpServle
/*     */ import javax.servlet.http.HttpServle;
/*     */ import org.json.JSONE
/*     */ import org.json.JSONObject;
/*     */ import .Logger;
/*     */ import org.slf4j.LoggerFactory;
/*    t org.springframework.beans.factory.annotowired;
/*     */ import org.springfraereotype.Controller;
/*     */ imporingframework.ui.ModelMap;
/*     */ imporingframework.web.bind.annotation.RequestMapping;
/*     */
/*     */ @Co/*     */ public class PopularityGroupAct
/*     */ {
/*  36vate static final Logger log = LoggerFactory.getLolarityGroupAct.class);
/*     */
/*     */   @Autowired
/*     */   privMng brandMn */
/*     */   @Auto    */   private ProductTypeMng productTyp    */
/*     */   @Autowired
/*     */   private PopularityGroupMng manager;
/*     */
/*     */   @Autowi  */   privctMng productMng;
/*    40 */   @RequestMapping({"/popularityGst.do"})
/* public String list(IntNo, HttpServletRequest request, ModelMap model) { P paginationanager.getPage(SimplePageNo), CookieUtils.getPageSize(request));
/*  41odel.addAttagination", pagination)*/     return "popularityGroup/list";
/*   /*     */
/*     */   @RequestMapping({"/popularityGroup/v_search.do"}*/   public void update(Long typeId, Long brandId, String productName, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  50 */     List list = this.productMng.getList(typeId, brandId, productName);
     JSONObject json = new JSONObject();
/*     */   *  53 */      0; for (int.size(); i < j; i++)
/*  54 */         json.append(((Product)l)).getId(), ((Product)list.get(i)).getName());
/*     */     }
/*     */     catch (JSONException e) {
/*  57 */       e.printStackTrace();
/*     */   9 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */
/*     */   @RequestMapping({"/popularityGroup/v_add.do"})/   public String add(ModelMap model) {
/*  64 */     List typeList = this.productTypeMng.getList(Long.valueOf(1L));
/*  65 */     List brandList = this.brandMng.getAllList();
/*  66odel.addAttributist", brandList);
/*  67 */     model.addAttribute("typeList", typeList);
/*   return "popularityGroup/add";
/*     */   }
/*     */
/*     */   @RequestMappinlarityGroup/v_)
/*     */ String edit(Long id, HttpServletRequest request, ModelMap /*  73 */     WebErrors errors = validateEdit(id, request);
/*  74 */     if (errors.hasErrors()) {
/*  75 */       return errors.showErrorPage(model);
/*     */     }
/*  77 */     List typeList = this.productTypeMng.getList(Long.valueOf(1L));
/*  78 */     List brandList = this.brandMng.getAllList();
/*  79 */     model.addAttribute("branrandList);
/*   model.add("typeList", typeList);
/*  81 */     model.addAttribute("poroup", this.manager.findById(id));
/*  82 */     return "popularityGroup/edit";
/*     */   }
/*     */
/*     */   @RequestMapping({"/popularityGroup/o_save.do"})
/*     */   public String save(PopularityGroup bean, String rightlist, HtRequest request, ModelMap model) {
/*  87 */     WebErrors errors = validateSave(bean, request);
/*  88 */     if (errors.hasErrors()) {
/*  89 */       return errors.showErrorPage(model);
/*     */     }
/*  91 */     bean = this.manager.save(bean);
/*  92 */     this.manager.addProduct(bean, getProductIds(rightlist));
/*  93 */     log.info("save PopularityGroup id={}", bean.getId());
/    return "relist.do";
/  }
/*     */
/*     */   @RequestMapping({"/popularityGroue.do"})
/*     */   public String update(PopularityGroup bean, String rightlist, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 100 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 101 */     if (errors.hasErrors()) {
/* 1   return errors.showErrorPage(model);
/*     */     }
/* 104 */     bean = this.manager.update(bean);
/* 105 */     this.manager.updateProduct(bean, getProductIds(rightlist));
/* 106 */     log.info("update PopularityGroup id={}.", bean.getId(7 */     returgeNo, reque);
/*     */   }
/*     */
/*     */   @RequestMapping({"/popoup/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */13 */     WebErrors errors = validateDelete(ids, request);
/* 114 */     if (errors.hasErrors()) {
/* 115 */       return errors.showErrorPage(model);
/*     */     }
/* 117 */  rityGroup[] beans = this.manager.deleteByIds(ids);
/* 118 */     for (PopularityGroup bean : beans) {
/* 119 */       log.info("delete PopularityGroup id={}", bean.getId());
/*     */     }
/* 121 */     return list(pageNo, request, model);
/*     */   }
/*       */   publigetProductI rightlist)
/*     */   {
/* 126 */     String[] arr = rightli",");
/* 127 */     Long[] productIds = new Long[arr.length];
/* 128 */     int i = 0; for (int j = arr.l< j; i++) {
/* 129 */       if (!arr[i].equals("")) {
/* 130 */         productIds[i] = Long.valueOf(Long.parseLong(arr[i]));
/*     */       }
/*     */     }
/* 133 */rn productIds;
/*     */   }
/*     */
/*     */   private WebErrors validateSave(PopularityGroup bean, HttpServletRequest request) {
/* 137 */     WebErrors errors = WebErrors.create(request);
/* 138 */     rors;
/*     */   }
/*     */
/*     */   private WebErrors validatg id, HttpServt request) */     WebErrors errors = WebErrors.create(request);
/* 14Website web = SiteUtils.getWeb(request);
/* 144 */     if (vldExist(id, web.getId(), errors)) {
/* 145 */       return errors;
/*     */     }
/* 147 */     return errors;
/*     */   }
/*     */
/*     */   private WebErrors validateUpdate(Long id, HttpServletRequest request) {
/* 151 */     WebEors = WebErrors.crest);
/* 152 */     Website web = SiteUtils.getWe);
/* 153 */  dExist(id, (), errors)) {
/* 154 */       return errors;
/*     */     }
/* 156 */     return errors;
/*     */   }
/*     */
/*     */   private WebErrors validateDelete(Long[] ids, HttpServletequest) {
/* 1 WebErrors WebErrors.create(request);
/* 161 */     Website web = SiteUtils.getWeb(request);
/* 162 */     errors.ifEmpty(ids, "ids");
/* 163 */     for (Long id : ids) {
/* 164 */       vldExist(id, web.getId(), errors);
/*     */     }
/* 166 */     return errors;
/*     */   }
/*     */
/* private boolean vldExist(Long id, Long webIdrs errors) {
/    if (errl(id, "id")) {
/* 171 */       return true;
/*     */     }
/* 173 */     PopularityGroup entity = this.manager.findById(id);
/* 174 */     if (errors.ifNotExist(entity, PopularityGroup.class, id)) {
/* 175 */       return true;
/*     */     }
/* 177 */     return false;
/*     */   */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.PopularityGroupAct
 * JD-Core Version:    0.6.2
 */