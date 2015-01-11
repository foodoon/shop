package guda.shop.cms.dao;

import guda.shop.cms.entity.Cart;

public abstract interface CartDao {
    public abstract Cart findById(Long paramLong);

    public abstract Cart saveOrUpdate(Cart paramCart);

    public abstract Cart update(Cart paramCart);

    public abstract Cart deleteById(Long paramLong);
}

