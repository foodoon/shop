package guda.shop.dao.impl;

import guda.shop.cms.dao.ProductTagDao;
iimport guda.shopcms.entity.ProductTag;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ProductTagDaoImpl extends HibernateBaseDao<ProductTag, Long>
  implements ProductTagDao
{
  public List<ProductTag> getList(Long paramLong)
  {
    String str = "from ProductTag bean where bean.website.id=?";
    return find(str, new Object[] { paramLong });
  }

  public ProductTag findById(Long paramLong)
  {
    ProductTag localProductTag = (ProductTag)get(paramLong);
    return localProductTag;
  }

  public ProductTag save(ProductTag paramProductTag)
  {
    getSession().save(paramProductTag);
    return paramProductTag;
  }

  public ProductTag deleteById(Long paramLong)
  {
    ProductTag localProductTag = (ProductTag)super.get(paramLong);
    if (localProductTag != null)
      getSession().delete(localProductTag);
    return localProductTag;
  }

  protected Class<ProductTag> getEntityClass()
  {
    return ProductTag.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ProductTagDaoImpl
 * JD-Core Version:    0.6.2
 */