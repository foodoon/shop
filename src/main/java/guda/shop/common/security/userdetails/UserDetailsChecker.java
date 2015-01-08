package guda.shop.common.security.userdetails;

import guda.shop.common.security.AccountStatusException;

public abstract interface UserDetailsChecker {
    public abstract void check(UserDetails paramUserDetails)
            throws AccountStatusException;
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.userdetails.UserDetailsChecker
 * JD-Core Version:    0.6.2
 */