package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductText;

import java.io.Serializable;

public abstract class BaseProductText
        implements Serializable {
    public static String REF = "ProductText";
    public static String PROP_TEXT = "text";
    public static String PROP_PRODUCT = "product";
    public static String PROP_TEXT1 = "text1";
    public static String PROP_TEXT2 = "text2";
    public static String PROP_ID = "id";
    private int _$6 = -2147483648;
    private Long _$5;
    private String _$4;
    private String _$3;
    private String _$2;
    private Product _$1;

    public BaseProductText() {
        initialize();
    }

    public BaseProductText(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$5;
    }

    public void setId(Long paramLong) {
        this._$5 = paramLong;
        this._$6 = -2147483648;
    }

    public String getText() {
        return this._$4;
    }

    public void setText(String paramString) {
        this._$4 = paramString;
    }

    public String getText1() {
        return this._$3;
    }

    public void setText1(String paramString) {
        this._$3 = paramString;
    }

    public String getText2() {
        return this._$2;
    }

    public void setText2(String paramString) {
        this._$2 = paramString;
    }

    public Product getProduct() {
        return this._$1;
    }

    public void setProduct(Product paramProduct) {
        this._$1 = paramProduct;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof ProductText))
            return false;
        ProductText localProductText = (ProductText) paramObject;
        if ((null == getId()) || (null == localProductText.getId()))
            return false;
        return getId().equals(localProductText.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$6) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$6 = str.hashCode();
        }
        return this._$6;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseProductText
 * JD-Core Version:    0.6.2
 */