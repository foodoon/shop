package guda.shop.common.util;

import java.io.PrintStream;

public class CnToSpell
{
  private char[] _$3 = { '啊', 33453, '擦', '搭', 34558, '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座' };
  private char[] _$2 = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
  private int[] _$1 = new int[27];

  public CnToSpell()
  {
    for (int i = 0; i < 27; i++)
      this._$1[i] = _$1(this._$3[i]);
  }

  public char charAlpha(char paramChar) {
      if ((paramChar >= 'a') && (paramChar <= 'z'))
          return paramChar;
      if ((paramChar >= 'A') && (paramChar <= 'Z'))
          return (char) (paramChar - 'A' + 97);
      if ((paramChar >= '0') && (paramChar <= '9'))
          return paramChar;
      int i = _$1(paramChar);
      if (i < this._$1[0])
          return '0';
      for (int j = 0; (j < 26) && (!_$1(j, i)); j++) {
          if (j >= 26)
              return '0';
          return this._$2[j];
      }
      return paramChar;
  }

  public String getBeginCharacter(String paramString)
  {
    String str = "";
    int i = paramString.length();
    try
    {
      for (int j = 0; j < i; j++)
        str = str + charAlpha(paramString.charAt(j));
    }
    catch (Exception localException)
    {
      str = "";
    }
    return str;
  }

  private boolean _$1(int paramInt1, int paramInt2)
  {
    if (paramInt2 < this._$1[paramInt1])
      return false;
    for (int i = paramInt1 + 1; (i < 26) && (this._$1[i] == this._$1[paramInt1]); i++) {
        if (i == 26) {
            return paramInt2 <= this._$1[i];
        }else {
            return paramInt2 < this._$1[i];
        }
    }

     return false;
  }

  private int _$1(char paramChar)
  {
    String str = new String();
    str = str + paramChar;
    try
    {
      byte[] arrayOfByte = str.getBytes("GB2312");
      if (arrayOfByte.length < 2)
        return 0;
      return (arrayOfByte[0] << 8 & 0xFF00) + (arrayOfByte[1] & 0xFF);
    }
    catch (Exception localException)
    {
    }
    return 0;
  }

  public static void main(String[] paramArrayOfString)
  {
    CnToSpell localCnToSpell = new CnToSpell();
    System.out.println(localCnToSpell.getBeginCharacter("测试数据8ADGaadf"));
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.util.CnToSpell
 * JD-Core Version:    0.6.2
 */