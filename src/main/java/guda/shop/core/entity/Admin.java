package guda.shop.core.entity;

import guda.shop.core.entity.base.BaseAdmin;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Admin extends BaseAdmin
{
  private static final long serialVersionUID = 1L;

  public void init()
  {
    if (getCreateTime() == null)
      setCreateTime(new Date());
    if (getDisabled() == null)
      setDisabled(Boolean.valueOf(false));
  }

  public Set<String> getRolesPerms()
  {
    Set localSet = getRoles();
    if (localSet == null)
      return null;
    HashSet localHashSet = new HashSet();
    Iterator localIterator = getRoles().iterator();
    while (localIterator.hasNext())
    {
      Role localRole = (Role)localIterator.next();
      localHashSet.addAll(localRole.getPerms());
    }
    return localHashSet;
  }

  public Integer[] getRoleIds()
  {
    Set localSet = getRoles();
    return Role.fetchIds(localSet);
  }

  public void addToRoles(Role paramRole)
  {
    if (paramRole == null)
      return;
    Object localObject = getRoles();
    if (localObject == null)
    {
      localObject = new HashSet();
      setRoles((Set)localObject);
    }
    ((Set)localObject).add(paramRole);
  }

  public boolean isSuper()
  {
    Set localSet = getRoles();
    if (localSet == null)
      return false;
    Iterator localIterator = getRoles().iterator();
    while (localIterator.hasNext())
    {
      Role localRole = (Role)localIterator.next();
      if (localRole.getSuper().booleanValue())
        return true;
    }
    return false;
  }

  public Admin()
  {
  }

  public Admin(Long paramLong)
  {
    super(paramLong);
  }

  public Admin(Long paramLong, User paramUser, Website paramWebsite, Date paramDate, Boolean paramBoolean)
  {
    super(paramLong, paramUser, paramWebsite, paramDate, paramBoolean);
  }

  public String getUsername()
  {
    User localUser = getUser();
    if (localUser != null)
      return localUser.getUsername();
    return null;
  }

  public String getEmail()
  {
    User localUser = getUser();
    if (localUser != null)
      return localUser.getEmail();
    return null;
  }

  public String getLastLoginIp()
  {
    User localUser = getUser();
    if (localUser != null)
      return localUser.getLastLoginIp();
    return null;
  }

  public Date getLastLoginTime()
  {
    User localUser = getUser();
    if (localUser != null)
      return localUser.getLastLoginTime();
    return null;
  }

  public String getCurrentLoginIp()
  {
    User localUser = getUser();
    if (localUser != null)
      return localUser.getCurrentLoginIp();
    return null;
  }

  public Date getCurrentLoginTime()
  {
    User localUser = getUser();
    if (localUser != null)
      return localUser.getCurrentLoginTime();
    return null;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.Admin
 * JD-Core Version:    0.6.2
 */