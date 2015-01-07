package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.CollectDao;
iimport guda.shopcms.entity.Collect;
imimport guda.shopms.entity.ShopMember;
impimport guda.shops.manager.CollectMng;
impoimport guda.shop.manager.ProductFashionMng;
imporimport guda.shopmanager.ProductMng;
importimport guda.shopn.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CollectMngImpl
  implements CollectMng
{

  @Autowired
  private ProductMng _$3;

  @Autowired
  private ProductFashionMng _$2;

  @Autowired
  private CollectDao _$1;

  @Transactional(readOnly=true)
  public Pagination getList(Integer paramInteger1, Integer paramInteger2, Long paramLong)
  {
    Pagination localPagination = this._$1.getList(paramInteger1, paramInteger2, paramLong);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public Collect findById(Integer paramInteger)
  {
    Collect localCollect = this._$1.findById(paramInteger);
    return localCollect;
  }

  public Collect save(ShopMember paramShopMember, Long paramLong1, Long paramLong2)
  {
    Collect localCollect = new Collect();
    localCollect.setProduct(this._$3.findById(paramLong1));
    if (paramLong2 != null)
      localCollect.setFashion(this._$2.findById(paramLong2));
    localCollect.setMember(paramShopMember);
    localCollect.setTime(new Date());
    this._$1.save(localCollect);
    return localCollect;
  }

  public Collect update(Collect paramCollect, Long paramLong)
  {
    Updater localUpdater = new Updater(paramCollect);
    Collect localCollect = this._$1.updateByUpdater(localUpdater);
    return localCollect;
  }

  public List<Collect> findByProductId(Long paramLong)
  {
    return this._$1.findByProductId(paramLong);
  }

  public Collect findByProductFashionId(Long paramLong)
  {
    return this._$1.findByProductFashionId(paramLong);
  }

  public Collect deleteById(Integer paramInteger)
  {
    Collect localCollect = findById(paramInteger);
    localCollect = this._$1.deleteById(paramInteger);
    return localCollect;
  }

  public Collect[] deleteByIds(Integer[] paramArrayOfInteger)
  {
    Collect[] arrayOfCollect = new Collect[paramArrayOfInteger.length];
    int i = 0;
    int j = paramArrayOfInteger.length;
    while (i < j)
    {
      arrayOfCollect[i] = deleteById(paramArrayOfInteger[i]);
      i++;
    }
    return arrayOfCollect;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.CollectMngImpl
 * JD-Core Version:    0.6.2
 */