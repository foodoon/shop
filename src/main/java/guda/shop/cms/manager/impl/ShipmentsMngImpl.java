package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.ShipmentsDao;
iimport guda.shopcms.entity.Shipments;
imimport guda.shopms.manager.ShipmentsMng;
impimport guda.shopmmon.hibernate3.Updater;
import com.jspgou.common.page.Pagination;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShipmentsMngImpl
  implements ShipmentsMng
{
  private ShipmentsDao _$1;

  @Transactional(readOnly=true)
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Pagination localPagination = this._$1.getPage(paramInt1, paramInt2);
    return localPagination;
  }

  public List<Shipments> getlist(Long paramLong)
  {
    return this._$1.getlist(paramLong);
  }

  public void deleteByorderId(Long paramLong)
  {
    List localList = getlist(paramLong);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Shipments localShipments = (Shipments)localIterator.next();
      deleteById(localShipments.getId());
    }
  }

  @Transactional(readOnly=true)
  public Shipments findById(Long paramLong)
  {
    Shipments localShipments = this._$1.findById(paramLong);
    return localShipments;
  }

  public Shipments save(Shipments paramShipments)
  {
    this._$1.save(paramShipments);
    return paramShipments;
  }

  public Shipments update(Shipments paramShipments)
  {
    Updater localUpdater = new Updater(paramShipments);
    Shipments localShipments = this._$1.updateByUpdater(localUpdater);
    return localShipments;
  }

  public Shipments deleteById(Long paramLong)
  {
    Shipments localShipments = this._$1.deleteById(paramLong);
    return localShipments;
  }

  public Shipments[] deleteByIds(Long[] paramArrayOfLong)
  {
    Shipments[] arrayOfShipments = new Shipments[paramArrayOfLong.length];
    int i = 0;
    int j = paramArrayOfLong.length;
    while (i < j)
    {
      arrayOfShipments[i] = deleteById(paramArrayOfLong[i]);
      i++;
    }
    return arrayOfShipments;
  }

  @Autowired
  public void setDao(ShipmentsDao paramShipmentsDao)
  {
    this._$1 = paramShipmentsDao;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ShipmentsMngImpl
 * JD-Core Version:    0.6.2
 */