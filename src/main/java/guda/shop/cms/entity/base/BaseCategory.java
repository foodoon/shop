package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Brand;
iimport guda.shop.ms.entity.Category;
imimport guda.shop.s.entity.ProductType;
impimport guda.shop..entity.StandardType;
import com.jspgou.core.entity.Website;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public abstract class BaseCategory
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "Category";
  public static String PROP_RGT = "rgt";
  public static String PROP_PARENT = "parent";
  public static String PROP_DESCRIPTION = "description";
  public static String PROP_WEBSITE = "website";
  public static String PROP_TPL_CHANNEL = "tplChannel";
  public static String PROP_TYPE = "type";
  public static String PROP_TITLE = "title";
  public static String PROP_PATH = "path";
  public static String PROP_PRIORITY = "priority";
  public static String PROP_NAME = "name";
  public static String PROP_ID = "id";
  public static String PROP_LFT = "lft";
  public static String PROP_IMAGE_PATH = "imagePath";
  public static String PROP_KEYWORDS = "keywords";
  public static String PROP_TPL_CONTENT = "tplContent";
  private int _$21 = -2147483648;
  private Long _$20;
  private String _$19;
  private String _$18;
  private String _$17;
  private String _$16;
  private Integer _$15;
  private Integer _$14;
  private Integer _$13;
  private String _$12;
  private String _$11;
  private String _$10;
  private String _$9;
  private Boolean _$8;
  private Category _$7;
  private ProductType _$6;
  private Website _$5;
  private Set<Category> _$4;
  private Set<Brand> _$3;
  private Set<StandardType> _$2;
  private Map<String, String> _$1;

  public BaseCategory()
  {
    initialize();
  }

  public BaseCategory(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseCategory(Long paramLong, ProductType paramProductType, Website paramWebsite, String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
  {
    setId(paramLong);
    setType(paramProductType);
    setWebsite(paramWebsite);
    setName(paramString1);
    setPath(paramString2);
    setLft(paramInteger1);
    setRgt(paramInteger2);
    setPriority(paramInteger3);
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

  public String getName()
  {
    return this._$19;
  }

  public void setName(String paramString)
  {
    this._$19 = paramString;
  }

  public String getPath()
  {
    return this._$18;
  }

  public void setPath(String paramString)
  {
    this._$18 = paramString;
  }

  public String getTplChannel()
  {
    return this._$17;
  }

  public void setTplChannel(String paramString)
  {
    this._$17 = paramString;
  }

  public String getTplContent()
  {
    return this._$16;
  }

  public void setTplContent(String paramString)
  {
    this._$16 = paramString;
  }

  public Integer getLft()
  {
    return this._$15;
  }

  public void setLft(Integer paramInteger)
  {
    this._$15 = paramInteger;
  }

  public Integer getRgt()
  {
    return this._$14;
  }

  public void setRgt(Integer paramInteger)
  {
    this._$14 = paramInteger;
  }

  public Integer getPriority()
  {
    return this._$13;
  }

  public void setPriority(Integer paramInteger)
  {
    this._$13 = paramInteger;
  }

  public Boolean getColorsize()
  {
    return this._$8;
  }

  public void setColorsize(Boolean paramBoolean)
  {
    this._$8 = paramBoolean;
  }

  public String getTitle()
  {
    return this._$12;
  }

  public void setTitle(String paramString)
  {
    this._$12 = paramString;
  }

  public String getImagePath()
  {
    return this._$11;
  }

  public void setImagePath(String paramString)
  {
    this._$11 = paramString;
  }

  public String getKeywords()
  {
    return this._$10;
  }

  public void setKeywords(String paramString)
  {
    this._$10 = paramString;
  }

  public String getDescription()
  {
    return this._$9;
  }

  public void setDescription(String paramString)
  {
    this._$9 = paramString;
  }

  public Category getParent()
  {
    return this._$7;
  }

  public void setParent(Category paramCategory)
  {
    this._$7 = paramCategory;
  }

  public ProductType getType()
  {
    return this._$6;
  }

  public void setType(ProductType paramProductType)
  {
    this._$6 = paramProductType;
  }

  public Website getWebsite()
  {
    return this._$5;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$5 = paramWebsite;
  }

  public Set<Category> getChild()
  {
    return this._$4;
  }

  public void setChild(Set<Category> paramSet)
  {
    this._$4 = paramSet;
  }

  public Set<Brand> getBrands()
  {
    return this._$3;
  }

  public void setBrands(Set<Brand> paramSet)
  {
    this._$3 = paramSet;
  }

  public Set<StandardType> getStandardType()
  {
    return this._$2;
  }

  public void setStandardType(Set<StandardType> paramSet)
  {
    this._$2 = paramSet;
  }

  public Map<String, String> getAttr()
  {
    return this._$1;
  }

  public void setAttr(Map<String, String> paramMap)
  {
    this._$1 = paramMap;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Category))
      return false;
    Category localCategory = (Category)paramObject;
    if ((null == getId()) || (null == localCategory.getId()))
      return false;
    return getId().equals(localCategory.getId());
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
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseCategory
 * JD-Core Version:    0.6.2
 */