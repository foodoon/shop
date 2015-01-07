package guda.shop.dao.impl;

import guda.shop.cms.dao.ExendedDao;
iimport guda.shopcms.entity.Exended;
imimport guda.shopommon.hibernate3.Finder;
impimport guda.shopmmon.hibernate3.HibernateBaseDao;
import com.jspgou.common.page.Pagination;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ExendedDaoImpl extends HibernateBaseDao<Exended, Long>
  implements ExendedDao
{
  public Pagination getPage(int paramInt1, int paramInt2)
  {
    Finder localFinder = Finder.create("from Exended bean where 1=1");
    localFinder.append(" order by bean.priority");
    return find(localFinder, paramInt1, paramInt2);
  }

  public Exended findById(Long paramLong)
  {
    Exended localExended = (Exended)get(paramLong);
    return localExended;
  }

  public List<Exended> getList()
  {
    Finder localFinder = Finder.create("from Exended bean where 1=1");
    localFinder.append(" order by bean.priority");
    return find(localFinder);
  }

  public List<Exended> getList(Long paramLong)
  {
    Finder localFinder = Finder.create("select bean from Exended bean ");
    localFinder.append(" inner join bean.productTypes ptype ");
    localFinder.append(" where ptype.id=:typeId").setParam("typeId", paramLong);
    localFinder.append(" order by bean.priority");
    return find(localFinder);
  }

  public Exended save(Exended paramExended)
  {
    getSession().save(paramExended);
    return paramExended;
  }

  public Exended deleteById(Long paramLong)
  {
    Exended localExended = (Exended)super.get(paramLong);
    if (localExended != null)
      getSession().delete(localExended);
    return localExended;
  }

  protected Class<Exended> getEntityClass()
  {
    return Exended.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ExendedDaoImpl
 * JD-Core Version:    0.6.2
 */