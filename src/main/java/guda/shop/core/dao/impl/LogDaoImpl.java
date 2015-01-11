package guda.shop.core.dao.impl;

import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import guda.shop.core.dao.LogDao;
import guda.shop.core.entity.Log;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoImpl extends HibernateBaseDao<Log, Long>
        implements LogDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public Log findById(Long paramLong) {
        Log localLog = (Log) get(paramLong);
        return localLog;
    }

    public Log save(Log paramLog) {
        getSession().save(paramLog);
        return paramLog;
    }

    public Log deleteById(Long paramLong) {
        Log localLog = (Log) super.get(paramLong);
        if (localLog != null)
            getSession().delete(localLog);
        return localLog;
    }

    protected Class<Log> getEntityClass() {
        return Log.class;
    }
}

