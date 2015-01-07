package guda.shop.manager.impl;

import guda.shop.cms.dao.BrandTextDao;
iimport guda.shopcms.entity.Brand;
imimport guda.shopms.entity.BrandText;
impimport guda.shops.manager.BrandTextMng;
import com.jspgou.common.hibernate3.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BrandTextMngImpl
  implements BrandTextMng
{
  private BrandTextDao _$1;

  public BrandText save(Brand paramBrand, String paramString)
  {
    BrandText localBrandText = new BrandText();
    localBrandText.setBrand(paramBrand);
    localBrandText.setText(paramString);
    this._$1.save(localBrandText);
    return localBrandText;
  }

  public BrandText update(BrandText paramBrandText)
  {
    BrandText localBrandText = this._$1.updateByUpdater(new Updater(paramBrandText));
    return localBrandText;
  }

  @Autowired
  public void setDao(BrandTextDao paramBrandTextDao)
  {
    this._$1 = paramBrandTextDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.BrandTextMngImpl
 * JD-Core Version:    0.6.2
 */