package guda.shop.common.security;

public class CredentialsExpiredException extends AccountStatusException
{
  public CredentialsExpiredException()
  {
  }

  public CredentialsExpiredException(String paramString)
  {
    super(paramString);
  }

  public CredentialsExpiredException(String paramString, Object paramObject)
  {
    super(paramString, paramObject);
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.CredentialsExpiredException
 * JD-Core Version:    0.6.2
 */