package guda.shop.core.web.front;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.UrlPathHelper;

public class URLHelper
{
  public static final String INDEX = "index";

  public static String removeSuffix(String paramString)
  {
    if (StringUtils.isBlank(paramString))
      return paramString;
    int i = paramString.lastIndexOf("/");
    if (i < 0)
      i = 0;
    return paramString.substring(0, paramString.indexOf(".", i));
  }

  public static boolean isIndex(String paramString)
  {
    return false;
  }

  public static int getPageNo(HttpServletRequest paramHttpServletRequest)
  {
    return getPageNo(_$1(paramHttpServletRequest));
  }

  private static String _$1(HttpServletRequest paramHttpServletRequest)
  {
    UrlPathHelper localUrlPathHelper = new UrlPathHelper();
    String str1 = localUrlPathHelper.getOriginatingRequestUri(paramHttpServletRequest);
    String str2 = localUrlPathHelper.getOriginatingContextPath(paramHttpServletRequest);
    if (!StringUtils.isBlank(str2))
      return str1.substring(str2.length());
    return str1;
  }

  public static PageInfo getPageInfo(HttpServletRequest paramHttpServletRequest)
  {
    UrlPathHelper localUrlPathHelper = new UrlPathHelper();
    String str1 = localUrlPathHelper.getOriginatingRequestUri(paramHttpServletRequest);
    String str2 = localUrlPathHelper.getOriginatingQueryString(paramHttpServletRequest);
    return getPageInfo(str1, str2);
  }

  public static PageInfo getPageInfo(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return null;
    if (!paramString1.startsWith("/"))
      throw new IllegalArgumentException("URI must start width '/'");
    int i = paramString1.indexOf("_");
    int j = paramString1.indexOf("-");
    int k = paramString1.indexOf(".");
    int m = paramString1.lastIndexOf("/") + 1;
    String str1;
    if (!StringUtils.isBlank(paramString2))
      str1 = paramString1 + "?" + paramString2;
    else
      str1 = paramString1;
    String str2;
    if (i != -1)
      str2 = paramString1.substring(m, i);
    else if (j != -1)
      str2 = paramString1.substring(m, j);
    else if (k != -1)
      str2 = paramString1.substring(m, k);
    else
      str2 = paramString1.substring(m);
    String str3;
    if (j != -1)
      str3 = str1.substring(j);
    else if (k != -1)
      str3 = str1.substring(k);
    else
      str3 = str1.substring(paramString1.length());
    String str4 = str1.substring(m);
    return new PageInfo(str4, str2, str3);
  }

  public static int getPageNo(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("URI can not be null");
    if (!paramString.startsWith("/"))
      throw new IllegalArgumentException("URI must start width '/'");
    int i = 1;
    int j = paramString.indexOf("_");
    int k = paramString.indexOf("-");
    int m = paramString.indexOf(".");
    if (j != -1)
    {
      String str;
      if (k != -1)
        str = paramString.substring(j + 1, k);
      else if (m != -1)
        str = paramString.substring(j + 1, m);
      else
        str = paramString.substring(j + 1);
      try
      {
        i = Integer.valueOf(str).intValue();
      }
      catch (Exception localException)
      {
      }
    }
    return i;
  }

