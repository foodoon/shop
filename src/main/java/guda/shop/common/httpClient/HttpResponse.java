package guda.shop.common.httpClient;

import org.apache.commons.httpclient.Header;

import java.io.UnsupportedEncodingException;

public class HttpResponse {
    private Header[] _$3;
    private String _$2;
    private byte[] _$1;

    public Header[] getResponseHeaders() {
        return this._$3;
    }

    public void setResponseHeaders(Header[] paramArrayOfHeader) {
        this._$3 = paramArrayOfHeader;
    }

    public byte[] getByteResult() {
        if (this._$1 != null)
            return this._$1;
        if (this._$2 != null)
            return this._$2.getBytes();
        return null;
    }

    public void setByteResult(byte[] paramArrayOfByte) {
        this._$1 = paramArrayOfByte;
    }

    public String getStringResult()
            throws UnsupportedEncodingException {
        if (this._$2 != null)
            return this._$2;
        if (this._$1 != null)
            return new String(this._$1, "utf-8");
        return null;
    }

    public void setStringResult(String paramString) {
        this._$2 = paramString;
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.httpClient.HttpResponse
 * JD-Core Version:    0.6.2
 */