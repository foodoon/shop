package guda.shop.entity.base;

import guda.shop.cms.entity.KeyWord;
import java.io.Serializable;

public abstract class BaseKeyWord
  implements Serializable
{
  public static String REF = "KeyWord";
  public static String PROP_KEYWORD = "keyword";
  public static String PROP_TIMES = "times";
  public static String PROP_ID = "id";
  private int _$4 = -2147483648;
  private Integer _$3;
  private String _$2;
  private Integer _$1;

  public BaseKeyWord()
  {
    initialize();
  }

  public BaseKeyWord(Integer paramInteger)
  {
    setId(paramInteger);
    initialize();
  }

  public BaseKeyWord(Integer paramInteger1, String paramString, Integer paramInteger2)
  {
    setId(paramInteger1);
    setKeyword(paramString);
    setTimes(paramInteger2);
    initialize();
  }

  protected void initialize()
  {
  }

  public Integer getId()
  {
    return this._$3;
  }

  public void setId(Integer paramInteger)
  {
    this._$3 = paramInteger;
    this._$4 = -2147483648;
  }

  public String getKeyword()
  {
    return this._$2;
  }

  public void setKeyword(String paramString)
  {
    this._$2 = paramString;
  }

  public Integer getTimes()
  {
    return this._$1;
  }

  public void setTimes(Integer paramInteger)
  {
    this._$1 = paramInteger;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof KeyWord))
      return false;
    KeyWord localKeyWord = (KeyWord)paramObject;
    if ((null == getId()) || (null == localKeyWord.getId()))
      return false;
    return getId().equals(localKeyWord.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$4)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$4 = str.hashCode();
    }
    return this._$4;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseKeyWord
 * JD-Core Version:    0.6.2
 */