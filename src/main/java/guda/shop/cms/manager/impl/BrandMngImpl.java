package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.BrandDao;
iimport guda.shopcms.entity.Brand;
imimport guda.shopms.entity.BrandText;
impimport guda.shops.manager.BrandMng;
impoimport guda.shop.manager.BrandTextMng;
imporimport guda.shopmanager.ProductTypeMng;
import guda.shop.common.hibernate3.Updater;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BrandMngImpl
  implements BrandMng
{
  private ProductTypeMng _$3;
  private BrandTextMng _$2;
  private BrandDao _$1;

  @Transactional(readOnly=true)
  public List<Brand> getAllList()
  {
    List localList = this._$1.getAllList();
    return localList;
  }

  public List<Brand> getList()
  {
    return this._$1.getList();
  }

  public List<Brand> getListForTag(Long paramLong, int paramInt1, int paramInt2)
  {
    return this._$1.getList(paramLong, paramInt1, paramInt2, true);
  }

  public Brand getsiftBrand()
  {
    return this._$1.getsiftBrand();
  }

  @Transactional(readOnly=true)
  public Brand findById(Long paramLong)
  {
    Brand localBrand = this._$1.findById(paramLong);
    return localBrand;
  }

  public Brand save(Brand paramBrand, String paramString)
  {
    Brand localBrand = this._$1.save(paramBrand);
    this._$2.save(localBrand, paramString);
    return localBrand;
  }

  public Brand update(Brand paramBrand, String paramString)
  {
    Updater localUpdater = new Updater(paramBrand);
    Brand localBrand = this._$1.updateByUpdater(localUpdater);
    localBrand.getBrandText().setText(paramString);
    return localBrand;
  }

  public Brand deleteById(Long paramLong)
  {
    Brand localBrand = findById(paramLong);
    localBrand = this._$1.deleteById(paramLong);
    return localBrand;
  }

  public Brand[] deleteByIds(Long[] paramArrayOfLong)
  {
    Brand[] arrayOfBrand = new Brand[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfBrand[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfBrand;
  }

  public Brand[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger)
  {
    Brand[] arrayOfBrand = new Brand[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfBrand[i] = findById(paramArrayOfLong[i]);
      arrayOfBrand[i].setPriority(paramArrayOfInteger[i]);
      i++;
    }
    return arrayOfBrand;
  }

  public boolean brandNameNotExist(String paramString)
  {
    return this._$1.countByBrandName(paramString) <= 0;
  }

  @Autowired
  public void setProductTypeMng(ProductTypeMng paramProductTypeMng)
  {
    this._$3 = paramProductTypeMng;
  }

  @Autowired
  public void setBrandTextMng(BrandTextMng paramBrandTextMng)
  {
    this._$2 = paramBrandTextMng;
  }

  @Autowired
  public void setDao(BrandDao paramBrandDao)
  {
    this._$1 = paramBrandDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.BrandMngImpl
 * JD-Core Version:    0.6.2
 */