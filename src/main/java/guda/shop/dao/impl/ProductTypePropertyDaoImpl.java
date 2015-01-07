package guda.shop.dao.impl;

import guda.shop.cms.dao.ProductTypePropertyDao;
iimport guda.shopcms.entity.ProductTypeProperty;
imimport guda.shopommon.hibernate3.Finder;
impimport guda.shopmmon.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ProductTypePropertyDaoImpl extends HibernateBaseDao<ProductTypeProperty, Long>
  implements ProductTypePropertyDao
{
  public ProductTypeProperty deleteById(Long paramLong)
  {
    ProductTypeProperty localProductTypeProperty = (ProductTypeProperty)super.get(paramLong);
    if (localProductTypeProperty != null)
      getSession().delete(localProductTypeProperty);
    return localProductTypeProperty;
  }

  public ProductTypeProperty findById(Long paramLong)
  {
    ProductTypeProperty localProductTypeProperty = (ProductTypeProperty)get(paramLong);
    return localProductTypeProperty;
  }

  public Pagination getList(int paramInt1, int paramInt2, Long paramLong, Boolean paramBoolean, String paramString1, String paramString2)
  {
    Finder localFinder = Finder.create("from ProductTypeProperty bean where 1=1 ");
    if (paramLong != null)
      localFinder.append(" and bean.propertyType.id = :typeid").setParam("typeid", paramLong);
    if (paramString1 != null)
    {
      if ("1".equals(paramString1))
        localFinder.append(" and bean.propertyType.name like :searchContent ");
      else if ("2".equals(paramString1))
        localFinder.append(" and bean.propertyName like :searchContent ");
      localFinder.setParam("searchContent", "%" + paramString2 + "%");
    }
    localFinder.append(" and bean.category=:isCategory").setParam("isCategory", paramBoolean);
    localFinder.append(" order by bean.propertyType.id,bean.sort");
    return find(localFinder, paramInt1, paramInt2);
  }

  public List<ProductTypeProperty> getList(Long paramLong, Boolean paramBoolean)
  {
    Finder localFinder = Finder.create("from ProductTypeProperty bean where 1=1 ");
    if (paramLong != null)
      localFinder.append(" and bean.propertyType.id = :typeId").setParam("typeId", paramLong);
    localFinder.append(" and bean.category=:isCategory").setParam("isCategory", paramBoolean);
    localFinder.append(" order by bean.sort asc");
    return find(localFinder);
  }

  public ProductTypeProperty save(ProductTypeProperty paramProductTypeProperty)
  {
    getSession().save(paramProductTypeProperty);
    return paramProductTypeProperty;
  }

  protected Class<ProductTypeProperty> getEntityClass()
  {
    return ProductTypeProperty.class;
  }

  public List<ProductTypeProperty> findBySearch(String paramString1, String paramString2)
  {
    String str = "from ProductTypeProperty bean where 1 = 1 ";
    if ("1".equals(paramString1))
      str = str + " and bean.propertyType.name like :searchContent ";
    else if ("2".equals(paramString1))
      str = str + " and bean.propertyName like :searchContent ";
    paramString2 = "%" + paramString2 + "%";
    return getSession().createQuery(str).setString("searchContent", paramString2).setCacheable(false).list();
  }

  public List<ProductTypeProperty> findListByTypeId(Long paramLong)
  {
    String str = "from ProductTypeProperty bean where bean.propertyType.id = :typeId ";
    return getSession().createQuery(str).setLong("typeId", paramLong.longValue()).setCacheable(false).list();
  }

  public List<ProductTypeProperty> getList(Long paramLong, boolean paramBoolean)
  {
    Finder localFinder = Finder.create("from ProductTypeProperty bean where 1=1");
    localFinder.append(" and bean.propertyType.id=:typeId").setParam("typeId", paramLong);
    localFinder.append(" and bean.category=:isCategory").setParam("isCategory", Boolean.valueOf(paramBoolean));
    localFinder.append(" order by bean.sort asc");
    return find(localFinder);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ProductTypePropertyDaoImpl
 * JD-Core Version:    0.6.2
 */