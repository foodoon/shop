package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseCoupon;
import guda.shop.core.entity.Website;

import java.math.BigDecimal;
import java.util.Date;

public class Coupon extends BaseCoupon {
    private static final long serialVersionUID = 1L;

    public Coupon() {
    }

    public Coupon(Long paramLong) {
        super(paramLong);
    }

    public Coupon(Long paramLong, Website paramWebsite, String paramString1, Date paramDate1, Date paramDate2, String paramString2, BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2, Boolean paramBoolean, Integer paramInteger) {
        super(paramLong, paramWebsite, paramString1, paramDate1, paramDate2, paramString2, paramBigDecimal1, paramBigDecimal2, paramBoolean, paramInteger);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Coupon
 * JD-Core Version:    0.6.2
 */