package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ProductTagDao;
import guda.shop.cms.entity.ProductTag;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductTagDaoImpl extends HibernateBaseDao<ProductTag, Long>
        implements ProductTagDao {
    public List<ProductTag> getList(Long paramLong) {
        String str = "from ProductTag bean where bean.website.id=?";
        return find(str, new Object[]{paramLong});
    }

    public ProductTag findById(Long paramLong) {
        ProductTag localProductTag = (ProductTag) get(paramLong);
        return localProductTag;
    }

    public ProductTag save(ProductTag paramProductTag) {
        getSession().save(paramProductTag);
        return paramProductTag;
    }

    public ProductTag deleteById(Long paramLong) {
        ProductTag localProductTag = (ProductTag) super.get(paramLong);
        if (localProductTag != null)
            getSession().delete(localProductTag);
        return localProductTag;
    }

    protected Class<ProductTag> getEntityClass() {
        return ProductTag.class;
    }
}

