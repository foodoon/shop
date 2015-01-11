package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseStandard;

import java.util.HashSet;
import java.util.Set;

public class Standard extends BaseStandard {
    private static final long serialVersionUID = 1L;

    public Standard() {
    }

    public Standard(Long paramLong) {
        super(paramLong);
    }

    public Standard(Long paramLong, StandardType paramStandardType, String paramString) {
        super(paramLong, paramStandardType, paramString);
    }

    public void addToProductFashions(ProductFashion paramProductFashion) {
        Object localObject = getFashions();
        if (localObject == null) {
            localObject = new HashSet();
            setFashions((Set) localObject);
        }
        ((Set) localObject).add(paramProductFashion);
    }
}

