package guda.shop.manager;

import guda.shop.cms.entity.ProductType;
import java.util.List;

public abstract interface ProductTypeMng
{
  public abstract List<ProductType> getList(Long paramLong);

  public abstract ProductType findById(Long paramLong);

  public abstract ProductType save(ProductType paramProductType);

  public abstract ProductType update(ProductType paramProductType);

  public abstract ProductType deleteById(Long paramLong);

  public abstract ProductType[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ProductTypeMng
 * JD-Core Version:    0.6.2
 */