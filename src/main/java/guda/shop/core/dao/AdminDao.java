package guda.shop.core.dao;

import guda.shop.common.hibernate3.Updater;
import guda.shop.core.entity.Admin;

public abstract interface AdminDao {
    public abstract Admin getByUserId(Long paramLong1, Long paramLong2);

    public abstract Admin findById(Long paramLong);

    public abstract Admin save(Admin paramAdmin);

    public abstract Admin updateByUpdater(Updater<Admin> paramUpdater);

    public abstract Admin deleteById(Long paramLong);
}

