package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Brand;
import guda.shop.cms.entity.BrandText;

import java.io.Serializable;

public abstract class BaseBrandText
        implements Serializable {
    public static String REF = "BrandText";
    public static String PROP_BRAND = "brand";
    public static String PROP_TEXT = "text";
    public static String PROP_ID = "id";
    private int _$4 = -2147483648;
    private Long _$3;
    private String _$2;
    private Brand _$1;

    public BaseBrandText() {
        initialize();
    }

    public BaseBrandText(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$3;
    }

    public void setId(Long paramLong) {
        this._$3 = paramLong;
        this._$4 = -2147483648;
    }

    public String getText() {
        return this._$2;
    }

    public void setText(String paramString) {
        this._$2 = paramString;
    }

    public Brand getBrand() {
        return this._$1;
    }

    public void setBrand(Brand paramBrand) {
        this._$1 = paramBrand;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof BrandText))
            return false;
        BrandText localBrandText = (BrandText) paramObject;
        if ((null == getId()) || (null == localBrandText.getId()))
            return false;
        return getId().equals(localBrandText.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$4) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$4 = str.hashCode();
        }
        return this._$4;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseBrandText
 * JD-Core Version:    0.6.2
 */