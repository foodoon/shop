package guda.shop.manager;

import guda.shop.cms.entity.Exended;
iimport guda.shopcms.entity.ExendedItem;
import com.jspgou.common.page.Pagination;
import java.util.Collection;

public abstract interface ExendedItemMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract ExendedItem findById(Long paramLong);

  public abstract Collection<ExendedItem> save(Collection<ExendedItem> paramCollection, Exended paramExended);

  public abstract Collection<ExendedItem> update(Collection<ExendedItem> paramCollection, Exended paramExended);

  public abstract ExendedItem deleteById(Long paramLong);

  public abstract ExendedItem[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ExendedItemMng
 * JD-Core Version:    0.6.2
 */