package guda.shop.common.security;

public class BadCredentialsException extends AuthenticationException {
    public BadCredentialsException() {
    }

    public BadCredentialsException(String paramString) {
        super(paramString);
    }
}

