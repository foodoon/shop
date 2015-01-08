package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.GiftDao;
import guda.shop.cms.entity.Gift;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public class GiftDaoImpl extends HibernateBaseDao<Gift, Long>
        implements GiftDao {
    public Pagination getPageGift(int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from Gift bean");
        localFinder.append(" order by bean.id desc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public Gift findById(Long paramLong) {
        Gift localGift = (Gift) get(paramLong);
        return localGift;
    }

    public Gift save(Gift paramGift) {
        getSession().save(paramGift);
        return paramGift;
    }

    public Gift deleteById(Long paramLong) {
        Gift localGift = (Gift) super.get(paramLong);
        if (localGift != null)
            getSession().delete(localGift);
        return localGift;
    }

    protected Class<Gift> getEntityClass() {
        return Gift.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.GiftDaoImpl
 * JD-Core Version:    0.6.2
 */