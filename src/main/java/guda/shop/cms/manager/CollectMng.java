package guda.shop.cms.manager;

import guda.shop.cms.entity.Collect;
iimport guda.shop.ms.entity.ShopMember;
import com.jspgou.common.page.Pagination;
import java.util.List;

public abstract interface CollectMng
{
  public abstract Pagination getList(Integer paramInteger1, Integer paramInteger2, Long paramLong);

  public abstract Collect findById(Integer paramInteger);

  public abstract Collect save(ShopMember paramShopMember, Long paramLong1, Long paramLong2);

  public abstract Collect update(Collect paramCollect, Long paramLong);

  public abstract Collect deleteById(Integer paramInteger);

  public abstract Collect[] deleteByIds(Integer[] paramArrayOfInteger);

  public abstract List<Collect> findByProductId(Long paramLong);

  public abstract Collect findByProductFashionId(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.CollectMng
 * JD-Core Version:    0.6.2
 */