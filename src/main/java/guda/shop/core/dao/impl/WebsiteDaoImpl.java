package guda.shop.core.dao.impl;

import guda.shop.common.hibernate3.HibernateBaseDao;
iimport guda.shop.ommon.page.Pagination;
imimport guda.shop.re.dao.WebsiteDao;
import com.jspgou.core.entity.Website;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class WebsiteDaoImpl extends HibernateBaseDao<Website, Long>
  implements WebsiteDao
{
  public Website getUniqueWebsite()
  {
    String str = "select bean from Website bean";
    return (Website)getSession().createQuery(str).uniqueResult();
  }

  public Website findByDomain(String paramString)
  {
    return (Website)findUniqueByProperty(Website.PROP_DOMAIN, paramString);
  }

  public int countWebsite()
  {
    String str = "select count(*) from Website";
    return ((Number)getSession().createQuery(str).iterate().next()).intValue();
  }

  public Pagination getAllPage(int paramInt1, int paramInt2)
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
    return localPagination;
  }

  public List<Website> getAllList()
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    List localList = localCriteria.list();
    return localList;
  }

  public Website findById(Long paramLong)
  {
    Website localWebsite = (Website)get(paramLong);
    Hibernate.initialize(localWebsite);
    return localWebsite;
  }

  public Website save(Website paramWebsite)
  {
    getSession().save(paramWebsite);
    return paramWebsite;
  }

  public Website deleteById(Long paramLong)
  {
    Website localWebsite = (Website)super.get(paramLong);
    if (localWebsite != null)
      getSession().delete(localWebsite);
    return localWebsite;
  }

  protected Class<Website> getEntityClass()
  {
    return Website.class;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.impl.WebsiteDaoImpl
 * JD-Core Version:    0.6.2
 */