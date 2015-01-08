
package guda.shop.cms.action.directive;



import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;














 public class ProductPageDirective extends ProductAbstractDirective
 {
       public static final String TPL_NAME = "ProductPage";



    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
     throws TemplateException, IOException
 {

        Website web = getWeb(env, params, this.websiteMng);

        Long ctgId = getCategoryId(params);

        Long tagId = getTagId(params);

        Pagination pagination = this.productMng.getPageForTag(web.getId(), ctgId,
       tagId, isRecommend(params), isSpecial(params), getPageNo(env),
       getCount(params));

        Map paramWrap = new HashMap(
       params);

        paramWrap.put("tag_pagination",
       ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));

        Map origMap =
       DirectiveUtils.addParamsToVariable(env, paramWrap);

        if (isInvokeTpl(params))
 includeTpl("shop", "ProductPage", web, params, env);

        else {

            renderBody(env, loopVars, body);

        }

        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);

    }

}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ProductPageDirective
 * JD-Core Version:    0.6.2
 */