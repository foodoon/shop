package guda.shop.cms.action.directive;

import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.manager.CouponMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class CouponPageDirective extends WebDirective
{
  public static final String TPL_NAME = "ShopCouponPage";

  @Autowired
  public CouponMng couponMng;
  private WebsiteMng websiteMng;

  public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
    throws TemplateException, IOException
  {
/* 39 */     Website web = getWeb(env, params, this.websiteMng);
/* 40 */     Integer count = Integer.valueOf(getCount(params));
/* 41 */     Pagination pagination = this.couponMng.getPageByUsing(getPageNo(env), count.intValue());
/* 42 */     Map paramWrap = new HashMap(
      params);
     paramWrap.put("tag_pagination",
      ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/* 46 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));
/* 47 */     Map origMap = 
/* 48 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 49 */     if (isInvokeTpl(params))
/* 50 */       includeTpl("shop", "ShopCouponPage", web, params, env);
    else {
/* 52 */       renderBody(env, loopVars, body);
    }
/* 54 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
  }

  private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
  {
/* 59 */     body.render(env.getOut());
  }

  @Autowired
  public void setWebsiteMng(WebsiteMng websiteMng)
  {
/* 69 */     this.websiteMng = websiteMng;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.CouponPageDirective
 * JD-Core Version:    0.6.2
 */