package guda.shop.core.entity.base;

import guda.shop.core.entity.Global;

import java.io.Serializable;

public abstract class BaseGlobal
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "Global";
    public static String PROP_CONTEXT_PATH = "contextPath";
    public static String PROP_PORT = "port";
    public static String PROP_ID = "id";
    private int hash = -2147483648;
    private Long id;
    private String contextPath;
    private Integer port;
    private String defImg;
    private String treaty;
    private Integer activeScore;
    private Integer stockWarning;

    public BaseGlobal() {
        initialize();
    }

    public BaseGlobal(Long paramLong) {
        setId(paramLong);
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

    public String getContextPath() {
        return this.contextPath;
    }

    public void setContextPath(String paramString) {
        this.contextPath = paramString;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer paramInteger) {
        this.port = paramInteger;
    }

    public String getDefImg() {
        return this.defImg;
    }

    public void setDefImg(String paramString) {
        this.defImg = paramString;
    }

    public String getTreaty() {
        return this.treaty;
    }

    public void setTreaty(String paramString) {
        this.treaty = paramString;
    }

    public Integer getActiveScore() {
        return this.activeScore;
    }

    public void setActiveScore(Integer paramInteger) {
        this.activeScore = paramInteger;
    }

    public Integer getStockWarning() {
        return this.stockWarning;
    }

    public void setStockWarning(Integer paramInteger) {
        this.stockWarning = paramInteger;
    }

    public boolean equals(Object paramObject) {
        if (null == paramObject)
            return false;
        if (!(paramObject instanceof Global))
            return false;
        Global localGlobal = (Global) paramObject;
        if ((null == getId()) || (null == localGlobal.getId()))
            return false;
        return getId().equals(localGlobal.getId());
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
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.base.BaseGlobal
 * JD-Core Version:    0.6.2
 */