package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Order;
iimport guda.shopcms.entity.OrderItem;
imimport guda.shopms.entity.Product;
impimport guda.shops.entity.ProductFashion;
import com.jspgou.core.entity.Website;
import java.io.Serializable;

public abstract class BaseOrderItem
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "OrderItem";
  public static String PROP_SECKILLPRICE = "seckillprice";
  public static String PROP_COUNT = "count";
  public static String PROP_PRODUCT = "product";
  public static String PROP_SALE_PRICE = "salePrice";
  public static String PROP_PRODUCT_FASH = "productFash";
  public static String PROP_WEBSITE = "website";
  public static String PROP_ORDER = "order";
  public static String PROP_ID = "id";
  public static String PROP_MEMBER_PRICE = "memberPrice";
  public static String PROP_PRODUCT_NAME = "productName";
  public static String PROP_FINAL_PRICE = "finalPrice";
  public static String PROP_COST_PRICE = "costPrice";
  private int _$12 = -2147483648;
  private Long _$11;
  private Integer _$10;
  private Double _$9;
  private Double _$8;
  private Double _$7;
  private Double _$6;
  private Double _$5;
  private Website _$4;
  private Product _$3;
  private Order _$2;
  private ProductFashion _$1;

  public BaseOrderItem()
  {
    initialize();
  }

  public BaseOrderItem(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseOrderItem(Long paramLong, Website paramWebsite, Product paramProduct, Order paramOrder, Integer paramInteger)
  {
    setId(paramLong);
    setWebsite(paramWebsite);
    setProduct(paramProduct);
    setOrdeR(paramOrder);
    setCount(paramInteger);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$11;
  }

  public void setId(Long paramLong)
  {
    this._$11 = paramLong;
    this._$12 = -2147483648;
  }

  public Integer getCount()
  {
    return this._$10;
  }

  public void setCount(Integer paramInteger)
  {
    this._$10 = paramInteger;
  }

  public Double getSalePrice()
  {
    return this._$9;
  }

  public void setSalePrice(Double paramDouble)
  {
    this._$9 = paramDouble;
  }

  public Double getMemberPrice()
  {
    return this._$8;
  }

  public void setMemberPrice(Double paramDouble)
  {
    this._$8 = paramDouble;
  }

  public Double getCostPrice()
  {
    return this._$7;
  }

  public void setCostPrice(Double paramDouble)
  {
    this._$7 = paramDouble;
  }

  public Double getFinalPrice()
  {
    return this._$6;
  }

  public void setFinalPrice(Double paramDouble)
  {
    this._$6 = paramDouble;
  }

  public Double getSeckillprice()
  {
    return this._$5;
  }

  public void setSeckillprice(Double paramDouble)
  {
    this._$5 = paramDouble;
  }

  public Website getWebsite()
  {
    return this._$4;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$4 = paramWebsite;
  }

  public Product getProduct()
  {
    return this._$3;
  }

  public void setProduct(Product paramProduct)
  {
    this._$3 = paramProduct;
  }

  public Order getOrdeR()
  {
    return this._$2;
  }

  public void setOrdeR(Order paramOrder)
  {
    this._$2 = paramOrder;
  }

  public ProductFashion getProductFash()
  {
    return this._$1;
  }

  public void setProductFash(ProductFashion paramProductFashion)
  {
    this._$1 = paramProductFashion;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof OrderItem))
      return false;
    OrderItem localOrderItem = (OrderItem)paramObject;
    if ((null == getId()) || (null == localOrderItem.getId()))
      return false;
    return getId().equals(localOrderItem.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$12)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$12 = str.hashCode();
    }
    return this._$12;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseOrderItem
 * JD-Core Version:    0.6.2
 */