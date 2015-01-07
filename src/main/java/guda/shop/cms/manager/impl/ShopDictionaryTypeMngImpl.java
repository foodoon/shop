package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopDictionaryTypeDao;
iimport guda.shopcms.entity.ShopDictionaryType;
imimport guda.shopms.manager.ShopDictionaryTypeMng;
impimport guda.shopmmon.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopDictionaryTypeMngImpl
  implements ShopDictionaryTypeMng
{
  private ShopDictionaryTypeDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public ShopDictionaryType findById(Long paramLong)
  {
    ShopDictionaryType localShopDictionaryType = this._$1.findById(paramLong);
    return localShopDictionaryType;
  }

  public List<ShopDictionaryType> findAll()
  {
    return this._$1.findAll();
  }

  public ShopDictionaryType save(ShopDictionaryType paramShopDictionaryType)
  {
    this._$1.save(paramShopDictionaryType);
    return paramShopDictionaryType;
  }

  public ShopDictionaryType update(ShopDictionaryType paramShopDictionaryType)
  {
    Updater localUpdater = new Updater(paramShopDictionaryType);
    ShopDictionaryType localShopDictionaryType = this._$1.updateByUpdater(localUpdater);
    return localShopDictionaryType;
  }

  public ShopDictionaryType deleteById(Long paramLong)
  {
    ShopDictionaryType localShopDictionaryType = this._$1.deleteById(paramLong);
    return localShopDictionaryType;
  }

  public ShopDictionaryType[] deleteByIds(Long[] paramArrayOfLong)
  {
    ShopDictionaryType[] arrayOfShopDictionaryType = new ShopDictionaryType[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfShopDictionaryType[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfShopDictionaryType;
  }

  @Autowired
  public void setDao(ShopDictionaryTypeDao paramShopDictionaryTypeDao)
  {
    this._$1 = paramShopDictionaryTypeDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopDictionaryTypeMngImpl
 * JD-Core Version:    0.6.2
 */