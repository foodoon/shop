package guda.shop.cms.action.directive;


import freemarker.core.Environment;
import freemarker.template.*;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.entity.ShopChannel;
import guda.shop.cms.manager.ShopChannelMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChannelListDirective extends WebDirective {
    public static final String TPL_NAME = "TopChannel";
    private ShopChannelMng shopChannelMng;
    private WebsiteMng websiteMng;


    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        Long webId = getWebId(params);

        Website web;

        if (webId == null)
            web = getWeb(env, params, this.websiteMng);

        else {

            web = this.websiteMng.findById(webId);

        }

        if (web == null) {

            throw new TemplateModelException("webId=" + webId + " not exist!");

        }

        Long parentId = DirectiveUtils.getLong("parentId", params);

        List list;

        if (parentId != null) {

            ShopChannel channel = this.shopChannelMng.findById(parentId);

            if (channel != null)
                list = new ArrayList(channel.getChild());

            else
                list = new ArrayList();

        } else {

            list = this.shopChannelMng.getTopListForTag(web.getId(), Integer.valueOf(getCount(params)));

        }

        Map paramsWrap = new HashMap(
                params);

        paramsWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));

        Map origMap =
                DirectiveUtils.addParamsToVariable(env, paramsWrap);

        if (isInvokeTpl(params))
            includeTpl("shop", "TopChannel", web, params, env);

        else {

            renderBody(env, loopVars, body);

        }

        DirectiveUtils.removeParamsFromVariable(env, paramsWrap, origMap);

    }


    private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

        body.render(env.getOut());

    }


    @Autowired
    public void setShopChannelMng(ShopChannelMng shopChannelMng) {

        this.shopChannelMng = shopChannelMng;

    }


    public void setWebsiteMng(WebsiteMng websiteMng) {

        this.websiteMng = websiteMng;

    }

}

