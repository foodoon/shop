package guda.shop.manager;

import guda.shop.cms.entity.Logistics;
import com.jspgou.cms.entity.LogisticsText;

public abstract interface LogisticsTextMng
{
  public abstract LogisticsText save(Logistics paramLogistics, String paramString);

  public abstract LogisticsText update(LogisticsText paramLogisticsText);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.LogisticsTextMng
 * JD-Core Version:    0.6.2
 */