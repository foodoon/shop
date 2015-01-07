package guda.shop.cms.web.threadvariable;

import guda.shop.cms.entity.ShopMemberGroup;

public class GroupThread
{
  private static ThreadLocal<ShopMemberGroup> _$1 = new ThreadLocal();

  public static ShopMemberGroup get()
  {
    return (ShopMemberGroup)_$1.get();
  }

  public static void set(ShopMemberGroup paramShopMemberGroup)
  {
    _$1.set(paramShopMemberGroup);
  }

  public static void remove()
  {
    _$1.remove();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.threadvariable.GroupThread
 * JD-Core Version:    0.6.2
 */