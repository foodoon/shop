package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopPayDao;
import guda.shop.cms.entity.ShopPay;
import guda.shop.cms.manager.ShopPayMng;
import guda.shop.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopPayMngImpl
  implements ShopPayMng
{

  @Autowired
  private ShopPayDao _$1;

  public ShopPay deleteById(Integer paramInteger)
  {
    return this._$1.deleteById(paramInteger);
  }

  public ShopPay[] deleteByIds(Integer[] paramArrayOfInteger)
  {
    ShopPay[] arrayOfShopPay = new ShopPay[paramArrayOfInteger.length];
    int i = 0;
    int j = paramArrayOfInteger.length;
    while (i < j)
    {
      arrayOfShopPay[i] = deleteById(paramArrayOfInteger[i]);
      i++;
    }
    return arrayOfShopPay;
  }

  public ShopPay findById(Integer paramInteger)
  {
    return this._$1.findById(paramInteger);
  }

  public ShopPay save(ShopPay paramShopPay)
  {
    return this._$1.save(paramShopPay);
  }

  public ShopPay updateByUpdater(ShopPay paramShopPay)
  {
    Updater localUpdater = new Updater(paramShopPay);
    return this._$1.updateByUpdater(localUpdater);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopPayMngImpl
 * JD-Core Version:    0.6.2
 */