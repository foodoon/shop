/*    */ package guda.shop.cms.lucene;
/*    */ 
/*    */ import guda.shop.common.page.Pagination;
/*    */ iimport guda.shopcommon.web.freemarker.DirectiveUtils;
/*    */ imimport guda.shopore.entity.Website;
/*    */ import com.jspgou.core.manager.WebsiteMng;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.ObjectWrapper;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import freemarker.template.TemplateNumberModel;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.servlet.ServletContext;
/*    */ import org.apache.lucene.queryParser.ParseException;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class LuceneDirectivePage extends LuceneDirectiveAbstract
/*    */ {
/*    */   public static final String TPL_NAME = "ProductPage";
/*    */ 
/*    */   @Autowired
/*    */   private LuceneProductSvc luceneProductSvc;
/*    */ 
/*    */   @Autowired
/*    */   private ServletContext servletContext;
/*    */   protected WebsiteMng websiteMng;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 43 */     Website web = this.websiteMng.findById(Long.valueOf(1L));
/* 44 */     String query = getQuery(params);
/* 45 */     Long ctgId = getCtgId(params);
/*    */ 
/* 47 */     Long type = getPtypeId(params);
/* 48 */     Date start = getStartDate(params);
/* 49 */     Date end = getEndDate(params);
/* 50 */     int pageNo = ((TemplateNumberModel)env.getGlobalVariable("pageNo")).getAsNumber().intValue();
/*    */     try
/*    */     {
/* 53 */       String path = this.servletContext.getRealPath("/WEB-INF/lucene");
/* 54 */       pagination = this.luceneProductSvc.search(path, query, web.getId(), ctgId, start, end, pageNo, getCount(params));
/*    */     }
/*    */     catch (ParseException e)
/*    */     {
/*    */       Pagination pagination;
/* 56 */       throw new RuntimeException(e);
/*    */     }
/*    */     Pagination pagination;
/* 58 */     Map paramWrap = new HashMap(
/* 59 */       params);
/* 60 */     paramWrap.put("tag_pagination", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/* 61 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));
/* 62 */     Map origMap = 
/* 63 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 64 */     if (isInvokeTpl(params))
/* 65 */       includeTpl("shop", "ProductPage", web, params, env);
/*    */     else {
/* 67 */       renderBody(env, loopVars, body);
/*    */     }
/* 69 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setWebsiteMng(WebsiteMng websiteMng)
/*    */   {
/* 81 */     this.websiteMng = websiteMng;
/*    */   }
/*    */ }

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.lucene.LuceneDirectivePage
 * JD-Core Version:    0.6.2
 */