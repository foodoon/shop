package guda.shop.dao.impl;

import guda.shop.cms.dao.ShipmentsDao;
iimport guda.shopcms.entity.Shipments;
imimport guda.shopommon.hibernate3.Finder;
impimport guda.shopmmon.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class ShipmentsDaoImpl extends HibernateBaseDao<Shipments, Long>
  implements ShipmentsDao
{
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
    return localPagination;
  }

  public List<Shipments> getlist(Long paramLong)
  {
    Finder localFinder = Finder.create("from Shipments bean where bean.indent.id=:id");
    localFinder.setParam("id", paramLong);
    return find(localFinder);
  }

  public Shipments findById(Long paramLong)
  {
    Shipments localShipments = (Shipments)get(paramLong);
    return localShipments;
  }

  public Shipments save(Shipments paramShipments)
  {
    getSession().save(paramShipments);
    return paramShipments;
  }

  public Shipments deleteById(Long paramLong)
  {
    Shipments localShipments = (Shipments)super.get(paramLong);
    if (localShipments != null)
      getSession().delete(localShipments);
    return localShipments;
  }

  protected Class<Shipments> getEntityClass()
  {
    return Shipments.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShipmentsDaoImpl
 * JD-Core Version:    0.6.2
 */