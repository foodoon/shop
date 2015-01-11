package guda.shop.cms.dao;

import guda.shop.cms.entity.ProductExt;
import guda.shop.common.hibernate3.Updater;

public abstract interface ProductExtDao {
    public abstract ProductExt findById(Long paramLong);

    public abstract ProductExt save(ProductExt paramProductExt);

    public abstract ProductExt updateByUpdater(Updater<ProductExt> paramUpdater);
}

