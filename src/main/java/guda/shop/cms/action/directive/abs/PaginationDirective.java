package guda.shop.cms.action.directive.abs;

import guda.shop.cms.web.FrontUtils;
import guda.shop.common.web.freemarker.DirectiveUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class PaginationDirective extends WebDirective
{
  public static final String PAGINATION_PATH = "/WEB-INF/tag/style_pagination/style";
  public static final String PARAM_SYTLE_NAME = "style";
  public static final String PARAM_CONTENT = "content";

  public void execute(Environment env, Map params, TemplateModel[] atemplatemodel, TemplateDirectiveBody body)
    throws TemplateException, IOException
  {
/* 40 */     Map model = DirectiveUtils.addParamsToVariable(env, params);
/* 41 */     String content = DirectiveUtils.getString("content", params);
/* 42 */     if ("1".equals(content)) {
      String sysPage = DirectiveUtils.getString("sysPage", params);
       String userPage = DirectiveUtils.getString("userPage", params);
      if (!StringUtils.isBlank(sysPage)) {
/* 46 */         String tpl = "/WEB-INF/t/gou_sys_defined/style_page/content_" + sysPage + ".html";
/* 47 */         env.include(tpl, "UTF-8", true);
/* 48 */       } else if (!StringUtils.isBlank(userPage)) {
/* 49 */         String tpl = "/WEB-INF/t/gou_sys_defined/style_page/content_" + userPage + ".html";
/* 50 */         env.include(tpl, "UTF-8", true);
      }
    }
    else
    {
/* 55 */       FrontUtils.includePagination(params, env);
    }
/* 57 */     DirectiveUtils.removeParamsFromVariable(env, params, model);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.action.directive.abs.PaginationDirective
 * JD-Core Version:    0.6.2
 */