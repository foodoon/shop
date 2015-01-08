package guda.shop.cms.manager;

import guda.shop.common.file.FileWrap;
import guda.shop.common.util.Zipper;
import guda.shop.core.entity.Website;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract interface ResourceMng {
    public abstract List<FileWrap> listFile(String paramString, boolean paramBoolean);

    public abstract void saveFile(String paramString, MultipartFile paramMultipartFile)
            throws IllegalStateException, IOException;

    public abstract boolean createDir(String paramString1, String paramString2);

    public abstract void createFile(String paramString1, String paramString2, String paramString3)
            throws IOException;

    public abstract String readFile(String paramString)
            throws IOException;

    public abstract void updateFile(String paramString1, String paramString2)
            throws IOException;

    public abstract void rename(String paramString1, String paramString2);

    public abstract int delete(String[] paramArrayOfString);

    public abstract String[] getSolutions(String paramString);

    public abstract List<Zipper.FileEntry> export();

    public abstract void imoport(File paramFile, Website paramWebsite)
            throws IOException;
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.ResourceMng
 * JD-Core Version:    0.6.2
 */