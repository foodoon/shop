package guda.shop.cms.action.directive;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.manager.KeyWordMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyWordDirective extends ProductAbstractDirective {
    public static final String TPL_NAME = "ProductList";

    @Autowired
    private KeyWordMng keywordMng;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
/* 36 */
        Website web = getWeb(env, params, this.websiteMng);
/* 37 */
        Integer count = Integer.valueOf(getCount(params));
/* 38 */
        List beanList = this.keywordMng.findKeyWord(count);
/* 39 */
        Map paramWrap = new HashMap(
/* 40 */       params);
/* 41 */
        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(beanList));
/* 42 */
        Map origMap =
/* 43 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 44 */
        if (isInvokeTpl(params))
/* 45 */ includeTpl("shop", "ProductList", web, params, env);
        else {
/* 47 */
            renderBody(env, loopVars, body);
        }
/* 49 */
        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.KeyWordDirective
 * JD-Core Version:    0.6.2
 */