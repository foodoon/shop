package guda.shop.cms.dao;

import guda.shop.cms.entity.PopularityItem;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface PopularityItemDao {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract List<PopularityItem> getlist(Long paramLong1, Long paramLong2);

    public abstract PopularityItem findById(Long paramLong1, Long paramLong2);

    public abstract PopularityItem findById(Long paramLong);

    public abstract PopularityItem save(PopularityItem paramPopularityItem);

    public abstract PopularityItem updateByUpdater(Updater<PopularityItem> paramUpdater);

    public abstract PopularityItem deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.PopularityItemDao
 * JD-Core Version:    0.6.2
 */