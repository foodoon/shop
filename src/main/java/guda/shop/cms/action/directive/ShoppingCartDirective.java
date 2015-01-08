package guda.shop.cms.action.directive;


import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContext;

import java.io.IOException;
import java.util.Map;


public class ShoppingCartDirective extends WebDirective {
    private WebsiteMng websiteMng;


    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        Website web = getWeb(env, params, this.websiteMng);

        RequestContext ctx = getContext(env);

        Map origMap =
                DirectiveUtils.addParamsToVariable(env, params);

        includeTpl(web, ctx, env);

        DirectiveUtils.removeParamsFromVariable(env, params, origMap);

    }


    private void includeTpl(Website web, RequestContext ctx, Environment env) throws IOException, TemplateException {

        String tpl = web
                .getTplSys("shop", ctx.getMessage("tpl.shoppingCart"));

        env.include(tpl, "UTF-8", true);

    }


    @Autowired
    public void setWebsiteMng(WebsiteMng websiteMng) {

        this.websiteMng = websiteMng;

    }

}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ShoppingCartDirective
 * JD-Core Version:    0.6.2
 */