package guda.shop.cms.dao;

import guda.shop.cms.entity.Category;
import guda.shop.common.hibernate3.Updater;

import java.util.List;

public abstract interface CategoryDao {
    public abstract Category getByPath(Long paramLong, String paramString, boolean paramBoolean);

    public abstract List<Category> getListForParent(Long paramLong1, Long paramLong2);

    public abstract List<Category> getListForChild(Long paramLong1, Long paramLong2);

    public abstract List<Category> getTopList(Long paramLong, boolean paramBoolean);

    public abstract List<Category> getChildList(Long paramLong1, Long paramLong2);

    public abstract int countPath(Long paramLong, String paramString);

    public abstract Category findById(Long paramLong);

    public abstract Category save(Category paramCategory);

    public abstract Category updateByUpdater(Updater<Category> paramUpdater);

    public abstract Category deleteById(Long paramLong);

    public abstract List<Category> getListByptype(Long paramLong1, Long paramLong2, Integer paramInteger);
}

