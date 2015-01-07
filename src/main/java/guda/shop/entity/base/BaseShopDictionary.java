package guda.shop.entity.base;

import guda.shop.cms.entity.ShopDictionary;
import com.jspgou.cms.entity.ShopDictionaryType;
import java.io.Serializable;

public abstract class BaseShopDictionary
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "ShopDictionary";
  public static String PROP_NAME = "name";
  public static String PROP_ID = "id";
  public static String PROP_SHOP_DICTIONARY_TYPE = "shopDictionaryType";
  private int _$5 = -2147483648;
  private Long _$4;
  private String _$3;
  private Integer _$2;
  private ShopDictionaryType _$1;

  public BaseShopDictionary()
  {
    initialize();
  }

  public BaseShopDictionary(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseShopDictionary(Long paramLong, ShopDictionaryType paramShopDictionaryType, String paramString)
  {
    setId(paramLong);
    setShopDictionaryType(paramShopDictionaryType);
    setName(paramString);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$4;
  }

  public void setId(Long paramLong)
  {
    this._$4 = paramLong;
    this._$5 = -2147483648;
  }

  public String getName()
  {
    return this._$3;
  }

  public void setName(String paramString)
  {
    this._$3 = paramString;
  }

  public Integer getPriority()
  {
    return this._$2;
  }

  public void setPriority(Integer paramInteger)
  {
    this._$2 = paramInteger;
  }

  public ShopDictionaryType getShopDictionaryType()
  {
    return this._$1;
  }

  public void setShopDictionaryType(ShopDictionaryType paramShopDictionaryType)
  {
    this._$1 = paramShopDictionaryType;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopDictionary))
      return false;
    ShopDictionary localShopDictionary = (ShopDictionary)paramObject;
    if ((null == getId()) || (null == localShopDictionary.getId()))
      return false;
    return getId().equals(localShopDictionary.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$5)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$5 = str.hashCode();
    }
    return this._$5;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopDictionary
 * JD-Core Version:    0.6.2
 */