package guda.shop.cms.action.directive;

import guda.shop.cms.action.directive.abs.WebDirective;
import guda.shop.cms.entity.Category;
import guda.shop.cms.manager.CategoryMng;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryListDirective extends WebDirective
{
  public static final String TPL_NAME = "category_list";
  private CategoryMng categoryMng;
  private WebsiteMng websiteMng;

  public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
    throws TemplateException, IOException
  {
/* 40 */     Long webId = getWebId(params);
    Website web;
    Website web;
/* 42 */     if (webId == null)
      web = getWeb(env, params, this.websiteMng);
    else {
      web = this.websiteMng.findById(webId);
    }
/* 47 */     if (web == null) {
/* 48 */       throw new TemplateModelException("webId=" + webId + " not exist!");
    }
/* 50 */     Long parentId = DirectiveUtils.getLong("parentId", params);
    List list;
    List list;
/* 52 */     if (parentId != null) {
/* 53 */       Category category = this.categoryMng.findById(parentId);
      List list;
/* 54 */       if (category != null)
/* 55 */         list = new ArrayList(category.getChild());
      else
/* 57 */         list = new ArrayList();
    }
    else {
/* 60 */       list = this.categoryMng.getTopListForTag(web.getId());
    }

/* 63 */     Map paramsWrap = new HashMap(
/* 64 */       params);
/* 65 */     paramsWrap.put("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
/* 66 */     Map origMap = 
/* 67 */       DirectiveUtils.addParamsToVariable(env, paramsWrap);
/* 68 */     if (isInvokeTpl(params))
/* 69 */       includeTpl("tag", "category_list", web, params, env);
    else {
/* 71 */       renderBody(env, loopVars, body);
    }
/* 73 */     DirectiveUtils.removeParamsFromVariable(env, paramsWrap, origMap);
  }

  private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
  {
/* 78 */     body.render(env.getOut());
  }

  @Autowired
  public void setCategoryMng(CategoryMng categoryMng)
  {
/* 86 */     this.categoryMng = categoryMng;
  }

  @Autowired
  public void setWebsiteMng(WebsiteMng websiteMng) {
/* 91 */     this.websiteMng = websiteMng;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.CategoryListDirective
 * JD-Core Version:    0.6.2
 */