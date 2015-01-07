package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.CollectDao;
import guda.shop.cms.entity.Collect;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class CollectDaoImpl extends HibernateBaseDao<Collect, Integer>
  implements CollectDao
{
  public Pagination getList(Integer paramInteger1, Integer paramInteger2, Long paramLong)
  {
    String str = "from Collect bean where 1=1 and bean.member.id=:id";
    Finder localFinder = Finder.create(str).setParam("id", paramLong);
    return find(localFinder, paramInteger2.intValue(), paramInteger1.intValue());
  }

  public Collect findById(Integer paramInteger)
  {
    Collect localCollect = (Collect)get(paramInteger);
    return localCollect;
  }

  public List<Collect> findByProductId(Long paramLong)
  {
    Finder localFinder = Finder.create("from Collect bean where bean.product.id=:id").setParam("id", paramLong);
    return find(localFinder);
  }

  public Collect findByProductFashionId(Long paramLong)
  {
    Iterator localIterator = getSession().createQuery("from Collect bean where bean.fashion.id=:id").setParameter("id", paramLong).iterate();
    if (localIterator.hasNext())
      return (Collect)localIterator.next();
    return null;
  }

  public Collect save(Collect paramCollect)
  {
    getSession().save(paramCollect);
    return paramCollect;
  }

  public Collect deleteById(Integer paramInteger)
  {
    Collect localCollect = (Collect)super.get(paramInteger);
    if (localCollect != null)
      getSession().delete(localCollect);
    return localCollect;
  }

  protected Class<Collect> getEntityClass()
  {
    return Collect.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.CollectDaoImpl
 * JD-Core Version:    0.6.2
 */