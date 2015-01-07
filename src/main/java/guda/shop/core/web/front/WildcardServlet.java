package guda.shop.core.web.front;

import guda.shop.core.entity.Website;
import guda.shop.core.manager.WebsiteMng;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WildcardServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  public static String DYNAMIC = "jeedynamic";
  public static String URL = "_dynamic_url";
  public static String URI_INFO = "_dynamic_uri_info";
  public static String QUERY_STRING = "_dynamic_query_string";
  private String _$2 = DYNAMIC;
  private WebsiteMng _$1;

  protected void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    String str1 = paramHttpServletRequest.getServerName();
    Website localWebsite = this._$1.getWebsite(str1);
    if (localWebsite != null)
    {
      String str2 = paramHttpServletRequest.getRequestURL().toString();
      paramHttpServletRequest.setAttribute(URL, str2);
      paramHttpServletRequest.setAttribute(URI_INFO, URLHelper.getURLInfo(str2, paramHttpServletRequest.getContextPath()));
      paramHttpServletRequest.setAttribute(QUERY_STRING, paramHttpServletRequest.getQueryString());
      String str3 = "/" + this._$2;
      RequestDispatcher localRequestDispatcher = paramHttpServletRequest.getRequestDispatcher(str3);
      localRequestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
    }
    else
    {
      throw new IllegalStateException("no website found");
    }
  }

  public void init()
    throws ServletException
  {
    WebApplicationContext localWebApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    this._$1 = ((WebsiteMng)BeanFactoryUtils.beanOfTypeIncludingAncestors(localWebApplicationContext, WebsiteMng.class, true, false));
    String str = getServletConfig().getInitParameter("dynamic");
    if (!StringUtils.isBlank(str))
      this._$2 = str;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.web.front.WildcardServlet
 * JD-Core Version:    0.6.2
 */