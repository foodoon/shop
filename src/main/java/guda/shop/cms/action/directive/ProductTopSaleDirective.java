/*    */
package guda.shop.cms.action.directive;
/*    */
/*    */

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.entity.OrderItem;
import guda.shop.cms.manager.OrderItemMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
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
/*    */

/*    */
/*    */ public class ProductTopSaleDirective extends ProductAbstractDirective
/*    */ {
    /*    */   public static final String TPL_NAME = "ProductList";
    /*    */
/*    */
    @Autowired
/*    */ private OrderItemMng orderItemMng;

    /*    */
/*    */
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */ {
/* 38 */
        Website web = getWeb(env, params, this.websiteMng);
/* 39 */
        Integer count = Integer.valueOf(getCount(params));
/* 40 */
        List oiList = this.orderItemMng.getOrderItem();
/* 41 */
        List productList = new ArrayList();
/* 42 */
        for (int i = 0; i < oiList.size(); i++) {
/* 43 */
            Object[] o = (Object[]) oiList.get(i);
/* 44 */
            productList.add(((OrderItem) o[0]).getProduct());
/* 45 */
            if (i == count.intValue() - 1) {
/*    */
                break;
/*    */
            }
/*    */
        }
/* 49 */
        Map paramWrap = new HashMap(
/* 50 */       params);
/* 51 */
        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(productList));
/* 52 */
        Map origMap =
/* 53 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 54 */
        if (isInvokeTpl(params))
/* 55 */ includeTpl("shop", "ProductList", web, params, env);
/*    */
        else {
/* 57 */
            renderBody(env, loopVars, body);
/*    */
        }
/* 59 */
        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */
    }
/*    */
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ProductTopSaleDirective
 * JD-Core Version:    0.6.2
 */