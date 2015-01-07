package guda.shop.common.security;

public class DisabledException extends AccountStatusException
{
  public DisabledException()
  {
  }

  public DisabledException(String paramString)
  {
    super(paramString);
  }

  public DisabledException(String paramString, Object paramObject)
  {
    super(paramString, paramObject);
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.DisabledException
 * JD-Core Version:    0.6.2
 */