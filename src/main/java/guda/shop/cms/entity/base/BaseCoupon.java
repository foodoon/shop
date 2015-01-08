package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Coupon;
import guda.shop.core.entity.Website;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public abstract class BaseCoupon
        implements Serializable {
    public static String REF = "Coupon";
    public static String PROP_COUPON_COUNT = "couponCount";
    public static String PROP_WEBSITE = "website";
    public static String PROP_COUPON_TIME = "couponTime";
    public static String PROP_ID = "id";
    public static String PROP_COUPON_NAME = "couponName";
    public static String PROP_ISUSING = "isusing";
    public static String PROP_LEAST_PRICE = "leastPrice";
    public static String PROP_COUPON_END_TIME = "couponEndTime";
    public static String PROP_COUPON_PRICE = "couponPrice";
    public static String PROP_COUPON_PICTURE = "couponPicture";
    private int _$11 = -2147483648;
    private Long _$10;
    private String _$9;
    private Date _$8;
    private Date _$7;
    private String _$6;
    private BigDecimal _$5;
    private BigDecimal _$4;
    private Boolean _$3;
    private Integer _$2;
    private Website _$1;

    public BaseCoupon() {
        initialize();
    }

    public BaseCoupon(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseCoupon(Long paramLong, Website paramWebsite, String paramString1, Date paramDate1, Date paramDate2, String paramString2, BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2, Boolean paramBoolean, Integer paramInteger) {
        setId(paramLong);
        setWebsite(paramWebsite);
        setCouponName(paramString1);
        setCouponTime(paramDate1);
        setCouponEndTime(paramDate2);
        setCouponPicture(paramString2);
        setCouponPrice(paramBigDecimal1);
        setLeastPrice(paramBigDecimal2);
        setIsusing(paramBoolean);
        setCouponCount(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$10;
    }

    public void setId(Long paramLong) {
        this._$10 = paramLong;
        this._$11 = -2147483648;
    }

    public String getCouponName() {
        return this._$9;
    }

    public void setCouponName(String paramString) {
        this._$9 = paramString;
    }

    public Date getCouponTime() {
        return this._$8;
    }

    public void setCouponTime(Date paramDate) {
        this._$8 = paramDate;
    }

    public Date getCouponEndTime() {
        return this._$7;
    }

    public void setCouponEndTime(Date paramDate) {
        this._$7 = paramDate;
    }

    public String getCouponPicture() {
        return this._$6;
    }

    public void setCouponPicture(String paramString) {
        this._$6 = paramString;
    }

    public BigDecimal getCouponPrice() {
        return this._$5;
    }

    public void setCouponPrice(BigDecimal paramBigDecimal) {
        this._$5 = paramBigDecimal;
    }

    public BigDecimal getLeastPrice() {
        return this._$4;
    }

    public void setLeastPrice(BigDecimal paramBigDecimal) {
        this._$4 = paramBigDecimal;
    }

    public Boolean getIsusing() {
        return this._$3;
    }

    public void setIsusing(Boolean paramBoolean) {
        this._$3 = paramBoolean;
    }

    public Integer getCouponCount() {
        return this._$2;
    }

    public void setCouponCount(Integer paramInteger) {
        this._$2 = paramInteger;
    }

    public Website getWebsite() {
        return this._$1;
    }

    public void setWebsite(Website paramWebsite) {
        this._$1 = paramWebsite;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Coupon))
            return false;
        Coupon localCoupon = (Coupon) paramObject;
        if ((null == getId()) || (null == localCoupon.getId()))
            return false;
        return getId().equals(localCoupon.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$11) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$11 = str.hashCode();
        }
        return this._$11;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseCoupon
 * JD-Core Version:    0.6.2
 */