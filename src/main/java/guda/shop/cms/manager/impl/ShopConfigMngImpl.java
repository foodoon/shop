package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopConfigDao;
import guda.shop.cms.entity.ShopConfig;
import guda.shop.cms.manager.ShopConfigMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopConfigMngImpl
        implements ShopConfigMng {
    private ShopConfigDao _$1;

    @Transactional(readOnly = true)
    public ShopConfig findById(Long paramLong) {
        ShopConfig localShopConfig = this._$1.findById(paramLong);
        return localShopConfig;
    }

    public ShopConfig save(ShopConfig paramShopConfig) {
        this._$1.save(paramShopConfig);
        return paramShopConfig;
    }

    public ShopConfig update(ShopConfig paramShopConfig) {
        Updater localUpdater = new Updater(paramShopConfig);
        ShopConfig localShopConfig = this._$1.updateByUpdater(localUpdater);
        return localShopConfig;
    }

    public ShopConfig deleteById(Long paramLong) {
        ShopConfig localShopConfig = this._$1.deleteById(paramLong);
        return localShopConfig;
    }

    @Autowired
    public void setDao(ShopConfigDao paramShopConfigDao) {
        this._$1 = paramShopConfigDao;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopConfigMngImpl
 * JD-Core Version:    0.6.2
 */