package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Cart;
import guda.shop.cms.entity.CartGift;
import guda.shop.cms.entity.Gift;
import guda.shop.core.entity.Website;

import java.io.Serializable;

public abstract class BaseCartGift
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "CartGift";
    public static String PROP_COUNT = "count";
    public static String PROP_GIFT = "gift";
    public static String PROP_WEBSITE = "website";
    public static String PROP_CART = "cart";
    public static String PROP_ID = "id";
    private int _$6 = -2147483648;
    private Long _$5;
    private Integer _$4;
    private Website _$3;
    private Cart _$2;
    private Gift _$1;

    public BaseCartGift() {
        initialize();
    }

    public BaseCartGift(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseCartGift(Long paramLong, Website paramWebsite, Cart paramCart, Gift paramGift, Integer paramInteger) {
        setId(paramLong);
        setWebsite(paramWebsite);
        setCart(paramCart);
        setGift(paramGift);
        setCount(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$5;
    }

    public void setId(Long paramLong) {
        this._$5 = paramLong;
        this._$6 = -2147483648;
    }

    public Integer getCount() {
        return this._$4;
    }

    public void setCount(Integer paramInteger) {
        this._$4 = paramInteger;
    }

    public Website getWebsite() {
        return this._$3;
    }

    public void setWebsite(Website paramWebsite) {
        this._$3 = paramWebsite;
    }

    public Cart getCart() {
        return this._$2;
    }

    public void setCart(Cart paramCart) {
        this._$2 = paramCart;
    }

    public Gift getGift() {
        return this._$1;
    }

    public void setGift(Gift paramGift) {
        this._$1 = paramGift;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof CartGift))
            return false;
        CartGift localCartGift = (CartGift) paramObject;
        if ((null == getId()) || (null == localCartGift.getId()))
            return false;
        return getId().equals(localCartGift.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$6) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$6 = str.hashCode();
        }
        return this._$6;
    }

    public String toString() {
        return super.toString();
    }
}

