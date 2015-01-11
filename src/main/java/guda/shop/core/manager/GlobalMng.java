package guda.shop.core.manager;

import guda.shop.core.entity.Global;

public abstract interface GlobalMng {
    public abstract Global findById(Long paramLong);

    public abstract Global update(Global paramGlobal);
}

