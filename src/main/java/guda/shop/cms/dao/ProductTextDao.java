package guda.shop.cms.dao;

import guda.shop.cms.entity.ProductText;
import guda.shop.common.hibernate3.Updater;

public abstract interface ProductTextDao {
    public abstract ProductText updateByUpdater(Updater<ProductText> paramUpdater);

    public abstract ProductText save(ProductText paramProductText);
}

