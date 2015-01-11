package guda.shop.cms.dao;

import guda.shop.cms.entity.BrandText;
import guda.shop.common.hibernate3.Updater;

public abstract interface BrandTextDao {
    public abstract BrandText save(BrandText paramBrandText);

    public abstract BrandText updateByUpdater(Updater<BrandText> paramUpdater);
}

