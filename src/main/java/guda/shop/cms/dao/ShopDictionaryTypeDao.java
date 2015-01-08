package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopDictionaryType;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface ShopDictionaryTypeDao {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract ShopDictionaryType findById(Long paramLong);

    public abstract List<ShopDictionaryType> findAll();

    public abstract ShopDictionaryType save(ShopDictionaryType paramShopDictionaryType);

    public abstract ShopDictionaryType updateByUpdater(Updater<ShopDictionaryType> paramUpdater);

    public abstract ShopDictionaryType deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ShopDictionaryTypeDao
 * JD-Core Version:    0.6.2
 */