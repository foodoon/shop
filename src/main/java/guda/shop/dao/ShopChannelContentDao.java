package guda.shop.dao;

import guda.shop.cms.entity.ShopChannelContent;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

public abstract interface ShopChannelContentDao
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract ShopChannelContent findById(Long paramLong);

  public abstract ShopChannelContent save(ShopChannelContent paramShopChannelContent);

  public abstract ShopChannelContent updateByUpdater(Updater<ShopChannelContent> paramUpdater);

  public abstract ShopChannelContent deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ShopChannelContentDao
 * JD-Core Version:    0.6.2
 */