package guda.shop.cms.manager;

import guda.shop.cms.entity.ProductType;

import java.util.List;

public abstract interface ProductTypeMng {
    public abstract List<ProductType> getList(Long paramLong);

    public abstract ProductType findById(Long paramLong);

    public abstract ProductType save(ProductType paramProductType);

    public abstract ProductType update(ProductType paramProductType);

    public abstract ProductType deleteById(Long paramLong);

    public abstract ProductType[] deleteByIds(Long[] paramArrayOfLong);
}

