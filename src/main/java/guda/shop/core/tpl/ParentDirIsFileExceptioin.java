package guda.shop.core.tpl;

public class ParentDirIsFileExceptioin extends RuntimeException {
    private String _$1;

    public ParentDirIsFileExceptioin(String paramString) {
        this._$1 = paramString;
    }

    public String getMessage() {
        return "parent directory is a file: " + this._$1;
    }

    public String getParentDir() {
        return this._$1;
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.tpl.ParentDirIsFileExceptioin
 * JD-Core Version:    0.6.2
 */