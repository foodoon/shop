package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Adspace;

import java.io.Serializable;

public abstract class BaseAdspace
        implements Serializable {
    public static String REF = "Adspace";
    public static String PROP_NAME = "name";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_ENABLE = "enable";
    public static String PROP_ID = "id";
    private int _$5 = -2147483648;
    private Integer _$4;
    private String _$3;
    private String _$2;
    private Boolean _$1;

    public BaseAdspace() {
        initialize();
    }

    public BaseAdspace(Integer paramInteger) {
        setId(paramInteger);
        initialize();
    }

    protected void initialize() {
    }

    public Integer getId() {
        return this._$4;
    }

    public void setId(Integer paramInteger) {
        this._$4 = paramInteger;
        this._$5 = -2147483648;
    }

    public String getName() {
        return this._$3;
    }

    public void setName(String paramString) {
        this._$3 = paramString;
    }

    public String getDescription() {
        return this._$2;
    }

    public void setDescription(String paramString) {
        this._$2 = paramString;
    }

    public Boolean getEnabled() {
        return this._$1;
    }

    public void setEnabled(Boolean paramBoolean) {
        this._$1 = paramBoolean;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Adspace))
            return false;
        Adspace localAdspace = (Adspace) paramObject;
        if ((null == getId()) || (null == localAdspace.getId()))
            return false;
        return getId().equals(localAdspace.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$5) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$5 = str.hashCode();
        }
        return this._$5;
    }

    public String toString() {
        return super.toString();
    }
}

