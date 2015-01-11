package guda.shop.cms.dao;

import guda.shop.cms.entity.Brand;
import guda.shop.common.hibernate3.Updater;

import java.util.List;

public abstract interface BrandDao {
    public abstract List<Brand> getAllList();

    public abstract List<Brand> getList();

    public abstract List<Brand> getList(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract Brand findById(Long paramLong);

    public abstract Brand save(Brand paramBrand);

    public abstract Brand updateByUpdater(Updater<Brand> paramUpdater);

    public abstract Brand deleteById(Long paramLong);

    public abstract List<Brand> getListByCate(Long paramLong);

    public abstract Brand getsiftBrand();

    public abstract int countByBrandName(String paramString);
}

