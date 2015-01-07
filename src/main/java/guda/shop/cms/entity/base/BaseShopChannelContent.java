package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ShopChannel;
import guda.shop.cms.entity.ShopChannelContent;
import java.io.Serializable;

public abstract class BaseShopChannelContent
  implements Serializable
{
  public static String REF = "ShopChannelContent";
  public static String PROP_CHANNEL = "channel";
  public static String PROP_CONTENT = "content";
  public static String PROP_ID = "id";
  private int _$4 = -2147483648;
  private Long _$3;
  private String _$2;
  private ShopChannel _$1;

  public BaseShopChannelContent()
  {
    initialize();
  }

  public BaseShopChannelContent(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$3;
  }

  public void setId(Long paramLong)
  {
    this._$3 = paramLong;
    this._$4 = -2147483648;
  }

  public String getContent()
  {
    return this._$2;
  }

  public void setContent(String paramString)
  {
    this._$2 = paramString;
  }

  public ShopChannel getChannel()
  {
    return this._$1;
  }

  public void setChannel(ShopChannel paramShopChannel)
  {
    this._$1 = paramShopChannel;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopChannelContent))
      return false;
    ShopChannelContent localShopChannelContent = (ShopChannelContent)paramObject;
    if ((null == getId()) || (null == localShopChannelContent.getId()))
      return false;
    return getId().equals(localShopChannelContent.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$4)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$4 = str.hashCode();
    }
    return this._$4;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopChannelContent
 * JD-Core Version:    0.6.2
 */