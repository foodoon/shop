package guda.shop.manager;

import guda.shop.cms.entity.Shipments;
import com.jspgou.common.page.Pagination;
import java.util.List;

public abstract interface ShipmentsMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract List<Shipments> getlist(Long paramLong);

  public abstract void deleteByorderId(Long paramLong);

  public abstract Shipments findById(Long paramLong);

  public abstract Shipments save(Shipments paramShipments);

  public abstract Shipments update(Shipments paramShipments);

  public abstract Shipments deleteById(Long paramLong);

  public abstract Shipments[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ShipmentsMng
 * JD-Core Version:    0.6.2
 */