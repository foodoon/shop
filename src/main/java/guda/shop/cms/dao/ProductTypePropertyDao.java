package guda.shop.cms.dao;

import guda.shop.cms.entity.ProductTypeProperty;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.List;

public abstract interface ProductTypePropertyDao
{
  public abstract Pagination getList(int paramInt1, int paramInt2, Long paramLong, Boolean paramBoolean, String paramString1, String paramString2);

  public abstract List<ProductTypeProperty> getList(Long paramLong, Boolean paramBoolean);

  public abstract ProductTypeProperty findById(Long paramLong);

  public abstract ProductTypeProperty save(ProductTypeProperty paramProductTypeProperty);

  public abstract ProductTypeProperty updateByUpdater(Updater<ProductTypeProperty> paramUpdater);

  public abstract ProductTypeProperty deleteById(Long paramLong);

  public abstract List<ProductTypeProperty> findBySearch(String paramString1, String paramString2);

  public abstract List<ProductTypeProperty> findListByTypeId(Long paramLong);

  public abstract List<ProductTypeProperty> getList(Long paramLong, boolean paramBoolean);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ProductTypePropertyDao
 * JD-Core Version:    0.6.2
 */