package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ProductStandardDao;
import guda.shop.cms.entity.ProductStandard;
import guda.shop.cms.manager.ProductStandardMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductStandardMngImpl
        implements ProductStandardMng {
    private ProductStandardDao _$1;

    @Transactional(readOnly = true)
    public Pagination getPage(int paramInt1, int paramInt2) {
        Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
        return localPagination;
    }

    @Transactional(readOnly = true)
    public ProductStandard findById(Long paramLong) {
        ProductStandard localProductStandard = this._$1.findById(paramLong);
        return localProductStandard;
    }

    public ProductStandard findByProductIdAndStandardId(Long paramLong1, Long paramLong2) {
        List localList = this._$1.findByProductIdAndStandardId(paramLong1, paramLong2);
        if (localList.size() > 0)
            return (ProductStandard) localList.get(0);
        return null;
    }

    public List<ProductStandard> findByProductId(Long paramLong) {
        return this._$1.findByProductId(paramLong);
    }

    public ProductStandard save(ProductStandard paramProductStandard) {
        this._$1.save(paramProductStandard);
        return paramProductStandard;
    }

    public ProductStandard update(ProductStandard paramProductStandard) {
        Updater localUpdater = new Updater(paramProductStandard);
        ProductStandard localProductStandard = this._$1.updateByUpdater(localUpdater);
        return localProductStandard;
    }

    public ProductStandard deleteById(Long paramLong) {
        ProductStandard localProductStandard = this._$1.deleteById(paramLong);
        return localProductStandard;
    }

    public ProductStandard[] deleteByIds(Long[] paramArrayOfLong) {
        ProductStandard[] arrayOfProductStandard = new ProductStandard[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfProductStandard[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfProductStandard;
    }

    @Autowired
    public void setDao(ProductStandardDao paramProductStandardDao) {
        this._$1 = paramProductStandardDao;
    }
}

