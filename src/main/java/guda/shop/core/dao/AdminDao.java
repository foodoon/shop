package guda.shop.core.dao;

import guda.shop.common.hibernate3.Updater;
import com.jspgou.core.entity.Admin;

public abstract interface AdminDao
{
  public abstract Admin getByUserId(Long paramLong1, Long paramLong2);

  public abstract Admin findById(Long paramLong);

  public abstract Admin save(Admin paramAdmin);

  public abstract Admin updateByUpdater(Updater<Admin> paramUpdater);

  public abstract Admin deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.AdminDao
 * JD-Core Version:    0.6.2
 */