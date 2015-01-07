package guda.shop.entity.base;

import guda.shop.cms.entity.ShopConfig;
iimport guda.shopcms.entity.ShopMemberGroup;
import com.jspgou.core.entity.Website;
import java.io.Serializable;

public abstract class BaseShopConfig
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "ShopConfig";
  public static String PROP_REGISTER_AUTO = "registerAuto";
  public static String PROP_FAVORITE_SIZE = "favoriteSize";
  public static String PROP_WEBSITE = "website";
  public static String PROP_REGISTER_GROUP = "registerGroup";
  public static String PROP_ID = "id";
  public static String PROP_MARKET_PRICE_NAME = "marketPriceName";
  public static String PROP_SHOP_PRICE_NAME = "shopPriceName";
  private int _$8 = -2147483648;
  private Long _$7;
  private String _$6;
  private String _$5;
  private Integer _$4;
  private Boolean _$3;
  private Website _$2;
  private ShopMemberGroup _$1;

  public BaseShopConfig()
  {
    initialize();
  }

  public BaseShopConfig(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseShopConfig(Long paramLong, ShopMemberGroup paramShopMemberGroup, String paramString1, String paramString2, Integer paramInteger, Boolean paramBoolean)
  {
    setId(paramLong);
    setRegisterGroup(paramShopMemberGroup);
    setShopPriceName(paramString1);
    setMarketPriceName(paramString2);
    setFavoriteSize(paramInteger);
    setRegisterAuto(paramBoolean);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$7;
  }

  public void setId(Long paramLong)
  {
    this._$7 = paramLong;
    this._$8 = -2147483648;
  }

  public String getShopPriceName()
  {
    return this._$6;
  }

  public void setShopPriceName(String paramString)
  {
    this._$6 = paramString;
  }

  public String getMarketPriceName()
  {
    return this._$5;
  }

  public void setMarketPriceName(String paramString)
  {
    this._$5 = paramString;
  }

  public Integer getFavoriteSize()
  {
    return this._$4;
  }

  public void setFavoriteSize(Integer paramInteger)
  {
    this._$4 = paramInteger;
  }

  public Boolean getRegisterAuto()
  {
    return this._$3;
  }

  public void setRegisterAuto(Boolean paramBoolean)
  {
    this._$3 = paramBoolean;
  }

  public Website getWebsite()
  {
    return this._$2;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$2 = paramWebsite;
  }

  public ShopMemberGroup getRegisterGroup()
  {
    return this._$1;
  }

  public void setRegisterGroup(ShopMemberGroup paramShopMemberGroup)
  {
    this._$1 = paramShopMemberGroup;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopConfig))
      return false;
    ShopConfig localShopConfig = (ShopConfig)paramObject;
    if ((null == getId()) || (null == localShopConfig.getId()))
      return false;
    return getId().equals(localShopConfig.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$8)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$8 = str.hashCode();
    }
    return this._$8;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopConfig
 * JD-Core Version:    0.6.2
 */