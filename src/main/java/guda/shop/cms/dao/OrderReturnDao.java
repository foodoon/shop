package guda.shop.cms.dao;

import guda.shop.cms.entity.OrderReturn;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.List;

public abstract interface OrderReturnDao
{
  public abstract Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract OrderReturn findById(Long paramLong);

  public abstract OrderReturn save(OrderReturn paramOrderReturn);

  public abstract List<OrderReturn> findByOrderId(Long paramLong);

  public abstract OrderReturn updateByUpdater(Updater<OrderReturn> paramUpdater);

  public abstract OrderReturn deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.OrderReturnDao
 * JD-Core Version:    0.6.2
 */