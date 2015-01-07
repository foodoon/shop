package guda.shop.cms.action.directive;

import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.manager.CollectMng;
import guda.shop.cms.web.threadvariable.MemberThread;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class CollectPageDirective extends WebDirective
{
  public static final String TPL_NAME = "ArticlePage";
  public static final String PARAM_CATEGORY_ID = "channelId";
  private CollectMng collectMng;
  private WebsiteMng websiteMng;

  public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
    throws TemplateException, IOException
  {
/* 46 */     ShopMember member = MemberThread.get();
/* 47 */     Website web = getWeb(env, params, this.websiteMng);
/* 48 */     Integer count = Integer.valueOf(getCount(params));
/* 49 */     Pagination pagination = this.collectMng.getList(count, Integer.valueOf(getPageNo(env)), member.getId());
/* 50 */     Map paramWrap = new HashMap(
/* 51 */       params);
/* 52 */     paramWrap.put("tag_pagination", 
/* 53 */       ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination));
/* 54 */     paramWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pagination.getList()));
/* 55 */     Map origMap = 
/* 56 */       DirectiveUtils.addParamsToVariable(env, paramWrap);
/* 57 */     if (isInvokeTpl(params))
/* 58 */       includeTpl("shop", "ArticlePage", web, params, env);
    else {
/* 60 */       renderBody(env, loopVars, body);
    }
/* 62 */     DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
  }

  private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
  {
/* 67 */     body.render(env.getOut());
  }

  @Autowired
  public void setCollectMng(CollectMng collectMng)
  {
/* 75 */     this.collectMng = collectMng;
  }

  @Autowired
  public void setWebsiteMng(WebsiteMng websiteMng) {
/* 80 */     this.websiteMng = websiteMng;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.CollectPageDirective
 * JD-Core Version:    0.6.2
 */