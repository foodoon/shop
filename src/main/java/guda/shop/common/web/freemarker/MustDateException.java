package guda.shop.common.web.freemarker;

import freemarker.template.TemplateModelException;

public class MustDateException extends TemplateModelException
{
  public MustDateException(String paramString)
  {
    super("The \"" + paramString + "\" parameter must be a date.");
  }
}

/* Location:           D:\demo22\jspgou-common.jar
 * Qualified Name:     com.jspgou.common.web.freemarker.MustDateException
 * JD-Core Version:    0.6.2
 */