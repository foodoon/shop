package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.GiftExchangeDao;
import guda.shop.cms.entity.GiftExchange;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GiftExchangeDaoImpl extends HibernateBaseDao<GiftExchange, Long>
        implements GiftExchangeDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public List<GiftExchange> getlist(Long paramLong) {
        Finder localFinder = Finder.create("from GiftExchange bean where bean.member.id=:memberId");
        localFinder.setParam("memberId", paramLong);
        return find(localFinder);
    }

    public GiftExchange findById(Long paramLong) {
        GiftExchange localGiftExchange = (GiftExchange) get(paramLong);
        return localGiftExchange;
    }

    public GiftExchange save(GiftExchange paramGiftExchange) {
        getSession().save(paramGiftExchange);
        return paramGiftExchange;
    }

    public GiftExchange deleteById(Long paramLong) {
        GiftExchange localGiftExchange = (GiftExchange) super.get(paramLong);
        if (localGiftExchange != null)
            getSession().delete(localGiftExchange);
        return localGiftExchange;
    }

    protected Class<GiftExchange> getEntityClass() {
        return GiftExchange.class;
    }
}

