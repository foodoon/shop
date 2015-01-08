package guda.shop.cms.entity.base;

import java.io.Serializable;

public abstract class BaseProductPicture
        implements Serializable {
    public static String REF = "ProductPicture";
    public static String PROP_BIG_PICTURE_PATH = "bigPicturePath";
    public static String PROP_APP_PICTURE_PATH = "appPicturePath";
    public static String PROP_PICTURE_PATH = "picturePath";
    private String _$3;
    private String _$2;
    private String _$1;

    public BaseProductPicture() {
        initialize();
    }

    public BaseProductPicture(String paramString1, String paramString2, String paramString3) {
        setPicturePath(paramString1);
        setBigPicturePath(paramString2);
        setAppPicturePath(paramString3);
        initialize();
    }

    protected void initialize() {
    }

    public String getPicturePath() {
        return this._$3;
    }

    public void setPicturePath(String paramString) {
        this._$3 = paramString;
    }

    public String getBigPicturePath() {
        return this._$2;
    }

    public void setBigPicturePath(String paramString) {
        this._$2 = paramString;
    }

    public String getAppPicturePath() {
        return this._$1;
    }

    public void setAppPicturePath(String paramString) {
        this._$1 = paramString;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseProductPicture
 * JD-Core Version:    0.6.2
 */