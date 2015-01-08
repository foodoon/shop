package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopConfigDao;
import guda.shop.cms.entity.ShopConfig;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class ShopConfigDaoImpl extends HibernateBaseDao<ShopConfig, Long>
        implements ShopConfigDao {
    public ShopConfig findById(Long paramLong) {
        ShopConfig localShopConfig = (ShopConfig) get(paramLong);
        return localShopConfig;
    }

    public ShopConfig save(ShopConfig paramShopConfig) {
        getSession().save(paramShopConfig);
        return paramShopConfig;
    }

    public ShopConfig deleteById(Long paramLong) {
        ShopConfig localShopConfig = (ShopConfig) super.get(paramLong);
        if (localShopConfig != null)
            getSession().delete(localShopConfig);
        return localShopConfig;
    }

    protected Class<ShopConfig> getEntityClass() {
        return ShopConfig.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopConfigDaoImpl
 * JD-Core Version:    0.6.2
 */