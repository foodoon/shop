/*     */ package guda.shop.action.directive.abs;
/*     */ 
/*     */ import guda.shop.common.web.freemarker.DirectiveUtils;
/*     */ import guda.shop.common.web.freemarker.MustNumberException;
/*     */ import guda.shop.common.web.freemarker.MustStringException;
/*     */ import guda.shop.common.web.freemarker.ParamsRequiredException;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.manager.WebsiteMng;
/*     */ import freemarker.core.Environment;
/*     */ import freemarker.template.AdapterTemplateModel;
/*     */ import freemarker.template.TemplateDirectiveBody;
/*     */ import freemarker.template.TemplateDirectiveModel;
/*     */ import freemarker.template.TemplateException;
/*     */ import freemarker.template.TemplateHashModel;
/*     */ import freemarker.template.TemplateModel;
/*     */ import freemarker.template.TemplateModelException;
/*     */ import freemarker.template.TemplateNumberModel;
/*     */ import freemarker.template.TemplateScalarModel;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.web.servlet.support.RequestContext;
/*     */ 
/*     */ public abstract class WebDirective
/*     */   implements TemplateDirectiveModel
/*     */ {
/*  22 */   protected final Logger log = LoggerFactory.getLogger(getClass());
/*     */   public static final String LOCATION = "location";
/*     */   public static final String URL_PREFIX = "urlPrefix";
/*     */   public static final String URL_SUFFIX = "urlSuffix";
/*     */   public static final String PAGE_NO = "pageNo";
/*     */   public static final String ROOT = "root";
/*     */   public static final String WEB = "web";
/*     */   public static final String BASE_DOMAIN = "baseDomain";
/*     */   public static final String LOGIN_URL = "loginUrl";
/*     */   public static final String CONFIG = "config";
/*     */   public static final String MEMBER = "member";
/*     */   public static final String GROUP = "group";
/*     */   public static final String PARAM_WEB_ID = "webId";
/*     */   public static final String PARAM_TPL = "tpl";
/*     */   public static final String PARAM_TPL_SUB = "tplSub";
/*     */   public static final String PARAM_COUNT = "count";
/*     */   public static final int MAX_COUNT = 200;
/*     */   public static final boolean PARAM_TPL_DEF = false;
/*     */   public static final String OUT_LIST = "tag_list";
/*     */   public static final String OUT_PAGINATION = "tag_pagination";
/*     */   public static final String PARAM_PARENT_ID = "parentId";
/*     */ 
/*     */   protected void renderBody(Environment env, TemplateDirectiveBody body)
/*     */     throws TemplateException, IOException
/*     */   {
/*  50 */     body.render(env.getOut());
/*     */   }
/*     */ 
/*     */   protected RequestContext getContext(Environment env) throws TemplateException
/*     */   {
/*  55 */     TemplateModel templatemodel = env.getGlobalVariable("springMacroRequestContext");
/*  56 */     if ((templatemodel instanceof AdapterTemplateModel)) {
/*  57 */       return (RequestContext)((AdapterTemplateModel)templatemodel).getAdaptedObject(RequestContext.class);
/*     */     }
/*  59 */     throw new TemplateModelException("RequestContext 'springMacroRequestContext' not found in DataModel.");
/*     */   }
/*     */ 
/*     */   protected void includeTpl(String s, String s1, Website website, Map params, Environment env)
/*     */     throws IOException, TemplateException
/*     */   {
/*  66 */     String s2 = website.getTplTag(s, s1, getSubTpl(params));
/*  67 */     env.include(s2, "UTF-8", true);
/*     */   }
/*     */ 
/*     */   protected int getPageNo(Environment env) throws TemplateException
/*     */   {
/*  72 */     TemplateModel templatemodel = env.getGlobalVariable("pageNo");
/*  73 */     if ((templatemodel instanceof TemplateNumberModel)) {
/*  74 */       return ((TemplateNumberModel)templatemodel).getAsNumber().intValue();
/*     */     }
/*  76 */     throw new TemplateModelException("RequestContext 'pageNo' not found in DataModel.");
/*     */   }
/*     */ 
/*     */   public static Map getMap(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/*  83 */     TemplateModel model = (TemplateModel)params.get(name);
/*  84 */     if (model == null) {
/*  85 */       return null;
/*     */     }
/*  87 */     if ((model instanceof TemplateHashModel)) {
/*  88 */       TemplateHashModel s = (TemplateHashModel)model;
/*  89 */       return (Map)s;
/*     */     }
/*  91 */     return params;
/*     */   }
/*     */ 
/*     */   protected Website getWeb(Environment env, Map params, WebsiteMng websitemng)
/*     */     throws TemplateException
/*     */   {
/*  97 */     Long long1 = getWebId(params);
/*  98 */     if (long1 != null) {
/*  99 */       Website website = websitemng.findById(long1);
/* 100 */       if (website == null) {
/* 101 */         throw new TemplateModelException("Website id=" + long1 + " not exist.");
/*     */       }
/* 103 */       return website;
/*     */     }
/*     */ 
/* 106 */     TemplateModel templatemodel = env.getGlobalVariable("web");
/* 107 */     if ((templatemodel instanceof AdapterTemplateModel)) {
/* 108 */       return (Website)((AdapterTemplateModel)templatemodel).getAdaptedObject(Website.class);
/*     */     }
/* 110 */     throw new TemplateModelException("Website 'web' not found in DataModel");
/*     */   }
/*     */ 
/*     */   protected Long getWebId(Map params)
/*     */     throws TemplateException
/*     */   {
/* 116 */     TemplateModel templatemodel = (TemplateModel)params.get("webId");
/* 117 */     if (templatemodel == null) {
/* 118 */       return null;
/*     */     }
/* 120 */     if ((templatemodel instanceof TemplateNumberModel)) {
/* 121 */       return Long.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().longValue());
/*     */     }
/* 123 */     throw new TemplateModelException("The 'webId' parameter must be a number.");
/*     */   }
/*     */ 
/*     */   protected int getCount(Map params)
/*     */     throws TemplateException
/*     */   {
/* 129 */     Integer integer = getInt("count", params);
/* 130 */     if (integer == null) {
/* 131 */       throw new ParamsRequiredException("count");
/*     */     }
/* 133 */     if (integer.intValue() > 200) {
/* 134 */       integer = Integer.valueOf(1);
/*     */     }
/* 136 */     else if (integer.intValue() < 1) {
/* 137 */       integer = Integer.valueOf(200);
/*     */     }
/*     */ 
/* 140 */     return integer.intValue();
/*     */   }
/*     */ 
/*     */   protected boolean isInvokeTpl(Map params) throws TemplateException
/*     */   {
/* 145 */     TemplateModel templatemodel = (TemplateModel)params.get("tpl");
/* 146 */     if (templatemodel == null) {
/* 147 */       return false;
/*     */     }
/* 149 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 150 */       return DirectiveUtils.getBoolean((TemplateScalarModel)templatemodel);
/*     */     }
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   protected String getSubTpl(Map params)
/*     */     throws TemplateException
/*     */   {
/* 158 */     TemplateModel templatemodel = (TemplateModel)params.get("tplSub");
/* 159 */     if (templatemodel == null) {
/* 160 */       return null;
/*     */     }
/* 162 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 163 */       return ((TemplateScalarModel)templatemodel).getAsString();
/*     */     }
/* 165 */     throw new MustStringException("tplSub");
/*     */   }
/*     */ 
/*     */   protected String getString(String s, Map params)
/*     */     throws TemplateException
/*     */   {
/* 171 */     TemplateModel templatemodel = (TemplateModel)params.get(s);
/* 172 */     if (templatemodel == null) {
/* 173 */       return null;
/*     */     }
/* 175 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 176 */       return ((TemplateScalarModel)templatemodel).getAsString();
/*     */     }
/* 178 */     throw new MustStringException(s);
/*     */   }
/*     */ 
/*     */   protected Long getLong(String s, Map params)
/*     */     throws TemplateException
/*     */   {
/* 184 */     TemplateModel templatemodel = (TemplateModel)params.get(s);
/* 185 */     if (templatemodel == null) {
/* 186 */       return null;
/*     */     }
/* 188 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 189 */       String s1 = ((TemplateScalarModel)templatemodel).getAsString();
/* 190 */       if (StringUtils.isBlank(s1))
/* 191 */         return null;
/*     */       try
/*     */       {
/* 194 */         return Long.valueOf(Long.parseLong(s1));
/*     */       } catch (NumberFormatException e) {
/* 196 */         throw new MustNumberException(s);
/*     */       }
/*     */     }
/* 199 */     if ((templatemodel instanceof TemplateNumberModel)) {
/* 200 */       return Long.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().longValue());
/*     */     }
/* 202 */     throw new MustNumberException(s);
/*     */   }
/*     */ 
/*     */   protected Integer getInt(String s, Map params)
/*     */     throws TemplateException
/*     */   {
/* 208 */     TemplateModel templatemodel = (TemplateModel)params.get(s);
/* 209 */     if (templatemodel == null) {
/* 210 */       return null;
/*     */     }
/* 212 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 213 */       String s1 = ((TemplateScalarModel)templatemodel).getAsString();
/* 214 */       if (StringUtils.isBlank(s1))
/* 215 */         return null;
/*     */       try
/*     */       {
/* 218 */         return Integer.valueOf(Integer.parseInt(s1));
/*     */       } catch (NumberFormatException e) {
/* 220 */         throw new MustNumberException(s);
/*     */       }
/*     */     }
/* 223 */     if ((templatemodel instanceof TemplateNumberModel)) {
/* 224 */       return Integer.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().intValue());
/*     */     }
/* 226 */     throw new MustNumberException(s);
/*     */   }
/*     */ 
/*     */   protected Double getDouble(String s, Map params)
/*     */     throws TemplateException
/*     */   {
/* 232 */     TemplateModel templatemodel = (TemplateModel)params.get(s);
/* 233 */     if (templatemodel == null) {
/* 234 */       return null;
/*     */     }
/* 236 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 237 */       String s1 = ((TemplateScalarModel)templatemodel).getAsString();
/* 238 */       if (StringUtils.isBlank(s1))
/* 239 */         return null;
/*     */       try
/*     */       {
/* 242 */         return Double.valueOf(Double.parseDouble(s1));
/*     */       } catch (NumberFormatException e) {
/* 244 */         throw new MustNumberException(s);
/*     */       }
/*     */     }
/* 247 */     if ((templatemodel instanceof TemplateNumberModel)) {
/* 248 */       return Double.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().intValue());
/*     */     }
/* 250 */     throw new MustNumberException(s);
/*     */   }
/*     */ 
/*     */   protected Boolean getBool(String s, Map params)
/*     */     throws TemplateException
/*     */   {
/* 256 */     TemplateModel templatemodel = (TemplateModel)params.get(s);
/* 257 */     if (templatemodel == null) {
/* 258 */       return null;
/*     */     }
/* 260 */     if ((templatemodel instanceof TemplateScalarModel)) {
/* 261 */       String s1 = ((TemplateScalarModel)templatemodel).getAsString();
/* 262 */       if (StringUtils.isBlank(s1)) {
/* 263 */         return null;
/*     */       }
/* 265 */       return Boolean.valueOf(!s1.equals("0"));
/*     */     }
/*     */ 
/* 268 */     if ((templatemodel instanceof TemplateNumberModel)) {
/* 269 */       return Boolean.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().intValue() != 0);
/*     */     }
/* 271 */     throw new MustNumberException(s);
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.abs.WebDirective
 * JD-Core Version:    0.6.2
 */