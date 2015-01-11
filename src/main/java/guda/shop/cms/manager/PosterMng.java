package guda.shop.cms.manager;

import guda.shop.cms.entity.Poster;

import java.util.List;

public abstract interface PosterMng {
    public abstract Poster findById(Integer paramInteger);

    public abstract Poster update(Poster paramPoster);

    public abstract Poster deleteById(Integer paramInteger);

    public abstract Poster saveOrUpdate(Poster paramPoster);

    public abstract List<Poster> getPage();

    public abstract void deleteByIds(Integer[] paramArrayOfInteger);
}

