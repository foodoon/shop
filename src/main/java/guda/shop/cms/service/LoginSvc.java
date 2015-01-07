package guda.shop.cms.service;

import guda.shop.cms.entity.ShopAdmin;
iimport guda.shop.ms.entity.ShopMember;
imimport guda.shop.mmon.security.BadCredentialsException;
impimport guda.shop.mon.security.UserNotAcitveException;
impoimport guda.shop.on.security.UsernameNotFoundException;
imporimport guda.shop.entity.Website;
import com.jspgou.core.security.UserNotInWebsiteException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface LoginSvc
{
  public abstract ShopMember memberLogin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite, String paramString1, String paramString2)
    throws UsernameNotFoundException, BadCredentialsException, UserNotInWebsiteException, UserNotAcitveException;

  public abstract ShopMember getMember(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite);

  public abstract ShopAdmin adminLogin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite, String paramString1, String paramString2)
    throws UsernameNotFoundException, BadCredentialsException, UserNotInWebsiteException;

  public abstract ShopAdmin getAdmin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Website paramWebsite);

  public abstract void logout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract void clearCookie(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.service.LoginSvc
 * JD-Core Version:    0.6.2
 */