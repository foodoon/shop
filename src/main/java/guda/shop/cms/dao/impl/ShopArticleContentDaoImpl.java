package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopArticleContentDao;
import guda.shop.cms.entity.ShopArticleContent;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

@Repository
public class ShopArticleContentDaoImpl extends HibernateBaseDao<ShopArticleContent, Long>
        implements ShopArticleContentDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public ShopArticleContent findById(Long paramLong) {
        ShopArticleContent localShopArticleContent = (ShopArticleContent) get(paramLong);
        return localShopArticleContent;
    }

    public ShopArticleContent save(ShopArticleContent paramShopArticleContent) {
        getSession().save(paramShopArticleContent);
        return paramShopArticleContent;
    }

    public ShopArticleContent deleteById(Long paramLong) {
        ShopArticleContent localShopArticleContent = (ShopArticleContent) super.get(paramLong);
        if (localShopArticleContent != null)
            getSession().delete(localShopArticleContent);
        return localShopArticleContent;
    }

    protected Class<ShopArticleContent> getEntityClass() {
        return ShopArticleContent.class;
    }
}

