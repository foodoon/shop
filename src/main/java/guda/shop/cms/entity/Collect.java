package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseCollect;

public class Collect extends BaseCollect {
    private static final long serialVersionUID = 1L;

    public Collect() {
    }

    public Collect(Integer paramInteger) {
        super(paramInteger);
    }

    public Collect(Integer paramInteger, ShopMember paramShopMember, Product paramProduct) {
        super(paramInteger, paramShopMember, paramProduct);
    }
}

