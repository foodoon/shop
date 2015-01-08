package guda.shop.cms.entity.base;

import guda.shop.cms.entity.PaymentPlugins;

import java.io.Serializable;

public abstract class BasePaymentPlugins
        implements Serializable {
    public static String REF = "PaymentPlugins";
    public static String PROP_NAME = "name";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_SELLER_KEY = "sellerKey";
    public static String PROP_ID = "id";
    public static String PROP_IMG_PATH = "imgPath";
    public static String PROP_SELLER_EMAIL = "sellerEmail";
    public static String PROP_PARTNER = "partner";
    public static String PROP_CODE = "code";
    public static String PROP_PRIORITY = "priority";
    private int _$10 = -2147483648;
    private Long _$9;
    private String _$8;
    private String _$7;
    private String _$6;
    private Integer _$5;
    private String _$4;
    private String _$3;
    private String _$2;
    private String _$1;

    public BasePaymentPlugins() {
        initialize();
    }

    public BasePaymentPlugins(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BasePaymentPlugins(Long paramLong, String paramString1, String paramString2, Integer paramInteger) {
        setId(paramLong);
        setName(paramString1);
        setCode(paramString2);
        setPriority(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$9;
    }

    public void setId(Long paramLong) {
        this._$9 = paramLong;
        this._$10 = -2147483648;
    }

    public String getName() {
        return this._$8;
    }

    public void setName(String paramString) {
        this._$8 = paramString;
    }

    public String getCode() {
        return this._$7;
    }

    public void setCode(String paramString) {
        this._$7 = paramString;
    }

    public String getDescription() {
        return this._$6;
    }

    public void setDescription(String paramString) {
        this._$6 = paramString;
    }

    public Integer getPriority() {
        return this._$5;
    }

    public void setPriority(Integer paramInteger) {
        this._$5 = paramInteger;
    }

    public String getImgPath() {
        return this._$4;
    }

    public void setImgPath(String paramString) {
        this._$4 = paramString;
    }

    public String getPartner() {
        return this._$3;
    }

    public void setPartner(String paramString) {
        this._$3 = paramString;
    }

    public String getSellerKey() {
        return this._$2;
    }

    public void setSellerKey(String paramString) {
        this._$2 = paramString;
    }

    public String getSellerEmail() {
        return this._$1;
    }

    public void setSellerEmail(String paramString) {
        this._$1 = paramString;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof PaymentPlugins))
            return false;
        PaymentPlugins localPaymentPlugins = (PaymentPlugins) paramObject;
        if ((null == getId()) || (null == localPaymentPlugins.getId()))
            return false;
        return getId().equals(localPaymentPlugins.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$10) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$10 = str.hashCode();
        }
        return this._$10;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BasePaymentPlugins
 * JD-Core Version:    0.6.2
 */