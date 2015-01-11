package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.CategoryDao;
import guda.shop.cms.entity.Category;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl extends HibernateBaseDao<Category, Long>
        implements CategoryDao {
    public Category getByPath(Long paramLong, String paramString, boolean paramBoolean) {
        String str = "from Category bean where bean.website.id=? and bean.path=?";
        return (Category) createQuery(str, new Object[]{paramLong, paramString}).setCacheable(paramBoolean).uniqueResult();
    }

    public List<Category> getListForParent(Long paramLong1, Long paramLong2) {
        Finder localFinder = Finder.create("select node");
        localFinder.append(" from Category node,Category exclude");
        localFinder.append(" where ex.id=:ctgId and node.website.id=?");
        localFinder.append(" and node.lft<exclude.lft and node.rgt>exclude.rgt");
        localFinder.append(" order by node.priority");
        localFinder.setParam("webId", paramLong1);
        localFinder.setParam("ctgId", paramLong2);
        return find(localFinder);
    }

    public List<Category> getListForChild(Long paramLong1, Long paramLong2) {
        Finder localFinder = Finder.create("select node");
        localFinder.append(" from Category node, Category parent");
        localFinder.append(" where parent.id=:ctgId and node.website.id=:webId");
        localFinder.append(" and node.lft>=parent.lft and node.rgt<=parent.rgt");
        localFinder.setParam("webId", paramLong1);
        localFinder.setParam("ctgId", paramLong2);
        return find(localFinder);
    }

    public List<Category> getTopList(Long paramLong, boolean paramBoolean) {
        String str = "from Category bean where bean.website.id=? and bean.parent.id is null order by bean.priority";
        return createQuery(str, new Object[]{paramLong}).setCacheable(paramBoolean).list();
    }

    public List<Category> getChildList(Long paramLong1, Long paramLong2) {
        Finder localFinder = Finder.create("from Category bean");
        localFinder.append(" where bean.parent.id=:parentId");
        localFinder.setParam("parentId", paramLong2);
        localFinder.append(" order by bean.priority asc,bean.id asc");
        return find(localFinder);
    }

    public int countPath(Long paramLong, String paramString) {
        String str = "select count(*) from Category bean where bean.website.id=:webId and bean.path=:path";
        return ((Number) getSession().createQuery(str).setParameter("webId", paramLong).setParameter("path", paramString).iterate().next()).intValue();
    }

    public List<Category> getListByptype(Long paramLong1, Long paramLong2, Integer paramInteger) {
        String str = "from Category bean where bean.website.id=? and bean.type.id=?";
        if ((paramInteger != null) && (paramInteger.intValue() != 0))
            return getSession().createQuery(str).setParameter(0, paramLong1).setParameter(1, paramLong2).setFirstResult(0).setMaxResults(paramInteger.intValue()).list();
        return getSession().createQuery(str).setParameter(0, paramLong1).setParameter(1, paramLong2).list();
    }

    public Category findById(Long paramLong) {
        Category localCategory = (Category) get(paramLong);
        return localCategory;
    }

    public Category save(Category paramCategory) {
        getSession().save(paramCategory);
        return paramCategory;
    }

    public Category deleteById(Long paramLong) {
        Category localCategory = (Category) super.get(paramLong);
        if (localCategory != null)
            getSession().delete(localCategory);
        return localCategory;
    }

    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}

