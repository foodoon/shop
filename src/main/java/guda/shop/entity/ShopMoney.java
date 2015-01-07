package guda.shop.entity;

import guda.shop.cms.entity.base.BaseShopMoney;
import java.util.Date;

public class ShopMoney extends BaseShopMoney
{
  private static final long serialVersionUID = 1L;

  public ShopMoney()
  {
  }

  public ShopMoney(Long paramLong)
  {
    super(paramLong);
  }

  public ShopMoney(Long paramLong, String paramString, Double paramDouble, boolean paramBoolean, Date paramDate)
  {
    super(paramLong, paramString, paramDouble, paramBoolean, paramDate);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ShopMoney
 * JD-Core Version:    0.6.2
 */