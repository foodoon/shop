package guda.shop.core.entity;

import guda.shop.core.entity.base.BaseUser;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class User extends BaseUser
{
  private static final long serialVersionUID = 1L;

  public void init()
  {
    if (StringUtils.isBlank(getEmail()))
      setEmail(null);
    if (getCreateTime() == null)
      setCreateTime(new Date());
    setLoginCount(Long.valueOf(0L));
  }

  public User()
  {
  }

  public User(Long paramLong)
  {
    super(paramLong);
  }

  public User(Long paramLong1, String paramString1, String paramString2, Date paramDate, Long paramLong2)
  {
    super(paramLong1, paramString1, paramString2, paramDate, paramLong2);
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.User
 * JD-Core Version:    0.6.2
 */