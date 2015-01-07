package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ShopMember;
import com.jspgou.cms.entity.ShopScore;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseShopScore
  implements Serializable
{
  public static String REF = "ShopScore";
  public static String PROP_NAME = "name";
  public static String PROP_STATUS = "status";
  public static String PROP_MEMBER = "member";
  public static String PROP_ORDER_ITEM = "orderItem";
  public static String PROP_ORDER = "order";
  public static String PROP_ID = "id";
  public static String PROP_SCORE_TIME = "scoreTime";
  public static String PROP_REMARK = "remark";
  public static String PROP_SCORE_TYPE = "scoreType";
  public static String PROP_SCORE = "score";
  public static String PROP_USE_STATUS = "useStatus";
  private int _$11 = -2147483648;
  private Long _$10;
  private String _$9;
  private Integer _$8;
  private Date _$7;
  private Integer _$6;
  private boolean _$5;
  private boolean _$4;
  private String _$3;
  private String _$2;
  private ShopMember _$1;

  public BaseShopScore()
  {
    initialize();
  }

  public BaseShopScore(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseShopScore(Long paramLong, Integer paramInteger, boolean paramBoolean1, boolean paramBoolean2)
  {
    setId(paramLong);
    setScoreType(paramInteger);
    setUseStatus(paramBoolean1);
    setStatus(paramBoolean2);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$10;
  }

  public void setId(Long paramLong)
  {
    this._$10 = paramLong;
    this._$11 = -2147483648;
  }

  public String getName()
  {
    return this._$9;
  }

  public void setName(String paramString)
  {
    this._$9 = paramString;
  }

  public Integer getScore()
  {
    return this._$8;
  }

  public void setScore(Integer paramInteger)
  {
    this._$8 = paramInteger;
  }

  public Date getScoreTime()
  {
    return this._$7;
  }

  public void setScoreTime(Date paramDate)
  {
    this._$7 = paramDate;
  }

  public Integer getScoreType()
  {
    return this._$6;
  }

  public void setScoreType(Integer paramInteger)
  {
    this._$6 = paramInteger;
  }

  public boolean getUseStatus()
  {
    return this._$5;
  }

  public void setUseStatus(boolean paramBoolean)
  {
    this._$5 = paramBoolean;
  }

  public boolean getStatus()
  {
    return this._$4;
  }

  public void setStatus(boolean paramBoolean)
  {
    this._$4 = paramBoolean;
  }

  public String getRemark()
  {
    return this._$3;
  }

  public void setRemark(String paramString)
  {
    this._$3 = paramString;
  }

  public ShopMember getMember()
  {
    return this._$1;
  }

  public void setMember(ShopMember paramShopMember)
  {
    this._$1 = paramShopMember;
  }

  public void setCode(String paramString)
  {
    this._$2 = paramString;
  }

  public String getCode()
  {
    return this._$2;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopScore))
      return false;
    ShopScore localShopScore = (ShopScore)paramObject;
    if ((null == getId()) || (null == localShopScore.getId()))
      return false;
    return getId().equals(localShopScore.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$11)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$11 = str.hashCode();
    }
    return this._$11;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopScore
 * JD-Core Version:    0.6.2
 */