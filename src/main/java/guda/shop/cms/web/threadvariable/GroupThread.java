package guda.shop.cms.web.threadvariable;

import guda.shop.cms.entity.ShopMemberGroup;

public class GroupThread {
    private static ThreadLocal<ShopMemberGroup> _$1 = new ThreadLocal();

    public static ShopMemberGroup get() {
        return (ShopMemberGroup) _$1.get();
    }

    public static void set(ShopMemberGroup paramShopMemberGroup) {
        _$1.set(paramShopMemberGroup);
    }

    public static void remove() {
        _$1.remove();
    }
}

