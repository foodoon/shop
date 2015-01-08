package guda.shop.common.security;

public class AccountExpiredException extends AccountStatusException {
    public AccountExpiredException() {
    }

    public AccountExpiredException(String paramString) {
        super(paramString);
    }

    public AccountExpiredException(String paramString, Object paramObject) {
        super(paramString, paramObject);
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.AccountExpiredException
 * JD-Core Version:    0.6.2
 */