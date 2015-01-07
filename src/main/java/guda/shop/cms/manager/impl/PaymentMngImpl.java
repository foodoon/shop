package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.PaymentDao;
iimport guda.shopcms.entity.Payment;
imimport guda.shopms.manager.PaymentMng;
impimport guda.shops.manager.ShippingMng;
import com.jspgou.common.hibernate3.Updater;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentMngImpl
  implements PaymentMng
{
  private PaymentDao _$2;

  @Autowired
  private ShippingMng _$1;

  public List<Payment> getListForCart(Long paramLong)
  {
    return this._$2.getListForCart(paramLong, true);
  }

  @Transactional(readOnly=true)
  public List<Payment> getList(Long paramLong, boolean paramBoolean)
  {
    return this._$2.getList(paramLong, paramBoolean);
  }

  public Payment[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger)
  {
    Payment[] arrayOfPayment = new Payment[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfPayment[i] = findById(paramArrayOfLong[i]);
      arrayOfPayment[i].setPriority(paramArrayOfInteger[i]);
      i++;
    }
    return arrayOfPayment;
  }

  @Transactional(readOnly=true)
  public List<Payment> getByCode(String paramString, Long paramLong)
  {
    return this._$2.getByCode(paramString, paramLong);
  }

  @Transactional(readOnly=true)
  public Payment findById(Long paramLong)
  {
    Payment localPayment = this._$2.findById(paramLong);
    return localPayment;
  }

  public Payment save(Payment paramPayment)
  {
    this._$2.save(paramPayment);
    return paramPayment;
  }

  public Payment update(Payment paramPayment)
  {
    Updater localUpdater = new Updater(paramPayment);
    Payment localPayment = this._$2.updateByUpdater(localUpdater);
    return localPayment;
  }

  public Payment deleteById(Long paramLong)
  {
    Payment localPayment = this._$2.deleteById(paramLong);
    return localPayment;
  }

  public Payment[] deleteByIds(Long[] paramArrayOfLong)
  {
    Payment[] arrayOfPayment = new Payment[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfPayment[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfPayment;
  }

  public void addShipping(Payment paramPayment, long[] paramArrayOfLong)
  {
    if (paramArrayOfLong != null)
      for (long l : paramArrayOfLong)
        paramPayment.addToShippings(this._$1.findById(Long.valueOf(l)));
  }

  public void updateShipping(Payment paramPayment, long[] paramArrayOfLong)
  {
    paramPayment.getShippings().clear();
    if (paramArrayOfLong != null)
      for (long l : paramArrayOfLong)
        paramPayment.addToShippings(this._$1.findById(Long.valueOf(l)));
  }

  @Autowired
  public void setDao(PaymentDao paramPaymentDao)
  {
    this._$2 = paramPaymentDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.PaymentMngImpl
 * JD-Core Version:    0.6.2
 */