package guda.shop.dao.impl;

import guda.shop.cms.dao.PosterDao;
iimport guda.shopcms.entity.Poster;
import com.jspgou.common.hibernate3.HibernateBaseDao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class PosterDaoImpl extends HibernateBaseDao<Poster, Integer>
  implements PosterDao
{
  public Poster findById(Integer paramInteger)
  {
    Poster localPoster = (Poster)get(paramInteger);
    return localPoster;
  }

  public Poster saveOrUpdate(Poster paramPoster)
  {
    getSession().saveOrUpdate(paramPoster);
    return paramPoster;
  }

  public Poster update(Poster paramPoster)
  {
    getSession().update(paramPoster);
    return paramPoster;
  }

  public List<Poster> getPage()
  {
    return getSession().createQuery("from Poster bean").list();
  }

  public Poster deleteById(Integer paramInteger)
  {
    Poster localPoster = (Poster)super.get(paramInteger);
    if (localPoster != null)
      getSession().delete(localPoster);
    return localPoster;
  }

  protected Class<Poster> getEntityClass()
  {
    return Poster.class;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.PosterDaoImpl
 * JD-Core Version:    0.6.2
 */