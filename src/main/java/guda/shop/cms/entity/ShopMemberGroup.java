package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShopMemberGroup;
import guda.shop.core.entity.Website;

public class ShopMemberGroup extends BaseShopMemberGroup
{
  private static final long serialVersionUID = 1L;

  public ShopMemberGroup()
  {
  }

  public ShopMemberGroup(Long paramLong)
  {
    super(paramLong);
  }

  public ShopMemberGroup(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger1, Integer paramInteger2)
  {
    super(paramLong, paramWebsite, paramString, paramInteger1, paramInteger2);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ShopMemberGroup
 * JD-Core Version:    0.6.2
 */