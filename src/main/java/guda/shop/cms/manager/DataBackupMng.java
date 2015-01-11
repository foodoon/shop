package guda.shop.cms.manager;

import guda.shop.cms.entity.DataBackup;

public abstract interface DataBackupMng {
    public abstract DataBackup update(DataBackup paramDataBackup);

    public abstract DataBackup getDataBackup();
}

