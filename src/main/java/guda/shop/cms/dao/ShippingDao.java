package guda.shop.cms.dao;

import guda.shop.cms.entity.Shipping;
import guda.shop.common.hibernate3.Updater;

import java.util.List;

public abstract interface ShippingDao {
    public abstract List<Shipping> getList(Long paramLong, boolean paramBoolean1, boolean paramBoolean2);

    public abstract Shipping findById(Long paramLong);

    public abstract Shipping save(Shipping paramShipping);

    public abstract Shipping updateByUpdater(Updater<Shipping> paramUpdater);

    public abstract Shipping deleteById(Long paramLong);
}

