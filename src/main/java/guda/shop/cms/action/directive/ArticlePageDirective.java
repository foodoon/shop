/*    */
package guda.shop.cms.action.directive;
/*    */
/*    */

import freemarker.core.Environment;
import freemarker.template.*;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.manager.ShopArticleMng;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
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
/*    */

/*    */
/*    */ public class ArticlePageDirective extends WebDirective
/*    */ {
    /*    */   public static final String TPL_NAME = "ArticlePage";
    /*    */   public static final String PARAM_CHANNEL_ID = "channelId";
    /*    */   private ShopArticleMng shopArticleMng;
    /*    */   private WebsiteMng websiteMng;

    /*    */
/*    */
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */ {
/* 43 */
        Website web = getWeb(env, params, this.websiteMng);
/* 44 */
        Long channelId = getChannelId(params);
/* 45 */
        Pagination pagination = this.shopArticleMng.getPageForTag(web.getId(),
/* 46 */       channelId, getPageNo(env), getCount(params));
/* 47 */
        Map paramWrap = new HashMap(
/* 48 */       params);
/* 49 */
        paramWrap.put("tag_pagination",
/* 50 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/* 51 */
        Map origMap =
/* 52 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 53 */
        if (isInvokeTpl(params))
/* 54 */ includeTpl("shop", "ArticlePage", web, params, env);
/*    */
        else {
/* 56 */
            renderBody(env, loopVars, body);
/*    */
        }
/* 58 */
        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */
    }

    /*    */
/*    */
    private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*    */ {
/* 63 */
        body.render(env.getOut());
/*    */
    }

    /*    */
/*    */
    private Long getChannelId(Map<String, TemplateModel> params) throws TemplateException
/*    */ {
/* 68 */
        TemplateModel parentId = (TemplateModel) params.get("channelId");
/* 69 */
        if (parentId == null) {
/* 70 */
            return null;
/*    */
        }
/* 72 */
        if ((parentId instanceof TemplateNumberModel)) {
/* 73 */
            return Long.valueOf(((TemplateNumberModel) parentId).getAsNumber().longValue());
/*    */
        }
/* 75 */
        throw new TemplateModelException("The 'channelId' parameter must be a number.");
/*    */
    }

    /*    */
/*    */
    @Autowired
/*    */ public void setShopArticleMng(ShopArticleMng shopArticleMng)
/*    */ {
/* 85 */
        this.shopArticleMng = shopArticleMng;
/*    */
    }

    /*    */
/*    */
    @Autowired
/*    */ public void setWebsiteMng(WebsiteMng websiteMng) {
/* 90 */
        this.websiteMng = websiteMng;
/*    */
    }
/*    */
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.ArticlePageDirective
 * JD-Core Version:    0.6.2
 */