package guda.shop.cms.action.directive;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.entity.Brand;
import guda.shop.cms.manager.BrandMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SiftBrandDirective extends WebDirective {
    /*    */   public static final String TPL_NAME = "BrandList";
    /*    */   private BrandMng brandMng;
    /*    */   private WebsiteMng websiteMng;

    /*    */
/*    */
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */ {
/* 38 */
        Website web = getWeb(env, params, this.websiteMng);
/* 39 */
        Brand brand = this.brandMng.getsiftBrand();
/* 40 */
        Map paramWrap = new HashMap(
/* 41 */       params);
/* 42 */
        paramWrap.put("tag_bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(brand));
/* 43 */
        Map origMap =
/* 44 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 45 */
        if (isInvokeTpl(params))
/* 46 */ includeTpl("shop", "BrandList", web, params, env);
/*    */
        else {
/* 48 */
            renderBody(env, loopVars, body);
/*    */
        }
/* 50 */
        DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
/*    */
    }

    /*    */
/*    */
    private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
/*    */ {
/* 55 */
        body.render(env.getOut());
/*    */
    }

    /*    */
/*    */
    @Autowired
/*    */ public void setBrandMng(BrandMng brandMng)
/*    */ {
/* 63 */
        this.brandMng = brandMng;
/*    */
    }

    /*    */
/*    */
    @Autowired
/*    */ public void setWebsiteMng(WebsiteMng websiteMng) {
/* 68 */
        this.websiteMng = websiteMng;
/*    */
    }
/*    */
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.SiftBrandDirective
 * JD-Core Version:    0.6.2
 */