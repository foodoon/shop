package guda.shop.core.dao;

import guda.shop.core.entity.Global;

public abstract interface GlobalDao {
    public abstract Global findById(Long paramLong);

    public abstract Global update(Global paramGlobal);
}

