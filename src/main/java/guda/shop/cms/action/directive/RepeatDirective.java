 package guda.shop.cms.action.directive;
/*     */*/ import f.core.Environment;
/*     */ import freemarker.SimpleNumber;
/*     */ import freemarker.templateBooleanModel;
/*     */ import freemarker.template.TemplateBody;
/*     */ import freemarker.template.TemplateDirective     */ import freemarker.template.TemplateException;
/*      freemarker.template.TemplateModel;
/*     */ import freemplate.TemplateModelException;
/*     */ import freemplate.TemplateNumberModel;
/*     */ import java.io.IOExcep    */ import java.io.Writer;
/*     */ import java.util.I/*     */ import java.util.Map;
/*    t java.util.Map.Entry;
/*     */ va.util.Set;
/*     */ 
/*     */ pub RepeatDirective
/*     */   impemplateDirectiveModel
/*     */ {
/*  rivate static final String PARAMNT = "count */   private static final String PARAM= "hr";
/*     */ 
/*     */   public void exeronment env,ms, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*     */ s TemplateException, IOException
/*     */   {
/*  58 */     iaram = 0;
/    boolean countParamSet = false;
/*  60 */     boolean hrParam = false;
/*     */ 
/*  62 */     Iterator paramItms
/*  63 */       .entrySet().iterator();
/*  64 */e (paramIter.hasNext()) {
/*  65 */       Entry ent =
/*  66 */         (Entry)paramIter
/*  66 */         .next();
/*     */ 
/*  6  String paramName = (String)ent.getKey();
/*  69 */       TemplateModel paramValue = (TemplateModel)ent.getValue();
/*     */ 
/*  71 */       if (paramName.equals("count")) {
/*  72 */         if (!(paramValue instanceof TemprModel)) {
/*  73 */           throw new TemplateModelException("The \"hr\" parameter must be a number.");
/*     */         }
/*     */ 
/*       countParam = ((TemplateNumberModel)paramValue).getAsNumber()
/*  77 */           .intValue();
/*  78 */         countParamSet = true;
/*  79 */         if (countParam < 0) {
/*  80 */           throw new TemplateModelExcee \"hr\" parameter cegative.");
/*     */         }
/*     */       }
/*  83 */       else if (paramName.equals("hr")) {
/*  84 */         if (!(paramValue instanceof TemplateBooleanModel)) {
/*  85 */           throw new TemplateModelException("The \"hr\" parameter must be a boolean.");
/*     */         }
/*     */ 
         hrParam = (BooleanModel)paramValue).getAsBoolean();
/*     */       } else {
/*  90 */         throw new TemplateModelException("Unsupported parameter: " +
/*  91 */           paramName);
/*     */       }
/*     */     }
/*  94 */     if (!countP{
/*  95 */       themplateModelException("The required \"count\" paramteris missing.");
/*     */     }
/*    99 */     if (loopVars.length > 1) {
/* 100 */       throw new TemplateModelException(
/* 101 */         "At most one loop variable is a;
/*     */     }

/* 109 */     Writer out = env.getOut();
/* 110 */     if (body != null)
/* 111 */       for (int i = 0; i < countParam; i++)
/*     */       {
/*       if ((hrPai != 0)) {
/* 115 */           out.write("<hr>");
/*     */         }
/*     */ 
/* 119 */         if (loopVars.length > 0) {
/* 120 */           loopVars[0] = neumber(i + 1);
/*       }
/*     */ 
/* 125 */         body.render(env.getOut());
/*     */       }
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.RepeatDirective
 * JD-Core Version:    0.6.2
 */