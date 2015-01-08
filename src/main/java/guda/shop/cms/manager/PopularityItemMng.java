package guda.shop.cms.manager;

import guda.shop.cms.entity.Cart;
import guda.shop.cms.entity.PopularityItem;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface PopularityItemMng {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract List<PopularityItem> getlist(Long paramLong1, Long paramLong2);

    public abstract PopularityItem findById(Long paramLong);

    public abstract PopularityItem findById(Long paramLong1, Long paramLong2);

    public abstract PopularityItem save(PopularityItem paramPopularityItem);

    public abstract PopularityItem update(PopularityItem paramPopularityItem);

    public abstract PopularityItem deleteById(Long paramLong);

    public abstract PopularityItem[] deleteByIds(Long[] paramArrayOfLong);

    public abstract void save(Cart paramCart, Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.PopularityItemMng
 * JD-Core Version:    0.6.2
 */