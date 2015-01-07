/*    */ package guda.shop.cms.action.directive;
/*    */ 
/*    */ import guda.shop.cms.action.directive.abs.WebDirective;
/*    */ import guda.shop.cms.manager.CustomerServiceMng;
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
/*    */ public class CustomerServiceListDirective extends WebDirective
/*    */ {
/*    */   public static final String TPL_NAME = "CustomerServiceList";
/*    */   private CustomerServiceMng customerServiceMng;
/*    */   private WebsiteMng websiteMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 38 */     Website web = getWeb(env, params, this.websiteMng);
/* 39 */     List list = this.customerServiceMng.getList();
/* 40 */     Map paramWrap = new HashMap(params);
/* 41 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 42 */     Map origMap = 
/* 43 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 44 */     if (isInvokeTpl(params))
/* 45 */       includeTpl("shop", "CustomerServiceList", web, params, env);
/*    */     else {
/* 47 */       renderBody(env, loopVars, body);
/*    */     }
/* 49 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*    */   {
/* 54 */     body.render(env.getOut());
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setCustomerServiceMng(CustomerServiceMng customerServiceMng)
/*    */   {
/* 62 */     this.customerServiceMng = customerServiceMng;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setWebsiteMng(WebsiteMng websiteMng) {
/* 67 */     this.websiteMng = websiteMng;
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.CustomerServiceListDirective
 * JD-Core Version:    0.6.2
 */