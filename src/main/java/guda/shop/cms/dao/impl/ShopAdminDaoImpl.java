package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopAdminDao;
import guda.shop.cms.entity.ShopAdmin;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.springframework.stereotype.Repository;

@Repository
public class ShopAdminDaoImpl extends HibernateBaseDao<ShopAdmin, Long>
        implements ShopAdminDao {
    public Pagination getPage(Long paramLong, int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("from ShopAdmin bean where bean.website.id=:webId order by bean.id desc");
        localFinder.setParam("webId", paramLong);
        return find(localFinder, paramInt1, paramInt2);
    }

    public ShopAdmin findById(Long paramLong) {
        ShopAdmin localShopAdmin = (ShopAdmin) get(paramLong);
        return localShopAdmin;
    }

    public ShopAdmin save(ShopAdmin paramShopAdmin) {
        getSession().save(paramShopAdmin);
        return paramShopAdmin;
    }

    public ShopAdmin deleteById(Long paramLong) {
        ShopAdmin localShopAdmin = (ShopAdmin) super.get(paramLong);
        if (localShopAdmin != null)
            getSession().delete(localShopAdmin);
        return localShopAdmin;
    }

    protected Class<ShopAdmin> getEntityClass() {
        return ShopAdmin.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopAdminDaoImpl
 * JD-Core Version:    0.6.2
 */