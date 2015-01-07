package guda.shop.common.fck;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class UploadResponse
{
  protected Object[] parameters;
  public static final int EN_OK = 0;
  public static final int EN_CUSTOM_ERROR = 1;
  public static final int EN_CUSTOM_WARNING = 101;
  public static final int EN_FILE_RENAMED_WARNING = 201;
  public static final int EN_INVALID_FILE_TYPE_ERROR = 202;
  public static final int EN_SECURITY_ERROR = 203;

  public UploadResponse(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject.length < 1) || (paramArrayOfObject.length > 4))
      throw new IllegalArgumentException("The amount of arguments has to be between 1 and 4");
    this.parameters = new Object[paramArrayOfObject.length];
    if (!(paramArrayOfObject[0] instanceof Integer))
      throw new IllegalArgumentException("The first argument has to be an error number (int)");
    System.arraycopy(paramArrayOfObject, 0, this.parameters, 0, paramArrayOfObject.length);
  }

  public void setCustomMessage(String paramString)
  {
    if (!StringUtils.isBlank(paramString))
    {
      if (this.parameters.length == 1)
      {
        Object localObject = this.parameters[0];
        this.parameters = new Object[4];
        this.parameters[0] = localObject;
        this.parameters[1] = null;
        this.parameters[2] = null;
      }
      this.parameters[3] = paramString;
    }
  }

  public static UploadResponse getOK(HttpServletRequest paramHttpServletRequest, String paramString)
  {
    String str = paramHttpServletRequest.getParameter("CKEditorFuncNum");
    return new UploadResponse(new Object[] { Integer.valueOf(Integer.parseInt(str)), paramString });
  }

  public static UploadResponse getFileRenamedWarning(HttpServletRequest paramHttpServletRequest, String paramString1, String paramString2)
  {
    return new UploadResponse(new Object[] { Integer.valueOf(201), paramString1, paramString2, LocalizedMessages.getFileRenamedWarning(paramHttpServletRequest, paramString2) });
  }

  public static UploadResponse getInvalidFileTypeError(HttpServletRequest paramHttpServletRequest)
  {
    return new UploadResponse(new Object[] { Integer.valueOf(202), LocalizedMessages.getInvalidFileTypeSpecified(paramHttpServletRequest) });
  }

  public static UploadResponse getInvalidCommandError(HttpServletRequest paramHttpServletRequest)
  {
    return new UploadResponse(new Object[] { Integer.valueOf(1), null, null, LocalizedMessages.getInvalidCommandSpecified(paramHttpServletRequest) });
  }

  public static UploadResponse getInvalidResourceTypeError(HttpServletRequest paramHttpServletRequest)
  {
    return new UploadResponse(new Object[] { Integer.valueOf(1), null, null, LocalizedMessages.getInvalidResouceTypeSpecified(paramHttpServletRequest) });
  }

  public static UploadResponse getInvalidCurrentFolderError(HttpServletRequest paramHttpServletRequest)
  {
    return new UploadResponse(new Object[] { Integer.valueOf(1), null, null, LocalizedMessages.getInvalidCurrentFolderSpecified(paramHttpServletRequest) });
  }

  public static UploadResponse getFileUploadDisabledError(HttpServletRequest paramHttpServletRequest)
  {
    return new UploadResponse(new Object[] { Integer.valueOf(203), null, null, LocalizedMessages.getFileUploadDisabled(paramHttpServletRequest) });
  }

  public static UploadResponse getFileUploadWriteError(HttpServletRequest paramHttpServletRequest)
  {
    return new UploadResponse(new Object[] { Integer.valueOf(1), null, null, LocalizedMessages.getFileUploadWriteError(paramHttpServletRequest) });
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(400);
    localStringBuffer.append("<script type=\"text/javascript\">\n");
    localStringBuffer.append("(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n");
    localStringBuffer.append("window.parent.CKEDITOR.tools.callFunction(");
    for (Object localObject : this.parameters)
    {
      if ((localObject instanceof Integer))
      {
        localStringBuffer.append(localObject);
      }
      else
      {
        localStringBuffer.append("'");
        if (localObject != null)
          localStringBuffer.append(localObject);
        localStringBuffer.append("'");
      }
      localStringBuffer.append(",");
    }
    localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
    localStringBuffer.append(");\n");
    localStringBuffer.append("</script>");
    return localStringBuffer.toString();
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.fck.UploadResponse
 * JD-Core Version:    0.6.2
 */