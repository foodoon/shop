package guda.shop.cms.dao;

import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductTag;
import guda.shop.common.hibernate3.Updater;
import guda.shop.common.page.Pagination;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public abstract interface ProductDao {
    public abstract List<Product> getList(Long paramLong1, Long paramLong2, String paramString, boolean paramBoolean);

    public abstract Pagination getPage(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);

    public abstract Pagination getPageByCategory(Long paramLong1, String paramString1, String paramString2, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5, Long paramLong2, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract Pagination getPageByWebsite(Long paramLong1, String paramString1, String paramString2, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5, Long paramLong2, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract Pagination getPageByTag(Long paramLong1, Long paramLong2, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract Pagination getPageByStockWarning(Long paramLong, Integer paramInteger, Boolean paramBoolean, int paramInt1, int paramInt2);

    public abstract List<Product> getListByCategory(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract List<Product> getListByWebsite(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract List<Product> getListByTag(Long paramLong1, Long paramLong2, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract int luceneWriteIndex(IndexWriter paramIndexWriter, Long paramLong, Date paramDate1, Date paramDate2)
            throws CorruptIndexException, IOException;

    public abstract List<Product> getListByWebsite1(Long paramLong, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, int paramInt1, int paramInt2, boolean paramBoolean);

    public abstract int deleteTagAssociation(ProductTag[] paramArrayOfProductTag);

    public abstract Product findById(Long paramLong);

    public abstract Product save(Product paramProduct);

    public abstract Product updateByUpdater(Updater<Product> paramUpdater);

    public abstract Product deleteById(Long paramLong);

    public abstract Pagination getPageByCategoryChannel(Long paramLong1, Long paramLong2, Boolean paramBoolean1, String[] paramArrayOfString1, String[] paramArrayOfString2, Boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);

    public abstract List<Product> getIsRecommend(Boolean paramBoolean, int paramInt);

    public abstract Integer[] getProductByTag(Long paramLong);

    public abstract List<Product> getHistoryProduct(HashSet<Long> paramHashSet, Integer paramInteger);
}

