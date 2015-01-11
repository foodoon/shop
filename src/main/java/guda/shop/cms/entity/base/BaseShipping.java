package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Logistics;
import guda.shop.cms.entity.Shipping;
import guda.shop.core.entity.Website;

import java.io.Serializable;

public abstract class BaseShipping
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "Shipping";
    public static String PROP_FIRST_WEIGHT = "firstWeight";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_WEBSITE = "website";
    public static String PROP_FIRST_PRICE = "firstPrice";
    public static String PROP_DISABLED = "disabled";
    public static String PROP_UNIFORM_PRICE = "uniformPrice";
    public static String PROP_METHOD = "method";
    public static String PROP_PRIORITY = "priority";
    public static String PROP_ADDITIONAL_WEIGHT = "additionalWeight";
    public static String PROP_NAME = "name";
    public static String PROP_ID = "id";
    public static String PROP_ADDITIONAL_PRICE = "additionalPrice";
    private int _$16 = -2147483648;
    private Long _$15;
    private String _$14;
    private String _$13;
    private Double _$12;
    private Integer _$11;
    private Integer _$10;
    private Double _$9;
    private Double _$8;
    private Integer _$7;
    private Integer _$6;
    private Boolean _$5;
    private Boolean _$4;
    private String _$3;
    private Website _$2;
    private Logistics _$1;

    public BaseShipping() {
        initialize();
    }

    public BaseShipping(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseShipping(Long paramLong, Website paramWebsite, String paramString, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean) {
        setId(paramLong);
        setWebsite(paramWebsite);
        setName(paramString);
        setMethod(paramInteger1);
        setPriority(paramInteger2);
        setDisabled(paramBoolean);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$15;
    }

    public void setId(Long paramLong) {
        this._$15 = paramLong;
        this._$16 = -2147483648;
    }

    public String getName() {
        return this._$14;
    }

    public void setName(String paramString) {
        this._$14 = paramString;
    }

    public String getDescription() {
        return this._$13;
    }

    public void setDescription(String paramString) {
        this._$13 = paramString;
    }

    public Double getUniformPrice() {
        return this._$12;
    }

    public void setUniformPrice(Double paramDouble) {
        this._$12 = paramDouble;
    }

    public Integer getFirstWeight() {
        return this._$11;
    }

    public void setFirstWeight(Integer paramInteger) {
        this._$11 = paramInteger;
    }

    public Integer getAdditionalWeight() {
        return this._$10;
    }

    public void setAdditionalWeight(Integer paramInteger) {
        this._$10 = paramInteger;
    }

    public Boolean getIsDefault() {
        return this._$4;
    }

    public void setIsDefault(Boolean paramBoolean) {
        this._$4 = paramBoolean;
    }

    public Double getFirstPrice() {
        return this._$9;
    }

    public void setFirstPrice(Double paramDouble) {
        this._$9 = paramDouble;
    }

    public Double getAdditionalPrice() {
        return this._$8;
    }

    public void setAdditionalPrice(Double paramDouble) {
        this._$8 = paramDouble;
    }

    public Integer getMethod() {
        return this._$7;
    }

    public void setMethod(Integer paramInteger) {
        this._$7 = paramInteger;
    }

    public Integer getPriority() {
        return this._$6;
    }

    public void setPriority(Integer paramInteger) {
        this._$6 = paramInteger;
    }

    public Boolean getDisabled() {
        return this._$5;
    }

    public void setDisabled(Boolean paramBoolean) {
        this._$5 = paramBoolean;
    }

    public Website getWebsite() {
        return this._$2;
    }

    public void setWebsite(Website paramWebsite) {
        this._$2 = paramWebsite;
    }

    public Logistics getLogistics() {
        return this._$1;
    }

    public void setLogistics(Logistics paramLogistics) {
        this._$1 = paramLogistics;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Shipping))
            return false;
        Shipping localShipping = (Shipping) paramObject;
        if ((null == getId()) || (null == localShipping.getId()))
            return false;
        return getId().equals(localShipping.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$16) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$16 = str.hashCode();
        }
        return this._$16;
    }

    public String toString() {
        return super.toString();
    }

    public String getLogisticsType() {
        return this._$3;
    }

    public void setLogisticsType(String paramString) {
        this._$3 = paramString;
    }
}

