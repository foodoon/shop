package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Cart;
import guda.shop.cms.entity.CartGift;
import guda.shop.cms.entity.CartItem;
import guda.shop.core.entity.Member;
import guda.shop.core.entity.Website;

import java.io.Serializable;
import java.util.Set;

public abstract class BaseCart
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "Cart";
    public static String PROP_MEMBER = "member";
    public static String PROP_WEBSITE = "website";
    public static String PROP_ADDRESS = "address";
    public static String PROP_TOTAL_ITEMS = "totalItems";
    public static String PROP_ID = "id";
    public static String PROP_SHIPPING = "shipping";
    public static String PROP_TOTAL_GIFTS = "totalGifts";
    public static String PROP_PAYMENT = "payment";
    private int _$8 = -2147483648;
    private Long _$7;
    private Integer _$6;
    private Integer _$5;
    private Member _$4;
    private Website _$3;
    private Set<CartItem> _$2;
    private Set<CartGift> _$1;

    public BaseCart() {
        initialize();
    }

    public BaseCart(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseCart(Long paramLong, Website paramWebsite, Integer paramInteger) {
        setId(paramLong);
        setWebsite(paramWebsite);
        setTotalItems(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$7;
    }

    public void setId(Long paramLong) {
        this._$7 = paramLong;
        this._$8 = -2147483648;
    }

    public Integer getTotalItems() {
        return this._$6;
    }

    public void setTotalItems(Integer paramInteger) {
        this._$6 = paramInteger;
    }

    public Integer getTotalGifts() {
        return this._$5;
    }

    public void setTotalGifts(Integer paramInteger) {
        this._$5 = paramInteger;
    }

    public Member getMember() {
        return this._$4;
    }

    public void setMember(Member paramMember) {
        this._$4 = paramMember;
    }

    public Website getWebsite() {
        return this._$3;
    }

    public void setWebsite(Website paramWebsite) {
        this._$3 = paramWebsite;
    }

    public Set<CartItem> getItems() {
        return this._$2;
    }

    public void setItems(Set<CartItem> paramSet) {
        this._$2 = paramSet;
    }

    public Set<CartGift> getGifts() {
        return this._$1;
    }

    public void setGifts(Set<CartGift> paramSet) {
        this._$1 = paramSet;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Cart))
            return false;
        Cart localCart = (Cart) paramObject;
        if ((null == getId()) || (null == localCart.getId()))
            return false;
        return getId().equals(localCart.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$8) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$8 = str.hashCode();
        }
        return this._$8;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseCart
 * JD-Core Version:    0.6.2
 */