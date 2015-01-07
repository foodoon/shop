package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Gift;
import java.io.Serializable;

public abstract class BaseGift
  implements Serializable
{
  public static String REF = "Gift";
  public static String PROP_GIFT_STOCK = "giftStock";
  public static String PROP_GIFT_NAME = "giftName";
  public static String PROP_GIFT_PICTURE = "giftPicture";
  public static String PROP_GIFT_SCORE = "giftScore";
  public static String PROP_ID = "id";
  public static String PROP_ISGIFT = "isgift";
  private int _$8 = -2147483648;
  private Long _$7;
  private String _$6;
  private Integer _$5;
  private Integer _$4;
  private String _$3;
  private Boolean _$2;
  private String _$1;

  public BaseGift()
  {
    initialize();
  }

  public BaseGift(Long paramLong)
  {
    setId(paramLong);
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

  public String getGiftName()
  {
    return this._$6;
  }

  public void setGiftName(String paramString)
  {
    this._$6 = paramString;
  }

  public Integer getGiftScore()
  {
    return this._$5;
  }

  public void setGiftScore(Integer paramInteger)
  {
    this._$5 = paramInteger;
  }

  public Integer getGiftStock()
  {
    return this._$4;
  }

  public void setGiftStock(Integer paramInteger)
  {
    this._$4 = paramInteger;
  }

  public String getGiftPicture()
  {
    return this._$3;
  }

  public void setGiftPicture(String paramString)
  {
    this._$3 = paramString;
  }

  public Boolean getIsgift()
  {
    return this._$2;
  }

  public void setIsgift(Boolean paramBoolean)
  {
    this._$2 = paramBoolean;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Gift))
      return false;
    Gift localGift = (Gift)paramObject;
    if ((null == getId()) || (null == localGift.getId()))
      return false;
    return getId().equals(localGift.getId());
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

  public void setIntroduced(String paramString)
  {
    this._$1 = paramString;
  }

  public String getIntroduced()
  {
    return this._$1;
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseGift
 * JD-Core Version:    0.6.2
 */