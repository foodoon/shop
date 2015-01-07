package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ShopAdmin;
import guda.shop.core.entity.Admin;
import guda.shop.core.entity.Website;
import java.io.Serializable;

public abstract class BaseShopAdmin
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "ShopAdmin";
  public static String PROP_LASTNAME = "lastname";
  public static String PROP_WEBSITE = "website";
  public static String PROP_FIRSTNAME = "firstname";
  public static String PROP_ADMIN = "admin";
  public static String PROP_ID = "id";
  private int _$6 = -2147483648;
  private Long _$5;
  private String _$4;
  private String _$3;
  private Admin _$2;
  private Website _$1;

  public BaseShopAdmin()
  {
    initialize();
  }

  public BaseShopAdmin(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseShopAdmin(Long paramLong, Website paramWebsite)
  {
    setId(paramLong);
    setWebsite(paramWebsite);
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

  public String getFirstname()
  {
    return this._$4;
  }

  public void setFirstname(String paramString)
  {
    this._$4 = paramString;
  }

  public String getLastname()
  {
    return this._$3;
  }

  public void setLastname(String paramString)
  {
    this._$3 = paramString;
  }

  public Admin getAdmin()
  {
    return this._$2;
  }

  public void setAdmin(Admin paramAdmin)
  {
    this._$2 = paramAdmin;
  }

  public Website getWebsite()
  {
    return this._$1;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$1 = paramWebsite;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopAdmin))
      return false;
    ShopAdmin localShopAdmin = (ShopAdmin)paramObject;
    if ((null == getId()) || (null == localShopAdmin.getId()))
      return false;
    return getId().equals(localShopAdmin.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopAdmin
 * JD-Core Version:    0.6.2
 */