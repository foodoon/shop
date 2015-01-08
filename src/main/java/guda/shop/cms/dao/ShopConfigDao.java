package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopConfig;
import guda.shop.common.hibernate3.Updater;

public abstract interface ShopConfigDao {
    public abstract ShopConfig findById(Long paramLong);

    public abstract ShopConfig save(ShopConfig paramShopConfig);

    public abstract ShopConfig updateByUpdater(Updater<ShopConfig> paramUpdater);

    public abstract ShopConfig deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ShopConfigDao
 * JD-Core Version:    0.6.2
 */