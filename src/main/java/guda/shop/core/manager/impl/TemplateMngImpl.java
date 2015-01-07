package guda.shop.core.manager.impl;

import guda.shop.common.file.FileWrap;
import com.jspgou.core.manager.TemplateMng;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class TemplateMngImpl
  implements TemplateMng
{
  private static final Logger _$1 = LoggerFactory.getLogger(TemplateMngImpl.class);
  private FileFilter resFilter = new FileFilter()
  {
    public boolean accept(File paramAnonymousFile)
    {
      if (paramAnonymousFile.isDirectory())
        return true;
      String str = FilenameUtils.getExtension(paramAnonymousFile.getName());
      return FileWrap.allowEdit(str);
    }
  };

  public FileWrap getTplFileWrap(String paramString1, String paramString2)
  {
    FileWrap localFileWrap = new FileWrap(new File(paramString1), paramString1);
    localFileWrap.setFilename(paramString2);
    return localFileWrap;
  }

  public FileWrap getResFileWrap(String paramString1, String paramString2)
  {
    FileWrap localFileWrap = new FileWrap(new File(paramString1), paramString1, this.resFilter);
    localFileWrap.setFilename(paramString2);
    return localFileWrap;
  }

  public int uploadResourceFile(String paramString, MultipartFile[] paramArrayOfMultipartFile)
  {
    if ((paramArrayOfMultipartFile == null) || (paramArrayOfMultipartFile.length == 0))
      return 0;
    File localFile = new File(paramString);
    int i = 0;
    try
    {
      for (MultipartFile localMultipartFile : paramArrayOfMultipartFile)
      {
        String str = localMultipartFile.getOriginalFilename();
        if ((!localMultipartFile.isEmpty()) && (allowUpload(FilenameUtils.getExtension(str))))
        {
          localMultipartFile.transferTo(new File(localFile, str));
          i++;
        }
      }
    }
    catch (IOException localIOException)
    {
      _$1.error("upload resource failed", localIOException);
    }
    return i;
  }

  public boolean allowUpload(String paramString)
  {
    return true;
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.manager.impl.TemplateMngImpl
 * JD-Core Version:    0.6.2
 */