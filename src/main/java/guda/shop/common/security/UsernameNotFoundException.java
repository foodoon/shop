package guda.shop.common.security;

public class UsernameNotFoundException extends AuthenticationException {
    public UsernameNotFoundException() {
    }

    public UsernameNotFoundException(String paramString) {
        super(paramString);
    }
}

