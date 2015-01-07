package guda.shop.cms.manager;

import guda.shop.cms.entity.PaymentPlugins;
import java.util.List;

public abstract interface PaymentPluginsMng
{
  public abstract PaymentPlugins[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger);

  public abstract List<PaymentPlugins> getList();

  public abstract PaymentPlugins findById(Long paramLong);

  public abstract PaymentPlugins findByCode(String paramString);

  public abstract PaymentPlugins save(PaymentPlugins paramPaymentPlugins);

  public abstract PaymentPlugins update(PaymentPlugins paramPaymentPlugins);

  public abstract PaymentPlugins deleteById(Long paramLong);

  public abstract PaymentPlugins[] deleteByIds(Long[] paramArrayOfLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.PaymentPluginsMng
 * JD-Core Version:    0.6.2
 */