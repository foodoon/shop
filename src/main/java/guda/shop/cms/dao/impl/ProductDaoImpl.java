package guda.shop.cms.dao.impl;

import guda.shop.cms.dao.ProductDao;
import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductTag;
import guda.shop.cms.lucene.LuceneProduct;
import guda.shop.common.hibernate3.Finder;
import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.common.page.Pagination;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class ProductDaoImpl extends HibernateBaseDao<Product, Long>
        implements ProductDao {
    public Pagination getPageByCategory(Long paramLong1, String paramString1, String paramString2, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5, Long paramLong2, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = _$2(paramLong1, paramString1, paramString2, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, paramLong2, paramDouble1, paramDouble2, paramInteger1, paramInteger2, paramBoolean);
        return find(localFinder, paramInt1, paramInt2);
    }

    public Pagination getPageByCategoryChannel(Long paramLong1, Long paramLong2, Boolean paramBoolean1, String[] paramArrayOfString1, String[] paramArrayOfString2, Boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
        Finder localFinder = _$1(paramLong1, paramLong2, paramBoolean1, paramArrayOfString1, paramArrayOfString2, paramBoolean2, paramInt1, paramBoolean);
        return find(localFinder, paramInt2, paramInt3);
    }

    public Pagination getPageByStockWarning(Long paramLong, Integer paramInteger, Boolean paramBoolean, int paramInt1, int paramInt2) {
        Finder localFinder = Finder.create("select bean from Product bean");
        localFinder.append(" where bean.website.id=:webId").setParam("webId", paramLong);
        localFinder.append(" and bean.stockCount <=:count").setParam("count", paramInteger);
        localFinder.append(" and bean.lackRemind=:status").setParam("status", paramBoolean);
        localFinder.append(" order by bean.stockCount asc");
        return find(localFinder, paramInt1, paramInt2);
    }

    public List<Product> getHistoryProduct(HashSet<Long> paramHashSet, Integer paramInteger) {
        if (paramHashSet.size() > 0)
            return getSession().createQuery("from Product bean where bean.id in (:ids)").setParameterList("ids", paramHashSet).setMaxResults(paramInteger.intValue()).list();
        return new ArrayList();
    }

    public Pagination getPageByWebsite(Long paramLong1, String paramString1, String paramString2, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5, Long paramLong2, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = _$1(paramLong1, paramString1, paramString2, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, paramLong2, paramDouble1, paramDouble2, paramInteger1, paramInteger2, paramBoolean);
        return find(localFinder, paramInt1, paramInt2);
    }

    public Pagination getPageByTag(Long paramLong1, Long paramLong2, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = _$1(paramLong2, paramLong1, paramBoolean1, paramBoolean2, paramBoolean);
        return find(localFinder, paramInt1, paramInt2);
    }

    public List<Product> getListByCategory(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = _$2(paramLong, null, null, Boolean.valueOf(true), paramBoolean1, paramBoolean2, null, null, null, null, null, null, null, paramBoolean);
        localFinder.setFirstResult(paramInt1);
        localFinder.setMaxResults(paramInt2);
        return find(localFinder);
    }

    public List<Product> getListByWebsite(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = _$1(paramLong, null, null, null, paramBoolean1, paramBoolean2, null, null, null, null, null, null, null, paramBoolean);
        localFinder.setFirstResult(paramInt1);
        localFinder.setMaxResults(paramInt2);
        return find(localFinder);
    }

    public List<Product> getListByWebsite1(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = _$1(paramLong, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean);
        localFinder.setFirstResult(paramInt1);
        localFinder.setMaxResults(paramInt2);
        return find(localFinder);
    }

    public List<Product> getListByTag(Long paramLong1, Long paramLong2, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean) {
        Finder localFinder = _$1(paramLong1, paramLong2, paramBoolean1, paramBoolean2, paramBoolean);
        localFinder.setFirstResult(paramInt1);
        localFinder.setMaxResults(paramInt2);
        return find(localFinder);
    }

    private Finder _$2(Long paramLong1, String paramString1, String paramString2, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5, Long paramLong2, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, boolean paramBoolean) {
        Finder localFinder = Finder.create("select bean from Product bean");
        localFinder.append(" inner join bean.category node,Category parent");
        localFinder.append(" where node.lft between parent.lft and parent.rgt");
        localFinder.append(" and parent.id=:ctgId");
        if (!StringUtils.isBlank(paramString1)) {
            localFinder.append(" and bean.name like:productName");
            localFinder.setParam("productName", "%" + paramString1 + "%");
        }
        if (!StringUtils.isBlank(paramString2)) {
            localFinder.append(" and bean.brand.name like:brandName");
            localFinder.setParam("brandName", "%" + paramString2 + "%");
        }
        if (paramBoolean1 != null) {
            localFinder.append(" and bean.onSale=:isOnSale");
            localFinder.setParam("isOnSale", paramBoolean1);
        }
        if (paramBoolean2 != null) {
            localFinder.append(" and bean.recommend=:isRecommend");
            localFinder.setParam("isRecommend", paramBoolean2);
        }
        if (paramBoolean3 != null) {
            localFinder.append(" and bean.special=:isSpecial");
            localFinder.setParam("isSpecial", paramBoolean3);
        }
        if (paramBoolean4 != null) {
            localFinder.append(" and bean.hotsale=:isHotsale");
            localFinder.setParam("isHotsale", paramBoolean4);
        }
        if (paramBoolean5 != null) {
            localFinder.append(" and bean.newProduct=:isNewProduct");
            localFinder.setParam("isNewProduct", paramBoolean5);
        }
        if (paramLong2 != null) {
            localFinder.append(" and bean.type.id=:typeId");
            localFinder.setParam("typeId", paramLong2);
        }
        if (paramDouble1 != null) {
            localFinder.append(" and bean.salePrice>:startSalePrice");
            localFinder.setParam("startSalePrice", paramDouble1);
        }
        if (paramDouble2 != null) {
            localFinder.append(" and bean.salePrice<:endSalePrice");
            localFinder.setParam("endSalePrice", paramDouble2);
        }
        if (paramInteger1 != null) {
            localFinder.append(" and bean.stockCount>:startStock");
            localFinder.setParam("startStock", paramInteger1);
        }
        if (paramInteger2 != null) {
            localFinder.append(" and bean.stockCount<:endStock");
            localFinder.setParam("endStock", paramInteger2);
        }
        localFinder.append(" order by bean.id desc");
        localFinder.setParam("ctgId", paramLong1);
        localFinder.setCacheable(paramBoolean);
        return localFinder;
    }

    public List<Product> getList(Long paramLong1, Long paramLong2, String paramString, boolean paramBoolean) {
        Finder localFinder = Finder.create("from Product bean where 1=1");
        if (paramLong1 != null) {
            localFinder.append(" and bean.type.id=:typeId");
            localFinder.setParam("typeId", paramLong1);
        }
        if (paramLong2 != null) {
            localFinder.append(" and bean.brand.id=:brandId");
            localFinder.setParam("brandId", paramLong2);
        }
        if (!StringUtils.isBlank(paramString)) {
            localFinder.append(" and bean.name like:productName");
            localFinder.setParam("productName", "%" + paramString + "%");
        }
        localFinder.setCacheable(paramBoolean);
        return find(localFinder);
    }

    public Pagination getPage(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
        Finder localFinder = Finder.create("from Product bean where 1=1");
        _$1(localFinder, paramInt1);
        localFinder.setCacheable(paramBoolean);
        return find(localFinder, paramInt2, paramInt3);
    }

    private Finder _$1(Long paramLong1, Long paramLong2, Boolean paramBoolean1, String[] paramArrayOfString1, String[] paramArrayOfString2, Boolean paramBoolean2, int paramInt, boolean paramBoolean) {
        Finder localFinder = Finder.create("select distinct bean from Product bean");
        localFinder.append(" join bean.category node,Category parent");
        String[] arrayOfString = null;
        int i;
        String str2;
        if (paramArrayOfString1 != null) {
            arrayOfString = new String[paramArrayOfString1.length];
            i = 0;
            int j = paramArrayOfString1.length;
            while (i < j) {
                str2 = "exended" + i;
                arrayOfString[i] = str2;
                localFinder.append(" inner join bean.exendeds " + str2);
                i++;
            }
        }
        localFinder.append(" where node.lft between parent.lft and parent.rgt and bean.onSale=true");
        localFinder.append(" and parent.id=:ctgId");
        localFinder.setParam("ctgId", paramLong2);
        if (paramBoolean1 != null) {
            localFinder.append(" and node.recommend=:recommend");
            localFinder.setParam("recommend", paramBoolean1);
        }
        if (paramBoolean2 != null) {
            localFinder.append(" and node.special=:special");
            localFinder.setParam("special", paramBoolean2);
        }
        if (paramLong1 != null) {
            localFinder.append(" and bean.brand.id=:id");
            localFinder.setParam("id", paramLong1);
        }
        if (paramArrayOfString1 != null)
            for (i = 0; i < paramArrayOfString1.length; i++) {
                String str1 = "attr_name" + i;
                str2 = "attr_value" + i;
                if (!paramArrayOfString2[i].equals("0")) {
                    localFinder.append(" and " + arrayOfString[i] + ".name=:" + str1).setParam(str1, paramArrayOfString1[i]);
                    localFinder.append(" and " + arrayOfString[i] + ".value=:" + str2).setParam(str2, paramArrayOfString2[i]);
                }
            }
        _$1(localFinder, paramInt);
        localFinder.setCacheable(paramBoolean);
        return localFinder;
    }

    private void _$1(Finder paramFinder, int paramInt) {
        switch (paramInt) {
            case 1:
                paramFinder.append(" order by bean.id asc");
                break;
            case 2:
                paramFinder.append(" order by bean.createTime desc");
                break;
            case 3:
                paramFinder.append(" order by bean.createTime asc");
                break;
            case 4:
                paramFinder.append(" order by bean.saleCount desc, bean.createTime desc");
                break;
            case 5:
                paramFinder.append(" order by bean.saleCount desc, bean.createTime asc");
                break;
            case 6:
                paramFinder.append(" order by bean.salePrice desc, bean.id desc");
                break;
            case 7:
                paramFinder.append(" order by bean.salePrice asc,bean.id desc");
                break;
            case 8:
                paramFinder.append(" order by bean.liRun desc");
                break;
            default:
                paramFinder.append(" order by bean.id desc");
        }
    }

    private Finder _$1(Long paramLong1, String paramString1, String paramString2, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5, Long paramLong2, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, boolean paramBoolean) {
        Finder localFinder = Finder.create("select bean from Product bean");
        localFinder.append(" where bean.website.id=:webId");
        if (!StringUtils.isBlank(paramString1)) {
            localFinder.append(" and bean.name like:productName");
            localFinder.setParam("productName", "%" + paramString1 + "%");
        }
        if (!StringUtils.isBlank(paramString2)) {
            localFinder.append(" and bean.brand.name like:brandName");
            localFinder.setParam("brandName", "%" + paramString2 + "%");
        }
        if (paramBoolean1 != null) {
            localFinder.append(" and bean.onSale=:isOnSale");
            localFinder.setParam("isOnSale", paramBoolean1);
        }
        if (paramBoolean2 != null) {
            localFinder.append(" and bean.recommend=:isRecommend");
            localFinder.setParam("isRecommend", paramBoolean2);
        }
        if (paramBoolean3 != null) {
            localFinder.append(" and bean.special=:isSpecial");
            localFinder.setParam("isSpecial", paramBoolean3);
        }
        if (paramBoolean4 != null) {
            localFinder.append(" and bean.hotsale=:isHotsale");
            localFinder.setParam("isHotsale", paramBoolean4);
        }
        if (paramBoolean5 != null) {
            localFinder.append(" and bean.newProduct=:isNewProduct");
            localFinder.setParam("isNewProduct", paramBoolean5);
        }
        if (paramLong2 != null) {
            localFinder.append(" and bean.type.id=:typeId");
            localFinder.setParam("typeId", paramLong2);
        }
        if (paramDouble1 != null) {
            localFinder.append(" and bean.salePrice>:startSalePrice");
            localFinder.setParam("startSalePrice", paramDouble1);
        }
        if (paramDouble2 != null) {
            localFinder.append(" and bean.salePrice<:endSalePrice");
            localFinder.setParam("endSalePrice", paramDouble2);
        }
        if (paramInteger1 != null) {
            localFinder.append(" and bean.stockCount>:startStock");
            localFinder.setParam("startStock", paramInteger1);
        }
        if (paramInteger2 != null) {
            localFinder.append(" and bean.stockCount<:endStock");
            localFinder.setParam("endStock", paramInteger2);
        }
        localFinder.append(" order by bean.id desc");
        localFinder.setParam("webId", paramLong1);
        localFinder.setCacheable(paramBoolean);
        return localFinder;
    }

    public List<Product> getIsRecommend(Boolean paramBoolean, int paramInt) {
        Finder localFinder = Finder.create("from Product bean where bean.recommend=:recommend").setParam("recommend", paramBoolean);
        localFinder.setMaxResults(paramInt);
        return find(localFinder);
    }

    private Finder _$1(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, boolean paramBoolean) {
        Finder localFinder = Finder.create("from Product bean");
        localFinder.append(" where bean.website.id=:webId and bean.onSale=true");
        if (paramBoolean1 != null) {
            localFinder.append(" and bean.recommend=:recommend");
            localFinder.setParam("recommend", paramBoolean1);
        }
        if (paramBoolean2 != null) {
            localFinder.append(" and bean.special=:special");
            localFinder.setParam("special", paramBoolean2);
        }
        if (paramBoolean3 != null) {
            localFinder.append(" and bean.hotsale=:hotsale");
            localFinder.setParam("hotsale", paramBoolean3);
        }
        if (paramBoolean4 != null) {
            localFinder.append(" and bean.newProduct=:newProduct");
            localFinder.setParam("newProduct", paramBoolean4);
        }
        localFinder.append(" order by bean.id desc");
        localFinder.setParam("webId", paramLong);
        localFinder.setCacheable(paramBoolean);
        return localFinder;
    }

    private Finder _$1(Long paramLong1, Long paramLong2, Boolean paramBoolean1, Boolean paramBoolean2, boolean paramBoolean) {
        Finder localFinder = Finder.create("select bean from Product bean");
        localFinder.append(" inner join bean.tags tag with tag.id=:tagId");
        localFinder.append(" where bean.onSale=true");
        localFinder.setParam("tagId", paramLong1);
        if (paramLong2 != null) {
            localFinder.append(" and bean.category.id=:ctgId");
            localFinder.setParam("ctgId", paramLong2);
        }
        if (paramBoolean1 != null) {
            localFinder.append(" and bean.recommend=:recommend");
            localFinder.setParam("recommend", paramBoolean1);
        }
        if (paramBoolean2 != null) {
            localFinder.append(" and bean.special=:special");
            localFinder.setParam("special", paramBoolean2);
        }
        localFinder.append(" order by bean.id desc");
        localFinder.setCacheable(paramBoolean);
        return localFinder;
    }

    public int luceneWriteIndex(IndexWriter paramIndexWriter, Long paramLong, Date paramDate1, Date paramDate2)
            throws CorruptIndexException, IOException {
        Session localSession = getSession();
        Finder localFinder = Finder.create("from Product bean where 1=1");
        if (paramLong != null) {
            localFinder.append(" and bean.website.id=:webId");
            localFinder.setParam("webId", paramLong);
        }
        if (paramDate1 != null) {
            localFinder.append(" and bean.createTime >= :start");
            localFinder.setParam("start", paramDate1);
        }
        if (paramDate2 != null) {
            localFinder.append(" and bean.createTime >= :end");
            localFinder.setParam("end", paramDate2);
        }
        ScrollableResults localScrollableResults = localFinder.createQuery(localSession).setCacheMode(CacheMode.IGNORE).scroll(ScrollMode.FORWARD_ONLY);
        int i = 0;
        while (localScrollableResults.next()) {
            Product localProduct = (Product) localScrollableResults.get(0);
            paramIndexWriter.addDocument(LuceneProduct.createDocument(localProduct));
            i++;
            if (i % 20 == 0)
                localSession.clear();
        }
        return i;
    }

    public int deleteTagAssociation(ProductTag[] paramArrayOfProductTag) {
        Long[] arrayOfLong = new Long[paramArrayOfProductTag.length];
        int i = 0;
        int j = paramArrayOfProductTag.length;
        while (i < j) {
            arrayOfLong[i] = paramArrayOfProductTag[i].getId();
            i++;
        }
        Session localSession = getSession();
        String str = "from Product bean inner join bean.tags tag where tag.id in (:tagIds)";
        ScrollableResults localScrollableResults = localSession.createQuery(str).setParameterList("tagIds", arrayOfLong).setCacheMode(CacheMode.IGNORE).scroll(ScrollMode.FORWARD_ONLY);
        int k = 0;
        while (localScrollableResults.next()) {
            Product localProduct = (Product) localScrollableResults.get(0);
            localProduct.getTags().removeAll(Arrays.asList(paramArrayOfProductTag));
            k++;
            if (k % 20 == 0) {
                localSession.flush();
                localSession.clear();
            }
        }
        return k;
    }

    public Integer[] getProductByTag(Long paramLong) {
        Long localLong1 = (Long) getSession().createQuery("select count(*) from Product bean where bean.website.id=:webId  and bean.newProduct=true").setParameter("webId", paramLong).uniqueResult();
        Long localLong2 = (Long) getSession().createQuery("select count(*) from Product bean where bean.website.id=:webId  and bean.hotsale=true").setParameter("webId", paramLong).uniqueResult();
        Long localLong3 = (Long) getSession().createQuery("select count(*) from Product bean where bean.website.id=:webId   and bean.special=true").setParameter("webId", paramLong).uniqueResult();
        Integer[] arrayOfInteger = new Integer[3];
        arrayOfInteger[0] = Integer.valueOf(localLong1.intValue());
        arrayOfInteger[1] = Integer.valueOf(localLong2.intValue());
        arrayOfInteger[2] = Integer.valueOf(localLong3.intValue());
        return arrayOfInteger;
    }

    public Product findById(Long paramLong) {
        Product localProduct = (Product) get(paramLong);
        return localProduct;
    }

    public Product save(Product paramProduct) {
        getSession().save(paramProduct);
        return paramProduct;
    }

    public Product deleteById(Long paramLong) {
        Product localProduct = (Product) super.get(paramLong);
        if (localProduct != null)
            getSession().delete(localProduct);
        return localProduct;
    }

    protected Class<Product> getEntityClass() {
        return Product.class;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.dao.impl.ProductDaoImpl
 * JD-Core Version:    0.6.2
 */