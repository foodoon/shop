package guda.shop.cms.dao;

import guda.shop.cms.entity.ProductStandard;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface ProductStandardDao {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract ProductStandard findById(Long paramLong);

    public abstract List<ProductStandard> findByProductIdAndStandardId(Long paramLong1, Long paramLong2);

    public abstract List<ProductStandard> findByProductId(Long paramLong);

    public abstract ProductStandard save(ProductStandard paramProductStandard);

    public abstract ProductStandard updateByUpdater(Updater<ProductStandard> paramUpdater);

    public abstract ProductStandard deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ProductStandardDao
 * JD-Core Version:    0.6.2
 */