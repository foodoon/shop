package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopAdmin;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

public abstract interface ShopAdminDao {
    public abstract Pagination getPage(Long paramLong, int paramInt1, int paramInt2);

    public abstract ShopAdmin findById(Long paramLong);

    public abstract ShopAdmin save(ShopAdmin paramShopAdmin);

    public abstract ShopAdmin updateByUpdater(Updater<ShopAdmin> paramUpdater);

    public abstract ShopAdmin deleteById(Long paramLong);
}

