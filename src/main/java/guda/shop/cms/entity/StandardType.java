package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseStandardType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StandardType extends BaseStandardType {
    private static final long serialVersionUID = 1L;

    public StandardType() {
    }

    public StandardType(Long paramLong) {
        super(paramLong);
    }

    public StandardType(Long paramLong, String paramString1, String paramString2, boolean paramBoolean) {
        super(paramLong, paramString1, paramString2, paramBoolean);
    }

    public static Long[] fetchIds(Collection<StandardType> paramCollection) {
        Long[] arrayOfLong = new Long[paramCollection.size()];
        int i = 0;
        Iterator localIterator = paramCollection.iterator();
        while (localIterator.hasNext()) {
            StandardType localStandardType = (StandardType) localIterator.next();
            arrayOfLong[(i++)] = localStandardType.getId();
        }
        return arrayOfLong;
    }

    public void removeFromCategorys(Category paramCategory) {
        Set localSet = getCategorys();
        if (localSet != null)
            localSet.remove(paramCategory);
    }

    public void addToCategory(Category paramCategory) {
        Object localObject = getCategorys();
        if (localObject == null) {
            localObject = new HashSet();
            setCategorys((Set) localObject);
        }
        ((Set) localObject).add(paramCategory);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.StandardType
 * JD-Core Version:    0.6.2
 */