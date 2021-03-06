package guda.shop.common.security.userdetails;

import guda.shop.common.security.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

public abstract interface UserDetailsService {
    public abstract UserDetails loadUser(Long paramLong, String paramString)
            throws UsernameNotFoundException, DataAccessException;
}

