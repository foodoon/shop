package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ProductTypeDao;
import guda.shop.cms.entity.ProductType;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductTypeDaoImpl extends HibernateBaseDao<ProductType, Long>
        implements ProductTypeDao {
    public List<ProductType> getList(Long paramLong) {
        String str = "from ProductType bean where bean.website.id=?";
        return find(str, new Object[]{paramLong});
    }

    public ProductType findById(Long paramLong) {
        ProductType localProductType = (ProductType) get(paramLong);
        return localProductType;
    }

    public ProductType save(ProductType paramProductType) {
        getSession().save(paramProductType);
        return paramProductType;
    }

    public ProductType deleteById(Long paramLong) {
        ProductType localProductType = (ProductType) super.get(paramLong);
        if (localProductType != null)
            getSession().delete(localProductType);
        return localProductType;
    }

    protected Class<ProductType> getEntityClass() {
        return ProductType.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ProductTypeDaoImpl
 * JD-Core Version:    0.6.2
 */