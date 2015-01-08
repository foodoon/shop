package guda.shop.cms.manager;

import guda.shop.cms.entity.Discuss;
import guda.shop.common.page.Pagination;

import java.util.Date;

public abstract interface DiscussMng {
    public abstract Discuss findById(Long paramLong);

    public abstract Discuss update(Discuss paramDiscuss);

    public abstract Discuss deleteById(Long paramLong);

    public abstract Discuss saveOrUpdate(Long paramLong1, String paramString, Long paramLong2);

    public abstract Pagination getPage(Long paramLong, String paramString1, String paramString2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract Discuss[] deleteByIds(Long[] paramArrayOfLong);

    public abstract Pagination getPageByMember(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.DiscussMng
 * JD-Core Version:    0.6.2
 */