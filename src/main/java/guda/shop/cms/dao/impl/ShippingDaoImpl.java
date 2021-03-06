package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShippingDao;
import guda.shop.cms.entity.Shipping;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShippingDaoImpl extends HibernateBaseDao<Shipping, Long>
        implements ShippingDao {
    public List<Shipping> getList(Long paramLong, boolean paramBoolean1, boolean paramBoolean2) {
        Finder localFinder = Finder.create("from Shipping bean where bean.website.id=:webId");
        localFinder.setParam("webId", paramLong);
        if (!paramBoolean1)
            localFinder.append(" and bean.disabled=false");
        localFinder.append(" order by bean.priority");
        localFinder.setCacheable(paramBoolean2);
        return find(localFinder);
    }

    public Shipping findById(Long paramLong) {
        Shipping localShipping = (Shipping) get(paramLong);
        return localShipping;
    }

    public Shipping save(Shipping paramShipping) {
        getSession().save(paramShipping);
        return paramShipping;
    }

    public Shipping deleteById(Long paramLong) {
        Shipping localShipping = (Shipping) super.get(paramLong);
        if (localShipping != null)
            getSession().delete(localShipping);
        return localShipping;
    }

    protected Class<Shipping> getEntityClass() {
        return Shipping.class;
    }
}

