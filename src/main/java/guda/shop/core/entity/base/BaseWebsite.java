package guda.shop.core.entity.base;

import guda.shop.core.entity.Admin;
import guda.shop.core.entity.EmailSender;
import guda.shop.core.entity.Global;
import guda.shop.core.entity.MessageTemplate;
import guda.shop.core.entity.Website;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public abstract class BaseWebsite
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public static String REF = "Website";
  public static String PROP_CONTROL_NAME_MINLEN = "controlNameMinlen";
  public static String PROP_RGT = "rgt";
  public static String PROP_CONTROL_ADMIN_IPS = "controlAdminIps";
  public static String PROP_LOCALE_ADMIN = "localeAdmin";
  public static String PROP_CREATE_TIME = "createTime";
  public static String PROP_CLOSE = "close";
  public static String PROP_CURRENT_SYSTEM = "currentSystem";
  public static String PROP_SUFFIX = "suffix";
  public static String PROP_FRONT_CONTENT_TYPE = "frontContentType";
  public static String PROP_PASSWORD = "password";
  public static String PROP_MOBILE = "mobile";
  public static String PROP_DOMAIN_ALIAS = "domainAlias";
  public static String PROP_LOCALE_FRONT = "localeFront";
  public static String PROP_NAME = "name";
  public static String PROP_CONTROL_FRONT_IPS = "controlFrontIps";
  public static String PROP_GLOBAL = "global";
  public static String PROP_HOST = "host";
  public static String PROP_DOMAIN = "domain";
  public static String PROP_RES_PATH = "resPath";
  public static String PROP_BASE_DOMAIN = "baseDomain";
  public static String PROP_PHONE = "phone";
  public static String PROP_CLOSE_REASON = "closeReason";
  public static String PROP_COPYRIGHT = "copyright";
  public static String PROP_RECORD_CODE = "recordCode";
  public static String PROP_EMAIL = "email";
  public static String PROP_ENCODING = "encoding";
  public static String PROP_FRONT_ENCODING = "frontEncoding";
  public static String PROP_SHORT_NAME = "shortName";
  public static String PROP_LFT = "lft";
  public static String PROP_PARENT = "parent";
  public static String PROP_COMPANY = "company";
  public static String PROP_PERSONAL = "personal";
  public static String PROP_CONTROL_RESERVED = "controlReserved";
  public static String PROP_ADMIN = "admin";
  public static String PROP_ID = "id";
  public static String PROP_USERNAME = "username";
  public static String PROP_RELATIVE_PATH = "relativePath";
  private int _$37 = -2147483648;
  private Long _$36;
  private String _$35;
  private String _$34;
  private String _$33;
  private String _$32;
  private String _$31;
  private Integer _$30;
  private Integer _$29;
  private Date _$28;
  private String _$27;
  private String _$26;
  private String _$25;
  private String _$24;
  private Boolean _$23;
  private Boolean _$22;
  private String _$21;
  private String _$20;
  private String _$19;
  private String _$18;
  private String _$17;
  private Integer _$16;
  private String _$15;
  private String _$14;
  private String _$13;
  private String _$12;
  private String _$11;
  private String _$10;
  private String _$9;
  private String _$8;
  private String _$7;
  private Boolean _$6;
  EmailSender emailSender;
  private Admin _$5;
  private Website _$4;
  private Global _$3;
  private Set<Website> _$2;
  private Map<String, MessageTemplate> _$1;

  public BaseWebsite()
  {
    initialize();
  }

  public BaseWebsite(Long paramLong)
  {
    setId(paramLong);
    initialize();
  }

  public BaseWebsite(Long paramLong, Global paramGlobal, String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger1, Integer paramInteger2, Date paramDate, Boolean paramBoolean1, Boolean paramBoolean2, String paramString5, String paramString6, String paramString7, String paramString8, Integer paramInteger3, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14)
  {
    setId(paramLong);
    setGlobal(paramGlobal);
    setDomain(paramString1);
    setName(paramString2);
    setCurrentSystem(paramString3);
    setSuffix(paramString4);
    setLft(paramInteger1);
    setRgt(paramInteger2);
    setCreateTime(paramDate);
    setClose(paramBoolean1);
    setRelativePath(paramBoolean2);
    setFrontEncoding(paramString5);
    setFrontContentType(paramString6);
    setLocaleFront(paramString7);
    setLocaleAdmin(paramString8);
    setControlNameMinlen(paramInteger3);
    setCompany(paramString9);
    setCopyright(paramString10);
    setRecordCode(paramString11);
    setEmail(paramString12);
    setPhone(paramString13);
    setMobile(paramString14);
    initialize();
  }

  protected void initialize()
  {
  }

  public Long getId()
  {
    return this._$36;
  }

  public void setId(Long paramLong)
  {
    this._$36 = paramLong;
    this._$37 = -2147483648;
  }

  public String getDomain()
  {
    return this._$35;
  }

  public void setDomain(String paramString)
  {
    this._$35 = paramString;
  }

  public String getName()
  {
    return this._$34;
  }

  public void setName(String paramString)
  {
    this._$34 = paramString;
  }

  public String getAdditionalTitle()
  {
    return this._$33;
  }

  public void setAdditionalTitle(String paramString)
  {
    this._$33 = paramString;
  }

  public String getCurrentSystem()
  {
    return this._$32;
  }

  public void setCurrentSystem(String paramString)
  {
    this._$32 = paramString;
  }

  public String getSuffix()
  {
    return this._$31;
  }

  public void setSuffix(String paramString)
  {
    this._$31 = paramString;
  }

  public Integer getLft()
  {
    return this._$30;
  }

  public void setLft(Integer paramInteger)
  {
    this._$30 = paramInteger;
  }

  public Integer getRgt()
  {
    return this._$29;
  }

  public void setRgt(Integer paramInteger)
  {
    this._$29 = paramInteger;
  }

  public Date getCreateTime()
  {
    return this._$28;
  }

  public void setCreateTime(Date paramDate)
  {
    this._$28 = paramDate;
  }

  public String getBaseDomain()
  {
    return this._$27;
  }

  public void setBaseDomain(String paramString)
  {
    this._$27 = paramString;
  }

  public String getDomainAlias()
  {
    return this._$26;
  }

  public void setDomainAlias(String paramString)
  {
    this._$26 = paramString;
  }

  public String getShortName()
  {
    return this._$25;
  }

  public void setShortName(String paramString)
  {
    this._$25 = paramString;
  }

  public String getCloseReason()
  {
    return this._$24;
  }

  public void setCloseReason(String paramString)
  {
    this._$24 = paramString;
  }

  public Boolean getClose()
  {
    return this._$23;
  }

  public void setClose(Boolean paramBoolean)
  {
    this._$23 = paramBoolean;
  }

  public Boolean getRelativePath()
  {
    return this._$22;
  }

  public void setRelativePath(Boolean paramBoolean)
  {
    this._$22 = paramBoolean;
  }

  public String getFrontEncoding()
  {
    return this._$21;
  }

  public void setFrontEncoding(String paramString)
  {
    this._$21 = paramString;
  }

  public String getFrontContentType()
  {
    return this._$20;
  }

  public void setFrontContentType(String paramString)
  {
    this._$20 = paramString;
  }

  public String getLocaleFront()
  {
    return this._$19;
  }

  public void setLocaleFront(String paramString)
  {
    this._$19 = paramString;
  }

  public String getLocaleAdmin()
  {
    return this._$18;
  }

  public void setLocaleAdmin(String paramString)
  {
    this._$18 = paramString;
  }

  public String getControlReserved()
  {
    return this._$17;
  }

  public void setControlReserved(String paramString)
  {
    this._$17 = paramString;
  }

  public Integer getControlNameMinlen()
  {
    return this._$16;
  }

  public void setControlNameMinlen(Integer paramInteger)
  {
    this._$16 = paramInteger;
  }

  public String getControlFrontIps()
  {
    return this._$15;
  }

  public void setControlFrontIps(String paramString)
  {
    this._$15 = paramString;
  }

  public String getControlAdminIps()
  {
    return this._$14;
  }

  public void setControlAdminIps(String paramString)
  {
    this._$14 = paramString;
  }

  public String getCompany()
  {
    return this._$13;
  }

  public void setCompany(String paramString)
  {
    this._$13 = paramString;
  }

  public String getCopyright()
  {
    return this._$12;
  }

  public void setCopyright(String paramString)
  {
    this._$12 = paramString;
  }

  public String getRecordCode()
  {
    return this._$11;
  }

  public void setRecordCode(String paramString)
  {
    this._$11 = paramString;
  }

  public String getEmail()
  {
    return this._$10;
  }

  public void setEmail(String paramString)
  {
    this._$10 = paramString;
  }

  public String getPhone()
  {
    return this._$9;
  }

  public void setPhone(String paramString)
  {
    this._$9 = paramString;
  }

  public String getMobile()
  {
    return this._$8;
  }

  public void setMobile(String paramString)
  {
    this._$8 = paramString;
  }

  public EmailSender getEmailSender()
  {
    return this.emailSender;
  }

  public void setEmailSender(EmailSender paramEmailSender)
  {
    this.emailSender = paramEmailSender;
  }

  public Admin getAdmin()
  {
    return this._$5;
  }

  public void setAdmin(Admin paramAdmin)
  {
    this._$5 = paramAdmin;
  }

  public Website getParent()
  {
    return this._$4;
  }

  public void setParent(Website paramWebsite)
  {
    this._$4 = paramWebsite;
  }

  public Global getGlobal()
  {
    return this._$3;
  }

  public void setGlobal(Global paramGlobal)
  {
    this._$3 = paramGlobal;
  }

  public Set<Website> getChild()
  {
    return this._$2;
  }

  public void setChild(Set<Website> paramSet)
  {
    this._$2 = paramSet;
  }

  public Map<String, MessageTemplate> getMessages()
  {
    return this._$1;
  }

  public void setMessages(Map<String, MessageTemplate> paramMap)
  {
    this._$1 = paramMap;
  }

  public boolean equals(Object paramObject)
  {
    if (null == paramObject)
      return false;
    if (!(paramObject instanceof Website))
      return false;
    Website localWebsite = (Website)paramObject;
    if ((null == getId()) || (null == localWebsite.getId()))
      return false;
    return getId().equals(localWebsite.getId());
  }

  public int hashCode()
  {
    if (-2147483648 == this._$37)
    {
      if (null == getId())
        return super.hashCode();
      String str = getClass().getName() + ":" + getId().hashCode();
      this._$37 = str.hashCode();
    }
    return this._$37;
  }

  public String toString()
  {
    return super.toString();
  }

  public void setVersion(String paramString)
  {
    this._$7 = paramString;
  }

  public String getVersion()
  {
    return this._$7;
  }

  public void setRestart(Boolean paramBoolean)
  {
    this._$6 = paramBoolean;
  }

  public Boolean getRestart()
  {
    return this._$6;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.base.BaseWebsite
 * JD-Core Version:    0.6.2
 */