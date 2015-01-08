package guda.shop.core.web.front;

import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class FrontLocaleInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject)
            throws ServletException {
        LocaleResolver localLocaleResolver = RequestContextUtils.getLocaleResolver(paramHttpServletRequest);
        if (localLocaleResolver == null)
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        Website localWebsite = SiteUtils.getWeb(paramHttpServletRequest);
        String str = localWebsite.getLocaleFront();
        LocaleEditor localLocaleEditor = new LocaleEditor();
        localLocaleEditor.setAsText(str);
        localLocaleResolver.setLocale(paramHttpServletRequest, paramHttpServletResponse, (Locale) localLocaleEditor.getValue());
        return true;
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.web.front.FrontLocaleInterceptor
 * JD-Core Version:    0.6.2
 */