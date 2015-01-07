package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Category;
iimport guda.shopcms.entity.Standard;
import com.jspgou.cms.entity.StandardType;
import java.io.Serializable;
import java.util.Set;

public abstract class BaseStandardType
  implements Serializable
{
  public static String REF = "StandardType";
  public static String PROP_NAME = "name";
  public static String PROP_DATA_TYPE = "dataType";
  public static String PROP_FIELD = "field";
  public static String PROP_ID = "id";
  public static String PROP_PRIORITY = "priority";
  private int _$9 = -2147483648;
  private Long _$8;
  private String _$7;
  private String _$6;
  private String _$5;
  private boolean _$4;
  private Integer _$3;
  private Set<Standard> _$2;
  private Set<Category> _$1;

  public BaseStandardType()
  {
    initialize();
  }

  public BaseStandardType(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseStandardType(Long paramLong, String paramString1, String paramString2, boolean paramBoolean)
  {
    setId(paramLong);
    setName(paramString1);
    setField(paramString2);
    setDataType(paramBoolean);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$8;
  }

  public void setId(Long paramLong)
  {
    this._$8 = paramLong;
    this._$9 = -2147483648;
  }

  public String getName()
  {
    return this._$7;
  }

  public void setName(String paramString)
  {
    this._$7 = paramString;
  }

  public String getField()
  {
    return this._$6;
  }

  public void setField(String paramString)
  {
    this._$6 = paramString;
  }

  public boolean getDataType()
  {
    return this._$4;
  }

  public void setDataType(boolean paramBoolean)
  {
    this._$4 = paramBoolean;
  }

  public Integer getPriority()
  {
    return this._$3;
  }

  public void setPriority(Integer paramInteger)
  {
    this._$3 = paramInteger;
  }

  public Set<Standard> getStandardSet()
  {
    return this._$2;
  }

  public void setStandardSet(Set<Standard> paramSet)
  {
    this._$2 = paramSet;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof StandardType))
      return false;
    StandardType localStandardType = (StandardType)paramObject;
    if ((null == getId()) || (null == localStandardType.getId()))
      return false;
    return getId().equals(localStandardType.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$9)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$9 = str.hashCode();
    }
    return this._$9;
  }

  public String toString()
  {
    return super.toString();
  }

  public void setCategorys(Set<Category> paramSet)
  {
    this._$1 = paramSet;
  }

  public Set<Category> getCategorys()
  {
    return this._$1;
  }

  public void setRemark(String paramString)
  {
    this._$5 = paramString;
  }

  public String getRemark()
  {
    return this._$5;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseStandardType
 * JD-Core Version:    0.6.2
 */