package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopScoreDao;
import guda.shop.cms.entity.ShopScore;
import guda.shop.cms.manager.ShopScoreMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShopScoreMngImpl
        implements ShopScoreMng {
    private ShopScoreDao _$1;

    @Transactional(readOnly = true)
    public Pagination getPage(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, Date paramDate1, Date paramDate2, Integer paramInteger1, Integer paramInteger2) {
        Pagination localPagination = this._$1.getPage(paramLong, paramBoolean1, paramBoolean2, paramDate1, paramDate2, paramInteger2, paramInteger1);
        return localPagination;
    }

    @Transactional(readOnly = true)
    public List<ShopScore> getlist(String paramString) {
        return this._$1.getlist(paramString);
    }

    @Transactional(readOnly = true)
    public ShopScore findById(Long paramLong) {
        ShopScore localShopScore = this._$1.findById(paramLong);
        return localShopScore;
    }

    public ShopScore save(ShopScore paramShopScore) {
        this._$1.save(paramShopScore);
        return paramShopScore;
    }

    public ShopScore update(ShopScore paramShopScore) {
        Updater localUpdater = new Updater(paramShopScore);
        ShopScore localShopScore = this._$1.updateByUpdater(localUpdater);
        return localShopScore;
    }

    public ShopScore deleteById(Long paramLong) {
        ShopScore localShopScore = this._$1.deleteById(paramLong);
        return localShopScore;
    }

    public ShopScore[] deleteByIds(Long[] paramArrayOfLong) {
        ShopScore[] arrayOfShopScore = new ShopScore[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfShopScore[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfShopScore;
    }

    @Autowired
    public void setDao(ShopScoreDao paramShopScoreDao) {
        this._$1 = paramShopScoreDao;
    }
}

