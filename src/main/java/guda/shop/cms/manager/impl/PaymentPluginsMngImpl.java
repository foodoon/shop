package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.PaymentPluginsDao;
import guda.shop.cms.entity.PaymentPlugins;
import guda.shop.cms.manager.PaymentPluginsMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentPluginsMngImpl
        implements PaymentPluginsMng {
    private PaymentPluginsDao _$1;

    @Transactional(readOnly = true)
    public List<PaymentPlugins> getList() {
        return this._$1.getList();
    }

    public PaymentPlugins[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger) {
        PaymentPlugins[] arrayOfPaymentPlugins = new PaymentPlugins[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfPaymentPlugins[i] = findById(paramArrayOfLong[i]);
            arrayOfPaymentPlugins[i].setPriority(paramArrayOfInteger[i]);
            i++;
        }
        return arrayOfPaymentPlugins;
    }

    public PaymentPlugins findByCode(String paramString) {
        return this._$1.findByCode(paramString);
    }

    @Transactional(readOnly = true)
    public PaymentPlugins findById(Long paramLong) {
        PaymentPlugins localPaymentPlugins = this._$1.findById(paramLong);
        return localPaymentPlugins;
    }

    public PaymentPlugins save(PaymentPlugins paramPaymentPlugins) {
        this._$1.save(paramPaymentPlugins);
        return paramPaymentPlugins;
    }

    public PaymentPlugins update(PaymentPlugins paramPaymentPlugins) {
        Updater localUpdater = new Updater(paramPaymentPlugins);
        PaymentPlugins localPaymentPlugins = this._$1.updateByUpdater(localUpdater);
        return localPaymentPlugins;
    }

    public PaymentPlugins deleteById(Long paramLong) {
        PaymentPlugins localPaymentPlugins = this._$1.deleteById(paramLong);
        return localPaymentPlugins;
    }

    public PaymentPlugins[] deleteByIds(Long[] paramArrayOfLong) {
        PaymentPlugins[] arrayOfPaymentPlugins = new PaymentPlugins[paramArrayOfLong.length];
        int i = 0;
        int j = paramArrayOfLong.length;
        while (i < j) {
            arrayOfPaymentPlugins[i] = deleteById(paramArrayOfLong[i]);
            i++;
        }
        return arrayOfPaymentPlugins;
    }

    @Autowired
    public void setDao(PaymentPluginsDao paramPaymentPluginsDao) {
        this._$1 = paramPaymentPluginsDao;
    }
}

