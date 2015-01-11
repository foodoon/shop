package guda.shop.cms.manager;

import guda.shop.cms.entity.ProductTag;

import java.util.List;

public abstract interface ProductTagMng {
    public abstract List<ProductTag> getList(Long paramLong);

    public abstract ProductTag findById(Long paramLong);

    public abstract ProductTag save(ProductTag paramProductTag);

    public abstract ProductTag[] updateTagName(Long[] paramArrayOfLong, String[] paramArrayOfString);

    public abstract ProductTag[] deleteByIds(Long[] paramArrayOfLong);
}

