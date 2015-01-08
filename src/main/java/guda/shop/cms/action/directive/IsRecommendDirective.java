/*    */
package guda.shop.cms.action.directive;
/*    */
/*    */

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.manager.ProductMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class IsRecommendDirective extends ProductAbstractDirective
/*    */ {
    /*    */   public static final String TPL_NAME = "ProductList";
    /*    */
/*    */
    @Autowired
/*    */ private ProductMng productMng;

    /*    */
/*    */
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */ {
/* 36 */
        Website web = getWeb(env, params, this.websiteMng);
/* 37 */
        Integer count = Integer.valueOf(getCount(params));
/* 38 */
        Boolean b = getBool("isrecommend", params);
/* 39 */
        List beanList = this.productMng.getIsRecommend(b, count.intValue());
/* 40 */
        Map paramWrap = new HashMap(
/* 41 */       params);
/* 42 */
        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(beanList));
/* 43 */
        Map origMap =
/* 44 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 45 */
        if (isInvokeTpl(params))
/* 46 */ includeTpl("shop", "ProductList", web, params, env);
/*    */
        else {
/* 48 */
            renderBody(env, loopVars, body);
/*    */
        }
/* 50 */
        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */
    }
/*    */
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.IsRecommendDirective
 * JD-Core Version:    0.6.2
 */