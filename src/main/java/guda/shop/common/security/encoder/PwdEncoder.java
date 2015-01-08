package guda.shop.common.security.encoder;

public abstract interface PwdEncoder {
    public abstract String encodePassword(String paramString);

    public abstract String encodePassword(String paramString1, String paramString2);

    public abstract boolean isPasswordValid(String paramString1, String paramString2);

    public abstract boolean isPasswordValid(String paramString1, String paramString2, String paramString3);
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.encoder.PwdEncoder
 * JD-Core Version:    0.6.2
 */