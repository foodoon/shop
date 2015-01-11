package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseCartItem;
import guda.shop.core.entity.Website;

public class CartItem extends BaseCartItem {
    private static final long serialVersionUID = 1L;

    public CartItem() {
    }

    public CartItem(Long paramLong) {
        super(paramLong);
    }

    public CartItem(Long paramLong, Website paramWebsite, Cart paramCart, Product paramProduct, Integer paramInteger) {
        super(paramLong, paramWebsite, paramCart, paramProduct, paramInteger);
    }

    public Double getSubtotal() {
        return Double.valueOf(getProduct().getMemberPrice().doubleValue() * getCount().intValue());
    }

    public Double getLimitSubtotal() {
        int i = getCount().intValue();
        Double localDouble1 = getProduct().getProductExt().getSeckillprice();
        Double localDouble2 = Double.valueOf(localDouble1.doubleValue() * i);
        return localDouble2;
    }

    public int getWeightForFreight() {
        Product localProduct = getProduct();
        return localProduct.getWeight().intValue() * getCount().intValue();
    }

    public int getCountForFreigt() {
        return getCount().intValue();
    }
}

