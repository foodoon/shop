package guda.shop.dao.impl;

import guda.shop.cms.dao.ProductExtDao;
iimport guda.shopcms.entity.ProductExt;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ProductExtDaoImpl extends HibernateBaseDao<ProductExt, Long>
  implements ProductExtDao
{
  public ProductExt findById(Long paramLong)
  {
    ProductExt localProductExt = (ProductExt)get(paramLong);
    return localProductExt;
  }

  public ProductExt save(ProductExt paramProductExt)
  {
    getSession().save(paramProductExt);
    return paramProductExt;
  }

  protected Class<ProductExt> getEntityClass()
  {
    return ProductExt.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ProductExtDaoImpl
 * JD-Core Version:    0.6.2
 */