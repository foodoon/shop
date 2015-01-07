package guda.shop.dao.impl;

import guda.shop.cms.dao.PaymentDao;
iimport guda.shopcms.entity.Payment;
imimport guda.shopommon.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDaoImpl extends HibernateBaseDao<Payment, Long>
  implements PaymentDao
{
  public List<Payment> getListForCart(Long paramLong, boolean paramBoolean)
  {
    String str = "from Payment bean where bean.website.id=:webId and bean.disabled=false order by bean.priority";
    return getSession().createQuery(str).setParameter("webId", paramLong).setCacheable(paramBoolean).list();
  }

  public List<Payment> getList(Long paramLong, boolean paramBoolean)
  {
    Finder localFinder = Finder.create("from Payment bean where bean.website.id=:webId");
    localFinder.setParam("webId", paramLong);
    if (!paramBoolean)
      localFinder.append(" bean.disabled=false order by bean.priority");
    else
      localFinder.append(" order by bean.disabled,bean.priority");
    return find(localFinder);
  }

  public List<Payment> getByCode(String paramString, Long paramLong)
  {
    String str = "from Payment bean where bean.website.id=? and bean.code=?";
    return find(str, new Object[] { paramLong, paramString });
  }

  public Payment findById(Long paramLong)
  {
    Payment localPayment = (Payment)get(paramLong);
    return localPayment;
  }

  public Payment save(Payment paramPayment)
  {
    getSession().save(paramPayment);
    return paramPayment;
  }

  public Payment deleteById(Long paramLong)
  {
    Payment localPayment = (Payment)super.get(paramLong);
    if (localPayment != null)
      getSession().delete(localPayment);
    return localPayment;
  }

  protected Class<Payment> getEntityClass()
  {
    return Payment.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.PaymentDaoImpl
 * JD-Core Version:    0.6.2
 */