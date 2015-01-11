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

