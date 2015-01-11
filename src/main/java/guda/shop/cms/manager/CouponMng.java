package guda.shop.cms.manager;

import guda.shop.cms.entity.Coupon;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface CouponMng {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract Pagination getPageByUsing(int paramInt1, int paramInt2);

    public abstract List<Coupon> getList();

    public abstract Coupon findById(Long paramLong);

    public abstract Coupon save(Coupon paramCoupon, Long paramLong);

    public abstract Coupon update(Coupon paramCoupon);

    public abstract Coupon deleteById(Long paramLong, String paramString);

    public abstract Coupon[] deleteByIds(Long[] paramArrayOfLong, String paramString);
}

