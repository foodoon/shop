package guda.shop.cms.manager;

import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductExt;
import guda.shop.cms.entity.ProductTag;
import guda.shop.common.page.Pagination;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public abstract interface ProductMng {
    public abstract Pagination getPage(int paramInt1, int paramInt2, int paramInt3);

    public abstract List<Product> getList(Long paramLong1, Long paramLong2, String paramString);

    public abstract List<Product> getListForTag(Long paramLong1, Long paramLong2, Long paramLong3, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, int paramInt1, int paramInt2);

    public abstract Pagination getPage(Long paramLong1, Long paramLong2, String paramString1, String paramString2, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5, Long paramLong3, Double paramDouble1, Double paramDouble2, Integer paramInteger1, Integer paramInteger2, int paramInt1, int paramInt2);

    public abstract Pagination getPageByStockWarning(Long paramLong, Integer paramInteger, Boolean paramBoolean, int paramInt1, int paramInt2);

    public abstract Pagination getPageForTag(Long paramLong1, Long paramLong2, Long paramLong3, Boolean paramBoolean1, Boolean paramBoolean2, int paramInt1, int paramInt2);

    public abstract Product findById(Long paramLong);

    public abstract Product updateByUpdater(Product paramProduct);

    public abstract int deleteTagAssociation(ProductTag[] paramArrayOfProductTag);

    public abstract Product save(Product paramProduct, ProductExt paramProductExt, Long paramLong1, Long paramLong2, Long paramLong3, Long[] paramArrayOfLong, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, MultipartFile paramMultipartFile);

    public abstract Product update(Product paramProduct, ProductExt paramProductExt, Long paramLong1, Long paramLong2, Long[] paramArrayOfLong, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, Map<String, String> paramMap, String[] paramArrayOfString4, String[] paramArrayOfString5, String[] paramArrayOfString6, MultipartFile paramMultipartFile);

    public abstract Product update(Product paramProduct);

    public abstract void resetSaleTop();

    public abstract Product[] deleteByIds(Long[] paramArrayOfLong);

    public abstract Pagination getPageForTagChannel(Long paramLong1, Long paramLong2, Long paramLong3, Long paramLong4, Boolean paramBoolean1, String[] paramArrayOfString1, String[] paramArrayOfString2, Boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3);

    public abstract Integer getStoreByProductPattern(Long paramLong1, Long paramLong2);

    public abstract Integer getTotalStore(Long paramLong);

    public abstract List<Product> getIsRecommend(Boolean paramBoolean, int paramInt);

    public abstract Integer[] getProductByTag(Long paramLong);

    public abstract List<Product> getHistoryProduct(HashSet<Long> paramHashSet, Integer paramInteger);

    public abstract void resetProfitTop();
}

