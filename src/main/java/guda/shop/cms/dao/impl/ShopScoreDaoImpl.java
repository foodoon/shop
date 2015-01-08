package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopScoreDao;
import guda.shop.cms.entity.ShopScore;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ShopScoreDaoImpl extends HibernateBaseDao<ShopScore, Long>
        implements ShopScoreDao {
    public Pagination getPage(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, Date paramDate1, Date paramDate2, Integer paramInteger1, Integer paramInteger2) {
        Finder localFinder = Finder.create("select bean from ShopScore bean where 1=1 ");
        if (paramLong != null)
            localFinder.append(" and bean.member.id=:memberId").setParam("memberId", paramLong);
        if (paramBoolean1 != null)
            localFinder.append(" and bean.status=:status").setParam("status", paramBoolean1);
        if (paramBoolean2 != null)
            localFinder.append(" and bean.useStatus=:useStatus").setParam("useStatus", paramBoolean2);
        if (paramDate1 != null) {
            localFinder.append(" and bean.scoreTime>:startTime");
            localFinder.setParam("startTime", paramDate1);
        }
        if (paramDate2 != null) {
            localFinder.append(" and bean.scoreTime<:endTime");
            localFinder.setParam("endTime", paramDate2);
        }
        localFinder.append(" order by bean.id desc");
        return find(localFinder, paramInteger2.intValue(), paramInteger1.intValue());
    }

    public List<ShopScore> getlist(String paramString) {
        Finder localFinder = Finder.create("select bean from ShopScore bean where 1=1 ");
        if (!StringUtils.isBlank(paramString))
            localFinder.append(" and bean.code=:code").setParam("code", paramString);
        return find(localFinder);
    }

    public ShopScore findById(Long paramLong) {
        ShopScore localShopScore = (ShopScore) get(paramLong);
        return localShopScore;
    }

    public ShopScore save(ShopScore paramShopScore) {
        getSession().save(paramShopScore);
        return paramShopScore;
    }

    public ShopScore deleteById(Long paramLong) {
        ShopScore localShopScore = (ShopScore) super.get(paramLong);
        if (localShopScore != null)
            getSession().delete(localShopScore);
        return localShopScore;
    }

    protected Class<ShopScore> getEntityClass() {
        return ShopScore.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopScoreDaoImpl
 * JD-Core Version:    0.6.2
 */