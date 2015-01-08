package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShopArticle;
import guda.shop.core.entity.Website;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

public class ShopArticle extends BaseShopArticle {
    private static final long serialVersionUID = 1L;

    public ShopArticle() {
    }

    public ShopArticle(Long paramLong) {
        super(paramLong);
    }

    public ShopArticle(Long paramLong, Website paramWebsite, ShopChannel paramShopChannel, String paramString, Date paramDate, Boolean paramBoolean) {
        super(paramLong, paramWebsite, paramShopChannel, paramString, paramDate, paramBoolean);
    }

    public String getUrl() {
        if (!StringUtils.isBlank(getLink()))
            return getLink();
        return "/" + getChannel().getPath() + "/" + getId() + getWebsite().getSuffix();
    }

    public String getContent() {
        ShopArticleContent localShopArticleContent = getArticleContent();
        if (localShopArticleContent != null)
            return localShopArticleContent.getContent();
        return null;
    }

    public void init() {
        Date localDate = getPublishTime();
        if (localDate == null)
            setPublishTime(new Timestamp(System.currentTimeMillis()));
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ShopArticle
 * JD-Core Version:    0.6.2
 */