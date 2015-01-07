package guda.shop.cms.service;

import guda.shop.cms.entity.ShopAdmin;
import guda.shop.cms.entity.ShopMember;
import guda.shop.common.security.BadCredentialsException;
import guda.shop.common.security.UserNotAcitveException;
import guda.shop.common.security.UsernameNotFoundException;
import guda.shop.core.entity.Website;
import guda.shop.core.security.UserNotInWebsiteException;
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