package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseProductTypeProperty;

public class ProductTypeProperty extends BaseProductTypeProperty {
    private static final long serialVersionUID = 1L;

    public ProductTypeProperty() {
    }

    public ProductTypeProperty(Long paramLong) {
        super(paramLong);
    }

    public ProductTypeProperty(Long paramLong, ProductType paramProductType, String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3) {
        super(paramLong, paramProductType, paramString1, paramString2, paramInteger1, paramInteger2, paramInteger3, paramInteger4, paramBoolean1, paramBoolean2, paramBoolean3);
    }
}

