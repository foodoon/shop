package guda.shop.entity.base;

import guda.shop.cms.entity.Logistics;
import com.jspgou.cms.entity.LogisticsText;
import java.io.Serializable;
import java.util.Set;

public abstract class BaseLogistics
  implements Serializable
{
  public static String REF = "Logistics";
  public static String PROP_NAME = "name";
  public static String PROP_ID = "id";
  public static String PROP_WEB_URL = "webUrl";
  public static String PROP_PRIORITY = "priority";
  public static String PROP_LOGO_PATH = "logoPath";
  private int _$7 = -2147483648;
  private Long _$6;
  private String _$5;
  private String _$4;
  private String _$3;
  private Integer _$2;
  private Set<LogisticsText> _$1;

  public BaseLogistics()
  {
    initialize();
  }

  public BaseLogistics(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseLogistics(Long paramLong, String paramString, Integer paramInteger)
  {
    setId(paramLong);
    setName(paramString);
    setPriority(paramInteger);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$6;
  }

  public void setId(Long paramLong)
  {
    this._$6 = paramLong;
    this._$7 = -2147483648;
  }

  public String getName()
  {
    return this._$5;
  }

  public void setName(String paramString)
  {
    this._$5 = paramString;
  }

  public String getWebUrl()
  {
    return this._$4;
  }

  public void setWebUrl(String paramString)
  {
    this._$4 = paramString;
  }

  public String getLogoPath()
  {
    return this._$3;
  }

  public void setLogoPath(String paramString)
  {
    this._$3 = paramString;
  }

  public Integer getPriority()
  {
    return this._$2;
  }

  public void setPriority(Integer paramInteger)
  {
    this._$2 = paramInteger;
  }

  public Set<LogisticsText> getLogisticsTextSet()
  {
    return this._$1;
  }

  public void setLogisticsTextSet(Set<LogisticsText> paramSet)
  {
    this._$1 = paramSet;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Logistics))
      return false;
    Logistics localLogistics = (Logistics)paramObject;
    if ((null == getId()) || (null == localLogistics.getId()))
      return false;
    return getId().equals(localLogistics.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$7)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$7 = str.hashCode();
    }
    return this._$7;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseLogistics
 * JD-Core Version:    0.6.2
 */