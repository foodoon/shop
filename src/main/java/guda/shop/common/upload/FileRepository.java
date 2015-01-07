package guda.shop.common.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

public class FileRepository
  implements Repository, ServletContextAware
{
  private Logger _$2 = LoggerFactory.getLogger(FileRepository.class);
  private ServletContext _$1;

  public String storeByExt(String paramString1, String paramString2, MultipartFile paramMultipartFile)
    throws IOException
  {
    String str = UploadUtils.generateFilename(paramString1, paramString2);
    File localFile = new File(this._$1.getRealPath(str));
    localFile = UploadUtils.getUniqueFile(localFile);
    _$1(paramMultipartFile, localFile);
    return str;
  }

  private void _$1(MultipartFile paramMultipartFile, File paramFile)
    throws IOException
  {
    try
    {
      UploadUtils.checkDirAndCreate(paramFile.getParentFile());
      paramMultipartFile.transferTo(paramFile);
    }
    catch (IOException localIOException)
    {
      this._$2.error("Transfer file error when upload file", localIOException);
      throw localIOException;
    }
  }

  public void setServletContext(ServletContext paramServletContext)
  {
    this._$1 = paramServletContext;
  }

  public boolean store(String paramString, InputStream paramInputStream)
    throws FileNotFoundException, IOException
  {
    IOUtils.copy(paramInputStream, new FileOutputStream(this._$1.getRealPath(paramString)));
    return true;
  }

  public boolean retrieve(String paramString, OutputStream paramOutputStream)
  {
    return false;
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.upload.FileRepository
 * JD-Core Version:    0.6.2
 */