package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.GatheringDao;
import guda.shop.cms.entity.Gathering;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GatheringDaoImpl extends HibernateBaseDao<Gathering, Long>
        implements GatheringDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public List<Gathering> getlist(Long paramLong) {
        Finder localFinder = Finder.create("from Gathering bean where bean.indent.id=:id");
        localFinder.setParam("id", paramLong);
        return find(localFinder);
    }

    public Gathering findById(Long paramLong) {
        Gathering localGathering = (Gathering) get(paramLong);
        return localGathering;
    }

    public Gathering save(Gathering paramGathering) {
        getSession().save(paramGathering);
        return paramGathering;
    }

    public Gathering deleteById(Long paramLong) {
        Gathering localGathering = (Gathering) super.get(paramLong);
        if (localGathering != null)
            getSession().delete(localGathering);
        return localGathering;
    }

    protected Class<Gathering> getEntityClass() {
        return Gathering.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.GatheringDaoImpl
 * JD-Core Version:    0.6.2
 */