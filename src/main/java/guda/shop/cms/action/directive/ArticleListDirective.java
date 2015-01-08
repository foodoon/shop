
package guda.shop.cms.action.directive;



import freemarker.core.Environment;
import freemarker.template.*;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.manager.ShopArticleMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



















 public class ArticleListDirective extends WebDirective
 {
       public static final String TPL_NAME = "ArticleList";
       public static final String PARAM_CHANNEL_ID = "channelId";
       private ShopArticleMng shopArticleMng;
       private WebsiteMng websiteMng;



    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
     throws TemplateException, IOException
 {

        Website web = getWeb(env, params, this.websiteMng);

        Long channelId = getChannelId(params);

        List list = this.shopArticleMng.getListForTag(web.getId(), channelId, 0, getCount(params));

        Map paramWrap = new HashMap(
       params);

        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));

        Map origMap =
       DirectiveUtils.addParamsToVariable(env, paramWrap);

        if (isInvokeTpl(params))
 includeTpl("shop", "ArticleList", web, params, env);

        else {

            renderBody(env, loopVars, body);

        }

        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);

    }



    private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
 {

        body.render(env.getOut());

    }



    private Long getChannelId(Map<String, TemplateModel> params) throws TemplateException
 {

        TemplateModel parentId = (TemplateModel) params.get("channelId");

        if (parentId == null) {

            return null;

        }

        if ((parentId instanceof TemplateNumberModel)) {

            return Long.valueOf(((TemplateNumberModel) parentId).getAsNumber().longValue());

        }

        throw new TemplateModelException("The 'channelId' parameter must be a number.");

    }



    @Autowired
 public void setShopArticleMng(ShopArticleMng shopArticleMng)
 {

        this.shopArticleMng = shopArticleMng;

    }



    @Autowired
 public void setWebsiteMng(WebsiteMng websiteMng) {

        this.websiteMng = websiteMng;

    }

}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ArticleListDirective
 * JD-Core Version:    0.6.2
 */