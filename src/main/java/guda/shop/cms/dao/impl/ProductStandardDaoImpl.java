package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ProductStandardDao;
import guda.shop.cms.entity.ProductStandard;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductStandardDaoImpl extends HibernateBaseDao<ProductStandard, Long>
        implements ProductStandardDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public ProductStandard findById(Long paramLong) {
        ProductStandard localProductStandard = (ProductStandard) get(paramLong);
        return localProductStandard;
    }

    public List<ProductStandard> findByProductIdAndStandardId(Long paramLong1, Long paramLong2) {
        Finder localFinder = Finder.create("from ProductStandard bean where 1=1");
        localFinder.append(" and bean.product.id=:productId").setParam("productId", paramLong1);
        localFinder.append(" and bean.standard.id=:standardId").setParam("standardId", paramLong2);
        return find(localFinder);
    }

    public List<ProductStandard> findByProductId(Long paramLong) {
        Finder localFinder = Finder.create("from ProductStandard bean where 1=1");
        localFinder.append(" and bean.product.id=:productId").setParam("productId", paramLong);
        return find(localFinder);
    }

    public ProductStandard save(ProductStandard paramProductStandard) {
        getSession().save(paramProductStandard);
        return paramProductStandard;
    }

    public ProductStandard deleteById(Long paramLong) {
        ProductStandard localProductStandard = (ProductStandard) super.get(paramLong);
        if (localProductStandard != null)
            getSession().delete(localProductStandard);
        return localProductStandard;
    }

    protected Class<ProductStandard> getEntityClass() {
        return ProductStandard.class;
    }
}

