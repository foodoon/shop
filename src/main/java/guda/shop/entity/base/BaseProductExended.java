package guda.shop.entity.base;

import java.io.Serializable;

public abstract class BaseProductExended
  implements Serializable
{
  public static String REF = "ProductExended";
  public static String PROP_NAME = "name";
  public static String PROP_VALUE = "value";
  private String _$2;
  private String _$1;

  public BaseProductExended()
  {
    initialize();
  }

  public BaseProductExended(String paramString)
  {
    setName(paramString);
    initialize();
  }

  protected void initialize()
  {
  }

  public String getName()
  {
    return this._$2;
  }

  public void setName(String paramString)
  {
    this._$2 = paramString;
  }

  public String getValue()
  {
    return this._$1;
  }

  public void setValue(String paramString)
  {
    this._$1 = paramString;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseProductExended
 * JD-Core Version:    0.6.2
 */