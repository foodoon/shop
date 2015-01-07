/*    */ package guda.shop.action.directive;
/*    */ 
/*    */ import guda.shop.cms.action.directive.abs.WebDirective;
/*    */ import guda.shop.cms.entity.ShopMember;
/*    */ import guda.shop.cms.manager.ShopMoneyMng;
/*    */ import guda.shop.cms.web.threadvariable.MemberThread;
/*    */ import guda.shop.common.page.Pagination;
/*    */ import guda.shop.common.web.freemarker.DirectiveUtils;
/*    */ import guda.shop.core.entity.Website;
/*    */ import guda.shop.core.manager.WebsiteMng;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class MoneyPageDirective extends WebDirective
/*    */ {
/*    */   public static final String TPL_NAME = "ShopScorePage";
/*    */ 
/*    */   @Autowired
/*    */   private ShopMoneyMng shopMoneyMng;
/*    */ 
/*    */   @Autowired
/*    */   private WebsiteMng websiteMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 46 */     ShopMember member = MemberThread.get();
/* 47 */     Website web = getWeb(env, params, this.websiteMng);
/* 48 */     Integer count = Integer.valueOf(getCount(params));
/* 49 */     Boolean status = getBool("status", params);
/* 50 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/* 51 */     String startTime = getString("startTime", params);
/* 52 */     String endTime = getString("endTime", params);
/* 53 */     Date start = null;
/* 54 */     Date end = null;
/*    */     try
/*    */     {
/* 57 */       if (!StringUtils.isBlank(startTime))
/* 58 */         start = df.parse(startTime);
/*    */       else {
/* 60 */         start = null;
/*    */       }
/* 62 */       if (!StringUtils.isBlank(endTime))
/* 63 */         end = df.parse(endTime);
/*    */       else
/* 65 */         end = null;
/*    */     }
/*    */     catch (ParseException e) {
/* 68 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 71 */     Pagination pagination = this.shopMoneyMng.getPage(member.getId(), status, start, end, Integer.valueOf(getPageNo(env)), count);
/* 72 */     Map paramWrap = new HashMap(
/* 73 */       params);
/* 74 */     paramWrap.put("tag_pagination", 
/* 75 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/* 76 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));
/* 77 */     Map origMap = 
/* 78 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 79 */     if (isInvokeTpl(params))
/* 80 */       includeTpl("shop", "ShopScorePage", web, params, env);
/*    */     else {
/* 82 */       renderBody(env, loopVars, body);
/*    */     }
/* 84 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*    */   {
/* 89 */     body.render(env.getOut());
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.MoneyPageDirective
 * JD-Core Version:    0.6.2
 */