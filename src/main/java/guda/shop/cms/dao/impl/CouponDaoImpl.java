package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.CouponDao;
import guda.shop.cms.entity.Coupon;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CouponDaoImpl extends HibernateBaseDao<Coupon, Long>
        implements CouponDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from Coupon bean");
        localFinder.append(" order by bean.id desc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public Pagination getPageByUsing(Date paramDate, int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from Coupon bean where bean.isusing=true");
        if (paramDate != null) {
            localFinder.append(" and bean.couponEndTime>:newTime");
            localFinder.setParam("newTime", paramDate);
        }
        localFinder.append(" order by bean.id asc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public List<Coupon> getList() {
        String str = "from Coupon bean where 1=1";
        return getSession().createQuery(str).list();
    }

    public Coupon findById(Long paramLong) {
        Coupon localCoupon = (Coupon) get(paramLong);
        return localCoupon;
    }

    public Coupon save(Coupon paramCoupon) {
        getSession().save(paramCoupon);
        return paramCoupon;
    }

    public Coupon deleteById(Long paramLong) {
        Coupon localCoupon = (Coupon) super.get(paramLong);
        if (localCoupon != null)
            getSession().delete(localCoupon);
        return localCoupon;
    }

    protected Class<Coupon> getEntityClass() {
        return Coupon.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.CouponDaoImpl
 * JD-Core Version:    0.6.2
 */