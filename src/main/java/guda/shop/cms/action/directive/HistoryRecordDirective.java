
package guda.shop.cms.action.directive;



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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
















 public class HistoryRecordDirective extends ProductAbstractDirective
 {
       public static final String TPL_NAME = "ProductList";


    @Autowired
 private ProductMng productMng;



    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
     throws TemplateException, IOException
 {

        Website web = getWeb(env, params, this.websiteMng);

        HashSet set = new HashSet();

        Integer count = getInt("count", params);

        String historyProductIds = getString("historyProductIds", params);

        if ((historyProductIds != null) && (!historyProductIds.equals(""))) {

            String[] pids = historyProductIds.split(",");

            if (pids.length > 0) {

                for (int i = 0; i < pids.length; i++) {

                    set.add(Long.valueOf(pids[i]));

                }

            }

        }

        List list = this.productMng.getHistoryProduct(set, count);

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
 * Qualified Name:     com.jspgou.cms.action.directive.HistoryRecordDirective
 * JD-Core Version:    0.6.2
 */