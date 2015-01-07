 package guda.shop.cms.action.front;
/*     */*/ import gcms.manager.KeyWordMng;
/*     */ import guda.shop.hopFrontHelper;
/*     */ import guda.shop.cms.web.S
/*     */ import guda.shop.common.web.Request     */ import guda.shop.common.web.springmvc.Messag;
/*     */ import guda.shop.core.entity.Website;
/*     */ impor.UnsupportedEncodingException;
/*     */ import URLEncoder;
/*     */ import java.util.regex.Matcher;
/import java.util.regex.Pattern;
/*    t javax.servlet.ServletContext;
/*     */ vax.servlet.http.HttpServletRequest;
/*   rt org.apache.commons.lang.StringUtils;
/*      org.springframework.beans.factory.annotation.Autowired;/ import org.springframework.stereotype.Controller;
/*mport org.springframework.ui.ModelMap;
/*     */ import org.springframewond.annotation.RequestMapping;
/*     */ import org.springfrab.context.ServletContextAware;
/*     */ 
/*     *ller
/*     */ public class SearchAct
/*     */   implements ServletConte*     */ {
/*     */   private static final String SEARCH_INPUT = "tplput";
/*   ivate static final StrH_RESULT = "tpl.searchResult";
/* private ServletContext servletContext;
/* *     */   @
/*     */   private KeyWordMng keyWordMng;
/*     */ 
/*     */   @Requesvalue={"/search*.jspx"}, method={org.springframework.web.bind.annotation.Reqd.GET})
/*     */   public String search(HttpServlerequest, Model)
/*     */   {
/*   Website web = SiteUtils.getWeb(request);
/    model.puestUtils.getQueryParams(request));
/*  41 */     ShopFrontHelper.setCommon(request, model, web);
/*  42 */     ShopFrontontPage(request, model);
/*  43 */     String q = RequestUtils.getQueryParam("q");
/*  44 */     q = StringUtils.trim(q);
/*  45 */     q = parseKeywords(q);
/*  46 */     model.addAttribute("q", q);
/*  47 */     String ctgId = RequestUtils.getQueryParam(request, "ctgId");
/*  48 */     ctgId = StringUtils.trim(ctgId);
/*  49 */     if ((StringUtils.isBlank(q)) && (StringUtils.isBlank(ctgId))) {
/*  50 */       model.remove("q");
       model.remove("ctgId");
/*  52 */       return web.getTplSys("shop", MessageResolver.getMessage(request, "tpl.searchInput", new Object[0]));
/*     */     }
/*  54 */     model.addAttribute("q", q);
/*  55 */     if (StringUtils.isBlank(ctgId))
/*  56 */       model.addAttribute("ctgId", null);
/*     */     else {
/*  58 */       model.addAttribute("ctgId", Integer.valueOf(ctgId));
/*     */     }
/*  60 */     this.keyWordMng.save(q);
/*  61 */     r.getTplSys("shop", MessageResolver.getMessage(request, "tpl.searchResult", new Object[0]));
/*     */   }
/*     */ 
/*     */   public String encodeFilenarvletRequest request, String fileName)
/*     */   {
/*  66 */     String agent = request.("USER-AGENT");
/*     */     try
/*     */     {
/*  69 */       if ((agent != null) && (-1 != agent.indexOf("MSIE")))
/*  70 */         fileName = URLEncoder.encode(fileF8");
/*     *lse
/*  72   fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
/*     */     }
/*   catch (UnsupportedEncodingException e) {
/*  75 */       e.printStackTrace  */     }
/*  77 turn fileName;
/*     */   }
/*     */ 
/*     */   public static String parseKeywords(String q)
/*     */   {
/*     */     try {
/*  83 */       Strin = "[\\+\\-\\&\\|\\!\\(\\)\\{\\}\\[\\]\\^\\~\\*\\?\\:\\\\]";
/*  84 */       Pattern p = Pattern.compile;
/*  85 */      m = p.matcher(q);
/*  86 */       String src = null;
/*  87 */       while (m.find()) {
/*       src = m.group();
/*  89 */         q = q.l("\\" + src, src);
/*     }
/*  91 */       q = q.replaceAll("AND", "and").replac, "or").replac"not");
/*     */     } catch (Exception localException) {
/*     */     }
/*  94 */     return q;
/*     */   }
/*     */ 
/*     */   public void setServletContext(ServletContext servletContext)
/*     */   {
/* 100 */     this.servletContext = servletContext;
/*     */   }
/*     */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.front.SearchAct
 * JD-Core Version:    0.6.2
 */