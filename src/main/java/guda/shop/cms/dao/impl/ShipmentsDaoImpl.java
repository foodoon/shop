package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShipmentsDao;
import guda.shop.cms.entity.Shipments;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipmentsDaoImpl extends HibernateBaseDao<Shipments, Long>
        implements ShipmentsDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public List<Shipments> getlist(Long paramLong) {
        Finder localFinder = Finder.create("from Shipments bean where bean.indent.id=:id");
        localFinder.setParam("id", paramLong);
        return find(localFinder);
    }

    public Shipments findById(Long paramLong) {
        Shipments localShipments = (Shipments) get(paramLong);
        return localShipments;
    }

    public Shipments save(Shipments paramShipments) {
        getSession().save(paramShipments);
        return paramShipments;
    }

    public Shipments deleteById(Long paramLong) {
        Shipments localShipments = (Shipments) super.get(paramLong);
        if (localShipments != null)
            getSession().delete(localShipments);
        return localShipments;
    }

    protected Class<Shipments> getEntityClass() {
        return Shipments.class;
    }
}

