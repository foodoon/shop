 package guda.shop.cms.action.directive;
/*     */*/ import gcms.action.directive.abs.WebDirective;
/*     */ import guda.shop.y.ShopMember;
/*     */ import guda.shop.cms.managoreMng;
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
/*     */ ass ScorePageDirective extends Wve
/*     */ {
/*     */   public static final String = "ShopScorePage";
/*     */   private ShopScoreMng shopScoreMng;
/*     ate WebsiteteMng;
/*     */ 
/*     */   public void execute(Environment earams, Templ] loopVars, TemplateDirectiveBody body)
/*     */     throws Templaon, IOException
/*     */   {
/*  45 */     Shoember = MemberThread.get();
/*  46 */     Wb = getWeb(ms, this.websiteMng);
/*  47 */     Integer count = Integer.valueOf(getCount(params));
/*  48 */     Boolean statusl("status", params);
/*  49 */     Boolean useStatusl("useStatus", params);
/*  50 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     String startTime = getString("startTime", params);
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
/*     */ e
/*  64 */         end = null;
/*     */     }
/*     */     catch (ParseException e) {
/*  67 */       e.printStackTra    */     }
/*  69 */     Pagination pagination = thiseMng.getPage(member.getId(), status, useStatus,
/*  70 */       start, end, Integer.valueOf(getPageNo(env)), co 71 */     Map paramWrap = new HashMap(
/*  72 */  ms);
/*  73 */  rap.put("tag_pagination",
/*  74 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(n));
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
/*  88 */ render(env.getOut());
/*     */   }
/*     */ 
/*     */   @Autowired
   public void setShopScoreMng(ShopScoreMng shopScoreMng)
/*     */   {
/*  96 */     this.shopS shopScoreMng;/   }
/*       */   @Autowired
/*     */   public void setWebsiteMng(WebsiteMng websiteMng) {
/* 101 */     this.websiteMng = websiteMng;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ScorePageDirective
 * JD-Core Version:    0.6.2
 */