package guda.shop.cms.web;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import guda.shop.common.web.RequestUtils;
import guda.shop.common.web.freemarker.DirectiveUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class FrontUtils {
    public static final String PARAM_SYS_PAGE = "sysPage";
    public static final String PARAM_USER_PAGE = "userPage";
    public static final String COUNT = "count";
    public static final String FIRST = "first";
    public static final String PAGE_NO = "pageNo";
    public static final String MESSAGE = "message";
    public static final String ARGS = "args";
    public static final String RETURN_URL = "returnUrl";

    public static int getCount(Map<String, TemplateModel> paramMap)
            throws TemplateException {
        Integer localInteger = DirectiveUtils.getInt("count", paramMap);
        if ((localInteger == null) || (localInteger.intValue() <= 0) || (localInteger.intValue() >= 5000))
            return 5000;
        return localInteger.intValue();
    }

    public static int getFirst(Map<String, TemplateModel> paramMap)
            throws TemplateException {
        Integer localInteger = DirectiveUtils.getInt("first", paramMap);
        if ((localInteger == null) || (localInteger.intValue() <= 0))
            return 0;
        return localInteger.intValue() - 1;
    }

    public static int getPageNo(Environment paramEnvironment)
            throws TemplateException {
        TemplateModel localTemplateModel = paramEnvironment.getGlobalVariable("pageNo");
        if ((localTemplateModel instanceof TemplateNumberModel))
            return ((TemplateNumberModel) localTemplateModel).getAsNumber().intValue();
        throw new TemplateModelException("'pageNo' not found in DataModel.");
    }

    public static void includePagination(Map<String, TemplateModel> paramMap, Environment paramEnvironment)
            throws TemplateException, IOException {
        String str1 = DirectiveUtils.getString("sysPage", paramMap);
        String str2 = DirectiveUtils.getString("userPage", paramMap);
        if (!StringUtils.isBlank(str1)) {
            String str3 = "/WEB-INF/style_page/channel_" + str1 + ".html";
            paramEnvironment.include(str3, "UTF-8", true);
        } else if (StringUtils.isBlank(str2)) ;
    }

    public static String showMessage(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap, String paramString) {
        Website localWebsite = SiteUtils.getWeb(paramHttpServletRequest);
        ShopFrontHelper.setCommonData(paramHttpServletRequest, paramModelMap, localWebsite, 1);
        paramModelMap.put("message", paramString);
        return localWebsite.getTplSys("common", MessageResolver.getMessage(paramHttpServletRequest, "tpl.messagePage", new Object[0]));
    }

    public static String showLogin(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap) {
        StringBuilder localStringBuilder = new StringBuilder("redirect:");
        localStringBuilder.append("/login.jspx").append("?");
        localStringBuilder.append("returnUrl").append("=");
        localStringBuilder.append(RequestUtils.getLocation(paramHttpServletRequest));
        return localStringBuilder.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.FrontUtils
 * JD-Core Version:    0.6.2
 */