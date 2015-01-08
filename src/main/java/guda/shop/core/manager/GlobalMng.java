package guda.shop.core.manager;

import guda.shop.core.entity.Global;

public abstract interface GlobalMng {
    public abstract Global findById(Long paramLong);

    public abstract Global update(Global paramGlobal);
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.GlobalMng
 * JD-Core Version:    0.6.2
 */