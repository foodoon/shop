package guda.shop.cms.manager;

import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductExt;

public abstract interface ProductExtMng
{
  public abstract ProductExt save(ProductExt paramProductExt, Product paramProduct);

  public abstract ProductExt saveOrUpdate(ProductExt paramProductExt, Product paramProduct);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ProductExtMng
 * JD-Core Version:    0.6.2
 */