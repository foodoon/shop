package guda.shop.cms.manager.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

class ResponseHandlerMngImpl
        implements ResponseHandler<String> {
    private String _$1;

    public ResponseHandlerMngImpl(UpdateMngImpl paramUpdateMngImpl, String paramString) {
        this._$1 = paramString;
    }

    public String handleResponse(HttpResponse paramHttpResponse)
            throws ClientProtocolException, IOException {
        StatusLine localStatusLine = paramHttpResponse.getStatusLine();
        if (localStatusLine.getStatusCode() >= 300)
            return null;
        HttpEntity localHttpEntity = paramHttpResponse.getEntity();
        if (localHttpEntity != null) {
            if (!StringUtils.isBlank(this._$1))
                return EntityUtils.toString(localHttpEntity, this._$1);
            return EntityUtils.toString(localHttpEntity);
        }
        return null;
    }
}

