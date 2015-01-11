package guda.shop.cms.web.threadvariable;

import guda.shop.cms.entity.ShopAdmin;

public class AdminThread {
    private static ThreadLocal<ShopAdmin> _$1 = new ThreadLocal();

    public static ShopAdmin get() {
        return (ShopAdmin) _$1.get();
    }

    public static void set(ShopAdmin paramShopAdmin) {
        _$1.set(paramShopAdmin);
    }

    public static void remove() {
        _$1.remove();
    }
}

