package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseCartGift;
import guda.shop.core.entity.Website;

public class CartGift extends BaseCartGift
{
  private static final long serialVersionUID = 1L;

  public CartGift()
  {
  }

  public CartGift(Long paramLong)
  {
    super(paramLong);
  }

  public CartGift(Long paramLong, Website paramWebsite, Cart paramCart, Gift paramGift, Integer paramInteger)
  {
    super(paramLong, paramWebsite, paramCart, paramGift, paramInteger);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.CartGift
 * JD-Core Version:    0.6.2
 */