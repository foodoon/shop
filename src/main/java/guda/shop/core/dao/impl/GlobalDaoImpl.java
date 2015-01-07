package guda.shop.core.dao.impl;

import guda.shop.common.hibernate3.HibernateBaseDao;
iimport guda.shop.ore.dao.GlobalDao;
import com.jspgou.core.entity.Global;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class GlobalDaoImpl extends HibernateBaseDao<Global, Long>
  implements GlobalDao
{
  public Global findById(Long paramLong)
  {
    Global localGlobal = (Global)get(paramLong);
    return localGlobal;
  }

  public Global update(Global paramGlobal)
  {
    getSession().update(paramGlobal);
    return paramGlobal;
  }

  protected Class<Global> getEntityClass()
  {
    return Global.class;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.impl.GlobalDaoImpl
 * JD-Core Version:    0.6.2
 */