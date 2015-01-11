package guda.shop.common.security.userdetails;

import guda.shop.common.security.AccountStatusException;

public abstract interface UserDetailsChecker {
    public abstract void check(UserDetails paramUserDetails)
            throws AccountStatusException;
}

