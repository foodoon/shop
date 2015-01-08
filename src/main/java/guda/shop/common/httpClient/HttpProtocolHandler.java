package guda.shop.common.httpClient;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class HttpProtocolHandler {
    private static final long _$3 = 3000L;
    private static String _$9 = "GBK";
    private static HttpProtocolHandler _$1 = new HttpProtocolHandler();
    private int _$8 = 8000;
    private int _$7 = 30000;
    private int _$6 = 60000;
    private int _$5 = 30;
    private int _$4 = 80;
    private HttpConnectionManager _$2 = new MultiThreadedHttpConnectionManager();

    private HttpProtocolHandler() {
        this._$2.getParams().setDefaultMaxConnectionsPerHost(this._$5);
        this._$2.getParams().setMaxTotalConnections(this._$4);
        IdleConnectionTimeoutThread localIdleConnectionTimeoutThread = new IdleConnectionTimeoutThread();
        localIdleConnectionTimeoutThread.addConnectionManager(this._$2);
        localIdleConnectionTimeoutThread.setConnectionTimeout(this._$6);
        localIdleConnectionTimeoutThread.start();
    }

    public static HttpProtocolHandler getInstance() {
        return _$1;
    }

    public HttpResponse execute(HttpRequest paramHttpRequest, String paramString1, String paramString2)
            throws HttpException, IOException {
        HttpClient localHttpClient = new HttpClient(this._$2);
        int i = this._$8;
        if (paramHttpRequest.getConnectionTimeout() > 0)
            i = paramHttpRequest.getConnectionTimeout();
        localHttpClient.getHttpConnectionManager().getParams().setConnectionTimeout(i);
        int j = this._$7;
        if (paramHttpRequest.getTimeout() > 0)
            j = paramHttpRequest.getTimeout();
        localHttpClient.getHttpConnectionManager().getParams().setSoTimeout(j);
        localHttpClient.getParams().setConnectionManagerTimeout(3000L);
        String str = paramHttpRequest.getCharset();
        str = str == null ? _$9 : str;
        Object localObject1 = null;
        if (paramHttpRequest.getMethod().equals("GET")) {
            localObject1 = new GetMethod(paramHttpRequest.getUrl());
            ((HttpMethod) localObject1).getParams().setCredentialCharset(str);
            ((HttpMethod) localObject1).setQueryString(paramHttpRequest.getQueryString());
        } else if ((paramString1.equals("")) && (paramString2.equals(""))) {
            localObject1 = new PostMethod(paramHttpRequest.getUrl());
            ((PostMethod) localObject1).addParameters(paramHttpRequest.getParameters());
            ((HttpMethod) localObject1).addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + str);
        } else {
            localObject1 = new PostMethod(paramHttpRequest.getUrl());
            List localObject2 = new ArrayList();
            for (int k = 0; k < paramHttpRequest.getParameters().length; k++)
                ((List) localObject2).add(new StringPart(paramHttpRequest.getParameters()[k].getName(), paramHttpRequest.getParameters()[k].getValue(), str));
            ((List) localObject2).add(new FilePart(paramString1, new FilePartSource(new File(paramString2))));
            ((PostMethod) localObject1).setRequestEntity(new MultipartRequestEntity((Part[]) ((List) localObject2).toArray(new Part[0]), new HttpMethodParams()));
        }
        ((HttpMethod) localObject1).addRequestHeader("User-Agent", "Mozilla/4.0");
        HttpResponse localObject2 = new HttpResponse();
        try {
            localHttpClient.executeMethod((HttpMethod) localObject1);
            if (paramHttpRequest.getResultType().equals(HttpResultType.STRING))
                (localObject2).setStringResult(((HttpMethod) localObject1).getResponseBodyAsString());
            else if (paramHttpRequest.getResultType().equals(HttpResultType.BYTES))
                (localObject2).setByteResult(((HttpMethod) localObject1).getResponseBody());
            (localObject2).setResponseHeaders(((HttpMethod) localObject1).getResponseHeaders());
        } catch (UnknownHostException localUnknownHostException) {

            return null;
        } catch (IOException localIOException) {
            return null;
        } catch (Exception localException) {
            return null;
        } finally {
            ((HttpMethod) localObject1).releaseConnection();
        }
        return localObject2;
    }

    protected String toString(NameValuePair[] paramArrayOfNameValuePair) {
        if ((paramArrayOfNameValuePair == null) || (paramArrayOfNameValuePair.length == 0))
            return "null";
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < paramArrayOfNameValuePair.length; i++) {
            NameValuePair localNameValuePair = paramArrayOfNameValuePair[i];
            if (i == 0)
                localStringBuffer.append(localNameValuePair.getName() + "=" + localNameValuePair.getValue());
            else
                localStringBuffer.append("&" + localNameValuePair.getName() + "=" + localNameValuePair.getValue());
        }
        return localStringBuffer.toString();
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.httpClient.HttpProtocolHandler
 * JD-Core Version:    0.6.2
 */