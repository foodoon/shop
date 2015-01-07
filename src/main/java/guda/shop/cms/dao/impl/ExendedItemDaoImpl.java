package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ExendedItemDao;
import guda.shop.cms.entity.ExendedItem;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class ExendedItemDaoImpl extends HibernateBaseDao<ExendedItem, Long>
  implements ExendedItemDao
{
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
    return localPagination;
  }

  public ExendedItem findById(Long paramLong)
  {
    ExendedItem localExendedItem = (ExendedItem)get(paramLong);
    return localExendedItem;
  }

  public ExendedItem save(ExendedItem paramExendedItem)
  {
    getSession().save(paramExendedItem);
    return paramExendedItem;
  }

  public ExendedItem deleteById(Long paramLong)
  {
    ExendedItem localExendedItem = (ExendedItem)super.get(paramLong);
    if (localExendedItem != null)
      getSession().delete(localExendedItem);
    return localExendedItem;
  }

  protected Class<ExendedItem> getEntityClass()
  {
    return ExendedItem.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ExendedItemDaoImpl
 * JD-Core Version:    0.6.2
 */