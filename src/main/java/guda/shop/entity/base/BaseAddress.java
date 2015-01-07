package guda.shop.entity.base;

import guda.shop.cms.entity.Address;
import java.io.Serializable;

public abstract class BaseAddress
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "Address";
  public static String PROP_NAME = "name";
  public static String PROP_PARENT = "parent";
  public static String PROP_ID = "id";
  private int _$5 = -2147483648;
  private Long _$4;
  private String _$3;
  private Integer _$2;
  private Address _$1;

  public BaseAddress()
  {
    initialize();
  }

  public BaseAddress(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseAddress(Long paramLong, String paramString)
  {
    setId(paramLong);
    setName(paramString);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$4;
  }

  public void setId(Long paramLong)
  {
    this._$4 = paramLong;
    this._$5 = -2147483648;
  }

  public String getName()
  {
    return this._$3;
  }

  public void setName(String paramString)
  {
    this._$3 = paramString;
  }

  public Integer getPriority()
  {
    return this._$2;
  }

  public void setPriority(Integer paramInteger)
  {
    this._$2 = paramInteger;
  }

  public Address getParent()
  {
    return this._$1;
  }

  public void setParent(Address paramAddress)
  {
    this._$1 = paramAddress;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Address))
      return false;
    Address localAddress = (Address)paramObject;
    if ((null == getId()) || (null == localAddress.getId()))
      return false;
    return getId().equals(localAddress.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$5)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$5 = str.hashCode();
    }
    return this._$5;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseAddress
 * JD-Core Version:    0.6.2
 */