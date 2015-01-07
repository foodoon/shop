package guda.shop.dao.impl;

import guda.shop.cms.dao.GiftExchangeDao;
iimport guda.shopcms.entity.GiftExchange;
imimport guda.shopommon.hibernate3.Finder;
impimport guda.shopmmon.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class GiftExchangeDaoImpl extends HibernateBaseDao<GiftExchange, Long>
  implements GiftExchangeDao
{
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Criteria localCriteria = createCriteria(new Criterion[0]);
    Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
    return localPagination;
  }

  public List<GiftExchange> getlist(Long paramLong)
  {
    Finder localFinder = Finder.create("from GiftExchange bean where bean.member.id=:memberId");
    localFinder.setParam("memberId", paramLong);
    return find(localFinder);
  }

  public GiftExchange findById(Long paramLong)
  {
    GiftExchange localGiftExchange = (GiftExchange)get(paramLong);
    return localGiftExchange;
  }

  public GiftExchange save(GiftExchange paramGiftExchange)
  {
    getSession().save(paramGiftExchange);
    return paramGiftExchange;
  }

  public GiftExchange deleteById(Long paramLong)
  {
    GiftExchange localGiftExchange = (GiftExchange)super.get(paramLong);
    if (localGiftExchange != null)
      getSession().delete(localGiftExchange);
    return localGiftExchange;
  }

  protected Class<GiftExchange> getEntityClass()
  {
    return GiftExchange.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.GiftExchangeDaoImpl
 * JD-Core Version:    0.6.2
 */