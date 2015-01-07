package guda.shop.core.dao;

import guda.shop.common.hibernate3.Updater;
iimport guda.shop.ommon.page.Pagination;
import com.jspgou.core.entity.Website;
import java.util.List;

public abstract interface WebsiteDao
{
  public abstract Website getUniqueWebsite();

  public abstract int countWebsite();

  public abstract Website findByDomain(String paramString);

  public abstract Pagination getAllPage(int paramInt1, int paramInt2);

  public abstract List<Website> getAllList();

  public abstract Website findById(Long paramLong);

  public abstract Website save(Website paramWebsite);

  public abstract Website updateByUpdater(Updater<Website> paramUpdater);

  public abstract Website deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.WebsiteDao
 * JD-Core Version:    0.6.2
 */