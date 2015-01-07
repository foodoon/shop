package guda.shop.cms.manager;

import guda.shop.cms.entity.Exended;
iimport guda.shopcms.entity.ExendedItem;
import com.jspgou.common.page.Pagination;
import java.util.List;

public abstract interface ExendedMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract Exended findById(Long paramLong);

  public abstract List<Exended> getList();

  public abstract List<Exended> getList(Long paramLong);

  public abstract Exended save(Exended paramExended, Long[] paramArrayOfLong, List<ExendedItem> paramList);

  public abstract Exended update(Exended paramExended, Long[] paramArrayOfLong, List<ExendedItem> paramList);

  public abstract Exended deleteById(Long paramLong);

  public abstract Exended[] deleteByIds(Long[] paramArrayOfLong);

  public abstract Exended[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ExendedMng
 * JD-Core Version:    0.6.2
 */