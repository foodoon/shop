package guda.shop.cms.manager;

import guda.shop.cms.entity.ShopMember;
import guda.shop.cms.entity.ShopMemberGroup;
import guda.shop.common.page.Pagination;
import guda.shop.core.entity.User;

public abstract interface ShopMemberMng
{
  public abstract ShopMember getByUsername(Long paramLong, String paramString);

  public abstract ShopMember getByUserId(Long paramLong1, Long paramLong2);

  public abstract ShopMember register(String paramString1, String paramString2, String paramString3, Boolean paramBoolean1, String paramString4, String paramString5, Boolean paramBoolean2, Long paramLong1, Long paramLong2, Long paramLong3, Long paramLong4, Long paramLong5, Long paramLong6, Long paramLong7, ShopMember paramShopMember);

  public abstract ShopMember join(String paramString, Long paramLong1, Long paramLong2);

  public abstract ShopMember join(Long paramLong1, Long paramLong2, ShopMemberGroup paramShopMemberGroup);

  public abstract ShopMember join(User paramUser, Long paramLong, ShopMemberGroup paramShopMemberGroup);

  public abstract ShopMember register(String paramString1, String paramString2, String paramString3, Boolean paramBoolean1, String paramString4, String paramString5, Boolean paramBoolean2, Long paramLong1, Long paramLong2);

  public abstract Pagination getPage(Long paramLong, int paramInt1, int paramInt2);

  public abstract ShopMember findById(Long paramLong);

  public abstract ShopMember save(ShopMember paramShopMember);

  public abstract ShopMember update(ShopMember paramShopMember, Long paramLong1, Long paramLong2, Long paramLong3, Long paramLong4, Long paramLong5, Long paramLong6, String paramString1, String paramString2, Boolean paramBoolean);

  public abstract ShopMember update(ShopMember paramShopMember, Long paramLong1, Long paramLong2, Long paramLong3, Long paramLong4, Long paramLong5, Long paramLong6);

  public abstract ShopMember deleteById(Long paramLong);

  public abstract ShopMember[] deleteByIds(Long[] paramArrayOfLong);

  public abstract ShopMember update(ShopMember paramShopMember);

  public abstract ShopMember updateScore(ShopMember paramShopMember, Integer paramInteger);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ShopMemberMng
 * JD-Core Version:    0.6.2
 */