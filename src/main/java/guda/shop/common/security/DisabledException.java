package guda.shop.common.security;

public class DisabledException extends AccountStatusException {
    public DisabledException() {
    }

    public DisabledException(String paramString) {
        super(paramString);
    }

    public DisabledException(String paramString, Object paramObject) {
        super(paramString, paramObject);
    }
}

