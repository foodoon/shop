package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopArticle;
import guda.shop.common.page.Pagination;
import java.util.List;

public abstract interface ShopArticleMng
{
  public abstract Pagination getPage(Long paramLong1, Long paramLong2, int paramInt1, int paramInt2);

  public abstract Pagination getPageForTag(Long paramLong1, Long paramLong2, int paramInt1, int paramInt2);

  public abstract List<ShopArticle> getListForTag(Long paramLong1, Long paramLong2, int paramInt1, int paramInt2);

  public abstract ShopArticle findById(Long paramLong);

  public abstract ShopArticle save(ShopArticle paramShopArticle, Long paramLong, String paramString);

  public abstract ShopArticle update(ShopArticle paramShopArticle, Long paramLong, String paramString);

  public abstract ShopArticle deleteById(Long paramLong);

  public abstract ShopArticle[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ShopArticleMng
 * JD-Core Version:    0.6.2
 */