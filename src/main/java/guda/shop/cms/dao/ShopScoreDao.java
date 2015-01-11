package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopScore;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.Date;
import java.util.List;

public abstract interface ShopScoreDao {
    public abstract Pagination getPage(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, Date paramDate1, Date paramDate2, Integer paramInteger1, Integer paramInteger2);

    public abstract List<ShopScore> getlist(String paramString);

    public abstract ShopScore findById(Long paramLong);

    public abstract ShopScore save(ShopScore paramShopScore);

    public abstract ShopScore updateByUpdater(Updater<ShopScore> paramUpdater);

    public abstract ShopScore deleteById(Long paramLong);
}

