package guda.shop.cms.dao;

import guda.shop.cms.entity.Advertise;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface AdvertiseDao {
    public abstract Pagination getPage(Integer paramInteger, Boolean paramBoolean, int paramInt1, int paramInt2);

    public abstract List<Advertise> getList(Integer paramInteger, Boolean paramBoolean);

    public abstract Advertise findById(Integer paramInteger);

    public abstract Advertise save(Advertise paramAdvertise);

    public abstract Advertise updateByUpdater(Updater<Advertise> paramUpdater);

    public abstract Advertise deleteById(Integer paramInteger);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.AdvertiseDao
 * JD-Core Version:    0.6.2
 */