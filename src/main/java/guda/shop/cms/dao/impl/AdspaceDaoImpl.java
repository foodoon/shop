package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.AdspaceDao;
import guda.shop.cms.entity.Adspace;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdspaceDaoImpl extends HibernateBaseDao<Adspace, Integer>
        implements AdspaceDao {
    public Adspace findById(Integer paramInteger) {
        Adspace localAdspace = (Adspace) get(paramInteger);
        return localAdspace;
    }

    public List<Adspace> getList() {
        return getSession().createQuery("from Adspace bean ").list();
    }

    public Adspace save(Adspace paramAdspace) {
        getSession().save(paramAdspace);
        return paramAdspace;
    }

    public Adspace deleteById(Integer paramInteger) {
        Adspace localAdspace = (Adspace) super.get(paramInteger);
        if (localAdspace != null)
            getSession().delete(localAdspace);
        return localAdspace;
    }

    protected Class<Adspace> getEntityClass() {
        return Adspace.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.AdspaceDaoImpl
 * JD-Core Version:    0.6.2
 */