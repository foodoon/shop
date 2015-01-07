package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopMoneyDao;
iimport guda.shop.ms.entity.ShopMoney;
imimport guda.shop.s.manager.ShopMoneyMng;
impimport guda.shop.mon.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopMoneyMngImpl
  implements ShopMoneyMng
{
  private ShopMoneyDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  public Pagination getPage(Long paramLong, Boolean paramBoolean, Date paramDate1, Date paramDate2, Integer paramInteger1, Integer paramInteger2)
  {
    Pagination localPagination = this._$1.getPage(paramLong, paramBoolean, paramDate1, paramDate2, paramInteger2, paramInteger1);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public ShopMoney findById(Long paramLong)
  {
    ShopMoney localShopMoney = this._$1.findById(paramLong);
    return localShopMoney;
  }

  public ShopMoney save(ShopMoney paramShopMoney)
  {
    this._$1.save(paramShopMoney);
    return paramShopMoney;
  }

  public ShopMoney update(ShopMoney paramShopMoney)
  {
    Updater localUpdater = new Updater(paramShopMoney);
    ShopMoney localShopMoney = this._$1.updateByUpdater(localUpdater);
    return localShopMoney;
  }

  public ShopMoney deleteById(Long paramLong)
  {
    ShopMoney localShopMoney = this._$1.deleteById(paramLong);
    return localShopMoney;
  }

  public ShopMoney[] deleteByIds(Long[] paramArrayOfLong)
  {
    ShopMoney[] arrayOfShopMoney = new ShopMoney[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfShopMoney[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfShopMoney;
  }

  @Autowired
  public void setDao(ShopMoneyDao paramShopMoneyDao)
  {
    this._$1 = paramShopMoneyDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopMoneyMngImpl
 * JD-Core Version:    0.6.2
 */