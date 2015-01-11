package guda.shop.core.dao.impl;

import guda.shop.common.hibernate3.HibernateBaseDao;
import guda.shop.core.dao.RoleDao;
import guda.shop.core.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl extends HibernateBaseDao<Role, Integer>
        implements RoleDao {
    public List<Role> getList() {
        String str = "from Role bean order by bean.priority asc";
        return find(str, new Object[0]);
    }

    public Role findById(Integer paramInteger) {
        Role localRole = (Role) get(paramInteger);
        return localRole;
    }

    public Role save(Role paramRole) {
        getSession().save(paramRole);
        return paramRole;
    }

    public Role deleteById(Integer paramInteger) {
        Role localRole = (Role) super.get(paramInteger);
        if (localRole != null)
            getSession().delete(localRole);
        return localRole;
    }

    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}

