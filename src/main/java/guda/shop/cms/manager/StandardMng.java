package guda.shop.cms.manager;

import guda.shop.cms.entity.Standard;
import guda.shop.common.page.Pagination;
import java.util.List;

public abstract interface StandardMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract Standard findById(Long paramLong);

  public abstract List<Standard> findByTypeId(Long paramLong);

  public abstract Standard save(Standard paramStandard);

  public abstract Standard update(Standard paramStandard);

  public abstract Standard deleteById(Long paramLong);

  public abstract Standard[] deleteByIds(Long[] paramArrayOfLong);

  public abstract Standard[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.StandardMng
 * JD-Core Version:    0.6.2
 */