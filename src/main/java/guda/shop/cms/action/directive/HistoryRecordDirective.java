/*    */ package guda.shop.cms.action.directive;
/*    */ 
/*    */ import guda.shop.cms.manager.ProductMng;
/*    */ import guda.shop.common.web.freemarker.DirectiveUtils;
/*    */ import guda.shop.core.entity.Website;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class HistoryRecordDirective extends ProductAbstractDirective
/*    */ {
/*    */   public static final String TPL_NAME = "ProductList";
/*    */ 
/*    */   @Autowired
/*    */   private ProductMng productMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 37 */     Website web = getWeb(env, params, this.websiteMng);
/* 38 */     HashSet set = new HashSet();
/* 39 */     Integer count = getInt("count", params);
/* 40 */     String historyProductIds = getString("historyProductIds", params);
/* 41 */     if ((historyProductIds != null) && (!historyProductIds.equals(""))) {
/* 42 */       String[] pids = historyProductIds.split(",");
/* 43 */       if (pids.length > 0) {
/* 44 */         for (int i = 0; i < pids.length; i++) {
/* 45 */           set.add(Long.valueOf(pids[i]));
/*    */         }
/*    */       }
/*    */     }
/* 49 */     List list = this.productMng.getHistoryProduct(set, count);
/* 50 */     Map paramWrap = new HashMap(
/* 51 */       params);
/* 52 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 53 */     Map origMap = 
/* 54 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 55 */     if (isInvokeTpl(params))
/* 56 */       includeTpl("shop", "ProductList", web, params, env);
/*    */     else {
/* 58 */       renderBody(env, loopVars, body);
/*    */     }
/* 60 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.HistoryRecordDirective
 * JD-Core Version:    0.6.2
 */