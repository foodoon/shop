package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopArticle;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface ShopArticleDao {
    public abstract Pagination getPage(Long paramLong1, Long paramLong2, int paramInt1, int paramInt2);

    public abstract Pagination getPageByChannel(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract Pagination getPageByWebsite(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract List<ShopArticle> getListByChannel(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract List<ShopArticle> getListByWebsite(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract ShopArticle findById(Long paramLong);

    public abstract ShopArticle save(ShopArticle paramShopArticle);

    public abstract ShopArticle updateByUpdater(Updater<ShopArticle> paramUpdater);

    public abstract ShopArticle deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ShopArticleDao
 * JD-Core Version:    0.6.2
 */