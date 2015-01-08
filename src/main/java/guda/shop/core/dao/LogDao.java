package guda.shop.core.dao;

import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import guda.shop.core.entity.Log;

public abstract interface LogDao {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract Log findById(Long paramLong);

    public abstract Log save(Log paramLog);

    public abstract Log updateByUpdater(Updater<Log> paramUpdater);

    public abstract Log deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.LogDao
 * JD-Core Version:    0.6.2
 */