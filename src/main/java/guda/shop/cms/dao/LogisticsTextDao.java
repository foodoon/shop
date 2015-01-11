package guda.shop.cms.dao;

import guda.shop.cms.entity.LogisticsText;
import guda.shop.common.hibernate3.Updater;

public abstract interface LogisticsTextDao {
    public abstract LogisticsText save(LogisticsText paramLogisticsText);

    public abstract LogisticsText updateByUpdater(Updater<LogisticsText> paramUpdater);
}

