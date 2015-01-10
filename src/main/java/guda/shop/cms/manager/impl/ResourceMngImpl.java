package guda.shop.cms.manager.impl;

import guda.shop.cms.manager.ResourceMng;
import guda.shop.common.file.FileWrap;
import guda.shop.common.util.Zipper;
import guda.shop.common.web.springmvc.RealPathResolver;
import guda.shop.core.entity.Website;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Service
public class ResourceMngImpl
        implements ResourceMng {
    private static final Logger _$2 = LoggerFactory.getLogger(ResourceMngImpl.class);
    private FileFilter filter = new FileFilter() {
        public boolean accept(File paramAnonymousFile) {
            return (paramAnonymousFile.isDirectory()) || (FileWrap.editableExt(FilenameUtils.getExtension(paramAnonymousFile.getName())));
        }
    };
    private RealPathResolver _$1;

    public List<FileWrap> listFile(String paramString, boolean paramBoolean) {
        File localFile1 = new File(this._$1.get(paramString));
        if (localFile1.exists()) {
            File[] arrayOfFile1;
            if (paramBoolean)
                arrayOfFile1 = localFile1.listFiles(this.filter);
            else
                arrayOfFile1 = localFile1.listFiles();
            Arrays.sort(arrayOfFile1, new FileWrap.FileComparator());
            ArrayList localArrayList = new ArrayList(arrayOfFile1.length);
            for (File localFile2 : arrayOfFile1)
                localArrayList.add(new FileWrap(localFile2, this._$1.get("")));
            return localArrayList;
        }
        return new ArrayList(0);
    }

    public boolean createDir(String paramString1, String paramString2) {
        File localFile1 = new File(this._$1.get(paramString1));
        localFile1.mkdirs();
        File localFile2 = new File(localFile1, paramString2);
        return localFile2.mkdir();
    }

    public void saveFile(String paramString, MultipartFile paramMultipartFile)
            throws IllegalStateException, IOException {
        File localFile = new File(this._$1.get(paramString), paramMultipartFile.getOriginalFilename());
        paramMultipartFile.transferTo(localFile);
    }

    public void createFile(String paramString1, String paramString2, String paramString3)
            throws IOException {
        File localFile1 = new File(this._$1.get(paramString1));
        localFile1.mkdirs();
        File localFile2 = new File(localFile1, paramString2);
        FileUtils.writeStringToFile(localFile2, paramString3, "UTF-8");
    }

    public String readFile(String paramString)
            throws IOException {
        File localFile = new File(this._$1.get(paramString));
        return FileUtils.readFileToString(localFile, "UTF-8");
    }

    public void updateFile(String paramString1, String paramString2)
            throws IOException {
        File localFile = new File(this._$1.get(paramString1));
        FileUtils.writeStringToFile(localFile, paramString2, "UTF-8");
    }

    public int delete(String[] paramArrayOfString) {
        int i = 0;
        for (String str : paramArrayOfString) {
            File localFile = new File(this._$1.get(str));
            if (FileUtils.deleteQuietly(localFile))
                i++;
        }
        return i;
    }

    public void rename(String paramString1, String paramString2) {
        File localFile1 = new File(this._$1.get(paramString1));
        File localFile2 = new File(this._$1.get(paramString2));
        localFile1.renameTo(localFile2);
    }

    public String[] getSolutions(String paramString) {
        File localFile = new File(this._$1.get(paramString));
        return localFile.list(new FilenameFilter() {
            public boolean accept(File paramAnonymousFile, String paramAnonymousString) {
                return paramAnonymousFile.isDirectory();
            }
        });
    }

    public List<Zipper.FileEntry> export() {
        ArrayList<Zipper.FileEntry> localArrayList = new ArrayList<Zipper.FileEntry>();
        File localFile1 = new File(this._$1.get("/WEB-INF/front"));
        localArrayList.add(new Zipper.FileEntry("", "", localFile1));
        File localFile2 = new File(this._$1.get("/r/gou/www"));
        if (localFile2.exists())
            for (File localFile3 : localFile2.listFiles())
                localArrayList.add(new Zipper.FileEntry("${res}", localFile3));
        return localArrayList;
    }

    public void imoport(File paramFile, Website paramWebsite)
            throws IOException {
        String str1 = "/r/gou/www";
        String str2 = "/WEB-INF/t/gou";
        ZipFile localZipFile = new ZipFile(paramFile, "GBK");
        byte[] arrayOfByte = new byte[1024];
        InputStream localInputStream = null;
        FileOutputStream localFileOutputStream = null;
        String str5 = null;
        Enumeration localEnumeration = localZipFile.getEntries();
        String str3;
        while (localEnumeration.hasMoreElements()) {
            str3 = ((ZipEntry) localEnumeration.nextElement()).getName();
            if (!str3.startsWith("${res}"))
                str5 = str3.substring(0, str3.indexOf("/"));
        }
        if (str5 == null)
            return;
        localEnumeration = localZipFile.getEntries();
        while (localEnumeration.hasMoreElements()) {
            ZipEntry localZipEntry = (ZipEntry) localEnumeration.nextElement();
            if (!localZipEntry.isDirectory()) {
                str3 = localZipEntry.getName();
                _$2.debug("unzip file：{}", str3);
                String str4;
                if (str3.startsWith("${res}"))
                    str4 = str1 + str3.substring("${res}".length());
                else
                    str4 = str2 + "/" + str3;
                _$2.debug("解压地址：{}", str4);
                File localFile1 = new File(this._$1.get(str4));
                File localFile2 = localFile1.getParentFile();
                if (!localFile2.exists())
                    localFile2.mkdirs();
                try {
                    localInputStream = localZipFile.getInputStream(localZipEntry);
                    localFileOutputStream = new FileOutputStream(localFile1);
                    int i;
                    while ((i = localInputStream.read(arrayOfByte)) != -1)
                        localFileOutputStream.write(arrayOfByte, 0, i);
                } finally {
                    if (localInputStream != null) {
                        localInputStream.close();
                        localInputStream = null;
                    }
                    if (localFileOutputStream != null) {
                        localFileOutputStream.close();
                        localFileOutputStream = null;
                    }
                }
            }
        }
    }

    @Autowired
    public void setRealPathResolver(RealPathResolver paramRealPathResolver) {
        this._$1 = paramRealPathResolver;
    }
}

/* Location:           D:\demo22\jspgou-cms.jar
 * Qualified Name:     com.jspgou.cms.manager.impl.ResourceMngImpl
 * JD-Core Version:    0.6.2
 */