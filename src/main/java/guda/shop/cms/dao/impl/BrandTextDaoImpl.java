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

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.BrandTextDaoImpl
 * JD-Core Version:    0.6.2
 */