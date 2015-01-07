package guda.shop.cms.manager;

import guda.shop.cms.entity.Order;

import guda.shop.cms.entity.OrderReturn;
import guda.shop.common.page.Pagination;

public abstract interface OrderReturnMng
{
  public abstract Pagination getPage(Integer paramInteger, int paramInt1, int paramInt2);

  public abstract OrderReturn findById(Long paramLong);

  public abstract OrderReturn findByOrderId(Long paramLong);

  public abstract OrderReturn save(OrderReturn paramOrderReturn);

  public abstract OrderReturn save(OrderReturn paramOrderReturn, Order paramOrder, Long paramLong, Boolean paramBoolean, String[] paramArrayOfString1, String[] paramArrayOfString2);

  public abstract OrderReturn update(OrderReturn paramOrderReturn);

  public abstract OrderReturn deleteById(Long paramLong);

  public abstract OrderReturn[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.OrderReturnMng
 * JD-Core Version:    0.6.2
 */