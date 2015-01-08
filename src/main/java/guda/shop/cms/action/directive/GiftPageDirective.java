package guda.shop.cms.action.directive;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.manager.GiftMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GiftPageDirective extends WebDirective {
    public static final String TPL_NAME = "ArticlePage";
    public static final String PARAM_CATEGORY_ID = "channelId";
    private GiftMng giftMng;
    private WebsiteMng websiteMng;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
/* 42 */
        Website web = getWeb(env, params, this.websiteMng);
/* 43 */
        Pagination pagination = this.giftMng.getPageGift(getPageNo(env), getCount(params));
/* 44 */
        Map paramWrap = new HashMap(
/* 45 */       params);
/* 46 */
        paramWrap.put("tag_pagination",
/* 47 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/* 48 */
        paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));
/* 49 */
        Map origMap =
/* 50 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 51 */
        if (isInvokeTpl(params))
/* 52 */ includeTpl("shop", "ArticlePage", web, params, env);
        else {
/* 54 */
            renderBody(env, loopVars, body);
        }
/* 56 */
        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
    }

    private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
/* 61 */
        body.render(env.getOut());
    }

    @Autowired
    public void setGiftMng(GiftMng giftMng) {
/* 69 */
        this.giftMng = giftMng;
    }

    @Autowired
    public void setWebsiteMng(WebsiteMng websiteMng) {
/* 74 */
        this.websiteMng = websiteMng;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.GiftPageDirective
 * JD-Core Version:    0.6.2
 */