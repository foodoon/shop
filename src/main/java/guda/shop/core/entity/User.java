package guda.shop.core.entity;

import guda.shop.core.entity.base.BaseUser;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class User extends BaseUser {
    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(Long paramLong) {
        super(paramLong);
    }

    public User(Long paramLong1, String paramString1, String paramString2, Date paramDate, Long paramLong2) {
        super(paramLong1, paramString1, paramString2, paramDate, paramLong2);
    }

    public void init() {
        if (StringUtils.isBlank(getEmail()))
            setEmail(null);
        if (getCreateTime() == null)
            setCreateTime(new Date());
        setLoginCount(Long.valueOf(0L));
    }
}

