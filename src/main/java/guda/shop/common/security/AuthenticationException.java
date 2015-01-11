package guda.shop.common.security;

public class AuthenticationException extends Exception {
    private Object _$1;

    public AuthenticationException() {
    }

    public AuthenticationException(String paramString) {
        super(paramString);
    }

    public AuthenticationException(String paramString, Object paramObject) {
        super(paramString);
        this._$1 = paramObject;
    }

    public Object getExtraInformation() {
        return this._$1;
    }

    public void clearExtraInformation() {
        this._$1 = null;
    }
}

