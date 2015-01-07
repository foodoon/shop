package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Order;
import guda.shop.cms.entity.OrderItem;
import guda.shop.cms.entity.OrderReturn;
import guda.shop.cms.entity.Payment;
import guda.shop.cms.entity.Shipping;
import guda.shop.cms.entity.ShopMember;
import guda.shop.core.entity.Website;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public abstract class BaseOrder
  implements Serializable
{
  public static String REF = "Order";
  public static String PROP_IP = "ip";
  public static String PROP_MEMBER = "member";
  public static String PROP_COMMENTS = "comments";
  public static String PROP_WEBSITE = "website";
  public static String PROP_RETURN_REASON = "returnReason";
  public static String PROP_FREIGHT = "freight";
  public static String PROP_CODE = "code";
  public static String PROP_PAYMENT = "payment";
  public static String PROP_PRODUCT_PRICE = "productPrice";
  public static String PROP_COUPON_PRICE = "couponPrice";
  public static String PROP_STATUS = "status";
  public static String PROP_SHIPPING_TIME = "shippingTime";
  public static String PROP_FINISHED_TIME = "finishedTime";
  public static String PROP_WEIGHT = "weight";
  public static String PROP_CREATE_TIME = "createTime";
  public static String PROP_ID = "id";
  public static String PROP_SHOP_DIRECTORY = "shopDirectory";
  public static String PROP_SHIPPING = "shipping";
  public static String PROP_PRODUCT_NAME = "productName";
  public static String PROP_LAST_MODIFIED = "lastModified";
  public static String PROP_SCORE = "score";
  public static String PROP_TOTAL = "total";
  private int _$32 = -2147483648;
  private Long _$31;
  private Long _$30;
  private String _$29;
  private String _$28;
  private Date _$27;
  private Date _$26;
  private Date _$25;
  private Date _$24;
  private Double _$23;
  private Double _$22;
  private Double _$21;
  private Integer _$20;
  private Double _$19;
  private Double _$18;
  private String _$17;
  private Integer _$16;
  private Integer _$15;
  private Integer _$14;
  private String _$13;
  private String _$12;
  private String _$11;
  private String _$10;
  private String _$9;
  private String _$8;
  private String _$7;
  private Website _$6;
  private ShopMember _$5;
  private Payment _$4;
  private Shipping _$3;
  private OrderReturn _$2;
  private Set<OrderItem> _$1;

  public BaseOrder()
  {
    initialize();
  }

  public BaseOrder(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseOrder(Long paramLong, Website paramWebsite, ShopMember paramShopMember, Payment paramPayment, Shipping paramShipping1, Shipping paramShipping2, long paramLong1, String paramString, Date paramDate1, Date paramDate2, Double paramDouble1, Integer paramInteger, Double paramDouble2)
  {
    setId(paramLong);
    setWebsite(paramWebsite);
    setMember(paramShopMember);
    setPayment(paramPayment);
    setShipping(paramShipping1);
    setCode(Long.valueOf(paramLong1));
    setIp(paramString);
    setCreateTime(paramDate1);
    setLastModified(paramDate2);
    setTotal(paramDouble1);
    setScore(paramInteger);
    setWeight(paramDouble2);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$31;
  }

  public void setId(Long paramLong)
  {
    this._$31 = paramLong;
    this._$32 = -2147483648;
  }

  public long getCode()
  {
    return this._$30.longValue();
  }

  public void setCode(Long paramLong)
  {
    this._$30 = paramLong;
  }

  public String getComments()
  {
    return this._$29;
  }

  public void setComments(String paramString)
  {
    this._$29 = paramString;
  }

  public String getIp()
  {
    return this._$28;
  }

  public void setIp(String paramString)
  {
    this._$28 = paramString;
  }

  public Date getCreateTime()
  {
    return this._$27;
  }

  public void setCreateTime(Date paramDate)
  {
    this._$27 = paramDate;
  }

  public Date getShippingTime()
  {
    return this._$26;
  }

  public void setShippingTime(Date paramDate)
  {
    this._$26 = paramDate;
  }

  public Date getFinishedTime()
  {
    return this._$25;
  }

  public void setFinishedTime(Date paramDate)
  {
    this._$25 = paramDate;
  }

  public Date getLastModified()
  {
    return this._$24;
  }

  public void setLastModified(Date paramDate)
  {
    this._$24 = paramDate;
  }

  public Double getProductPrice()
  {
    return this._$23;
  }

  public void setProductPrice(Double paramDouble)
  {
    this._$23 = paramDouble;
  }

  public Double getFreight()
  {
    return this._$22;
  }

  public void setFreight(Double paramDouble)
  {
    this._$22 = paramDouble;
  }

  public Double getTotal()
  {
    return this._$21;
  }

  public void setTotal(Double paramDouble)
  {
    this._$21 = paramDouble;
  }

  public Integer getScore()
  {
    return this._$20;
  }

  public void setScore(Integer paramInteger)
  {
    this._$20 = paramInteger;
  }

  public Double getWeight()
  {
    return this._$19;
  }

  public void setWeight(Double paramDouble)
  {
    this._$19 = paramDouble;
  }

  public Double getCouponPrice()
  {
    return this._$18;
  }

  public void setCouponPrice(Double paramDouble)
  {
    this._$18 = paramDouble;
  }

  public String getProductName()
  {
    return this._$17;
  }

  public void setProductName(String paramString)
  {
    this._$17 = paramString;
  }

  public Website getWebsite()
  {
    return this._$6;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$6 = paramWebsite;
  }

  public ShopMember getMember()
  {
    return this._$5;
  }

  public void setMember(ShopMember paramShopMember)
  {
    this._$5 = paramShopMember;
  }

  public Payment getPayment()
  {
    return this._$4;
  }

  public void setPayment(Payment paramPayment)
  {
    this._$4 = paramPayment;
  }

  public Shipping getShipping()
  {
    return this._$3;
  }

  public void setShipping(Shipping paramShipping)
  {
    this._$3 = paramShipping;
  }

  public Set<OrderItem> getItems()
  {
    return this._$1;
  }

  public void setItems(Set<OrderItem> paramSet)
  {
    this._$1 = paramSet;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Order))
      return false;
    Order localOrder = (Order)paramObject;
    if ((null == getId()) || (null == localOrder.getId()))
      return false;
    return getId().equals(localOrder.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$32)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$32 = str.hashCode();
    }
    return this._$32;
  }

  public String toString()
  {
    return super.toString();
  }

  public void setPaymentStatus(Integer paramInteger)
  {
    this._$16 = paramInteger;
  }

  public Integer getPaymentStatus()
  {
    return this._$16;
  }

  public void setShippingStatus(Integer paramInteger)
  {
    this._$15 = paramInteger;
  }

  public Integer getShippingStatus()
  {
    return this._$15;
  }

  public void setStatus(Integer paramInteger)
  {
    this._$14 = paramInteger;
  }

  public Integer getStatus()
  {
    return this._$14;
  }

  public void setReturnOrder(OrderReturn paramOrderReturn)
  {
    this._$2 = paramOrderReturn;
  }

  public OrderReturn getReturnOrder()
  {
    return this._$2;
  }

  public void setReceiveName(String paramString)
  {
    this._$13 = paramString;
  }

  public String getReceiveName()
  {
    return this._$13;
  }

  public void setReceiveAddress(String paramString)
  {
    this._$12 = paramString;
  }

  public String getReceiveAddress()
  {
    return this._$12;
  }

  public void setReceiveZip(String paramString)
  {
    this._$11 = paramString;
  }

  public String getReceiveZip()
  {
    return this._$11;
  }

  public void setReceivePhone(String paramString)
  {
    this._$10 = paramString;
  }

  public String getReceivePhone()
  {
    return this._$10;
  }

  public void setReceiveMobile(String paramString)
  {
    this._$9 = paramString;
  }

  public String getReceiveMobile()
  {
    return this._$9;
  }

  public void setTradeNo(String paramString)
  {
    this._$8 = paramString;
  }

  public String getTradeNo()
  {
    return this._$8;
  }

  public void setPaymentCode(String paramString)
  {
    this._$7 = paramString;
  }

  public String getPaymentCode()
  {
    return this._$7;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseOrder
 * JD-Core Version:    0.6.2
 */