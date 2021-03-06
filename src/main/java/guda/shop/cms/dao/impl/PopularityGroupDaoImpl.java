package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.PopularityGroupDao;
import guda.shop.cms.entity.PopularityGroup;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class PopularityGroupDaoImpl extends HibernateBaseDao<PopularityGroup, Long>
        implements PopularityGroupDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public PopularityGroup findById(Long paramLong) {
        PopularityGroup localPopularityGroup = (PopularityGroup) get(paramLong);
        return localPopularityGroup;
    }

    public PopularityGroup save(PopularityGroup paramPopularityGroup) {
        getSession().save(paramPopularityGroup);
        return paramPopularityGroup;
    }

    public PopularityGroup deleteById(Long paramLong) {
        PopularityGroup localPopularityGroup = (PopularityGroup) super.get(paramLong);
        if (localPopularityGroup != null)
            getSession().delete(localPopularityGroup);
        return localPopularityGroup;
    }

    protected Class<PopularityGroup> getEntityClass() {
        return PopularityGroup.class;
    }
}

