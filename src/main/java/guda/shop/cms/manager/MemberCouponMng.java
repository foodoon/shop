package guda.shop.cms.manager;

import guda.shop.cms.entity.MemberCoupon;

import java.math.BigDecimal;
import java.util.List;

public abstract interface MemberCouponMng {
    public abstract MemberCoupon findByCoupon(Long paramLong1, Long paramLong2);

    public abstract MemberCoupon findById(Long paramLong);

    public abstract List<MemberCoupon> getList(Long paramLong, BigDecimal paramBigDecimal);

    public abstract List<MemberCoupon> getList(Long paramLong);

    public abstract MemberCoupon save(MemberCoupon paramMemberCoupon);

    public abstract MemberCoupon update(MemberCoupon paramMemberCoupon);
}

