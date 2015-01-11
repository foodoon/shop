package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ShopDictionaryType;

import java.io.Serializable;

public abstract class BaseShopDictionaryType
        implements Serializable {
    public static String REF = "ShopDictionaryType";
    public static String PROP_NAME = "name";
    public static String PROP_ID = "id";
    private int _$3 = -2147483648;
    private Long _$2;
    private String _$1;

    public BaseShopDictionaryType() {
        initialize();
    }

    public BaseShopDictionaryType(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$2;
    }

    public void setId(Long paramLong) {
        this._$2 = paramLong;
        this._$3 = -2147483648;
    }

    public String getName() {
        return this._$1;
    }

    public void setName(String paramString) {
        this._$1 = paramString;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof ShopDictionaryType))
            return false;
        ShopDictionaryType localShopDictionaryType = (ShopDictionaryType) paramObject;
        if ((null == getId()) || (null == localShopDictionaryType.getId()))
            return false;
        return getId().equals(localShopDictionaryType.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$3) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$3 = str.hashCode();
        }
        return this._$3;
    }

    public String toString() {
        return super.toString();
    }
}

