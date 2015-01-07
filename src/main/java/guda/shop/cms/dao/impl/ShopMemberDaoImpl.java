package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopMemberDao;
import guda.shop.cms.entity.ShopMember;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ShopMemberDaoImpl extends HibernateBaseDao<ShopMember, Long>
  implements ShopMemberDao
{
  public Pagination getPage(Long paramLong, int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from ShopMember bean where bean.website.id=:webId order by bean.id desc");
    localFinder.setParam("webId", paramLong);
    return find(localFinder, paramInt1, paramInt2);
  }

  public ShopMember findById(Long paramLong)
  {
    ShopMember localShopMember = (ShopMember)get(paramLong);
    return localShopMember;
  }

  public ShopMember save(ShopMember paramShopMember)
  {
    getSession().save(paramShopMember);
    return paramShopMember;
  }

  public ShopMember deleteById(Long paramLong)
  {
    ShopMember localShopMember = (ShopMember)super.get(paramLong);
    if (localShopMember != null)
      getSession().delete(localShopMember);
    return localShopMember;
  }

  protected Class<ShopMember> getEntityClass()
  {
    return ShopMember.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopMemberDaoImpl
 * JD-Core Version:    0.6.2
 */