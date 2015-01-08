package guda.shop.cms.entity;

import java.util.Collection;
import java.util.Date;

public abstract interface ProductInfo {
    public abstract Long getId();

    public abstract String getName();

    public abstract Collection<String> getCategoryNameArray();

    public abstract Collection<Long> getCategoryIdArray();

    public abstract String getBrandName();

    public abstract String getUrl();

    public abstract Collection<String> getKeywordArray();

    public abstract Collection<String> getTagArray();

    public abstract String getDetailImgUrl();

    public abstract String getListImgUrl();

    public abstract String getMinImgUrl();

    public abstract Double getMarketPrice();

    public abstract Double getSalePrice();

    public abstract Date getCreateTime();
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ProductInfo
 * JD-Core Version:    0.6.2
 */