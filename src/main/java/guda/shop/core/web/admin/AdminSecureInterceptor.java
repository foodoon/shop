package guda.shop.core.web.admin;

import guda.shop.cms.entity.ShopAdmin;
iimport guda.shop.ms.web.threadvariable.AdminThread;
import guda.shop.core.entity.Admin;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminSecureInterceptor extends HandlerInterceptorAdapter
{
  private boolean _$1 = false;

  public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject)
    throws Exception
  {
    ShopAdmin localShopAdmin = AdminThread.get();
    if (this._$1)
      return true;
    Set localSet = (Set)paramHttpServletRequest.getAttribute("_permission_key");
    String str1 = getURI(paramHttpServletRequest.getRequestURI(), paramHttpServletRequest.getContextPath());
    if (str1.equals("/index.do"))
      return true;
    if ((localShopAdmin != null) && (localShopAdmin.getAdmin().isSuper()))
      return true;
    if (localSet == null)
    {
      _$1(paramHttpServletRequest, paramHttpServletResponse);
      return false;
    }
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      if ((str1.equals(str2)) || (str1.startsWith(str2)))
        return true;
    }
    paramHttpServletResponse.sendError(403);
    return false;
  }

  public static String getURI(String paramString1, String paramString2)
    throws IllegalStateException
  {
    int i = 0;
    int j = 0;
    int k = 2;
    if (!StringUtils.isBlank(paramString2))
      k++;
    while ((j < k) && (i != -1))
    {
      i = paramString1.indexOf('/', i + 1);
      j++;
    }
    if (i <= 0)
      throw new IllegalStateException("admin access path not like '/jeeadmin/jspgou/...' pattern: " + paramString1);
    return paramString1.substring(i);
  }

  private void _$1(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws IOException
  {
    String str = paramHttpServletRequest.getRequestURI();
    int i = StringUtils.countMatches(str, "/");
    StringBuilder localStringBuilder = new StringBuilder();
    int j = 3;
    if (!StringUtils.isBlank(paramHttpServletRequest.getContextPath()))
      j++;
    while (j < i)
    {
      localStringBuilder.append("../");
      j++;
    }
    localStringBuilder.append("index.do");
    paramHttpServletResponse.sendRedirect(localStringBuilder.toString());
  }

  public void setDevelop(boolean paramBoolean)
  {
    this._$1 = paramBoolean;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.web.admin.AdminSecureInterceptor
 * JD-Core Version:    0.6.2
 */