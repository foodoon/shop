package guda.shop.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

public class RequestUtils1
{
  private static final Logger _$1 = LoggerFactory.getLogger(RequestUtils1.class);

  public static String getQueryParam(HttpServletRequest paramHttpServletRequest, String paramString)
  {
    if (StringUtils.isBlank(paramString))
      return null;
    if (paramHttpServletRequest.getMethod().equalsIgnoreCase("POST"))
      return paramHttpServletRequest.getParameter(paramString);
    String str = paramHttpServletRequest.getQueryString();
    if (StringUtils.isBlank(str))
      return null;
    try
    {
      str = URLDecoder.decode(str, "UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      _$1.error("encoding UTF-8 not support?", localUnsupportedEncodingException);
    }
    String[] arrayOfString = (String[])parseQueryString(str).get(paramString);
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      return arrayOfString[(arrayOfString.length - 1)];
    return null;
  }

  public static Map<String, Object> getQueryParams(HttpServletRequest paramHttpServletRequest)
  {
    Map localMap;
    if (paramHttpServletRequest.getMethod().equalsIgnoreCase("POST"))
    {
      localMap = paramHttpServletRequest.getParameterMap();
    }
    else
    {
      localObject = paramHttpServletRequest.getQueryString();
      if (StringUtils.isBlank((String)localObject))
        return new HashMap();
      try
      {
        localObject = URLDecoder.decode((String)localObject, "UTF-8");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        _$1.error("encoding UTF-8 not support?", localUnsupportedEncodingException);
      }
      localMap = parseQueryString((String)localObject);
    }
    Object localObject = new HashMap(localMap.size());
    Iterator localIterator = localMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      int i = ((String[])localEntry.getValue()).length;
      if (i == 1)
        ((Map)localObject).put(localEntry.getKey(), ((String[])localEntry.getValue())[0]);
      else if (i > 1)
        ((Map)localObject).put(localEntry.getKey(), localEntry.getValue());
    }
    return localObject;
  }

  public static Map<String, String[]> parseQueryString(String paramString)
  {
    String[] arrayOfString1 = null;
    if (paramString == null)
      throw new IllegalArgumentException();
    HashMap localHashMap = new HashMap();
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "&");
    while (localStringTokenizer.hasMoreTokens())
    {
      String str1 = localStringTokenizer.nextToken();
      int i = str1.indexOf('=');
      if (i != -1)
      {
        String str2 = str1.substring(0, i);
        String str3 = str1.substring(i + 1, str1.length());
        if (localHashMap.containsKey(str2))
        {
          String[] arrayOfString2 = (String[])localHashMap.get(str2);
          arrayOfString1 = new String[arrayOfString2.length + 1];
          for (int j = 0; j < arrayOfString2.length; j++)
            arrayOfString1[j] = arrayOfString2[j];
          arrayOfString1[arrayOfString2.length] = str3;
        }
        else
        {
          arrayOfString1 = new String[1];
          arrayOfString1[0] = str3;
        }
        localHashMap.put(str2, arrayOfString1);
      }
    }
    return localHashMap;
  }

  public static Map<String, String> getRequestMap(HttpServletRequest paramHttpServletRequest, String paramString)
  {
    return _$1(paramHttpServletRequest, paramString, false);
  }

  public static Map<String, String> getRequestMapWithPrefix(HttpServletRequest paramHttpServletRequest, String paramString)
  {
    return _$1(paramHttpServletRequest, paramString, true);
  }

  private static Map<String, String> _$1(HttpServletRequest paramHttpServletRequest, String paramString, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    Enumeration localEnumeration = paramHttpServletRequest.getParameterNames();
    while (localEnumeration.hasMoreElements())
    {
      String str1 = (String)localEnumeration.nextElement();
      if (str1.startsWith(paramString))
      {
        String str2 = paramBoolean ? str1 : str1.substring(paramString.length());
        String str3 = StringUtils.join(paramHttpServletRequest.getParameterValues(str1), ',');
        localHashMap.put(str2, str3);
      }
    }
    return localHashMap;
  }

  public static String getIpAddr(HttpServletRequest paramHttpServletRequest)
  {
    String str = paramHttpServletRequest.getHeader("X-Real-IP");
    if ((!StringUtils.isBlank(str)) && (!"unknown".equalsIgnoreCase(str)))
      return str;
    str = paramHttpServletRequest.getHeader("X-Forwarded-For");
    if ((!StringUtils.isBlank(str)) && (!"unknown".equalsIgnoreCase(str)))
    {
      int i = str.indexOf(',');
      if (i != -1)
        return str.substring(0, i);
      return str;
    }
    return paramHttpServletRequest.getRemoteAddr();
  }

  public static String getLocation(HttpServletRequest paramHttpServletRequest)
  {
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

  public static void main(String[] paramArrayOfString)
  {
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.RequestUtils1
 * JD-Core Version:    0.6.2
 */