package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ProductType;
import com.jspgou.cms.entity.ProductTypeProperty;
import java.io.Serializable;

public abstract class BaseProductTypeProperty
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "ProductTypeProperty";
  public static String PROP_COLS = "cols";
  public static String PROP_SORT = "sort";
  public static String PROP_ROWS = "rows";
  public static String PROP_FIELD = "field";
  public static String PROP_DATA_TYPE = "dataType";
  public static String PROP_IS_NOT_NULL = "isNotNull";
  public static String PROP_PROPERTY_TYPE = "propertyType";
  public static String PROP_CUSTOM = "custom";
  public static String PROP_PROPERTY_NAME = "propertyName";
  public static String PROP_IS_START = "isStart";
  public static String PROP_SINGLE = "single";
  public static String PROP_OPT_VALUE = "optValue";
  public static String PROP_CATEGORY = "category";
  public static String PROP_DEF_VALUE = "defValue";
  public static String PROP_ID = "id";
  private int _$16 = -2147483648;
  private Long _$15;
  private String _$14;
  private String _$13;
  private Integer _$12;
  private Integer _$11;
  private Integer _$10;
  private String _$9;
  private String _$8;
  private String _$7;
  private String _$6;
  private Integer _$5;
  private Boolean _$4;
  private Boolean _$3;
  private Boolean _$2;
  private ProductType _$1;

  public BaseProductTypeProperty()
  {
    initialize();
  }

  public BaseProductTypeProperty(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseProductTypeProperty(Long paramLong, ProductType paramProductType, String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3)
  {
    setId(paramLong);
    setPropertyType(paramProductType);
    setPropertyName(paramString1);
    setField(paramString2);
    setIsStart(paramInteger1);
    setIsNotNull(paramInteger2);
    setSort(paramInteger3);
    setDataType(paramInteger4);
    setSingle(paramBoolean1);
    setCategory(paramBoolean2);
    setCustom(paramBoolean3);
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

  public String getPropertyName()
  {
    return this._$14;
  }

  public void setPropertyName(String paramString)
  {
    this._$14 = paramString;
  }

  public String getField()
  {
    return this._$13;
  }

  public void setField(String paramString)
  {
    this._$13 = paramString;
  }

  public Integer getIsStart()
  {
    return this._$12;
  }

  public void setIsStart(Integer paramInteger)
  {
    this._$12 = paramInteger;
  }

  public Integer getIsNotNull()
  {
    return this._$11;
  }

  public void setIsNotNull(Integer paramInteger)
  {
    this._$11 = paramInteger;
  }

  public Integer getSort()
  {
    return this._$10;
  }

  public void setSort(Integer paramInteger)
  {
    this._$10 = paramInteger;
  }

  public String getDefValue()
  {
    return this._$9;
  }

  public void setDefValue(String paramString)
  {
    this._$9 = paramString;
  }

  public String getOptValue()
  {
    return this._$8;
  }

  public void setOptValue(String paramString)
  {
    this._$8 = paramString;
  }

  public String getRows()
  {
    return this._$7;
  }

  public void setRows(String paramString)
  {
    this._$7 = paramString;
  }

  public String getCols()
  {
    return this._$6;
  }

  public void setCols(String paramString)
  {
    this._$6 = paramString;
  }

  public Integer getDataType()
  {
    return this._$5;
  }

  public void setDataType(Integer paramInteger)
  {
    this._$5 = paramInteger;
  }

  public Boolean getSingle()
  {
    return this._$4;
  }

  public void setSingle(Boolean paramBoolean)
  {
    this._$4 = paramBoolean;
  }

  public Boolean getCategory()
  {
    return this._$3;
  }

  public void setCategory(Boolean paramBoolean)
  {
    this._$3 = paramBoolean;
  }

  public Boolean getCustom()
  {
    return this._$2;
  }

  public void setCustom(Boolean paramBoolean)
  {
    this._$2 = paramBoolean;
  }

  public ProductType getPropertyType()
  {
    return this._$1;
  }

  public void setPropertyType(ProductType paramProductType)
  {
    this._$1 = paramProductType;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ProductTypeProperty))
      return false;
    ProductTypeProperty localProductTypeProperty = (ProductTypeProperty)paramObject;
    if ((null == getId()) || (null == localProductTypeProperty.getId()))
      return false;
    return getId().equals(localProductTypeProperty.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseProductTypeProperty
 * JD-Core Version:    0.6.2
 */