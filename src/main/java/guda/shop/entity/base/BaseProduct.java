package guda.shop.entity.base;

import guda.shop.cms.entity.Brand;
iimport guda.shopcms.entity.Category;
imimport guda.shopms.entity.PopularityGroup;
impimport guda.shops.entity.Product;
impoimport guda.shop.entity.ProductExended;
imporimport guda.shopentity.ProductExt;
importimport guda.shopntity.ProductFashion;
import import guda.shoptity.ProductPicture;
import cimport guda.shopity.ProductTag;
import coimport guda.shopty.ProductText;
import comimport guda.shopy.ProductType;
import com.import guda.shop.ShopConfig;
import com.jspgou.core.entity.Website;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseProduct
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "Product";
  public static String PROP_PRODUCT_EXT = "productExt";
  public static String PROP_BRAND = "brand";
  public static String PROP_CONFIG = "config";
  public static String PROP_SALE_COUNT = "saleCount";
  public static String PROP_SPECIAL = "special";
  public static String PROP_TYPE = "type";
  public static String PROP_SHARE_CONTENT = "shareContent";
  public static String PROP_RECOMMEND = "recommend";
  public static String PROP_NEW_PRODUCT = "newProduct";
  public static String PROP_VIEW_COUNT = "viewCount";
  public static String PROP_HOTSALE = "hotsale";
  public static String PROP_SCORE = "score";
  public static String PROP_MARKET_PRICE = "marketPrice";
  public static String PROP_WEBSITE = "website";
  public static String PROP_STOCK_COUNT = "stockCount";
  public static String PROP_PRODUCT_TEXT = "productText";
  public static String PROP_ON_SALE = "onSale";
  public static String PROP_NAME = "name";
  public static String PROP_CATEGORY = "category";
  public static String PROP_SALE_PRICE = "salePrice";
  public static String PROP_CREATE_TIME = "createTime";
  public static String PROP_ID = "id";
  public static String PROP_COST_PRICE = "costPrice";
  private int _$35 = -2147483648;
  private Long _$34;
  private String _$33;
  private Double _$32;
  private Double _$31;
  private Double _$30;
  private Long _$29;
  private Integer _$28;
  private Integer _$27;
  private Date _$26;
  private Date _$25;
  private Boolean _$24;
  private Boolean _$23;
  private Boolean _$22;
  private Boolean _$21;
  private Boolean _$20;
  private Boolean _$19;
  private Integer _$18;
  private String _$17;
  private Integer _$16;
  private Double _$15;
  private ProductText _$14;
  private ProductExt _$13;
  private Brand _$12;
  private ShopConfig _$11;
  private Category _$10;
  private ProductType _$9;
  private Website _$8;
  private Set<ProductTag> _$7;
  private Set<ProductFashion> _$6;
  private List<String> _$5;
  private List<ProductPicture> _$4;
  private Map<String, String> _$3;
  private List<ProductExended> _$2;
  private Set<PopularityGroup> _$1;

  public BaseProduct()
  {
    initialize();
  }

  public BaseProduct(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseProduct(Long paramLong1, ShopConfig paramShopConfig, Category paramCategory, ProductType paramProductType, Website paramWebsite, String paramString, Double paramDouble1, Double paramDouble2, Double paramDouble3, Long paramLong2, Integer paramInteger1, Integer paramInteger2, Date paramDate, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5)
  {
    setId(paramLong1);
    setConfig(paramShopConfig);
    setCategory(paramCategory);
    setType(paramProductType);
    setWebsite(paramWebsite);
    setName(paramString);
    setMarketPrice(paramDouble1);
    setSalePrice(paramDouble2);
    setCostPrice(paramDouble3);
    setViewCount(paramLong2);
    setSaleCount(paramInteger1);
    setStockCount(paramInteger2);
    setCreateTime(paramDate);
    setSpecial(paramBoolean1);
    setRecommend(paramBoolean2);
    setHotsale(paramBoolean3);
    setNewProduct(paramBoolean4);
    setOnSale(paramBoolean5);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$34;
  }

  public void setId(Long paramLong)
  {
    this._$34 = paramLong;
    this._$35 = -2147483648;
  }

  public String getName()
  {
    return this._$33;
  }

  public void setName(String paramString)
  {
    this._$33 = paramString;
  }

  public Double getMarketPrice()
  {
    return this._$32;
  }

  public void setMarketPrice(Double paramDouble)
  {
    this._$32 = paramDouble;
  }

  public Double getSalePrice()
  {
    return this._$31;
  }

  public void setSalePrice(Double paramDouble)
  {
    this._$31 = paramDouble;
  }

  public Double getCostPrice()
  {
    return this._$30;
  }

  public void setCostPrice(Double paramDouble)
  {
    this._$30 = paramDouble;
  }

  public Long getViewCount()
  {
    return this._$29;
  }

  public void setViewCount(Long paramLong)
  {
    this._$29 = paramLong;
  }

  public Integer getSaleCount()
  {
    return this._$28;
  }

  public void setSaleCount(Integer paramInteger)
  {
    this._$28 = paramInteger;
  }

  public Integer getStockCount()
  {
    return this._$27;
  }

  public void setStockCount(Integer paramInteger)
  {
    this._$27 = paramInteger;
  }

  public Date getCreateTime()
  {
    return this._$26;
  }

  public void setCreateTime(Date paramDate)
  {
    this._$26 = paramDate;
  }

  public Boolean getSpecial()
  {
    return this._$24;
  }

  public void setSpecial(Boolean paramBoolean)
  {
    this._$24 = paramBoolean;
  }

  public Boolean getRecommend()
  {
    return this._$23;
  }

  public void setRecommend(Boolean paramBoolean)
  {
    this._$23 = paramBoolean;
  }

  public Boolean getHotsale()
  {
    return this._$22;
  }

  public void setHotsale(Boolean paramBoolean)
  {
    this._$22 = paramBoolean;
  }

  public Boolean getNewProduct()
  {
    return this._$21;
  }

  public void setNewProduct(Boolean paramBoolean)
  {
    this._$21 = paramBoolean;
  }

  public Boolean getOnSale()
  {
    return this._$20;
  }

  public void setOnSale(Boolean paramBoolean)
  {
    this._$20 = paramBoolean;
  }

  public Boolean getLackRemind()
  {
    return this._$19;
  }

  public void setLackRemind(Boolean paramBoolean)
  {
    this._$19 = paramBoolean;
  }

  public Integer getScore()
  {
    return this._$18;
  }

  public void setScore(Integer paramInteger)
  {
    this._$18 = paramInteger;
  }

  public String getShareContent()
  {
    return this._$17;
  }

  public void setShareContent(String paramString)
  {
    this._$17 = paramString;
  }

  public ProductText getProductText()
  {
    return this._$14;
  }

  public void setProductText(ProductText paramProductText)
  {
    this._$14 = paramProductText;
  }

  public ProductExt getProductExt()
  {
    return this._$13;
  }

  public void setProductExt(ProductExt paramProductExt)
  {
    this._$13 = paramProductExt;
  }

  public Brand getBrand()
  {
    return this._$12;
  }

  public void setBrand(Brand paramBrand)
  {
    this._$12 = paramBrand;
  }

  public ShopConfig getConfig()
  {
    return this._$11;
  }

  public void setConfig(ShopConfig paramShopConfig)
  {
    this._$11 = paramShopConfig;
  }

  public Category getCategory()
  {
    return this._$10;
  }

  public void setCategory(Category paramCategory)
  {
    this._$10 = paramCategory;
  }

  public ProductType getType()
  {
    return this._$9;
  }

  public void setType(ProductType paramProductType)
  {
    this._$9 = paramProductType;
  }

  public Website getWebsite()
  {
    return this._$8;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$8 = paramWebsite;
  }

  public Set<ProductTag> getTags()
  {
    return this._$7;
  }

  public void setTags(Set<ProductTag> paramSet)
  {
    this._$7 = paramSet;
  }

  public Set<ProductFashion> getFashions()
  {
    return this._$6;
  }

  public void setFashions(Set<ProductFashion> paramSet)
  {
    this._$6 = paramSet;
  }

  public List<String> getKeywords()
  {
    return this._$5;
  }

  public void setKeywords(List<String> paramList)
  {
    this._$5 = paramList;
  }

  public List<ProductPicture> getPictures()
  {
    return this._$4;
  }

  public void setPictures(List<ProductPicture> paramList)
  {
    this._$4 = paramList;
  }

  public Map<String, String> getAttr()
  {
    return this._$3;
  }

  public void setAttr(Map<String, String> paramMap)
  {
    this._$3 = paramMap;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Product))
      return false;
    Product localProduct = (Product)paramObject;
    if ((null == getId()) || (null == localProduct.getId()))
      return false;
    return getId().equals(localProduct.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$35)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$35 = str.hashCode();
    }
    return this._$35;
  }

  public String toString()
  {
    return super.toString();
  }

  public void setAlertInventory(Integer paramInteger)
  {
    this._$16 = paramInteger;
  }

  public Integer getAlertInventory()
  {
    return this._$16;
  }

  public void setExendeds(List<ProductExended> paramList)
  {
    this._$2 = paramList;
  }

  public List<ProductExended> getExendeds()
  {
    return this._$2;
  }

  public void setExpireTime(Date paramDate)
  {
    this._$25 = paramDate;
  }

  public Date getExpireTime()
  {
    return this._$25;
  }

  public void setLiRun(Double paramDouble)
  {
    this._$15 = paramDouble;
  }

  public Double getLiRun()
  {
    return this._$15;
  }

  public void setPopularityGroups(Set<PopularityGroup> paramSet)
  {
    this._$1 = paramSet;
  }

  public Set<PopularityGroup> getPopularityGroups()
  {
    return this._$1;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseProduct
 * JD-Core Version:    0.6.2
 */