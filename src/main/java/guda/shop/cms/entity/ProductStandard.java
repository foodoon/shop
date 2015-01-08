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

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ProductStandard
 * JD-Core Version:    0.6.2
 */