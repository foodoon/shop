package guda.shop.manager;

import guda.shop.cms.entity.Brand;
import com.jspgou.cms.entity.BrandText;

public abstract interface BrandTextMng
{
  public abstract BrandText save(Brand paramBrand, String paramString);

  public abstract BrandText update(BrandText paramBrandText);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.BrandTextMng
 * JD-Core Version:    0.6.2
 */