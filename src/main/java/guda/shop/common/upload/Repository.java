package guda.shop.common.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface Repository {
    public abstract boolean store(String paramString, InputStream paramInputStream)
            throws IOException;

    public abstract boolean retrieve(String paramString, OutputStream paramOutputStream);
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.upload.Repository
 * JD-Core Version:    0.6.2
 */