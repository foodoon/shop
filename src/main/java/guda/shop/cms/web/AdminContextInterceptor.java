package guda.shop.cms.web;

import guda.shop.cms.entity.ShopAdmin;
iimport guda.shop.ms.manager.ShopAdminMng;
imimport guda.shop.s.service.LoginSvc;
impimport guda.shop..web.threadvariable.AdminThread;
impoimport guda.shop.on.web.springmvc.MessageResolver;
imporimport guda.shop.entity.Website;
import com.jspgou.core.web.SiteUtils;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

public class AdminContextInterceptor extends HandlerInterceptorAdapter
{

  @Autowired
  private LoginSvc _$3;

  @Autowired
  private ShopAdminMng _$2;
  private Long _$1;

  public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject)
    throws Exception
  {
    Website localWebsite = SiteUtils.getWeb(paramHttpServletRequest);
    ShopAdmin localShopAdmin;
    if (this._$1 != null)
    {
      localShopAdmin = this._$2.findById(this._$1);
      if (localShopAdmin == null)
        throw new IllegalStateException("developAdminId not found: " + this._$1);
      localObject = localShopAdmin.getWebsite().getId();
      if (!((Long)localObject).equals(localWebsite.getId()))
        throw new IllegalStateException("developAdminId's website id=" + localObject + " not in current website id=" + localWebsite.getId());
    }
    else
    {
      localShopAdmin = this._$3.getAdmin(paramHttpServletRequest, paramHttpServletResponse, localWebsite);
    }
    Object localObject = _$1(paramHttpServletRequest);
    if (localShopAdmin != null)
    {
      boolean bool = localShopAdmin.getViewonlyAdmin().booleanValue();
      AdminThread.set(localShopAdmin);
      Set localSet = localShopAdmin.getPerms();
      paramHttpServletRequest.setAttribute("_permission_key", localSet);
      if ((bool) && (_$1((String)localObject)))
      {
        paramHttpServletRequest.setAttribute("message", MessageResolver.getMessage(paramHttpServletRequest, "login.notPermission", new Object[0]));
        paramHttpServletResponse.sendError(403);
        return false;
      }
    }
    return true;
  }

  public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, ModelAndView paramModelAndView)
    throws Exception
  {
    ShopAdmin localShopAdmin = AdminThread.get();
    if ((localShopAdmin != null) && (paramModelAndView != null) && (paramModelAndView.getModelMap() != null) && (paramModelAndView.getViewName() != null) && (!paramModelAndView.getViewName().startsWith("redirect:")))
      paramModelAndView.getModelMap().addAttribute("_permission_key", localShopAdmin.getPerms());
  }

  public void afterCompletion(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException)
    throws Exception
  {
    AdminThread.remove();
  }

  private boolean _$1(String paramString)
  {
    String str = null;
    int i = paramString.lastIndexOf("/");
    if (i == -1)
      throw new RuntimeException("uri must start width '/':" + paramString);
    str = paramString.substring(i + 1);
    return str.startsWith("o_");
  }

  private static String _$1(HttpServletRequest paramHttpServletRequest)
    throws IllegalStateException
  {
    UrlPathHelper localUrlPathHelper = new UrlPathHelper();
    String str1 = localUrlPathHelper.getOriginatingRequestUri(paramHttpServletRequest);
    String str2 = localUrlPathHelper.getOriginatingContextPath(paramHttpServletRequest);
    int i = 0;
    int j = 0;
    int k = 2;
    if (!StringUtils.isBlank(str2))
      k++;
    while ((j < k) && (i != -1))
    {
      i = str1.indexOf('/', i + 1);
      j++;
    }
    if (i <= 0)
      throw new IllegalStateException("admin access path not like '/jeeadmin/jspgou/...' pattern: " + str1);
    return str1.substring(i);
  }

  public void setDevelopAdminId(Long paramLong)
  {
    this._$1 = paramLong;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.AdminContextInterceptor
 * JD-Core Version:    0.6.2
 */