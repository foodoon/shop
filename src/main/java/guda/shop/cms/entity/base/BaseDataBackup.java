package guda.shop.cms.entity.base;

import guda.shop.cms.entity.DataBackup;
import java.io.Serializable;

public abstract class BaseDataBackup
  implements Serializable
{
  public static String REF = "DataBackup";
  public static String PROP_PASSWORD = "password";
  public static String PROP_USERNAME = "username";
  public static String PROP_ADDRESS = "address";
  public static String PROP_ID = "id";
  public static String PROP_DATA_BASE_NAME = "dataBaseName";
  public static String PROP_DATA_BASE_PATH = "dataBasePath";
  private int _$7 = -2147483648;
  private Integer _$6;
  private String _$5;
  private String _$4;
  private String _$3;
  private String _$2;
  private String _$1;

  public BaseDataBackup()
  {
    initialize();
  }

  public BaseDataBackup(Integer paramInteger)
  {
    setId(paramInteger);
    initialize();
  }

  public BaseDataBackup(Integer paramInteger, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    setId(paramInteger);
    setDataBasePath(paramString1);
    setAddress(paramString2);
    setDataBaseName(paramString3);
    setUsername(paramString4);
    setPassword(paramString5);
    initialize();
  }

  protected void initialize()
  {
  }

  public Integer getId()
  {
    return this._$6;
  }

  public void setId(Integer paramInteger)
  {
    this._$6 = paramInteger;
    this._$7 = -2147483648;
  }

  public String getDataBasePath()
  {
    return this._$5;
  }

  public void setDataBasePath(String paramString)
  {
    this._$5 = paramString;
  }

  public String getAddress()
  {
    return this._$4;
  }

  public void setAddress(String paramString)
  {
    this._$4 = paramString;
  }

  public String getDataBaseName()
  {
    return this._$3;
  }

  public void setDataBaseName(String paramString)
  {
    this._$3 = paramString;
  }

  public String getUsername()
  {
    return this._$2;
  }

  public void setUsername(String paramString)
  {
    this._$2 = paramString;
  }

  public String getPassword()
  {
    return this._$1;
  }

  public void setPassword(String paramString)
  {
    this._$1 = paramString;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof DataBackup))
      return false;
    DataBackup localDataBackup = (DataBackup)paramObject;
    if ((null == getId()) || (null == localDataBackup.getId()))
      return false;
    return getId().equals(localDataBackup.getId());
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
 * Qualified Name:     com.jspgou.cms.entity.base.BaseDataBackup
 * JD-Core Version:    0.6.2
 */