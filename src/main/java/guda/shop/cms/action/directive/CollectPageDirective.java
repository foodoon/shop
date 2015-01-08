
package guda.shop.cms.action.directive;



import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.CollectMng;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



















 public class CollectPageDirective extends WebDirective
 {
       public static final String TPL_NAME = "ArticlePage";
       public static final String PARAM_CATEGORY_ID = "channelId";
       private CollectMng collectMng;
       private WebsiteMng websiteMng;



    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
     throws TemplateException, IOException
 {

        ShopMember member = MemberThread.get();

        Website web = getWeb(env, params, this.websiteMng);

        Integer count = Integer.valueOf(getCount(params));

        Pagination pagination = this.collectMng.getList(count, Integer.valueOf(getPageNo(env)), member.getId());

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



    private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
 {

        body.render(env.getOut());

    }



    @Autowired
 public void setCollectMng(CollectMng collectMng)
 {

        this.collectMng = collectMng;

    }



    @Autowired
 public void setWebsiteMng(WebsiteMng websiteMng) {

        this.websiteMng = websiteMng;

    }

}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.CollectPageDirective
 * JD-Core Version:    0.6.2
 */