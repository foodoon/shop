package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Order;
import guda.shop.cms.entity.Shipments;
import guda.shop.cms.entity.ShopAdmin;
import java.io.Serializable;

public abstract class BaseShipments
  implements Serializable
{
  public static String REF = "Shipments";
  public static String PROP_RECEIVING = "receiving";
  public static String PROP_MONEY = "money";
  public static String PROP_COMMENT = "comment";
  public static String PROP_WAYBILL = "waybill";
  public static String PROP_ID = "id";
  public static String PROP_SHOP_ADMIN = "shopAdmin";
  public static String PROP_INDENT = "indent";
  private int _$8 = -2147483648;
  private Long _$7;
  private String _$6;
  private double _$5;
  private String _$4;
  private String _$3;
  private Order _$2;
  private ShopAdmin _$1;

  public BaseShipments()
  {
    initialize();
  }

  public BaseShipments(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseShipments(Long paramLong, Order paramOrder, ShopAdmin paramShopAdmin, String paramString1, String paramString2, String paramString3)
  {
    setId(paramLong);
    setIndent(paramOrder);
    setShopAdmin(paramShopAdmin);
    setWaybill(paramString1);
    setReceiving(paramString2);
    setComment(paramString3);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$7;
  }

  public void setId(Long paramLong)
  {
    this._$7 = paramLong;
    this._$8 = -2147483648;
  }

  public String getWaybill()
  {
    return this._$6;
  }

  public void setWaybill(String paramString)
  {
    this._$6 = paramString;
  }

  public double getMoney()
  {
    return this._$5;
  }

  public void setMoney(double paramDouble)
  {
    this._$5 = paramDouble;
  }

  public String getReceiving()
  {
    return this._$4;
  }

  public void setReceiving(String paramString)
  {
    this._$4 = paramString;
  }

  public String getComment()
  {
    return this._$3;
  }

  public void setComment(String paramString)
  {
    this._$3 = paramString;
  }

  public Order getIndent()
  {
    return this._$2;
  }

  public void setIndent(Order paramOrder)
  {
    this._$2 = paramOrder;
  }

  public ShopAdmin getShopAdmin()
  {
    return this._$1;
  }

  public void setShopAdmin(ShopAdmin paramShopAdmin)
  {
    this._$1 = paramShopAdmin;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Shipments))
      return false;
    Shipments localShipments = (Shipments)paramObject;
    if ((null == getId()) || (null == localShipments.getId()))
      return false;
    return getId().equals(localShipments.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$8)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$8 = str.hashCode();
    }
    return this._$8;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShipments
 * JD-Core Version:    0.6.2
 */