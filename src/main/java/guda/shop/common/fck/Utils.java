package guda.shop.common.fck;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Utils
{
  public static final String EMPTY_STRING = "";

  public static Set<String> getSet(String paramString1, String paramString2)
  {
    if (isEmpty(paramString2))
      throw new IllegalArgumentException("Argument 'delimiter' shouldn't be empty!");
    if (isEmpty(paramString1))
      return new HashSet();
    HashSet localHashSet = new HashSet();
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, paramString2);
    while (localStringTokenizer.hasMoreTokens())
    {
      String str = localStringTokenizer.nextToken();
      if (isNotEmpty(str))
        localHashSet.add(str.toLowerCase());
    }
    return localHashSet;
  }

  public static Set<String> getSet(String paramString)
  {
    return getSet(paramString, "|");
  }

  public static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static boolean isNotEmpty(String paramString)
  {
    return !isEmpty(paramString);
  }

  public static boolean isBlank(String paramString)
  {
    if (isEmpty(paramString))
      return true;
    for (char c : paramString.toCharArray())
      if (!Character.isWhitespace(c))
        return false;
    return true;
  }

  public static boolean isNotBlank(String paramString)
  {
    return !isBlank(paramString);
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.fck.Utils
 * JD-Core Version:    0.6.2
 */