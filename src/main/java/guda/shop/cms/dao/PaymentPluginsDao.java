package guda.shop.cms.dao;

import guda.shop.cms.entity.PaymentPlugins;
import guda.shop.common.hibernate3.Updater;
import java.util.List;

public abstract interface PaymentPluginsDao
{
  public abstract List<PaymentPlugins> getList();

  public abstract PaymentPlugins findByCode(String paramString);

  public abstract PaymentPlugins findById(Long paramLong);

  public abstract PaymentPlugins save(PaymentPlugins paramPaymentPlugins);

  public abstract PaymentPlugins updateByUpdater(Updater<PaymentPlugins> paramUpdater);

  public abstract PaymentPlugins deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.PaymentPluginsDao
 * JD-Core Version:    0.6.2
 */