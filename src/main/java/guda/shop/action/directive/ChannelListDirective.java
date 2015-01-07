/*    */ package guda.shop.action.directive;
/*    */ 
/*    */ import guda.shop.cms.action.directive.abs.WebDirective;
/*    */ import guda.shop.cms.entity.ShopChannel;
/*    */ import guda.shop.cms.manager.ShopChannelMng;
/*    */ import guda.shop.common.web.freemarker.DirectiveUtils;
/*    */ import guda.shop.core.entity.Website;
/*    */ import guda.shop.core.manager.WebsiteMng;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import freemarker.template.TemplateModelException;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class ChannelListDirective extends WebDirective
/*    */ {
/*    */   public static final String TPL_NAME = "TopChannel";
/*    */   private ShopChannelMng shopChannelMng;
/*    */   private WebsiteMng websiteMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 39 */     Long webId = getWebId(params);
/*    */     Website web;
/*    */     Website web;
/* 41 */     if (webId == null)
/* 42 */       web = getWeb(env, params, this.websiteMng);
/*    */     else {
/* 44 */       web = this.websiteMng.findById(webId);
/*    */     }
/* 46 */     if (web == null) {
/* 47 */       throw new TemplateModelException("webId=" + webId + " not exist!");
/*    */     }
/* 49 */     Long parentId = DirectiveUtils.getLong("parentId", params);
/*    */     List list;
/*    */     List list;
/* 51 */     if (parentId != null) {
/* 52 */       ShopChannel channel = this.shopChannelMng.findById(parentId);
/*    */       List list;
/* 53 */       if (channel != null)
/* 54 */         list = new ArrayList(channel.getChild());
/*    */       else
/* 56 */         list = new ArrayList();
/*    */     }
/*    */     else {
/* 59 */       list = this.shopChannelMng.getTopListForTag(web.getId(), Integer.valueOf(getCount(params)));
/*    */     }
/* 61 */     Map paramsWrap = new HashMap(
/* 62 */       params);
/* 63 */     paramsWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 64 */     Map origMap = 
/* 65 */       DirectiveUtils.addParamsToVariable(env, paramsWrap);
/* 66 */     if (isInvokeTpl(params))
/* 67 */       includeTpl("shop", "TopChannel", web, params, env);
/*    */     else {
/* 69 */       renderBody(env, loopVars, body);
/*    */     }
/* 71 */     DirectiveUtils.removeParamsFromVariable(env, paramsWrap, origMap);
/*    */   }
/*    */ 
/*    */   private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*    */   {
/* 76 */     body.render(env.getOut());
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setShopChannelMng(ShopChannelMng shopChannelMng)
/*    */   {
/* 84 */     this.shopChannelMng = shopChannelMng;
/*    */   }
/*    */ 
/*    */   public void setWebsiteMng(WebsiteMng websiteMng) {
/* 88 */     this.websiteMng = websiteMng;
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ChannelListDirective
 * JD-Core Version:    0.6.2
 */