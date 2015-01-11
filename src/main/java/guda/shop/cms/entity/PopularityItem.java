package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BasePopularityItem;

public class PopularityItem extends BasePopularityItem {
    private static final long serialVersionUID = 1L;

    public PopularityItem() {
    }

    public PopularityItem(Long paramLong) {
        super(paramLong);
    }

    public PopularityItem(Long paramLong, Cart paramCart, Integer paramInteger) {
        super(paramLong, paramCart, paramInteger);
    }
}

