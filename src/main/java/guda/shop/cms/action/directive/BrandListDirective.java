/*    */ package guda.shop.cms.action.directive;
/*    */ 
/*    */ import guda.shop.cms.action.directive.abs.WebDirective;
/*    */ import guda.shop.cms.manager.BrandMng;
/*    */ import guda.shop.common.web.freemarker.DirectiveUtils;
/*    */ import guda.shop.core.entity.Website;
/*    */ import guda.shop.core.manager.WebsiteMng;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class BrandListDirective extends WebDirective
/*    */ {
/*    */   public static final String TPL_NAME = "BrandList";
/*    */   private BrandMng brandMng;
/*    */   private WebsiteMng websiteMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 38 */     Website web = getWeb(env, params, this.websiteMng);
/* 39 */     List list = this.brandMng.getListForTag(web.getId(), 0, 
/* 40 */       getCount(params));
/* 41 */     Map paramWrap = new HashMap(
/* 42 */       params);
/* 43 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 44 */     Map origMap = 
/* 45 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 46 */     if (isInvokeTpl(params))
/* 47 */       includeTpl("shop", "BrandList", web, params, env);
/*    */     else {
/* 49 */       renderBody(env, loopVars, body);
/*    */     }
/* 51 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*    */   {
/* 56 */     body.render(env.getOut());
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setBrandMng(BrandMng brandMng)
/*    */   {
/* 64 */     this.brandMng = brandMng;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setWebsiteMng(WebsiteMng websiteMng) {
/* 69 */     this.websiteMng = websiteMng;
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.BrandListDirective
 * JD-Core Version:    0.6.2
 */