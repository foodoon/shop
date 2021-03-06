package guda.shop.cms.manager;

import guda.shop.cms.entity.Gathering;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface GatheringMng {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract List<Gathering> getlist(Long paramLong);

    public abstract void deleteByorderId(Long paramLong);

    public abstract Gathering findById(Long paramLong);

    public abstract Gathering save(Gathering paramGathering);

    public abstract Gathering update(Gathering paramGathering);

    public abstract Gathering deleteById(Long paramLong);

    public abstract Gathering[] deleteByIds(Long[] paramArrayOfLong);
}

