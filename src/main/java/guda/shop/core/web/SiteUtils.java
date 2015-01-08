package guda.shop.core.web;

import guda.shop.core.entity.Website;

import javax.servlet.http.HttpServletRequest;

public abstract class SiteUtils {
    public static Website getWeb(HttpServletRequest paramHttpServletRequest) {
        Website localWebsite = (Website) paramHttpServletRequest.getAttribute("_web_key");
        if (localWebsite == null)
            throw new IllegalStateException("Webiste not found in Request Attribute '_web_key'");
        return localWebsite;
    }

    public static Long getWebId(HttpServletRequest paramHttpServletRequest) {
        return getWeb(paramHttpServletRequest).getId();
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.web.SiteUtils
 * JD-Core Version:    0.6.2
 */