package guda.shop.manager.impl;

import guda.shop.cms.dao.ProductTypeDao;
iimport guda.shopcms.entity.ProductType;
imimport guda.shopms.manager.BrandMng;
impimport guda.shops.manager.ProductTypeMng;
import com.jspgou.common.hibernate3.Updater;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductTypeMngImpl
  implements ProductTypeMng
{
  private BrandMng _$2;
  private ProductTypeDao _$1;

  @Transactional(readOnly=true)
  public List<ProductType> getList(Long paramLong)
  {
    return this._$1.getList(paramLong);
  }

  @Transactional(readOnly=true)
  public ProductType findById(Long paramLong)
  {
    ProductType localProductType = this._$1.findById(paramLong);
    return localProductType;
  }

  public ProductType save(ProductType paramProductType)
  {
    this._$1.save(paramProductType);
    return paramProductType;
  }

  public ProductType update(ProductType paramProductType)
  {
    ProductType localProductType = this._$1.updateByUpdater(new Updater(paramProductType));
    return localProductType;
  }

  public ProductType deleteById(Long paramLong)
  {
    ProductType localProductType = this._$1.deleteById(paramLong);
    return localProductType;
  }

  public ProductType[] deleteByIds(Long[] paramArrayOfLong)
  {
    ProductType[] arrayOfProductType = new ProductType[paramArrayOfLong.length];
    for (int i = 0; i < paramArrayOfLong.length; i++)
      arrayOfProductType[i] = deleteById(paramArrayOfLong[i]);
    return arrayOfProductType;
  }

  @Autowired
  public void setBrandMng(BrandMng paramBrandMng)
  {
    this._$2 = paramBrandMng;
  }

  @Autowired
  public void setDao(ProductTypeDao paramProductTypeDao)
  {
    this._$1 = paramProductTypeDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ProductTypeMngImpl
 * JD-Core Version:    0.6.2
 */