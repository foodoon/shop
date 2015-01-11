package guda.shop.cms.dao;

import guda.shop.cms.entity.PopularityGroup;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

public abstract interface PopularityGroupDao {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract PopularityGroup findById(Long paramLong);

    public abstract PopularityGroup save(PopularityGroup paramPopularityGroup);

    public abstract PopularityGroup updateByUpdater(Updater<PopularityGroup> paramUpdater);

    public abstract PopularityGroup deleteById(Long paramLong);
}

