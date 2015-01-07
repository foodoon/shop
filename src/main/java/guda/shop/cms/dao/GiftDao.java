package guda.shop.cms.dao;

import guda.shop.cms.entity.Gift;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

public abstract interface GiftDao
{
  public abstract Pagination getPageGift(int paramInt1, int paramInt2);

  public abstract Gift findById(Long paramLong);

  public abstract Gift save(Gift paramGift);

  public abstract Gift updateByUpdater(Updater<Gift> paramUpdater);

  public abstract Gift deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.GiftDao
 * JD-Core Version:    0.6.2
 */