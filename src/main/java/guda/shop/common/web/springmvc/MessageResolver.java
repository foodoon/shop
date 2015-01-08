package guda.shop.common.web.springmvc;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public final class MessageResolver {
    public static String getMessage(HttpServletRequest paramHttpServletRequest, String paramString, Object[] paramArrayOfObject) {
        WebApplicationContext localWebApplicationContext = RequestContextUtils.getWebApplicationContext(paramHttpServletRequest);
        if (localWebApplicationContext == null)
            throw new IllegalStateException("WebApplicationContext not found!");
        LocaleResolver localLocaleResolver = RequestContextUtils.getLocaleResolver(paramHttpServletRequest);
        Locale localLocale;
        if (localLocaleResolver != null)
            localLocale = localLocaleResolver.resolveLocale(paramHttpServletRequest);
        else
            localLocale = paramHttpServletRequest.getLocale();
        return localWebApplicationContext.getMessage(paramString, paramArrayOfObject, localLocale);
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.springmvc.MessageResolver
 * JD-Core Version:    0.6.2
 */