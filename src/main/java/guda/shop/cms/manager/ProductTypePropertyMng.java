package guda.shop.cms.manager;

import guda.shop.cms.entity.ProductTypeProperty;
import guda.shop.common.page.Pagination;
import java.util.List;

public abstract interface ProductTypePropertyMng
{
  public abstract Pagination getList(int paramInt1, int paramInt2, Long paramLong, Boolean paramBoolean, String paramString1, String paramString2);

  public abstract List<ProductTypeProperty> getList(Long paramLong, Boolean paramBoolean);

  public abstract void saveList(List<ProductTypeProperty> paramList);

  public abstract ProductTypeProperty findById(Long paramLong);

  public abstract ProductTypeProperty save(ProductTypeProperty paramProductTypeProperty);

  public abstract void updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger, String[] paramArrayOfString, Boolean[] paramArrayOfBoolean);

  public abstract ProductTypeProperty deleteById(Long paramLong);

  public abstract ProductTypeProperty[] deleteByIds(Long[] paramArrayOfLong);

  public abstract ProductTypeProperty update(ProductTypeProperty paramProductTypeProperty);

  public abstract List<ProductTypeProperty> findBySearch(String paramString1, String paramString2);

  public abstract List<ProductTypeProperty> findListByTypeId(Long paramLong);

  public abstract List<ProductTypeProperty> getList(Long paramLong, boolean paramBoolean);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ProductTypePropertyMng
 * JD-Core Version:    0.6.2
 */