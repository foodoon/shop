package guda.shop.core.web;

import java.io.Serializable;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;

public class WebErrors extends com.jspgou.common.web.springmvc.WebErrors
{
  public static final String ERROR_PAGE = "/common/error_message";
  public static final String ERROR_ATTR_NAME = "errors";

  public static WebErrors create(HttpServletRequest paramHttpServletRequest)
  {
    return new WebErrors(paramHttpServletRequest);
  }

  public WebErrors()
  {
  }

  public WebErrors(HttpServletRequest paramHttpServletRequest)
  {
    super(paramHttpServletRequest);
  }

  public WebErrors(MessageSource paramMessageSource, Locale paramLocale)
  {
    super(paramMessageSource, paramLocale);
  }

  public void notInWeb(Class paramClass, Serializable paramSerializable)
  {
    addErrorCode("error.notInWeb", new Object[] { paramClass.getSimpleName(), paramSerializable });
  }

  protected String getErrorAttrName()
  {
    return "errors";
  }

  protected String getErrorPage()
  {
    return "/common/error_message";
  }
}

/* Location:           D:\demo22\jspgou-core.jar
 * Qualified Name:     com.jspgou.core.web.WebErrors
 * JD-Core Version:    0.6.2
 */