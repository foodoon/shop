package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopChannel;

import java.util.List;

public abstract interface ShopChannelMng {
    public abstract List<ShopChannel> getTopList(Long paramLong);

    public abstract List<ShopChannel> getChildList(Long paramLong1, Long paramLong2);

    public abstract List<ShopChannel> getList(Long paramLong);

    public abstract List<ShopChannel> getArticleList(Long paramLong);

    public abstract List<ShopChannel> getListForParent(Long paramLong1, Long paramLong2);

    public abstract List<ShopChannel> getListForParentNoSort(Long paramLong1, Long paramLong2);

    public abstract List<ShopChannel> getTopListForTag(Long paramLong, Integer paramInteger);

    public abstract ShopChannel getByPath(Long paramLong, String paramString);

    public abstract ShopChannel findById(Long paramLong);

    public abstract ShopChannel save(ShopChannel paramShopChannel, Long paramLong, String paramString);

    public abstract ShopChannel update(ShopChannel paramShopChannel, Long paramLong, String paramString);

    public abstract ShopChannel deleteById(Long paramLong);

    public abstract ShopChannel[] deleteByIds(Long[] paramArrayOfLong);

    public abstract ShopChannel[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger);

    public abstract List<ShopChannel> getAloneChannelList(Long paramLong);

    public abstract List<ShopChannel> getList(Long paramLong1, Long paramLong2, Long paramLong3);
}

