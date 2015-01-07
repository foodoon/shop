package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.MemberCouponDao;
import guda.shop.cms.entity.MemberCoupon;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class MemberCouponDaoImpl extends HibernateBaseDao<MemberCoupon, Long>
  implements MemberCouponDao
{
  public MemberCoupon findByCoupon(Long paramLong1, Long paramLong2)
  {
    String str = "from MemberCoupon bean where bean.member.id=? and bean.coupon.id=?";
    Query localQuery = getSession().createQuery(str);
    localQuery.setParameter(0, paramLong1).setParameter(1, paramLong2);
    localQuery.setMaxResults(1);
    return (MemberCoupon)localQuery.setCacheable(true).uniqueResult();
  }

  public List<MemberCoupon> getList(Long paramLong, Date paramDate, BigDecimal paramBigDecimal)
  {
    Finder localFinder = Finder.create("select bean from MemberCoupon bean where bean.isuse=false");
    if (paramLong != null)
    {
      localFinder.append(" and bean.member.id=:id");
      localFinder.setParam("id", paramLong);
    }
    if (paramDate != null)
    {
      localFinder.append(" and bean.coupon.isusing=true");
      localFinder.append(" and bean.coupon.couponEndTime>:newTime");
      localFinder.append(" and bean.coupon.couponTime<:newTime");
      localFinder.setParam("newTime", paramDate);
    }
    if (paramBigDecimal != null)
    {
      localFinder.append(" and bean.coupon.leastPrice<=:price");
      localFinder.setParam("price", paramBigDecimal);
    }
    return find(localFinder);
  }

  public List<MemberCoupon> getList(Long paramLong)
  {
    String str = "from MemberCoupon bean where bean.member.id=:id";
    return getSession().createQuery(str).setParameter("id", paramLong).list();
  }

  public MemberCoupon findById(Long paramLong)
  {
    MemberCoupon localMemberCoupon = (MemberCoupon)get(paramLong);
    return localMemberCoupon;
  }

  public MemberCoupon update(MemberCoupon paramMemberCoupon)
  {
    getSession().update(paramMemberCoupon);
    return paramMemberCoupon;
  }

  public MemberCoupon save(MemberCoupon paramMemberCoupon)
  {
    getSession().save(paramMemberCoupon);
    return paramMemberCoupon;
  }

  public MemberCoupon deleteById(Long paramLong)
  {
    MemberCoupon localMemberCoupon = (MemberCoupon)super.get(paramLong);
    if (localMemberCoupon != null)
      getSession().delete(localMemberCoupon);
    return localMemberCoupon;
  }

  protected Class<MemberCoupon> getEntityClass()
  {
    return MemberCoupon.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.MemberCouponDaoImpl
 * JD-Core Version:    0.6.2
 */