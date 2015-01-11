package guda.shop.cms.entity.base;

import guda.shop.cms.entity.PopularityGroup;
import guda.shop.cms.entity.Product;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public abstract class BasePopularityGroup
        implements Serializable {
    public static String REF = "PopularityGroup";
    public static String PROP_NAME = "name";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_BEGIN_TIME = "beginTime";
    public static String PROP_PRICE = "price";
    public static String PROP_ID = "id";
    public static String PROP_END_TIME = "endTime";
    private int _$8 = -2147483648;
    private Long _$7;
    private String _$6;
    private Date _$5;
    private Date _$4;
    private Double _$3;
    private String _$2;
    private Set<Product> _$1;

    public BasePopularityGroup() {
        initialize();
    }

    public BasePopularityGroup(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BasePopularityGroup(Long paramLong, String paramString, Date paramDate1, Date paramDate2, Double paramDouble) {
        setId(paramLong);
        setName(paramString);
        setBeginTime(paramDate1);
        setEndTime(paramDate2);
        setPrice(paramDouble);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$7;
    }

    public void setId(Long paramLong) {
        this._$7 = paramLong;
        this._$8 = -2147483648;
    }

    public String getName() {
        return this._$6;
    }

    public void setName(String paramString) {
        this._$6 = paramString;
    }

    public Date getBeginTime() {
        return this._$5;
    }

    public void setBeginTime(Date paramDate) {
        this._$5 = paramDate;
    }

    public Date getEndTime() {
        return this._$4;
    }

    public void setEndTime(Date paramDate) {
        this._$4 = paramDate;
    }

    public Double getPrice() {
        return this._$3;
    }

    public void setPrice(Double paramDouble) {
        this._$3 = paramDouble;
    }

    public String getDescription() {
        return this._$2;
    }

    public void setDescription(String paramString) {
        this._$2 = paramString;
    }

    public Set<Product> getProducts() {
        return this._$1;
    }

    public void setProducts(Set<Product> paramSet) {
        this._$1 = paramSet;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof PopularityGroup))
            return false;
        PopularityGroup localPopularityGroup = (PopularityGroup) paramObject;
        if ((null == getId()) || (null == localPopularityGroup.getId()))
            return false;
        return getId().equals(localPopularityGroup.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$8) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$8 = str.hashCode();
        }
        return this._$8;
    }

    public String toString() {
        return super.toString();
    }
}

