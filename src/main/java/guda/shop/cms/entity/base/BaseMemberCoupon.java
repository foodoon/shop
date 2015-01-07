package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Coupon;
import guda.shop.cms.entity.MemberCoupon;
import guda.shop.cms.entity.ShopMember;
import java.io.Serializable;

public abstract class BaseMemberCoupon
  implements Serializable
{
  public static String REF = "MemberCoupon";
  public static String PROP_MEMBER = "member";
  public static String PROP_COUPON = "coupon";
  public static String PROP_ID = "id";
  public static String PROP_ISUSE = "isuse";
  private int _$5 = -2147483648;
  private Long _$4;
  private Boolean _$3;
  private ShopMember _$2;
  private Coupon _$1;

  public BaseMemberCoupon()
  {
    initialize();
  }

  public BaseMemberCoupon(Long paramLong)
  {
    setId(paramLong);
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

  public Boolean getIsuse()
  {
    return this._$3;
  }

  public void setIsuse(Boolean paramBoolean)
  {
    this._$3 = paramBoolean;
  }

  public ShopMember getMember()
  {
    return this._$2;
  }

  public void setMember(ShopMember paramShopMember)
  {
    this._$2 = paramShopMember;
  }

  public Coupon getCoupon()
  {
    return this._$1;
  }

  public void setCoupon(Coupon paramCoupon)
  {
    this._$1 = paramCoupon;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof MemberCoupon))
      return false;
    MemberCoupon localMemberCoupon = (MemberCoupon)paramObject;
    if ((null == getId()) || (null == localMemberCoupon.getId()))
      return false;
    return getId().equals(localMemberCoupon.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseMemberCoupon
 * JD-Core Version:    0.6.2
 */