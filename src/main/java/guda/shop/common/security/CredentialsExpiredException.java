package guda.shop.common.security;

public class CredentialsExpiredException extends AccountStatusException {
    public CredentialsExpiredException() {
    }

    public CredentialsExpiredException(String paramString) {
        super(paramString);
    }

    public CredentialsExpiredException(String paramString, Object paramObject) {
        super(paramString, paramObject);
    }
}

