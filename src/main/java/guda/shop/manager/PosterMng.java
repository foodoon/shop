package guda.shop.manager;

import guda.shop.cms.entity.Poster;
import java.util.List;

public abstract interface PosterMng
{
  public abstract Poster findById(Integer paramInteger);

  public abstract Poster update(Poster paramPoster);

  public abstract Poster deleteById(Integer paramInteger);

  public abstract Poster saveOrUpdate(Poster paramPoster);

  public abstract List<Poster> getPage();

  public abstract void deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.PosterMng
 * JD-Core Version:    0.6.2
 */