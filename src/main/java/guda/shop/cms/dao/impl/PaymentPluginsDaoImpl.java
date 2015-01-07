package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.PaymentPluginsDao;
import guda.shop.cms.entity.PaymentPlugins;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentPluginsDaoImpl extends HibernateBaseDao<PaymentPlugins, Long>
  implements PaymentPluginsDao
{
  public List<PaymentPlugins> getList()
  {
    Finder localFinder = Finder.create("from PaymentPlugins bean where 1=1");
    localFinder.append(" order by bean.priority");
    return find(localFinder);
  }

  public PaymentPlugins findById(Long paramLong)
  {
    PaymentPlugins localPaymentPlugins = (PaymentPlugins)get(paramLong);
    return localPaymentPlugins;
  }

  public PaymentPlugins findByCode(String paramString)
  {
    return (PaymentPlugins)findUniqueByProperty("code", paramString);
  }

  public PaymentPlugins save(PaymentPlugins paramPaymentPlugins)
  {
    getSession().save(paramPaymentPlugins);
    return paramPaymentPlugins;
  }

  public PaymentPlugins deleteById(Long paramLong)
  {
    PaymentPlugins localPaymentPlugins = (PaymentPlugins)super.get(paramLong);
    if (localPaymentPlugins != null)
      getSession().delete(localPaymentPlugins);
    return localPaymentPlugins;
  }

  protected Class<PaymentPlugins> getEntityClass()
  {
    return PaymentPlugins.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.PaymentPluginsDaoImpl
 * JD-Core Version:    0.6.2
 */