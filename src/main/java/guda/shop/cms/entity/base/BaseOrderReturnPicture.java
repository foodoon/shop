package guda.shop.cms.entity.base;

import java.io.Serializable;

public abstract class BaseOrderReturnPicture
        implements Serializable {
    public static String REF = "OrderReturnPicture";
    public static String PROP_DESCRIPTION = "description";
    public static String PROP_IMG_PATH = "imgPath";
    private String _$2;
    private String _$1;

    public BaseOrderReturnPicture() {
        initialize();
    }

    public BaseOrderReturnPicture(String paramString) {
        setImgPath(paramString);
        initialize();
    }

    protected void initialize() {
    }

    public String getImgPath() {
        return this._$2;
    }

    public void setImgPath(String paramString) {
        this._$2 = paramString;
    }

    public String getDescription() {
        return this._$1;
    }

    public void setDescription(String paramString) {
        this._$1 = paramString;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.entity.base.BaseOrderReturnPicture
 * JD-Core Version:    0.6.2
 */