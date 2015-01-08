package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ConsultDao;
import guda.shop.cms.entity.Consult;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class ConsultDaoImpl extends HibernateBaseDao<Consult, Long>
        implements ConsultDao {
    public Consult findById(Long paramLong) {
        Consult localConsult = (Consult) get(paramLong);
        return localConsult;
    }

    public Consult saveOrUpdate(Consult paramConsult) {
        getSession().saveOrUpdate(paramConsult);
        return paramConsult;
    }

    public List<Consult> findByProductId(Long paramLong) {
        Finder localFinder = Finder.create("from Consult bean where bean.product.id=:id").setParam("id", paramLong);
        return find(localFinder);
    }

    public Consult getSameConsult(Long paramLong) {
        Iterator localIterator = getSession().createQuery("from Consult bean where bean.member.id=:id order by bean.id desc").setParameter("id", paramLong).setMaxResults(1).iterate();
        if (localIterator.hasNext())
            return (Consult) localIterator.next();
        return null;
    }

    public Pagination getPage(Long paramLong, String paramString1, String paramString2, Date paramDate1, Date paramDate2, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = Finder.create("from Consult bean where 1=1 ");
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
        Finder localFinder = Finder.create("from Consult bean");
        if (paramLong != null) {
            localFinder.append(" where bean.member.id=:id");
            localFinder.setParam("id", paramLong);
        }
        localFinder.append(" order by bean.id desc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public Consult update(Consult paramConsult) {
        getSession().update(paramConsult);
        return paramConsult;
    }

    public Consult deleteById(Long paramLong) {
        Consult localConsult = (Consult) super.get(paramLong);
        if (localConsult != null)
            getSession().delete(localConsult);
        return localConsult;
    }

    protected Class<Consult> getEntityClass() {
        return Consult.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ConsultDaoImpl
 * JD-Core Version:    0.6.2
 */