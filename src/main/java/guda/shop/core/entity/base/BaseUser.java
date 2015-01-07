package guda.shop.core.entity.base;

import guda.shop.core.entity.User;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseUser
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "User";
  public static String PROP_LOGIN_COUNT = "loginCount";
  public static String PROP_LAST_LOGIN_IP = "lastLoginIp";
  public static String PROP_CREATE_TIME = "createTime";
  public static String PROP_RESET_KEY = "resetKey";
  public static String PROP_LAST_LOGIN_TIME = "lastLoginTime";
  public static String PROP_RESET_PWD = "resetPwd";
  public static String PROP_PASSWORD = "password";
  public static String PROP_EMAIL = "email";
  public static String PROP_CURRENT_LOGIN_TIME = "currentLoginTime";
  public static String PROP_CURRENT_LOGIN_IP = "currentLoginIp";
  public static String PROP_REGISTER_IP = "registerIp";
  public static String PROP_ID = "id";
  public static String PROP_USERNAME = "username";
  private int _$14 = -2147483648;
  private Long _$13;
  private String _$12;
  private String _$11;
  private String _$10;
  private Date _$9;
  private Long _$8;
  private String _$7;
  private Date _$6;
  private String _$5;
  private Date _$4;
  private String _$3;
  private String _$2;
  private String _$1;

  public BaseUser()
  {
    initialize();
  }

  public BaseUser(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseUser(Long paramLong1, String paramString1, String paramString2, Date paramDate, Long paramLong2)
  {
    setId(paramLong1);
    setUsername(paramString1);
    setPassword(paramString2);
    setCreateTime(paramDate);
    setLoginCount(paramLong2);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$13;
  }

  public void setId(Long paramLong)
  {
    this._$13 = paramLong;
    this._$14 = -2147483648;
  }

  public String getUsername()
  {
    return this._$12;
  }

  public void setUsername(String paramString)
  {
    this._$12 = paramString;
  }

  public String getEmail()
  {
    return this._$11;
  }

  public void setEmail(String paramString)
  {
    this._$11 = paramString;
  }

  public String getPassword()
  {
    return this._$10;
  }

  public void setPassword(String paramString)
  {
    this._$10 = paramString;
  }

  public Date getCreateTime()
  {
    return this._$9;
  }

  public void setCreateTime(Date paramDate)
  {
    this._$9 = paramDate;
  }

  public Long getLoginCount()
  {
    return this._$8;
  }

  public void setLoginCount(Long paramLong)
  {
    this._$8 = paramLong;
  }

  public String getRegisterIp()
  {
    return this._$7;
  }

  public void setRegisterIp(String paramString)
  {
    this._$7 = paramString;
  }

  public Date getLastLoginTime()
  {
    return this._$6;
  }

  public void setLastLoginTime(Date paramDate)
  {
    this._$6 = paramDate;
  }

  public String getLastLoginIp()
  {
    return this._$5;
  }

  public void setLastLoginIp(String paramString)
  {
    this._$5 = paramString;
  }

  public Date getCurrentLoginTime()
  {
    return this._$4;
  }

  public void setCurrentLoginTime(Date paramDate)
  {
    this._$4 = paramDate;
  }

  public String getCurrentLoginIp()
  {
    return this._$3;
  }

  public void setCurrentLoginIp(String paramString)
  {
    this._$3 = paramString;
  }

  public String getResetKey()
  {
    return this._$2;
  }

  public void setResetKey(String paramString)
  {
    this._$2 = paramString;
  }

  public String getResetPwd()
  {
    return this._$1;
  }

  public void setResetPwd(String paramString)
  {
    this._$1 = paramString;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof User))
      return false;
    User localUser = (User)paramObject;
    if ((null == getId()) || (null == localUser.getId()))
      return false;
    return getId().equals(localUser.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$14)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$14 = str.hashCode();
    }
    return this._$14;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.base.BaseUser
 * JD-Core Version:    0.6.2
 */