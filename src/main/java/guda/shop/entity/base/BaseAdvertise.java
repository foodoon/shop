package guda.shop.entity.base;

import guda.shop.cms.entity.Adspace;
import com.jspgou.cms.entity.Advertise;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public abstract class BaseAdvertise
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "Advertise";
  public static String PROP_NAME = "name";
  public static String PROP_WEIGHT = "weight";
  public static String PROP_ADSPACE = "adspace";
  public static String PROP_ENABLE = "enable";
  public static String PROP_CLICK_COUNT = "clickCount";
  public static String PROP_DSIPLAY_COUNT = "displayCount";
  public static String PROP_ID = "id";
  public static String PROP_END_TIME = "endTime";
  public static String PROP_START_TIME = "startTime";
  private int _$11 = -2147483648;
  private Integer _$10;
  private String _$9;
  private Integer _$8;
  private Integer _$7;
  private Integer _$6;
  private Date _$5;
  private Date _$4;
  private Boolean _$3;
  private Adspace _$2;
  private Map<String, String> _$1;

  public BaseAdvertise()
  {
    initialize();
  }

  public BaseAdvertise(Integer paramInteger)
  {
    setId(paramInteger);
    initialize();
  }

  public BaseAdvertise(Integer paramInteger, Adspace paramAdspace)
  {
    setId(paramInteger);
    setAdspace(paramAdspace);
    initialize();
  }

  protected void initialize()
  {
  }

  public Integer getId()
  {
    return this._$10;
  }

  public void setId(Integer paramInteger)
  {
    this._$10 = paramInteger;
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

  public Integer getWeight()
  {
    return this._$8;
  }

  public void setWeight(Integer paramInteger)
  {
    this._$8 = paramInteger;
  }

  public Integer getDisplayCount()
  {
    return this._$7;
  }

  public void setDisplayCount(Integer paramInteger)
  {
    this._$7 = paramInteger;
  }

  public Integer getClickCount()
  {
    return this._$6;
  }

  public void setClickCount(Integer paramInteger)
  {
    this._$6 = paramInteger;
  }

  public Date getStartTime()
  {
    return this._$5;
  }

  public void setStartTime(Date paramDate)
  {
    this._$5 = paramDate;
  }

  public Date getEndTime()
  {
    return this._$4;
  }

  public void setEndTime(Date paramDate)
  {
    this._$4 = paramDate;
  }

  public Boolean getEnabled()
  {
    return this._$3;
  }

  public void setEnabled(Boolean paramBoolean)
  {
    this._$3 = paramBoolean;
  }

  public Adspace getAdspace()
  {
    return this._$2;
  }

  public void setAdspace(Adspace paramAdspace)
  {
    this._$2 = paramAdspace;
  }

  public Map<String, String> getAttr()
  {
    return this._$1;
  }

  public void setAttr(Map<String, String> paramMap)
  {
    this._$1 = paramMap;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Advertise))
      return false;
    Advertise localAdvertise = (Advertise)paramObject;
    if ((null == getId()) || (null == localAdvertise.getId()))
      return false;
    return getId().equals(localAdvertise.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseAdvertise
 * JD-Core Version:    0.6.2
 */