package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Brand;
import guda.shop.cms.entity.BrandText;
import guda.shop.cms.entity.Category;
import guda.shop.core.entity.Website;

import java.io.Serializable;
import java.util.Set;

public abstract class BaseBrand
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "Brand";
    public static String PROP_NAME = "name";
    public static String PROP_ALIAS = "alias";
    public static String PROP_WEBSITE = "website";
    public static String PROP_ID = "id";
    public static String PROP_WEB_URL = "webUrl";
    public static String PROP_SIFT = "sift";
    public static String PROP_PRIORITY = "priority";
    public static String PROP_LOGO_PATH = "logoPath";
    private int _$13 = -2147483648;
    private Long _$12;
    private String _$11;
    private String _$10;
    private String _$9;
    private String _$8;
    private Integer _$7;
    private Boolean _$6;
    private Boolean _$5;
    private String _$4;
    private Website _$3;
    private Set<Category> _$2;
    private Set<BrandText> _$1;

    public BaseBrand() {
        initialize();
    }

    public BaseBrand(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseBrand(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger) {
        setId(paramLong);
        setWebsite(paramWebsite);
        setName(paramString);
        setPriority(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$12;
    }

    public void setId(Long paramLong) {
        this._$12 = paramLong;
        this._$13 = -2147483648;
    }

    public Boolean getDisabled() {
        return this._$5;
    }

    public void setDisabled(Boolean paramBoolean) {
        this._$5 = paramBoolean;
    }

    public String getFirstCharacter() {
        return this._$4;
    }

    public void setFirstCharacter(String paramString) {
        this._$4 = paramString;
    }

    public String getName() {
        return this._$11;
    }

    public void setName(String paramString) {
        this._$11 = paramString;
    }

    public String getAlias() {
        return this._$10;
    }

    public void setAlias(String paramString) {
        this._$10 = paramString;
    }

    public String getWebUrl() {
        return this._$9;
    }

    public void setWebUrl(String paramString) {
        this._$9 = paramString;
    }

    public String getLogoPath() {
        return this._$8;
    }

    public void setLogoPath(String paramString) {
        this._$8 = paramString;
    }

    public Integer getPriority() {
        return this._$7;
    }

    public void setPriority(Integer paramInteger) {
        this._$7 = paramInteger;
    }

    public Boolean getSift() {
        return this._$6;
    }

    public void setSift(Boolean paramBoolean) {
        this._$6 = paramBoolean;
    }

    public Website getWebsite() {
        return this._$3;
    }

    public void setWebsite(Website paramWebsite) {
        this._$3 = paramWebsite;
    }

    public Set<Category> getCategorys() {
        return this._$2;
    }

    public void setCategorys(Set<Category> paramSet) {
        this._$2 = paramSet;
    }

    public Set<BrandText> getBrandTextSet() {
        return this._$1;
    }

    public void setBrandTextSet(Set<BrandText> paramSet) {
        this._$1 = paramSet;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Brand))
            return false;
        Brand localBrand = (Brand) paramObject;
        if ((null == getId()) || (null == localBrand.getId()))
            return false;
        return getId().equals(localBrand.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$13) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$13 = str.hashCode();
        }
        return this._$13;
    }

    public String toString() {
        return super.toString();
    }
}

