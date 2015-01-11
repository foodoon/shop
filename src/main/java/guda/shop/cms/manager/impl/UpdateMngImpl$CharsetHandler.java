package guda.shop.cms.manager.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

class UpdateMngImpl$CharsetHandler
        implements ResponseHandler<String> {
    private String charset;

    public UpdateMngImpl$CharsetHandler(UpdateMngImpl paramUpdateMngImpl, String charset) {

        this.charset = charset;
    }

    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

        StatusLine statusLine = response.getStatusLine();

        if (statusLine.getStatusCode() >= 300) {

            return null;
        }

        HttpEntity entity = response.getEntity();

        if (entity != null) {

            if (!StringUtils.isBlank(this.charset)) {

                return EntityUtils.toString(entity, this.charset);
            }

            return EntityUtils.toString(entity);
        }


        return null;
    }
}

