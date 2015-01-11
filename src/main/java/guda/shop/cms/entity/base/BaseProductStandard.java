package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductStandard;
import guda.shop.cms.entity.Standard;
import guda.shop.cms.entity.StandardType;

import java.io.Serializable;

public abstract class BaseProductStandard
        implements Serializable {
    public static String REF = "ProductStandard";
    public static String PROP_STANDARD = "standard";
    public static String PROP_DATA_TYPE = "dataType";
    public static String PROP_PRODUCT = "product";
    public static String PROP_TYPE = "type";
    public static String PROP_ID = "id";
    public static String PROP_IMG_PATH = "imgPath";
    private int _$7 = -2147483648;
    private Long _$6;
    private String _$5;
    private boolean _$4;
    private Product _$3;
    private Standard _$2;
    private StandardType _$1;

    public BaseProductStandard() {
        initialize();
    }

    public BaseProductStandard(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseProductStandard(Long paramLong, Product paramProduct, Standard paramStandard, StandardType paramStandardType, String paramString, boolean paramBoolean) {
        setId(paramLong);
        setProduct(paramProduct);
        setStandard(paramStandard);
        setType(paramStandardType);
        setImgPath(paramString);
        setDataType(paramBoolean);
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

    public String getImgPath() {
        return this._$5;
    }

    public void setImgPath(String paramString) {
        this._$5 = paramString;
    }

    public boolean isDataType() {
        return this._$4;
    }

    public void setDataType(boolean paramBoolean) {
        this._$4 = paramBoolean;
    }

    public Product getProduct() {
        return this._$3;
    }

    public void setProduct(Product paramProduct) {
        this._$3 = paramProduct;
    }

    public Standard getStandard() {
        return this._$2;
    }

    public void setStandard(Standard paramStandard) {
        this._$2 = paramStandard;
    }

    public StandardType getType() {
        return this._$1;
    }

    public void setType(StandardType paramStandardType) {
        this._$1 = paramStandardType;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof ProductStandard))
            return false;
        ProductStandard localProductStandard = (ProductStandard) paramObject;
        if ((null == getId()) || (null == localProductStandard.getId()))
            return false;
        return getId().equals(localProductStandard.getId());
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

