package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopChannelContentDao;
import guda.shop.cms.entity.ShopChannelContent;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class ShopChannelContentDaoImpl extends HibernateBaseDao<ShopChannelContent, Long>
        implements ShopChannelContentDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public ShopChannelContent findById(Long paramLong) {
        ShopChannelContent localShopChannelContent = (ShopChannelContent) get(paramLong);
        return localShopChannelContent;
    }

    public ShopChannelContent save(ShopChannelContent paramShopChannelContent) {
        getSession().save(paramShopChannelContent);
        return paramShopChannelContent;
    }

    public ShopChannelContent deleteById(Long paramLong) {
        ShopChannelContent localShopChannelContent = (ShopChannelContent) super.get(paramLong);
        if (localShopChannelContent != null)
            getSession().delete(localShopChannelContent);
        return localShopChannelContent;
    }

    protected Class<ShopChannelContent> getEntityClass() {
        return ShopChannelContent.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopChannelContentDaoImpl
 * JD-Core Version:    0.6.2
 */