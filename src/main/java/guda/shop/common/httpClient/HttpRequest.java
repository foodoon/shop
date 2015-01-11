package guda.shop.common.httpClient;

import org.apache.commons.httpclient.NameValuePair;

public class HttpRequest {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    private String _$9 = null;
    private String _$8 = "POST";
    private int _$7 = 0;
    private int _$6 = 0;
    private NameValuePair[] _$5 = null;
    private String _$4 = null;
    private String _$3 = "GBK";
    private String _$2;
    private HttpResultType _$1 = HttpResultType.BYTES;

    public HttpRequest(HttpResultType paramHttpResultType) {
        this._$1 = paramHttpResultType;
    }

    public String getClientIp() {
        return this._$2;
    }

    public void setClientIp(String paramString) {
        this._$2 = paramString;
    }

    public NameValuePair[] getParameters() {
        return this._$5;
    }

    public void setParameters(NameValuePair[] paramArrayOfNameValuePair) {
        this._$5 = paramArrayOfNameValuePair;
    }

    public String getQueryString() {
        return this._$4;
    }

    public void setQueryString(String paramString) {
        this._$4 = paramString;
    }

    public String getUrl() {
        return this._$9;
    }

    public void setUrl(String paramString) {
        this._$9 = paramString;
    }

    public String getMethod() {
        return this._$8;
    }

    public void setMethod(String paramString) {
        this._$8 = paramString;
    }

    public int getConnectionTimeout() {
        return this._$6;
    }

    public void setConnectionTimeout(int paramInt) {
        this._$6 = paramInt;
    }

    public int getTimeout() {
        return this._$7;
    }

    public void setTimeout(int paramInt) {
        this._$7 = paramInt;
    }

    public String getCharset() {
        return this._$3;
    }

    public void setCharset(String paramString) {
        this._$3 = paramString;
    }

    public HttpResultType getResultType() {
        return this._$1;
    }

    public void setResultType(HttpResultType paramHttpResultType) {
        this._$1 = paramHttpResultType;
    }
}

