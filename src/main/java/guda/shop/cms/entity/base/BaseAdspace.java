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
    private int hash = -2147483648;
    private Integer id;
    private String name;
    private String description;
    private Boolean enabled;

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
        return this.id;
    }

    public void setId(Integer paramInteger) {
        this.id = paramInteger;
        this.hash = -2147483648;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String paramString) {
        this.description = paramString;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean paramBoolean) {
        this.enabled = paramBoolean;
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
        if (-2147483648 == this.hash) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this.hash = str.hashCode();
        }
        return this.hash;
    }

    public String toString() {
        return super.toString();
    }
}

