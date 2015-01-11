package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopMemberAddress;

import java.util.List;

public abstract interface ShopMemberAddressMng {
    public abstract List<ShopMemberAddress> getList(Long paramLong);

    public abstract ShopMemberAddress findByMemberDefault(Long paramLong, Boolean paramBoolean);

    public abstract List<ShopMemberAddress> findByMemberId(Long paramLong);

    public abstract ShopMemberAddress findById(Long paramLong);

    public abstract ShopMemberAddress save(ShopMemberAddress paramShopMemberAddress);

    public abstract ShopMemberAddress updateByUpdater(ShopMemberAddress paramShopMemberAddress);

    public abstract ShopMemberAddress deleteById(Long paramLong1, Long paramLong2);
}

