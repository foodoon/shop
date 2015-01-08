package guda.shop.cms.dao;

import guda.shop.cms.entity.Poster;

import java.util.List;

public abstract interface PosterDao {
    public abstract Poster findById(Integer paramInteger);

    public abstract Poster saveOrUpdate(Poster paramPoster);

    public abstract Poster update(Poster paramPoster);

    public abstract Poster deleteById(Integer paramInteger);

    public abstract List<Poster> getPage();
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.PosterDao
 * JD-Core Version:    0.6.2
 */