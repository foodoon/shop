package guda.shop.common.util;

import org.apache.commons.lang.StringUtils;

public class StrUtils
{
  public static String handelUrl(String paramString)
  {
    if (paramString == null)
      return null;
    paramString = paramString.trim();
    if ((paramString.equals("")) || (paramString.startsWith("http://")) || (paramString.startsWith("https://")))
      return paramString;
    return "http://" + paramString.trim();
  }

  public static String textCut(String paramString1, int paramInt, String paramString2)
  {
    if (paramString1 == null)
      return null;
    int i = paramString1.length();
    if (i <= paramInt)
      return paramString1;
    int j = paramInt * 2;
    int k = 0;
    for (int m = 0; (k < j) && (m < i); m++)
      if (paramString1.codePointAt(m) < 256)
        k++;
      else
        k += 2;
    if (m < i)
    {
      if (k > j)
        m--;
      if (!StringUtils.isBlank(paramString2))
      {
        if (paramString1.codePointAt(m - 1) < 256)
          m -= 2;
        else
          m--;
        return paramString1.substring(0, m) + paramString2;
      }
      return paramString1.substring(0, m);
    }
    return paramString1;
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.util.StrUtils
 * JD-Core Version:    0.6.2
 */