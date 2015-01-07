package guda.shop.entity.base;

import guda.shop.cms.entity.ShopMemberGroup;
import com.jspgou.core.entity.Website;
import java.io.Serializable;

public abstract class BaseShopMemberGroup
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "ShopMemberGroup";
  public static String PROP_WEBSITE = "website";
  public static String PROP_DISCOUNT = "discount";
  public static String PROP_SCORE = "score";
  public static String PROP_NAME = "name";
  public static String PROP_ID = "id";
  private int _$6 = -2147483648;
  private Long _$5;
  private String _$4;
  private Integer _$3;
  private Integer _$2;
  private Website _$1;

  public BaseShopMemberGroup()
  {
    initialize();
  }

  public BaseShopMemberGroup(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseShopMemberGroup(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger1, Integer paramInteger2)
  {
    setId(paramLong);
    setWebsite(paramWebsite);
    setName(paramString);
    setScore(paramInteger1);
    setDiscount(paramInteger2);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$5;
  }

  public void setId(Long paramLong)
  {
    this._$5 = paramLong;
    this._$6 = -2147483648;
  }

  public String getName()
  {
    return this._$4;
  }

  public void setName(String paramString)
  {
    this._$4 = paramString;
  }

  public Integer getScore()
  {
    return this._$3;
  }

  public void setScore(Integer paramInteger)
  {
    this._$3 = paramInteger;
  }

  public Integer getDiscount()
  {
    return this._$2;
  }

  public void setDiscount(Integer paramInteger)
  {
    this._$2 = paramInteger;
  }

  public Website getWebsite()
  {
    return this._$1;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$1 = paramWebsite;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopMemberGroup))
      return false;
    ShopMemberGroup localShopMemberGroup = (ShopMemberGroup)paramObject;
    if ((null == getId()) || (null == localShopMemberGroup.getId()))
      return false;
    return getId().equals(localShopMemberGroup.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$6)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$6 = str.hashCode();
    }
    return this._$6;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopMemberGroup
 * JD-Core Version:    0.6.2
 */