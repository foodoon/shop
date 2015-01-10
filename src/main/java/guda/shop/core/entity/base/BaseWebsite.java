package guda.shop.core.entity.base;

import guda.shop.core.entity.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public abstract class BaseWebsite
        implements Serializable {
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
    EmailSender emailSender;
    private int hash = -2147483648;
    private Long id;
    private String domain;
    private String name;
    private String additionalTitle;
    private String currentSystem;
    private String suffix;
    private Integer lft;
    private Integer rgt;
    private Date createTime;
    private String baseDomain;
    private String domainAlias;
    private String shortName;
    private String closeReason;
    private Boolean close;
    private Boolean relativePath;
    private String frontEncoding;
    private String frontContentType;
    private String localeFront;
    private String localeAdmin;
    private String controlReserved;
    private Integer controlNameMinlen;
    private String controlFrontIps;
    private String controlAdminIps;
    private String company;
    private String copyright;
    private String recordCode;
    private String email;
    private String phone;
    private String mobile;
    private String version;
    private Boolean aBoolean;
    private Admin admin;
    private Website website;
    private Global global;
    private Set<Website> websiteSet;
    private Map<String, MessageTemplate> _$1;

    public BaseWebsite() {
        initialize();
    }

    public BaseWebsite(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseWebsite(Long paramLong, Global paramGlobal, String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger1, Integer paramInteger2, Date paramDate, Boolean paramBoolean1, Boolean paramBoolean2, String paramString5, String paramString6, String paramString7, String paramString8, Integer paramInteger3, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14) {
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

    protected void initialize() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long paramLong) {
        this.id = paramLong;
        this.hash = -2147483648;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String paramString) {
        this.domain = paramString;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public String getAdditionalTitle() {
        return this.additionalTitle;
    }

    public void setAdditionalTitle(String paramString) {
        this.additionalTitle = paramString;
    }

    public String getCurrentSystem() {
        return this.currentSystem;
    }

    public void setCurrentSystem(String paramString) {
        this.currentSystem = paramString;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String paramString) {
        this.suffix = paramString;
    }

    public Integer getLft() {
        return this.lft;
    }

    public void setLft(Integer paramInteger) {
        this.lft = paramInteger;
    }

    public Integer getRgt() {
        return this.rgt;
    }

    public void setRgt(Integer paramInteger) {
        this.rgt = paramInteger;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date paramDate) {
        this.createTime = paramDate;
    }

    public String getBaseDomain() {
        return this.baseDomain;
    }

    public void setBaseDomain(String paramString) {
        this.baseDomain = paramString;
    }

    public String getDomainAlias() {
        return this.domainAlias;
    }

    public void setDomainAlias(String paramString) {
        this.domainAlias = paramString;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String paramString) {
        this.shortName = paramString;
    }

    public String getCloseReason() {
        return this.closeReason;
    }

    public void setCloseReason(String paramString) {
        this.closeReason = paramString;
    }

    public Boolean getClose() {
        return this.close;
    }

    public void setClose(Boolean paramBoolean) {
        this.close = paramBoolean;
    }

    public Boolean getRelativePath() {
        return this.relativePath;
    }

    public void setRelativePath(Boolean paramBoolean) {
        this.relativePath = paramBoolean;
    }

    public String getFrontEncoding() {
        return this.frontEncoding;
    }

    public void setFrontEncoding(String paramString) {
        this.frontEncoding = paramString;
    }

    public String getFrontContentType() {
        return this.frontContentType;
    }

    public void setFrontContentType(String paramString) {
        this.frontContentType = paramString;
    }

    public String getLocaleFront() {
        return this.localeFront;
    }

    public void setLocaleFront(String paramString) {
        this.localeFront = paramString;
    }

    public String getLocaleAdmin() {
        return this.localeAdmin;
    }

    public void setLocaleAdmin(String paramString) {
        this.localeAdmin = paramString;
    }

    public String getControlReserved() {
        return this.controlReserved;
    }

    public void setControlReserved(String paramString) {
        this.controlReserved = paramString;
    }

    public Integer getControlNameMinlen() {
        return this.controlNameMinlen;
    }

    public void setControlNameMinlen(Integer paramInteger) {
        this.controlNameMinlen = paramInteger;
    }

    public String getControlFrontIps() {
        return this.controlFrontIps;
    }

    public void setControlFrontIps(String paramString) {
        this.controlFrontIps = paramString;
    }

    public String getControlAdminIps() {
        return this.controlAdminIps;
    }

    public void setControlAdminIps(String paramString) {
        this.controlAdminIps = paramString;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String paramString) {
        this.company = paramString;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String paramString) {
        this.copyright = paramString;
    }

    public String getRecordCode() {
        return this.recordCode;
    }

    public void setRecordCode(String paramString) {
        this.recordCode = paramString;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String paramString) {
        this.email = paramString;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String paramString) {
        this.phone = paramString;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String paramString) {
        this.mobile = paramString;
    }

    public EmailSender getEmailSender() {
        return this.emailSender;
    }

    public void setEmailSender(EmailSender paramEmailSender) {
        this.emailSender = paramEmailSender;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    public void setAdmin(Admin paramAdmin) {
        this.admin = paramAdmin;
    }

    public Website getParent() {
        return this.website;
    }

    public void setParent(Website paramWebsite) {
        this.website = paramWebsite;
    }

    public Global getGlobal() {
        return this.global;
    }

    public void setGlobal(Global paramGlobal) {
        this.global = paramGlobal;
    }

    public Set<Website> getChild() {
        return this.websiteSet;
    }

    public void setChild(Set<Website> paramSet) {
        this.websiteSet = paramSet;
    }

    public Map<String, MessageTemplate> getMessages() {
        return this._$1;
    }

    public void setMessages(Map<String, MessageTemplate> paramMap) {
        this._$1 = paramMap;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Website))
            return false;
        Website localWebsite = (Website) paramObject;
        if ((null == getId()) || (null == localWebsite.getId()))
            return false;
        return getId().equals(localWebsite.getId());
    }

    public int hashCode() {
        if (-2147483648 == this.hash) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this.hash = str.hashCode();
        }
        return this.hash;
    }

    public String toString() {
        return super.toString();
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String paramString) {
        this.version = paramString;
    }

    public Boolean getRestart() {
        return this.aBoolean;
    }

    public void setRestart(Boolean paramBoolean) {
        this.aBoolean = paramBoolean;
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.base.BaseWebsite
 * JD-Core Version:    0.6.2
 */