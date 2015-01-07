package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Discuss;
iimport guda.shop.ms.entity.Product;
import com.jspgou.cms.entity.ShopMember;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseDiscuss
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "Discuss";
  public static String PROP_MEMBER = "member";
  public static String PROP_TIME = "time";
  public static String PROP_PRODUCT = "product";
  public static String PROP_ID = "id";
  public static String PROP_CONTENT = "content";
  private int _$6 = -2147483648;
  private Long _$5;
  private String _$4;
  private Date _$3;
  private ShopMember _$2;
  private Product _$1;

  public BaseDiscuss()
  {
    initialize();
  }

  public BaseDiscuss(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseDiscuss(Long paramLong, ShopMember paramShopMember, Product paramProduct, String paramString)
  {
    setId(paramLong);
    setMember(paramShopMember);
    setProduct(paramProduct);
    setContent(paramString);
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

  public String getContent()
  {
    return this._$4;
  }

  public void setContent(String paramString)
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
    if (!(paramObject instanceof Discuss))
      return false;
    Discuss localDiscuss = (Discuss)paramObject;
    if ((null == getId()) || (null == localDiscuss.getId()))
      return false;
    return getId().equals(localDiscuss.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseDiscuss
 * JD-Core Version:    0.6.2
 */