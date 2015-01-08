package guda.shop.core.manager.impl;

import guda.shop.common.hibernate3.Updater;
import guda.shop.core.dao.AdminDao;
import guda.shop.core.entity.Admin;
import guda.shop.core.entity.User;
import guda.shop.core.entity.Website;
import guda.shop.core.manager.AdminMng;
import guda.shop.core.manager.RoleMng;
import guda.shop.core.manager.UserMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class AdminMngImpl
        implements AdminMng {
    protected RoleMng roleMng;
    private UserMng _$2;
    private AdminDao _$1;

    public Admin getByUserId(Long paramLong1, Long paramLong2) {
        return this._$1.getByUserId(paramLong1, paramLong2);
    }

    public Admin register(String paramString1, String paramString2, String paramString3, String paramString4, Boolean paramBoolean1, Website paramWebsite, Boolean paramBoolean2) {
        Admin localAdmin = new Admin();
        Timestamp localTimestamp = new Timestamp(System.currentTimeMillis());
        User localUser = this._$2.register(paramString1, paramString2, paramString3, paramString4, localTimestamp);
        localAdmin.setCreateTime(localTimestamp);
        localAdmin.setUser(localUser);
        localAdmin.setWebsite(paramWebsite);
        localAdmin.setDisabled(paramBoolean1);
        localAdmin.setViewonlyAdmin(paramBoolean2);
        return save(localAdmin);
    }

    public Admin findById(Long paramLong) {
        return this._$1.findById(paramLong);
    }

    public Admin save(Admin paramAdmin) {
        paramAdmin.init();
        return this._$1.save(paramAdmin);
    }

    public Admin update(Admin paramAdmin) {
        return this._$1.updateByUpdater(new Updater(paramAdmin));
    }

    public Admin deleteById(Long paramLong) {
        return this._$1.deleteById(paramLong);
    }

    public Admin[] deleteByIds(Long[] paramArrayOfLong) {
        Admin[] arrayOfAdmin = new Admin[paramArrayOfLong.length];
        for (int i = 0; i < paramArrayOfLong.length; i++)
            arrayOfAdmin[i] = deleteById(paramArrayOfLong[i]);
        return arrayOfAdmin;
    }

    public void updateRole(Admin paramAdmin, Integer[] paramArrayOfInteger) {
        paramAdmin.getRoles().clear();
        if (paramArrayOfInteger != null)
            for (Integer localInteger : paramArrayOfInteger)
                paramAdmin.addToRoles(this.roleMng.findById(localInteger));
    }

    public void addRole(Admin paramAdmin, Integer[] paramArrayOfInteger) {
        if (paramArrayOfInteger != null)
            for (Integer localInteger : paramArrayOfInteger)
                paramAdmin.addToRoles(this.roleMng.findById(localInteger));
    }

    @Autowired
    public void setDao(AdminDao paramAdminDao) {
        this._$1 = paramAdminDao;
    }

    @Autowired
    public void setUserMng(UserMng paramUserMng) {
        this._$2 = paramUserMng;
    }

    @Autowired
    public void setRoleMng(RoleMng paramRoleMng) {
        this.roleMng = paramRoleMng;
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.impl.AdminMngImpl
 * JD-Core Version:    0.6.2
 */