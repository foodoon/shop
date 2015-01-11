package guda.shop.common.security.rememberme;

public class InvalidCookieException extends RememberMeAuthenticationException {
    public InvalidCookieException() {
    }

    public InvalidCookieException(String paramString) {
        super(paramString);
    }
}

