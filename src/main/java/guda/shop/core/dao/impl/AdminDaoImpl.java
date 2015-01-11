package guda.shop.core.dao.impl;

import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.core.dao.AdminDao;
import guda.shop.core.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends HibernateBaseDao<Admin, Long>
        implements AdminDao {
    public Admin getByUserId(Long paramLong1, Long paramLong2) {
        String str = "from Admin bean where bean.user.id=:userId and bean.website.id=:webId";
        return (Admin) getSession().createQuery(str).setParameter("userId", paramLong1).setParameter("webId", paramLong2).uniqueResult();
    }

    public Admin findById(Long paramLong) {
        Admin localAdmin = (Admin) get(paramLong);
        return localAdmin;
    }

    public Admin save(Admin paramAdmin) {
        getSession().save(paramAdmin);
        return paramAdmin;
    }

    public Admin deleteById(Long paramLong) {
        Admin localAdmin = (Admin) super.get(paramLong);
        if (localAdmin != null)
            getSession().delete(localAdmin);
        return localAdmin;
    }

    protected Class<Admin> getEntityClass() {
        return Admin.class;
    }
}

