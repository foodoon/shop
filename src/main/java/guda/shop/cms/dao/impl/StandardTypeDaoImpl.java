package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.StandardTypeDao;
import guda.shop.cms.entity.StandardType;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StandardTypeDaoImpl extends HibernateBaseDao<StandardType, Long>
        implements StandardTypeDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from StandardType bean where 1=1");
        localFinder.append(" order by bean.priority");
        return find(localFinder, paramInt1, paramInt2);
    }

    public StandardType getByField(String paramString) {
        return (StandardType) findUniqueByProperty("field", paramString);
    }

    public StandardType findById(Long paramLong) {
        StandardType localStandardType = (StandardType) get(paramLong);
        return localStandardType;
    }

    public List<StandardType> getList() {
        Finder localFinder = Finder.create("from StandardType bean where 1=1");
        localFinder.append(" order by bean.priority");
        return find(localFinder);
    }

    public List<StandardType> getList(Long paramLong) {
        Finder localFinder = Finder.create("select bean from StandardType bean ");
        localFinder.append(" inner join bean.categorys category");
        localFinder.append(" where category.id=:categoryId").setParam("categoryId", paramLong);
        localFinder.append(" order by bean.priority");
        return find(localFinder);
    }

    public StandardType save(StandardType paramStandardType) {
        getSession().save(paramStandardType);
        return paramStandardType;
    }

    public StandardType deleteById(Long paramLong) {
        StandardType localStandardType = (StandardType) super.get(paramLong);
        if (localStandardType != null)
            getSession().delete(localStandardType);
        return localStandardType;
    }

    protected Class<StandardType> getEntityClass() {
        return StandardType.class;
    }
}

