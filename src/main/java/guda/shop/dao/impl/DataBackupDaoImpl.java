package guda.shop.dao.impl;

import guda.shop.cms.dao.DataBackupDao;
iimport guda.shopcms.entity.DataBackup;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class DataBackupDaoImpl extends HibernateBaseDao<DataBackup, Integer>
  implements DataBackupDao
{
  protected Class<DataBackup> getEntityClass()
  {
    return DataBackup.class;
  }

  public DataBackup getDataBackup()
  {
    String str = " from DataBackup";
    List localList = getSession().createQuery(str).list();
    if (localList.size() == 0)
      return null;
    return (DataBackup)localList.get(0);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.DataBackupDaoImpl
 * JD-Core Version:    0.6.2
 */