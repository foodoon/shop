package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ExendedItemDao;
iimport guda.shopcms.entity.Exended;
imimport guda.shopms.entity.ExendedItem;
impimport guda.shops.manager.ExendedItemMng;
impoimport guda.shopmon.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExendedItemMngImpl
  implements ExendedItemMng
{
  private ExendedItemDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  @Transactional(readOnly=true)
  public ExendedItem findById(Long paramLong)
  {
    ExendedItem localExendedItem = this._$1.findById(paramLong);
    return localExendedItem;
  }

  public Collection<ExendedItem> save(Collection<ExendedItem> paramCollection, Exended paramExended)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      ExendedItem localExendedItem = (ExendedItem)localIterator.next();
      localExendedItem.setExended(paramExended);
      this._$1.save(localExendedItem);
    }
    return paramCollection;
  }

  public Collection<ExendedItem> update(Collection<ExendedItem> paramCollection, Exended paramExended)
  {
    Object localObject1 = paramExended.getItems();
    if (localObject1 == null)
      localObject1 = new HashSet();
    HashSet localHashSet = new HashSet();
    Object localObject2 = ((Set)localObject1).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (ExendedItem)((Iterator)localObject2).next();
      if (!paramCollection.contains(localObject3))
        localHashSet.add(localObject3);
    }
    ((Set)localObject1).removeAll(localHashSet);
    Object localObject3 = new HashSet();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      ExendedItem localExendedItem = (ExendedItem)localIterator.next();
      if (((Set)localObject1).contains(localExendedItem))
      {
        localObject2 = new Updater(localExendedItem);
        this._$1.updateByUpdater((Updater)localObject2);
      }
      else
      {
        ((Set)localObject3).add(localExendedItem);
      }
    }
    save((Collection)localObject3, paramExended);
    ((Set)localObject1).addAll((Collection)localObject3);
    return localObject1;
  }

  public ExendedItem deleteById(Long paramLong)
  {
    ExendedItem localExendedItem = this._$1.deleteById(paramLong);
    return localExendedItem;
  }

  public ExendedItem[] deleteByIds(Long[] paramArrayOfLong)
  {
    ExendedItem[] arrayOfExendedItem = new ExendedItem[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfExendedItem[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfExendedItem;
  }

  @Autowired
  public void setDao(ExendedItemDao paramExendedItemDao)
  {
    this._$1 = paramExendedItemDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ExendedItemMngImpl
 * JD-Core Version:    0.6.2
 */