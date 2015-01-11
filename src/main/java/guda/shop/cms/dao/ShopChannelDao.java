package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopChannel;
import guda.shop.common.hibernate3.Updater;

import java.util.List;

public abstract interface ShopChannelDao {
    public abstract List<ShopChannel> getTopList(Long paramLong, boolean paramBoolean, Integer paramInteger);

    public abstract List<ShopChannel> getChildList(Long paramLong1, Long paramLong2);

    public abstract List<ShopChannel> getListForChild(Long paramLong1, Long paramLong2);

    public abstract List<ShopChannel> getList(Long paramLong);

    public abstract List<ShopChannel> getList(Long paramLong, Integer paramInteger);

    public abstract List<ShopChannel> getListForParent(Long paramLong1, Long paramLong2);

    public abstract ShopChannel getByPath(Long paramLong, String paramString);

    public abstract ShopChannel findById(Long paramLong);

    public abstract ShopChannel save(ShopChannel paramShopChannel);

    public abstract ShopChannel updateByUpdater(Updater<ShopChannel> paramUpdater);

    public abstract ShopChannel deleteById(Long paramLong);

    public abstract List<ShopChannel> getList(Long paramLong1, Integer paramInteger, Long paramLong2, Long paramLong3);
}

