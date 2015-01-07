package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ProductExtDao;
iimport guda.shopcms.entity.Product;
imimport guda.shopms.entity.ProductExt;
impimport guda.shops.manager.ProductExtMng;
import guda.shop.common.hibernate3.Updater;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductExtMngImpl
  implements ProductExtMng
{
  private ProductExtDao _$1;

  public ProductExt save(ProductExt paramProductExt, Product paramProduct)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    long l = Long.parseLong(localSimpleDateFormat.format(new Date())) + paramProduct.getId().longValue();
    paramProductExt.setCode(Long.valueOf(l));
    paramProductExt.setProduct(paramProduct);
    paramProductExt.init();
    this._$1.save(paramProductExt);
    return paramProductExt;
  }

  public ProductExt saveOrUpdate(ProductExt paramProductExt, Product paramProduct)
  {
    ProductExt localProductExt = this._$1.findById(paramProductExt.getId());
    if (localProductExt != null)
    {
      Updater localUpdater = new Updater(paramProductExt);
      this._$1.updateByUpdater(localUpdater);
      return localProductExt;
    }
    return save(paramProductExt, paramProduct);
  }

  @Autowired
  public void setDao(ProductExtDao paramProductExtDao)
  {
    this._$1 = paramProductExtDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ProductExtMngImpl
 * JD-Core Version:    0.6.2
 */