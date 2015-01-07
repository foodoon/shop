package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ProductTypePropertyDao;
iimport guda.shopcms.entity.ProductTypeProperty;
imimport guda.shopms.manager.ProductTypePropertyMng;
impimport guda.shopmmon.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductTypePropertyMngImpl
  implements ProductTypePropertyMng
{

  @Autowired
  private ProductTypePropertyDao _$1;

  public ProductTypeProperty deleteById(Long paramLong)
  {
    return this._$1.deleteById(paramLong);
  }

  public ProductTypeProperty[] deleteByIds(Long[] paramArrayOfLong)
  {
    ProductTypeProperty[] arrayOfProductTypeProperty = new ProductTypeProperty[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfProductTypeProperty[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfProductTypeProperty;
  }

  public ProductTypeProperty findById(Long paramLong)
  {
    return this._$1.findById(paramLong);
  }

  public Pagination getList(int paramInt1, int paramInt2, Long paramLong, Boolean paramBoolean, String paramString1, String paramString2)
  {
    return this._$1.getList(paramInt1, paramInt2, paramLong, paramBoolean, paramString1, paramString2);
  }

  public List<ProductTypeProperty> getList(Long paramLong, Boolean paramBoolean)
  {
    return this._$1.getList(paramLong, paramBoolean);
  }

  public void saveList(List<ProductTypeProperty> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ProductTypeProperty localProductTypeProperty = (ProductTypeProperty)localIterator.next();
      save(localProductTypeProperty);
    }
  }

  public ProductTypeProperty save(ProductTypeProperty paramProductTypeProperty)
  {
    return this._$1.save(paramProductTypeProperty);
  }

  public void updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger, String[] paramArrayOfString, Boolean[] paramArrayOfBoolean)
  {
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      ProductTypeProperty localProductTypeProperty = findById(paramArrayOfLong[i]);
      localProductTypeProperty.setPropertyName(paramArrayOfString[i]);
      localProductTypeProperty.setSort(paramArrayOfInteger[i]);
      localProductTypeProperty.setSingle(paramArrayOfBoolean[i]);
      i++;
    }
  }

  public List<ProductTypeProperty> findBySearch(String paramString1, String paramString2)
  {
    return this._$1.findBySearch(paramString1, paramString2);
  }

  public List<ProductTypeProperty> findListByTypeId(Long paramLong)
  {
    return this._$1.findListByTypeId(paramLong);
  }

  public List<ProductTypeProperty> getList(Long paramLong, boolean paramBoolean)
  {
    return this._$1.getList(paramLong, paramBoolean);
  }

  public ProductTypeProperty update(ProductTypeProperty paramProductTypeProperty)
  {
    Updater localUpdater = new Updater(paramProductTypeProperty);
    ProductTypeProperty localProductTypeProperty = this._$1.updateByUpdater(localUpdater);
    return localProductTypeProperty;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ProductTypePropertyMngImpl
 * JD-Core Version:    0.6.2
 */