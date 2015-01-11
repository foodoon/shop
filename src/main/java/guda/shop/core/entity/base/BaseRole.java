package guda.shop.core.entity.base;

import guda.shop.core.entity.Role;

import java.io.Serializable;
import java.util.Set;

public abstract class BaseRole
        implements Serializable {
    public static String REF = "Role";
    public static String PROP_NAME = "name";
    public static String PROP_ID = "id";
    public static String PROP_SUPER = "super";
    public static String PROP_PRIORITY = "priority";
    private int _$6 = -2147483648;
    private Integer _$5;
    private String _$4;
    private Integer _$3;
    private Boolean _$2;
    private Set<String> _$1;

    public BaseRole() {
        initialize();
    }

    public BaseRole(Integer paramInteger) {
        setId(paramInteger);
        initialize();
    }

    public BaseRole(Integer paramInteger1, String paramString, Integer paramInteger2, Boolean paramBoolean) {
        setId(paramInteger1);
        setName(paramString);
        setPriority(paramInteger2);
        setSuper(paramBoolean);
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

    public String getName() {
        return this._$4;
    }

    public void setName(String paramString) {
        this._$4 = paramString;
    }

    public Integer getPriority() {
        return this._$3;
    }

    public void setPriority(Integer paramInteger) {
        this._$3 = paramInteger;
    }

    public Boolean getSuper() {
        return this._$2;
    }

    public void setSuper(Boolean paramBoolean) {
        this._$2 = paramBoolean;
    }

    public Set<String> getPerms() {
        return this._$1;
    }

    public void setPerms(Set<String> paramSet) {
        this._$1 = paramSet;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Role))
            return false;
        Role localRole = (Role) paramObject;
        if ((null == getId()) || (null == localRole.getId()))
            return false;
        return getId().equals(localRole.getId());
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

