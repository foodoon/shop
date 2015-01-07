/*     */ package guda.shop.action.directive;
/*     */ 
/*     */ import freemarker.core.Environment;
/*     */ import freemarker.template.SimpleNumber;
/*     */ import freemarker.template.TemplateBooleanModel;
/*     */ import freemarker.template.TemplateDirectiveBody;
/*     */ import freemarker.template.TemplateDirectiveModel;
/*     */ import freemarker.template.TemplateException;
/*     */ import freemarker.template.TemplateModel;
/*     */ import freemarker.template.TemplateModelException;
/*     */ import freemarker.template.TemplateNumberModel;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class RepeatDirective
/*     */   implements TemplateDirectiveModel
/*     */ {
/*     */   private static final String PARAM_NAME_COUNT = "count";
/*     */   private static final String PARAM_NAME_HR = "hr";
/*     */ 
/*     */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*     */     throws TemplateException, IOException
/*     */   {
/*  58 */     int countParam = 0;
/*  59 */     boolean countParamSet = false;
/*  60 */     boolean hrParam = false;
/*     */ 
/*  62 */     Iterator paramIter = params
/*  63 */       .entrySet().iterator();
/*  64 */     while (paramIter.hasNext()) {
/*  65 */       Entry ent =
/*  66 */         (Entry)paramIter
/*  66 */         .next();
/*     */ 
/*  68 */       String paramName = (String)ent.getKey();
/*  69 */       TemplateModel paramValue = (TemplateModel)ent.getValue();
/*     */ 
/*  71 */       if (paramName.equals("count")) {
/*  72 */         if (!(paramValue instanceof TemplateNumberModel)) {
/*  73 */           throw new TemplateModelException("The \"hr\" parameter must be a number.");
/*     */         }
/*     */ 
/*  76 */         countParam = ((TemplateNumberModel)paramValue).getAsNumber()
/*  77 */           .intValue();
/*  78 */         countParamSet = true;
/*  79 */         if (countParam < 0) {
/*  80 */           throw new TemplateModelException("The \"hr\" parameter can't be negative.");
/*     */         }
/*     */       }
/*  83 */       else if (paramName.equals("hr")) {
/*  84 */         if (!(paramValue instanceof TemplateBooleanModel)) {
/*  85 */           throw new TemplateModelException("The \"hr\" parameter must be a boolean.");
/*     */         }
/*     */ 
/*  88 */         hrParam = ((TemplateBooleanModel)paramValue).getAsBoolean();
/*     */       } else {
/*  90 */         throw new TemplateModelException("Unsupported parameter: " + 
/*  91 */           paramName);
/*     */       }
/*     */     }
/*  94 */     if (!countParamSet) {
/*  95 */       throw new TemplateModelException("The required \"count\" paramteris missing.");
/*     */     }
/*     */ 
/*  99 */     if (loopVars.length > 1) {
/* 100 */       throw new TemplateModelException(
/* 101 */         "At most one loop variable is allowed.");
/*     */     }
/*     */ 
/* 109 */     Writer out = env.getOut();
/* 110 */     if (body != null)
/* 111 */       for (int i = 0; i < countParam; i++)
/*     */       {
/* 114 */         if ((hrParam) && (i != 0)) {
/* 115 */           out.write("<hr>");
/*     */         }
/*     */ 
/* 119 */         if (loopVars.length > 0) {
/* 120 */           loopVars[0] = new SimpleNumber(i + 1);
/*     */         }
/*     */ 
/* 125 */         body.render(env.getOut());
/*     */       }
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.RepeatDirective
 * JD-Core Version:    0.6.2
 */