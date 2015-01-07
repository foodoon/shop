package guda.shop.cms.dao;

import guda.shop.cms.entity.ProductTag;
import guda.shop.common.hibernate3.Updater;
import java.util.List;

public abstract interface ProductTagDao
{
  public abstract List<ProductTag> getList(Long paramLong);

  public abstract ProductTag findById(Long paramLong);

  public abstract ProductTag save(ProductTag paramProductTag);

  public abstract ProductTag updateByUpdater(Updater<ProductTag> paramUpdater);

  public abstract ProductTag deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ProductTagDao
 * JD-Core Version:    0.6.2
 */