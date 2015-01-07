package guda.shop.entity;

import guda.shop.cms.entity.base.BaseOrderReturn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderReturn extends BaseOrderReturn
{
  private static final long serialVersionUID = 1L;

  public OrderReturn()
  {
  }

  public OrderReturn(Long paramLong)
  {
    super(paramLong);
  }

  public OrderReturn(Long paramLong, Order paramOrder, ShopDictionary paramShopDictionary, Integer paramInteger1, Integer paramInteger2, Double paramDouble, Integer paramInteger3, Date paramDate)
  {
    super(paramLong, paramOrder, paramShopDictionary, paramInteger1, paramInteger2, paramDouble, paramInteger3, paramDate);
  }

  public void addToPictures(String paramString1, String paramString2)
  {
    Object localObject = getPictures();
    if (localObject == null)
    {
      localObject = new ArrayList();
      setPictures((List)localObject);
    }
    OrderReturnPicture localOrderReturnPicture = new OrderReturnPicture();
    localOrderReturnPicture.setImgPath(paramString1);
    localOrderReturnPicture.setDescription(paramString2);
    ((List)localObject).add(localOrderReturnPicture);
  }

  public OrderReturnStatus getOrderReturnStatus()
  {
    Integer localInteger = getReturnType();
    if (localInteger != null)
      return OrderReturnStatus.valueOf(localInteger.intValue());
    return null;
  }

  public static enum OrderReturnStatus
  {
    SELLER_NODELIVERY_RETURN(1), SELLER_DELIVERY_RETURN(2);

    private int value;

    private OrderReturnStatus(int paramInt)
    {
      this.value = paramInt;
    }

    public int getValue()
    {
      return this.value;
    }

    public static OrderReturnStatus valueOf(int paramInt)
    {
      for (OrderReturnStatus localOrderReturnStatus : values())
        if (localOrderReturnStatus.getValue() == paramInt)
          return localOrderReturnStatus;
      throw new IllegalArgumentException("Connot find value " + paramInt + " in eunu OrderStatus.");
    }
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.OrderReturn
 * JD-Core Version:    0.6.2
 */