package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShippingDao;
iimport guda.shop.ms.entity.Shipping;
imimport guda.shop.mmon.hibernate3.Finder;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ShippingDaoImpl extends HibernateBaseDao<Shipping, Long>
  implements ShippingDao
{
  public List<Shipping> getList(Long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    Finder localFinder = Finder.create("from Shipping bean where bean.website.id=:webId");
    localFinder.setParam("webId", paramLong);
    if (!paramBoolean1)
      localFinder.append(" and bean.disabled=false");
    localFinder.append(" order by bean.priority");
    localFinder.setCacheable(paramBoolean2);
    return find(localFinder);
  }

  public Shipping findById(Long paramLong)
  {
    Shipping localShipping = (Shipping)get(paramLong);
    return localShipping;
  }

  public Shipping save(Shipping paramShipping)
  {
    getSession().save(paramShipping);
    return paramShipping;
  }

  public Shipping deleteById(Long paramLong)
  {
    Shipping localShipping = (Shipping)super.get(paramLong);
    if (localShipping != null)
      getSession().delete(localShipping);
    return localShipping;
  }

  protected Class<Shipping> getEntityClass()
  {
    return Shipping.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShippingDaoImpl
 * JD-Core Version:    0.6.2
 */