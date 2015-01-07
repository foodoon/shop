package guda.shop.cms.dao;

import guda.shop.cms.entity.ProductType;
import guda.shop.common.hibernate3.Updater;
import java.util.List;

public abstract interface ProductTypeDao
{
  public abstract List<ProductType> getList(Long paramLong);

  public abstract ProductType findById(Long paramLong);

  public abstract ProductType save(ProductType paramProductType);

  public abstract ProductType updateByUpdater(Updater<ProductType> paramUpdater);

  public abstract ProductType deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ProductTypeDao
 * JD-Core Version:    0.6.2
 */