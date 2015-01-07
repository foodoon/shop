package guda.shop.web.threadvariable;

import guda.shop.cms.entity.ShopAdmin;

public class AdminThread
{
  private static ThreadLocal<ShopAdmin> _$1 = new ThreadLocal();

  public static ShopAdmin get()
  {
    return (ShopAdmin)_$1.get();
  }

  public static void set(ShopAdmin paramShopAdmin)
  {
    _$1.set(paramShopAdmin);
  }

  public static void remove()
  {
    _$1.remove();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.web.threadvariable.AdminThread
 * JD-Core Version:    0.6.2
 */