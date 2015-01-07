package guda.shop.cms.action.directive;

import guda.shop.cms.manager.ProductTypeMng;
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

public class ProductTypeListDirective extends ProductAbstractDirective
{
  public static final String TPL_NAME = "ProductList";

  @Autowired
  private ProductTypeMng productTypeMng;

  public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
    throws TemplateException, IOException
  {
/* 37 */     Website web = getWeb(env, params, this.websiteMng);

/* 39 */     List list = this.productTypeMng.getList(web.getId());
/* 40 */     Map paramWrap = new HashMap(
/* 41 */       params);
/* 42 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
    Map origMap =
       DirectiveUtils.addParamsToVariable(env, paramWrap);
    if (isInvokeTpl(params))
/* 46 */       includeTpl("shop", "ProductList", web, params, env);
    else {
/* 48 */       renderBody(env, loopVars, body);
    }
/* 50 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ProductTypeListDirective
 * JD-Core Version:    0.6.2
 */