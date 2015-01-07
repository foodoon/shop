package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShopAdmin;
iimport guda.shop.ore.entity.Admin;
import com.jspgou.core.entity.Website;
import java.io.PrintStream;
import java.util.Date;
import java.util.Set;

public class ShopAdmin extends BaseShopAdmin
{
  private static final long serialVersionUID = 1L;

  public Set<String> getPerms()
  {
    Admin localAdmin = getAdmin();
    if (localAdmin != null)
      return localAdmin.getRolesPerms();
    return null;
  }

  public String getUsername()
  {
    Admin localAdmin = getAdmin();
    if (localAdmin != null)
      return localAdmin.getUsername();
    return null;
  }

  public String getEmail()
  {
    Admin localAdmin = getAdmin();
    if (localAdmin != null)
      return localAdmin.getEmail();
    return null;
  }

  public Date getLastLoginTime()
  {
    Admin localAdmin = getAdmin();
    if (localAdmin != null)
      return localAdmin.getLastLoginTime();
    return null;
  }

  public String getLastLoginIp()
  {
    Admin localAdmin = getAdmin();
    if (localAdmin != null)
      return localAdmin.getLastLoginIp();
    return null;
  }

  public Boolean getViewonlyAdmin()
  {
    Admin localAdmin = getAdmin();
    if (localAdmin != null)
      return localAdmin.getViewonlyAdmin();
    System.out.println(2);
    return null;
  }

  public Date getCurrentLoginTime()
  {
    Admin localAdmin = getAdmin();
    if (localAdmin != null)
      return localAdmin.getCurrentLoginTime();
    return null;
  }

  public String getCurrentLoginIp()
  {
    Admin localAdmin = getAdmin();
    if (localAdmin != null)
      return localAdmin.getCurrentLoginIp();
    return null;
  }

  public Boolean getDisabled()
  {
    Admin localAdmin = getAdmin();
    if (localAdmin != null)
      return localAdmin.getDisabled();
    return null;
  }

  public ShopAdmin()
  {
  }

  public ShopAdmin(Long paramLong)
  {
    super(paramLong);
  }

  public ShopAdmin(Long paramLong, Website paramWebsite)
  {
    super(paramLong, paramWebsite);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ShopAdmin
 * JD-Core Version:    0.6.2
 */