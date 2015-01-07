package guda.shop.dao.impl;

import guda.shop.cms.dao.ShopDictionaryDao;
iimport guda.shopcms.entity.ShopDictionary;
imimport guda.shopommon.hibernate3.Finder;
impimport guda.shopmmon.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDictionaryDaoImpl extends HibernateBaseDao<ShopDictionary, Long>
  implements ShopDictionaryDao
{
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
    return localPagination;
  }

  public Pagination getPage(String paramString, Long paramLong, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from ShopDictionary bean where 1=1");
    if (paramString != null)
    {
      localFinder.append(" and bean.name like :name");
      localFinder.setParam("name", "%" + paramString + "%");
    }
    if (paramLong != null)
    {
      localFinder.append(" and bean.shopDictionaryType.id=:typeId");
      localFinder.setParam("typeId", paramLong);
    }
    localFinder.append(" order by bean.priority asc, bean.id asc");
    localFinder.setCacheable(true);
    return find(localFinder, paramInt1, paramInt2);
  }

  public ShopDictionary findById(Long paramLong)
  {
    ShopDictionary localShopDictionary = (ShopDictionary)get(paramLong);
    return localShopDictionary;
  }

  public List<ShopDictionary> getListByType(Long paramLong)
  {
    Finder localFinder = Finder.create("from ShopDictionary bean where 1=1");
    if (paramLong != null)
    {
      localFinder.append(" and bean.shopDictionaryType.id=:typeId");
      localFinder.setParam("typeId", paramLong);
    }
    return find(localFinder);
  }

  public ShopDictionary save(ShopDictionary paramShopDictionary)
  {
    getSession().save(paramShopDictionary);
    return paramShopDictionary;
  }

  public ShopDictionary deleteById(Long paramLong)
  {
    ShopDictionary localShopDictionary = (ShopDictionary)super.get(paramLong);
    if (localShopDictionary != null)
      getSession().delete(localShopDictionary);
    return localShopDictionary;
  }

  protected Class<ShopDictionary> getEntityClass()
  {
    return ShopDictionary.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopDictionaryDaoImpl
 * JD-Core Version:    0.6.2
 */