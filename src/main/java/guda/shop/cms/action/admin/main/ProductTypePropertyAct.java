 package guda.shop.cms.action.admin.main;
/*     */*/ import gcms.entity.ProductType;
/*     */ import guda.shop.y.ProductTypeProperty;
/*     */ import guda.shop.cms.managtTypeMng;
/*     */ import guda.shop.cms.manager.ProducertyMng;
/*     */ import java.util.ArrayList;
/*     */ importl.List;
/*     */ import javax.servletpServletRequest;
/*     */ importhe.commons.lang.StringUtils;
/*     */ import org.slf4j.*     */ import org.slf4j.LoggerFactory;
/*     */ imppringframework.beans.factory.annotawired;
/*     */ import org.springframeworype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*      org.springframework.web.bind.annotation.RequestMapping;
/* *     */ @Controller
/*     */ public class ProducertyAct
/*     */ {
/*  26 */   private static final Logger log = LoggerFtLogger(ProropertyAct.class);
/* *     */   @Autowired
/*     */   private ProdopertyMng manager;
/*     */
/*     */   @Autowired
/*     */   private ProductTypeMng productTypeMng;
/*     */*/   @Reque({"/typeProperty/v_list     */   public String list(Long typeId, Boolean is HttpServlerequest, ModelMap modelctType pType = this.productTypeMng.findById(typeId)*/     List list = this.manager.getList(typeId, isCategory);
/*  33 del.addAttribute("list", list);
/*  34 */     model.addAttribute("typeId", typeId);
/*  35 */     model.addAttribute("isCategory", isCategory);
/*  36 */     model.addAttribute("fieldList", getFieldList(list));
/*  37 */     model.addAttribute("pType", pType);
/*  38 */     if (isCategory.booleanValue()) {
/*  39 */       return "typeProperty/list_category";
/*     */     }
/*  41 */     return "typeProperty/list_product";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/typeProperty/v_add.do"})
/*     */   public String add(Long typeId, Boolean isCategory, HttpServletRequest request, ModelMap model*/   {
/*  48 */     ProductTypeProperty property = this.manager.fypeId);
/*  49odel.addAttroperty", property);
/*  50 */     model.addAttribute("typeId);
     model.addAttribute("isCategory", isCategory);
/*  52 */     return "typeProperty/add"*/   }
/*     */ 
/*     */   @RequestMapping({"/typeProperty/v_edit.do"})
/*     */   public String delete(Long id, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  58 */     ProductTypeProperty property = this.manager.findById(id);
/*  59 */     model.addAttribute("prproperty);
/*   model.add("typeId", property.getPropertyType().getId());
/*  61 */l.addAttribute("isCategory", property.getCategory());
/*  62 */     return "typeProperty/edit";
/*    *     */
/*     */   @RequestMapping({"/typeProperty/o_save_list.do"})
/*     */   public String saveList(Long typeId, Boolean isCategory, String[] fields, String[] propertyNames, Integer[] dataTypes, Integer[] sorts, Boolean[] singles, HttpServletRequest request, ModelMap model)
/*     */   {
/*  70 */     ProductType pType = this.peMng.findById(/*  71 */  temList = getItems(pType, isCategory.booleanValue(), fields, pmes,
/*  72 */       dataTypes, sorts, singles);
/*  73 */     this.manager.saveList(itemList);
/*  74 */     log.info("save CmsModelItem count={}", Integer.valueOf(itemList.size()));
/*  75 */     model.addAttypeId", typeId);
/*  76 */     model.addAttribute("isCategory", isCategory);
/*  77 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/typeProperty/o_save.do"})
/*     */   public String save(ProductTypeProperty bean, Long typeId, HttpServletRequest request, ModelMap model)
/*     */   {
/*  83 */     ProductType type = new ProductType();
/*  84 */     type.setId(typeId);
/*  85 */     bean.setPropertyType(type);
/*  86 */     bean.setSingle(Boolean.valueOf(true));
/*  8bean.setCustomvalueOf(tru88 */     this.manager.save(bean);
/*  89 */     model.ade("typeId", typeId);
/*  90 */     model.addAttribute("isCategory", bean.getCategory());
/*  91 */     return "redist.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/typeProperty/o_update.do"})
/*     */   public String update(ProductTypeProperty bean, Long typeId, boolean category, HttpServletRequest request, ModelMap model)
/*     */   {
/*  97 */     this.manager.update(bean);
/*  98 */     model.addAttribute("typeId", typeId);
/*  99 */     model.addAttribute("isCategory", Boolean.valueOf(category));
/* 100 */     return "redirect:v_list.do"*/   }
/*       */   @Reqng({"/typeProperty/o_priority.do"})
/*     */   public Strity(Long[] wids, Integer[] sort, String[] propertyName, Boolean[] single, Long typeId, Boolean isCategory, HttpServletRequest request, model)
/*     */   {
/* 107 */     if ((wids != null) && (wids.length > 0)) {
/* 108 */       this.manager.updatePriority(wids, sort, propertyName, single);
/*     */     }
/* 110 */     model.addAttribute("message", "global.;
/* 111 */   list(typeIdory, request, model);
/*     */   }
/*     */ 
/*     */   @Rping({"/typeProperty/o_delete.do"})
/*     */   public String delete(Long[] ids, Integer typeId, Boolean isCategory, HttpServletRequest request, ModelMap model)
/*     */   {
/* 117 oductTypeProperty[] beans = this.manager.deleteByIds(ids);
/* 118 */     for (ProductTypeProperty bean : beans) {
/* 119 */       log.info("delete Cms id={}", bean.getId());
/*     */     }
/* 121 */     model.addAttribute("typeId", typeId);
/* 122 */     model.addAttribute("isCategory", isC
/* 123 */    redirect:v_
/*     */   }
/*     */ 
/*     */   private List<String> ist(List<ProductTypeProperty> items) {
/* 127 */     List list = new ArrayList(items.size());
/* 128 */     for (ProductTypePtem : items) {
/* 129 */       list.add(item.getField());
/*     */     }
/* 131 */     return list;
/*     */   }
/*     */ 
/*     */   private List<ProductTypeProperty> getItems(ProductType model, boolean isCatring[] fields, String[] propertyNames, Integer[] dataTypes, Integer[] sorts, Boolean[] singles)
/*     */   {
/* 137 */     List list = new ArrayList();
/*     */
/* 139 t i = 0; for ( fields.lenlen; i++) {
/* 140 */       if (!StringUtils.isBlank(fields[i])) {
/* 141 */         ProductTypeProperty item = new ProductTypeProperty();
/* 142 */         item.setCustom(Boolean.valueOf(false));
/* 143 */         item.setPropertyTyp
/* 144 */         item.setCategory(BooleanisCategory));

/* 146 */item.setField(fields[i]);
/* 147 */         item.setPropertyName(propertyNames[i]);
/* 148 */         item.setSort(sorts[i]);
/* 149 */         item.setDataType(dataTypes[i]);
/* 150 */       tSingle(singles[i]);
/* 151 */         list.add(item);
/*     }
/*     */     }
/* 154 */     return list;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.admin.main.ProductTypePropertyAct
 * JD-Core Version:    0.6.2
 */