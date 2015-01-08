package guda.shop.common.web;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.Map.Entry;

public class RequestUtils {
    private static final Logger _$1 = LoggerFactory.getLogger(RequestUtils.class);

    public static String getQueryParam(HttpServletRequest paramHttpServletRequest, String paramString) {
        String str1 = paramHttpServletRequest.getQueryString();
        if (StringUtils.isBlank(str1))
            return null;
        try {
            str1 = URLDecoder.decode(str1, "UTF-8");
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            _$1.error("encoding UTF-8 not support?", localUnsupportedEncodingException);
        }
        if (StringUtils.isBlank(str1))
            return null;
        String[] arrayOfString1 = StringUtils.split(str1, "&");
        for (String str2 : arrayOfString1) {
            String[] arrayOfString3 = StringUtils.split(str2, "=");
            int k = arrayOfString3.length;
            if ((k >= 1) && (arrayOfString3[0].equals(paramString))) {
                if (k == 2)
                    return arrayOfString3[1];
                return "";
            }
        }
        return null;
    }

    public static Map<String, String[]> getQueryParams(HttpServletRequest paramHttpServletRequest) {
        Map<String, String[]> localMap;
        String localObject = "";
        if (paramHttpServletRequest.getMethod().equalsIgnoreCase("POST")) {
            localMap = paramHttpServletRequest.getParameterMap();
        } else {
            localObject = paramHttpServletRequest.getQueryString();
            if (StringUtils.isBlank((String) localObject))
                return new HashMap();
            try {
                localObject = URLDecoder.decode((String) localObject, "UTF-8");
            } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
                _$1.error("encoding UTF-8 not support?", localUnsupportedEncodingException);
            }
            localMap = parseQueryString((String) localObject);
        }
        Map<String, String[]> map = new HashMap(localMap.size());
        Iterator<Entry<String, String[]>> localIterator = localMap.entrySet().iterator();
        while (localIterator.hasNext()) {
            Entry<String, String[]> localEntry = localIterator.next();
            int i = (localEntry.getValue()).length;
            if (i == 1)
                map.put(localEntry.getKey(), new String[]{localEntry.getValue()[0]});
            else if (i > 1)
                map.put(localEntry.getKey(), localEntry.getValue());
        }
        return localMap;
    }

    public static Map<String, String[]> parseQueryString(String paramString) {
        String[] arrayOfString1 = null;
        if (paramString == null)
            throw new IllegalArgumentException();
        HashMap localHashMap = new HashMap();
        StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "&");
        while (localStringTokenizer.hasMoreTokens()) {
            String str1 = localStringTokenizer.nextToken();
            int i = str1.indexOf('=');
            if (i != -1) {
                String str2 = str1.substring(0, i);
                String str3 = str1.substring(i + 1, str1.length());
                if (localHashMap.containsKey(str2)) {
                    String[] arrayOfString2 = (String[]) localHashMap.get(str2);
                    arrayOfString1 = new String[arrayOfString2.length + 1];
                    for (int j = 0; j < arrayOfString2.length; j++)
                        arrayOfString1[j] = arrayOfString2[j];
                    arrayOfString1[arrayOfString2.length] = str3;
                } else {
                    arrayOfString1 = new String[1];
                    arrayOfString1[0] = str3;
                }
                localHashMap.put(str2, arrayOfString1);
            }
        }
        return localHashMap;
    }

    public static String getLocation(HttpServletRequest paramHttpServletRequest) {
        UrlPathHelper localUrlPathHelper = new UrlPathHelper();
        StringBuffer localStringBuffer = paramHttpServletRequest.getRequestURL();
        String str1 = paramHttpServletRequest.getRequestURI();
        String str2 = localUrlPathHelper.getOriginatingRequestUri(paramHttpServletRequest);
        localStringBuffer.replace(localStringBuffer.length() - str1.length(), localStringBuffer.length(), str2);
        String str3 = localUrlPathHelper.getOriginatingQueryString(paramHttpServletRequest);
        if (str3 != null)
            localStringBuffer.append("?").append(str3);
        return localStringBuffer.toString();
    }

    public static Map<String, String> getRequestMap(HttpServletRequest paramHttpServletRequest, String paramString) {
        return _$1(paramHttpServletRequest, paramString, false);
    }

    public static String getIpAddr(HttpServletRequest paramHttpServletRequest) {
        String str = paramHttpServletRequest.getHeader("X-Real-IP");
        if ((!StringUtils.isBlank(str)) && (!"unknown".equalsIgnoreCase(str)))
            return str;
        str = paramHttpServletRequest.getHeader("X-Forwarded-For");
        if ((!StringUtils.isBlank(str)) && (!"unknown".equalsIgnoreCase(str))) {
            int i = str.indexOf(',');
            if (i != -1)
                return str.substring(0, i);
            return str;
        }
        return paramHttpServletRequest.getRemoteAddr();
    }

    private static Map<String, String> _$1(HttpServletRequest paramHttpServletRequest, String paramString, boolean paramBoolean) {
        HashMap localHashMap = new HashMap();
        Enumeration localEnumeration = paramHttpServletRequest.getParameterNames();
        while (localEnumeration.hasMoreElements()) {
            String str1 = (String) localEnumeration.nextElement();
            if (str1.startsWith(paramString)) {
                String str2 = paramBoolean ? str1 : str1.substring(paramString.length());
                String str3 = StringUtils.join(paramHttpServletRequest.getParameterValues(str1), ',');
                localHashMap.put(str2, str3);
            }
        }
        return localHashMap;
    }

    public static void main(String[] paramArrayOfString) {
        System.out.println(StringUtils.split("", "=").length);
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.RequestUtils
 * JD-Core Version:    0.6.2
 */