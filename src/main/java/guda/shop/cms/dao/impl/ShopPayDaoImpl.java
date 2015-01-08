package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopPayDao;
import guda.shop.cms.entity.ShopPay;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public class ShopPayDaoImpl extends HibernateBaseDao<ShopPay, Integer>
        implements ShopPayDao {
    public Pagination getPageShopPay(int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from ShopPay bean");
        localFinder.append(" order by bean.id desc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public ShopPay findById(Integer paramInteger) {
        ShopPay localShopPay = (ShopPay) get(paramInteger);
        return localShopPay;
    }

    public ShopPay save(ShopPay paramShopPay) {
        getSession().save(paramShopPay);
        return paramShopPay;
    }

    public ShopPay deleteById(Integer paramInteger) {
        ShopPay localShopPay = (ShopPay) super.get(paramInteger);
        if (localShopPay != null)
            getSession().delete(localShopPay);
        return localShopPay;
    }

    protected Class<ShopPay> getEntityClass() {
        return ShopPay.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopPayDaoImpl
 * JD-Core Version:    0.6.2
 */