package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ShopArticleDao;
import guda.shop.cms.entity.ShopArticle;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopArticleDaoImpl extends HibernateBaseDao<ShopArticle, Long>
        implements ShopArticleDao {
    public Pagination getPage(Long paramLong1, Long paramLong2, int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("select bean from ShopArticle bean ");
        if (paramLong1 != null) {
            localFinder.append(" inner join bean.channel channel,ShopChannel parent");
            localFinder.append(" where channel.lft between parent.lft and parent.rgt");
            localFinder.append(" and parent.id=:parentId");
            localFinder.setParam("parentId", paramLong1);
            localFinder.append(" and bean.website.id=:webId");
            localFinder.setParam("webId", paramLong2);
        } else {
            localFinder.append(" where bean.website.id=:webId");
            localFinder.setParam("webId", paramLong2);
        }
        localFinder.append(" order by bean.publishTime desc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public Pagination getPageByChannel(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = Finder.create("from ShopArticle bean");
        localFinder.append(" where bean.channel.id=:channelId");
        localFinder.append(" order by bean.publishTime desc");
        localFinder.setParam("channelId", paramLong);
        localFinder.setCacheable(paramBoolean);
        return find(localFinder, paramInt1, paramInt2);
    }

    public Pagination getPageByWebsite(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = Finder.create("from ShopArticle bean");
        localFinder.append(" where bean.website.id=:webId");
        localFinder.append(" order by bean.publishTime desc");
        localFinder.setParam("webId", paramLong);
        localFinder.setCacheable(paramBoolean);
        return find(localFinder, paramInt1, paramInt2);
    }

    public List<ShopArticle> getListByChannel(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = Finder.create("from ShopArticle bean");
        localFinder.append(" where bean.channel.id=:channelId");
        localFinder.setParam("channelId", paramLong);
        localFinder.setCacheable(paramBoolean);
        localFinder.setFirstResult(paramInt1);
        localFinder.setMaxResults(paramInt2);
        return find(localFinder);
    }

    public List<ShopArticle> getListByWebsite(Long paramLong, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = Finder.create("from ShopArticle bean");
        localFinder.append(" where bean.website.id=:webId");
        localFinder.setParam("webId", paramLong);
        localFinder.setCacheable(paramBoolean);
        localFinder.setFirstResult(paramInt1);
        localFinder.setMaxResults(paramInt2);
        return find(localFinder);
    }

    public ShopArticle findById(Long paramLong) {
        ShopArticle localShopArticle = (ShopArticle) get(paramLong);
        return localShopArticle;
    }

    public ShopArticle save(ShopArticle paramShopArticle) {
        getSession().save(paramShopArticle);
        return paramShopArticle;
    }

    public ShopArticle deleteById(Long paramLong) {
        ShopArticle localShopArticle = (ShopArticle) super.get(paramLong);
        if (localShopArticle != null)
            getSession().delete(localShopArticle);
        return localShopArticle;
    }

    protected Class<ShopArticle> getEntityClass() {
        return ShopArticle.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ShopArticleDaoImpl
 * JD-Core Version:    0.6.2
 */