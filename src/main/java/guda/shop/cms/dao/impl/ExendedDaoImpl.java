package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ExendedDao;
import guda.shop.cms.entity.Exended;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExendedDaoImpl extends HibernateBaseDao<Exended, Long>
        implements ExendedDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from Exended bean where 1=1");
        localFinder.append(" order by bean.priority");
        return find(localFinder, paramInt1, paramInt2);
    }

    public Exended findById(Long paramLong) {
        Exended localExended = (Exended) get(paramLong);
        return localExended;
    }

    public List<Exended> getList() {
        Finder localFinder = Finder.create("from Exended bean where 1=1");
        localFinder.append(" order by bean.priority");
        return find(localFinder);
    }

    public List<Exended> getList(Long paramLong) {
        Finder localFinder = Finder.create("select bean from Exended bean ");
        localFinder.append(" inner join bean.productTypes ptype ");
        localFinder.append(" where ptype.id=:typeId").setParam("typeId", paramLong);
        localFinder.append(" order by bean.priority");
        return find(localFinder);
    }

    public Exended save(Exended paramExended) {
        getSession().save(paramExended);
        return paramExended;
    }

    public Exended deleteById(Long paramLong) {
        Exended localExended = (Exended) super.get(paramLong);
        if (localExended != null)
            getSession().delete(localExended);
        return localExended;
    }

    protected Class<Exended> getEntityClass() {
        return Exended.class;
    }
}

