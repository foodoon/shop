package guda.shop.cms.action.directive;


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


public class ProductTopSaleDirective extends ProductAbstractDirective {
    public static final String TPL_NAME = "ProductList";


    @Autowired
    private OrderItemMng orderItemMng;


    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        Website web = getWeb(env, params, this.websiteMng);

        Integer count = Integer.valueOf(getCount(params));

        List oiList = this.orderItemMng.getOrderItem();

        List productList = new ArrayList();

        for (int i = 0; i < oiList.size(); i++) {

            Object[] o = (Object[]) oiList.get(i);

            productList.add(((OrderItem) o[0]).getProduct());

            if (i == count.intValue() - 1) {

                break;

            }

        }

        Map paramWrap = new HashMap(
                params);

        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(productList));

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

