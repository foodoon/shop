package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BasePayment;
import guda.shop.core.entity.Website;

import java.util.HashSet;
import java.util.Set;

public class Payment extends BasePayment {
    public Payment() {
    }

    public Payment(Long paramLong) {
        super(paramLong);
    }

    public Payment(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger, Boolean paramBoolean) {
        super(paramLong, paramWebsite, paramString, paramInteger, paramBoolean);
    }

    public void addToShippings(Shipping paramShipping) {
        if (paramShipping == null)
            return;
        Object localObject = getShippings();
        if (localObject == null) {
            localObject = new HashSet();
            setShippings((Set) localObject);
        }
        ((Set) localObject).add(paramShipping);
    }

    public Long[] getShippingIds() {
        Set localSet = getShippings();
        return Shipping.fetchIds(localSet);
    }
}

