package guda.shop.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class Zipper
{
  private static final Logger _$3 = LoggerFactory.getLogger(Zipper.class);
  private byte[] _$2 = new byte[1024];
  private ZipOutputStream _$1;

  public static void zip(OutputStream paramOutputStream, List<FileEntry> paramList, String paramString)
  {
    new Zipper(paramOutputStream, paramList, paramString);
  }

  public static void zip(OutputStream paramOutputStream, List<FileEntry> paramList)
  {
    new Zipper(paramOutputStream, paramList, null);
  }

  protected Zipper(OutputStream paramOutputStream, List<FileEntry> paramList, String paramString)
  {
    Assert.notEmpty(paramList);
    long l1 = System.currentTimeMillis();
    _$3.debug("开始制作压缩包");
    try
    {
      try
      {
        this._$1 = new ZipOutputStream(paramOutputStream);
        if (!StringUtils.isBlank(paramString))
        {
          _$3.debug("using encoding: {}", paramString);
          this._$1.setEncoding(paramString);
        }
        else
        {
          _$3.debug("using default encoding");
        }
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          FileEntry localFileEntry = (FileEntry)localIterator.next();
          _$1(localFileEntry.getFile(), localFileEntry.getFilter(), localFileEntry.getZipEntry(), localFileEntry.getPrefix());
        }
      }
      finally
      {
        this._$1.close();
      }
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("制作压缩包时，出现IO异常！", localIOException);
    }
    long l2 = System.currentTimeMillis();
    _$3.info("制作压缩包成功。耗时：{}ms。", Long.valueOf(l2 - l1));
  }

  private void _$1(File paramFile, FilenameFilter paramFilenameFilter, ZipEntry paramZipEntry, String paramString)
    throws IOException
  {
    ZipEntry localZipEntry;
    Object localObject1;
    if (paramFile.isDirectory())
    {
      if (paramZipEntry == null)
        localZipEntry = new ZipEntry(paramFile.getName());
      else
        localZipEntry = new ZipEntry(paramZipEntry.getName() + "/" + paramFile.getName());
      localObject1 = paramFile.listFiles(paramFilenameFilter);
      for (File localFile : localObject1)
        _$1(localFile, paramFilenameFilter, localZipEntry, paramString);
    }
    else
    {
      if (paramZipEntry == null)
        localZipEntry = new ZipEntry(paramString + paramFile.getName());
      else
        localZipEntry = new ZipEntry(paramZipEntry.getName() + "/" + paramString + paramFile.getName());
      try
      {
        _$3.debug("读取文件：{}", paramFile.getAbsolutePath());
        localObject1 = new FileInputStream(paramFile);
        try
        {
          this._$1.putNextEntry(localZipEntry);
          int i;
          while ((i = ((FileInputStream)localObject1).read(this._$2)) > 0)
            this._$1.write(this._$2, 0, i);
          this._$1.closeEntry();
        }
        finally
        {
          ((FileInputStream)localObject1).close();
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        throw new RuntimeException("制作压缩包时，源文件不存在：" + paramFile.getAbsolutePath(), localFileNotFoundException);
      }
    }
  }

  public static class FileEntry
  {
    private FilenameFilter _$4;
    private String _$3;
    private File _$2;
    private String _$1;

    public FileEntry(String paramString1, String paramString2, File paramFile, FilenameFilter paramFilenameFilter)
    {
      this._$3 = paramString1;
      this._$1 = paramString2;
      this._$2 = paramFile;
      this._$4 = paramFilenameFilter;
    }

    public FileEntry(String paramString, File paramFile)
    {
      this._$3 = paramString;
      this._$2 = paramFile;
    }

    public FileEntry(String paramString1, String paramString2, File paramFile)
    {
      this(paramString1, paramString2, paramFile, null);
    }

    public ZipEntry getZipEntry()
    {
      if (StringUtils.isBlank(this._$3))
        return null;
      return new ZipEntry(this._$3);
    }

    public FilenameFilter getFilter()
    {
      return this._$4;
    }

    public void setFilter(FilenameFilter paramFilenameFilter)
    {
      this._$4 = paramFilenameFilter;
    }

    public String getParent()
    {
      return this._$3;
    }

    public void setParent(String paramString)
    {
      this._$3 = paramString;
    }

    public File getFile()
    {
      return this._$2;
    }

    public void setFile(File paramFile)
    {
      this._$2 = paramFile;
    }

    public String getPrefix()
    {
      if (this._$1 == null)
        return "";
      return this._$1;
    }

    public void setPrefix(String paramString)
    {
      this._$1 = paramString;
    }
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.util.Zipper
 * JD-Core Version:    0.6.2
 */