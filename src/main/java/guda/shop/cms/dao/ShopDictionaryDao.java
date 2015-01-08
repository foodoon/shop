package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopDictionary;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface ShopDictionaryDao {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract Pagination getPage(String paramString, Long paramLong, int paramInt1, int paramInt2);

    public abstract ShopDictionary findById(Long paramLong);

    public abstract List<ShopDictionary> getListByType(Long paramLong);

    public abstract ShopDictionary save(ShopDictionary paramShopDictionary);

    public abstract ShopDictionary updateByUpdater(Updater<ShopDictionary> paramUpdater);

    public abstract ShopDictionary deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ShopDictionaryDao
 * JD-Core Version:    0.6.2
 */