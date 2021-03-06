package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopMember;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;

public abstract interface ShopMemberDao {
    public abstract Pagination getPage(Long paramLong, int paramInt1, int paramInt2);

    public abstract ShopMember findById(Long paramLong);

    public abstract ShopMember save(ShopMember paramShopMember);

    public abstract ShopMember updateByUpdater(Updater<ShopMember> paramUpdater);

    public abstract ShopMember deleteById(Long paramLong);
}

