package guda.shop.cms.dao;

import guda.shop.cms.entity.ProductText;
import guda.shop.common.hibernate3.Updater;

public abstract interface ProductTextDao {
    public abstract ProductText updateByUpdater(Updater<ProductText> paramUpdater);

    public abstract ProductText save(ProductText paramProductText);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.ProductTextDao
 * JD-Core Version:    0.6.2
 */