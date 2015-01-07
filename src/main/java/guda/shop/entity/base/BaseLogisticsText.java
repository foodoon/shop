package guda.shop.entity.base;

import guda.shop.cms.entity.Logistics;
import com.jspgou.cms.entity.LogisticsText;
import java.io.Serializable;

public abstract class BaseLogisticsText
  implements Serializable
{
  public static String REF = "LogisticsText";
  public static String PROP_TEXT = "text";
  public static String PROP_LOGISTICS = "logistics";
  public static String PROP_ID = "id";
  private int _$4 = -2147483648;
  private Long _$3;
  private String _$2;
  private Logistics _$1;

  public BaseLogisticsText()
  {
    initialize();
  }

  public BaseLogisticsText(Long paramLong)
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

  public String getText()
  {
    return this._$2;
  }

  public void setText(String paramString)
  {
    this._$2 = paramString;
  }

  public Logistics getLogistics()
  {
    return this._$1;
  }

  public void setLogistics(Logistics paramLogistics)
  {
    this._$1 = paramLogistics;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof LogisticsText))
      return false;
    LogisticsText localLogisticsText = (LogisticsText)paramObject;
    if ((null == getId()) || (null == localLogisticsText.getId()))
      return false;
    return getId().equals(localLogisticsText.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseLogisticsText
 * JD-Core Version:    0.6.2
 */