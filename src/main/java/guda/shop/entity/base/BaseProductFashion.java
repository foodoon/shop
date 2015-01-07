package guda.shop.entity.base;

import guda.shop.cms.entity.CartItem;
iimport guda.shopcms.entity.Product;
imimport guda.shopms.entity.ProductFashion;
import com.jspgou.cms.entity.Standard;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public abstract class BaseProductFashion
  implements Serializable
{
  public static String REF = "ProductFashion";
  public static String PROP_DEFAULT = "default";
  public static String PROP_STANDARD = "standard";
  public static String PROP_SALE_COUNT = "saleCount";
  public static String PROP_MARKET_PRICE = "marketPrice";
  public static String PROP_PRODUCT_CODE = "productCode";
  public static String PROP_STOCK_COUNT = "stockCount";
  public static String PROP_PRODUCT_ID = "productId";
  public static String PROP_ON_SALE = "onSale";
  public static String PROP_SALE_PRICE = "salePrice";
  public static String PROP_STANDARD_SECOND = "standardSecond";
  public static String PROP_FASHION_STYLE = "fashionStyle";
  public static String PROP_CREATE_TIME = "createTime";
  public static String PROP_FASHION_PIC = "fashionPic";
  public static String PROP_FASHION_SIZE = "fashionSize";
  public static String PROP_ID = "id";
  public static String PROP_COST_PRICE = "costPrice";
  public static String PROP_FASHION_COLOR = "fashionColor";
  private int _$21 = -2147483648;
  private Long _$20;
  private String _$19;
  private String _$18;
  private Integer _$17;
  private Integer _$16;
  private Integer _$15;
  private Date _$14;
  private Double _$13;
  private Double _$12;
  private Double _$11;
  private Integer _$10;
  private String _$9;
  private String _$8;
  private String _$7;
  private Boolean _$6;
  private String _$5;
  private String _$4;
  private Product _$3;
  private Set<CartItem> _$2;
  private Set<Standard> _$1;

  public BaseProductFashion()
  {
    initialize();
  }

  public BaseProductFashion(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseProductFashion(Long paramLong, Boolean paramBoolean)
  {
    setId(paramLong);
    setIsDefault(paramBoolean);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$20;
  }

  public void setId(Long paramLong)
  {
    this._$20 = paramLong;
    this._$21 = -2147483648;
  }

  public String getFashionStyle()
  {
    return this._$19;
  }

  public void setFashionStyle(String paramString)
  {
    this._$19 = paramString;
  }

  public String getProductCode()
  {
    return this._$18;
  }

  public void setProductCode(String paramString)
  {
    this._$18 = paramString;
  }

  public Integer getSaleCount()
  {
    return this._$17;
  }

  public void setSaleCount(Integer paramInteger)
  {
    this._$17 = paramInteger;
  }

  public Integer getStockCount()
  {
    return this._$16;
  }

  public void setStockCount(Integer paramInteger)
  {
    this._$16 = paramInteger;
  }

  public Integer getOnSale()
  {
    return this._$15;
  }

  public void setOnSale(Integer paramInteger)
  {
    this._$15 = paramInteger;
  }

  public Date getCreateTime()
  {
    return this._$14;
  }

  public void setCreateTime(Date paramDate)
  {
    this._$14 = paramDate;
  }

  public Double getMarketPrice()
  {
    return this._$13;
  }

  public void setMarketPrice(Double paramDouble)
  {
    this._$13 = paramDouble;
  }

  public Double getSalePrice()
  {
    return this._$12;
  }

  public void setSalePrice(Double paramDouble)
  {
    this._$12 = paramDouble;
  }

  public Double getCostPrice()
  {
    return this._$11;
  }

  public void setCostPrice(Double paramDouble)
  {
    this._$11 = paramDouble;
  }

  public Integer getLackRemind()
  {
    return this._$10;
  }

  public void setLackRemind(Integer paramInteger)
  {
    this._$10 = paramInteger;
  }

  public String getFashionPic()
  {
    return this._$9;
  }

  public void setFashionPic(String paramString)
  {
    this._$9 = paramString;
  }

  public String getFashionColor()
  {
    return this._$8;
  }

  public void setFashionColor(String paramString)
  {
    this._$8 = paramString;
  }

  public String getFashionSize()
  {
    return this._$7;
  }

  public void setFashionSize(String paramString)
  {
    this._$7 = paramString;
  }

  public Boolean getIsDefault()
  {
    return this._$6;
  }

  public void setIsDefault(Boolean paramBoolean)
  {
    this._$6 = paramBoolean;
  }

  public Product getProductId()
  {
    return this._$3;
  }

  public void setProductId(Product paramProduct)
  {
    this._$3 = paramProduct;
  }

  public Set<CartItem> getCartItems()
  {
    return this._$2;
  }

  public void setCartItems(Set<CartItem> paramSet)
  {
    this._$2 = paramSet;
  }

  public void setStandards(Set<Standard> paramSet)
  {
    this._$1 = paramSet;
  }

  public Set<Standard> getStandards()
  {
    return this._$1;
  }

  public void setNature(String paramString)
  {
    this._$5 = paramString;
  }

  public String getNature()
  {
    return this._$5;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ProductFashion))
      return false;
    ProductFashion localProductFashion = (ProductFashion)paramObject;
    if ((null == getId()) || (null == localProductFashion.getId()))
      return false;
    return getId().equals(localProductFashion.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$21)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$21 = str.hashCode();
    }
    return this._$21;
  }

  public String toString()
  {
    return super.toString();
  }

  public void setAttitude(String paramString)
  {
    this._$4 = paramString;
  }

  public String getAttitude()
  {
    return this._$4;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseProductFashion
 * JD-Core Version:    0.6.2
 */