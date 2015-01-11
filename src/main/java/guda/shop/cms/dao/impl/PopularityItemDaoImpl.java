package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.PopularityItemDao;
import guda.shop.cms.entity.PopularityItem;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PopularityItemDaoImpl extends HibernateBaseDao<PopularityItem, Long>
        implements PopularityItemDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public List<PopularityItem> getlist(Long paramLong1, Long paramLong2) {
        Finder localFinder = Finder.create("select bean from PopularityItem bean where 1=1 ");
        if (paramLong1 != null) {
            localFinder.append(" and bean.cart.id=:cartId");
            localFinder.setParam("cartId", paramLong1);
        }
        if (paramLong2 != null) {
            localFinder.append(" and bean.popularityGroup.id=:popularityGroupId");
            localFinder.setParam("popularityGroupId", paramLong2);
        }
        return find(localFinder);
    }

    public PopularityItem findById(Long paramLong1, Long paramLong2) {
        String str = "from PopularityItem bean where bean.cart.id=? and bean.popularityGroup.id=?";
        Query localQuery = getSession().createQuery(str);
        localQuery.setParameter(0, paramLong1).setParameter(1, paramLong2);
        localQuery.setMaxResults(1);
        return (PopularityItem) localQuery.setCacheable(true).uniqueResult();
    }

    public PopularityItem findById(Long paramLong) {
        PopularityItem localPopularityItem = (PopularityItem) get(paramLong);
        return localPopularityItem;
    }

    public PopularityItem save(PopularityItem paramPopularityItem) {
        getSession().save(paramPopularityItem);
        return paramPopularityItem;
    }

    public PopularityItem deleteById(Long paramLong) {
        PopularityItem localPopularityItem = (PopularityItem) super.get(paramLong);
        if (localPopularityItem != null)
            getSession().delete(localPopularityItem);
        return localPopularityItem;
    }

    protected Class<PopularityItem> getEntityClass() {
        return PopularityItem.class;
    }
}

