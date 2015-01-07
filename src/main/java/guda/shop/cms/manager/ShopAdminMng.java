package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopAdmin;
import com.jspgou.common.page.Pagination;

public abstract interface ShopAdminMng
{
  public abstract ShopAdmin getByUserId(Long paramLong1, Long paramLong2);

  public abstract ShopAdmin register(String paramString1, String paramString2, Boolean paramBoolean, String paramString3, String paramString4, boolean paramBoolean1, Long paramLong, ShopAdmin paramShopAdmin);

  public abstract Pagination getPage(Long paramLong, int paramInt1, int paramInt2);

  public abstract ShopAdmin findById(Long paramLong);

  public abstract ShopAdmin save(ShopAdmin paramShopAdmin);

  public abstract ShopAdmin update(ShopAdmin paramShopAdmin, String paramString1, Boolean paramBoolean1, String paramString2, Boolean paramBoolean2);

  public abstract ShopAdmin deleteById(Long paramLong);

  public abstract ShopAdmin[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ShopAdminMng
 * JD-Core Version:    0.6.2
 */