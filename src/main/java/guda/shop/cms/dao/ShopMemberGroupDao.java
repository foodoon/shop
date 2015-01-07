package guda.shop.cms.dao;

import guda.shop.cms.entity.ShopMemberGroup;
import guda.shop.common.hibernate3.Updater;
import java.util.List;

public abstract interface ShopMemberGroupDao
{
  public abstract List<ShopMemberGroup> getList(Long paramLong, boolean paramBoolean);

  public abstract ShopMemberGroup findById(Long paramLong);

  public abstract ShopMemberGroup save(ShopMemberGroup paramShopMemberGroup);

  public abstract ShopMemberGroup updateByUpdater(Updater<ShopMemberGroup> paramUpdater);

  public abstract ShopMemberGroup deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ShopMemberGroupDao
 * JD-Core Version:    0.6.2
 */