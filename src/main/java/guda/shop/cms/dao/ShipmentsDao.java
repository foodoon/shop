package guda.shop.cms.dao;

import guda.shop.cms.entity.Shipments;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.List;

public abstract interface ShipmentsDao
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract List<Shipments> getlist(Long paramLong);

  public abstract Shipments findById(Long paramLong);

  public abstract Shipments save(Shipments paramShipments);

  public abstract Shipments updateByUpdater(Updater<Shipments> paramUpdater);

  public abstract Shipments deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ShipmentsDao
 * JD-Core Version:    0.6.2
 */