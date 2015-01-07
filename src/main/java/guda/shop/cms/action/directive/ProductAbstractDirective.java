package guda.shop.cms.action.directive;

import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.manager.ProductMng;
import guda.shop.core.manager.WebsiteMng;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ProductAbstractDirective extends WebDirective
{
  public static final String PARAM_CATEGORY_ID = "categoryId";
  public static final String PARAM_TAG_ID = "tagId";
  public static final String PARAM_RECOMMEND = "recommend";
  public static final String PARAM_SPECIAL = "special";
  protected ProductMng productMng;
  protected WebsiteMng websiteMng;

  protected Long getCategoryId(Map<String, TemplateModel> params)
    throws TemplateException
  {
/* 42 */     return getLong("categoryId", params);
  }

  protected Long getPtypeId(Map<String, TemplateModel> params) throws TemplateException
  {
/* 47 */     return getLong("ptypeId", params);
  }

  protected Long getTagId(Map<String, TemplateModel> params) throws TemplateException
  {
/* 52 */     return getLong("tagId", params);
  }

  protected Boolean isRecommend(Map<String, TemplateModel> params) throws TemplateException
  {
/* 57 */     return getBool("recommend", params);
  }

  protected Boolean isSpecial(Map<String, TemplateModel> params) throws TemplateException
  {
/* 62 */     return getBool("special", params);
  }

  protected Boolean isHostSale(Map<String, TemplateModel> params) throws TemplateException
  {
/* 67 */     return getBool("hostSale", params);
  }

  protected Boolean isNewProduct(Map<String, TemplateModel> params) throws TemplateException
  {
/* 72 */     return getBool("newProduct", params);
  }

  protected void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
  {
/* 77 */     body.render(env.getOut());
  }

  @Autowired
  public void setProductMng(ProductMng productMng)
  {
/* 85 */     this.productMng = productMng;
  }

  @Autowired
  public void setWebsiteMng(WebsiteMng websiteMng) {
/* 90 */     this.websiteMng = websiteMng;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ProductAbstractDirective
 * JD-Core Version:    0.6.2
 */