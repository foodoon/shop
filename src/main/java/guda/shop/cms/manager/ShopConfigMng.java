package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopConfig;

public abstract interface ShopConfigMng {
    public abstract ShopConfig findById(Long paramLong);

    public abstract ShopConfig save(ShopConfig paramShopConfig);

    public abstract ShopConfig update(ShopConfig paramShopConfig);

    public abstract ShopConfig deleteById(Long paramLong);
}

