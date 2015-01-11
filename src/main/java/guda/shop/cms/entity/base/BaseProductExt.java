package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Product;
import guda.shop.cms.entity.ProductExt;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseProductExt
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "ProductExt";
    public static String PROP_SECKILLPRICE = "seckillprice";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_PRODUCT = "product";
    public static String PROP_DETAIL_IMG = "detailImg";
    public static String PROP_MTITLE = "mtitle";
    public static String PROP_MIN_IMG = "minImg";
    public static String PROP_MDESCRIPTION = "mdescription";
    public static String PROP_LIST_IMG = "listImg";
    public static String PROP_UNIT = "unit";
    public static String PROP_PRODUCT_IMG_DESC = "productImgDesc";
    public static String PROP_PRODUCT_IMGS = "productImgs";
    public static String PROP_TIME_LIMIT = "timeLimit";
    public static String PROP_WEIGHT = "weight";
    public static String PROP_PRODUCT_PROPERTY = "productProperty";
    public static String PROP_ID = "id";
    public static String PROP_ISLIMIT_TIME = "islimitTime";
    public static String PROP_MKEYWORDS = "mkeywords";
    private int _$18 = -2147483648;
    private Long _$17;
    private Long _$16;
    private String _$15;
    private String _$14;
    private String _$13;
    private String _$12;
    private String _$11;
    private String _$10;
    private String _$9;
    private Integer _$8;
    private String _$7;
    private Boolean _$6;
    private Date _$5;
    private Double _$4;
    private String _$3;
    private String _$2;
    private Product _$1;

    public BaseProductExt() {
        initialize();
    }

    public BaseProductExt(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseProductExt(Long paramLong, Integer paramInteger, String paramString) {
        setId(paramLong);
        setWeight(paramInteger);
        setUnit(paramString);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$17;
    }

    public void setId(Long paramLong) {
        this._$17 = paramLong;
        this._$18 = -2147483648;
    }

    public Long getCode() {
        return this._$16;
    }

    public void setCode(Long paramLong) {
        this._$16 = paramLong;
    }

    public String getMtitle() {
        return this._$15;
    }

    public void setMtitle(String paramString) {
        this._$15 = paramString;
    }

    public String getMkeywords() {
        return this._$14;
    }

    public void setMkeywords(String paramString) {
        this._$14 = paramString;
    }

    public String getMdescription() {
        return this._$13;
    }

    public void setMdescription(String paramString) {
        this._$13 = paramString;
    }

    public String getDetailImg() {
        return this._$12;
    }

    public void setDetailImg(String paramString) {
        this._$12 = paramString;
    }

    public String getListImg() {
        return this._$11;
    }

    public void setListImg(String paramString) {
        this._$11 = paramString;
    }

    public String getMinImg() {
        return this._$10;
    }

    public void setMinImg(String paramString) {
        this._$10 = paramString;
    }

    public String getCoverImg() {
        return this._$9;
    }

    public void setCoverImg(String paramString) {
        this._$9 = paramString;
    }

    public Integer getWeight() {
        return this._$8;
    }

    public void setWeight(Integer paramInteger) {
        this._$8 = paramInteger;
    }

    public String getUnit() {
        return this._$7;
    }

    public void setUnit(String paramString) {
        this._$7 = paramString;
    }

    public Boolean getIslimitTime() {
        return this._$6;
    }

    public void setIslimitTime(Boolean paramBoolean) {
        this._$6 = paramBoolean;
    }

    public Date getTimeLimit() {
        return this._$5;
    }

    public void setTimeLimit(Date paramDate) {
        this._$5 = paramDate;
    }

    public Double getSeckillprice() {
        return this._$4;
    }

    public void setSeckillprice(Double paramDouble) {
        this._$4 = paramDouble;
    }

    public String getProductImgs() {
        return this._$3;
    }

    public void setProductImgs(String paramString) {
        this._$3 = paramString;
    }

    public String getProductImgDesc() {
        return this._$2;
    }

    public void setProductImgDesc(String paramString) {
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
        if (!(paramObject instanceof ProductExt))
            return false;
        ProductExt localProductExt = (ProductExt) paramObject;
        if ((null == getId()) || (null == localProductExt.getId()))
            return false;
        return getId().equals(localProductExt.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$18) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$18 = str.hashCode();
        }
        return this._$18;
    }

    public String toString() {
        return super.toString();
    }
}

