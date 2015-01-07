package guda.shop.cms.manager;

import guda.shop.cms.entity.Gift;
import guda.shop.common.page.Pagination;

public abstract interface GiftMng
{
  public abstract Pagination getPageGift(int paramInt1, int paramInt2);

  public abstract Gift findById(Long paramLong);

  public abstract Gift save(Gift paramGift);

  public abstract Gift updateByUpdater(Gift paramGift);

  public abstract Gift deleteById(Long paramLong);

  public abstract Gift updateByGiftnumb(Long paramLong1, Integer paramInteger, Long paramLong2);

  public abstract Gift[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.GiftMng
 * JD-Core Version:    0.6.2
 */