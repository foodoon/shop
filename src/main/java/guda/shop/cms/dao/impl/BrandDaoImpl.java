package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.BrandDao;
import guda.shop.cms.entity.Brand;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository
public class BrandDaoImpl extends HibernateBaseDao<Brand, Long>
  implements BrandDao
{
  public List<Brand> getAllList()
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    localCriteria.addOrder(Order.asc(Brand.PROP_PRIORITY));
    List localList = localCriteria.list();
    return localList;
  }

  public List<Brand> getList(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Finder localFinder = Finder.create("select bean from Brand bean where bean.website.id=:webId order by bean.priority");
    localFinder.setParam("webId", paramLong);
    localFinder.setFirstResult(paramInt1);
    localFinder.setMaxResults(paramInt2);
    localFinder.setCacheable(paramBoolean);
    return find(localFinder);
  }

  public List<Brand> getList()
  {
    Finder localFinder = Finder.create("from Brand bean where bean.disabled=false");
    localFinder.append(" order by bean.priority");
    return find(localFinder);
  }

  public List<Brand> getListByCate(Long paramLong)
  {
    return getSession().createQuery("select bean from Brand bean join bean.categorys cate where cate.id=:id").setParameter("id", paramLong).list();
  }

  public Brand getsiftBrand()
  {
    return (Brand)getSession().createQuery("from Brand bean where bean.sift=true order by bean.id desc").setMaxResults(1).uniqueResult();
  }

  public Brand findById(Long paramLong)
  {
    Brand localBrand = (Brand)get(paramLong);
    return localBrand;
  }

  public Brand save(Brand paramBrand)
  {
    getSession().save(paramBrand);
    return paramBrand;
  }

  public Brand deleteById(Long paramLong)
  {
    Brand localBrand = (Brand)super.get(paramLong);
    if (localBrand != null)
      getSession().delete(localBrand);
    return localBrand;
  }

  public int countByBrandName(String paramString)
  {
    String str = "select count(*) from Brand bean where bean.name=:brandName";
    Query localQuery = getSession().createQuery(str);
    localQuery.setParameter("brandName", paramString);
    return ((Number)localQuery.iterate().next()).intValue();
  }

  protected Class<Brand> getEntityClass()
  {
    return Brand.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.BrandDaoImpl
 * JD-Core Version:    0.6.2
 */