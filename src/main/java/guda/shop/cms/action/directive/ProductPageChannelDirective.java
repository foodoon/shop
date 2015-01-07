package guda.shop.cms.action.directive;

import guda.shop.cms.manager.ProductMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class ProductPageChannelDirective extends ProductAbstractDirective
{
  public static final String TPL_NAME = "ProductPage";

  public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
    throws TemplateException, IOException
  {
/* 34 */     Website web = getWeb(env, params, this.websiteMng);
/* 35 */     Long ctgId = getCategoryId(params);
/* 36 */     Long brandId = getLong("brandId", params);
/* 37 */     Long tagId = getTagId(params);
/* 38 */     String[] names = StringUtils.split(getString("names", params), ',');
/* 39 */     String[] values = StringUtils.split(getString("values", params), ',');
/* 40 */     Integer orderBy = getInt("orderBy", params);
/* 41 */     Pagination pagination = this.productMng.getPageForTagChannel(brandId, web.getId(), ctgId, 
/* 42 */       tagId, isRecommend(params), names, values, isSpecial(params), orderBy.intValue(), getPageNo(env), 
      getCount(params));
     Map paramWrap = new HashMap(
      params);
/* 46 */     paramWrap.put("tag_pagination", 
/* 47 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/* 48 */     Map origMap = 
/* 49 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 50 */     if (isInvokeTpl(params))
/* 51 */       includeTpl("shop", "ProductPage", web, params, env);
    else {
/* 53 */       renderBody(env, loopVars, body);
    }
/* 55 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ProductPageChannelDirective
 * JD-Core Version:    0.6.2
 */