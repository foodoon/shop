package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopAdminDao;
iimport guda.shop.ms.entity.ShopAdmin;
imimport guda.shop.mmon.hibernate3.Finder;
impimport guda.shop.mon.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ShopAdminDaoImpl extends HibernateBaseDao<ShopAdmin, Long>
  implements ShopAdminDao
{
  public Pagination getPage(Long paramLong, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from ShopAdmin bean where bean.website.id=:webId order by bean.id desc");
    localFinder.setParam("webId", paramLong);
    return find(localFinder, paramInt1, paramInt2);
  }

  public ShopAdmin findById(Long paramLong)
  {
    ShopAdmin localShopAdmin = (ShopAdmin)get(paramLong);
    return localShopAdmin;
  }

  public ShopAdmin save(ShopAdmin paramShopAdmin)
  {
    getSession().save(paramShopAdmin);
    return paramShopAdmin;
  }

  public ShopAdmin deleteById(Long paramLong)
  {
    ShopAdmin localShopAdmin = (ShopAdmin)super.get(paramLong);
    if (localShopAdmin != null)
      getSession().delete(localShopAdmin);
    return localShopAdmin;
  }

  protected Class<ShopAdmin> getEntityClass()
  {
    return ShopAdmin.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopAdminDaoImpl
 * JD-Core Version:    0.6.2
 */