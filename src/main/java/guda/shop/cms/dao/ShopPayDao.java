package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopPay;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

public abstract interface ShopPayDao {
    public abstract Pagination getPageShopPay(int paramInt1, int paramInt2);

    public abstract ShopPay findById(Integer paramInteger);

    public abstract ShopPay save(ShopPay paramShopPay);

    public abstract ShopPay updateByUpdater(Updater<ShopPay> paramUpdater);

    public abstract ShopPay deleteById(Integer paramInteger);
}

