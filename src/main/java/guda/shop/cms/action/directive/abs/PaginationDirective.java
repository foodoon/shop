package guda.shop.cms.action.directive.abs;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import guda.shop.cms.web.FrontUtils;
import guda.shop.common.web.freemarker.DirectiveUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Map;

public class PaginationDirective extends WebDirective {
    public static final String PAGINATION_PATH = "/WEB-INF/tag/style_pagination/style";
    public static final String PARAM_SYTLE_NAME = "style";
    public static final String PARAM_CONTENT = "content";

    public void execute(Environment env, Map params, TemplateModel[] atemplatemodel, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        Map model = DirectiveUtils.addParamsToVariable(env, params);

        String content = DirectiveUtils.getString("content", params);

        if ("1".equals(content)) {

            String sysPage = DirectiveUtils.getString("sysPage", params);

            String userPage = DirectiveUtils.getString("userPage", params);

            if (!StringUtils.isBlank(sysPage)) {

                String tpl = "/WEB-INF/style_page/content_" + sysPage + ".html";

                env.include(tpl, "UTF-8", true);

            } else if (!StringUtils.isBlank(userPage)) {

                String tpl = "/WEB-INF/style_page/content_" + userPage + ".html";

                env.include(tpl, "UTF-8", true);
            }
        } else {

            FrontUtils.includePagination(params, env);
        }

        DirectiveUtils.removeParamsFromVariable(env, params, model);
    }
}

