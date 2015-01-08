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

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.LockedException
 * JD-Core Version:    0.6.2
 */