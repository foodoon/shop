 package guda.shop.cms.action.directive.abs;
/*     */*/ import gcommon.web.freemarker.DirectiveUtils;
/*     */ import guda.shop.b.freemarker.MustNumberException;
/*     */ import guda.shop.common.weker.MustStringException;
/*     */ import guda.shop.common.web.freemarsRequiredException;
/*     */ import guda.shop.core.entity.Website;
/*    t guda.shop.core.manager.WebsiteMng;
/*     */ iemarker.core.Environment;
/*     */ import freemarkee.AdapterTemplateModel;
/*     */ import freemplate.TemplateDirectiveBody;
/*     */ import freemarker.templateDirectiveModel;
/*     */ import freemarker.template.Tception;
/*     */ import freemarker.template.TemplateHashMod  */ import freemarker.template.TemplateModel;
/*     */reemarker.template.TemplateModelException;
/*     */ imparker.template.TemplateNumberModel;
/*     */ importer.template.TemplateScalarModel;
/*     */ import java.io.IOE
/*     */ import java.util.Map;
/*     */ import org.apacs.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/* port org.slf4j.LoggerFactory;
/*     *org.springframework.web.servlet.equestContext;
/*     */ 
/*     */ public abstract clrective
/*     */   implements TemptiveModel
/*     */ {
/*  22 */   protecteogger log = LoggerFactory.getLogger(getClass());
/*     */   public statitring LOCATcation";
/*     */   public static final StriEFIX = "urlPrefix";
/*     */   public static ing URL_SUFFIX = "urlSuffix";
/*     */   public static final String PAGE_NO = "pageNo";
/  public static final String ROOT = "root";
/*     */   publicinal String WEB = "web";
/*     */   public static final String BN = "baseDomain";
/*     */   public static final String LOGIN_URnUrl";
/*     */   public static final String CONFIG = "con    */   public static final String MEMBER = "member";/   public static final String GROUP = "group";
/*  ublic static final String PARAM_WEB_ID = "webId";
/*     */   publifinal String PARAM_TPL = "tpl";
/*     */   public static finalARAM_TPL_SUB = "tplSub";
/*     */   public static final SAM_COUNT = "count";
/*     */   public static final int MA 200;
/*     */   public static final boolean PARAM_TPL_se;
/*     */   public static final String OUT_LIST = "tag_list */   public static final String OUT_PAGINATION = "tag_pag
/*     */   public static final String PARAM_PARENT_ID = "parent   */
/*     */   protected void renderBody(Environment env, irectiveBody body)
/*     */     throws TemplateExcepxception
/*     */   {
/*  50 */     body.render(env.getOut());/   }
/*     */ 
/*     */   protected RequestContext getContenment env) throws TemplateException
/*     */   {
/*  55 */     TemplateMoatemodel = env.getGlobalVariable("springMacroRequestContext");
/*  56f ((templatstanceof AdapterTemplateModel)) {
/*  57 */       return (RequestContext)((AdapterTdel)templatemodel).getAdaptedObject(RequestContext.c     */     }
/*  59 */     throw new TemplateModelExcequestContext 'roRequestCot found in DataModel.");
/*     */   }
/*     */ 
/*     */   protected void includeTpl(Sttring s1, Website website, Map params, Environment env)
/*     */     throws IOException, TemplateException
/*     */   {
/*  66 */     String s2 = website.getTplTag(s, s1, getSubTpl(params));
/*  67 */     env.include(s2, "UTF-8", true);
/*     */   }
/*     */
/*     */   protected int getPagonment env) throws TemplateException
/*     */   {
/*  72 */     TemplateModel templatemodel = env.getGlobalVariable("pageNo");
/*  73f ((templatemonceof TemplModel)) {
/*  74 */       return ((TemplateNumberModel)templatemodel).getAsNumber().intValue();
/*     */  76 */     throw new TemplateModelException("Reques'pageNo' not found in DataModel.");
/*     */   }
/*     */ 
/*     */   public static Map getMap(String name, Map<String, Templaterams)
/*     *ows Templatn
/*     */   {
/*  83 */     TemplateModel model = (TemplateModel)params.get(  84 */     if (model == null) {
/*  85 */       return null;
/*     */     }
/*  87 */     if ((model instanceof TemplateHashModel)) {
/*  88 */       TemplateHashModel s = (TemplateHashModel)model;
/*  89 */       return (Map)s;
/*     */     */     return params;
/*     */   }
/*     */ 
/*     */   protected Website getWeb(Environment env, Map params, Wewebsitemng)
/*   throws Tception
/*     */   {
/*  97 */     Long long1 = getWebId(params);
/*  98 */     if (null) {
/*  99 */       Website websiteemng.findById(long1);
/* 100 */       if (website == null) {
/* 101 */         throw new TemplateModelException("Website id=" + long1 + " not exist   */       }
/* 103 */       return website;
/*     */     }
/*     */ 
/* 106 */     TemplateModel templatemodel = env.getGlobalVariable("web");
/* 107 */     if ((teml instanceof AdapterTemplateModel)) {
/* 108 return (WebsiterTemplateMlatemodel).getAdaptedObject(Website.class);
/*     */     }
/* 110 */     throw new Templxception("Website 'web' not found in Da;
/*     */   }
/*     */ 
/*     */   protected Long getWebId(Map params)
/*     */     throws TemplateException
/*     */   {
/* 116 */     TemplateModel templatemodel = (TemplateModel)params.get("webId");
/* 117 */     if (templatemodel == null) {
/* 118 */       return null;
/*
/* 120 */     if ((templatemodel instanceof TemplModel)) {
/* 121 return Long.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().longValue());
/*     */     }
/* 123 */     throw new TemplateModelException("The 'webId' parameter must be a number.");
/*     */   }
/*     */ 
/*     */   protected int getCount(Map *     */     throws TemplateException
/*     */   {
/* 129 */     Integer integer = getInt("count", para30 */     if (= null) {
/      throw new ParamsRequiredException("count")*/     }
/* 133 */     if (integer.intV200) {
/* 134 */       integer = Integer.valueOf(1);
/*     */     }
/* 136 */     else if (integer.intValue() < 1) {
/* 137 */       integer = Integer.valueOf(200);
     }
/*     */ 
/* 140 */     return integer.intValue();
/*     */   }
/*     */ 
/*     */   protected boolean isInvokeTpl(Map params) throws TemplateException
/*     */   {
/* 145 *plateModel templatemodel = (TemplateModel)params.get("tpl");
/* 146 */     if (templatemodel == null) {
/*     return fal  */     }
     if ((templatemodel instanceof TemplateScal {
/* 150 */       return DirectiveUtilean((TemplateScalarModel)templatemodel);
/*     */     }
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   protected String getSubTpl(Map params)
/*     rows TemplateException
/*     */   {
/* 158 */     TemplateModel templatemodel = (TemplateModel)params.get("
/* 159 */     if (templatemodel == null) {
/* 160 */       return null;
/*     */     }
/* 162 */     if ((templinstanceof TemplModel)) {
/* 163 */       return ((TemplateScalarModtemodel).getAs
/*     */ 165 */     throw new MustStringException("tplSub");
/*     */   }
/*     */ 
/* protected String getString(String s, Map params)
/*     */     throws TemplateException
/*     */   {
/* 171 */     TemplateModel templatemodel = (TemplateModel)par);
/* 172 */     if (templatemodel == null) {
/* 173 */       return null;
/*     */     }
/* 175 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 176 */  rn ((TemplateScalarModel)templatemodel).getA;
/*     */   8 */     thustStringException(s);
/*     */   }
/*     */
/* protected Long getLong(String s, Map pa    */     throws TemplateException
/*     */   {
/* 184 */     TemplateModel templatemodel = (TemplateModel)params.get(s);
/* 185 */     if (templatemodel == null) {
       return null;
/*     */     }
/* 188 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 189 */       String s1 = ((TemplateScalarModel)templ.getAsString();
/* 190 */       if (StringUtils.isBlank(s1))
/* 191 */ eturn null;
/*     try
/*     {
/* 194 */         return Long.valueOf(Long.parseLong(s   */       } catch (NumberFormatExcept/* 196 */         throw new MustNumberException(s);
/*     */       }
/*     */     }
/* 199 */     if ((templatemodel instanceof TemplateNumberModel)) {
/* 200 return Long.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().longValue());
/*     */     }
/* 202 */     throw new MustNumberException(s);
/*     */   */
/*     */   protected Integer getInt(String s, Map params*/     throws xception
/* {
/* 208 */     TemplateModel templatemodel = (TemplateMms.get(s);
/* 209 */     if (templatemoll) {
/* 210 */       return null;
/*     */     }
/* 212 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 213 */       String s1 = ((TemplateScatemplatemodel).getAsString();
/* 214 */       if (StringUtils.isBlank(s1))
/* 215 */         return null;
/*     */       try
/*     */       {
/* 218 */         return Integer.valueOf(Integer.parseInt(s1));
/*     */       } catch (NumberFption e) {
/* 220 */throw new MustNumberException(s);
/*     */       }
/*     */     }
/* 223 */(templatemodel instanceof TemplateNumberModel)) {
/* 224 */       return Integer.valueOf(((TemplateNumbemplatemodel).getAsintValue());
/*     */     }
/* 226 */     throw new MustNumberException(s);
/*     */   }
/*     */ 
/*     */   protected Double getDouble(String s, Map params)
/*     */     throws Tception
/*     */   {
/* 232 */     TemplateModel templatemodel teModel)params/* 233 */  mplatemodel == null) {
/* 234 */       return null;
/*     /* 236 */     if ((templatemodel instanlateScalarModel)) {
/* 237 */       String s1 = ((TemplateScalarModel)templatemodel).getAsString();
/* 238 */       if (StringUtils.isBlank(s1))
/* 239 */       null;
/*     */       try
/*     */       {
/* 242 */         return Double.valueOf(Double.parseDouble(s1));
/*     */       } catch (NumberFormatException e) {
/* 244 */         throw new MustNumberException(s);
/*     */       }
/*     ** 247 */     if ((teel instanceof TemplateNumberModel)) {
/* 248 */       return Double.valueOf(((TemprModel)templatemodel).getAsNumber().intValue());
/*     */     }
/* 250 */     throw new MustNumberExcep/*     */   }
/*       */   protected Boolean getBool(String s, Map params)
/*     */     throws TemplateException
/*     */   {
/* 256 */     TemplateModel templatemodel = (TemplateModel)params.get(s);
/*   if (templatemodel == null) {
/* 258 */       return null;
/*   }
/* 260 */ templatemodceof TemplateScalarModel)) {
/* 261 */       String s1 = ((TelarModel)templatemodel).getAsString();
       if (StringUtils.isBlank(s1)) {
/* 263 */         return null;
/*     */       }
/* 265 */       return Boolean.valueOf(!s1.equals("0"));
/*     */     }

/* 268 */     if ((templatemodel instanceof TemplateNumberModel)) {
/* 269 */       return Boolean.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().intValue() != 0);
/*     */     }
/* 271 */     throw new MustNumberException(s)*/   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.abs.WebDirective
 * JD-Core Version:    0.6.2
 */