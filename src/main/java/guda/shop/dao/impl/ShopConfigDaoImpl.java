package guda.shop.dao.impl;

import guda.shop.cms.dao.ShopConfigDao;
iimport guda.shopcms.entity.ShopConfig;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ShopConfigDaoImpl extends HibernateBaseDao<ShopConfig, Long>
  implements ShopConfigDao
{
  public ShopConfig findById(Long paramLong)
  {
    ShopConfig localShopConfig = (ShopConfig)get(paramLong);
    return localShopConfig;
  }

  public ShopConfig save(ShopConfig paramShopConfig)
  {
    getSession().save(paramShopConfig);
    return paramShopConfig;
  }

  public ShopConfig deleteById(Long paramLong)
  {
    ShopConfig localShopConfig = (ShopConfig)super.get(paramLong);
    if (localShopConfig != null)
      getSession().delete(localShopConfig);
    return localShopConfig;
  }

  protected Class<ShopConfig> getEntityClass()
  {
    return ShopConfig.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopConfigDaoImpl
 * JD-Core Version:    0.6.2
 */