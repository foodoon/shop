package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseProductStandard;

public class ProductStandard extends BaseProductStandard {
    private static final long serialVersionUID = 1L;

    public ProductStandard() {
    }

    public ProductStandard(Long paramLong) {
        super(paramLong);
    }

    public ProductStandard(Long paramLong, Product paramProduct, Standard paramStandard, StandardType paramStandardType, String paramString, boolean paramBoolean) {
        super(paramLong, paramProduct, paramStandard, paramStandardType, paramString, paramBoolean);
    }
}

