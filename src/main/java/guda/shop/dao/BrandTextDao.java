package guda.shop.dao;

import guda.shop.cms.entity.BrandText;
import guda.shop.common.hibernate3.Updater;

public abstract interface BrandTextDao
{
  public abstract BrandText save(BrandText paramBrandText);

  public abstract BrandText updateByUpdater(Updater<BrandText> paramUpdater);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.BrandTextDao
 * JD-Core Version:    0.6.2
 */