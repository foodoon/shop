package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.DataBackupDao;
import guda.shop.cms.entity.DataBackup;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataBackupDaoImpl extends HibernateBaseDao<DataBackup, Integer>
        implements DataBackupDao {
    protected Class<DataBackup> getEntityClass() {
        return DataBackup.class;
    }

    public DataBackup getDataBackup() {
        String str = " from DataBackup";
        List localList = getSession().createQuery(str).list();
        if (localList.size() == 0)
            return null;
        return (DataBackup) localList.get(0);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.DataBackupDaoImpl
 * JD-Core Version:    0.6.2
 */