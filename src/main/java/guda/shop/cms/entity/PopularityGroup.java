package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BasePopularityGroup;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PopularityGroup extends BasePopularityGroup {
    private static final long serialVersionUID = 1L;

    public PopularityGroup() {
    }

    public PopularityGroup(Long paramLong) {
        super(paramLong);
    }

    public PopularityGroup(Long paramLong, String paramString, Date paramDate1, Date paramDate2, Double paramDouble) {
        super(paramLong, paramString, paramDate1, paramDate2, paramDouble);
    }

    public void addToProducts(Product paramProduct) {
        if (paramProduct == null)
            return;
        Object localObject = getProducts();
        if (localObject == null) {
            localObject = new HashSet();
            setProducts((Set) localObject);
        }
        ((Set) localObject).add(paramProduct);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.PopularityGroup
 * JD-Core Version:    0.6.2
 */