package guda.shop.cms.manager.impl;

import guda.shop.cms.dao.PosterDao;
import guda.shop.cms.entity.Poster;
import guda.shop.cms.manager.PosterMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PosterMngImpl
        implements PosterMng {
    private PosterDao _$1;

    @Transactional(readOnly = true)
    public Poster findById(Integer paramInteger) {
        Poster localPoster = this._$1.findById(paramInteger);
        return localPoster;
    }

    public Poster saveOrUpdate(Poster paramPoster) {
        this._$1.saveOrUpdate(paramPoster);
        return paramPoster;
    }

    public List<Poster> getPage() {
        return this._$1.getPage();
    }

    public Poster update(Poster paramPoster) {
        return this._$1.update(paramPoster);
    }

    public void deleteByIds(Integer[] paramArrayOfInteger) {
        for (Integer localInteger : paramArrayOfInteger)
            deleteById(localInteger);
    }

    public Poster deleteById(Integer paramInteger) {
        Poster localPoster = this._$1.deleteById(paramInteger);
        return localPoster;
    }

    @Autowired
    public void setDao(PosterDao paramPosterDao) {
        this._$1 = paramPosterDao;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.PosterMngImpl
 * JD-Core Version:    0.6.2
 */