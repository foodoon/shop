package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.PosterDao;
import guda.shop.cms.entity.Poster;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PosterDaoImpl extends HibernateBaseDao<Poster, Integer>
        implements PosterDao {
    public Poster findById(Integer paramInteger) {
        Poster localPoster = (Poster) get(paramInteger);
        return localPoster;
    }

    public Poster saveOrUpdate(Poster paramPoster) {
        getSession().saveOrUpdate(paramPoster);
        return paramPoster;
    }

    public Poster update(Poster paramPoster) {
        getSession().update(paramPoster);
        return paramPoster;
    }

    public List<Poster> getPage() {
        return getSession().createQuery("from Poster bean").list();
    }

    public Poster deleteById(Integer paramInteger) {
        Poster localPoster = (Poster) super.get(paramInteger);
        if (localPoster != null)
            getSession().delete(localPoster);
        return localPoster;
    }

    protected Class<Poster> getEntityClass() {
        return Poster.class;
    }
}

