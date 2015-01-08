package guda.shop.core.web.front;

import guda.shop.common.web.RequestUtils;
import guda.shop.common.web.springmvc.MessageResolver;
import guda.shop.core.entity.Website;
import guda.shop.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

public abstract class FrontHelper {
    public static final String TPL_PAGE_NOT_FOUND = "tpl.pageNotFound";
    public static final String TPL_SUCCESS_PAGE = "tpl.successPage";
    public static final String TPL_ERROR_PAGE = "tpl.errorPage";
    public static final String TPL_MESSAGE_PAGE = "tpl.messagePage";

    public static String pageNotFound(Website paramWebsite, ModelMap paramModelMap, HttpServletRequest paramHttpServletRequest) {
        setCommonData(paramHttpServletRequest, paramModelMap, paramWebsite, 1);
        return paramWebsite.getTplSys("common", MessageResolver.getMessage(paramHttpServletRequest, "tpl.pageNotFound", new Object[0]));
    }

    public static String showSuccess(String paramString1, String paramString2, Website paramWebsite, ModelMap paramModelMap, HttpServletRequest paramHttpServletRequest) {
        setCommonData(paramHttpServletRequest, paramModelMap, paramWebsite, 1);
        paramModelMap.addAttribute("message", paramString1);
        if (!StringUtils.isBlank(paramString2))
            paramModelMap.addAttribute("backUrl", paramString2);
        return paramWebsite.getTplSys("common", MessageResolver.getMessage(paramHttpServletRequest, "tpl.successPage", new Object[0]));
    }

    public static String showError(WebErrors paramWebErrors, Website paramWebsite, ModelMap paramModelMap, HttpServletRequest paramHttpServletRequest) {
        setCommonData(paramHttpServletRequest, paramModelMap, paramWebsite, 1);
        paramWebErrors.toModel(paramModelMap);
        return paramWebsite.getTplSys("common", MessageResolver.getMessage(paramHttpServletRequest, "tpl.errorPage", new Object[0]));
    }

    public static String showMessage(String paramString, Website paramWebsite, ModelMap paramModelMap, HttpServletRequest paramHttpServletRequest) {
        setCommonData(paramHttpServletRequest, paramModelMap, paramWebsite, 1);
        paramModelMap.addAttribute("message", paramString);
        return paramWebsite.getTplSys("common", MessageResolver.getMessage(paramHttpServletRequest, "tpl.messagePage", new Object[0]));
    }

    public static void setDynamicPageData(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap, Website paramWebsite, String paramString1, String paramString2, String paramString3, int paramInt) {
        paramModelMap.addAttribute("urlPrefix", paramString2);
        paramModelMap.addAttribute("urlSuffix", paramString3);
        _$1(paramHttpServletRequest, paramModelMap, paramWebsite, paramInt, paramString1);
    }

    private static void _$1(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap, Website paramWebsite, int paramInt, String paramString) {
        paramModelMap.addAttribute("location", paramString);
        paramModelMap.addAttribute("web", paramWebsite);
        String str1 = (String) paramHttpServletRequest.getAttribute("_base_domain_key");
        if (str1 != null)
            paramModelMap.addAttribute("baseDomain", str1);
        String str2 = (String) paramHttpServletRequest.getAttribute("_login_url");
        if (str2 != null)
            paramModelMap.addAttribute("loginUrl", getLoginUrl(str2, paramHttpServletRequest.getContextPath(), paramString));
        paramModelMap.addAttribute("root", paramWebsite.getResBaseUrl());
        paramModelMap.addAttribute("pageNo", Integer.valueOf(paramInt));
        paramModelMap.addAttribute("_start_time", paramHttpServletRequest.getAttribute("_start_time"));
    }

    public static void setCommonData(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap, Website paramWebsite, int paramInt) {
        StringBuffer localStringBuffer = paramHttpServletRequest.getRequestURL();
        Map localMap = paramHttpServletRequest.getParameterMap();
        if ((localMap != null) && (localMap.size() > 0)) {
            localStringBuffer.append("?");
            Iterator localIterator = localMap.keySet().iterator();
            while (localIterator.hasNext()) {
                String str = (String) localIterator.next();
                String[] arrayOfString = (String[]) localMap.get(str);
                for (int i = 0; i < arrayOfString.length; i++)
                    localStringBuffer.append(str).append("=").append(arrayOfString[i]).append("&");
            }
        }
        _$1(paramHttpServletRequest, paramModelMap, paramWebsite, paramInt, localStringBuffer.toString());
    }

    public static void setCommon(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap, Website paramWebsite) {
        String str = RequestUtils.getLocation(paramHttpServletRequest);
        _$1(paramHttpServletRequest, paramModelMap, paramWebsite, str);
    }

    private static void _$1(HttpServletRequest paramHttpServletRequest, ModelMap paramModelMap, Website paramWebsite, String paramString) {
        paramModelMap.addAttribute("location", paramString);
        paramModelMap.addAttribute("web", paramWebsite);
        String str1 = (String) paramHttpServletRequest.getAttribute("_base_domain_key");
        if (str1 != null)
            paramModelMap.addAttribute("baseDomain", str1);
        String str2 = (String) paramHttpServletRequest.getAttribute("_login_url");
        if (str2 != null)
            paramModelMap.addAttribute("loginUrl", getLoginUrl(str2, paramHttpServletRequest.getContextPath(), paramString));
        paramModelMap.addAttribute("root", paramWebsite.getResBaseUrl());
        paramModelMap.addAttribute("_start_time", paramHttpServletRequest.getAttribute("_start_time"));
    }

    public static String getLoginUrl(String paramString1, String paramString2, String paramString3) {
        StringBuilder localStringBuilder = new StringBuilder();
        if (!paramString1.startsWith("http")) {
            localStringBuilder.append(paramString2);
            if (!paramString1.startsWith("/"))
                localStringBuilder.append("/");
        }
        localStringBuilder.append(paramString1).append("?returnUrl=").append(paramString3);
        return localStringBuilder.toString();
    }

    public static String getLoginUrl(HttpServletRequest paramHttpServletRequest) {
        StringBuffer localStringBuffer = paramHttpServletRequest.getRequestURL();
        Map localMap = paramHttpServletRequest.getParameterMap();
        if ((localMap != null) && (localMap.size() > 0)) {
            localStringBuffer.append("?");
            Iterator localIterator = localMap.keySet().iterator();
            while (localIterator.hasNext()) {
                String str = (String) localIterator.next();
                String[] arrayOfString = (String[]) localMap.get(str);
                for (int i = 0; i < arrayOfString.length; i++)
                    localStringBuffer.append(str).append("=").append(arrayOfString[i]).append("&");
            }
        }
        return getLoginUrl((String) paramHttpServletRequest.getAttribute("_login_url"), "", localStringBuffer.toString());
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.web.front.FrontHelper
 * JD-Core Version:    0.6.2
 */