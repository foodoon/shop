package guda.shop.dao.impl;

import guda.shop.cms.dao.CustomerServiceDao;
iimport guda.shopcms.entity.CustomerService;
imimport guda.shopommon.hibernate3.Finder;
impimport guda.shopmmon.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerServiceDaoImpl extends HibernateBaseDao<CustomerService, Long>
  implements CustomerServiceDao
{
  public CustomerService findById(Long paramLong)
  {
    CustomerService localCustomerService = (CustomerService)get(paramLong);
    return localCustomerService;
  }

  public Pagination getPagination(Boolean paramBoolean, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from CustomerService bean where 1=1");
    if (paramBoolean != null)
      localFinder.append(" and bean.disable=:disable").setParam("disable", paramBoolean);
    localFinder.append(" order by bean.priority");
    return find(localFinder, paramInt1, paramInt2);
  }

  public List<CustomerService> getList(Boolean paramBoolean)
  {
    Finder localFinder = Finder.create("from CustomerService bean where 1=1");
    if (paramBoolean != null)
      localFinder.append(" and bean.disable=:disable").setParam("disable", paramBoolean);
    localFinder.append(" order by bean.priority");
    return find(localFinder);
  }

  public CustomerService save(CustomerService paramCustomerService)
  {
    getSession().save(paramCustomerService);
    return paramCustomerService;
  }

  public CustomerService deleteById(Long paramLong)
  {
    CustomerService localCustomerService = (CustomerService)super.get(paramLong);
    if (localCustomerService != null)
      getSession().delete(localCustomerService);
    return localCustomerService;
  }

  protected Class<CustomerService> getEntityClass()
  {
    return CustomerService.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.CustomerServiceDaoImpl
 * JD-Core Version:    0.6.2
 */