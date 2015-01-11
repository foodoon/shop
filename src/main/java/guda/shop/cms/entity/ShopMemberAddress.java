package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseShopMemberAddress;
import org.apache.commons.lang.StringUtils;

public class ShopMemberAddress extends BaseShopMemberAddress
        implements AddressInterface {
    private static final long serialVersionUID = 1L;

    public ShopMemberAddress() {
    }

    public ShopMemberAddress(Long paramLong) {
        super(paramLong);
    }

    public ShopMemberAddress(Long paramLong, ShopMember paramShopMember, Address paramAddress1, Address paramAddress2, Address paramAddress3, String paramString1, String paramString2, boolean paramBoolean) {
        super(paramLong, paramShopMember, paramAddress1, paramAddress2, paramAddress3, paramString1, paramString2, paramBoolean);
    }

    public String getMobile() {
        String str = null;
        if (!StringUtils.isBlank(getPhone())) {
            if (StringUtils.isBlank(getAreaCode()))
                str = getAreaCode() + "-";
            str = str + getPhone();
            if (StringUtils.isBlank(getExtNumber()))
                str = "-" + getExtNumber();
            return str;
        }
        return str;
    }
}

