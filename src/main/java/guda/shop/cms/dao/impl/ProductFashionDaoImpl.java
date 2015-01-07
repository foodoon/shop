package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ProductFashionDao;
import guda.shop.cms.entity.ProductFashion;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ProductFashionDaoImpl extends HibernateBaseDao<ProductFashion, Long>
  implements ProductFashionDao
{
  protected Class<ProductFashion> getEntityClass()
  {
    return ProductFashion.class;
  }

  public ProductFashion deleteById(Long paramLong)
  {
    ProductFashion localProductFashion = (ProductFashion)super.get(paramLong);
    if (localProductFashion != null)
      getSession().delete(localProductFashion);
    return localProductFashion;
  }

  public ProductFashion findById(Long paramLong)
  {
    ProductFashion localProductFashion = (ProductFashion)get(paramLong);
    return localProductFashion;
  }

  public List<ProductFashion> findByProductId(Long paramLong)
  {
    Finder localFinder = Finder.create(" from ProductFashion bean where bean.productId.id=:productId ");
    return find(localFinder);
  }

  public Pagination getPage(Long paramLong, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create(" from ProductFashion bean where bean.productId.id=:productId ");
    localFinder.setParam("productId", paramLong);
    localFinder.setCacheable(true);
    return find(localFinder, paramInt1, paramInt2);
  }

  public ProductFashion save(ProductFashion paramProductFashion)
  {
    getSession().save(paramProductFashion);
    return paramProductFashion;
  }

  public Pagination productLack(Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from ProductFashion bean where bean.lackRemind=:status ");
    localFinder.setParam("status", paramInteger1);
    if (paramInteger2 == null)
      paramInteger2 = Integer.valueOf(5);
    localFinder.append(" and bean.stockCount < :count").setParam("count", paramInteger2);
    return find(localFinder, paramInt1, paramInt2);
  }

  public Integer productLackCount(Integer paramInteger1, Integer paramInteger2)
  {
    String str = " select count(bean) from ProductFashion bean where bean.lackRemind=:status ";
    if (paramInteger2 == null)
      paramInteger2 = Integer.valueOf(5);
    str = str + " and bean.stockCount < :count";
    Iterator localIterator = getSession().createQuery(str).setInteger("count", paramInteger2.intValue()).setInteger("status", paramInteger1.intValue()).iterate();
    Integer localInteger = Integer.valueOf(0);
    if (localIterator.hasNext())
      localInteger = Integer.valueOf(Integer.parseInt(localIterator.next() + ""));
    return localInteger;
  }

  public Pagination getSaleTopPage(int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create(" from ProductFashion bean order by bean.saleCount desc");
    return find(localFinder, paramInt1, paramInt2);
  }

  public ProductFashion getPfashion(Long paramLong1, Long paramLong2)
  {
    return (ProductFashion)getSession().createQuery("from ProductFashion bean where bean.productId.id=:pid and bean.id=:fid").setParameter("pid", paramLong1).setParameter("fid", paramLong2).uniqueResult();
  }

  public Boolean getOneFashion(Long paramLong)
  {
    Finder localFinder = Finder.create("from ProductFashion bean where bean.productId.id=:id").setParam("id", paramLong);
    List localList = find(localFinder);
    if (localList.size() <= 1)
      return Boolean.valueOf(false);
    return Boolean.valueOf(true);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ProductFashionDaoImpl
 * JD-Core Version:    0.6.2
 */