package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShopMember;
import guda.shop.core.entity.Website;

import java.util.Date;

public class ShopMember extends BaseShopMember {
    private static final long serialVersionUID = 1L;

    public ShopMember() {
    }

    public ShopMember(Long paramLong) {
        super(paramLong);
    }

    public ShopMember(Long paramLong, Website paramWebsite, ShopMemberGroup paramShopMemberGroup, Integer paramInteger) {
        super(paramLong, paramWebsite, paramShopMemberGroup, paramInteger);
    }

    public void init() {
        if (getScore() == null)
            setScore(Integer.valueOf(0));
    }

    public Date getCreateTime() {
        return getMember().getCreateTime();
    }

    public Boolean getDisabled() {
        return getMember().getDisabled();
    }

    public String getUsername() {
        return getMember().getUsername();
    }

    public String getEmail() {
        return getMember().getEmail();
    }

    public String getPassword() {
        return getMember().getPassword();
    }

    public Long getLoginCount() {
        return getMember().getLoginCount();
    }

    public String getRegisterIp() {
        return getMember().getRegisterIp();
    }

    public Date getLastLoginTime() {
        return getMember().getLastLoginTime();
    }

    public String getLastLoginIp() {
        return getMember().getLastLoginIp();
    }

    public Date getCurrentLoginTime() {
        return getMember().getCurrentLoginTime();
    }

    public String getCurrentLoginIp() {
        return getMember().getCurrentLoginIp();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.ShopMember
 * JD-Core Version:    0.6.2
 */