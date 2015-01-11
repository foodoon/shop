package guda.shop.cms.manager;

import guda.shop.cms.entity.ProductText;

public abstract interface ProductTextMng {
    public abstract ProductText update(ProductText paramProductText);

    public abstract ProductText save(ProductText paramProductText);
}

