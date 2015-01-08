package guda.shop.common.security.userdetails;

import guda.shop.common.security.*;

public class AccountStatusUserDetailsChecker
        implements UserDetailsChecker {
    public void check(UserDetails paramUserDetails)
            throws AccountStatusException {
        if (!paramUserDetails.isAccountNonLocked())
            throw new LockedException();
        if (!paramUserDetails.isEnabled())
            throw new DisabledException("User is disabled", paramUserDetails);
        if (!paramUserDetails.isAccountNonExpired())
            throw new AccountExpiredException("User account has expired", paramUserDetails);
        if (!paramUserDetails.isCredentialsNonExpired())
            throw new CredentialsExpiredException("User credentials have expired", paramUserDetails);
    }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.userdetails.AccountStatusUserDetailsChecker
 * JD-Core Version:    0.6.2
 */