package guda.shop.common.security.rememberme;

import guda.shop.common.security.AuthenticationException;

public class RememberMeAuthenticationException extends AuthenticationException {
    public RememberMeAuthenticationException() {
    }

    public RememberMeAuthenticationException(String paramString) {
        super(paramString);
    }
}

