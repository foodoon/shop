package guda.shop.dao;

import guda.shop.cms.entity.Payment;
import guda.shop.common.hibernate3.Updater;
import java.util.List;

public abstract interface PaymentDao
{
  public abstract List<Payment> getListForCart(Long paramLong, boolean paramBoolean);

  public abstract List<Payment> getList(Long paramLong, boolean paramBoolean);

  public abstract List<Payment> getByCode(String paramString, Long paramLong);

  public abstract Payment findById(Long paramLong);

  public abstract Payment save(Payment paramPayment);

  public abstract Payment updateByUpdater(Updater<Payment> paramUpdater);

  public abstract Payment deleteById(Long paramLong);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.PaymentDao
 * JD-Core Version:    0.6.2
 */