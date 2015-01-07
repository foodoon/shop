package guda.shop.dao;

import guda.shop.cms.entity.Standard;
iimport guda.shopcommon.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.List;

public abstract interface StandardDao
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract Standard findById(Long paramLong);

  public abstract List<Standard> findByTypeId(Long paramLong);

  public abstract Standard save(Standard paramStandard);

  public abstract Standard updateByUpdater(Updater<Standard> paramUpdater);

  public abstract Standard deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.StandardDao
 * JD-Core Version:    0.6.2
 */