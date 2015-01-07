package guda.shop.manager;

import guda.shop.cms.entity.OrderItem;
import com.jspgou.common.page.Pagination;
import java.util.List;

public abstract interface OrderItemMng
{
  public abstract List<Object[]> profitTop(Long paramLong1, Long paramLong2, Integer paramInteger1, Integer paramInteger2);

  public abstract Integer totalCount(Long paramLong1, Long paramLong2);

  public abstract List<Object[]> getOrderItem();

  public abstract Pagination getPageByMember(Integer paramInteger, Long paramLong, int paramInt1, int paramInt2);

  public abstract OrderItem findByMember(Long paramLong1, Long paramLong2);

  public abstract OrderItem findById(Long paramLong);

  public abstract OrderItem updateByUpdater(OrderItem paramOrderItem);

  public abstract Pagination getOrderItem(Integer paramInteger1, Integer paramInteger2, Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.OrderItemMng
 * JD-Core Version:    0.6.2
 */