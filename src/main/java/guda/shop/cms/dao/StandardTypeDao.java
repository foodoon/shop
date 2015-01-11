package guda.shop.cms.dao;

import guda.shop.cms.entity.StandardType;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface StandardTypeDao {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract StandardType getByField(String paramString);

    public abstract StandardType findById(Long paramLong);

    public abstract List<StandardType> getList();

    public abstract List<StandardType> getList(Long paramLong);

    public abstract StandardType save(StandardType paramStandardType);

    public abstract StandardType updateByUpdater(Updater<StandardType> paramUpdater);

    public abstract StandardType deleteById(Long paramLong);
}

