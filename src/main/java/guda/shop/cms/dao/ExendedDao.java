package guda.shop.cms.dao;

import guda.shop.cms.entity.Exended;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface ExendedDao {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract Exended findById(Long paramLong);

    public abstract List<Exended> getList();

    public abstract List<Exended> getList(Long paramLong);

    public abstract Exended save(Exended paramExended);

    public abstract Exended updateByUpdater(Updater<Exended> paramUpdater);

    public abstract Exended deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ExendedDao
 * JD-Core Version:    0.6.2
 */