package guda.shop.cms.action.directive;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.OrderMng;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderPageDirective extends WebDirective {
    public static final String TPL_NAME = "ArticlePage";
    public static final String ALL = "all";
    public static final String PENDING = "pending";
    public static final String PROSESSING = "processing";
    public static final String DELIVERED = "delivered";
    public static final String COMPLETE = "complete";
    private OrderMng orderMng;
    private WebsiteMng websiteMng;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        ShopMember member = MemberThread.get();

        Website web = getWeb(env, params, this.websiteMng);

        Integer count = Integer.valueOf(getCount(params));

        Integer status = getInt("status", params);

        String userName = getString("userName", params);

        String productName = getString("productName", params);

        Long code = getLong("code", params);

        Long paymentId = getLong("paymentId", params);

        Long shippingId = getLong("shippingId", params);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String startTime = getString("startTime", params);

        String endTime = getString("endTime", params);

        Date start = null;

        Date end = null;
        try {

            if (!StringUtils.isBlank(startTime))
                start = df.parse(startTime);
            else {

                start = null;
            }

            if (!StringUtils.isBlank(endTime))
                end = df.parse(endTime);
            else
                end = null;
        } catch (ParseException e) {

            e.printStackTrace();
        }

        Double startOrderTotal = getDouble("startOrderTotal", params);

        Double endOrderTotal = getDouble("endOrderTotal", params);


        Pagination pagination = this.orderMng.getPage(web.getId(), member.getId(), productName, userName, paymentId,
                shippingId, start, end, startOrderTotal, endOrderTotal, status, code, getPageNo(env), count.intValue());

        Map paramWrap = new HashMap(
                params);

        paramWrap.put("tag_pagination",
                ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));

        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));


        Map origMap =
                DirectiveUtils.addParamsToVariable(env, paramWrap);

        if (isInvokeTpl(params))
            includeTpl("shop", "ArticlePage", web, params, env);
        else {

            renderBody(env, loopVars, body);
        }

        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
    }

    private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

        body.render(env.getOut());
    }

    @Autowired
    public void setOrderMng(OrderMng orderMng) {

        this.orderMng = orderMng;
    }

    @Autowired
    public void setWebsiteMng(WebsiteMng websiteMng) {

        this.websiteMng = websiteMng;
    }
}

