package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopArticle;
import guda.shop.cms.entity.ShopArticleContent;
import guda.shop.common.page.Pagination;

public abstract interface ShopArticleContentMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract ShopArticleContent findById(Long paramLong);

  public abstract ShopArticleContent save(String paramString, ShopArticle paramShopArticle);

  public abstract ShopArticleContent update(ShopArticleContent paramShopArticleContent);

  public abstract ShopArticleContent deleteById(Long paramLong);

  public abstract ShopArticleContent[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ShopArticleContentMng
 * JD-Core Version:    0.6.2
 */