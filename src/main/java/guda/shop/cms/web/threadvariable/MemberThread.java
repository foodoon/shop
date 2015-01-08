package guda.shop.cms.web.threadvariable;

import guda.shop.cms.entity.ShopMember;

public class MemberThread {
    private static ThreadLocal<ShopMember> _$1 = new ThreadLocal();

    public static ShopMember get() {
        return (ShopMember) _$1.get();
    }

    public static void set(ShopMember paramShopMember) {
        _$1.set(paramShopMember);
    }

    public static void remove() {
        _$1.remove();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.threadvariable.MemberThread
 * JD-Core Version:    0.6.2
 */