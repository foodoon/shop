package guda.shop.cms;

import guda.shop.common.httpClient.HttpProtocolHandler;
import guda.shop.common.httpClient.HttpRequest;
import guda.shop.common.httpClient.HttpResponse;
import guda.shop.common.httpClient.HttpResultType;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.NameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

public class Alipay {
    private static final String _$2 = "https://mapi.alipay.com/gateway.do?";
    private static final String _$1 = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

    public static String buildRequestMysign(Map<String, String> paramMap, String paramString) {
        String str1 = createLinkString(paramMap);
        String str2 = "";
        str2 = sign(str1, paramString, "utf-8");
        return str2;
    }

    private static Map<String, String> _$1(Map<String, String> paramMap, String paramString) {
        Map localMap = paraFilter(paramMap);
        String str = buildRequestMysign(localMap, paramString);
        localMap.put("sign", str);
        localMap.put("sign_type", "MD5");
        return localMap;
    }

    public static String buildRequest(Map<String, String> paramMap, String paramString1, String paramString2, String paramString3) {
        Map localMap = _$1(paramMap, paramString1);
        ArrayList localArrayList = new ArrayList(localMap.keySet());
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"https://mapi.alipay.com/gateway.do?_input_charset=utf-8\" method=\"" + paramString2 + "\">");
        for (int i = 0; i < localArrayList.size(); i++) {
            String str1 = (String) localArrayList.get(i);
            String str2 = (String) localMap.get(str1);
            localStringBuffer.append("<input type=\"hidden\" name=\"" + str1 + "\" value=\"" + str2 + "\"/>");
        }
        localStringBuffer.append("<input type=\"submit\" value=\"" + paramString3 + "\" style=\"display:none;\"></form>");
        localStringBuffer.append("<script>document.forms['alipaysubmit'].submit();</script>");
        return localStringBuffer.toString();
    }

    public static String buildRequest(String paramString1, String paramString2, Map<String, String> paramMap, String paramString3)
            throws Exception {
        Map localMap = _$1(paramMap, paramString3);
        HttpProtocolHandler localHttpProtocolHandler = HttpProtocolHandler.getInstance();
        HttpRequest localHttpRequest = new HttpRequest(HttpResultType.BYTES);
        localHttpRequest.setCharset("utf-8");
        localHttpRequest.setParameters(_$1(localMap));
        localHttpRequest.setUrl("https://mapi.alipay.com/gateway.do?_input_charset=utf-8");
        HttpResponse localHttpResponse = localHttpProtocolHandler.execute(localHttpRequest, paramString1, paramString2);
        if (localHttpResponse == null)
            return null;
        String str = localHttpResponse.getStringResult();
        return str;
    }

    private static NameValuePair[] _$1(Map<String, String> paramMap) {
        NameValuePair[] arrayOfNameValuePair = new NameValuePair[paramMap.size()];
        int i = 0;
        Iterator localIterator = paramMap.entrySet().iterator();
        while (localIterator.hasNext()) {
            Entry localEntry = (Entry) localIterator.next();
            arrayOfNameValuePair[(i++)] = new NameValuePair((String) localEntry.getKey(), (String) localEntry.getValue());
        }
        return arrayOfNameValuePair;
    }

    public static boolean verify(Map<String, String> paramMap, String paramString1, String paramString2) {
        String str1 = "true";
        if (paramMap.get("notify_id") != null) {
            str2 = (String) paramMap.get("notify_id");
            str1 = _$2(str2, paramString1);
        }
        String str2 = "";
        if (paramMap.get("sign") != null)
            str2 = (String) paramMap.get("sign");
        boolean bool = _$1(paramMap, str2, paramString2);
        return (bool) && (str1.equals("true"));
    }

    private static boolean _$1(Map<String, String> paramMap, String paramString1, String paramString2) {
        Map localMap = paraFilter(paramMap);
        String str = createLinkString(localMap);
        boolean bool = false;
        bool = verify(str, paramString1, paramString2, "utf-8");
        return bool;
    }

    private static String _$2(String paramString1, String paramString2) {
        String str = "https://mapi.alipay.com/gateway.do?service=notify_verify&partner=" + paramString2 + "&notify_id=" + paramString1;
        return _$1(str);
    }

    private static String _$1(String paramString) {
        String str = "";
        try {
            URL localURL = new URL(paramString);
            HttpURLConnection localHttpURLConnection = (HttpURLConnection) localURL.openConnection();
            BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localHttpURLConnection.getInputStream()));
            str = localBufferedReader.readLine().toString();
        } catch (Exception localException) {
            localException.printStackTrace();
            str = "";
        }
        return str;
    }

    public static Map<String, String> paraFilter(Map<String, String> paramMap) {
        HashMap localHashMap = new HashMap();
        if ((paramMap == null) || (paramMap.size() <= 0))
            return localHashMap;
        Iterator localIterator = paramMap.keySet().iterator();
        while (localIterator.hasNext()) {
            String str1 = (String) localIterator.next();
            String str2 = (String) paramMap.get(str1);
            if ((str2 != null) && (!str2.equals("")) && (!str1.equalsIgnoreCase("sign")) && (!str1.equalsIgnoreCase("sign_type")))
                localHashMap.put(str1, str2);
        }
        return localHashMap;
    }

    public static String createLinkString(Map<String, String> paramMap) {
        ArrayList localArrayList = new ArrayList(paramMap.keySet());
        Collections.sort(localArrayList);
        String str1 = "";
        for (int i = 0; i < localArrayList.size(); i++) {
            String str2 = (String) localArrayList.get(i);
            String str3 = (String) paramMap.get(str2);
            if (i == localArrayList.size() - 1)
                str1 = str1 + str2 + "=" + str3;
            else
                str1 = str1 + str2 + "=" + str3 + "&";
        }
        return str1;
    }

    public static String sign(String paramString1, String paramString2, String paramString3) {
        paramString1 = paramString1 + paramString2;
        return DigestUtils.md5Hex(_$1(paramString1, paramString3));
    }

    public static boolean verify(String paramString1, String paramString2, String paramString3, String paramString4) {
        paramString1 = paramString1 + paramString3;
        String str = DigestUtils.md5Hex(_$1(paramString1, paramString4));
        return str.equals(paramString2);
    }

    private static byte[] _$1(String paramString1, String paramString2) {
        if ((paramString2 == null) || ("".equals(paramString2)))
            return paramString1.getBytes();
        try {
            return paramString1.getBytes(paramString2);
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        }
        throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + paramString2);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.Alipay
 * JD-Core Version:    0.6.2
 */