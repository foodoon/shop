package guda.shop.core.entity;

import guda.shop.common.security.userdetails.UserDetails;
import guda.shop.core.entity.base.BaseMember;
import java.util.Date;

public class Member extends BaseMember
  implements UserDetails
{
  private static final long serialVersionUID = 1L;

  public void init()
  {
    if (getActive() == null)
      setActive(Boolean.valueOf(true));
    if (getCreateTime() == null)
      setCreateTime(new Date());
    if (getDisabled() == null)
      setDisabled(Boolean.valueOf(false));
  }

  public Member()
  {
  }

  public Member(Long paramLong)
  {
    super(paramLong);
  }

  public Member(Long paramLong, User paramUser, Website paramWebsite, Date paramDate, Boolean paramBoolean1, Boolean paramBoolean2, String paramString)
  {
    super(paramLong, paramUser, paramWebsite, paramDate, paramBoolean1, paramBoolean2, paramString);
  }

  public boolean isAccountNonExpired()
  {
    return true;
  }

  public boolean isAccountNonLocked()
  {
    return true;
  }

  public boolean isEnabled()
  {
    return !getDisabled().booleanValue();
  }

  public boolean isCredentialsNonExpired()
  {
    return true;
  }

  public String getUsername()
  {
    return getUser().getUsername();
  }

  public String getEmail()
  {
    return getUser().getEmail();
  }

  public String getPassword()
  {
    return getUser().getPassword();
  }

  public Long getLoginCount()
  {
    return getUser().getLoginCount();
  }

  public String getRegisterIp()
  {
    return getUser().getRegisterIp();
  }

  public Date getLastLoginTime()
  {
    return getUser().getLastLoginTime();
  }

  public String getLastLoginIp()
  {
    return getUser().getLastLoginIp();
  }

  public Date getCurrentLoginTime()
  {
    return getUser().getCurrentLoginTime();
  }

  public String getCurrentLoginIp()
  {
    return getUser().getCurrentLoginIp();
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.Member
 * JD-Core Version:    0.6.2
 */