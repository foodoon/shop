package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopChannelDao;
import guda.shop.cms.entity.ShopChannel;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopChannelDaoImpl extends HibernateBaseDao<ShopChannel, Long>
        implements ShopChannelDao {
    public List<ShopChannel> getList(Long paramLong) {
        String str = "from ShopChannel bean where bean.website.id=?";
        return find(str, new Object[]{paramLong});
    }

    public List<ShopChannel> getList(Long paramLong, Integer paramInteger) {
        String str = "from ShopChannel bean where bean.website.id=? and bean.type=? order by bean.priority";
        return find(str, new Object[]{paramLong, paramInteger});
    }

    public List<ShopChannel> getList(Long paramLong1, Integer paramInteger, Long paramLong2, Long paramLong3) {
        List localList = getSession().createQuery("from ShopChannel bean where bean.website.id=:webId and bean.type=:type and bean.id >=:idBegin and bean.id <=:idEnd").setParameter("webId", paramLong1).setParameter("type", paramInteger).setParameter("idBegin", paramLong2).setParameter("idEnd", paramLong3).list();
        return localList;
    }

    public List<ShopChannel> getListForParent(Long paramLong1, Long paramLong2) {
        Finder localFinder = Finder.create("select node from ShopChannel node,ShopChannel exclude");
        localFinder.append(" where node.lft<exclude.lft and node.rgt>exclude.rgt");
        localFinder.append(" and exclude.id=:currId and node.website.id=:webId");
        localFinder.setParam("webId", paramLong1);
        localFinder.setParam("currId", paramLong2);
        return find(localFinder);
    }

    public List<ShopChannel> getListForChild(Long paramLong1, Long paramLong2) {
        Finder localFinder = Finder.create("select node from ShopChannel node, ShopChannel parent");
        localFinder.append(" where node.lft>=parent.lft and node.lft<=parent.rgt");
        localFinder.append(" and parent.id=:parentId and node.website.id=:webId");
        localFinder.setParam("webId", paramLong1);
        localFinder.setParam("parentId", paramLong2);
        return find(localFinder);
    }

    public List<ShopChannel> getTopList(Long paramLong, boolean paramBoolean, Integer paramInteger) {
        String str = "from ShopChannel bean where bean.website.id=? and bean.parent.id is null order by bean.priority";
        if (paramInteger != null)
            return getSession().createQuery(str).setParameter(0, paramLong).setCacheable(paramBoolean).setFirstResult(0).setMaxResults(paramInteger.intValue()).list();
        return getSession().createQuery(str).setParameter(0, paramLong).setCacheable(paramBoolean).list();
    }

    public List<ShopChannel> getChildList(Long paramLong1, Long paramLong2) {
        Finder localFinder = Finder.create("from ShopChannel bean");
        localFinder.append(" where bean.parent.id=:parentId");
        localFinder.setParam("parentId", paramLong2);
        localFinder.append(" order by bean.priority asc,bean.id asc");
        return find(localFinder);
    }

    public ShopChannel getByPath(Long paramLong, String paramString) {
        String str = "from ShopChannel bean where bean.website.id=? and bean.path=?";
        return (ShopChannel) findUnique(str, new Object[]{paramLong, paramString});
    }

    public ShopChannel findById(Long paramLong) {
        ShopChannel localShopChannel = (ShopChannel) get(paramLong);
        return localShopChannel;
    }

    public ShopChannel save(ShopChannel paramShopChannel) {
        getSession().save(paramShopChannel);
        return paramShopChannel;
    }

    public ShopChannel deleteById(Long paramLong) {
        ShopChannel localShopChannel = (ShopChannel) super.get(paramLong);
        if (localShopChannel != null)
            getSession().delete(localShopChannel);
        return localShopChannel;
    }

    protected Class<ShopChannel> getEntityClass() {
        return ShopChannel.class;
    }
}

