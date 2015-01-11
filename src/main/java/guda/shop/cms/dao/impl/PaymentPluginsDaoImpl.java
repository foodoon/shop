package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.PaymentPluginsDao;
import guda.shop.cms.entity.PaymentPlugins;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentPluginsDaoImpl extends HibernateBaseDao<PaymentPlugins, Long>
        implements PaymentPluginsDao {
    public List<PaymentPlugins> getList() {
        Finder localFinder = Finder.create("from PaymentPlugins bean where 1=1");
        localFinder.append(" order by bean.priority");
        return find(localFinder);
    }

    public PaymentPlugins findById(Long paramLong) {
        PaymentPlugins localPaymentPlugins = (PaymentPlugins) get(paramLong);
        return localPaymentPlugins;
    }

    public PaymentPlugins findByCode(String paramString) {
        return (PaymentPlugins) findUniqueByProperty("code", paramString);
    }

    public PaymentPlugins save(PaymentPlugins paramPaymentPlugins) {
        getSession().save(paramPaymentPlugins);
        return paramPaymentPlugins;
    }

    public PaymentPlugins deleteById(Long paramLong) {
        PaymentPlugins localPaymentPlugins = (PaymentPlugins) super.get(paramLong);
        if (localPaymentPlugins != null)
            getSession().delete(localPaymentPlugins);
        return localPaymentPlugins;
    }

    protected Class<PaymentPlugins> getEntityClass() {
        return PaymentPlugins.class;
    }
}

