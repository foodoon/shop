package guda.shop.cms.entity.base;

import guda.shop.cms.entity.CustomerService;

import java.io.Serializable;

public abstract class BaseCustomerService
        implements Serializable {
    public static String REF = "CustomerService";
    public static String PROP_NAME = "name";
    public static String PROP_DISABLE = "disable";
    public static String PROP_ID = "id";
    public static String PROP_CONTENT = "content";
    public static String PROP_PRIORITY = "priority";
    private int _$6 = -2147483648;
    private Long _$5;
    private String _$4;
    private String _$3;
    private Integer _$2;
    private Boolean _$1;

    public BaseCustomerService() {
        initialize();
    }

    public BaseCustomerService(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseCustomerService(Long paramLong, String paramString1, String paramString2, Integer paramInteger, Boolean paramBoolean) {
        setId(paramLong);
        setName(paramString1);
        setContent(paramString2);
        setPriority(paramInteger);
        setDisable(paramBoolean);
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

    public String getName() {
        return this._$4;
    }

    public void setName(String paramString) {
        this._$4 = paramString;
    }

    public String getContent() {
        return this._$3;
    }

    public void setContent(String paramString) {
        this._$3 = paramString;
    }

    public Integer getPriority() {
        return this._$2;
    }

    public void setPriority(Integer paramInteger) {
        this._$2 = paramInteger;
    }

    public Boolean getDisable() {
        return this._$1;
    }

    public void setDisable(Boolean paramBoolean) {
        this._$1 = paramBoolean;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof CustomerService))
            return false;
        CustomerService localCustomerService = (CustomerService) paramObject;
        if ((null == getId()) || (null == localCustomerService.getId()))
            return false;
        return getId().equals(localCustomerService.getId());
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

