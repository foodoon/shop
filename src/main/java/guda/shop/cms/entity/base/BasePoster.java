package guda.shop.cms.entity.base;

import guda.shop.cms.entity.Poster;

import java.io.Serializable;
import java.util.Date;

public abstract class BasePoster
        implements Serializable {
    public static String REF = "Poster";
    public static String PROP_TIME = "time";
    public static String PROP_PIC_URL = "picUrl";
    public static String PROP_INTER_URL = "interUrl";
    public static String PROP_ID = "id";
    private int _$5 = -2147483648;
    private Integer _$4;
    private String _$3;
    private Date _$2;
    private String _$1;

    public BasePoster() {
        initialize();
    }

    public BasePoster(Integer paramInteger) {
        setId(paramInteger);
        initialize();
    }

    public BasePoster(Integer paramInteger, String paramString) {
        setId(paramInteger);
        setPicUrl(paramString);
        initialize();
    }

    protected void initialize() {
    }

    public Integer getId() {
        return this._$4;
    }

    public void setId(Integer paramInteger) {
        this._$4 = paramInteger;
        this._$5 = -2147483648;
    }

    public String getPicUrl() {
        return this._$3;
    }

    public void setPicUrl(String paramString) {
        this._$3 = paramString;
    }

    public Date getTime() {
        return this._$2;
    }

    public void setTime(Date paramDate) {
        this._$2 = paramDate;
    }

    public String getInterUrl() {
        return this._$1;
    }

    public void setInterUrl(String paramString) {
        this._$1 = paramString;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Poster))
            return false;
        Poster localPoster = (Poster) paramObject;
        if ((null == getId()) || (null == localPoster.getId()))
            return false;
        return getId().equals(localPoster.getId());
    }

    public int hashCode() {
        if (-2147483648 == this._$5) {
            if (null == getId())
                return super.hashCode();
            String str = getClass().getName() + ":" + getId().hashCode();
            this._$5 = str.hashCode();
        }
        return this._$5;
    }

    public String toString() {
        return super.toString();
    }
}

