package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopPay;

public abstract interface ShopPayMng {
    public abstract ShopPay findById(Integer paramInteger);

    public abstract ShopPay save(ShopPay paramShopPay);

    public abstract ShopPay updateByUpdater(ShopPay paramShopPay);

    public abstract ShopPay deleteById(Integer paramInteger);

    public abstract ShopPay[] deleteByIds(Integer[] paramArrayOfInteger);
}

