package guda.shop.core.security;

import guda.shop.common.security.AuthenticationException;

public class UserNotInWebsiteException extends AuthenticationException
{
  private static final long serialVersionUID = 1L;

  public UserNotInWebsiteException()
  {
  }

  public UserNotInWebsiteException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.security.UserNotInWebsiteException
 * JD-Core Version:    0.6.2
 */