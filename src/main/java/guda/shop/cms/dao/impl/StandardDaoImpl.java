package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.StandardDao;
iimport guda.shop.ms.entity.Standard;
imimport guda.shop.mmon.hibernate3.Finder;
impimport guda.shop.mon.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class StandardDaoImpl extends HibernateBaseDao<Standard, Long>
  implements StandardDao
{
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Standard bean where 1=1 ");
    localFinder.append(" order by bean.priority");
    return find(localFinder, paramInt1, paramInt2);
  }

  public Standard findById(Long paramLong)
  {
    Standard localStandard = (Standard)get(paramLong);
    return localStandard;
  }

  public List<Standard> findByTypeId(Long paramLong)
  {
    String str = "from Standard bean where 1=1";
    Finder localFinder = Finder.create(str);
    localFinder.append(" and bean.type.id=:typeId").setParam("typeId", paramLong);
    localFinder.append(" order by bean.priority");
    return find(localFinder);
  }

  public Standard save(Standard paramStandard)
  {
    getSession().save(paramStandard);
    return paramStandard;
  }

  public Standard deleteById(Long paramLong)
  {
    Standard localStandard = (Standard)super.get(paramLong);
    if (localStandard != null)
      getSession().delete(localStandard);
    return localStandard;
  }

  protected Class<Standard> getEntityClass()
  {
    return Standard.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.StandardDaoImpl
 * JD-Core Version:    0.6.2
 */