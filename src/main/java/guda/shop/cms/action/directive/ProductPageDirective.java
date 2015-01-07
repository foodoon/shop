/*    */ package guda.shop.cms.action.directive;
/*    */ 
/*    */ import guda.shop.cms.manager.ProductMng;
/*    */ import guda.shop.common.page.Pagination;
/*    */ import guda.shop.common.web.freemarker.DirectiveUtils;
/*    */ import guda.shop.core.entity.Website;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ProductPageDirective extends ProductAbstractDirective
/*    */ {
/*    */   public static final String TPL_NAME = "ProductPage";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 32 */     Website web = getWeb(env, params, this.websiteMng);
/* 33 */     Long ctgId = getCategoryId(params);
/* 34 */     Long tagId = getTagId(params);
/* 35 */     Pagination pagination = this.productMng.getPageForTag(web.getId(), ctgId, 
/* 36 */       tagId, isRecommend(params), isSpecial(params), getPageNo(env), 
/* 37 */       getCount(params));
/* 38 */     Map paramWrap = new HashMap(
/* 39 */       params);
/* 40 */     paramWrap.put("tag_pagination", 
/* 41 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/* 42 */     Map origMap = 
/* 43 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 44 */     if (isInvokeTpl(params))
/* 45 */       includeTpl("shop", "ProductPage", web, params, env);
/*    */     else {
/* 47 */       renderBody(env, loopVars, body);
/*    */     }
/* 49 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ProductPageDirective
 * JD-Core Version:    0.6.2
 */