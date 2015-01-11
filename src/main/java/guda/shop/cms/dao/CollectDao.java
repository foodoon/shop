package guda.shop.cms.dao;

import guda.shop.cms.entity.Collect;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface CollectDao {
    public abstract Pagination getList(Integer paramInteger1, Integer paramInteger2, Long paramLong);

    public abstract Collect findById(Integer paramInteger);

    public abstract Collect save(Collect paramCollect);

    public abstract Collect updateByUpdater(Updater<Collect> paramUpdater);

    public abstract Collect deleteById(Integer paramInteger);

    public abstract List<Collect> findByProductId(Long paramLong);

    public abstract Collect findByProductFashionId(Long paramLong);
}

