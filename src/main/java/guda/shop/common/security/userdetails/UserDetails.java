package guda.shop.common.security.userdetails;

import java.io.Serializable;

public abstract interface UserDetails extends Serializable {
    public abstract String getPassword();

    public abstract String getUsername();

    public abstract Long getId();

    public abstract boolean isAccountNonExpired();

    public abstract boolean isAccountNonLocked();

    public abstract boolean isCredentialsNonExpired();

    public abstract boolean isEnabled();
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.security.userdetails.UserDetails
 * JD-Core Version:    0.6.2
 */