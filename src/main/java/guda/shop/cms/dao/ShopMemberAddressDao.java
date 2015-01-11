package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopMemberAddress;
import guda.shop.common.hibernate3.Updater;

import java.util.List;

public abstract interface ShopMemberAddressDao {
    public abstract List<ShopMemberAddress> getList(Long paramLong);

    public abstract List<ShopMemberAddress> findByMemberDefault(Long paramLong, Boolean paramBoolean);

    public abstract ShopMemberAddress findById(Long paramLong);

    public abstract ShopMemberAddress save(ShopMemberAddress paramShopMemberAddress);

    public abstract ShopMemberAddress updateByUpdater(Updater<ShopMemberAddress> paramUpdater);

    public abstract ShopMemberAddress deleteById(Long paramLong);
}

