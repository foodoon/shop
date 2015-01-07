package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Exended;
import com.jspgou.cms.entity.ExendedItem;
import java.io.Serializable;

public abstract class BaseExendedItem
  implements Serializable
{
  public static String REF = "ExendedItem";
  public static String PROP_NAME = "name";
  public static String PROP_ID = "id";
  public static String PROP_EXENDED = "exended";
  public static String PROP_PRIORITY = "priority";
  private int _$4 = -2147483648;
  private Long _$3;
  private String _$2;
  private Exended _$1;

  public BaseExendedItem()
  {
    initialize();
  }

  public BaseExendedItem(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseExendedItem(Long paramLong, Exended paramExended, String paramString)
  {
    setId(paramLong);
    setExended(paramExended);
    setName(paramString);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$3;
  }

  public void setId(Long paramLong)
  {
    this._$3 = paramLong;
    this._$4 = -2147483648;
  }

  public String getName()
  {
    return this._$2;
  }

  public void setName(String paramString)
  {
    this._$2 = paramString;
  }

  public Exended getExended()
  {
    return this._$1;
  }

  public void setExended(Exended paramExended)
  {
    this._$1 = paramExended;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ExendedItem))
      return false;
    ExendedItem localExendedItem = (ExendedItem)paramObject;
    if ((null == getId()) || (null == localExendedItem.getId()))
      return false;
    return getId().equals(localExendedItem.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseExendedItem
 * JD-Core Version:    0.6.2
 */