package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.LogisticsTextDao;
import guda.shop.cms.entity.LogisticsText;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class LogisticsTextDaoImpl extends HibernateBaseDao<LogisticsText, Long>
        implements LogisticsTextDao {
    public LogisticsText save(LogisticsText paramLogisticsText) {
        getSession().save(paramLogisticsText);
        return paramLogisticsText;
    }

    protected Class<LogisticsText> getEntityClass() {
        return LogisticsText.class;
    }
}

