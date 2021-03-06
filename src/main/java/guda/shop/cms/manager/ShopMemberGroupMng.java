package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopMemberGroup;

import java.util.List;

public abstract interface ShopMemberGroupMng {
    public abstract ShopMemberGroup findGroupByScore(Long paramLong, int paramInt);

    public abstract List<ShopMemberGroup> getList(Long paramLong);

    public abstract ShopMemberGroup findById(Long paramLong);

    public abstract ShopMemberGroup save(ShopMemberGroup paramShopMemberGroup);

    public abstract ShopMemberGroup update(ShopMemberGroup paramShopMemberGroup);

    public abstract ShopMemberGroup deleteById(Long paramLong);

    public abstract ShopMemberGroup[] deleteByIds(Long[] paramArrayOfLong);
}

