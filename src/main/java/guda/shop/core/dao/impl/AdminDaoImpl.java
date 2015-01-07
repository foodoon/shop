package guda.shop.core.dao.impl;

import guda.shop.common.hibernate3.HibernateBaseDao;
iimport guda.shop.ore.dao.AdminDao;
import com.jspgou.core.entity.Admin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends HibernateBaseDao<Admin, Long>
  implements AdminDao
{
  public Admin getByUserId(Long paramLong1, Long paramLong2)
  {
    String str = "from Admin bean where bean.user.id=:userId and bean.website.id=:webId";
    return (Admin)getSession().createQuery(str).setParameter("userId", paramLong1).setParameter("webId", paramLong2).uniqueResult();
  }

  public Admin findById(Long paramLong)
  {
    Admin localAdmin = (Admin)get(paramLong);
    return localAdmin;
  }

  public Admin save(Admin paramAdmin)
  {
    getSession().save(paramAdmin);
    return paramAdmin;
  }

  public Admin deleteById(Long paramLong)
  {
    Admin localAdmin = (Admin)super.get(paramLong);
    if (localAdmin != null)
      getSession().delete(localAdmin);
    return localAdmin;
  }

  protected Class<Admin> getEntityClass()
  {
    return Admin.class;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.impl.AdminDaoImpl
 * JD-Core Version:    0.6.2
 */