package guda.shop.core.web.admin;

import guda.shop.core.entity.Website;
import guda.shop.core.web.SiteUtils;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

public class AdminLocaleInterceptor extends HandlerInterceptorAdapter
{
  public static final String LOCALE = "locale";

  public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject)
    throws ServletException
  {
    LocaleResolver localLocaleResolver = RequestContextUtils.getLocaleResolver(paramHttpServletRequest);
    if (localLocaleResolver == null)
      throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
    Website localWebsite = SiteUtils.getWeb(paramHttpServletRequest);
    String str = localWebsite.getLocaleAdmin();
    LocaleEditor localLocaleEditor = new LocaleEditor();
    localLocaleEditor.setAsText(str);
    localLocaleResolver.setLocale(paramHttpServletRequest, paramHttpServletResponse, (Locale)localLocaleEditor.getValue());
    return true;
  }

  public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, ModelAndView paramModelAndView)
    throws Exception
  {
    LocaleResolver localLocaleResolver = RequestContextUtils.getLocaleResolver(paramHttpServletRequest);
    if (localLocaleResolver == null)
      throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
    if (paramModelAndView != null)
      paramModelAndView.getModelMap().addAttribute("locale", localLocaleResolver.resolveLocale(paramHttpServletRequest).toString());
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.web.admin.AdminLocaleInterceptor
 * JD-Core Version:    0.6.2
 */