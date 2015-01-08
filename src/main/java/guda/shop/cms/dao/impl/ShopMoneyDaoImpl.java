package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopMoneyDao;
import guda.shop.cms.entity.ShopMoney;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class ShopMoneyDaoImpl extends HibernateBaseDao<ShopMoney, Long>
        implements ShopMoneyDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public Pagination getPage(Long paramLong, Boolean paramBoolean, Date paramDate1, Date paramDate2, Integer paramInteger1, Integer paramInteger2) {
        Finder localFinder = Finder.create("select bean from ShopMoney bean where 1=1 ");
        if (paramLong != null)
            localFinder.append(" and bean.member.id=:memberId").setParam("memberId", paramLong);
        if (paramBoolean != null)
            localFinder.append(" and bean.status=:status").setParam("status", paramBoolean);
        if (paramDate1 != null) {
            localFinder.append(" and bean.createTime>:startTime");
            localFinder.setParam("startTime", paramDate1);
        }
        if (paramDate2 != null) {
            localFinder.append(" and bean.createTime<:endTime");
            localFinder.setParam("endTime", paramDate2);
        }
        localFinder.append(" order by bean.id desc");
        return find(localFinder, paramInteger2.intValue(), paramInteger1.intValue());
    }

    public ShopMoney findById(Long paramLong) {
        ShopMoney localShopMoney = (ShopMoney) get(paramLong);
        return localShopMoney;
    }

    public ShopMoney save(ShopMoney paramShopMoney) {
        getSession().save(paramShopMoney);
        return paramShopMoney;
    }

    public ShopMoney deleteById(Long paramLong) {
        ShopMoney localShopMoney = (ShopMoney) super.get(paramLong);
        if (localShopMoney != null)
            getSession().delete(localShopMoney);
        return localShopMoney;
    }

    protected Class<ShopMoney> getEntityClass() {
        return ShopMoney.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopMoneyDaoImpl
 * JD-Core Version:    0.6.2
 */