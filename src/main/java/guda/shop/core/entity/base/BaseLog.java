package guda.shop.core.entity.base;

import guda.shop.core.entity.Log;
import guda.shop.core.entity.User;
import guda.shop.core.entity.Website;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseLog
        implements Serializable {
    public static String REF = "Log";
    public static String PROP_USER = "user";
    public static String PROP_IP = "ip";
    public static String PROP_CATEGORY = "category";
    public static String PROP_SITE = "site";
    public static String PROP_TIME = "time";
    public static String PROP_URL = "url";
    public static String PROP_ID = "id";
    public static String PROP_CONTENT = "content";
    public static String PROP_TITLE = "title";
    private int _$10 = -2147483648;
    private Long _$9;
    private Integer _$8;
    private Date _$7;
    private String _$6;
    private String _$5;
    private String _$4;
    private String _$3;
    private User _$2;
    private Website _$1;

    public BaseLog() {
        initialize();
    }

    public BaseLog(Long paramLong) {
        setId(paramLong);
        initialize();
    }

    public BaseLog(Long paramLong, Integer paramInteger, Date paramDate) {
        setId(paramLong);
        setCategory(paramInteger);
        setTime(paramDate);
        initialize();
    }

    protected void initialize() {
    }

    public Long getId() {
        return this._$9;
    }

    public void setId(Long paramLong) {
        this._$9 = paramLong;
        this._$10 = -2147483648;
    }

    public Integer getCategory() {
        return this._$8;
    }

    public void setCategory(Integer paramInteger) {
        this._$8 = paramInteger;
    }

    public Date getTime() {
        return this._$7;
    }

    public void setTime(Date paramDate) {
        this._$7 = paramDate;
    }

    public String getIp() {
        return this._$6;
    }

    public void setIp(String paramString) {
        this._$6 = paramString;
    }

    public String getUrl() {
        return this._$5;
    }

    public void setUrl(String paramString) {
        this._$5 = paramString;
    }

    public String getTitle() {
        return this._$4;
    }

    public void setTitle(String paramString) {
        this._$4 = paramString;
    }

    public String getContent() {
        return this._$3;
    }

    public void setContent(String paramString) {
        this._$3 = paramString;
    }

    public User getUser() {
        return this._$2;
    }

    public void setUser(User paramUser) {
        this._$2 = paramUser;
    }

    public Website getSite() {
        return this._$1;
    }

    public void setSite(Website paramWebsite) {
        this._$1 = paramWebsite;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Log))
            return false;
        Log localLog = (Log) paramObject;
        if ((null == getId()) || (null == localLog.getId()))
            return false;
        return getId().equals(localLog.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$10) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$10 = str.hashCode();
        }
        return this._$10;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.base.BaseLog
 * JD-Core Version:    0.6.2
 */