/*     */ package guda.shop.action.directive;
/*     */ 
/*     */ import guda.shop.cms.action.directive.abs.WebDirective;
/*     */ import guda.shop.cms.entity.ShopMember;
/*     */ import guda.shop.cms.manager.OrderMng;
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
/*     */ public class OrderPageDirective extends WebDirective
/*     */ {
/*     */   public static final String TPL_NAME = "ArticlePage";
/*     */   public static final String ALL = "all";
/*     */   public static final String PENDING = "pending";
/*     */   public static final String PROSESSING = "processing";
/*     */   public static final String DELIVERED = "delivered";
/*     */   public static final String COMPLETE = "complete";
/*     */   private OrderMng orderMng;
/*     */   private WebsiteMng websiteMng;
/*     */ 
/*     */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*     */     throws TemplateException, IOException
/*     */   {
/*  52 */     ShopMember member = MemberThread.get();
/*  53 */     Website web = getWeb(env, params, this.websiteMng);
/*  54 */     Integer count = Integer.valueOf(getCount(params));
/*  55 */     Integer status = getInt("status", params);
/*  56 */     String userName = getString("userName", params);
/*  57 */     String productName = getString("productName", params);
/*  58 */     Long code = getLong("code", params);
/*  59 */     Long paymentId = getLong("paymentId", params);
/*  60 */     Long shippingId = getLong("shippingId", params);
/*  61 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*  62 */     String startTime = getString("startTime", params);
/*  63 */     String endTime = getString("endTime", params);
/*  64 */     Date start = null;
/*  65 */     Date end = null;
/*     */     try {
/*  67 */       if (!StringUtils.isBlank(startTime))
/*  68 */         start = df.parse(startTime);
/*     */       else {
/*  70 */         start = null;
/*     */       }
/*  72 */       if (!StringUtils.isBlank(endTime))
/*  73 */         end = df.parse(endTime);
/*     */       else
/*  75 */         end = null;
/*     */     }
/*     */     catch (ParseException e) {
/*  78 */       e.printStackTrace();
/*     */     }
/*  80 */     Double startOrderTotal = getDouble("startOrderTotal", params);
/*  81 */     Double endOrderTotal = getDouble("endOrderTotal", params);
/*     */ 
/*  83 */     Pagination pagination = this.orderMng.getPage(web.getId(), member.getId(), productName, userName, paymentId, 
/*  84 */       shippingId, start, end, startOrderTotal, endOrderTotal, status, code, getPageNo(env), count.intValue());
/*  85 */     Map paramWrap = new HashMap(
/*  86 */       params);
/*  87 */     paramWrap.put("tag_pagination", 
/*  88 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/*  89 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));
/*     */ 
/*  91 */     Map origMap = 
/*  92 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/*  93 */     if (isInvokeTpl(params))
/*  94 */       includeTpl("shop", "ArticlePage", web, params, env);
/*     */     else {
/*  96 */       renderBody(env, loopVars, body);
/*     */     }
/*  98 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*     */   }
/*     */ 
/*     */   private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*     */   {
/* 103 */     body.render(env.getOut());
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setOrderMng(OrderMng orderMng)
/*     */   {
/* 111 */     this.orderMng = orderMng;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setWebsiteMng(WebsiteMng websiteMng) {
/* 116 */     this.websiteMng = websiteMng;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.OrderPageDirective
 * JD-Core Version:    0.6.2
 */