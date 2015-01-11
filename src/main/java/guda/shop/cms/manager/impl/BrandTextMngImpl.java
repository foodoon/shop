package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.BrandTextDao;
import guda.shop.cms.entity.Brand;
import guda.shop.cms.entity.BrandText;
import guda.shop.cms.manager.BrandTextMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BrandTextMngImpl
        implements BrandTextMng {
    private BrandTextDao _$1;

    public BrandText save(Brand paramBrand, String paramString) {
        BrandText localBrandText = new BrandText();
        localBrandText.setBrand(paramBrand);
        localBrandText.setText(paramString);
        this._$1.save(localBrandText);
        return localBrandText;
    }

    public BrandText update(BrandText paramBrandText) {
        BrandText localBrandText = this._$1.updateByUpdater(new Updater(paramBrandText));
        return localBrandText;
    }

    @Autowired
    public void setDao(BrandTextDao paramBrandTextDao) {
        this._$1 = paramBrandTextDao;
    }
}

