package guda.shop.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public static final String UTF8 = "UTF-8";
    private String[] _$2;
    private String[] _$1;

    public XssHttpServletRequestWrapper(HttpServletRequest paramHttpServletRequest, String paramString1, String paramString2, String paramString3) {
        super(paramHttpServletRequest);
        if ((paramString1 != null) && (paramString1.length() > 0))
            this._$2 = paramString1.split(paramString3);
        if ((paramString2 != null) && (paramString2.length() > 0))
            this._$1 = paramString2.split(paramString3);
    }

    public String getQueryString() {
        String str = super.getQueryString();
        if (str != null)
            str = _$1(str);
        return str;
    }

    public String getParameter(String paramString) {
        String str = super.getParameter(_$1(paramString));
        if (str != null)
            str = _$1(str);
        return str;
    }

    public String[] getParameterValues(String paramString) {
        String[] arrayOfString = super.getParameterValues(paramString);
        if ((arrayOfString == null) || (arrayOfString.length == 0))
            return null;
        for (int i = 0; i < arrayOfString.length; i++)
            arrayOfString[i] = _$1(arrayOfString[i]);
        return arrayOfString;
    }

    public String getHeader(String paramString) {
        String str = super.getHeader(_$1(paramString));
        if (str != null)
            str = _$1(str);
        return str;
    }

    private String _$1(String paramString) {
        if ((paramString == null) || (paramString.equals("")))
            return paramString;
        try {
            paramString = URLDecoder.decode(paramString, "UTF-8");
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            localUnsupportedEncodingException.printStackTrace();
        } catch (Exception localException) {
        }
        for (int i = 0; i < this._$2.length; i++)
            if (paramString.contains(this._$2[i]))
                paramString = paramString.replace(this._$2[i], this._$1[i]);
        return paramString;
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.XssHttpServletRequestWrapper
 * JD-Core Version:    0.6.2
 */