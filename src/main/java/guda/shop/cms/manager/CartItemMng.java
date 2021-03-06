package guda.shop.cms.manager;

import guda.shop.cms.entity.CartItem;

import java.util.List;

public abstract interface CartItemMng {
    public abstract List<CartItem> getlist(Long paramLong1, Long paramLong2);

    public abstract CartItem findById(Long paramLong);

    public abstract CartItem updateByUpdater(CartItem paramCartItem);

    public abstract CartItem deleteById(Long paramLong);

    public abstract CartItem[] deleteByIds(Long[] paramArrayOfLong);

    public abstract int deleteByProductId(Long paramLong);
}

