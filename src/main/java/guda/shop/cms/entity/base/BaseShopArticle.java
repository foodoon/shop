package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ShopArticle;
import guda.shop.cms.entity.ShopArticleContent;
import guda.shop.cms.entity.ShopChannel;
import guda.shop.core.entity.Website;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseShopArticle
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "ShopArticle";
    public static String PROP_ARTICLE_CONTENT = "articleContent";
    public static String PROP_PUBLISH_TIME = "publishTime";
    public static String PROP_LINK = "link";
    public static String PROP_WEBSITE = "website";
    public static String PROP_PARAM3 = "param3";
    public static String PROP_DISABLED = "disabled";
    public static String PROP_CHANNEL = "channel";
    public static String PROP_TITLE = "title";
    public static String PROP_PARAM2 = "param2";
    public static String PROP_ID = "id";
    private int _$11 = -2147483648;
    private Long _$10;
    private String _$9;
    private Date _$8;
    private Boolean _$7;
    private String _$6;
    private String _$5;
    private String _$4;
    private ShopArticleContent _$3;
    private Website _$2;
    private ShopChannel _$1;

    public BaseShopArticle() {
        initialize();
    }

    public BaseShopArticle(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseShopArticle(Long paramLong, Website paramWebsite, ShopChannel paramShopChannel, String paramString, Date paramDate, Boolean paramBoolean) {
        setId(paramLong);
        setWebsite(paramWebsite);
        setChannel(paramShopChannel);
        setTitle(paramString);
        setPublishTime(paramDate);
        setDisabled(paramBoolean);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$10;
    }

    public void setId(Long paramLong) {
        this._$10 = paramLong;
        this._$11 = -2147483648;
    }

    public String getTitle() {
        return this._$9;
    }

    public void setTitle(String paramString) {
        this._$9 = paramString;
    }

    public Date getPublishTime() {
        return this._$8;
    }

    public void setPublishTime(Date paramDate) {
        this._$8 = paramDate;
    }

    public Boolean getDisabled() {
        return this._$7;
    }

    public void setDisabled(Boolean paramBoolean) {
        this._$7 = paramBoolean;
    }

    public String getLink() {
        return this._$6;
    }

    public void setLink(String paramString) {
        this._$6 = paramString;
    }

    public String getParam2() {
        return this._$5;
    }

    public void setParam2(String paramString) {
        this._$5 = paramString;
    }

    public String getParam3() {
        return this._$4;
    }

    public void setParam3(String paramString) {
        this._$4 = paramString;
    }

    public ShopArticleContent getArticleContent() {
        return this._$3;
    }

    public void setArticleContent(ShopArticleContent paramShopArticleContent) {
        this._$3 = paramShopArticleContent;
    }

    public Website getWebsite() {
        return this._$2;
    }

    public void setWebsite(Website paramWebsite) {
        this._$2 = paramWebsite;
    }

    public ShopChannel getChannel() {
        return this._$1;
    }

    public void setChannel(ShopChannel paramShopChannel) {
        this._$1 = paramShopChannel;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof ShopArticle))
            return false;
        ShopArticle localShopArticle = (ShopArticle) paramObject;
        if ((null == getId()) || (null == localShopArticle.getId()))
            return false;
        return getId().equals(localShopArticle.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$11) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$11 = str.hashCode();
        }
        return this._$11;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopArticle
 * JD-Core Version:    0.6.2
 */