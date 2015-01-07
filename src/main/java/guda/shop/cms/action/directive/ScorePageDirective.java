/*     */ package guda.shop.cms.action.directive;
/*     */ 
/*     */ import guda.shop.cms.action.directive.abs.WebDirective;
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.manager.ShopScoreMng;
/*     */ import guda.shop.cms.web.threadvariable.MemberThread;
/*     */ import guda.shop.common.page.Pagination;
/*     */ import guda.shop.common.web.freemarker.DirectiveUtils;
/*     */ import guda.shop.core.entity.Website;
/*     */ import guda.shop.core.manager.WebsiteMng;
/*     */ import freemarker.core.Environment;
/*     */ import freemarker.template.ObjectWrapper;
/*     */ import freemarker.template.TemplateDirectiveBody;
/*     */ import freemarker.template.TemplateException;
/*     */ import freemarker.template.TemplateModel;
/*     */ import java.io.IOException;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ 
/*     */ public class ScorePageDirective extends WebDirective
/*     */ {
/*     */   public static final String TPL_NAME = "ShopScorePage";
/*     */   private ShopScoreMng shopScoreMng;
/*     */   private WebsiteMng websiteMng;
/*     */ 
/*     */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*     */     throws TemplateException, IOException
/*     */   {
/*  45 */     ShopMember member = MemberThread.get();
/*  46 */     Website web = getWeb(env, params, this.websiteMng);
/*  47 */     Integer count = Integer.valueOf(getCount(params));
/*  48 */     Boolean status = getBool("status", params);
/*  49 */     Boolean useStatus = getBool("useStatus", params);
/*  50 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*  51 */     String startTime = getString("startTime", params);
/*  52 */     String endTime = getString("endTime", params);
/*  53 */     Date start = null;
/*  54 */     Date end = null;
/*     */     try {
/*  56 */       if (!StringUtils.isBlank(startTime))
/*  57 */         start = df.parse(startTime);
/*     */       else {
/*  59 */         start = null;
/*     */       }
/*  61 */       if (!StringUtils.isBlank(endTime))
/*  62 */         end = df.parse(endTime);
/*     */       else
/*  64 */         end = null;
/*     */     }
/*     */     catch (ParseException e) {
/*  67 */       e.printStackTrace();
/*     */     }
/*  69 */     Pagination pagination = this.shopScoreMng.getPage(member.getId(), status, useStatus, 
/*  70 */       start, end, Integer.valueOf(getPageNo(env)), count);
/*  71 */     Map paramWrap = new HashMap(
/*  72 */       params);
/*  73 */     paramWrap.put("tag_pagination", 
/*  74 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/*  75 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));
/*  76 */     Map origMap = 
/*  77 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/*  78 */     if (isInvokeTpl(params))
/*  79 */       includeTpl("shop", "ShopScorePage", web, params, env);
/*     */     else {
/*  81 */       renderBody(env, loopVars, body);
/*     */     }
/*  83 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*     */   }
/*     */ 
/*     */   private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*     */   {
/*  88 */     body.render(env.getOut());
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setShopScoreMng(ShopScoreMng shopScoreMng)
/*     */   {
/*  96 */     this.shopScoreMng = shopScoreMng;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setWebsiteMng(WebsiteMng websiteMng) {
/* 101 */     this.websiteMng = websiteMng;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ScorePageDirective
 * JD-Core Version:    0.6.2
 */