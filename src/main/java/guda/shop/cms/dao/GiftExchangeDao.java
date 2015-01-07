package guda.shop.cms.dao;

import guda.shop.cms.entity.GiftExchange;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.List;

public abstract interface GiftExchangeDao
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract List<GiftExchange> getlist(Long paramLong);

  public abstract GiftExchange findById(Long paramLong);

  public abstract GiftExchange save(GiftExchange paramGiftExchange);

  public abstract GiftExchange updateByUpdater(Updater<GiftExchange> paramUpdater);

  public abstract GiftExchange deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.GiftExchangeDao
 * JD-Core Version:    0.6.2
 */