package guda.shop.core.manager;

import guda.shop.common.page.Pagination;
import com.jspgou.core.entity.Log;

public abstract interface LogMng
{
  public abstract Pagination getPage(int paramInt1, int paramInt2);

  public abstract Log findById(Long paramLong);

  public abstract Log save(Log paramLog);

  public abstract void save(String paramString1, String paramString2);

  public abstract Log update(Log paramLog);

  public abstract Log deleteById(Long paramLong);

  public abstract Log[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.LogMng
 * JD-Core Version:    0.6.2
 */