package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.DataBackupDao;
import guda.shop.cms.entity.DataBackup;
import guda.shop.cms.manager.DataBackupMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataBackupMngImpl
        implements DataBackupMng {

    @Autowired
    private DataBackupDao _$1;

    public DataBackup getDataBackup() {
        return this._$1.getDataBackup();
    }

    public DataBackup update(DataBackup paramDataBackup) {
        Updater localUpdater = new Updater(paramDataBackup);
        DataBackup localDataBackup = this._$1.updateByUpdater(localUpdater);
        return localDataBackup;
    }
}

