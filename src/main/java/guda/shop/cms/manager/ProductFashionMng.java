package guda.shop.cms.manager;

import guda.shop.cms.entity.ProductFashion;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface ProductFashionMng {
    public abstract Pagination getPage(Long paramLong, int paramInt1, int paramInt2);

    public abstract ProductFashion deleteById(Long paramLong);

    public abstract ProductFashion[] deleteByIds(Long[] paramArrayOfLong);

    public abstract ProductFashion findById(Long paramLong);

    public abstract List<ProductFashion> findByProductId(Long paramLong);

    public abstract ProductFashion update(ProductFashion paramProductFashion);

    public abstract ProductFashion update(ProductFashion paramProductFashion, String[] paramArrayOfString);

    public abstract Integer productLackCount(Integer paramInteger1, Integer paramInteger2);

    public abstract Pagination productLack(Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2);

    public abstract Pagination getSaleTopPage(int paramInt1, int paramInt2);

    public abstract ProductFashion save(ProductFashion paramProductFashion, String[] paramArrayOfString);

    public abstract Boolean getOneFash(Long paramLong);

    public abstract void addStandard(ProductFashion paramProductFashion, String[] paramArrayOfString);

    public abstract void updateStandard(ProductFashion paramProductFashion, String[] paramArrayOfString);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ProductFashionMng
 * JD-Core Version:    0.6.2
 */