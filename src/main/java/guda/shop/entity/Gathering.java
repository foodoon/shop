package guda.shop.entity;

import guda.shop.cms.entity.base.BaseGathering;

public class Gathering extends BaseGathering
{
  private static final long serialVersionUID = 1L;

  public Gathering()
  {
  }

  public Gathering(Long paramLong)
  {
    super(paramLong);
  }

  public Gathering(Long paramLong, Order paramOrder, ShopAdmin paramShopAdmin, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramLong, paramOrder, paramShopAdmin, paramString1, paramString2, paramString3, paramString4);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Gathering
 * JD-Core Version:    0.6.2
 */