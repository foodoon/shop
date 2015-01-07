package guda.shop.cms.manager;

import guda.shop.cms.entity.Cart;
import com.jspgou.cms.entity.Product;

public abstract interface CartMng
{
  public abstract Cart findById(Long paramLong);

  public abstract Cart update(Cart paramCart);

  public abstract Cart deleteById(Long paramLong);

  public abstract Cart addGift(Long paramLong1, int paramInt, boolean paramBoolean, Long paramLong2, Long paramLong3);

  public abstract Cart collectAddItem(Product paramProduct, Long paramLong1, Long paramLong2, int paramInt, boolean paramBoolean, Long paramLong3, Long paramLong4);
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.CartMng
 * JD-Core Version:    0.6.2
 */