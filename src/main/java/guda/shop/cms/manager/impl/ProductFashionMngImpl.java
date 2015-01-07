package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ProductFashionDao;

import guda.shop.cms.entity.ProductFashion;
import guda.shop.cms.manager.CategoryMng;
import guda.shop.cms.manager.ProductFashionMng;
import guda.shop.cms.manager.StandardMng;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.image.ImageScale;
import guda.shop.common.page.Pagination;
import guda.shop.common.web.springmvc.RealPathResolver;
import guda.shop.core.manager.WebsiteMng;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductFashionMngImpl
  implements ProductFashionMng
{

  @Autowired
  private ProductFashionDao _$6;

  @Autowired
  private WebsiteMng _$5;

  @Autowired
  private RealPathResolver _$4;

  @Autowired
  private CategoryMng _$3;

  @Autowired
  private ImageScale _$2;

  @Autowired
  private StandardMng _$1;

  public ProductFashion deleteById(Long paramLong)
  {
    return this._$6.deleteById(paramLong);
  }

  public ProductFashion[] deleteByIds(Long[] paramArrayOfLong)
  {
    ProductFashion[] arrayOfProductFashion = new ProductFashion[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfProductFashion[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfProductFashion;
  }

  public ProductFashion findById(Long paramLong)
  {
    return this._$6.findById(paramLong);
  }

  public List<ProductFashion> findByProductId(Long paramLong)
  {
    return this._$6.findByProductId(paramLong);
  }

  public Pagination getPage(Long paramLong, int paramInt1, int paramInt2)
  {
    return this._$6.getPage(paramLong, paramInt1, paramInt2);
  }

  public ProductFashion save(ProductFashion paramProductFashion, String[] paramArrayOfString)
  {
    String str1 = "";
    for (String str2 : paramArrayOfString)
      str1 = str1 + " " + this._$1.findById(Long.valueOf(Long.parseLong(str2))).getName();
    paramProductFashion.setAttitude(str1);
    paramProductFashion.init();
    return this._$6.save(paramProductFashion);
  }

  public void addStandard(ProductFashion paramProductFashion, String[] paramArrayOfString)
  {
    for (String str : paramArrayOfString)
      paramProductFashion.addTostandards(this._$1.findById(Long.valueOf(Long.parseLong(str))));
  }

  public void updateStandard(ProductFashion paramProductFashion, String[] paramArrayOfString)
  {
    paramProductFashion.getStandards().clear();
    for (String str : paramArrayOfString)
      paramProductFashion.addTostandards(this._$1.findById(Long.valueOf(Long.parseLong(str))));
  }

  public void deleteImage(ProductFashion paramProductFashion, String paramString)
  {
  }

  public ProductFashion update(ProductFashion paramProductFashion, String[] paramArrayOfString)
  {
    String str1 = "";
    for (String str2 : paramArrayOfString)
      str1 = str1 + " " + this._$1.findById(Long.valueOf(Long.parseLong(str2))).getName();
    paramProductFashion.setAttitude(str1);
      Updater updater = new Updater(paramProductFashion);
    ProductFashion localProductFashion = this._$6.updateByUpdater(updater);
    return localProductFashion;
  }

  public ProductFashion update(ProductFashion paramProductFashion)
  {
    Updater localUpdater = new Updater(paramProductFashion);
    ProductFashion localProductFashion = this._$6.updateByUpdater(localUpdater);
    return localProductFashion;
  }

  public Pagination productLack(Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2)
  {
    return this._$6.productLack(paramInteger1, paramInteger2, paramInt1, paramInt2);
  }

  public Integer productLackCount(Integer paramInteger1, Integer paramInteger2)
  {
    return this._$6.productLackCount(paramInteger1, paramInteger2);
  }

  public Pagination getSaleTopPage(int paramInt1, int paramInt2)
  {
    return this._$6.getSaleTopPage(paramInt1, paramInt2);
  }

  public Boolean getOneFash(Long paramLong)
  {
    return this._$6.getOneFashion(paramLong);
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ProductFashionMngImpl
 * JD-Core Version:    0.6.2
 */