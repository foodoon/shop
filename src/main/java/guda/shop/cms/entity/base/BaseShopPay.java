package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ShopPay;

import java.io.Serializable;

public abstract class BaseShopPay
        implements Serializable {
    public static String REF = "ShopPay";
    public static String PROP_BANK_NUM = "bankNum";
    public static String PROP_BANKID = "bankid";
    public static String PROP_ADDRESS = "address";
    public static String PROP_ID = "id";
    public static String PROP_BANKKEY = "bankkey";
    private int _$6 = -2147483648;
    private Integer _$5;
    private String _$4;
    private String _$3;
    private String _$2;
    private String _$1;

    public BaseShopPay() {
        initialize();
    }

    public BaseShopPay(Integer paramInteger) {
        setId(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Integer getId() {
        return this._$5;
    }

    public void setId(Integer paramInteger) {
        this._$5 = paramInteger;
        this._$6 = -2147483648;
    }

    public String getAddress() {
        return this._$4;
    }

    public void setAddress(String paramString) {
        this._$4 = paramString;
    }

    public String getBankNum() {
        return this._$3;
    }

    public void setBankNum(String paramString) {
        this._$3 = paramString;
    }

    public String getBankid() {
        return this._$2;
    }

    public void setBankid(String paramString) {
        this._$2 = paramString;
    }

    public String getBankkey() {
        return this._$1;
    }

    public void setBankkey(String paramString) {
        this._$1 = paramString;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof ShopPay))
            return false;
        ShopPay localShopPay = (ShopPay) paramObject;
        if ((null == getId()) || (null == localShopPay.getId()))
            return false;
        return getId().equals(localShopPay.getId());
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

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopPay
 * JD-Core Version:    0.6.2
 */