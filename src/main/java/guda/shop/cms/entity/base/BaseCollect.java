package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Collect;
import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductFashion;
import guda.shop.cms.entity.ShopMember;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseCollect
        implements Serializable {
    public static String REF = "Collect";
    public static String PROP_MEMBER = "member";
    public static String PROP_TIME = "time";
    public static String PROP_PRODUCT = "product";
    public static String PROP_FASHION = "fashion";
    public static String PROP_ID = "id";
    private int _$6 = -2147483648;
    private Integer _$5;
    private Date _$4;
    private ShopMember _$3;
    private Product _$2;
    private ProductFashion _$1;

    public BaseCollect() {
        initialize();
    }

    public BaseCollect(Integer paramInteger) {
        setId(paramInteger);
        initialize();
    }

    public BaseCollect(Integer paramInteger, ShopMember paramShopMember, Product paramProduct) {
        setId(paramInteger);
        setMember(paramShopMember);
        setProduct(paramProduct);
        initialize();
    }

    protected void initialize() {
    }

    public Integer getId() {
        return this._$5;
    }

    public void setId(Integer paramInteger) {
        this._$5 = paramInteger;
        this._$6 = -2147483648;
    }

    public Date getTime() {
        return this._$4;
    }

    public void setTime(Date paramDate) {
        this._$4 = paramDate;
    }

    public ShopMember getMember() {
        return this._$3;
    }

    public void setMember(ShopMember paramShopMember) {
        this._$3 = paramShopMember;
    }

    public Product getProduct() {
        return this._$2;
    }

    public void setProduct(Product paramProduct) {
        this._$2 = paramProduct;
    }

    public ProductFashion getFashion() {
        return this._$1;
    }

    public void setFashion(ProductFashion paramProductFashion) {
        this._$1 = paramProductFashion;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Collect))
            return false;
        Collect localCollect = (Collect) paramObject;
        if ((null == getId()) || (null == localCollect.getId()))
            return false;
        return getId().equals(localCollect.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseCollect
 * JD-Core Version:    0.6.2
 */