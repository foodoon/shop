package guda.shop.core.entity.base;

import guda.shop.core.entity.Admin;
import guda.shop.core.entity.Role;
import guda.shop.core.entity.User;
import guda.shop.core.entity.Website;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public abstract class BaseAdmin
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "Admin";
    public static String PROP_WEBSITE = "website";
    public static String PROP_CREATE_TIME = "createTime";
    public static String PROP_DISABLED = "disabled";
    public static String PROP_USER = "user";
    public static String PROP_ID = "id";
    private int _$8 = -2147483648;
    private Long _$7;
    private Date _$6;
    private Boolean _$5;
    private Boolean _$4;
    private User _$3;
    private Website _$2;
    private Set<Role> _$1;

    public BaseAdmin() {
        initialize();
    }

    public BaseAdmin(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseAdmin(Long paramLong, User paramUser, Website paramWebsite, Date paramDate, Boolean paramBoolean) {
        setId(paramLong);
        setUser(paramUser);
        setWebsite(paramWebsite);
        setCreateTime(paramDate);
        setDisabled(paramBoolean);
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

    public Date getCreateTime() {
        return this._$6;
    }

    public void setCreateTime(Date paramDate) {
        this._$6 = paramDate;
    }

    public Boolean getDisabled() {
        return this._$5;
    }

    public void setDisabled(Boolean paramBoolean) {
        this._$5 = paramBoolean;
    }

    public User getUser() {
        return this._$3;
    }

    public void setUser(User paramUser) {
        this._$3 = paramUser;
    }

    public Website getWebsite() {
        return this._$2;
    }

    public void setWebsite(Website paramWebsite) {
        this._$2 = paramWebsite;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Admin))
            return false;
        Admin localAdmin = (Admin) paramObject;
        if ((null == getId()) || (null == localAdmin.getId()))
            return false;
        return getId().equals(localAdmin.getId());
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

    public Set<Role> getRoles() {
        return this._$1;
    }

    public void setRoles(Set<Role> paramSet) {
        this._$1 = paramSet;
    }

    public Boolean getViewonlyAdmin() {
        return this._$4;
    }

    public void setViewonlyAdmin(Boolean paramBoolean) {
        this._$4 = paramBoolean;
    }
}

