package guda.shop.cms.manager;

import guda.shop.cms.entity.Brand;
import guda.shop.cms.entity.Category;

import java.util.List;
import java.util.Map;

public abstract interface CategoryMng {
    public abstract Category getByPath(Long paramLong, String paramString);

    public abstract Category getByPathForTag(Long paramLong, String paramString);

    public abstract List<Category> getListForParent(Long paramLong1, Long paramLong2);

    public abstract List<Category> getListForProduct(Long paramLong1, Long paramLong2);

    public abstract List<Category> getTopList(Long paramLong);

    public abstract List<Category> getChildList(Long paramLong1, Long paramLong2);

    public abstract List<Category> getTopListForTag(Long paramLong);

    public abstract List<Category> getList(Long paramLong);

    public abstract boolean checkPath(Long paramLong, String paramString);

    public abstract Category findById(Long paramLong);

    public abstract Category save(Category paramCategory, Long paramLong1, Long paramLong2, Long[] paramArrayOfLong1, Long[] paramArrayOfLong2);

    public abstract Category update(Category paramCategory, Long paramLong1, Long paramLong2, Long[] paramArrayOfLong1, Map<String, String> paramMap, Long[] paramArrayOfLong2);

    public abstract Category deleteById(Long paramLong);

    public abstract Category[] deleteByIds(Long[] paramArrayOfLong);

    public abstract Category[] updatePriority(Long[] paramArrayOfLong, Integer[] paramArrayOfInteger);

    public abstract List<Category> getListBypType(Long paramLong1, Long paramLong2, Integer paramInteger);

    public abstract List<Brand> getBrandByCate(Long paramLong);
}

