package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.StandardDao;
import guda.shop.cms.entity.Standard;
import guda.shop.cms.manager.StandardMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StandardMngImpl
        implements StandardMng {
    private StandardDao _$1;

    @Transactional(readOnly = true)
    public Pagination getPage(int paramInt1, int paramInt2) {
        Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
        return localPagination;
    }

    @Transactional(readOnly = true)
    public Standard findById(Long paramLong) {
        Standard localStandard = this._$1.findById(paramLong);
        return localStandard;
    }

    public List<Standard> findByTypeId(Long paramLong) {
        return this._$1.findByTypeId(paramLong);
    }

    public Standard save(Standard paramStandard) {
        this._$1.save(paramStandard);
        return paramStandard;
    }

    public Standard update(Standard paramStandard) {
        Updater localUpdater = new Updater(paramStandard);
        Standard localStandard = this._$1.updateByUpdater(localUpdater);
        return localStandard;
    }

    public Standard deleteById(Long paramLong) {
        Standard localStandard = this._$1.deleteById(paramLong);
        return localStandard;
    }

    public Standard[] deleteByIds(Long[] paramArrayOfLong) {
        Standard[] arrayOfStandard = new Standard[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfStandard[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfStandard;
    }

    public Standard[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger) {
        int i = paramArrayOfLong.length;
        Standard[] arrayOfStandard = new Standard[i];
        for (int j = 0; j < i; j++)
            arrayOfStandard[j] = findById(paramArrayOfLong[j]);
        return arrayOfStandard;
    }

    @Autowired
    public void setDao(StandardDao paramStandardDao) {
        this._$1 = paramStandardDao;
    }
}

