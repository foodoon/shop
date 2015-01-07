package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BasePopularityItem;

public class PopularityItem extends BasePopularityItem
{
  private static final long serialVersionUID = 1L;

  public PopularityItem()
  {
  }

  public PopularityItem(Long paramLong)
  {
    super(paramLong);
  }

  public PopularityItem(Long paramLong, Cart paramCart, Integer paramInteger)
  {
    super(paramLong, paramCart, paramInteger);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.PopularityItem
 * JD-Core Version:    0.6.2
 */