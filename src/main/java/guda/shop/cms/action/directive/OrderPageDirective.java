 package guda.shop.cms.action.directive;
/*     */*/ import gcms.action.directive.abs.WebDirective;
/*     */ import guda.shop.y.ShopMember;
/*     */ import guda.shop.cms.managng;
/*     */ import guda.shop.cms.web.threadvarierThread;
/*     */ import guda.shop.common.page.Pagination;
/* port guda.shop.common.web.freemarker.DirectiveUtils*/ import guda.shop.core.entity.Website;
/*     */ import guda.shanager.WebsiteMng;
/*     */ import freemarker.conment;
/*     */ import freemarker.template.ObjectW*     */ import freemarker.template.TemplateDidy;
/*     */ import freemarker.template.TemplateExc*     */ import freemarker.template.TemplateModel;
/*     */ava.io.IOException;
/*     */ import java.text.ParseExce     */ import java.text.SimpleDateFormat;
/*     */ava.util.Date;
/*     */ import java.uap;
/*     */ import java.util.Map;
/*      org.apache.commons.lang.StringUtils;
/*      org.springframework.beans.factorion.Autowired;
/*     */ 
/*     */ ass OrderPageDirective extends Wve
/*     */ {
/*     */   public static final String = "ArticlePage";
/*     */   public static final String ALL = "all";
/*  ublic statitring PENDING = "pending";
/*     */   public static final StriSING = "proc/*     */   public static final String DELIVERED = "delivered";
/  public static final String COMPLETE = "complete";
   private OrderMng orderMng;
/*     */   private WebsiteMngng;
/*     */ 
/*     */   public void execute(Environment env, Ma TemplateModel[] loopVars, TemplateDirectiveBody body)
/*     */ws TemplateException, IOException
/*     */   {
/*  52 */     r member = MemberThread.get();
/*  53 *site web = getWeb(env, params, this.website 54 */     ount = Integer.valueOf(getCount(params));
/*  55 */     Integer status = getInt("status", params);
/*  56 */     StName = getString("userName", params);
/*  57 */     oductName = getString("productName", params);
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
/*   }
/*     */     catch (ParseException e) {
/*  78 */       e.printStackTrace();
/*     */     }
/*  80 */     Double stotal = getDouble("startOrderTotal", params);
/*  81 */ e endOrderTotal = getDouble("endOrderTotal", params);
/*     */ 
/*  83 */     Pagination pagination = this.ordeage(web.getId(), member.getId(), productName, userNentId,
/*  84 *hippingId, start, end, startOrderTotal, endOrderTotal, status, code, getPageNount.intValue());
/*  85 */     Map paramWrap = new HashMap(
/*  86 */       params);
/*  87 */     paramWrap.put("tag_pagination", 
/*  88 */       ObjectWrapper.DEFAER.wrap(pagination));
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
/*     ate void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*     */   {
/* 103 */     body.render(env.getOut());
/*     */   }
/*     */ 
/*     owired
/*     */   public void setOrderMng(OrderMng orderMng)
/*     *111 */     this.orderMng = orderMng;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */  oid setWebsiteteMng websi/* 116 */     this.websiteMng = websiteMng;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.OrderPageDirective
 * JD-Core Version:    0.6.2
 */