package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseExended;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Exended extends BaseExended {
    private static final long serialVersionUID = 1L;

    public Exended() {
    }

    public Exended(Long paramLong) {
        super(paramLong);
    }

    public Exended(Long paramLong, String paramString1, String paramString2) {
        super(paramLong, paramString1, paramString2);
    }

    public void addToProductTypes(ProductType paramProductType) {
        Object localObject = getProductTypes();
        if (localObject == null) {
            localObject = new HashSet();
            setProductTypes((Set) localObject);
        }
        ((Set) localObject).add(paramProductType);
    }

    public Long[] getProductTypeIds() {
        Set localSet = getProductTypes();
        if (localSet == null)
            return null;
        Long[] arrayOfLong = new Long[localSet.size()];
        int i = 0;
        Iterator localIterator = localSet.iterator();
        while (localIterator.hasNext()) {
            ProductType localProductType = (ProductType) localIterator.next();
            arrayOfLong[i] = localProductType.getId();
            i++;
        }
        return arrayOfLong;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Exended
 * JD-Core Version:    0.6.2
 */