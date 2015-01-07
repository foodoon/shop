package guda.shop.common.security.rememberme;

import guda.shop.common.security.userdetails.UserDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface RememberMeService
{
  public abstract UserDetails autoLogin(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws CookieTheftException;

  public abstract void loginFail(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);

  public abstract boolean loginSuccess(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, UserDetails paramUserDetails);

  public abstract void logout(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.rememberme.RememberMeService
 * JD-Core Version:    0.6.2
 */