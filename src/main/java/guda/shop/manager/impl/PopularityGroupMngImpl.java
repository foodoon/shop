package guda.shop.manager.impl;

import guda.shop.cms.dao.PopularityGroupDao;
iimport guda.shopcms.entity.PopularityGroup;
imimport guda.shopms.manager.PopularityGroupMng;
impimport guda.shops.manager.ProductMng;
impoimport guda.shopmon.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PopularityGroupMngImpl
  implements PopularityGroupMng
{
  private PopularityGroupDao _$2;

  @Autowired
  private ProductMng _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$2.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public PopularityGroup findById(Long paramLong)
  {
    PopularityGroup localPopularityGroup = null;
    if (paramLong != null)
      localPopularityGroup = this._$2.findById(paramLong);
    return localPopularityGroup;
  }

  public PopularityGroup save(PopularityGroup paramPopularityGroup)
  {
    this._$2.save(paramPopularityGroup);
    return paramPopularityGroup;
  }

  public PopularityGroup update(PopularityGroup paramPopularityGroup)
  {
    Updater localUpdater = new Updater(paramPopularityGroup);
    PopularityGroup localPopularityGroup = this._$2.updateByUpdater(localUpdater);
    return localPopularityGroup;
  }

  public PopularityGroup deleteById(Long paramLong)
  {
    PopularityGroup localPopularityGroup = findById(paramLong);
    localPopularityGroup.getProducts().clear();
    this._$2.deleteById(paramLong);
    return localPopularityGroup;
  }

  public PopularityGroup[] deleteByIds(Long[] paramArrayOfLong)
  {
    PopularityGroup[] arrayOfPopularityGroup = new PopularityGroup[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfPopularityGroup[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfPopularityGroup;
  }

  public void addProduct(PopularityGroup paramPopularityGroup, Long[] paramArrayOfLong)
  {
    if (paramArrayOfLong != null)
    {
      Long[] arrayOfLong = paramArrayOfLong;
      int i = arrayOfLong.length;
      for (int j = 0; j < i; j++)
      {
        long l = arrayOfLong[j].longValue();
        paramPopularityGroup.addToProducts(this._$1.findById(Long.valueOf(l)));
      }
    }
  }

  public void updateProduct(PopularityGroup paramPopularityGroup, Long[] paramArrayOfLong)
  {
    paramPopularityGroup.getProducts().clear();
    if (paramArrayOfLong != null)
    {
      Long[] arrayOfLong = paramArrayOfLong;
      int i = arrayOfLong.length;
      for (int j = 0; j < i; j++)
      {
        long l = arrayOfLong[j].longValue();
        paramPopularityGroup.addToProducts(this._$1.findById(Long.valueOf(l)));
      }
    }
  }

  @Autowired
  public void setDao(PopularityGroupDao paramPopularityGroupDao)
  {
    this._$2 = paramPopularityGroupDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.PopularityGroupMngImpl
 * JD-Core Version:    0.6.2
 */