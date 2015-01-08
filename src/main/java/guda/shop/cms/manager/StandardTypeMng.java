package guda.shop.cms.manager;

import guda.shop.cms.entity.StandardType;
import guda.shop.common.page.Pagination;

import java.util.List;

public abstract interface StandardTypeMng {
    public abstract Pagination getPage(int paramInt1, int paramInt2);

    public abstract StandardType getByField(String paramString);

    public abstract StandardType findById(Long paramLong);

    public abstract List<StandardType> getList();

    public abstract List<StandardType> getList(Long paramLong);

    public abstract StandardType save(StandardType paramStandardType);

    public abstract StandardType update(StandardType paramStandardType);

    public abstract StandardType deleteById(Long paramLong);

    public abstract StandardType[] deleteByIds(Long[] paramArrayOfLong);

    public abstract StandardType[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.StandardTypeMng
 * JD-Core Version:    0.6.2
 */