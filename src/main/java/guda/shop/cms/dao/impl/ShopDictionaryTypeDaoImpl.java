package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopDictionaryTypeDao;
iimport guda.shop.ms.entity.ShopDictionaryType;
imimport guda.shop.mmon.hibernate3.Finder;
impimport guda.shop.mon.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDictionaryTypeDaoImpl extends HibernateBaseDao<ShopDictionaryType, Long>
  implements ShopDictionaryTypeDao
{
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
    return localPagination;
  }

  public ShopDictionaryType findById(Long paramLong)
  {
    ShopDictionaryType localShopDictionaryType = (ShopDictionaryType)get(paramLong);
    return localShopDictionaryType;
  }

  public List<ShopDictionaryType> findAll()
  {
    Finder localFinder = Finder.create("from ShopDictionaryType bean ");
    return find(localFinder);
  }

  public ShopDictionaryType save(ShopDictionaryType paramShopDictionaryType)
  {
    getSession().save(paramShopDictionaryType);
    return paramShopDictionaryType;
  }

  public ShopDictionaryType deleteById(Long paramLong)
  {
    ShopDictionaryType localShopDictionaryType = (ShopDictionaryType)super.get(paramLong);
    if (localShopDictionaryType != null)
      getSession().delete(localShopDictionaryType);
    return localShopDictionaryType;
  }

  protected Class<ShopDictionaryType> getEntityClass()
  {
    return ShopDictionaryType.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopDictionaryTypeDaoImpl
 * JD-Core Version:    0.6.2
 */