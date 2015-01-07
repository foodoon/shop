package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.MemberCouponDao;
iimport guda.shopcms.entity.MemberCoupon;
import com.jspgou.cms.manager.MemberCouponMng;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberCouponMngImpl
  implements MemberCouponMng
{

  @Autowired
  private MemberCouponDao _$1;

  public List<MemberCoupon> getList(Long paramLong)
  {
    List localList = this._$1.getList(paramLong);
    return localList;
  }

  public MemberCoupon findById(Long paramLong)
  {
    return this._$1.findById(paramLong);
  }

  @Transactional(readOnly=true)
  public List<MemberCoupon> getList(Long paramLong, BigDecimal paramBigDecimal)
  {
    return this._$1.getList(paramLong, new Date(), paramBigDecimal);
  }

  @Transactional(readOnly=true)
  public MemberCoupon findByCoupon(Long paramLong1, Long paramLong2)
  {
    return this._$1.findByCoupon(paramLong1, paramLong2);
  }

  public MemberCoupon save(MemberCoupon paramMemberCoupon)
  {
    return this._$1.save(paramMemberCoupon);
  }

  public MemberCoupon update(MemberCoupon paramMemberCoupon)
  {
    return this._$1.update(paramMemberCoupon);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.MemberCouponMngImpl
 * JD-Core Version:    0.6.2
 */