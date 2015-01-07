 package guda.shop.cms.lucene;
/*     */*/ import gcms.action.directive.abs.WebDirective;
/*     */ import com.jspgoueb.freemarker.DirectiveUtils;
/*     */ import freemarker.core.Env
/*     */ import freemarker.template.TemplateBody;
/*     */ import freemarker.template.TemplateDirective     */ import freemarker.template.TemplateException;
/*      freemarker.template.TemplateModel;
/*     */ import javception;
/*     */ import java.util.Date;
/*     */ va.util.Map;
/*     */ 
/*     */ publct class LuceneDirectiveAbstract ebDirective
/*     */   implementeDirective    */ {
/*     */   public static final String PARAM_QUERY = "q";
/*     */ static final String PARAM_WEBSITE_ID = "websit    */   pubc final String PARAM_PTYPE_ID = "ptypeId";
/*     */   pubc final String PARAM_START_DATE = "startDate";
/*     */   public statitring PARAM_END_DATE = "endDate";
/*     */   public static final S_ID = "ctgId";
/*     */   public static final String PARAM_TAG_ID = "t     */   public static final String PARAM_RECOMMEND = "recommend";/   public static final String PARAM_SPECIAL = "special";/
/*     */   protected String getQuery(Map<String, TemplateMoms)
/*     */     throws TemplateException
/*     */   {
/*  62 */    irectiveUtils.getString("q", params);
/*     */   }
/*     */ 
/* protected LbSiteId(Map<String, TemplateModel> params) throws TemplateException
/*   /*  67 */     return DirectiveUtils.getsiteId", params);
/*     */   }
/*     */ 
/*     */   protected Long getPp<String, Temp> params) tplateException
/*     */   {
/*  72 */     return DirectiveUtils.getLong("ptypeId", params);
/*          */
/*     */   protected Date getStartDate(Map<String, TemplateModel> paras TemplateExce    */   {
     return DirectiveUtils.getDate("startDate", params);
/*     */   }
/*     */ 
/*     */   prote getEndDate(Map<String, TemplateModel> params) throws TemplateException
/*   /*  82 */     rectiveUtil("endDate", params);
/*     */   }
/*     */ 
/*     */   protected Long getCtgId(Map<String, Templaparams) throws TemplateException
/*     */   {
/*  87 */     return getLong("ctgms);
/*     */   */
/*  rotected Long getTagId(Map<String, TemplateModel> params)
/*     */     throws TemplateException
/  {
/*  95 */     return getLong("tagId", params);
/*     */   }
/*     */ 
/* protected Boocommend(MapTemplateModel> params) throws TemplateException
/*     */   {
/* 100 */     return getBool("recoarams);
/*     */   }
/*     */
/*     */   protected Booleaal(Map<String,Model> paras TemplateException
/*     */   {
/* 105 */     return getBool("special);
/*     */   }
/*     */ 
/*     */  d Boolean isHostSale(Map<String, TemplateModel> params) throweException
/* {
/* 110 */rn getBool("hostSale", params);
/*     */   }
/*     */ 
/*     */   protected Boolean isNewProduct(Ma TemplateModel> params) throws TemplateException
/*     */   {
/*   return getBroduct", pa     */   }
/*     */ 
/*     */   protected void renderBody(Environment env, TemplateModel[] loopVaateDirectiveBody body) throws TemplateException, IOException
/* {
/* 120 */  ender(env.g
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.lucene.LuceneDirectiveAbstract
 * JD-Core Version:    0.6.2
 */