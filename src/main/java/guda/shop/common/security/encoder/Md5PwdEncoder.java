package guda.shop.common.security.encoder;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5PwdEncoder
        implements PwdEncoder {
    private String _$1;

    public String encodePassword(String paramString) {
        return encodePassword(paramString, this._$1);
    }

    public String encodePassword(String paramString1, String paramString2) {
        String str = mergePasswordAndSalt(paramString1, paramString2, false);
        MessageDigest localMessageDigest = getMessageDigest();
        byte[] arrayOfByte;
        try {
            arrayOfByte = localMessageDigest.digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            throw new IllegalStateException("UTF-8 not supported!");
        }
        return new String(Hex.encodeHex(arrayOfByte));
    }

    public boolean isPasswordValid(String paramString1, String paramString2) {
        return isPasswordValid(paramString1, paramString2, this._$1);
    }

    public boolean isPasswordValid(String paramString1, String paramString2, String paramString3) {
        if (paramString1 == null)
            return false;
        String str = encodePassword(paramString2, paramString3);
        return paramString1.equals(str);
    }

    protected final MessageDigest getMessageDigest() {
        String str = "MD5";
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
        }
        throw new IllegalArgumentException("No such algorithm [" + str + "]");
    }

    protected String mergePasswordAndSalt(String paramString, Object paramObject, boolean paramBoolean) {
        if (paramString == null)
            paramString = "";
        if ((paramBoolean) && (paramObject != null) && ((paramObject.toString().lastIndexOf("{") != -1) || (paramObject.toString().lastIndexOf("}") != -1)))
            throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
        if ((paramObject == null) || ("".equals(paramObject)))
            return paramString;
        return paramString + "{" + paramObject.toString() + "}";
    }

    public String getDefaultSalt() {
        return this._$1;
    }

    public void setSefaultSalt(String paramString) {
        this._$1 = paramString;
    }
}

