package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Consult;
import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ShopMember;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseConsult
  implements Serializable
{
  public static String REF = "Consult";
  public static String PROP_MEMBER = "member";
  public static String PROP_TIME = "time";
  public static String PROP_PRODUCT = "product";
  public static String PROP_ID = "id";
  public static String PROP_CONSULT = "consult";
  public static String PROP_ADMIN_REPLAY = "adminReplay";
  private int _$7 = -2147483648;
  private Long _$6;
  private String _$5;
  private String _$4;
  private Date _$3;
  private ShopMember _$2;
  private Product _$1;

  public BaseConsult()
  {
    initialize();
  }

  public BaseConsult(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseConsult(Long paramLong, ShopMember paramShopMember, Product paramProduct)
  {
    setId(paramLong);
    setMember(paramShopMember);
    setProduct(paramProduct);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$6;
  }

  public void setId(Long paramLong)
  {
    this._$6 = paramLong;
    this._$7 = -2147483648;
  }

  public String getConsult()
  {
    return this._$5;
  }

  public void setConsult(String paramString)
  {
    this._$5 = paramString;
  }

  public String getAdminReplay()
  {
    return this._$4;
  }

  public void setAdminReplay(String paramString)
  {
    this._$4 = paramString;
  }

  public Date getTime()
  {
    return this._$3;
  }

  public void setTime(Date paramDate)
  {
    this._$3 = paramDate;
  }

  public ShopMember getMember()
  {
    return this._$2;
  }

  public void setMember(ShopMember paramShopMember)
  {
    this._$2 = paramShopMember;
  }

  public Product getProduct()
  {
    return this._$1;
  }

  public void setProduct(Product paramProduct)
  {
    this._$1 = paramProduct;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Consult))
      return false;
    Consult localConsult = (Consult)paramObject;
    if ((null == getId()) || (null == localConsult.getId()))
      return false;
    return getId().equals(localConsult.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$7)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$7 = str.hashCode();
    }
    return this._$7;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseConsult
 * JD-Core Version:    0.6.2
 */