package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ProductTextDao;
import guda.shop.cms.entity.ProductText;
import guda.shop.cms.manager.ProductTextMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductTextMngImpl
        implements ProductTextMng {
    private ProductTextDao _$1;

    public ProductText update(ProductText paramProductText) {
        Updater localUpdater = new Updater(paramProductText);
        ProductText localProductText = this._$1.updateByUpdater(localUpdater);
        return localProductText;
    }

    public ProductText save(ProductText paramProductText) {
        return this._$1.save(paramProductText);
    }

    @Autowired
    public void setDao(ProductTextDao paramProductTextDao) {
        this._$1 = paramProductTextDao;
    }
}

