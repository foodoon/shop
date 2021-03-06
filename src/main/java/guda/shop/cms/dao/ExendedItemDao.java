package guda.shop.cms.dao;

import guda.shop.cms.entity.ExendedItem;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

public abstract interface ExendedItemDao {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract ExendedItem findById(Long paramLong);

    public abstract ExendedItem save(ExendedItem paramExendedItem);

    public abstract ExendedItem updateByUpdater(Updater<ExendedItem> paramUpdater);

    public abstract ExendedItem deleteById(Long paramLong);
}

