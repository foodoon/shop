package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.MemberCouponDao;
import guda.shop.cms.entity.MemberCoupon;
import guda.shop.cms.manager.MemberCouponMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MemberCouponMngImpl
        implements MemberCouponMng {

    @Autowired
    private MemberCouponDao _$1;

    public List<MemberCoupon> getList(Long paramLong) {
        List localList = this._$1.getList(paramLong);
        return localList;
    }

    public MemberCoupon findById(Long paramLong) {
        return this._$1.findById(paramLong);
    }

    @Transactional(readOnly = true)
    public List<MemberCoupon> getList(Long paramLong, BigDecimal paramBigDecimal) {
        return this._$1.getList(paramLong, new Date(), paramBigDecimal);
    }

    @Transactional(readOnly = true)
    public MemberCoupon findByCoupon(Long paramLong1, Long paramLong2) {
        return this._$1.findByCoupon(paramLong1, paramLong2);
    }

    public MemberCoupon save(MemberCoupon paramMemberCoupon) {
        return this._$1.save(paramMemberCoupon);
    }

    public MemberCoupon update(MemberCoupon paramMemberCoupon) {
        return this._$1.update(paramMemberCoupon);
    }
}

