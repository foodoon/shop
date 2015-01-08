package guda.shop.core.manager.impl;

import guda.shop.common.hibernate3.Updater;
import guda.shop.core.dao.RoleDao;
import guda.shop.core.entity.Role;
import guda.shop.core.manager.RoleMng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleMngImpl
        implements RoleMng {
    private RoleDao _$1;

    @Transactional(readOnly = true)
    public List<Role> getList() {
        return this._$1.getList();
    }

    @Transactional(readOnly = true)
    public Role findById(Integer paramInteger) {
        Role localRole = this._$1.findById(paramInteger);
        return localRole;
    }

    public Role save(Role paramRole, Set<String> paramSet) {
        paramRole.setPerms(paramSet);
        this._$1.save(paramRole);
        return paramRole;
    }

    public Role update(Role paramRole, Set<String> paramSet) {
        Updater localUpdater = new Updater(paramRole);
        paramRole = this._$1.updateByUpdater(localUpdater);
        paramRole.getPerms().clear();
        paramRole.setPerms(paramSet);
        return paramRole;
    }

    public Role deleteById(Integer paramInteger) {
        Role localRole = this._$1.deleteById(paramInteger);
        return localRole;
    }

    public Role[] deleteByIds(Integer[] paramArrayOfInteger) {
        Role[] arrayOfRole = new Role[paramArrayOfInteger.length];
        int i = 0;
        int j = paramArrayOfInteger.length;
        while (i < j) {
            arrayOfRole[i] = deleteById(paramArrayOfInteger[i]);
            i++;
        }
        return arrayOfRole;
    }

    @Autowired
    public void setDao(RoleDao paramRoleDao) {
        this._$1 = paramRoleDao;
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.impl.RoleMngImpl
 * JD-Core Version:    0.6.2
 */