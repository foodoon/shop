package guda.shop.cms.dao;

import guda.shop.cms.entity.CartItem;
import guda.shop.common.hibernate3.Updater;
import java.util.List;

public abstract interface CartItemDao
{
  public abstract CartItem findById(Long paramLong);

  public abstract List<CartItem> getlist(Long paramLong1, Long paramLong2);

  public abstract CartItem updateByUpdater(Updater<CartItem> paramUpdater);

  public abstract CartItem deleteById(Long paramLong);

  public abstract int deleteByProductId(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.CartItemDao
 * JD-Core Version:    0.6.2
 */