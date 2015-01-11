package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.LogisticsDao;
import guda.shop.cms.entity.Logistics;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogisticsDaoImpl extends HibernateBaseDao<Logistics, Long>
        implements LogisticsDao {
    public List<Logistics> getAllList() {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        localCriteria.addOrder(Order.asc(Logistics.PROP_PRIORITY));
        List localList = localCriteria.list();
        return localList;
    }

    public Logistics findById(Long paramLong) {
        Logistics localLogistics = (Logistics) get(paramLong);
        return localLogistics;
    }

    public Logistics save(Logistics paramLogistics) {
        getSession().save(paramLogistics);
        return paramLogistics;
    }

    public Logistics deleteById(Long paramLong) {
        Logistics localLogistics = (Logistics) super.get(paramLong);
        if (localLogistics != null)
            getSession().delete(localLogistics);
        return localLogistics;
    }

    protected Class<Logistics> getEntityClass() {
        return Logistics.class;
    }
}

