package guda.shop.core.dao;

import guda.shop.core.entity.Global;

public abstract interface GlobalDao
{
  public abstract Global findById(Long paramLong);

  public abstract Global update(Global paramGlobal);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.dao.GlobalDao
 * JD-Core Version:    0.6.2
 */