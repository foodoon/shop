package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Exended;
iimport guda.shopcms.entity.ProductType;
imimport guda.shopms.entity.ProductTypeProperty;
import com.jspgou.core.entity.Website;
import java.io.Serializable;
import java.util.Set;

public abstract class BaseProductType
  implements Serializable
{
  public static String REF = "ProductType";
  public static String PROP_LIST_IMG_HEIGHT = "listImgHeight";
  public static String PROP_WEBSITE = "website";
  public static String PROP_DETAIL_IMG_WIDTH = "detailImgWidth";
  public static String PROP_LIST_IMG_WIDTH = "listImgWidth";
  public static String PROP_MIN_IMG_HEIGHT = "minImgHeight";
  public static String PROP_PATH = "path";
  public static String PROP_DETAIL_IMG_HEIGHT = "detailImgHeight";
  public static String PROP_NAME = "name";
  public static String PROP_ALIAS = "alias";
  public static String PROP_PROPS = "props";
  public static String PROP_ID = "id";
  public static String PROP_MIN_IMG_WIDTH = "minImgWidth";
  public static String PROP_CONTENT_PREFIX = "contentPrefix";
  public static String PROP_REF_BRAND = "refBrand";
  public static String PROP_CHANNEL_PREFIX = "channelPrefix";
  private int _$16 = -2147483648;
  private Long _$15;
  private String _$14;
  private String _$13;
  private String _$12;
  private String _$11;
  private String _$10;
  private Integer _$9;
  private Integer _$8;
  private Integer _$7;
  private Integer _$6;
  private Integer _$5;
  private Integer _$4;
  private Website _$3;
  private Set<ProductTypeProperty> _$2;
  private Set<Exended> _$1;

  public BaseProductType()
  {
    initialize();
  }

  public BaseProductType(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseProductType(Long paramLong, Website paramWebsite, String paramString1, String paramString2, String paramString3, Boolean paramBoolean, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, Integer paramInteger6)
  {
    setId(paramLong);
    setWebsite(paramWebsite);
    setName(paramString1);
    setChannelPrefix(paramString2);
    setContentPrefix(paramString3);
    setDetailImgWidth(paramInteger1);
    setDetailImgHeight(paramInteger2);
    setListImgWidth(paramInteger3);
    setListImgHeight(paramInteger4);
    setMinImgWidth(paramInteger5);
    setMinImgHeight(paramInteger6);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$15;
  }

  public void setId(Long paramLong)
  {
    this._$15 = paramLong;
    this._$16 = -2147483648;
  }

  public String getName()
  {
    return this._$14;
  }

  public void setName(String paramString)
  {
    this._$14 = paramString;
  }

  public String getAlias()
  {
    return this._$13;
  }

  public void setAlias(String paramString)
  {
    this._$13 = paramString;
  }

  public String getChannelPrefix()
  {
    return this._$12;
  }

  public void setChannelPrefix(String paramString)
  {
    this._$12 = paramString;
  }

  public String getContentPrefix()
  {
    return this._$11;
  }

  public void setContentPrefix(String paramString)
  {
    this._$11 = paramString;
  }

  public String getProps()
  {
    return this._$10;
  }

  public void setProps(String paramString)
  {
    this._$10 = paramString;
  }

  public Integer getDetailImgWidth()
  {
    return this._$9;
  }

  public void setDetailImgWidth(Integer paramInteger)
  {
    this._$9 = paramInteger;
  }

  public Integer getDetailImgHeight()
  {
    return this._$8;
  }

  public void setDetailImgHeight(Integer paramInteger)
  {
    this._$8 = paramInteger;
  }

  public Integer getListImgWidth()
  {
    return this._$7;
  }

  public void setListImgWidth(Integer paramInteger)
  {
    this._$7 = paramInteger;
  }

  public Integer getListImgHeight()
  {
    return this._$6;
  }

  public void setListImgHeight(Integer paramInteger)
  {
    this._$6 = paramInteger;
  }

  public Integer getMinImgWidth()
  {
    return this._$5;
  }

  public void setMinImgWidth(Integer paramInteger)
  {
    this._$5 = paramInteger;
  }

  public Integer getMinImgHeight()
  {
    return this._$4;
  }

  public void setMinImgHeight(Integer paramInteger)
  {
    this._$4 = paramInteger;
  }

  public Website getWebsite()
  {
    return this._$3;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$3 = paramWebsite;
  }

  public Set<ProductTypeProperty> getProperties()
  {
    return this._$2;
  }

  public void setProperties(Set<ProductTypeProperty> paramSet)
  {
    this._$2 = paramSet;
  }

  public void setExendeds(Set<Exended> paramSet)
  {
    this._$1 = paramSet;
  }

  public Set<Exended> getExendeds()
  {
    return this._$1;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ProductType))
      return false;
    ProductType localProductType = (ProductType)paramObject;
    if ((null == getId()) || (null == localProductType.getId()))
      return false;
    return getId().equals(localProductType.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$16)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$16 = str.hashCode();
    }
    return this._$16;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseProductType
 * JD-Core Version:    0.6.2
 */