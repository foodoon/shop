package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseProductTag;
import guda.shop.core.entity.Website;

public class ProductTag extends BaseProductTag {
    private static final long serialVersionUID = 1L;

    public ProductTag() {
    }

    public ProductTag(Long paramLong) {
        super(paramLong);
    }

    public ProductTag(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger) {
        super(paramLong, paramWebsite, paramString, paramInteger);
    }

    public void increaseCount() {
        addCount(1);
    }

    public void reduceCount() {
        addCount(-1);
    }

    public void addCount(int paramInt) {
        Integer localInteger = getCount();
        if (localInteger != null)
            paramInt += localInteger.intValue();
        if (paramInt < 0)
            paramInt = 0;
        setCount(Integer.valueOf(paramInt));
    }

    public void init() {
        setCount(Integer.valueOf(0));
    }
}

