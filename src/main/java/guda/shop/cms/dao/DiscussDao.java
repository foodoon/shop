package guda.shop.cms.dao;

import guda.shop.cms.entity.Discuss;
import guda.shop.common.page.Pagination;

import java.util.Date;

public abstract interface DiscussDao {
    public abstract Discuss findById(Long paramLong);

    public abstract Discuss saveOrUpdate(Discuss paramDiscuss);

    public abstract Discuss update(Discuss paramDiscuss);

    public abstract Discuss deleteById(Long paramLong);

    public abstract Pagination getPageByProduct(Long paramLong, String paramString1, String paramString2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract Pagination getPageByMember(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean);
}

