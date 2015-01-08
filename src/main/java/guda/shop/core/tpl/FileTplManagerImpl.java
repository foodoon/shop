package guda.shop.core.tpl;

import guda.shop.common.web.springmvc.RealPathResolver;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileTplManagerImpl
        implements TplManager {
    private static Logger _$3 = LoggerFactory.getLogger(FileTplManagerImpl.class);
    private String _$2;
    private RealPathResolver _$1;

    public int delete(String[] paramArrayOfString) {
        int i = 0;
        for (String str : paramArrayOfString) {
            File localFile = new File(this._$1.get(str));
            if (localFile.isDirectory()) {
                if (FileUtils.deleteQuietly(localFile))
                    i++;
            } else if (localFile.delete())
                i++;
        }
        return i;
    }

    public Tpl get(String paramString) {
        File localFile = new File(this._$1.get(paramString));
        if (localFile.exists())
            return new FileTpl(localFile, this._$2);
        return null;
    }

    public List<Tpl> getListByPrefix(String paramString) {
        File localFile1 = new File(this._$1.get(paramString));
        String str = paramString.substring(paramString.lastIndexOf("/") + 1);
        File localFile2;
        if (paramString.endsWith("/")) {
            str = "";
            localFile2 = localFile1;
        } else {
            localFile2 = localFile1.getParentFile();
        }
        if (localFile2.exists()) {
            File[] arrayOfFile1 = localFile2.listFiles(new PrefixFilter(str));
            if (arrayOfFile1 != null) {
                ArrayList localArrayList = new ArrayList();
                for (File localFile3 : arrayOfFile1)
                    localArrayList.add(new FileTpl(localFile3, this._$2));
                return localArrayList;
            }
            return new ArrayList(0);
        }
        return new ArrayList(0);
    }

    public List<String> getNameListByPrefix(String paramString) {
        List localList = getListByPrefix(paramString);
        ArrayList localArrayList = new ArrayList(localList.size());
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext()) {
            Tpl localTpl = (Tpl) localIterator.next();
            localArrayList.add(localTpl.getName());
        }
        return localArrayList;
    }

    public List<Tpl> getChild(String paramString) {
        File localFile1 = new File(this._$1.get(paramString));
        File[] arrayOfFile1 = localFile1.listFiles();
        if (arrayOfFile1 != null) {
            ArrayList localArrayList = new ArrayList(arrayOfFile1.length);
            for (File localFile2 : arrayOfFile1)
                localArrayList.add(new FileTpl(localFile2, this._$2));
            return localArrayList;
        }
        return new ArrayList(0);
    }

    public void rename(String paramString1, String paramString2) {
        String str1 = this._$1.get(paramString1);
        File localFile1 = new File(str1);
        String str2 = this._$1.get(paramString2);
        File localFile2 = new File(str2);
        try {
            if (localFile1.isDirectory())
                FileUtils.moveDirectory(localFile1, localFile2);
            else
                FileUtils.moveFile(localFile1, localFile2);
        } catch (IOException localIOException) {
            _$3.error("Move template error: " + paramString1 + " to " + paramString2, localIOException);
        }
    }

    public void save(String paramString1, String paramString2, boolean paramBoolean) {
        String str = this._$1.get(paramString1);
        File localFile = new File(str);
        if (paramBoolean)
            localFile.mkdirs();
        else
            try {
                FileUtils.writeStringToFile(localFile, paramString2, "UTF-8");
            } catch (IOException localIOException) {
                _$3.error("Save template error: " + paramString1, localIOException);
                throw new RuntimeException(localIOException);
            }
    }

    public void save(String paramString, MultipartFile paramMultipartFile) {
        File localFile = new File(this._$1.get(paramString), paramMultipartFile.getOriginalFilename());
        try {
            paramMultipartFile.transferTo(localFile);
        } catch (IllegalStateException localIllegalStateException) {
            _$3.error("upload template error!", localIllegalStateException);
        } catch (IOException localIOException) {
            _$3.error("upload template error!", localIOException);
        }
    }

    public void update(String paramString1, String paramString2) {
        String str = this._$1.get(paramString1);
        File localFile = new File(str);
        try {
            FileUtils.writeStringToFile(localFile, paramString2, "UTF-8");
        } catch (IOException localIOException) {
            _$3.error("Save template error: " + paramString1, localIOException);
            throw new RuntimeException(localIOException);
        }
    }

    @Autowired
    public void setRealPathResolver(RealPathResolver paramRealPathResolver) {
        this._$1 = paramRealPathResolver;
        this._$2 = paramRealPathResolver.get("");
    }

    private static class PrefixFilter
            implements FileFilter {
        private String _$1;

        public PrefixFilter(String paramString) {
            this._$1 = paramString;
        }

        public boolean accept(File paramFile) {
            String str = paramFile.getName();
            return (paramFile.isFile()) && (str.startsWith(this._$1));
        }
    }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.tpl.FileTplManagerImpl
 * JD-Core Version:    0.6.2
 */