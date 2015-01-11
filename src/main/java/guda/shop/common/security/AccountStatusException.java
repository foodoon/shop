package guda.shop.common.security;

public class AccountStatusException extends AuthenticationException {
    public AccountStatusException() {
    }

    public AccountStatusException(String paramString) {
        super(paramString);
    }

    public AccountStatusException(String paramString, Object paramObject) {
        super(paramString, paramObject);
    }
}

