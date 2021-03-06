package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShopAdmin;
import guda.shop.core.entity.Admin;
import guda.shop.core.entity.Website;

import java.util.Date;
import java.util.Set;

public class ShopAdmin extends BaseShopAdmin {
    private static final long serialVersionUID = 1L;

    public ShopAdmin() {
    }

    public ShopAdmin(Long paramLong) {
        super(paramLong);
    }

    public ShopAdmin(Long paramLong, Website paramWebsite) {
        super(paramLong, paramWebsite);
    }

    public Set<String> getPerms() {
        Admin localAdmin = getAdmin();
        if (localAdmin != null)
            return localAdmin.getRolesPerms();
        return null;
    }

    public String getUsername() {
        Admin localAdmin = getAdmin();
        if (localAdmin != null)
            return localAdmin.getUsername();
        return null;
    }

    public String getEmail() {
        Admin localAdmin = getAdmin();
        if (localAdmin != null)
            return localAdmin.getEmail();
        return null;
    }

    public Date getLastLoginTime() {
        Admin localAdmin = getAdmin();
        if (localAdmin != null)
            return localAdmin.getLastLoginTime();
        return null;
    }

    public String getLastLoginIp() {
        Admin localAdmin = getAdmin();
        if (localAdmin != null)
            return localAdmin.getLastLoginIp();
        return null;
    }

    public Boolean getViewonlyAdmin() {
        Admin localAdmin = getAdmin();
        if (localAdmin != null)
            return localAdmin.getViewonlyAdmin();
        System.out.println(2);
        return null;
    }

    public Date getCurrentLoginTime() {
        Admin localAdmin = getAdmin();
        if (localAdmin != null)
            return localAdmin.getCurrentLoginTime();
        return null;
    }

    public String getCurrentLoginIp() {
        Admin localAdmin = getAdmin();
        if (localAdmin != null)
            return localAdmin.getCurrentLoginIp();
        return null;
    }

    public Boolean getDisabled() {
        Admin localAdmin = getAdmin();
        if (localAdmin != null)
            return localAdmin.getDisabled();
        return null;
    }
}

