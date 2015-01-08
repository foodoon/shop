package guda.shop.cms.dao;

import guda.shop.cms.entity.Adspace;
import guda.shop.common.hibernate3.Updater;

import java.util.List;

public abstract interface AdspaceDao {
    public abstract Adspace findById(Integer paramInteger);

    public abstract Adspace save(Adspace paramAdspace);

    public abstract Adspace updateByUpdater(Updater<Adspace> paramUpdater);

    public abstract Adspace deleteById(Integer paramInteger);

    public abstract List<Adspace> getList();
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.AdspaceDao
 * JD-Core Version:    0.6.2
 */