  public static URLInfo getURLInfo(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return null;
    int i = 0;
    if (paramString1.startsWith("http://"))
      i += paramString1.indexOf("/", "http://".length());
    if (!StringUtils.isBlank(paramString2))
      i += paramString2.length();
    if (paramString1.startsWith("/", i))
      i++;
    String str1 = null;
    int j = paramString1.indexOf("?");
    int k = paramString1.indexOf(".", i);
    if (j >= 0)
      str1 = paramString1.substring(j + 1);
    String str2;
    if (k >= 0)
      str2 = paramString1.substring(i, k);
    else if (j >= 0)
      str2 = paramString1.substring(i, j);
    else
      str2 = paramString1.substring(i);
    int m = str2.lastIndexOf("/") + 1 + i;
    int n = str2.indexOf("_");
    int i1 = str2.indexOf("-");
    String str3;
    String str6;
    String str5;
    String str4;
    String str7;
    if (n >= 0)
    {
      str3 = str2.substring(0, n);
      str6 = paramString1.substring(m, n + i);
      if (i1 >= 0)
      {
        str5 = str2.substring(i1 + 1);
        str4 = str2.substring(n + 1, i1);
        str7 = paramString1.substring(i1 + i);
      }
      else
      {
        str5 = null;
        str4 = str2.substring(n + 1);
        if (k >= 0)
          str7 = paramString1.substring(k);
        else
          str7 = paramString1.substring(n + i + 1);
      }
    }
    else
    {
      str4 = null;
      if (i1 >= 0)
      {
        str3 = str2.substring(0, i1);
        str5 = str2.substring(i1 + 1);
        str6 = paramString1.substring(m, i1 + i);
        str7 = paramString1.substring(i1 + i);
      }
      else
      {
        str3 = str2;
        str5 = null;
        if (k >= 0)
        {
          str6 = paramString1.substring(m, k);
          str7 = paramString1.substring(k);
        }
        else if (j >= 0)
        {
          str6 = paramString1.substring(m, j);
          str7 = paramString1.substring(j);
        }
        else
        {
          str6 = paramString1.substring(m);
          str7 = "";
        }
      }
    }
    String[] arrayOfString1;
    if (!StringUtils.isBlank(str3))
      arrayOfString1 = str3.split("/");
    else
      arrayOfString1 = new String[0];
    int i2;
    try
    {
      i2 = Integer.parseInt(str4);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      i2 = 1;
    }
    String[] arrayOfString2;
    if (str5 != null)
      arrayOfString2 = str5.split("-");
    else
      arrayOfString2 = new String[0];
    return new URLInfo(arrayOfString1, i2, arrayOfString2, str6, str7, str1);
  }

  public static class PageInfo
  {
    private String _$3;
    private String _$2;
    private String _$1;

    public PageInfo(String paramString1, String paramString2, String paramString3)
    {
      this._$3 = paramString1;
      this._$2 = paramString2;
      this._$1 = paramString3;
    }

    public String getHref()
    {
      return this._$3;
    }

    public void setHref(String paramString)
    {
      this._$3 = paramString;
    }

    public String getHrefFormer()
    {
      return this._$2;
    }

    public void setHrefFormer(String paramString)
    {
      this._$2 = paramString;
    }

    public String getHrefLatter()
    {
      return this._$1;
    }

    public void setHrefLatter(String paramString)
    {
      this._$1 = paramString;
    }
  }

  public static class URLInfo
  {
    private String[] _$6;
    private int _$5;
    private String[] _$4;
    private String _$3;
    private String _$2;
    private String _$1;

    public URLInfo(String[] paramArrayOfString1, int paramInt, String[] paramArrayOfString2, String paramString1, String paramString2, String paramString3)
    {
      this._$6 = paramArrayOfString1;
      this._$5 = paramInt;
      this._$4 = paramArrayOfString2;
      this._$2 = paramString1;
      this._$1 = paramString2;
      this._$3 = paramString3;
    }

    public String[] getPaths()
    {
      return this._$6;
    }

    public void setPaths(String[] paramArrayOfString)
    {
      this._$6 = paramArrayOfString;
    }

    public int getPageNo()
    {
      return this._$5;
    }

    public void setPageNo(int paramInt)
    {
      this._$5 = paramInt;
    }

    public String[] getParams()
    {
      return this._$4;
    }

    public void setParams(String[] paramArrayOfString)
    {
      this._$4 = paramArrayOfString;
    }

    public String getUrlPrefix()
    {
      return this._$2;
    }

    public void setUrlPrefix(String paramString)
    {
      this._$2 = paramString;
    }

    public String getUrlSuffix()
    {
      return this._$1;
    }

    public void setUrlSuffix(String paramString)
    {
      this._$1 = paramString;
    }

    public String getQueryString()
    {
      return this._$3;
    }

    public void setQueryString(String paramString)
    {
      this._$3 = paramString;
    }
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.web.front.URLHelper
 * JD-Core Version:    0.6.2
 */