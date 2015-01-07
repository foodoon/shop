package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShopDictionaryDao;
iimport guda.shopcms.entity.ShopDictionary;
imimport guda.shopms.manager.ShopDictionaryMng;
impimport guda.shopmmon.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopDictionaryMngImpl
  implements ShopDictionaryMng
{
  private ShopDictionaryDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  public Pagination getPage(String paramString, Long paramLong, int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramString, paramLong, paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public ShopDictionary findById(Long paramLong)
  {
    ShopDictionary localShopDictionary = this._$1.findById(paramLong);
    return localShopDictionary;
  }

  public List<ShopDictionary> getListByType(Long paramLong)
  {
    return this._$1.getListByType(paramLong);
  }

  public ShopDictionary save(ShopDictionary paramShopDictionary)
  {
    this._$1.save(paramShopDictionary);
    return paramShopDictionary;
  }

  public ShopDictionary update(ShopDictionary paramShopDictionary)
  {
    Updater localUpdater = new Updater(paramShopDictionary);
    ShopDictionary localShopDictionary = this._$1.updateByUpdater(localUpdater);
    return localShopDictionary;
  }

  public ShopDictionary deleteById(Long paramLong)
  {
    ShopDictionary localShopDictionary = this._$1.deleteById(paramLong);
    return localShopDictionary;
  }

  public ShopDictionary[] deleteByIds(Long[] paramArrayOfLong)
  {
    ShopDictionary[] arrayOfShopDictionary = new ShopDictionary[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfShopDictionary[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfShopDictionary;
  }

  public ShopDictionary[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger)
  {
    int i = paramArrayOfLong.length;
    ShopDictionary[] arrayOfShopDictionary = new ShopDictionary[i];
    for (int j = 0; j < i; j++)
    {
      arrayOfShopDictionary[j] = findById(paramArrayOfLong[j]);
      arrayOfShopDictionary[j].setPriority(paramArrayOfInteger[j]);
    }
    return arrayOfShopDictionary;
  }

  @Autowired
  public void setDao(ShopDictionaryDao paramShopDictionaryDao)
  {
    this._$1 = paramShopDictionaryDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShopDictionaryMngImpl
 * JD-Core Version:    0.6.2
 */