package guda.shop.entity.base;

import guda.shop.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopMoney;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseShopMoney
  implements Serializable
{
  public static String REF = "ShopMoney";
  public static String PROP_NAME = "name";
  public static String PROP_STATUS = "status";
  public static String PROP_MEMBER = "member";
  public static String PROP_MONEY = "money";
  public static String PROP_CREATE_TIME = "createTime";
  public static String PROP_ID = "id";
  public static String PROP_REMARK = "remark";
  private int _$8 = -2147483648;
  private Long _$7;
  private String _$6;
  private Double _$5;
  private boolean _$4;
  private Date _$3;
  private String _$2;
  private ShopMember _$1;

  public BaseShopMoney()
  {
    initialize();
  }

  public BaseShopMoney(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseShopMoney(Long paramLong, String paramString, Double paramDouble, boolean paramBoolean, Date paramDate)
  {
    setId(paramLong);
    setName(paramString);
    setMoney(paramDouble);
    setStatus(paramBoolean);
    setCreateTime(paramDate);
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

  public String getName()
  {
    return this._$6;
  }

  public void setName(String paramString)
  {
    this._$6 = paramString;
  }

  public Double getMoney()
  {
    return this._$5;
  }

  public void setMoney(Double paramDouble)
  {
    this._$5 = paramDouble;
  }

  public boolean isStatus()
  {
    return this._$4;
  }

  public void setStatus(boolean paramBoolean)
  {
    this._$4 = paramBoolean;
  }

  public Date getCreateTime()
  {
    return this._$3;
  }

  public void setCreateTime(Date paramDate)
  {
    this._$3 = paramDate;
  }

  public String getRemark()
  {
    return this._$2;
  }

  public void setRemark(String paramString)
  {
    this._$2 = paramString;
  }

  public ShopMember getMember()
  {
    return this._$1;
  }

  public void setMember(ShopMember paramShopMember)
  {
    this._$1 = paramShopMember;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopMoney))
      return false;
    ShopMoney localShopMoney = (ShopMoney)paramObject;
    if ((null == getId()) || (null == localShopMoney.getId()))
      return false;
    return getId().equals(localShopMoney.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopMoney
 * JD-Core Version:    0.6.2
 */