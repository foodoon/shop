package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseBrand;
import guda.shop.core.entity.Website;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Brand extends BaseBrand {
    private static final long serialVersionUID = 1L;

    public Brand() {
    }

    public Brand(Long paramLong) {
        super(paramLong);
    }

    public Brand(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger) {
        super(paramLong, paramWebsite, paramString, paramInteger);
    }

    public String getText() {
        BrandText localBrandText = getBrandText();
        if (localBrandText != null)
            return localBrandText.getText();
        return null;
    }

    public BrandText getBrandText() {
        Set localSet = getBrandTextSet();
        if (localSet != null) {
            Iterator localIterator = localSet.iterator();
            if (localIterator.hasNext())
                return (BrandText) localIterator.next();
        }
        return null;
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

