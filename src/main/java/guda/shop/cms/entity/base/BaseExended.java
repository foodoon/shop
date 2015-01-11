package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Exended;
import guda.shop.cms.entity.ExendedItem;
import guda.shop.cms.entity.ProductType;

import java.io.Serializable;
import java.util.Set;

public abstract class BaseExended
        implements Serializable {
    public static String REF = "Exended";
    public static String PROP_NAME = "name";
    public static String PROP_DATA_TYPE = "dataType";
    public static String PROP_FIELD = "field";
    public static String PROP_ID = "id";
    public static String PROP_PRIORITY = "priority";
    private int _$8 = -2147483648;
    private Long _$7;
    private String _$6;
    private String _$5;
    private Byte _$4;
    private Integer _$3;
    private Set<ProductType> _$2;
    private Set<ExendedItem> _$1;

    public BaseExended() {
        initialize();
    }

    public BaseExended(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseExended(Long paramLong, String paramString1, String paramString2) {
        setId(paramLong);
        setName(paramString1);
        setField(paramString2);
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

    public String getField() {
        return this._$5;
    }

    public void setField(String paramString) {
        this._$5 = paramString;
    }

    public Byte getDataType() {
        return this._$4;
    }

    public void setDataType(Byte paramByte) {
        this._$4 = paramByte;
    }

    public Integer getPriority() {
        return this._$3;
    }

    public void setPriority(Integer paramInteger) {
        this._$3 = paramInteger;
    }

    public Set<ProductType> getProductTypes() {
        return this._$2;
    }

    public void setProductTypes(Set<ProductType> paramSet) {
        this._$2 = paramSet;
    }

    public Set<ExendedItem> getItems() {
        return this._$1;
    }

    public void setItems(Set<ExendedItem> paramSet) {
        this._$1 = paramSet;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Exended))
            return false;
        Exended localExended = (Exended) paramObject;
        if ((null == getId()) || (null == localExended.getId()))
            return false;
        return getId().equals(localExended.getId());
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

