
package guda.shop.cms.action.directive;



import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.manager.ProductTypeMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;















 public class ProductTypeListDirective extends ProductAbstractDirective
 {
       public static final String TPL_NAME = "ProductList";


    @Autowired
 private ProductTypeMng productTypeMng;



    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
     throws TemplateException, IOException
 {

        Website web = getWeb(env, params, this.websiteMng);


        List list = this.productTypeMng.getList(web.getId());

        Map paramWrap = new HashMap(
       params);

        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));

        Map origMap =
       DirectiveUtils.addParamsToVariable(env, paramWrap);

        if (isInvokeTpl(params))
 includeTpl("shop", "ProductList", web, params, env);

        else {

            renderBody(env, loopVars, body);

        }

        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);

    }

}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ProductTypeListDirective
 * JD-Core Version:    0.6.2
 */