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

