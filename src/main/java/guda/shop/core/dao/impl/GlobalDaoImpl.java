package guda.shop.core.dao.impl;

import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.core.dao.GlobalDao;
import guda.shop.core.entity.Global;
import org.springframework.stereotype.Repository;

@Repository
public class GlobalDaoImpl extends HibernateBaseDao<Global, Long>
        implements GlobalDao {
    public Global findById(Long paramLong) {
        Global localGlobal = (Global) get(paramLong);
        return localGlobal;
    }

    public Global update(Global paramGlobal) {
        getSession().update(paramGlobal);
        return paramGlobal;
    }

    protected Class<Global> getEntityClass() {
        return Global.class;
    }
}

