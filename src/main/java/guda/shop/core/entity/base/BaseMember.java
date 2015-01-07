package guda.shop.core.entity.base;

import guda.shop.core.entity.Member;
iimport guda.shop.ore.entity.User;
import com.jspgou.core.entity.Website;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseMember
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "Member";
  public static String PROP_WEBSITE = "website";
  public static String PROP_CREATE_TIME = "createTime";
  public static String PROP_DISABLED = "disabled";
  public static String PROP_USER = "user";
  public static String PROP_ID = "id";
  private int _$8 = -2147483648;
  private Long _$7;
  private Date _$6;
  private Boolean _$5;
  private Boolean _$4;
  private String _$3;
  private User _$2;
  private Website _$1;

  public BaseMember()
  {
    initialize();
  }

  public BaseMember(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseMember(Long paramLong, User paramUser, Website paramWebsite, Date paramDate, Boolean paramBoolean1, Boolean paramBoolean2, String paramString)
  {
    setId(paramLong);
    setUser(paramUser);
    setWebsite(paramWebsite);
    setCreateTime(paramDate);
    setDisabled(paramBoolean1);
    setActive(paramBoolean2);
    setActivationCode(paramString);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$7;
  }

  public void setId(Long paramLong)
  {
    this._$7 = paramLong;
    this._$8 = -2147483648;
  }

  public Date getCreateTime()
  {
    return this._$6;
  }

  public void setCreateTime(Date paramDate)
  {
    this._$6 = paramDate;
  }

  public Boolean getDisabled()
  {
    return this._$5;
  }

  public void setDisabled(Boolean paramBoolean)
  {
    this._$5 = paramBoolean;
  }

  public Boolean getActive()
  {
    return this._$4;
  }

  public void setActive(Boolean paramBoolean)
  {
    this._$4 = paramBoolean;
  }

  public String getActivationCode()
  {
    return this._$3;
  }

  public void setActivationCode(String paramString)
  {
    this._$3 = paramString;
  }

  public User getUser()
  {
    return this._$2;
  }

  public void setUser(User paramUser)
  {
    this._$2 = paramUser;
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
    if (!(paramObject instanceof Member))
      return false;
    Member localMember = (Member)paramObject;
    if ((null == getId()) || (null == localMember.getId()))
      return false;
    return getId().equals(localMember.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$8)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$8 = str.hashCode();
    }
    return this._$8;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.base.BaseMember
 * JD-Core Version:    0.6.2
 */