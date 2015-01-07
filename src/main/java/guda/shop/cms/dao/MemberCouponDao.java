package guda.shop.cms.dao;

import guda.shop.cms.entity.MemberCoupon;
import guda.shop.common.hibernate3.Updater;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract interface MemberCouponDao
{
  public abstract MemberCoupon findByCoupon(Long paramLong1, Long paramLong2);

  public abstract MemberCoupon findById(Long paramLong);

  public abstract MemberCoupon save(MemberCoupon paramMemberCoupon);

  public abstract MemberCoupon updateByUpdater(Updater<MemberCoupon> paramUpdater);

  public abstract MemberCoupon deleteById(Long paramLong);

  public abstract List<MemberCoupon> getList(Long paramLong, Date paramDate, BigDecimal paramBigDecimal);

  public abstract List<MemberCoupon> getList(Long paramLong);

  public abstract MemberCoupon update(MemberCoupon paramMemberCoupon);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.MemberCouponDao
 * JD-Core Version:    0.6.2
 */