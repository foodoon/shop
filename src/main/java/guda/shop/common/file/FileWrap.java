package guda.shop.common.file;

import guda.shop.common.image.ImageUtils;
import java.io.File;
import java.io.FileFilter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;

public class FileWrap
{
  public static final String[] EDITABLE_EXT = { "html", "htm", "css", "js", "txt" };
  private File _$5;
  private String _$4;
  private FileFilter _$3;
  private List<FileWrap> _$2;
  private String _$1;

  public FileWrap(File paramFile)
  {
    this(paramFile, null);
  }

  public FileWrap(File paramFile, String paramString)
  {
    this(paramFile, paramString, null);
  }

  public FileWrap(File paramFile, String paramString, FileFilter paramFileFilter)
  {
    this._$5 = paramFile;
    this._$4 = paramString;
    this._$3 = paramFileFilter;
  }

  public static boolean allowEdit(String paramString)
  {
    paramString = paramString.toLowerCase();
    for (String str : EDITABLE_EXT)
      if (str.equals(paramString))
        return true;
    return false;
  }

  public static boolean editableExt(String paramString)
  {
    paramString = paramString.toLowerCase(Locale.ENGLISH);
    for (String str : EDITABLE_EXT)
      if (str.equals(paramString))
        return true;
    return false;
  }

  public String getName()
  {
    String str1 = this._$5.getAbsolutePath();
    String str2 = str1.substring(this._$4.length());
    return str2.replace(File.separator, "/");
  }

  public String getPath()
  {
    String str = getName();
    return str.substring(0, str.lastIndexOf('/'));
  }

  public String getFilename()
  {
    return this._$1 != null ? this._$1 : this._$5.getName();
  }

  public String getExtension()
  {
    return FilenameUtils.getExtension(this._$5.getName());
  }

  public long getLastModified()
  {
    return this._$5.lastModified();
  }

  public Date getLastModifiedDate()
  {
    return new Timestamp(this._$5.lastModified());
  }

  public boolean isImage()
  {
    if (isDirectory())
      return false;
    String str = getExtension();
    return ImageUtils.isImage(str);
  }

  public boolean isEditable()
  {
    if (isDirectory())
      return false;
    String str1 = getExtension().toLowerCase();
    for (String str2 : EDITABLE_EXT)
      if (str2.equals(str1))
        return true;
    return false;
  }

  public boolean isDirectory()
  {
    return this._$5.isDirectory();
  }

  public boolean isFile()
  {
    return this._$5.isFile();
  }

  public long getSize()
  {
    return this._$5.length() / 1024L + 1L;
  }

  public String getRelPath()
  {
    String str1 = this._$5.getAbsolutePath();
    String str2 = str1.substring(this._$4.length());
    return str2.replace(File.separator, "/");
  }

  public String getIco()
  {
    if (this._$5.isDirectory())
      return "folder";
    String str = getExtension().toLowerCase();
    if ((str.equals("jpg")) || (str.equals("jpeg")))
      return "jpg";
    if (str.equals("gif"))
      return "gif";
    if ((str.equals("html")) || (str.equals("htm")))
      return "html";
    if (str.equals("swf"))
      return "swf";
    if (str.equals("txt"))
      return "txt";
    return "unknow";
  }

  public List<FileWrap> getChild()
  {
    if (this._$2 != null)
      return this._$2;
    File[] arrayOfFile1;
    if (this._$3 == null)
      arrayOfFile1 = getFile().listFiles();
    else
      arrayOfFile1 = getFile().listFiles(this._$3);
    ArrayList localArrayList = new ArrayList();
    if (arrayOfFile1 != null)
    {
      Arrays.sort(arrayOfFile1, new FileComparator());
      for (File localFile : arrayOfFile1)
      {
        FileWrap localFileWrap = new FileWrap(localFile, this._$4, this._$3);
        localArrayList.add(localFileWrap);
      }
    }
    return localArrayList;
  }

  public File getFile()
  {
    return this._$5;
  }

  public void setFile(File paramFile)
  {
    this._$5 = paramFile;
  }

  public void setFilename(String paramString)
  {
    this._$1 = paramString;
  }

  public void setChild(List<FileWrap> paramList)
  {
    this._$2 = paramList;
  }

  public static class FileComparator
    implements Comparator<File>
  {
    public int compare(File paramFile1, File paramFile2)
    {
      if ((paramFile1.isDirectory()) && (!paramFile2.isDirectory()))
        return -1;
      if ((!paramFile1.isDirectory()) && (paramFile2.isDirectory()))
        return 1;
      return paramFile1.compareTo(paramFile2);
    }
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.file.FileWrap
 * JD-Core Version:    0.6.2
 */