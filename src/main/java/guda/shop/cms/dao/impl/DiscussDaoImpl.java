package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.DiscussDao;
import guda.shop.cms.entity.Discuss;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class DiscussDaoImpl extends HibernateBaseDao<Discuss, Long>
        implements DiscussDao {
    public Discuss findById(Long paramLong) {
        Discuss localDiscuss = (Discuss) get(paramLong);
        return localDiscuss;
    }

    public Discuss saveOrUpdate(Discuss paramDiscuss) {
        getSession().saveOrUpdate(paramDiscuss);
        return paramDiscuss;
    }

    public Discuss update(Discuss paramDiscuss) {
        getSession().update(paramDiscuss);
        return paramDiscuss;
    }

    public Pagination getPageByProduct(Long paramLong, String paramString1, String paramString2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = Finder.create("from Discuss bean where 1=1 ");
        if (paramLong != null) {
            localFinder.append(" and bean.product.id=:id");
            localFinder.setParam("id", paramLong);
        }
        if (!StringUtils.isBlank(paramString1)) {
            localFinder.append(" and bean.member.member.user.username like:userName");
            localFinder.setParam("userName", "%" + paramString1 + "%");
        }
        if (!StringUtils.isBlank(paramString2)) {
            localFinder.append(" and bean.product.name like:productName");
            localFinder.setParam("productName", "%" + paramString2 + "%");
        }
        if (paramDate1 != null) {
            localFinder.append(" and bean.time>:startTime");
            localFinder.setParam("startTime", paramDate1);
        }
        if (paramDate2 != null) {
            localFinder.append(" and bean.time<:endTime");
            localFinder.setParam("endTime", paramDate2);
        }
        localFinder.append(" order by bean.id desc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public Pagination getPageByMember(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = Finder.create("from Discuss bean");
        if (paramLong != null) {
            localFinder.append(" where bean.member.id=:id");
            localFinder.setParam("id", paramLong);
        }
        localFinder.append(" order by bean.id desc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public Discuss deleteById(Long paramLong) {
        Discuss localDiscuss = (Discuss) super.get(paramLong);
        if (localDiscuss != null)
            getSession().delete(localDiscuss);
        return localDiscuss;
    }

    protected Class<Discuss> getEntityClass() {
        return Discuss.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.DiscussDaoImpl
 * JD-Core Version:    0.6.2
 */