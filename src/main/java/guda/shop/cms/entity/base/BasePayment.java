package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Payment;
iimport guda.shop.ms.entity.Shipping;
import com.jspgou.core.entity.Website;
import java.io.Serializable;
import java.util.Set;

public abstract class BasePayment
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "Payment";
  public static String PROP_DESCRIPTION = "description";
  public static String PROP_WEBSITE = "website";
  public static String PROP_DISABLED = "disabled";
  public static String PROP_CODE = "code";
  public static String PROP_PRIORITY = "priority";
  public static String PROP_NAME = "name";
  public static String PROP_ID = "id";
  public static String PROP_CONFIG = "config";
  private int _$11 = -2147483648;
  private Long _$10;
  private String _$9;
  private String _$8;
  private Integer _$7;
  private Boolean _$6;
  private Boolean _$5;
  private Byte _$4;
  private String _$3;
  private Website _$2;
  private Set<Shipping> _$1;

  public BasePayment()
  {
    initialize();
  }

  public BasePayment(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BasePayment(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger, Boolean paramBoolean)
  {
    setId(paramLong);
    setWebsite(paramWebsite);
    setName(paramString);
    setPriority(paramInteger);
    setDisabled(paramBoolean);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$10;
  }

  public void setId(Long paramLong)
  {
    this._$10 = paramLong;
    this._$11 = -2147483648;
  }

  public String getName()
  {
    return this._$9;
  }

  public void setName(String paramString)
  {
    this._$9 = paramString;
  }

  public String getDescription()
  {
    return this._$8;
  }

  public void setDescription(String paramString)
  {
    this._$8 = paramString;
  }

  public Integer getPriority()
  {
    return this._$7;
  }

  public void setPriority(Integer paramInteger)
  {
    this._$7 = paramInteger;
  }

  public Boolean getDisabled()
  {
    return this._$6;
  }

  public void setDisabled(Boolean paramBoolean)
  {
    this._$6 = paramBoolean;
  }

  public Boolean getIsDefault()
  {
    return this._$5;
  }

  public void setIsDefault(Boolean paramBoolean)
  {
    this._$5 = paramBoolean;
  }

  public Website getWebsite()
  {
    return this._$2;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$2 = paramWebsite;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Payment))
      return false;
    Payment localPayment = (Payment)paramObject;
    if ((null == getId()) || (null == localPayment.getId()))
      return false;
    return getId().equals(localPayment.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$11)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$11 = str.hashCode();
    }
    return this._$11;
  }

  public String toString()
  {
    return super.toString();
  }

  public void setType(Byte paramByte)
  {
    this._$4 = paramByte;
  }

  public Byte getType()
  {
    return this._$4;
  }

  public void setShippings(Set<Shipping> paramSet)
  {
    this._$1 = paramSet;
  }

  public Set<Shipping> getShippings()
  {
    return this._$1;
  }

  public void setIntroduce(String paramString)
  {
    this._$3 = paramString;
  }

  public String getIntroduce()
  {
    return this._$3;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BasePayment
 * JD-Core Version:    0.6.2
 */