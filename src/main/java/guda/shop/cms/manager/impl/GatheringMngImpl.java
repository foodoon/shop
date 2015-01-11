package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.GatheringDao;
import guda.shop.cms.entity.Gathering;
import guda.shop.cms.manager.GatheringMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GatheringMngImpl
        implements GatheringMng {
    private GatheringDao _$1;

    @Transactional(readOnly = true)
    public Pagination getPage(int paramInt1, int paramInt2) {
        Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
        return localPagination;
    }

    @Transactional(readOnly = true)
    public Gathering findById(Long paramLong) {
        Gathering localGathering = this._$1.findById(paramLong);
        return localGathering;
    }

    public List<Gathering> getlist(Long paramLong) {
        return this._$1.getlist(paramLong);
    }

    public void deleteByorderId(Long paramLong) {
        List localList = getlist(paramLong);
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext()) {
            Gathering localGathering = (Gathering) localIterator.next();
            deleteById(localGathering.getId());
        }
    }

    public Gathering save(Gathering paramGathering) {
        this._$1.save(paramGathering);
        return paramGathering;
    }

    public Gathering update(Gathering paramGathering) {
        Updater localUpdater = new Updater(paramGathering);
        Gathering localGathering = this._$1.updateByUpdater(localUpdater);
        return localGathering;
    }

    public Gathering deleteById(Long paramLong) {
        Gathering localGathering = this._$1.deleteById(paramLong);
        return localGathering;
    }

    public Gathering[] deleteByIds(Long[] paramArrayOfLong) {
        Gathering[] arrayOfGathering = new Gathering[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfGathering[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfGathering;
    }

    @Autowired
    public void setDao(GatheringDao paramGatheringDao) {
        this._$1 = paramGatheringDao;
    }
}

