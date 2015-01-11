package guda.shop.cms.manager;

import guda.shop.cms.entity.Brand;
import guda.shop.cms.entity.BrandText;

public abstract interface BrandTextMng {
    public abstract BrandText save(Brand paramBrand, String paramString);

    public abstract BrandText update(BrandText paramBrandText);
}

