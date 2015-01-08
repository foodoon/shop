package guda.shop.cms.entity;

import guda.shop.cms.entity.base.BaseDiscuss;

public class Discuss extends BaseDiscuss {
    private static final long serialVersionUID = 1L;

    public Discuss() {
    }

    public Discuss(Long paramLong) {
        super(paramLong);
    }

    public Discuss(Long paramLong, ShopMember paramShopMember, Product paramProduct, String paramString) {
        super(paramLong, paramShopMember, paramProduct, paramString);
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.Discuss
 * JD-Core Version:    0.6.2
 */