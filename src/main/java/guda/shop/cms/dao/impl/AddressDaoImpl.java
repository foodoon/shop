package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.AddressDao;
import guda.shop.cms.entity.Address;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDaoImpl extends HibernateBaseDao<Address, Long>
        implements AddressDao {
    public List<Address> getListById(Long paramLong) {
        Finder localFinder = Finder.create("from Address bean where 1=1 ");
        if (paramLong == null) {
            localFinder.append(" and bean.parent.id is null");
        } else {
            localFinder.append(" and bean.parent.id=:parentId");
            localFinder.setParam("parentId", paramLong);
        }
        return find(localFinder);
    }

    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public Pagination getPageByParentId(Long paramLong, int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from Address bean where 1=1 ");
        if (paramLong != null) {
            localFinder.append(" and bean.parent.id=:id");
            localFinder.setParam("id", paramLong);
        } else {
            localFinder.append(" and bean.parent.id is null");
        }
        localFinder.append(" order by bean.priority");
        return find(localFinder, paramInt1, paramInt2);
    }

    public Address findById(Long paramLong) {
        Address localAddress = (Address) get(paramLong);
        return localAddress;
    }

    public Address save(Address paramAddress) {
        getSession().save(paramAddress);
        return paramAddress;
    }

    public Address deleteById(Long paramLong) {
        Address localAddress = (Address) super.get(paramLong);
        if (localAddress != null)
            getSession().delete(localAddress);
        return localAddress;
    }

    protected Class<Address> getEntityClass() {
        return Address.class;
    }
}

