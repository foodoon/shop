package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ProductTextDao;
import guda.shop.cms.entity.ProductText;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class ProductTextDaoImpl extends HibernateBaseDao<ProductText, Long>
        implements ProductTextDao {
    public ProductText save(ProductText paramProductText) {
        getSession().save(paramProductText);
        return paramProductText;
    }

    protected Class<ProductText> getEntityClass() {
        return ProductText.class;
    }
}

