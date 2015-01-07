package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopChannel;
import guda.shop.cms.entity.ShopChannelContent;
import guda.shop.common.page.Pagination;

public abstract interface ShopChannelContentMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract ShopChannelContent findById(Long paramLong);

  public abstract ShopChannelContent save(String paramString, ShopChannel paramShopChannel);

  public abstract ShopChannelContent update(ShopChannelContent paramShopChannelContent);

  public abstract ShopChannelContent deleteById(Long paramLong);

  public abstract ShopChannelContent[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ShopChannelContentMng
 * JD-Core Version:    0.6.2
 */