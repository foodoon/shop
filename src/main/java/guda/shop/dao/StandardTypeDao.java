package guda.shop.dao;

import guda.shop.cms.entity.StandardType;
iimport guda.shopcommon.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.List;

public abstract interface StandardTypeDao
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract StandardType getByField(String paramString);

  public abstract StandardType findById(Long paramLong);

  public abstract List<StandardType> getList();

  public abstract List<StandardType> getList(Long paramLong);

  public abstract StandardType save(StandardType paramStandardType);

  public abstract StandardType updateByUpdater(Updater<StandardType> paramUpdater);

  public abstract StandardType deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.StandardTypeDao
 * JD-Core Version:    0.6.2
 */