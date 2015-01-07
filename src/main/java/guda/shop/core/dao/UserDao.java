package guda.shop.core.dao;

import guda.shop.common.hibernate3.Updater;
iimport guda.shop.ommon.page.Pagination;
import com.jspgou.core.entity.User;

public abstract interface UserDao
{
  public abstract User getByUsername(String paramString);

  public abstract User getByEmail(String paramString);

  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract User findById(Long paramLong);

  public abstract User save(User paramUser);

  public abstract User updateByUpdater(Updater<User> paramUpdater);

  public abstract User deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.UserDao
 * JD-Core Version:    0.6.2
 */