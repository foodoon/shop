package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Cart;
import guda.shop.cms.entity.PopularityGroup;
import guda.shop.cms.entity.PopularityItem;
import java.io.Serializable;

public abstract class BasePopularityItem
  implements Serializable
{
  public static String REF = "PopularityItem";
  public static String PROP_COUNT = "count";
  public static String PROP_CART = "cart";
  public static String PROP_ID = "id";
  public static String PROP_POPULARITY_GROUP = "popularityGroup";
  private int _$5 = -2147483648;
  private Long _$4;
  private Integer _$3;
  private Cart _$2;
  private PopularityGroup _$1;

  public BasePopularityItem()
  {
    initialize();
  }

  public BasePopularityItem(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BasePopularityItem(Long paramLong, Cart paramCart, Integer paramInteger)
  {
    setId(paramLong);
    setCart(paramCart);
    setCount(paramInteger);
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

  public Integer getCount()
  {
    return this._$3;
  }

  public void setCount(Integer paramInteger)
  {
    this._$3 = paramInteger;
  }

  public Cart getCart()
  {
    return this._$2;
  }

  public void setCart(Cart paramCart)
  {
    this._$2 = paramCart;
  }

  public PopularityGroup getPopularityGroup()
  {
    return this._$1;
  }

  public void setPopularityGroup(PopularityGroup paramPopularityGroup)
  {
    this._$1 = paramPopularityGroup;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof PopularityItem))
      return false;
    PopularityItem localPopularityItem = (PopularityItem)paramObject;
    if ((null == getId()) || (null == localPopularityItem.getId()))
      return false;
    return getId().equals(localPopularityItem.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BasePopularityItem
 * JD-Core Version:    0.6.2
 */