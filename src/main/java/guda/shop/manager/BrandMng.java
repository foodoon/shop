package guda.shop.manager;

import guda.shop.cms.entity.Brand;
import java.util.List;

public abstract interface BrandMng
{
  public abstract List<Brand> getAllList();

  public abstract List<Brand> getList();

  public abstract List<Brand> getListForTag(Long paramLong, int paramInt1, int paramInt2);

  public abstract Brand findById(Long paramLong);

  public abstract Brand save(Brand paramBrand, String paramString);

  public abstract Brand update(Brand paramBrand, String paramString);

  public abstract Brand deleteById(Long paramLong);

  public abstract Brand[] deleteByIds(Long[] paramArrayOfLong);

  public abstract Brand[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger);

  public abstract Brand getsiftBrand();

  public abstract boolean brandNameNotExist(String paramString);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.BrandMng
 * JD-Core Version:    0.6.2
 */