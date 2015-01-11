package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopDictionaryTypeDao;
import guda.shop.cms.entity.ShopDictionaryType;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDictionaryTypeDaoImpl extends HibernateBaseDao<ShopDictionaryType, Long>
        implements ShopDictionaryTypeDao {
    public Pagination getPage(int paramInt1, int paramInt2) {
        Criteria localCriteria = createCriteria(new Criterion[0]);
        Pagination localPagination = findByCriteria(localCriteria, paramInt1, paramInt2);
        return localPagination;
    }

    public ShopDictionaryType findById(Long paramLong) {
        ShopDictionaryType localShopDictionaryType = (ShopDictionaryType) get(paramLong);
        return localShopDictionaryType;
    }

    public List<ShopDictionaryType> findAll() {
        Finder localFinder = Finder.create("from ShopDictionaryType bean ");
        return find(localFinder);
    }

    public ShopDictionaryType save(ShopDictionaryType paramShopDictionaryType) {
        getSession().save(paramShopDictionaryType);
        return paramShopDictionaryType;
    }

    public ShopDictionaryType deleteById(Long paramLong) {
        ShopDictionaryType localShopDictionaryType = (ShopDictionaryType) super.get(paramLong);
        if (localShopDictionaryType != null)
            getSession().delete(localShopDictionaryType);
        return localShopDictionaryType;
    }

    protected Class<ShopDictionaryType> getEntityClass() {
        return ShopDictionaryType.class;
    }
}

