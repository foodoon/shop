package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopMemberGroupDao;
import guda.shop.cms.entity.ShopMemberGroup;
import guda.shop.common.hibernate3.HibernateBaseDao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ShopMemberGroupDaoImpl extends HibernateBaseDao<ShopMemberGroup, Long>
  implements ShopMemberGroupDao
{
  public List<ShopMemberGroup> getList(Long paramLong, boolean paramBoolean)
  {
    String str = "from ShopMemberGroup bean where bean.website.id=:webId order by bean.score";
    return getSession().createQuery(str).setParameter("webId", paramLong).setCacheable(paramBoolean).list();
  }

  public ShopMemberGroup findById(Long paramLong)
  {
    ShopMemberGroup localShopMemberGroup = (ShopMemberGroup)get(paramLong);
    return localShopMemberGroup;
  }

  public ShopMemberGroup save(ShopMemberGroup paramShopMemberGroup)
  {
    getSession().save(paramShopMemberGroup);
    return paramShopMemberGroup;
  }

  public ShopMemberGroup deleteById(Long paramLong)
  {
    ShopMemberGroup localShopMemberGroup = (ShopMemberGroup)super.get(paramLong);
    if (localShopMemberGroup != null)
      getSession().delete(localShopMemberGroup);
    return localShopMemberGroup;
  }

  protected Class<ShopMemberGroup> getEntityClass()
  {
    return ShopMemberGroup.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopMemberGroupDaoImpl
 * JD-Core Version:    0.6.2
 */