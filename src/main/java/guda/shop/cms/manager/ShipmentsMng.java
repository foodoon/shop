package guda.shop.cms.manager;

import guda.shop.cms.entity.Shipments;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface ShipmentsMng {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract List<Shipments> getlist(Long paramLong);

    public abstract void deleteByorderId(Long paramLong);

    public abstract Shipments findById(Long paramLong);

    public abstract Shipments save(Shipments paramShipments);

    public abstract Shipments update(Shipments paramShipments);

    public abstract Shipments deleteById(Long paramLong);

    public abstract Shipments[] deleteByIds(Long[] paramArrayOfLong);
}

