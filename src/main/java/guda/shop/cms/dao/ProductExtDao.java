package guda.shop.cms.dao;

import guda.shop.cms.entity.ProductExt;
import guda.shop.common.hibernate3.Updater;

public abstract interface ProductExtDao {
    public abstract ProductExt findById(Long paramLong);

    public abstract ProductExt save(ProductExt paramProductExt);

    public abstract ProductExt updateByUpdater(Updater<ProductExt> paramUpdater);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ProductExtDao
 * JD-Core Version:    0.6.2
 */