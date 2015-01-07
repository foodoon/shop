package guda.shop.manager.impl;

import guda.shop.cms.dao.PopularityItemDao;
iimport guda.shopcms.entity.Cart;
imimport guda.shopms.entity.PopularityItem;
impimport guda.shops.manager.PopularityGroupMng;
impoimport guda.shop.manager.PopularityItemMng;
imporimport guda.shopon.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PopularityItemMngImpl
  implements PopularityItemMng
{
  private PopularityItemDao _$2;

  @Autowired
  private PopularityGroupMng _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$2.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  public List<PopularityItem> getlist(Long paramLong1, Long paramLong2)
  {
    return this._$2.getlist(paramLong1, paramLong2);
  }

  public PopularityItem findById(Long paramLong1, Long paramLong2)
  {
    return this._$2.findById(paramLong1, paramLong2);
  }

  @Transactional(readOnly=true)
  public PopularityItem findById(Long paramLong)
  {
    PopularityItem localPopularityItem = this._$2.findById(paramLong);
    return localPopularityItem;
  }

  public PopularityItem save(PopularityItem paramPopularityItem)
  {
    this._$2.save(paramPopularityItem);
    return paramPopularityItem;
  }

  public PopularityItem update(PopularityItem paramPopularityItem)
  {
    Updater localUpdater = new Updater(paramPopularityItem);
    PopularityItem localPopularityItem = this._$2.updateByUpdater(localUpdater);
    return localPopularityItem;
  }

  public PopularityItem deleteById(Long paramLong)
  {
    PopularityItem localPopularityItem = this._$2.deleteById(paramLong);
    return localPopularityItem;
  }

  public PopularityItem[] deleteByIds(Long[] paramArrayOfLong)
  {
    PopularityItem[] arrayOfPopularityItem = new PopularityItem[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfPopularityItem[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfPopularityItem;
  }

  public void save(Cart paramCart, Long paramLong)
  {
    if (paramLong != null)
    {
      PopularityItem localPopularityItem = findById(paramCart.getId(), paramLong);
      if (localPopularityItem != null)
      {
        localPopularityItem.setCount(Integer.valueOf(localPopularityItem.getCount().intValue() + 1));
        update(localPopularityItem);
      }
      else
      {
        localPopularityItem = new PopularityItem();
        localPopularityItem.setCart(paramCart);
        localPopularityItem.setPopularityGroup(this._$1.findById(paramLong));
        localPopularityItem.setCount(Integer.valueOf(1));
        save(localPopularityItem);
      }
    }
  }

  @Autowired
  public void setDao(PopularityItemDao paramPopularityItemDao)
  {
    this._$2 = paramPopularityItemDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.PopularityItemMngImpl
 * JD-Core Version:    0.6.2
 */