package guda.shop.cms.manager;

import guda.shop.cms.entity.Gift;
iimport guda.shopcms.entity.GiftExchange;
imimport guda.shopms.entity.ShopMember;
impimport guda.shops.entity.ShopMemberAddress;
import com.jspgou.common.page.Pagination;
import java.util.List;

public abstract interface GiftExchangeMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract GiftExchange save(Gift paramGift, ShopMemberAddress paramShopMemberAddress, ShopMember paramShopMember, Integer paramInteger);

  public abstract List<GiftExchange> getlist(Long paramLong);

  public abstract GiftExchange findById(Long paramLong);

  public abstract GiftExchange save(GiftExchange paramGiftExchange);

  public abstract GiftExchange update(GiftExchange paramGiftExchange);

  public abstract GiftExchange deleteById(Long paramLong);

  public abstract GiftExchange[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.GiftExchangeMng
 * JD-Core Version:    0.6.2
 */