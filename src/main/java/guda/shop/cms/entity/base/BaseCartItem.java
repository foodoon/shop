package guda.shop.cms.entity.base;

import guda.shop.cms.entity.*;
import guda.shop.core.entity.Website;

import java.io.Serializable;

public abstract class BaseCartItem
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "CartItem";
    public static String PROP_COUNT = "count";
    public static String PROP_PRODUCT = "product";
    public static String PROP_PRODUCT_FASH = "productFash";
    public static String PROP_WEBSITE = "website";
    public static String PROP_CART = "cart";
    public static String PROP_ID = "id";
    public static String PROP_SCORE = "score";
    private int _$9 = -2147483648;
    private Long _$8;
    private Integer _$7;
    private Integer _$6;
    private Website _$5;
    private Cart _$4;
    private Product _$3;
    private ProductFashion _$2;
    private PopularityGroup _$1;

    public BaseCartItem() {
        initialize();
    }

    public BaseCartItem(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseCartItem(Long paramLong, Website paramWebsite, Cart paramCart, Product paramProduct, Integer paramInteger) {
        setId(paramLong);
        setWebsite(paramWebsite);
        setCart(paramCart);
        setProduct(paramProduct);
        setCount(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$8;
    }

    public void setId(Long paramLong) {
        this._$8 = paramLong;
        this._$9 = -2147483648;
    }

    public Integer getCount() {
        return this._$7;
    }

    public void setCount(Integer paramInteger) {
        this._$7 = paramInteger;
    }

    public Integer getScore() {
        return this._$6;
    }

    public void setScore(Integer paramInteger) {
        this._$6 = paramInteger;
    }

    public Website getWebsite() {
        return this._$5;
    }

    public void setWebsite(Website paramWebsite) {
        this._$5 = paramWebsite;
    }

    public Cart getCart() {
        return this._$4;
    }

    public void setCart(Cart paramCart) {
        this._$4 = paramCart;
    }

    public Product getProduct() {
        return this._$3;
    }

    public void setProduct(Product paramProduct) {
        this._$3 = paramProduct;
    }

    public ProductFashion getProductFash() {
        return this._$2;
    }

    public void setProductFash(ProductFashion paramProductFashion) {
        this._$2 = paramProductFashion;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof CartItem))
            return false;
        CartItem localCartItem = (CartItem) paramObject;
        if ((null == getId()) || (null == localCartItem.getId()))
            return false;
        return getId().equals(localCartItem.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$9) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$9 = str.hashCode();
        }
        return this._$9;
    }

    public String toString() {
        return super.toString();
    }

    public PopularityGroup getPopularityGroup() {
        return this._$1;
    }

    public void setPopularityGroup(PopularityGroup paramPopularityGroup) {
        this._$1 = paramPopularityGroup;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseCartItem
 * JD-Core Version:    0.6.2
 */