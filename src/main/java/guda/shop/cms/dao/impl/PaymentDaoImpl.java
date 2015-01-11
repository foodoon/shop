package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.PaymentDao;
import guda.shop.cms.entity.Payment;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDaoImpl extends HibernateBaseDao<Payment, Long>
        implements PaymentDao {
    public List<Payment> getListForCart(Long paramLong, boolean paramBoolean) {
        String str = "from Payment bean where bean.website.id=:webId and bean.disabled=false order by bean.priority";
        return getSession().createQuery(str).setParameter("webId", paramLong).setCacheable(paramBoolean).list();
    }

    public List<Payment> getList(Long paramLong, boolean paramBoolean) {
        Finder localFinder = Finder.create("from Payment bean where bean.website.id=:webId");
        localFinder.setParam("webId", paramLong);
        if (!paramBoolean)
            localFinder.append(" bean.disabled=false order by bean.priority");
        else
            localFinder.append(" order by bean.disabled,bean.priority");
        return find(localFinder);
    }

    public List<Payment> getByCode(String paramString, Long paramLong) {
        String str = "from Payment bean where bean.website.id=? and bean.code=?";
        return find(str, new Object[]{paramLong, paramString});
    }

    public Payment findById(Long paramLong) {
        Payment localPayment = (Payment) get(paramLong);
        return localPayment;
    }

    public Payment save(Payment paramPayment) {
        getSession().save(paramPayment);
        return paramPayment;
    }

    public Payment deleteById(Long paramLong) {
        Payment localPayment = (Payment) super.get(paramLong);
        if (localPayment != null)
            getSession().delete(localPayment);
        return localPayment;
    }

    protected Class<Payment> getEntityClass() {
        return Payment.class;
    }
}

