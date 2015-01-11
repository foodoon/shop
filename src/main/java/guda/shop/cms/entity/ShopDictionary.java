package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShopDictionary;

public class ShopDictionary extends BaseShopDictionary {
    private static final long serialVersionUID = 1L;

    public ShopDictionary() {
    }

    public ShopDictionary(Long paramLong) {
        super(paramLong);
    }

    public ShopDictionary(Long paramLong, ShopDictionaryType paramShopDictionaryType, String paramString) {
        super(paramLong, paramShopDictionaryType, paramString);
    }
}

