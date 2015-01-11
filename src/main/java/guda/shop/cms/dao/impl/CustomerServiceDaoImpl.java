package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.CustomerServiceDao;
import guda.shop.cms.entity.CustomerService;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerServiceDaoImpl extends HibernateBaseDao<CustomerService, Long>
        implements CustomerServiceDao {
    public CustomerService findById(Long paramLong) {
        CustomerService localCustomerService = (CustomerService) get(paramLong);
        return localCustomerService;
    }

    public Pagination getPagination(Boolean paramBoolean, int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from CustomerService bean where 1=1");
        if (paramBoolean != null)
            localFinder.append(" and bean.disable=:disable").setParam("disable", paramBoolean);
        localFinder.append(" order by bean.priority");
        return find(localFinder, paramInt1, paramInt2);
    }

    public List<CustomerService> getList(Boolean paramBoolean) {
        Finder localFinder = Finder.create("from CustomerService bean where 1=1");
        if (paramBoolean != null)
            localFinder.append(" and bean.disable=:disable").setParam("disable", paramBoolean);
        localFinder.append(" order by bean.priority");
        return find(localFinder);
    }

    public CustomerService save(CustomerService paramCustomerService) {
        getSession().save(paramCustomerService);
        return paramCustomerService;
    }

    public CustomerService deleteById(Long paramLong) {
        CustomerService localCustomerService = (CustomerService) super.get(paramLong);
        if (localCustomerService != null)
            getSession().delete(localCustomerService);
        return localCustomerService;
    }

    protected Class<CustomerService> getEntityClass() {
        return CustomerService.class;
    }
}

