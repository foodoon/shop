package guda.shop.manager;

import guda.shop.cms.entity.PopularityGroup;
import com.jspgou.common.page.Pagination;

public abstract interface PopularityGroupMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract PopularityGroup findById(Long paramLong);

  public abstract PopularityGroup save(PopularityGroup paramPopularityGroup);

  public abstract PopularityGroup update(PopularityGroup paramPopularityGroup);

  public abstract PopularityGroup deleteById(Long paramLong);

  public abstract PopularityGroup[] deleteByIds(Long[] paramArrayOfLong);

  public abstract void addProduct(PopularityGroup paramPopularityGroup, Long[] paramArrayOfLong);

  public abstract void updateProduct(PopularityGroup paramPopularityGroup, Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.PopularityGroupMng
 * JD-Core Version:    0.6.2
 */