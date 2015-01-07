package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseConsult;

public class Consult extends BaseConsult
{
  private static final long serialVersionUID = 1L;

  public Consult()
  {
  }

  public Consult(Long paramLong)
  {
    super(paramLong);
  }

  public Consult(Long paramLong, ShopMember paramShopMember, Product paramProduct)
  {
    super(paramLong, paramShopMember, paramProduct);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Consult
 * JD-Core Version:    0.6.2
 */