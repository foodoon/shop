package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.BrandTextDao;
import guda.shop.cms.entity.BrandText;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class BrandTextDaoImpl extends HibernateBaseDao<BrandText, Long>
        implements BrandTextDao {
    public BrandText save(BrandText paramBrandText) {
        getSession().save(paramBrandText);
        return paramBrandText;
    }

    protected Class<BrandText> getEntityClass() {
        return BrandText.class;
    }
}

