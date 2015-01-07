package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopArticleContent;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

public abstract interface ShopArticleContentDao
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract ShopArticleContent findById(Long paramLong);

  public abstract ShopArticleContent save(ShopArticleContent paramShopArticleContent);

  public abstract ShopArticleContent updateByUpdater(Updater<ShopArticleContent> paramUpdater);

  public abstract ShopArticleContent deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ShopArticleContentDao
 * JD-Core Version:    0.6.2
 */