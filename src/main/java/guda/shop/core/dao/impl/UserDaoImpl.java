package guda.shop.core.dao.impl;

import guda.shop.common.hibernate3.HibernateBaseDao;
iimport guda.shop.ommon.page.Pagination;
imimport guda.shop.re.dao.UserDao;
import guda.shop.core.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends HibernateBaseDao<User, Long>
  implements UserDao
{
  public User getByUsername(String paramString)
  {
    return (User)findUniqueByProperty("username", paramString);
  }

  public User getByEmail(String paramString)
  {
    return (User)findUniqueByProperty("email", paramString);
  }

  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
    return localPagination;
  }

  public User findById(Long paramLong)
  {
    User localUser = (User)get(paramLong);
    return localUser;
  }

  public User save(User paramUser)
  {
    getSession().save(paramUser);
    return paramUser;
  }

  public User deleteById(Long paramLong)
  {
    User localUser = (User)super.get(paramLong);
    if (localUser != null)
      getSession().delete(localUser);
    return localUser;
  }

  protected Class<User> getEntityClass()
  {
    return User.class;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.impl.UserDaoImpl
 * JD-Core Version:    0.6.2
 */