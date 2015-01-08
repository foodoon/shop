package guda.shop.core.entity.base;

import java.io.Serializable;

public abstract class BaseMessageTemplate
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String REF = "MessageTemplate";
    public static String PROP_SUBJECT = "subject";
    public static String PROP_TEXT = "text";
    private String _$4;
    private String _$3;
    private String _$2;
    private String _$1;

    public BaseMessageTemplate() {
        initialize();
    }

    protected void initialize() {
    }

    public String getSubject() {
        return this._$4;
    }

    public void setSubject(String paramString) {
        this._$4 = paramString;
    }

    public String getText() {
        return this._$3;
    }

    public void setText(String paramString) {
        this._$3 = paramString;
    }

    public String getActiveTitle() {
        return this._$2;
    }

    public void setActiveTitle(String paramString) {
        this._$2 = paramString;
    }

    public String getActiveTxt() {
        return this._$1;
    }

    public void setActiveTxt(String paramString) {
        this._$1 = paramString;
    }

    public String toString() {
        return super.toString();
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.entity.base.BaseMessageTemplate
 * JD-Core Version:    0.6.2
 */