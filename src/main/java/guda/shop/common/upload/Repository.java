package guda.shop.common.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface Repository {
    public abstract boolean store(String paramString, InputStream paramInputStream)
            throws IOException;

    public abstract boolean retrieve(String paramString, OutputStream paramOutputStream);
}

