package guda.shop.common.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static final String COOKIE_PAGE_SIZE = "_cookie_page_size";
    public static final int DEFAULT_SIZE = 20;
    public static final int MAX_SIZE = 200;

    public static int getPageSize(HttpServletRequest paramHttpServletRequest) {
        Assert.notNull(paramHttpServletRequest);
        Cookie localCookie = getCookie(paramHttpServletRequest, "_cookie_page_size");
        int i = 0;
        if (localCookie != null)
            try {
                i = Integer.parseInt(localCookie.getValue());
            } catch (Exception localException) {
            }
        if (i <= 0)
            i = 20;
        else if (i > 200)
            i = 200;
        return i;
    }

    public static Cookie getCookie(HttpServletRequest paramHttpServletRequest, String paramString) {
        Assert.notNull(paramHttpServletRequest);
        Cookie[] arrayOfCookie1 = paramHttpServletRequest.getCookies();
        if ((arrayOfCookie1 != null) && (arrayOfCookie1.length > 0))
            for (Cookie localCookie : arrayOfCookie1)
                if (localCookie.getName().equals(paramString))
                    return localCookie;
        return null;
    }

    public static void cancleCookie(HttpServletResponse paramHttpServletResponse, String paramString1, String paramString2) {
        Cookie localCookie = new Cookie(paramString1, null);
        localCookie.setMaxAge(0);
        localCookie.setPath("/");
        if (!StringUtils.isBlank(paramString2))
            localCookie.setDomain(paramString2);
        paramHttpServletResponse.addCookie(localCookie);
    }
}

