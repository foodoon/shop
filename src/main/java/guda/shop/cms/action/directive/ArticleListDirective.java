/*    */ package guda.shop.cms.action.directive;
/*    */ 
/*    */ import guda.shop.cms.action.directive.abs.WebDirective;
/*    */ import guda.shop.cms.manager.ShopArticleMng;
/*    */ import guda.shop.common.web.freemarker.DirectiveUtils;
/*    */ import guda.shop.core.entity.Website;
/*    */ import guda.shop.core.manager.WebsiteMng;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import freemarker.template.TemplateModelException;
/*    */ import freemarker.template.TemplateNumberModel;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class ArticleListDirective extends WebDirective
/*    */ {
/*    */   public static final String TPL_NAME = "ArticleList";
/*    */   public static final String PARAM_CHANNEL_ID = "channelId";
/*    */   private ShopArticleMng shopArticleMng;
/*    */   private WebsiteMng websiteMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 44 */     Website web = getWeb(env, params, this.websiteMng);
/* 45 */     Long channelId = getChannelId(params);
/* 46 */     List list = this.shopArticleMng.getListForTag(web.getId(), channelId, 0, getCount(params));
/* 47 */     Map paramWrap = new HashMap(
/* 48 */       params);
/* 49 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 50 */     Map origMap = 
/* 51 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 52 */     if (isInvokeTpl(params))
/* 53 */       includeTpl("shop", "ArticleList", web, params, env);
/*    */     else {
/* 55 */       renderBody(env, loopVars, body);
/*    */     }
/* 57 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*    */   {
/* 62 */     body.render(env.getOut());
/*    */   }
/*    */ 
/*    */   private Long getChannelId(Map<String, TemplateModel> params) throws TemplateException
/*    */   {
/* 67 */     TemplateModel parentId = (TemplateModel)params.get("channelId");
/* 68 */     if (parentId == null) {
/* 69 */       return null;
/*    */     }
/* 71 */     if ((parentId instanceof TemplateNumberModel)) {
/* 72 */       return Long.valueOf(((TemplateNumberModel)parentId).getAsNumber().longValue());
/*    */     }
/* 74 */     throw new TemplateModelException("The 'channelId' parameter must be a number.");
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setShopArticleMng(ShopArticleMng shopArticleMng)
/*    */   {
/* 84 */     this.shopArticleMng = shopArticleMng;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setWebsiteMng(WebsiteMng websiteMng) {
/* 89 */     this.websiteMng = websiteMng;
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ArticleListDirective
 * JD-Core Version:    0.6.2
 */