package guda.shop.core.entity.base;

import guda.shop.core.entity.Global;
import java.io.Serializable;

public abstract class BaseGlobal
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "Global";
  public static String PROP_CONTEXT_PATH = "contextPath";
  public static String PROP_PORT = "port";
  public static String PROP_ID = "id";
  private int _$8 = -2147483648;
  private Long _$7;
  private String _$6;
  private Integer _$5;
  private String _$4;
  private String _$3;
  private Integer _$2;
  private Integer _$1;

  public BaseGlobal()
  {
    initialize();
  }

  public BaseGlobal(Long paramLong)
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

  public String getContextPath()
  {
    return this._$6;
  }

  public void setContextPath(String paramString)
  {
    this._$6 = paramString;
  }

  public Integer getPort()
  {
    return this._$5;
  }

  public void setPort(Integer paramInteger)
  {
    this._$5 = paramInteger;
  }

  public String getDefImg()
  {
    return this._$4;
  }

  public void setDefImg(String paramString)
  {
    this._$4 = paramString;
  }

  public String getTreaty()
  {
    return this._$3;
  }

  public void setTreaty(String paramString)
  {
    this._$3 = paramString;
  }

  public Integer getActiveScore()
  {
    return this._$2;
  }

  public void setActiveScore(Integer paramInteger)
  {
    this._$2 = paramInteger;
  }

  public Integer getStockWarning()
  {
    return this._$1;
  }

  public void setStockWarning(Integer paramInteger)
  {
    this._$1 = paramInteger;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Global))
      return false;
    Global localGlobal = (Global)paramObject;
    if ((null == getId()) || (null == localGlobal.getId()))
      return false;
    return getId().equals(localGlobal.getId());
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

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.base.BaseGlobal
 * JD-Core Version:    0.6.2
 */