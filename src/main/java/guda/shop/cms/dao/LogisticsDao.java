package guda.shop.cms.dao;

import guda.shop.cms.entity.Logistics;
import guda.shop.common.hibernate3.Updater;

import java.util.List;

public abstract interface LogisticsDao {
    public abstract List<Logistics> getAllList();

    public abstract Logistics findById(Long paramLong);

    public abstract Logistics save(Logistics paramLogistics);

    public abstract Logistics updateByUpdater(Updater<Logistics> paramUpdater);

    public abstract Logistics deleteById(Long paramLong);
}

