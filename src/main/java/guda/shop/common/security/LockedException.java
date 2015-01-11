package guda.shop.common.security;

public class LockedException extends AccountStatusException {
    public LockedException() {
    }

    public LockedException(String paramString) {
        super(paramString);
    }

    public LockedException(String paramString, Object paramObject) {
        super(paramString, paramObject);
    }
}

