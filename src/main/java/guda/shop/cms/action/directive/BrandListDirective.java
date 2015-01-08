package guda.shop.cms.action.directive;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.manager.BrandMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrandListDirective extends WebDirective {
    public static final String TPL_NAME = "BrandList";
    private BrandMng brandMng;
    private WebsiteMng websiteMng;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        Website web = getWeb(env, params, this.websiteMng);

        List list = this.brandMng.getListForTag(web.getId(), 0,
       getCount(params));

        Map paramWrap = new HashMap(
       params);

        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));

        Map origMap =
       DirectiveUtils.addParamsToVariable(env, paramWrap);

        if (isInvokeTpl(params))
 includeTpl("shop", "BrandList", web, params, env);
        else {

            renderBody(env, loopVars, body);
        }

        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
    }

    private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

        body.render(env.getOut());
    }

    @Autowired
    public void setBrandMng(BrandMng brandMng) {

        this.brandMng = brandMng;
    }

    @Autowired
    public void setWebsiteMng(WebsiteMng websiteMng) {

        this.websiteMng = websiteMng;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.BrandListDirective
 * JD-Core Version:    0.6.2
 */