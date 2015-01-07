/*    */ package guda.shop.cms.action.directive;
/*    */ 
/*    */ import guda.shop.cms.manager.ShopChannelMng;
/*    */ import guda.shop.common.web.freemarker.DirectiveUtils;
/*    */ import guda.shop.core.entity.Website;
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
/*    */ public class AloneChannelDirective extends ProductAbstractDirective
/*    */ {
/*    */   public static final String TPL_NAME = "ProductList";
/*    */ 
/*    */   @Autowired
/*    */   private ShopChannelMng manager;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 36 */     Website web = getWeb(env, params, this.websiteMng);
/* 37 */     List beanList = this.manager.getAloneChannelList(web.getId());
/* 38 */     Map paramWrap = new HashMap(
/* 39 */       params);
/* 40 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(beanList));
/* 41 */     Map origMap = 
/* 42 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 43 */     if (isInvokeTpl(params))
/* 44 */       includeTpl("shop", "ProductList", web, params, env);
/*    */     else {
/* 46 */       renderBody(env, loopVars, body);
/*    */     }
/* 48 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.AloneChannelDirective
 * JD-Core Version:    0.6.2
 */