package guda.shop.dao;

import guda.shop.cms.entity.LogisticsText;
import guda.shop.common.hibernate3.Updater;

public abstract interface LogisticsTextDao
{
  public abstract LogisticsText save(LogisticsText paramLogisticsText);

  public abstract LogisticsText updateByUpdater(Updater<LogisticsText> paramUpdater);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.LogisticsTextDao
 * JD-Core Version:    0.6.2
 */