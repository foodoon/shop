package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShipments;

public class Shipments extends BaseShipments {
    private static final long serialVersionUID = 1L;

    public Shipments() {
    }

    public Shipments(Long paramLong) {
        super(paramLong);
    }

    public Shipments(Long paramLong, Order paramOrder, ShopAdmin paramShopAdmin, String paramString1, String paramString2, String paramString3) {
        super(paramLong, paramOrder, paramShopAdmin, paramString1, paramString2, paramString3);
    }
}

