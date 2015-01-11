package guda.shop.core.entity.base;

import java.io.Serializable;

public abstract class BaseEmailSender
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "EmailSender";
    public static String PROP_PASSWORD = "password";
    public static String PROP_HOST = "host";
    public static String PROP_ENCODING = "encoding";
    public static String PROP_PERSONAL = "personal";
    public static String PROP_USERNAME = "username";
    private String _$5;
    private String _$4;
    private String _$3;
    private String _$2;
    private String _$1;

    public BaseEmailSender() {
        initialize();
    }

    protected void initialize() {
    }

    public String getHost() {
        return this._$5;
    }

    public void setHost(String paramString) {
        this._$5 = paramString;
    }

    public String getEncoding() {
        return this._$4;
    }

    public void setEncoding(String paramString) {
        this._$4 = paramString;
    }

    public String getUsername() {
        return this._$3;
    }

    public void setUsername(String paramString) {
        this._$3 = paramString;
    }

    public String getPassword() {
        return this._$2;
    }

    public void setPassword(String paramString) {
        this._$2 = paramString;
    }

    public String getPersonal() {
        return this._$1;
    }

    public void setPersonal(String paramString) {
        this._$1 = paramString;
    }

    public String toString() {
        return super.toString();
    }
}

