package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.AdvertiseDao;
import guda.shop.cms.entity.Advertise;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertiseDaoImpl extends HibernateBaseDao<Advertise, Integer>
  implements AdvertiseDao
{
  public Pagination getPage(Integer paramInteger, Boolean paramBoolean, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Advertise bean where 1=1");
    if (paramInteger != null)
    {
      localFinder.append(" and bean.adspace.id=:adspaceId");
      localFinder.setParam("adspaceId", paramInteger);
    }
    if (paramBoolean != null)
    {
      localFinder.append(" and bean.enabled=:enabled");
      localFinder.setParam("enabled", paramBoolean);
    }
    localFinder.append(" order by bean.id desc");
    return find(localFinder, paramInt1, paramInt2);
  }

  public List<Advertise> getList(Integer paramInteger, Boolean paramBoolean)
  {
    Finder localFinder = Finder.create("from Advertise bean where 1=1");
    if (paramInteger != null)
    {
      localFinder.append(" and bean.adspace.id=:adspaceId");
      localFinder.setParam("adspaceId", paramInteger);
    }
    if (paramBoolean != null)
    {
      localFinder.append(" and bean.enabled=:enabled");
      localFinder.setParam("enabled", paramBoolean);
    }
    return find(localFinder);
  }

  public Advertise findById(Integer paramInteger)
  {
    Advertise localAdvertise = (Advertise)get(paramInteger);
    return localAdvertise;
  }

  public Advertise save(Advertise paramAdvertise)
  {
    getSession().save(paramAdvertise);
    return paramAdvertise;
  }

  public Advertise deleteById(Integer paramInteger)
  {
    Advertise localAdvertise = (Advertise)super.get(paramInteger);
    if (localAdvertise != null)
      getSession().delete(localAdvertise);
    return localAdvertise;
  }

  protected Class<Advertise> getEntityClass()
  {
    return Advertise.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.AdvertiseDaoImpl
 * JD-Core Version:    0.6.2
 */