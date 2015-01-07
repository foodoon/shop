package guda.shop.common.web.freemarker;

import freemarker.template.TemplateModelException;

public class MustNumberException extends TemplateModelException
{
  public MustNumberException(String paramString)
  {
    super("The \"" + paramString + "\" parameter must be a number.");
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.freemarker.MustNumberException
 * JD-Core Version:    0.6.2
 */