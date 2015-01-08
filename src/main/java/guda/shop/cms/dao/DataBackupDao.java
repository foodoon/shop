package guda.shop.cms.dao;

import guda.shop.cms.entity.DataBackup;
import guda.shop.common.hibernate3.Updater;

public abstract interface DataBackupDao {
    public abstract DataBackup updateByUpdater(Updater<DataBackup> paramUpdater);

    public abstract DataBackup getDataBackup();
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.DataBackupDao
 * JD-Core Version:    0.6.2
 */