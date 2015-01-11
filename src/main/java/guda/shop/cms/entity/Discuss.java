package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseDiscuss;

public class Discuss extends BaseDiscuss {
    private static final long serialVersionUID = 1L;

    public Discuss() {
    }

    public Discuss(Long paramLong) {
        super(paramLong);
    }

    public Discuss(Long paramLong, ShopMember paramShopMember, Product paramProduct, String paramString) {
        super(paramLong, paramShopMember, paramProduct, paramString);
    }
}

