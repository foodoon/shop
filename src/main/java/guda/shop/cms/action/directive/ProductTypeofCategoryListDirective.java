package guda.shop.cms.action.directive;

import guda.shop.cms.manager.CategoryMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductTypeofCategoryListDirective extends ProductAbstractDirective
{
  public static final String TPL_NAME = "ProductList";

  @Autowired
  private CategoryMng categoryMng;

  public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
    throws TemplateException, IOException
  {
/* 36 */     Website web = getWeb(env, params, this.websiteMng);

/* 38 */     List list = this.categoryMng.getListBypType(web.getId(), getPtypeId(params), Integer.valueOf(getCount(params)));
/* 39 */     Map paramWrap = new HashMap(
/* 40 */       params);
/* 41 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 42 */     Map origMap = 
      DirectiveUtils.addParamsToVariable(env, paramWrap);
     if (isInvokeTpl(params))
      includeTpl("shop", "ProductList", web, params, env);
    else {
/* 47 */       renderBody(env, loopVars, body);
    }
/* 49 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ProductTypeofCategoryListDirective
 * JD-Core Version:    0.6.2
 */