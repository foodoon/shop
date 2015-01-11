package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ProductTag;
import guda.shop.core.entity.Website;

import java.io.Serializable;

public abstract class BaseProductTag
        implements Serializable {
    public static String REF = "ProductTag";
    public static String PROP_WEBSITE = "website";
    public static String PROP_NAME = "name";
    public static String PROP_ID = "id";
    public static String PROP_COUNT = "count";
    private int _$5 = -2147483648;
    private Long _$4;
    private String _$3;
    private Integer _$2;
    private Website _$1;

    public BaseProductTag() {
        initialize();
    }

    public BaseProductTag(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseProductTag(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger) {
        setId(paramLong);
        setWebsite(paramWebsite);
        setName(paramString);
        setCount(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$4;
    }

    public void setId(Long paramLong) {
        this._$4 = paramLong;
        this._$5 = -2147483648;
    }

    public String getName() {
        return this._$3;
    }

    public void setName(String paramString) {
        this._$3 = paramString;
    }

    public Integer getCount() {
        return this._$2;
    }

    public void setCount(Integer paramInteger) {
        this._$2 = paramInteger;
    }

    public Website getWebsite() {
        return this._$1;
    }

    public void setWebsite(Website paramWebsite) {
        this._$1 = paramWebsite;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof ProductTag))
            return false;
        ProductTag localProductTag = (ProductTag) paramObject;
        if ((null == getId()) || (null == localProductTag.getId()))
            return false;
        return getId().equals(localProductTag.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$5) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$5 = str.hashCode();
        }
        return this._$5;
    }

    public String toString() {
        return super.toString();
    }
}

