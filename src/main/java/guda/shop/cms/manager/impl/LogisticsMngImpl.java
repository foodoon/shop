package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.LogisticsDao;
import guda.shop.cms.entity.Logistics;
import guda.shop.cms.manager.LogisticsMng;
import guda.shop.cms.manager.LogisticsTextMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogisticsMngImpl
        implements LogisticsMng {
    private LogisticsTextMng _$2;
    private LogisticsDao _$1;

    @Transactional(readOnly = true)
    public List<Logistics> getAllList() {
        List localList = this._$1.getAllList();
        return localList;
    }

    @Transactional(readOnly = true)
    public Logistics findById(Long paramLong) {
        Logistics localLogistics = this._$1.findById(paramLong);
        return localLogistics;
    }

    public Logistics save(Logistics paramLogistics, String paramString) {
        Logistics localLogistics = this._$1.save(paramLogistics);
        this._$2.save(localLogistics, paramString);
        return localLogistics;
    }

    public Logistics update(Logistics paramLogistics, String paramString) {
        Updater localUpdater = new Updater(paramLogistics);
        Logistics localLogistics = this._$1.updateByUpdater(localUpdater);
        localLogistics.getLogisticsText().setText(paramString);
        return localLogistics;
    }

    public Logistics deleteById(Long paramLong) {
        return this._$1.deleteById(paramLong);
    }

    public Logistics[] deleteByIds(Long[] paramArrayOfLong) {
        Logistics[] arrayOfLogistics = new Logistics[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfLogistics[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfLogistics;
    }

    public Logistics[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger) {
        Logistics[] arrayOfLogistics = new Logistics[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfLogistics[i] = findById(paramArrayOfLong[i]);
            arrayOfLogistics[i].setPriority(paramArrayOfInteger[i]);
            i++;
        }
        return arrayOfLogistics;
    }

    @Autowired
    public void setLogisticsTextMng(LogisticsTextMng paramLogisticsTextMng) {
        this._$2 = paramLogisticsTextMng;
    }

    @Autowired
    public void setDao(LogisticsDao paramLogisticsDao) {
        this._$1 = paramLogisticsDao;
    }
}

