package guda.shop.core.tpl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class FileTpl
  implements Tpl
{
  private File _$2;
  private String _$1;

  public FileTpl(File paramFile, String paramString)
  {
    this._$2 = paramFile;
    this._$1 = paramString;
  }

  public String getName()
  {
    String str = this._$2.getAbsolutePath().substring(this._$1.length());
    str = str.replace(File.separatorChar, '/');
    if (!str.startsWith("/"))
      str = "/" + str;
    return str;
  }

  public String getPath()
  {
    String str = getName();
    return str.substring(0, str.lastIndexOf('/'));
  }

  public String getFilename()
  {
    return this._$2.getName();
  }

  public String getSource()
  {
    if (this._$2.isDirectory())
      return null;
    try
    {
      return FileUtils.readFileToString(this._$2, "UTF-8");
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }

  public long getLastModified()
  {
    return this._$2.lastModified();
  }

  public Date getLastModifiedDate()
  {
    return new Timestamp(getLastModified());
  }

  public long getLength()
  {
    return this._$2.length();
  }

  public int getSize()
  {
    return (int)(getLength() / 1024L) + 1;
  }

  public boolean isDirectory()
  {
    return this._$2.isDirectory();
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.tpl.FileTpl
 * JD-Core Version:    0.6.2
 */