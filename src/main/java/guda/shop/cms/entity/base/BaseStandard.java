package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ProductFashion;
import guda.shop.cms.entity.Standard;
import guda.shop.cms.entity.StandardType;

import java.io.Serializable;
import java.util.Set;

public abstract class BaseStandard
        implements Serializable {
    public static String REF = "Standard";
    public static String PROP_NAME = "name";
    public static String PROP_TYPE = "type";
    public static String PROP_ID = "id";
    public static String PROP_PRIORITY = "priority";
    public static String PROP_COLOR = "color";
    private int _$7 = -2147483648;
    private Long _$6;
    private String _$5;
    private String _$4;
    private Integer _$3;
    private StandardType _$2;
    private Set<ProductFashion> _$1;

    public BaseStandard() {
        initialize();
    }

    public BaseStandard(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseStandard(Long paramLong, StandardType paramStandardType, String paramString) {
        setId(paramLong);
        setType(paramStandardType);
        setName(paramString);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$6;
    }

    public void setId(Long paramLong) {
        this._$6 = paramLong;
        this._$7 = -2147483648;
    }

    public String getName() {
        return this._$5;
    }

    public void setName(String paramString) {
        this._$5 = paramString;
    }

    public String getColor() {
        return this._$4;
    }

    public void setColor(String paramString) {
        this._$4 = paramString;
    }

    public Integer getPriority() {
        return this._$3;
    }

    public void setPriority(Integer paramInteger) {
        this._$3 = paramInteger;
    }

    public StandardType getType() {
        return this._$2;
    }

    public void setType(StandardType paramStandardType) {
        this._$2 = paramStandardType;
    }

    public Set<ProductFashion> getFashions() {
        return this._$1;
    }

    public void setFashions(Set<ProductFashion> paramSet) {
        this._$1 = paramSet;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Standard))
            return false;
        Standard localStandard = (Standard) paramObject;
        if ((null == getId()) || (null == localStandard.getId()))
            return false;
        return getId().equals(localStandard.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$7) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$7 = str.hashCode();
        }
        return this._$7;
    }

    public String toString() {
        return super.toString();
    }
}

