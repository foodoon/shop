package guda.shop.cms.action.directive;


import freemarker.core.Environment;
import freemarker.template.*;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.entity.Category;
import guda.shop.cms.manager.CategoryMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CategoryListDirective extends WebDirective {
    public static final String TPL_NAME = "category_list";
    private CategoryMng categoryMng;
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
            Category category = this.categoryMng.findById(parentId);
            if (category != null)
                list = new ArrayList(category.getChild());
            else
                list = new ArrayList();
        } else {
            list = this.categoryMng.getTopListForTag(web.getId());

        }
        Map paramsWrap = new HashMap(
                params);

        paramsWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));

        Map origMap =
                DirectiveUtils.addParamsToVariable(env, paramsWrap);

        if (isInvokeTpl(params))
            includeTpl("tag", "category_list", web, params, env);

        else {

            renderBody(env, loopVars, body);

        }

        DirectiveUtils.removeParamsFromVariable(env, paramsWrap, origMap);

    }


    private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

        body.render(env.getOut());

    }


    @Autowired
    public void setCategoryMng(CategoryMng categoryMng) {

        this.categoryMng = categoryMng;

    }


    @Autowired
    public void setWebsiteMng(WebsiteMng websiteMng) {

        this.websiteMng = websiteMng;

    }

}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.CategoryListDirective
 * JD-Core Version:    0.6.2
 */