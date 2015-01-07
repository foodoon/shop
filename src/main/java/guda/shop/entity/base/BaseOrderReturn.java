package guda.shop.entity.base;

import guda.shop.cms.entity.Order;
iimport guda.shopcms.entity.OrderReturn;
imimport guda.shopms.entity.OrderReturnPicture;
import com.jspgou.cms.entity.ShopDictionary;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class BaseOrderReturn
  implements Serializable
{
  public static String REF = "OrderReturn";
  public static String PROP_SELLER_MONEY = "sellerMoney";
  public static String PROP_MONEY = "money";
  public static String PROP_SHOP_DICTIONARY = "shopDictionary";
  public static String PROP_ORDER = "order";
  public static String PROP_ALIPAY_ID = "alipayId";
  public static String PROP_RETURN_TYPE = "returnType";
  public static String PROP_CODE = "code";
  public static String PROP_STATUS = "status";
  public static String PROP_PAY_TYPE = "PayType";
  public static String PROP_FINISHED_TIME = "finishedTime";
  public static String PROP_CREATE_TIME = "createTime";
  public static String PROP_ID = "id";
  public static String PROP_REASON = "reason";
  private int _$15 = -2147483648;
  private Long _$14;
  private String _$13;
  private String _$12;
  private String _$11;
  private Integer _$10;
  private Double _$9;
  private Double _$8;
  private Integer _$7;
  private Date _$6;
  private Date _$5;
  private Integer _$4;
  private Order _$3;
  private ShopDictionary _$2;
  private List<OrderReturnPicture> _$1;

  public BaseOrderReturn()
  {
    initialize();
  }

  public BaseOrderReturn(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseOrderReturn(Long paramLong, Order paramOrder, ShopDictionary paramShopDictionary, Integer paramInteger1, Integer paramInteger2, Double paramDouble, Integer paramInteger3, Date paramDate)
  {
    setId(paramLong);
    setOrder(paramOrder);
    setShopDictionary(paramShopDictionary);
    setPayType(paramInteger1);
    setStatus(paramInteger2);
    setMoney(paramDouble);
    setReturnType(paramInteger3);
    setCreateTime(paramDate);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$14;
  }

  public void setId(Long paramLong)
  {
    this._$14 = paramLong;
    this._$15 = -2147483648;
  }

  public String getCode()
  {
    return this._$13;
  }

  public void setCode(String paramString)
  {
    this._$13 = paramString;
  }

  public String getReason()
  {
    return this._$12;
  }

  public void setReason(String paramString)
  {
    this._$12 = paramString;
  }

  public String getAlipayId()
  {
    return this._$11;
  }

  public void setAlipayId(String paramString)
  {
    this._$11 = paramString;
  }

  public Integer getStatus()
  {
    return this._$10;
  }

  public void setStatus(Integer paramInteger)
  {
    this._$10 = paramInteger;
  }

  public Double getMoney()
  {
    return this._$9;
  }

  public void setMoney(Double paramDouble)
  {
    this._$9 = paramDouble;
  }

  public Double getSellerMoney()
  {
    return this._$8;
  }

  public void setSellerMoney(Double paramDouble)
  {
    this._$8 = paramDouble;
  }

  public Integer getReturnType()
  {
    return this._$7;
  }

  public void setReturnType(Integer paramInteger)
  {
    this._$7 = paramInteger;
  }

  public Date getCreateTime()
  {
    return this._$6;
  }

  public void setCreateTime(Date paramDate)
  {
    this._$6 = paramDate;
  }

  public Date getFinishedTime()
  {
    return this._$5;
  }

  public void setFinishedTime(Date paramDate)
  {
    this._$5 = paramDate;
  }

  public Order getOrder()
  {
    return this._$3;
  }

  public void setOrder(Order paramOrder)
  {
    this._$3 = paramOrder;
  }

  public ShopDictionary getShopDictionary()
  {
    return this._$2;
  }

  public void setShopDictionary(ShopDictionary paramShopDictionary)
  {
    this._$2 = paramShopDictionary;
  }

  public List<OrderReturnPicture> getPictures()
  {
    return this._$1;
  }

  public void setPictures(List<OrderReturnPicture> paramList)
  {
    this._$1 = paramList;
  }

  public void setPayType(Integer paramInteger)
  {
    this._$4 = paramInteger;
  }

  public Integer getPayType()
  {
    return this._$4;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof OrderReturn))
      return false;
    OrderReturn localOrderReturn = (OrderReturn)paramObject;
    if ((null == getId()) || (null == localOrderReturn.getId()))
      return false;
    return getId().equals(localOrderReturn.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$15)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$15 = str.hashCode();
    }
    return this._$15;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseOrderReturn
 * JD-Core Version:    0.6.2
 */