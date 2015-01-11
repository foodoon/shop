package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseGiftExchange;

import java.util.Date;

public class GiftExchange extends BaseGiftExchange {
    private static final long serialVersionUID = 1L;

    public GiftExchange() {
    }

    public GiftExchange(Long paramLong) {
        super(paramLong);
    }

    public GiftExchange(Long paramLong, ShopMember paramShopMember, Gift paramGift, Date paramDate, Integer paramInteger) {
        super(paramLong, paramShopMember, paramGift, paramDate, paramInteger);
    }
}

