package guda.shop.common.security;

public class AuthenticationException extends Exception
{
  private Object _$1;

  public AuthenticationException()
  {
  }

  public AuthenticationException(String paramString)
  {
    super(paramString);
  }

  public AuthenticationException(String paramString, Object paramObject)
  {
    super(paramString);
    this._$1 = paramObject;
  }

  public Object getExtraInformation()
  {
    return this._$1;
  }

  public void clearExtraInformation()
  {
    this._$1 = null;
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.AuthenticationException
 * JD-Core Version:    0.6.2
 */