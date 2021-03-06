package guda.shop.cms.manager;

import guda.shop.cms.entity.PopularityGroup;
import guda.shop.common.page.Pagination;

public abstract interface PopularityGroupMng {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract PopularityGroup findById(Long paramLong);

    public abstract PopularityGroup save(PopularityGroup paramPopularityGroup);

    public abstract PopularityGroup update(PopularityGroup paramPopularityGroup);

    public abstract PopularityGroup deleteById(Long paramLong);

    public abstract PopularityGroup[] deleteByIds(Long[] paramArrayOfLong);

    public abstract void addProduct(PopularityGroup paramPopularityGroup, Long[] paramArrayOfLong);

    public abstract void updateProduct(PopularityGroup paramPopularityGroup, Long[] paramArrayOfLong);
}

