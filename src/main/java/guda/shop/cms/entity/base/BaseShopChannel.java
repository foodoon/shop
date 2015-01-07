package guda.shop.cms.entity.base;

import guda.shop.cms.entity.ShopChannel;
import guda.shop.cms.entity.ShopChannelContent;
import guda.shop.core.entity.Website;
import java.io.Serializable;
import java.util.Set;

public abstract class BaseShopChannel
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "ShopChannel";
  public static String PROP_TYPE = "type";
  public static String PROP_CHANNEL_CONTENT = "channelContent";
  public static String PROP_PARAM1 = "param1";
  public static String PROP_RGT = "rgt";
  public static String PROP_WEBSITE = "website";
  public static String PROP_TPL_CHANNEL = "tplChannel";
  public static String PROP_PRIORITY = "priority";
  public static String PROP_TPL_CONTENT = "tplContent";
  public static String PROP_OUTER_URL = "outerUrl";
  public static String PROP_BLANK = "blank";
  public static String PROP_PARAM3 = "param3";
  public static String PROP_LFT = "lft";
  public static String PROP_PARENT = "parent";
  public static String PROP_PATH = "path";
  public static String PROP_DISPLAY = "display";
  public static String PROP_NAME = "name";
  public static String PROP_PARAM2 = "param2";
  public static String PROP_ID = "id";
  private int _$20 = -2147483648;
  private Long _$19;
  private Integer _$18;
  private Integer _$17;
  private Integer _$16;
  private String _$15;
  private String _$14;
  private String _$13;
  private String _$12;
  private String _$11;
  private Integer _$10;
  private Boolean _$9;
  private Boolean _$8;
  private String _$7;
  private String _$6;
  private String _$5;
  private ShopChannelContent _$4;
  private ShopChannel _$3;
  private Website _$2;
  private Set<ShopChannel> _$1;

  public BaseShopChannel()
  {
    initialize();
  }

  public BaseShopChannel(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseShopChannel(Long paramLong, Website paramWebsite, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString, Integer paramInteger4)
  {
    setId(paramLong);
    setWebsite(paramWebsite);
    setLft(paramInteger1);
    setRgt(paramInteger2);
    setType(paramInteger3);
    setName(paramString);
    setPriority(paramInteger4);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$19;
  }

  public void setId(Long paramLong)
  {
    this._$19 = paramLong;
    this._$20 = -2147483648;
  }

  public Integer getLft()
  {
    return this._$18;
  }

  public void setLft(Integer paramInteger)
  {
    this._$18 = paramInteger;
  }

  public Integer getRgt()
  {
    return this._$17;
  }

  public void setRgt(Integer paramInteger)
  {
    this._$17 = paramInteger;
  }

  public Integer getType()
  {
    return this._$16;
  }

  public void setType(Integer paramInteger)
  {
    this._$16 = paramInteger;
  }

  public String getName()
  {
    return this._$15;
  }

  public void setName(String paramString)
  {
    this._$15 = paramString;
  }

  public String getPath()
  {
    return this._$14;
  }

  public void setPath(String paramString)
  {
    this._$14 = paramString;
  }

  public String getOuterUrl()
  {
    return this._$13;
  }

  public void setOuterUrl(String paramString)
  {
    this._$13 = paramString;
  }

  public String getTplChannel()
  {
    return this._$12;
  }

  public void setTplChannel(String paramString)
  {
    this._$12 = paramString;
  }

  public String getTplContent()
  {
    return this._$11;
  }

  public void setTplContent(String paramString)
  {
    this._$11 = paramString;
  }

  public Integer getPriority()
  {
    return this._$10;
  }

  public void setPriority(Integer paramInteger)
  {
    this._$10 = paramInteger;
  }

  public Boolean getBlank()
  {
    return this._$9;
  }

  public void setBlank(Boolean paramBoolean)
  {
    this._$9 = paramBoolean;
  }

  public Boolean getDisplay()
  {
    return this._$8;
  }

  public void setDisplay(Boolean paramBoolean)
  {
    this._$8 = paramBoolean;
  }

  public String getParam1()
  {
    return this._$7;
  }

  public void setParam1(String paramString)
  {
    this._$7 = paramString;
  }

  public String getParam2()
  {
    return this._$6;
  }

  public void setParam2(String paramString)
  {
    this._$6 = paramString;
  }

  public String getParam3()
  {
    return this._$5;
  }

  public void setParam3(String paramString)
  {
    this._$5 = paramString;
  }

  public ShopChannelContent getChannelContent()
  {
    return this._$4;
  }

  public void setChannelContent(ShopChannelContent paramShopChannelContent)
  {
    this._$4 = paramShopChannelContent;
  }

  public ShopChannel getParent()
  {
    return this._$3;
  }

  public void setParent(ShopChannel paramShopChannel)
  {
    this._$3 = paramShopChannel;
  }

  public Website getWebsite()
  {
    return this._$2;
  }

  public void setWebsite(Website paramWebsite)
  {
    this._$2 = paramWebsite;
  }

  public Set<ShopChannel> getChild()
  {
    return this._$1;
  }

  public void setChild(Set<ShopChannel> paramSet)
  {
    this._$1 = paramSet;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof ShopChannel))
      return false;
    ShopChannel localShopChannel = (ShopChannel)paramObject;
    if ((null == getId()) || (null == localShopChannel.getId()))
      return false;
    return getId().equals(localShopChannel.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$20)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$20 = str.hashCode();
    }
    return this._$20;
  }

  public String toString()
  {
    return super.toString();
  }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseShopChannel
 * JD-Core Version:    0.6.2
 */