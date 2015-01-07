package guda.shop.dao;

import guda.shop.cms.entity.OrderItem;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.List;

public abstract interface OrderItemDao
{
  public abstract List<Object[]> profitTop(Long paramLong1, Long paramLong2, Integer paramInteger1, Integer paramInteger2);

  public abstract Integer totalCount(Long paramLong1, Long paramLong2);

  public abstract List<Object[]> getOrderItem();

  public abstract OrderItem findByMember(Long paramLong1, Long paramLong2);

  public abstract OrderItem findById(Long paramLong);

  public abstract OrderItem updateByUpdater(Updater<OrderItem> paramUpdater);

  public abstract Pagination getPageForMember(Long paramLong, Integer paramInteger, int paramInt1, int paramInt2);

  public abstract List<OrderItem> getOrderItem(Long paramLong);

  public abstract List<OrderItem> getOrderItemList(Long paramLong, Integer paramInteger1, Integer paramInteger2);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.OrderItemDao
 * JD-Core Version:    0.6.2
 */