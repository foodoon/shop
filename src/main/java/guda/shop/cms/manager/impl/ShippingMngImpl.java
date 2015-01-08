package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShippingDao;
import guda.shop.cms.entity.Shipping;
import guda.shop.cms.manager.ShippingMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class ShippingMngImpl
        implements ShippingMng {
    private ShippingDao _$1;

    @Transactional(readOnly = true)
    public List<Shipping> getList(Long paramLong, boolean paramBoolean) {
        return this._$1.getList(paramLong, paramBoolean, false);
    }

    @Transactional(readOnly = true)
    public List<Shipping> getListForCart(Long paramLong1, Long paramLong2, int paramInt1, int paramInt2) {
        List localList = this._$1.getList(paramLong1, false, true);
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext()) {
            Shipping localShipping = (Shipping) localIterator.next();
        }
        return localList;
    }

    @Transactional(readOnly = true)
    public Shipping findById(Long paramLong) {
        Shipping localShipping = this._$1.findById(paramLong);
        return localShipping;
    }

    public Shipping save(Shipping paramShipping) {
        this._$1.save(paramShipping);
        return paramShipping;
    }

    public Shipping update(Shipping paramShipping) {
        Shipping localShipping = findById(paramShipping.getId());
        Updater localUpdater = new Updater(paramShipping);
        localShipping = this._$1.updateByUpdater(localUpdater);
        return localShipping;
    }

    public Shipping deleteById(Long paramLong) {
        Shipping localShipping = this._$1.deleteById(paramLong);
        return localShipping;
    }

    public Shipping[] deleteByIds(Long[] paramArrayOfLong) {
        Shipping[] arrayOfShipping = new Shipping[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfShipping[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfShipping;
    }

    public Shipping[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger) {
        Shipping[] arrayOfShipping = new Shipping[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfShipping[i] = findById(paramArrayOfLong[i]);
            arrayOfShipping[i].setPriority(paramArrayOfInteger[i]);
            i++;
        }
        return arrayOfShipping;
    }

    @Autowired
    public void setDao(ShippingDao paramShippingDao) {
        this._$1 = paramShippingDao;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShippingMngImpl
 * JD-Core Version:    0.6.2
 */